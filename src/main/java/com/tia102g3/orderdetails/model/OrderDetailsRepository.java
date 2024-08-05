package com.tia102g3.orderdetails.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderDetailsRepository extends JpaRepository<OrderDetailsVO, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "delet from order_details where ordDtlID = ?1", nativeQuery = true)
	void deldetByOrderDetails(int ordDtlID);
	
	@Query(value = "from OrderDetailsVO where ordDtlID = ?1 and productID like?2 and quantity=?3 order by ordDtlID")
	List<OrderDetailsVO> findByOthers(int ordDtlID , int prouductID , int quantity );
}
