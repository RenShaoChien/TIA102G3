package com.tia102g3.orderdetails.model;

import java.util.*;

import com.tia102g3.order.model.OrderVO;

public interface OrderDetailsDAOInterface {
	
	public void insert(OrderDetailsVO orderDetailsrVO );
	public void update(OrderDetailsVO orderDetailsrVO );
	public void delete(Integer ordDtlID);
	public OrderDetailsVO findByPrimaryKey(Integer ordDtlID);
//	public OrderVO findByForeignKey(Integer memberID); //
	public List<OrderDetailsVO> getAll();
	
	
//	public List<OrderVO> getAll(Map<String, String[]map>);

}
