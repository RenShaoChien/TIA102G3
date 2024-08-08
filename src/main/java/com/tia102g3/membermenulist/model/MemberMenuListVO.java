package com.tia102g3.membermenulist.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="membermenulist")
public class MemberMenuListVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menuListSN", updatable = false)
	private Integer menuListSN;
	@Column(name = "healthSN")
	private Integer healthSN;
	@Column(name = "menuNumber")
	private Integer menuNumber;
	@Column(name = "menuDate")
	private Date menuDate;
	@Column(name = "memberID")
	private Integer memberID;

	
	public MemberMenuListVO() {
		super();
	}


	public Integer getMenuListSN() {
		return menuListSN;
	}


	public void setMenuListSN(Integer menuListSN) {
		this.menuListSN = menuListSN;
	}


	public Integer getHealthSN() {
		return healthSN;
	}


	public void setHealthSN(Integer healthSN) {
		this.healthSN = healthSN;
	}


	public Integer getMenuNumber() {
		return menuNumber;
	}


	public void setMenuNumber(Integer menuNumber) {
		this.menuNumber = menuNumber;
	}


	public Date getMenuDate() {
		return menuDate;
	}


	public void setMenuDate(Date menuDate) {
		this.menuDate = menuDate;
	}


	public Integer getMemberID() {
		return memberID;
	}


	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	@Override
	public String toString() {
		return "MemberMenuListVO [menuListSN=" + menuListSN + ", healthSN=" + healthSN + ", menuNumber=" + menuNumber
		        + ", menuDate=" + menuDate + ", memberID=" + memberID + "]";
	}
	
	
}
