package com.tia102g3.coachcoursepic.model;

import com.utils.HibernateUtil;
import com.utils.JDBCUtils;
import com.tia102g3.basedao.BaseDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.util.List;

/**
 * ClassName： CoachCoursePicDAOImpl
 * package：com.tia102g3.coachcoursepic
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
public class CoachCoursePicDAOImpl extends BaseDAO<CoachCoursePic> implements CoachCoursePicDAO {
    private SessionFactory sessionFactory;

    public CoachCoursePicDAOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    @Override
    public List<CoachCoursePic> getAllList() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "SELECT * FROM coach_course_pic";
        return getForList(conn, sql);
    }

    @Override
    public CoachCoursePic getOneByID(Integer coachCoursePicID) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "SELECT * FROM coach_course_pic WHERE coachCoursePicID=?";
        return getInstance(conn, sql, coachCoursePicID);
    }

    @Override
    public int insert(CoachCoursePic coachCoursePic) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "INSERT INTO coach_course_pic (coachCourseID, pic) VALUES (?,?)";
        return update(conn, sql, coachCoursePic.getCoachCourse().getCoachCourseID(), coachCoursePic.getPic());
    }

    @Override
    public int update(CoachCoursePic coachCoursePic) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "UPDATE coach_course_pic SET pic=? WHERE coachCourseID=?";
        return update(conn, sql, coachCoursePic.getPic(), coachCoursePic.getCoachCourse().getCoachCourseID());
    }

    @Override
    public int deleteById(Integer coachCoursePicID) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "DELETE FROM coach_course_pic WHERE coachCoursePicID=?";
        return update(conn, sql, coachCoursePicID);
    }
}
