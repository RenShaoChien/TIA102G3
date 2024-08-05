package com.tia102g3.resmap.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tia102g3.resmap.model.ResMapVO;

public interface ResMapRepository extends JpaRepository<ResMapVO, Integer> {

}

