package com.tia102g3.basedao;

import com.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName： BaseDAO
 * package：orm.basedao
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/6/29 @{TIME}
 * @Version 1.0
 */
@Component
public abstract class BaseDAO<T> {

    @Autowired
    private DataSource dataSource;

    public Connection getConnection() throws SQLException {
        return DataSourceUtils.getConnection(dataSource);
    }

    private Class<T> clazz = null;

    {
        //獲取當前BaseDAO的子類繼承的父類中的泛型
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        clazz = (Class<T>) typeArguments[0];
    }

    /**
     * 通用【增刪改】操作(有考慮交易)
     *
     * @param sql
     * @param args
     * @return
     */
    public int update( String sql, Object... args) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = getConnection();
            System.out.println("conn = " + conn);
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, null);
        }
        return 0;
    }

    /**
     * 通用的【查詢】操作，用於返回資料庫中的一筆資料(有考慮交易)
     *
     * @param sql
     * @param args
     * @return
     */
    public T getInstance(String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            if (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    try {
                        Field field = null;
                        try {
                            field = clazz.getDeclaredField(columnLabel);
                        } catch (NoSuchFieldException e) {
                            field = findForeignKeyField(columnLabel);
                        }

                        if (field != null) {
                            field.setAccessible(true);
                            ForeignKey foreignKey = field.getAnnotation(ForeignKey.class);
                            if (foreignKey != null) {
                                Class<?> targetEntity = foreignKey.targetEntity();
                                String keyField = foreignKey.keyField();
                                Object foreignKeyInstance = targetEntity.newInstance();
                                Field idField = targetEntity.getDeclaredField(keyField);
                                idField.setAccessible(true);
                                idField.set(foreignKeyInstance, columnValue);
                                field.set(t, foreignKeyInstance);
                            } else if (field.getType().isEnum()) {
                                Object enumValue = getEnumValue(field.getType(), columnValue);
                                field.set(t, enumValue);
                            } else {
                                field.set(t, columnValue);
                            }
                        } else {
                            System.err.println("No such field: " + columnLabel);
                        }
                    } catch (Exception e) {
                        System.err.println("Error setting field value: " + columnLabel);
                        e.printStackTrace();
                    }
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }
    private Field findForeignKeyField(String columnLabel) throws NoSuchFieldException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ForeignKey foreignKey = field.getAnnotation(ForeignKey.class);
            if (foreignKey != null) {
                String keyField = foreignKey.keyField();
                if (keyField.equals(columnLabel)) {
                    return field;
                }
            }
        }
        throw new NoSuchFieldException(columnLabel);
    }

    /**
     * 通用的【查詢】操作，用於返回資料庫中的多筆資料構成的集合(有考慮交易)
     *
     * @param sql
     * @param args
     * @return
     */
    public List<T> getForList(String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> list = null;
        Connection conn = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            list = new ArrayList<>();

            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    try {
                        Field field = null;
                        try {
                            field = clazz.getDeclaredField(columnLabel);
                        } catch (NoSuchFieldException e) {
                            field = findForeignKeyField(columnLabel);
                        }

                        if (field != null) {
                            field.setAccessible(true);
                            ForeignKey foreignKey = field.getAnnotation(ForeignKey.class);
                            if (foreignKey != null) {
                                Class<?> targetEntity = foreignKey.targetEntity();
                                String keyField = foreignKey.keyField();
                                Object foreignKeyInstance = targetEntity.newInstance();
                                Field idField = targetEntity.getDeclaredField(keyField);
                                idField.setAccessible(true);
                                idField.set(foreignKeyInstance, columnValue);
                                field.set(t, foreignKeyInstance);
                            } else if (field.getType().isEnum()) {
                                Object enumValue = getEnumValue(field.getType(), columnValue);
                                field.set(t, enumValue);
                            } else {
                                field.set(t, columnValue);
                            }
                        } else {
                            System.err.println("No such field: " + columnLabel);
                        }
                    } catch (Exception e) {
                        System.err.println("Error setting field value: " + columnLabel);
                        e.printStackTrace();
                    }
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

    /**
     * 用於查詢特出值得通用的方法
     *
     * @param sql
     * @param args
     * @param <E>
     * @return
     */
    public <E> E getValue(String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            System.out.println("conn = " + conn);

            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return (E) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

    /**
     * 執行複雜查詢
     *
     * @param sql
     * @param params
     * @return 統計結果
     */
    protected Object[] executeComplexQuery(String sql, Object... params) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = getConnection();
            System.out.println("conn = " + conn);

            ps = conn.prepareStatement(sql);
            setParams(ps, params);
            rs = ps.executeQuery();

            //通過rs可以獲取結果集的元數據
            //元數據，描述結果集數據的數據，簡單講 就是這個結果集有哪些【欄、型別】等等
            ResultSetMetaData rsmd = rs.getMetaData();
            //獲取結果集的欄數
            int columnCount = rsmd.getColumnCount();
            Object[] columnValueArr = new Object[columnCount];
            //解析rs
            if (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    columnValueArr[i] = columnValue;
                }
                return columnValueArr;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

    private void setParams(PreparedStatement ps, Object... params) throws SQLException {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
    }

    /**
     * 將資料庫欄位值轉換為枚舉值
     *
     * @param enumType
     * @param columnValue
     * @return
     * @throws Exception
     */
    private Object getEnumValue(Class<?> enumType, Object columnValue) throws Exception {
        Method fromValueMethod = null;
        try {
            fromValueMethod = enumType.getMethod("getSystemCourseLevelByInt", columnValue.getClass());
            return fromValueMethod.invoke(null, columnValue);
        } catch (NoSuchMethodException e) {
            // Ignore, try valueOf
        }

        Method valueOfMethod = enumType.getMethod("valueOf", String.class);
        Object enumObject = valueOfMethod.invoke(null, columnValue.toString());
        return ((Enum<?>) enumObject).name(); // 返回枚舉名稱
    }

    /**
     * 插入資料獲取生成的主键
     *
     * @param sql
     * @param args
     * @return 生成的主键
     */
    protected int insertAndGetKey(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            System.out.println("conn = " + conn);

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return 0;
    }

}
