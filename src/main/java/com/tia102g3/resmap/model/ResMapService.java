package com.tia102g3.resmap.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.tia102g3.restifo.model.RestIfoVO;

public class ResMapService {

	@Autowired
	ResMapRepository repository;

	public void addResMap(ResMapVO resmapVO) {
		repository.save(resmapVO);
	}

	public void updateResMap(ResMapVO resMapVO) {
		repository.save(resMapVO);
	}

	public void deleteDept(Integer mapID) {
		if (repository.existsById(mapID))
			repository.deleteById(mapID);
	}

	public ResMapVO getOneResMap(Integer mapID) {
		Optional<ResMapVO> optional = repository.findById(mapID);
//	return optional.get();
		return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	public List<ResMapVO> getAll() {
		return repository.findAll();
	}

	public Set<RestIfoVO> getEmpsByDeptno(Integer mapID) {
		return getOneResMap(mapID).getRestIfos();
	}

}
