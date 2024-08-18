// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.tia102g3.coachmember.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CoachMemberRepository extends JpaRepository<CoachMember, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from coach_member where cMemberID =?1", nativeQuery = true)
	void deleteByCMemberID(int cMemberID);

	@Query("FROM CoachMember cm WHERE cm.cMemberID = :cMemberID AND cm.name LIKE CONCAT('%', :name, '%') AND cm.regDate = :regDate ORDER BY cm.cMemberID")
	List<CoachMember> findByOthers(@Param("cMemberID") int cMemberID, 
	                               @Param("name") String name, 
	                               @Param("regDate") java.sql.Date regDate);

	CoachMember findByAccount(String account);
	boolean existsByAccount(String account);
	CoachMember findByEmail(String email);
	
	@Transactional
	@Modifying
	@Query(value = "update CoachMember m set m.personalPhotos = :personalPhotos where m.cMemberID = :cMemberID")
	void updateCoachMember(byte[] personalPhotos , Integer cMemberID);
}