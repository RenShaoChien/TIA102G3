package com.tia102g3.healthstatus.model;

import java.util.List;

public interface HealthStatusService {
	HealthStatusVO addHealthStatus(HealthStatusVO healthStatus);
	
	HealthStatusVO updateHealthStatus(HealthStatusVO healthStatus);
	
//	HealthStatusVO getHealthStatus(HealthStatusVO healthStatus);
	
	List<HealthStatusVO> getAllHealthStatus();
	
}
