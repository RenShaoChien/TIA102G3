package com.tia102g3.foodlist.model;

import com.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

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
