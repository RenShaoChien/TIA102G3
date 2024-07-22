package com.tia102g3.admin.model;

import java.util.*;

public interface AdminDAOInterface {

	
	public void insert(AdminVO adminVO);
	public void update(AdminVO adminVO);
	public void delete(Integer adminID);
	public AdminVO findByPrimaryKey(Integer adminID);
	public List<AdminVO> getAll();
	
	
//	public List<OrderVO> getAll(Map<String, String[]map>);
}
