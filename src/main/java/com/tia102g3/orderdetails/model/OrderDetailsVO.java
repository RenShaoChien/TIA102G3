//package com.tia102g3.orderdetails.model;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.OrderBy;
//import javax.persistence.Table;
//
//import com.tia102g3.order.model.OrderVO;
//
//@Entity
//@Table(name = "order_details")
//public class OrderDetailsVO implements java.io.Serializable{
//private static final long serialVersionUID = 1L;
//
//	private Integer ordDtlID;
//	private Integer productID;
//	private Integer quantity;
//	private Set<OrderVO> orders = new HashSet<OrderVO>();
//
//	public OrderDetailsVO() {
//	}
//
//	@Id
//	@Column(name = "ORDERDETAILSID")
//	@GeneratedValue(strategy = GenerationType.IDENTITY) //@GeneratedValue的generator屬性指定要用哪個generator //【strategy的GenerationType, 有四種值: AUTO, IDENTITY, SEQUENCE, TABLE】
//	public Integer getOrderDtlID() {
//		return this.ordDtlID;
//	}
//
//	public void setOrdDtlID(Integer ordDtlID) {
//		this.ordDtlID = ordDtlID;
//	}
//
//	@Column(name = "PROUDCTID")
//	public Integer getProductID() {
//		return this.productID;
//	}
//
//	public void setProductID(Integer productID) {
//		this.productID = productID;
//	}
//
//	@Column(name = "QUANTITY")
//	public Integer getQuantity() {
//		return this.quantity;
//	}
//
//	public void setQuantity(Integer quantity) {
//		this.quantity = quantity;
//	}
//
//	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="orderDetailsVO")
//	@OrderBy("orderID asc")
//	//註1:【現在是設定成 cascade="all" lazy="false" inverse="true"之意】
//	//註2:【mappedBy="多方的關聯屬性名"：用在雙向關聯中，把關係的控制權反轉】【deptVO是EmpVO的屬性】
//	//註3:【原預設為@OneToMany(fetch=FetchType.LAZY, mappedBy="deptVO")之意】--> 【是指原為  lazy="true"  inverse="true"之意】
//	//FetchType.EAGER : Defines that data must be eagerly fetched
//	//FetchType.LAZY  : Defines that data can be lazily fetched
//	public Set<OrderVO> getOrders() {
//		return this.orders;
//	}
//
//	public void setOrders(Set<OrderVO> orders) {
//		this.orders = orders;
//	}
//
//
//
//}
//
