package com.tia102g3.food.model;

import java.util.*;

public interface FoodDAO {
	public List<FoodVO> getAll();
	
	public void insert(FoodVO foodVO);
	
	public void update(FoodVO foodVO);
	
	public void delete(Integer foodNumber);
	
	public FoodVO getOne(Integer foodNumber);

}
