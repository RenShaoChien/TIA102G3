//package com.tia102g3.orderdetails.model;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//import hibernate.util.CompositeQuery.HibernateUtilCompositeQuery_order_details;
//
//
//@Service("OrderDetailsService")
//public class OrderDetailsService{
//
//	@Autowired
//	OrderDetailsRepository repository;
//
//	@Autowired
//	private SessionFactory sessionFactory;
//
//	public void addOrderDetails(OrderDetailsVO orderDetailsVO) {
//		repository.save(orderDetailsVO);
//	}
//
//	public void updateOrderDetails(OrderDetailsVO orderDetailsVO) {
//		repository.save(orderDetailsVO);
//	}
//
////	public void deletOrderDetails(Integer ordDtlID) {
////		if(repository.existsById(ordDtlID))
////			repository.deleteByOrderDtlID;
////	}
//	public OrderDetailsVO getOneOrderDetails(Integer ordDtlID) {
//		Optional<OrderDetailsVO> optional = repository.findById(ordDtlID);
//		return optional.orElse(null);
//	}
//
//	public List<OrderDetailsVO> getAll(){
//		return repository.findAll();
//	}
//
//	public List<OrderDetailsVO> getAll(Map<String, String[]> map) {
//		return HibernateUtilCompositeQuery_order_details.getAllC(map,sessionFactory.openSession());
//	}
//}
