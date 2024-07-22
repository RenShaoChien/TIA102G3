package com.tia102g3.foodlist.model;

import java.util.List;

public class FoodListServiceImpl implements FoodListService {

	private FoodListDAO dao;
	
	public FoodListServiceImpl() {
		super();
		dao = new FoodListDAOImpl();
	}

	@Override
	public List<FoodListVO> getAllFoodList() {
		return dao.getAll();
	}

	

}
