package com.tia102g3.shoppingcart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCartVO {
	
	//根據table建立欄位變數
	//PK欄位加@Id
	//欄位加@Column對映在table的欄位，但如果變數名稱與欄位名稱相同可省略
	@Id
	@Column(name = "shoppingCartID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer shoppingCartID;
	
	@Column(name = "memberID")
	private Integer memberID;
	
	@Column(name = "productID")
	private Integer productID;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	
	//建立無參數建構子
	public ShoppingCartVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//建立有參數建構子
	public ShoppingCartVO(Integer shoppingCartID, Integer memberID, Integer productID, Integer quantity) {
		super();
		this.shoppingCartID = shoppingCartID;
		this.memberID = memberID;
		this.productID = productID;
		this.quantity = quantity;
	}
	
	
	//建立getter與setter
	public Integer getShoppingCartID() {
		return shoppingCartID;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public Integer getProductID() {
		return productID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setShoppingCartID(Integer shoppingCartID) {
		this.shoppingCartID = shoppingCartID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

}
