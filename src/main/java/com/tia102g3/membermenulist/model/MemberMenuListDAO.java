package com.tia102g3.membermenulist.model;

import java.util.List;

public interface MemberMenuListDAO {

	public List<MemberMenuListVO> getAll();

	public Integer insert(MemberMenuListVO memberMenuListVO);

	public Integer update(MemberMenuListVO memberMenuListVO);

	public MemberMenuListVO getOne(Integer menuListSN);
	
}
