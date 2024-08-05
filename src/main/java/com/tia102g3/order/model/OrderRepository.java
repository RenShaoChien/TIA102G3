package com.tia102g3.order.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<OrderVO, Integer>{

	@Transactional
	@Modifying
	@Query(value = "delet from orderid where orderID = ?1", nativeQuery = true)
	void deldetByOrderID(int orderID);
	
	
	@Query(value = "from OrderVO where orderID =?1 and status like?2 and orderDate=?3 order by orderID")
	List<OrderVO> findByOthers(int orderID , String status , java.sql.Timestamp orderDate);
}