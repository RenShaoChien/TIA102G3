package com.tia102g3.membermenulist.model;

import java.util.List;

public class MemberMenuListServiceImpl implements MemberMenuListService {
	
 	private MemberMenuListDAO dao;

	public MemberMenuListServiceImpl() {
		dao = new MemberMenuListDAOImpl();
	}

	@Override
	public List<MemberMenuListVO> getAllMenuList() {		
		return dao.getAll();
	}

	
}
