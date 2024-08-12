package com.tia102g3.restmap.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tia102g3.restifo.model.RestIfoVO;

@Service("restMapService")
public class RestMapService {

	@Autowired
	RestMapRepository repository;

	public void addRestMap(RestMapVO restmapVO) {
		repository.save(restmapVO);
	}

	public void updateRestMap(RestMapVO restMapVO) {
		repository.save(restMapVO);
	}

	public void deleteDept(Integer mapID) {
		if (repository.existsById(mapID))
			repository.deleteById(mapID);
	}

	public RestMapVO getOneRestMap(Integer mapID) {
		Optional<RestMapVO> optional = repository.findById(mapID);
//	return optional.get();
		return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	public List<RestMapVO> getAll() {
		return repository.findAll();
	}

	public Set<RestIfoVO> getEmpsByDeptno(Integer mapID) {
		return getOneRestMap(mapID).getRestIfos();
	}

}