package com.tia102g3.systemcourse.model;

import com.tia102g3.basedao.BaseDAO;
import com.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
@Repository
public class SystemCourseDAOImpl extends BaseDAO<SystemCourse> implements SystemCourseDAO {
    @Autowired
    private JDBCUtils jdbcUtils;
    @Override
    public int insertSystemCourse(SystemCourse systemCourse) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "INSERT INTO system_course (courseName, sportEventName, sportTypes, sportEquipment, courseLevel, burnCalories, rps, eachExerciseTime, sportTime, swp, illustrate, video) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        return super.update(conn, sql, systemCourse.getCourseName(), systemCourse.getSportEventName(), systemCourse.getSportTypes(), systemCourse.getSportEquipment(), systemCourse.getCourseLevel().getLevel(), systemCourse.getBurnCalories(), systemCourse.getRps(), systemCourse.getEachExerciseTime(), systemCourse.getSportTime(), systemCourse.getSwp(), systemCourse.getIllustrate(), systemCourse.getVideo());
    }

    @Override
    public SystemCourse getSystemCourseById(Integer systemCourseID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "SELECT * FROM system_course WHERE systemCourseID=?";
        return getInstance(conn, sql, systemCourseID);
    }

    @Override
    public int updateSystemCourse(SystemCourse systemCourse) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "UPDATE system_course SET courseName=?, sportEventName=?, sportTypes=?, sportEquipment=?, courseLevel=?, burnCalories=?, rps=?, eachExerciseTime=?, sportTime=?, swp=?, illustrate=?, video=? WHERE systemCourseID=?";
        return update(conn, sql, systemCourse.getCourseName(), systemCourse.getSportEventName(), systemCourse.getSportTypes(), systemCourse.getSportEquipment(), systemCourse.getCourseLevel().getLevel(), systemCourse.getBurnCalories(), systemCourse.getRps(), systemCourse.getEachExerciseTime(), systemCourse.getSportTime(), systemCourse.getSwp(), systemCourse.getIllustrate(), systemCourse.getVideo(), systemCourse.getSystemCourseID());
    }

    @Override
    public int deleteSystemCourse(Integer systemCourseID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "DELETE FROM system_course WHERE systemCourseID=?";
        return update(conn, sql, systemCourseID);
    }

    @Override
    public List<SystemCourse> getAllSystemCourses() throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "SELECT * FROM system_course";
        return getForList(conn, sql);
    }
}
