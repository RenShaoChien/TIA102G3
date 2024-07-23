package com.course_order.model;

import java.util.List;

public interface CourseOrderDAO_interface {
	public void insert(CourseOrderVO courseOrderVO);
    public void update(CourseOrderVO courseOrderVO);
    public void delete(Integer courseOrderID);
    public CourseOrderVO getOne(Integer courseOrderID);
    public List<CourseOrderVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
