package com.tia102g3.orderdetails.model;

import java.io.Serializable;

public class OrderDetailsVO implements Serializable{
	private Integer ordDtlID;
	private Integer productID ;
	private Integer quantity;
	private Integer orderID;
	
	public OrderDetailsVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public OrderDetailsVO(Integer ordDtlID, Integer productID, Integer quantity, Integer orderID) {
		super();
		this.ordDtlID = ordDtlID;
		this.productID = productID;
		this.quantity = quantity;
		this.orderID = orderID;
	}



	public Integer getOrdDtlID() {
		return ordDtlID;
	}

	public void setOrdDtlID(Integer ordDtlID) {
		this.ordDtlID = ordDtlID;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	
	
	

}
