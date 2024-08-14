package com.tia102g3.order.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<OrderVO, Integer> {

    @Transactional(readOnly = true)
    @Query(
            value = "SELECT * FROM orderid WHERE " +
                    "orderID LIKE CONCAT('%', :keyword, '%') OR " +
                    "memberID LIKE CONCAT('%', :keyword, '%') OR " +
                    "orderDate LIKE CONCAT('%', :keyword, '%') OR " +
                    "status LIKE CONCAT('%', :keyword, '%') OR " +
                    "totalPrice LIKE CONCAT('%', :keyword, '%') " +
                    "LIMIT :offset, 5",
            nativeQuery = true)
	List<Object[]> getOrderList(@Param("keyword") String keyword, int offset);

	
    @Transactional(readOnly = true)
    @Query(
            value = "SELECT count(*) FROM orderid WHERE " +
                    "orderID LIKE CONCAT('%', :keyword, '%') OR " +
                    "memberID LIKE CONCAT('%', :keyword, '%') OR " +
                    "orderDate LIKE CONCAT('%', :keyword, '%') OR " +
                    "status LIKE CONCAT('%', :keyword, '%') OR " +
                    "totalPrice LIKE CONCAT('%', :keyword, '%') " ,
            nativeQuery = true)
    Long getOrderCount(@Param("keyword")String keyword);
}
