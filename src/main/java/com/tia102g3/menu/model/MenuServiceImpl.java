package com.tia102g3.menu.model;

import java.util.List;

public class MenuServiceImpl implements MenuService {

	
	private MenuDAO dao;

	public MenuServiceImpl() {
		super();
		dao = new MenuDAOImpl();
	}


	@Override
	public List<MenuVO> getAllMenu() {
		return dao.getAll();
	}

}
