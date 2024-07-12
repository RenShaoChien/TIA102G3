package com.tia102g3order.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderVO implements Serializable{
	private Integer orderID;
	private java.sql.Timestamp orderDate ;
	private String status;
	private Integer totalPrice;
	private Integer memberID;
	
	public OrderVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderVO(Integer orderID, Timestamp orderDate, String status, Integer totalPrice, Integer memberID) {
		super();
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.status = status;
		this.totalPrice = totalPrice;
		this.memberID = memberID;
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public java.sql.Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.sql.Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}


	
	
	
	

}
