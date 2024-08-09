package com.tia102g3.admin.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminRepository extends JpaRepository<AdminVO, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from admin_id where admin_ID =?1", nativeQuery = true)
	void deleteByEmpno(int admin_ID); // 這裡是刪除

	// ● (自訂)條件查詢
	@Query(value = "from AdminVO where admin_ID=?1 and adminName like?2 and adminUsername=?3 order by admin_ID")
	List<AdminVO> findByOthers(int admin_ID, String adminName, String adminUsername);
}

