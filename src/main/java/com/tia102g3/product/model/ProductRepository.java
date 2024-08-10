package com.tia102g3.product.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<ProductVO, Integer> {

	
    @Transactional(readOnly = true)
    @Query(
            value = "SELECT * FROM product WHERE " +
                    "productID LIKE CONCAT('%', :keyword, '%') OR " +
                    "prodName LIKE CONCAT('%', :keyword, '%') OR " +
                    "price LIKE CONCAT('%', :keyword, '%') OR " +
                    "productQuantity LIKE CONCAT('%', :keyword, '%') OR " +
                    "intro LIKE CONCAT('%', :keyword, '%') OR " +
                    "LIMIT :offset, 5",
            nativeQuery = true)
	List<Object[]> getProductList(@Param("keyword") String keyword, Integer offset);


    @Transactional(readOnly = true)
    @Query(
            value = "SELECT * FROM product WHERE " +
                    "productID LIKE CONCAT('%', :keyword, '%') OR " +
                    "prodName LIKE CONCAT('%', :keyword, '%') OR " +
                    "price LIKE CONCAT('%', :keyword, '%') OR " +
                    "productQuantity LIKE CONCAT('%', :keyword, '%') OR " +
                    "intro LIKE CONCAT('%', :keyword, '%')" ,
            nativeQuery = true)
	Long getProductCount(@Param("keyword") String keyword);
	
	// ● 查詢全部資料
//  @Query(value = "select from FoodVO")
//  List<FoodVO> findAllFood();

//	List<ShoppingCartVO> getAll();
//
//	void delete(Integer shoppingCartVO);
}
