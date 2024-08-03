package com.tia102g3.systemcoursepic.model;

import com.tia102g3.basedao.BaseDAO;
import com.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

/**
 * ClassName： SystemCoursePicDAOImpl
 * package：com.tia102g3.systemcoursepic.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
@Repository
public class SystemCoursePicDAOImpl extends BaseDAO<SystemCoursePic> implements SystemCoursePicDAO {
    @Autowired
    private JDBCUtils jdbcUtils;
    @Override
    public int insertSystemCoursePic(SystemCoursePic systemCoursePic) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "insert into system_course_pic (systemCourseID, pic) values (?,?)";
        return super.update(conn, sql, systemCoursePic.getSystemCourse().getSystemCourseID(), systemCoursePic.getPic());
    }

    @Override
    public int deleteSystemCoursePicByID(Integer systemCoursePicID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "delete from system_course_pic where by id = ?";
        return super.update(conn, sql, systemCoursePicID);
    }

    @Override
    public int updateSystemCoursePic(SystemCoursePic systemCoursePic) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "update system_course_pic set pic = ? where by id = ?";
        return super.update(conn, sql, systemCoursePic.getPic(), systemCoursePic.getSystemCoursePicID());
    }

    @Override
    public SystemCoursePic selectSystemCoursePicByID(Integer systemCoursePicID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "select * from system_course_pic by id = ?";
        return super.getInstance(conn, sql, systemCoursePicID);
    }
}
