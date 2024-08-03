package com.tia102g3.order.model;

import com.tia102g3.member.model.Member;

import java.sql.Timestamp;
import java.util.List;

public class OrderService {
	
	private OrderDAOInterface dao;
	
	public OrderService() {
		dao = new OrderJDBCDAO();
		}
	
	public OrderVO addOrder(Integer memberID,Timestamp orderDate, String status, Integer totalPrice) {
	
		OrderVO orderVO = new OrderVO();
		
		orderVO.setMember(new Member(memberID));
		orderVO.setOrderDate(orderDate);
		orderVO.setStatus(status);
		orderVO.setTotalPrice(totalPrice);
		dao.insert(orderVO);
		
		return orderVO;
	}
	
	public OrderVO updateOrder(Integer orderID, Integer memberID,Timestamp orderDate, String status, Integer totalPrice) {
		
		OrderVO orderVO = new OrderVO();
		
		orderVO.setOrderID(orderID);
		orderVO.setMember(new Member(memberID));
		orderVO.setOrderDate(orderDate);
		orderVO.setStatus(status);
		orderVO.setTotalPrice(totalPrice);
		dao.insert(orderVO);
		
		return orderVO;
	}
	public void deleteOrder(Integer orderID) {
		dao.delete(orderID);
	}

	public OrderVO getOneOrder(Integer orderID) {
		return dao.findByPrimaryKey(orderID);
	}
	
//	public OrderVO getOneMember(Integer memberID) { //
//		return dao.findByForeignKey(memberID);
//	}
	

	public List<OrderVO> getAll() {
		return dao.getAll();
	}
	
}