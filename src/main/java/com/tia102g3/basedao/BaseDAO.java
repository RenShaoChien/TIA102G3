package com.tia102g3.basedao;

import com.utils.JDBCUtils;

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
public abstract class BaseDAO<T> {
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
     * @param conn
     * @param sql
     * @param args
     * @return
     */
    public int update(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        int executeUpdate = 0;
        try {
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
     * @param conn
     * @param sql
     * @param args
     * @return
     */
    public T getInstance(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            //獲取結果集的元數據
            ResultSetMetaData rsmd = rs.getMetaData();
            //獲取結果集的列數
            int columnCount = rsmd.getColumnCount();

            if (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    Field field = clazz.getDeclaredField(columnLabel);
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
                        // 處理枚舉型別
                        Object enumValue = getEnumValue(field.getType(), columnValue);
                        field.set(t, enumValue);
                    } else {
                        field.set(t, columnValue);
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

    /**
     * 通用的【查詢】操作，用於返回資料庫中的多筆資料構成的集合(有考慮交易)
     * @param conn
     * @param sql
     * @param args
     * @return
     */
    public List<T> getForList(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> list = null;
        try {
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
                    Field field = clazz.getDeclaredField(columnLabel);
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
                        // 處理枚舉型別
                        Object enumValue = getEnumValue(field.getType(), columnValue);
                        field.set(t, enumValue);
                    }else
                        field.set(t, columnValue);
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
     * @param conn
     * @param sql
     * @param args
     * @return
     * @param <E>
     */
    public <E> E getValue(Connection conn, String sql, Object ... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            if (rs.next()){
                return (E)rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }


    /**
     * 將資料庫欄位值轉換為枚舉值
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
}
