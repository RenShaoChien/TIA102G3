package com.tia102g3.healthstatus.model;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.utils.HibernateUtil;

public class HealthStatusDAOImpl implements HealthStatusDAO {

	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public HealthStatusDAOImpl() {
		super();
		factory = HibernateUtil.getSessionFactory();
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public List<HealthStatusVO> getAll() {
		return getSession().createQuery("from HealthStatusVO", HealthStatusVO.class).list();
	}

	@Override
	public List<HealthStatusVO> getByMemberID(Integer memberID) {
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<HealthStatusVO> criteria = builder.createQuery(HealthStatusVO.class);
		Root<HealthStatusVO> root = criteria.from(HealthStatusVO.class);
		
		Predicate p1 = builder.equal(root.get("memberID"), memberID);
		
		criteria.where(p1);
		
		TypedQuery<HealthStatusVO> query = getSession().createQuery(criteria);
		
		return query.getResultList();
	}

	@Override
	public Integer insert(HealthStatusVO healthStatusVO) {
		return (Integer)getSession().save(healthStatusVO);
	}

	@Override
	public Integer update(HealthStatusVO healthStatusVO) {
		try {
			getSession().update(healthStatusVO);
			return 1;
		}catch(Exception e) {
			return -1;
		}
	}

	@Override
	public HealthStatusVO getOne(Integer healthSN) {
		return getSession().get(HealthStatusVO.class, healthSN);	
	}

}
