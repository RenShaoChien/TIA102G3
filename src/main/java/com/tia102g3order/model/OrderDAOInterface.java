package com.tia102g3order.model;

import java.util.*;

public interface OrderDAOInterface {

	
	public void insert(OrderVO orderVO);
	public void update(OrderVO order);
	public void delete(Integer orderID);
	public OrderVO findByPrimaryKey(Integer orderID);
	public List<OrderVO> getAll();
	
	
//	public List<OrderVO> getAll(Map<String, String[]map>);
}
