package com.tia102g3.order.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hibernate.util.CompositeQuery.HibernateUtilCompositeQuery_orderid;

@Service("OrderService")
public class OrderService {
	
	@Autowired
	OrderRepository repository;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addOrder(OrderVO orderVO) {
		repository.save(orderVO);
	}
	
	public void updateOrder(OrderVO orderVO) {
		repository.save(orderVO);
	}
	
//	public void deletOrder(Integer orderID) {
//		if(repository.existsById(orderID))
//			repository.deleteByOrderID;
//	}
	public OrderVO getOneOrder(Integer orderID) {
		Optional<OrderVO> optional = repository.findById(orderID);
		return optional.orElse(null);
	}
	
	public List<OrderVO> getAll(){
		return repository.findAll();
	}
	
	public List<OrderVO> getAll(Map<String, String[]> map) {
		return HibernateUtilCompositeQuery_orderid.getAllC(map,sessionFactory.openSession());
	}

	public List<OrderVO> searchOrder(String query) {
		// TODO Auto-generated method stub
		return null;
	}


	
}