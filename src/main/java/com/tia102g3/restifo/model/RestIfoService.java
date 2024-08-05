package com.tia102g3.restifo.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_rest_lfo;

@Service("RestIfoService")
public class RestIfoService {

	@Autowired
	RestIfoRepository repository;

	@Autowired
	private SessionFactory sessionFactory;

	public void addRestIfo(RestIfoVO restIfoVO) {
		repository.save(restIfoVO);
	}

	public void updateRestIfo(RestIfoVO restIfoVO) {
		repository.save(restIfoVO);
	}

	public void deleteRestIfoVO(Integer restLoc) {
		if (repository.existsById(restLoc))
			repository.deleteByEmpno(restLoc);
//		    repository.deleteById(restLoc);
	}

	public RestIfoVO getOneRestIfo(Integer restLoc) {
		Optional<RestIfoVO> optional = repository.findById(restLoc);
//		return optional.get();
		return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	public List<RestIfoVO> getAll() {
		return repository.findAll();
	}

	public List<RestIfoVO> getAll(Map<String, String[]> map) {
		return HibernateUtil_CompositeQuery_rest_lfo.getAllC(map, sessionFactory.openSession());
	}

}

