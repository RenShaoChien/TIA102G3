package com.tia102g3.orderdetails.model;

import java.util.List;

public class OrderDetailsService {
private OrderDetailsJDBCDAO dao;
	
	public OrderDetailsService() {
		dao = new OrderDetailsJDBCDAO();
		}
	
	public OrderDetailsVO addOrder(Integer productID,Integer quantity, Integer orderID) {
	
		OrderDetailsVO orderDetailsVO = new OrderDetailsVO();
		
		orderDetailsVO.setProductID(productID);
		orderDetailsVO.setQuantity(quantity);
		orderDetailsVO.setOrderID(orderID);
		dao.insert(orderDetailsVO);
		
		return orderDetailsVO;
	}
	
	public OrderDetailsVO updateOrder(Integer ordDtlID, Integer productID,Integer quantity, Integer orderID) {
		
		OrderDetailsVO orderDetailsVO = new OrderDetailsVO();
		
		orderDetailsVO.setOrdDtlID(ordDtlID);
		orderDetailsVO.setProductID(productID);
		orderDetailsVO.setQuantity(quantity);
		orderDetailsVO.setOrderID(orderID);
		dao.insert(orderDetailsVO);
		
		return orderDetailsVO;
	}
	public void deleteOrder(Integer ordDtlID) {
		dao.delete(ordDtlID);
	}

	public OrderDetailsVO getOneOrder(Integer ordDtlID) {
		return dao.findByPrimaryKey(ordDtlID);
	}
	
//	public OrderDetailsVO getOneMember(Integer memberID) { //
//		return dao.findByForeignKey(memberID);
//	}
	

	public List<OrderDetailsVO> getAll() {
		return dao.getAll();
	}

}