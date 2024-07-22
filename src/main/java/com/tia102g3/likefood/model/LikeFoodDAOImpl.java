package com.tia102g3.likefood.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tia102g3.menu.model.MenuVO;

import com.utils.HibernateUtil;

public class LikeFoodDAOImpl implements LikeFoodDAO {

	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public LikeFoodDAOImpl() {
		super();
		factory = HibernateUtil.getSessionFactory();
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public List<LikeFoodVO> getAll() {
		return getSession().createQuery("from LikeFoodVO", LikeFoodVO.class).list();
	}

	@Override
	public Integer insert(LikeFoodVO foodLikeVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(LikeFoodVO foodLikeVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LikeFoodVO getOne(Integer memberID) {
		// TODO Auto-generated method stub
		return null;
	}

}
