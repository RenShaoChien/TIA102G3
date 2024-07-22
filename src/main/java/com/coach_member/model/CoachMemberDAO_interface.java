package com.coach_member.model;

import java.util.List;

public interface CoachMemberDAO_interface {
	public void insert(CoachMemberVO coachMemberVO);
    public void update(CoachMemberVO coachMemberVO);
    public void delete(Double coachMemberID);
    public CoachMemberVO getOne(Double coachMemberID);
    public List<CoachMemberVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
