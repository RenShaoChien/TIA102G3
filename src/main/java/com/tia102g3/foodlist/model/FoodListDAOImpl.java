package com.tia102g3.foodlist.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.utils.HibernateUtil;

public class FoodListDAOImpl implements FoodListDAO {
	
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;
	
	public FoodListDAOImpl() {
		super();
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession(); 
	}

	@Override
	public List<FoodListVO> getAll() {
		return getSession().createQuery("from FoodListVO", FoodListVO.class).list();
	}

}
