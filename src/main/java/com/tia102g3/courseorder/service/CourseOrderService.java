package com.tia102g3.courseorder.service;

import com.tia102g3.courseorder.model.CourseOrder;
import com.tia102g3.courseorder.model.CourseOrderRepository;
import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_CourseOrder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("courseOrderService")
public class CourseOrderService {

	@Autowired
	CourseOrderRepository repository; // 自動注入 CourseOrderRepository 物件

	@Autowired
	private SessionFactory sessionFactory; // 自動注入 Hibernate 的 SessionFactory 物件

	// 新增會員的方法
	public void addCourseOrder(CourseOrder courseOrder) {
		repository.save(courseOrder); // 使用 repository 保存會員資料
	}

	// 更新會員資料的方法
	public void updateCourseOrder(CourseOrder courseOrder) {
		repository.save(courseOrder); // 使用 repository 更新會員資料
	}

	// 刪除會員的方法，根據會員 ID
	public void deleteCourseOrder(Integer courseOrderID) {
		if (repository.existsById(courseOrderID)) // 檢查該會員 ID 是否存在
			repository.deleteByCourseOrderID(courseOrderID); // 若存在則刪除會員資料
	}

	// 根據會員 ID 取得會員資料的方法
	public CourseOrder getOneCourseOrder(Integer courseOrderID) {
		Optional<CourseOrder> optional = repository.findById(courseOrderID); // 查找會員資料，回傳 Optional 物件
		return optional.orElse(null); // 若資料存在則回傳，否則回傳 null
	}

	// 取得所有會員資料的方法
	public List<CourseOrder> getAll() {
		return repository.findAll(); // 使用 repository 取得所有會員資料
	}

	// 調用 HibernateUtil_CompositeQuery_Emp3 的 getAllC 方法，
	public List<CourseOrder> getAll(Map<String, String[]> map) {
		// 傳入查詢參數 map 和新開啟的 Hibernate Session，返回查詢結果的 CourseOrder 列表。
		return HibernateUtil_CompositeQuery_CourseOrder.getAllCourseOrders(map, sessionFactory.openSession());
	}
	@Transactional
	public Optional<CourseOrder> getOneByMemberIdAndCourseId(Integer memberID, Integer courseID) {
		return repository.findByMemberIdAndCourseId(memberID, courseID);
	}
}
