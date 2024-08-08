package com.tia102g3.courseorder.model;

import com.tia102g3.basedao.BaseDAO;
import com.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName： CourseOrderDAOImpl
 * package：com.tia102g3.courseorder.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/27 下午10:22
 * @Version 1.0
 */
@Repository
public class CourseOrderDAOImpl extends BaseDAO<CourseOrder> implements CourseOrderDAO {
    @Autowired
    private JDBCUtils jdbcUtils;
    @Override
    public int insert(CourseOrder courseOrder) throws Exception {

        String sql = "insert into course_order (memberID, coachCourseID, orderDate, price, status) values (?,?,?,?,?)";
        return super.update(sql, courseOrder.getMember().getMemberID(), courseOrder.getCoachCourse().getCoachCourseID(), courseOrder.getOrderDate(), courseOrder.getStatus(), courseOrder.getPrice());
    }

    @Override
    public int update(CourseOrder courseOrder) throws Exception {

        String sql = "update course_order set memberID=?, coachCourseID=?, orderDate=?, price=?, status=? where courseOrderID=?";
        return super.update(
                sql, courseOrder.getMember().getMemberID(), courseOrder.getCoachCourse().getCoachCourseID(),
                courseOrder.getOrderDate(), courseOrder.getPrice(), courseOrder.getStatus(), courseOrder.getCourseOrderID()
        );
    }

    @Override
    public int delete(CourseOrder courseOrder) throws Exception {

        String sql = "delete from course_order where courseOrderID=?";
        return super.update(sql, courseOrder.getCourseOrderID());
    }

    @Override
    public CourseOrder getCourseOrderByID(Integer courseOrderID) throws Exception {

        String sql = "SELECT * FROM course_order WHERE courseOrderID=?";
        return super.getInstance(sql, courseOrderID);
    }

    @Override
    public List<CourseOrder> getCourseOrdersList() throws Exception {

        String sql = "SELECT * FROM course_order";
        return super.getForList(sql);
    }
}
