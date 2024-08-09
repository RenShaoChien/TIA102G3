package com.tia102g3.menu.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("menuService")
public class MenuService {

    @Autowired
    MenuRepository repository;

    public void deleteMenu(Integer menuNumber) {
        repository.deleteById(menuNumber);
    }

    public List<MenuVO> getAll() {
        return repository.findAll();
    }

    public void addMenu(MenuVO menuVO) {
        repository.save(menuVO);
    }

    public void updateMenu(MenuVO menuVO) {
        repository.save(menuVO);
    }
    
    
    public MenuVO getOneMenu(Integer menuNumber) {
        Optional<MenuVO> optional = repository.findById(menuNumber);

        return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
    }

}
