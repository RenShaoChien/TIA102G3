package com.tia102g3.foodlist.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("foodListService")
public class FoodListService {

    @Autowired
    FoodListRepository repository;

//  @Autowired
//  private SessionFactory sessionFactory;

    public void deleteByFoodListNum(Integer foodListSN) {
        repository.deleteById(foodListSN);
    }

    public List<FoodListVO> getAll() {
        return repository.findAll();
    }
    
    public List<Integer> getAllMenuNum() {
        return repository.findAllMenuNumber();
    }
    
    public List<FoodListVO> getOneByMenuNum(Integer menuNum) {
        return repository.findByMenuNum(menuNum);
    }

    public void addFoodList(FoodListVO foodListVO) {
        repository.save(foodListVO);
    }

    public void updateFoodList(FoodListVO foodListVO) {
        repository.save(foodListVO);
    }
    
    
    public FoodListVO getOneFoodList(Integer foodListSN) {
        Optional<FoodListVO> optional = repository.findById(foodListSN);

        return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
    }

}
