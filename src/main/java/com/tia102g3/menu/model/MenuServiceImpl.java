package com.tia102g3.menu.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.utils.HibernateUtil;

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
