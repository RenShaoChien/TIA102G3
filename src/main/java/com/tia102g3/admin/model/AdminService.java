package com.tia102g3.admin.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hibernate.util.CompositeQuery.HibernateUtilCompositeQuery_admin_id;

@Service("adminService")
public class AdminService {

	@Autowired
	AdminRepository repository;

	@Autowired
	private SessionFactory sessionFactory;

	public void addAdmin(AdminVO adminVO) {
		repository.save(adminVO);
	}

	public void updateAdmin(AdminVO adminVO) {
		repository.save(adminVO);
	}

	public void deleteAdmin(Integer admin_ID) {
		if (repository.existsById(admin_ID))
			repository.deleteByEmpno(admin_ID);
//		    repository.deleteById(admin_ID);
	}

	public AdminVO getOneEmp(Integer admin_ID) {
		Optional<AdminVO> optional = repository.findById(admin_ID);
//		return optional.get();
		return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	public List<AdminVO> getAll() {
		return repository.findAll();
	}

	public List<AdminVO> getAll(Map<String, String[]> map) {
		return HibernateUtilCompositeQuery_admin_id.getAllC(map, sessionFactory.openSession());
	}

}
