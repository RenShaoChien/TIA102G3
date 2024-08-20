// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.tia102g3.member.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from member where memberID =?1", nativeQuery = true)
	void deleteByMemberID(int memberID);

	//● (自訂)條件查詢
	@Query(value = "from Member where memberID=?1 and name like?2 and regDate=?3 order by memberID")
	List<Member> findByOthers(int memberID , String name , java.sql.Date regDate);
	
	Member findByAccount(String account);
	boolean existsByAccount(String account);
	Member findByEmail(String email);
	boolean existsByEmail(String email);

	@Transactional
	@Modifying
	@Query(value = "update Member m set m.receiver = :receiver, m.receiverAddress = :receiverAddress, m.receiverPhone = :receiverPhone where m.memberID = :memberID")
	void update(String receiver, String receiverAddress, String receiverPhone, Integer memberID);
	
	@Transactional
	@Modifying
	@Query(value = "update Member m set m.personalPhotos = :personalPhotos where m.memberID = :memberID")
	void updateMember(byte[] personalPhotos , Integer memberID);
}
