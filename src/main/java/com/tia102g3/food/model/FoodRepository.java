package com.tia102g3.food.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodRepository extends JpaRepository<FoodVO, Integer> {
	// ● 查詢全部資料
//    @Query(value = "select from FoodVO")
//    List<FoodVO> findAllFood();
}
