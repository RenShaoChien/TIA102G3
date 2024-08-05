package com.tia102g3.admin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "admin_id")
public class AdminVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer admin_ID;
	private String adminName;
	private String adminUsername;
	private String adminPassword;
	private String adminEmail;

	public AdminVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "admin_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAdmin_ID() {
		return admin_ID;
	}

	public void setAdmin_ID(Integer adminID) {
		this.admin_ID = admin_ID;
	}

	@Column(name = "adminName")
	@NotNull(message = "管理員姓名: 請勿空白")
	@Pattern(regexp = "^[\\u4e00-\\u9fa5]{0,}$", message = "訂單狀態: 只能是中文字")
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Column(name = "adminUsername")
	@NotNull(message = "管理員帳號: 請勿空白")
	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$", message = "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間")
	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUserName) {
		this.adminUsername = adminUsername;
	}

	@Column(name = "adminPassword")
	@NotEmpty(message = "密碼: 請勿空白")
	@Pattern(regexp = "^(a-zA-Z0-9_)]{6,}$", message = "密碼: 只能是英文字母、數字和_ , 且長度必需至少6位")
	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Column(name = "adminEmail")
	@NotEmpty(message = "信箱: 請勿空白")
	@Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "電子郵件地址無效")

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	
}
