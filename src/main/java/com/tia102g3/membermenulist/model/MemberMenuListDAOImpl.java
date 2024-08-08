package com.tia102g3.membermenulist.model;

import com.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MemberMenuListDAOImpl implements MemberMenuListDAO {

	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public MemberMenuListDAOImpl() {
			super();
			factory = HibernateUtil.getSessionFactory();
		}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public List<MemberMenuListVO> getAll() {
		return getSession().createQuery("from MemberMenuListVO", MemberMenuListVO.class).list();
	}

	@Override
	public Integer insert(MemberMenuListVO memberMenuListVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(MemberMenuListVO memberMenuListVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberMenuListVO getOne(Integer menuListSN) {
		// TODO Auto-generated method stub
		return null;
	}

}
