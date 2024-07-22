package com.tia102g3.healthstatus.model;

import java.util.List;

public class HealthStatusServiceImpl implements HealthStatusService {
	
	private HealthStatusDAO dao;
	
	public HealthStatusServiceImpl() {
		dao = new HealthStatusDAOImpl();
	}

	@Override
	public HealthStatusVO addHealthStatus(HealthStatusVO healthStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HealthStatusVO updateHealthStatus(HealthStatusVO healthStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HealthStatusVO> getAllHealthStatus() {
		return dao.getAll();
	}
	
}
