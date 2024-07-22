package com.tia102g3.admin.model;

import java.util.List;

import com.tia102g3.order.model.OrderVO;

public class AdminService {
	
	private AdminDAOInterface dao;
	
	public AdminService() {
		dao = new AdminJDBCDAO();
		}
	
	public AdminVO addOrder(String adminName,String adminUserName, String adminPassword, String adminEmail) {
	
		AdminVO adminVO = new AdminVO();
		
		adminVO.setAdminName(adminName);
		adminVO.setAdminUserName(adminUserName);
		adminVO.setAdminPassword(adminPassword);
		adminVO.setAdminEmail(adminEmail);
		dao.insert(adminVO);
		
		return adminVO;
	}
	
	public AdminVO updateOrder(Integer adminID, String adminName, String adminUserName, String adminPassword, String adminEmail) {
		
		AdminVO adminVO = new AdminVO();
		
		adminVO.setAdminID(adminID);
		adminVO.setAdminName(adminName);
		adminVO.setAdminUserName(adminUserName);
		adminVO.setAdminPassword(adminPassword);
		adminVO.setAdminEmail(adminEmail);
		dao.insert(adminVO);
		
		return adminVO;
	}
	public void deleteOrder(Integer adminID) {
		dao.delete(adminID);
	}

	public AdminVO getOneOrder(Integer adminID) {
		return dao.findByPrimaryKey(adminID);
	}
	
//	public OrderVO getOneMember(Integer memberID) { //
//		return dao.findByForeignKey(memberID);
//	}
	

	public List<AdminVO> getAll() {
		return dao.getAll();
	}
	
}