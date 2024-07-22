package com.tia102g3.admin.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AdminVO implements Serializable{
	private Integer adminID;
	private String adminName ;
	private String adminUserName;
	private String adminPassword;
	private String adminEmail;
	
	public AdminVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminVO(Integer adminID, String adminName, String adminUserName, String adminPassword, String adminEmail) {
		super();
		this.adminID = adminID;
		this.adminName = adminName;
		this.adminUserName = adminUserName;
		this.adminPassword = adminPassword;
		this.adminEmail = adminEmail;
	}

	public Integer getAdminID() {
		return adminID;
	}

	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	
	

}
