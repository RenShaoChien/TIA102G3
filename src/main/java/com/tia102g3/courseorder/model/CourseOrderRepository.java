// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.tia102g3.courseorder.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CourseOrderRepository extends JpaRepository<CourseOrder, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from course_order where courseOrderID =?1", nativeQuery = true)
	void deleteByCourseOrderID(int courseOrderID);

	//● (自訂)條件查詢
	@Query(value = "from CourseOrder where courseOrderID=?1 and memberID like?2 and orderDate=?3 order by courseOrderID")
	List<CourseOrder> findByOthers(int courseOrderID , int memberID , java.sql.Date orderDate);
}