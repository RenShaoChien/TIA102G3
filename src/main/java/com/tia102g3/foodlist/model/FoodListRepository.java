package com.tia102g3.foodlist.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodListRepository extends JpaRepository<FoodListVO, Integer> {
  @Query(value = "from FoodListVO where menuNumber=?1 order by foodNumber")
  List<FoodListVO> findByMenuNum(Integer menuNumber);
  
  @Query(value = "SELECT DISTINCT menuNumber FROM foodlist ORDER BY menuNumber", nativeQuery = true)
  List<Integer> findAllMenuNumber();
  
  
}
