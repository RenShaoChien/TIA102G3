package com.tia102g3.customizedcourse.model;

import com.tia102g3.basedao.BaseDAO;
import com.utils.HibernateUtil;
import com.utils.JDBCUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.util.List;

/**
 * ClassName： CustomizedCourseDAOImpl
 * package：com.tia102g3.customizedcourse.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/19 @{TIME}
 * @Version 1.0
 */
public class CustomizedCourseDAOImpl extends BaseDAO<CustomizedCourse> implements CustomizedCourseDAO {
    private SessionFactory sessionFactory;

    public CustomizedCourseDAOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int insertCustomizedCourse(CustomizedCourse customizedCourse) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "INSERT INTO customized_course (systemCourseID, memberID) VALUES (?, ?)";
        return update(conn, sql, customizedCourse.getSystemCourse().getSystemCourseID(), customizedCourse.getMember().getMemberID());
    }

    @Override
    public int updateCustomizedCourse(CustomizedCourse customizedCourse) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "UPDATE customized_course SET systemCourseID=?, memberID=? WHERE customizeCourseID=?";
        return update(conn, sql, customizedCourse.getSystemCourse().getSystemCourseID(), customizedCourse.getMember().getMemberID(), customizedCourse.getCustomizedCourseID());
    }

    @Override
    public int deleteCustomizedCourseByID(Integer customizeCourseID) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "DELETE FROM customized_course WHERE customizeCourseID=?";
        return update(conn, sql, customizeCourseID);
    }

    @Override
    public CustomizedCourse selectCustomizedCourseById(Integer customizeCourseID) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "SELECT * FROM customized_course WHERE customizeCourseID=?";
        return getInstance(conn, sql, customizeCourseID);
    }

    @Override
    public List<CustomizedCourse> getCustomizedCourseList() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "SELECT * FROM customized_course";
        return getForList(conn, sql);
    }

    @Override
    public List<CustomizedCourse> getCustomizedCourseListByMemberID(Integer memberID) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "SELECT * FROM customized_course WHERE memberID=?";
        return getForList(conn, sql, memberID);
    }
}
