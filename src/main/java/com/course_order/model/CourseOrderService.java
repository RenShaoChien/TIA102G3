package com.course_order.model;

import java.sql.Date;
import java.util.List;

public class CourseOrderService {

	private CourseOrderDAO_interface dao;

	public CourseOrderService() {
		dao = new CourseOrderDAO();
	}

	public CourseOrderVO addCourseOrder(Integer memberID, Integer coachCourseID, Date orderDate, Integer price, String status) {

		CourseOrderVO courseOrderVO = new CourseOrderVO();

		courseOrderVO.setMemberID(memberID);
		courseOrderVO.setCoachCourseID(coachCourseID);
		courseOrderVO.setOrderDate(orderDate);
		courseOrderVO.setPrice(price);
		courseOrderVO.setStatus(status);

		dao.insert(courseOrderVO);

		return courseOrderVO;
	}

	public CourseOrderVO updateCourseOrder(Integer courseOrderID, Integer memberID, Integer coachCourseID, Date orderDate, Integer price, String status) {

		CourseOrderVO courseOrderVO = new CourseOrderVO();

		courseOrderVO.setCourseOrderID(courseOrderID);
		courseOrderVO.setMemberID(memberID);
		courseOrderVO.setCoachCourseID(coachCourseID);
		courseOrderVO.setOrderDate(orderDate);
		courseOrderVO.setPrice(price);
		courseOrderVO.setStatus(status);

		dao.update(courseOrderVO);

		return courseOrderVO;
	}

	public void deleteCourseOrder(Integer courseOrderID) {
		dao.delete(courseOrderID);
	}

	public CourseOrderVO getOneCourseOrder(Integer courseOrderID) {
		return dao.getOne(courseOrderID);
	}

	public List<CourseOrderVO> getAll() {
		return dao.getAll();
	}
}
