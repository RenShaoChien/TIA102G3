package com.tia102g3.shoppingcart.dao;

import com.tia102g3.shoppingcart.entity.ShoppingCartVO;
import com.tia102g3.shoppingcart.unit.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ShoppingCartDAOImpl implements ShoppingCartDAO{
	
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;
	
	public ShoppingCartDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}
	/*	
	@Override
	public int insert(ShoppingCart entity) {
		// 回傳給 service 剛新增成功的自增主鍵值
		return (Integer) getSession().save(entity);
	}

	@Override
	public int update(ShoppingCart entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer id) {
		ShoppingCart sc = getSession().get(ShoppingCart.class, id);
		if (sc != null) {
			getSession().delete(sc);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}

	@Override
	public ShoppingCart getById(Integer id) {
		return getSession().get(ShoppingCart.class, id);
	}

	@Override
	public List<ShoppingCart> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShoppingCart> getByCompositeQuery(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShoppingCart> getAll(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
	*/

	
	@Override
	public int insert(ShoppingCartVO sc) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(sc);
			session.getTransaction().commit();
			return id;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(ShoppingCartVO sc) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(sc);
			session.getTransaction().commit();
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override 
	public int delete(Integer shoppingCartID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ShoppingCartVO sc = session.get(ShoppingCartVO.class, shoppingCartID);
			if (sc != null) {
				session.delete(sc);
			}
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public ShoppingCartVO findByPK(Integer shoppingCartID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ShoppingCartVO sc = session.get(ShoppingCartVO.class, shoppingCartID);
			session.getTransaction().commit();
			return sc;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<ShoppingCartVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<ShoppingCartVO> list = session.createQuery("from ShoppingCartVO", ShoppingCartVO.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	/*
	@Override
	public List<ShoppingCart> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("from Emp", ShoppingCart.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from ShoppingCart", Long.class).uniqueResult();
	}

	@Override
	public List<ShoppingCart> getByCompositeQuery(Map<String, String> map) {
		return null;
		
		if (map.size() == 0)
		return getAll();
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<ShoppingCart> criteria = builder.createQuery(ShoppingCart.class);
		Root<ShoppingCart> root = criteria.from(ShoppingCart.class);

		List<Predicate> predicates = new ArrayList<>();
		
		if (map.containsKey("starthiredate") && map.containsKey("endhiredate"))
			predicates.add(builder.between(root.get("hiredate"), Date.valueOf(map.get("starthiredate")), Date.valueOf(map.get("endhiredate"))));

		if (map.containsKey("startsal") && map.containsKey("endsal"))
			predicates.add(builder.between(root.get("sal"), new BigDecimal(map.get("startsal")), new BigDecimal(map.get("endsal"))));
		
		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("ename".equals(row.getKey())) {
				predicates.add(builder.like(root.get("ename"), "%" + row.getValue() + "%"));
			}

			if ("job".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("job"), row.getValue()));
			}

			if ("starthiredate".equals(row.getKey())) {
				if (!map.containsKey("endhiredate"))
					predicates.add(builder.greaterThanOrEqualTo(root.get("hiredate"), Date.valueOf(row.getValue())));
			}

			if ("endhiredate".equals(row.getKey())) {
				if (!map.containsKey("starthiredate"))
					predicates.add(builder.lessThanOrEqualTo(root.get("hiredate"), Date.valueOf(row.getValue())));

			}

			if ("startsal".equals(row.getKey())) {
				if (!map.containsKey("endsal"))
					predicates.add(builder.greaterThanOrEqualTo(root.get("sal"), new BigDecimal(row.getValue())));

			}

			if ("endsal".equals(row.getKey())) {
				if (!map.containsKey("startsal"))
					predicates.add(builder.lessThanOrEqualTo(root.get("sal"), new BigDecimal(row.getValue())));

			}

		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("empno")));
		TypedQuery<ShoppingCart> query = getSession().createQuery(criteria);

		return query.getResultList();
		
	}
	*/
}

