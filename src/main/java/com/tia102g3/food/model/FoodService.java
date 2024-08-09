package com.tia102g3.food.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("foodService")
public class FoodService {

    @Autowired
    FoodRepository repository;

//	@Autowired
//	private SessionFactory sessionFactory;

    public void deleteByFoodNum(Integer foodNumber) {
        repository.deleteById(foodNumber);
    }

    public List<FoodVO> getAll() {
        return repository.findAll();
    }

    public void addFood(FoodVO foodVO) {
        repository.save(foodVO);
    }

    public void updateFood(FoodVO foodVO) {
        repository.save(foodVO);
    }
    
    
    public FoodVO getOneFood(Integer foodNumber) {
        Optional<FoodVO> optional = repository.findById(foodNumber);
//	      return optional.get();
        return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
    }

}
