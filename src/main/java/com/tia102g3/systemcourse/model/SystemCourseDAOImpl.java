package com.tia102g3.systemcourse.model;

import com.utils.HibernateUtil;
import com.utils.JDBCUtils;
import com.tia102g3.basedao.BaseDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.util.List;

/**
 * ClassName： SystemCourseDAOImpl
 * package：com.tia102g3.systemcourse.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
public class SystemCourseDAOImpl extends BaseDAO<SystemCourse> implements SystemCourseDAO {
    private SessionFactory sessionFactory;

    public SystemCourseDAOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int insertSystemCourse(SystemCourse systemCourse) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "INSERT INTO system_course (courseName, sportEventID, courseLevel, burnCalories, rps, eachExerciseTime, sportTime, swp, illustrate, video) VALUES (?,?,?,?,?,?,?,?,?,?)";
        return super.update(conn, sql, systemCourse.getCourseName(), systemCourse.getSportEvent().getSportEventID(), systemCourse.getCourseLevel(), systemCourse.getBurnCalories(), systemCourse.getRps(), systemCourse.getEachExerciseTime(), systemCourse.getSportTime(), systemCourse.getSwp(), systemCourse.getIllustrate(), systemCourse.getVideo());
    }

    @Override
    public SystemCourse getSystemCourseById(Integer systemCourseID) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "SELECT * FROM system_course WHERE systemCourseID=?";
        return getInstance(conn, sql, systemCourseID);
    }

    @Override
    public int updateSystemCourse(SystemCourse systemCourse) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "UPDATE system_course SET courseName=?, sportEventID=?, courseLevel=?, burnCalories=?, rps=?, eachExerciseTime=?, sportTime=?, swp=?, illustrate=?, video=? WHERE systemCourseID=?";
        return update(conn, sql, systemCourse.getCourseName(), systemCourse.getSportEvent().getSportEventID(), systemCourse.getCourseLevel(), systemCourse.getBurnCalories(), systemCourse.getRps(), systemCourse.getEachExerciseTime(), systemCourse.getSportTime(), systemCourse.getSwp(), systemCourse.getIllustrate(), systemCourse.getVideo(), systemCourse.getSystemCourseID());
    }

    @Override
    public int deleteSystemCourse(Integer systemCourseID) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "DELETE FROM system_course WHERE systemCourseID=?";
        return update(conn, sql, systemCourseID);
    }

    @Override
    public List<SystemCourse> getAllSystemCourses() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "SELECT * FROM system_course";
        return getForList(conn, sql);
    }
}
