package com.tia102g3.menu.model;

import com.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MenuDAOImpl implements MenuDAO {

	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public MenuDAOImpl() {
		super();
		factory = HibernateUtil.getSessionFactory();
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public List<MenuVO> getAll() {
		return getSession().createQuery("from MenuVO", MenuVO.class).list();
	}

	@Override
	public Integer insert(MenuVO menuVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(MenuVO menuVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuVO getOne(Integer menuNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
