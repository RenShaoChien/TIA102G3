package com.tia102g3.menu.model;

import java.util.List;

public interface MenuDAO {
	
	public List<MenuVO> getAll();

	public Integer insert(MenuVO menuVO);

	public Integer update(MenuVO menuVO);

	public MenuVO getOne(Integer menuNumber);
}	
