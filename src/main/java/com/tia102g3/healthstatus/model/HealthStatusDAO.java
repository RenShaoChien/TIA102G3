package com.tia102g3.healthstatus.model;

import java.util.List;

public interface HealthStatusDAO {
	
	public List<HealthStatusVO> getAll();
	
	public List<HealthStatusVO> getByMemberID(Integer memberID);
	
	public Integer insert(HealthStatusVO healthStatusVO);
	
	public Integer update(HealthStatusVO healthStatusVO);
	
	public HealthStatusVO getOne(Integer healthSN);

}
