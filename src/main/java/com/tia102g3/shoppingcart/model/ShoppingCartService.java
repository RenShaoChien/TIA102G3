package com.tia102g3.shoppingcart.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("shoppingCartService")
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository repository;

//	@Autowired
//	private SessionFactory sessionFactory;

    
    public void deleteSCart(Integer shoppingCartVO) {
        repository.deleteById(shoppingCartVO);
    }

    
    public List<ShoppingCartVO> getAll() {
        return repository.findAll();
    }

    
    public void insertSCart(ShoppingCartVO shoppingCartVO) {
        repository.save(shoppingCartVO);
    }

    
    public void updateSCart(ShoppingCartVO shoppingCartVO) {
        repository.save(shoppingCartVO);
    }
    
    
    public ShoppingCartVO findByPK(Integer shoppingCartID) {
        Optional<ShoppingCartVO> optional = repository.findById(shoppingCartID);
//	      return optional.get();
        return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
    }

}
