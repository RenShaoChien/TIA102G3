package com.tia102g3.order.model;

import java.util.*;

public interface OrderDAOInterface {

	
	public void insert(OrderVO orderVO);
	public void update(OrderVO order);
	public void delete(Integer orderID);
	public OrderVO findByPrimaryKey(Integer orderID);
	public OrderVO findByForeignKey(Integer memberID); //
	public List<OrderVO> getAll();
	
	
//	public List<OrderVO> getAll(Map<String, String[]map>);
}
