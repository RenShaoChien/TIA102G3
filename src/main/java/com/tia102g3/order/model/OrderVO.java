package com.tia102g3.order.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "orderid")
public class OrderVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Integer orderID;
	private java.sql.Timestamp orderDate ;
	private String status;
	private Integer totalPrice;
	private MemberVO member;


	private byte[] upFiles;
	
	public OrderVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Id
	@Column(name = "orderID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	@Column(name = "orderDate")
	@NotNull(message="雇用日期: 請勿空白")
	@Past(message="日期必須是在今日(含)之前")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	public java.sql.Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.sql.Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "status")
	@NotEmpty(message = "訂單狀態: 請勿空白")
	@Pattern(regexp = "^[\\u4e00-\\u9fa5]{0,}$", message = "訂單狀態: 只能是中文字")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "totalPrice")
	@NotNull(message = "總金額: 請勿空白")
	@DecimalMin(value = "0", message = "總金額: 不能小於{value}")
	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

//	@ManyToOne
//	@JoinColumn(name = "merberID")
//	public MemberVO getMemberVO() {
//		return this.getMemberVO();
//	}
//
//	public void setMemberID(Integer memberID) {
//		this.MemberVO = memberVO;
//	}


	@Column(name = "UPFILES")
//	@NotEmpty(message="請上傳照片") --> 由EmpController.java 第60行處理錯誤信息
	public byte[] getUpFiles() {
		return upFiles;
	}
	public void setUpFiles(byte[] upFiles) {
		this.upFiles = upFiles;
	}
	

	

}
