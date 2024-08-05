package com.tia102g3.restifo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;



public interface RestIfoRepository extends JpaRepository<RestIfoVO, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from rest_lfo where restLoc =?1", nativeQuery = true)
	void deleteByEmpno(int restLoc);  //這裡是刪除

	//● (自訂)條件查詢
	@Query(value = "from RestIfoVO where restLoc=?1 and restName like?2 and restAddress=?3 order by restLoc")
	List<RestIfoVO> findByOthers(int restLoc , String restName , String restTime);
}
