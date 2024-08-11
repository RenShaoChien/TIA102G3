package com.tia102g3.restmap.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tia102g3.restmap.model.RestMapVO;

public interface RestMapRepository extends JpaRepository<RestMapVO, Integer> {

}