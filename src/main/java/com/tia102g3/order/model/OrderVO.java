package com.tia102g3.order.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderVO implements Serializable{
	private Integer orderID;
	private java.sql.Timestamp orderDate ;
<<<<<<< HEAD
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


=======
	private String orderStaus;
	private Integer totalPrice;
	private Integer memberID;
	
	public OrderVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderVO(Integer orderId, Timestamp orderDate, String orderStaus, Integer totalPrice, Integer memberId) {
		super();
		this.orderID = orderId;
		this.orderDate = orderDate;
		this.orderStaus = orderStaus;
		this.totalPrice = totalPrice;
		this.memberID = memberId;
	}

	public Integer getOrderId() {
		return orderID;
	}

	public void setOrderId(Integer orderId) {
		this.orderID = orderId;
	}

	public java.sql.Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.sql.Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStaus() {
		return orderStaus;
	}

	public void setOrderStaus(String orderStaus) {
		this.orderStaus = orderStaus;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getMemberId() {
		return memberID;
	}

	public void setMemberId(Integer memberId) {
		this.memberID = memberId;
	}
>>>>>>> refs/heads/master
	
	
	
	

}
