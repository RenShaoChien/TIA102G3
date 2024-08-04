package com.tia102g3.coachcourse.model;

import com.tia102g3.basedao.BaseDAO;
import com.tia102g3.coachmember.model.CoachMember;
import com.tia102g3.sportevent.model.SportEvent;
import com.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * ClassName： CoachCourseDAOImpl
 * package：com.tia102g3.coachcourse.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/16 @{TIME}
 * @Version 1.0
 */
@Repository
public class CoachCourseDAOImpl extends BaseDAO<CoachCourse> implements CoachCourseDAO {
    @Autowired
    private JDBCUtils jdbcUtils;
    @Override
    public int insertCoachCourse(CoachCourse coachCourse) throws Exception {
        Connection conn = jdbcUtils.getConnection();

        String sql = "insert into CoachCourse(" +
                "classStartTime, classEndTime, cMemberID, courseName, courseLevel, courseStartDate, courseEndDate, noOfClasses, maxCap, status, sportEventID, coursePrice) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        return super.update(
                conn, sql, coachCourse.getClassStartTime(), coachCourse.getClassEndTime(), coachCourse.getCMember().getCMemberID(), coachCourse.getCourseName(), coachCourse.getCourseLevel().getLevel(),
                coachCourse.getCourseStartDate(), coachCourse.getCourseEndDate(), coachCourse.getNoOfClasses(), coachCourse.getMaxCap(),
                coachCourse.getStatus().getStatus(), coachCourse.getSportEvent().getSportEventID(), coachCourse.getCoursePrice()
        );
    }

    @Override
    public int updateCoachCourse(CoachCourse coachCourse) throws Exception {
        Connection conn = jdbcUtils.getConnection();

        String sql = "update CoachCourse set " +
                "classStartTime=?, classEndTime=?, cMemberID=?, courseName=?, courseLevel=?, courseStartTime=?, courseEndTime=?, noOfClasses=?, maxCap=?, status=?, sportEventID=?, coursePrice=? " +
                "where coachCourseID=?";

        return super.update(
                conn, sql, coachCourse.getClassStartTime(), coachCourse.getClassEndTime(), coachCourse.getCMember().getCMemberID(), coachCourse.getCourseName(), coachCourse.getCourseLevel().getLevel(), coachCourse.getCourseStartDate(), coachCourse.getCourseEndDate(),
                coachCourse.getNoOfClasses(), coachCourse.getMaxCap(), coachCourse.getStatus().getStatus(), coachCourse.getSportEvent().getSportEventID(), coachCourse.getCoursePrice(),
                coachCourse.getCoachCourseID()
        );
    }

    @Override
    public CoachCourse getCoachCourseByID(Integer coachCourseID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "select * from CoachCourse where coachCourseID=?";
        return super.getInstance(conn, sql, coachCourseID);
    }

    @Override
    public int deleteCoachCourseByID(Integer coachCourseID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "delete from CoachCourse where coachCourseID=?";
        return super.update(conn, sql, coachCourseID);
    }

    @Override
    public List<CoachCourse> getAllCoachCoursesList() throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "select * from CoachCourse";
        return super.getForList(conn, sql);
    }

    @Override
    public List<CoachCourse> findCoursesByCMember(CoachMember cMember) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "select * from CoachCourse where cMemberID=?";
        return super.getForList(conn, sql, cMember.getCMemberID());
    }

    @Override
    public List<CoachCourse> findCoursesByLevel(Integer courseLevel) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "select * from CoachCourse where courseLevel=?";
        return super.getForList(conn, sql, courseLevel);
    }

    @Override
    public List<CoachCourse> findCoursesBySportEvent(SportEvent sportEvent) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "select * from CoachCourse where sportEventID=?";
        return super.getForList(conn, sql, sportEvent.getSportEventID());
    }

    @Override
    public List<CoachCourse> findCoursesByDate(Date startDate) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "SELECT * FROM CoachCourses WHERE courseStartTime >= ?";
        return super.getForList(conn, sql, startDate);
    }

    @Override
    public List<CoachCourse> findActiveCourses(Date currentDate) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "SELECT * FROM CoachCourses WHERE courseStartTime <=? AND courseEndTime >=? AND status =?";
        return super.getForList(conn, sql, currentDate, currentDate, 1);
    }

    @Override
    public int updateCourseStatus(CoachCourse coachCourse, CourseStatus status) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "UPDATE CoachCourses SET status =? WHERE coachCourseID =?";
        return super.update(conn, sql, status.getStatus(), coachCourse.getCoachCourseID());
    }
}
