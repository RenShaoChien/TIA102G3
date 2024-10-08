package com.tia102g3.coachmember.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tia102g3.coachmember.model.CoachMember;
import com.tia102g3.coachmember.model.CoachMemberRepository;

import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_CoachMember;

@Service("CoachMemberService")
public class CoachMemberService {

	@Autowired
	CoachMemberRepository repository; // 自動注入 MemberRepository 物件

	@Autowired
	private SessionFactory sessionFactory; // 自動注入 Hibernate 的 SessionFactory 物件

	// 新增會員的方法
	public void addCoachMember(CoachMember coachMember) {
		repository.save(coachMember); // 使用 repository 保存會員資料
	}
	
	public CoachMember findById(Integer cMemberId) {
        return repository.findById(cMemberId).orElse(null);
    }
	
	public boolean existsByAccount(String account) {
	    return repository.existsByAccount(account);
	}
	
	public CoachMember findByEmail(String email) {
        return repository.findByEmail(email); // 根據電子郵件查找會員
    }

	public boolean existsByEmail(String email) {
		return repository.existsByAccount(email);
	}
	
	// 更新會員資料的方法
	public void updateCoachMember(CoachMember coachMember) {
		repository.save(coachMember); // 使用 repository 更新會員資料
	}

	// 刪除會員的方法，根據會員 ID
	public void deleteCoachMember(Integer cMemberID) {
		if (repository.existsById(cMemberID)) // 檢查該會員 ID 是否存在
			repository.deleteByCMemberID(cMemberID); // 若存在則刪除會員資料
	}

	// 根據會員 ID 取得會員資料的方法
	public CoachMember getOneCoachMember(Integer cMemberID) {
		Optional<CoachMember> optional = repository.findById(cMemberID); // 查找會員資料，回傳 Optional 物件
		return optional.orElse(null); // 若資料存在則回傳，否則回傳 null
	}

	// 取得所有會員資料的方法
	public List<CoachMember> getAll() {
		return repository.findAll(); // 使用 repository 取得所有會員資料
	}

	// 調用 HibernateUtil_CompositeQuery_Emp3 的 getAllC 方法，
	public List<CoachMember> getAll(Map<String, String[]> map) {
		// 傳入查詢參數 map 和新開啟的 Hibernate Session，返回查詢結果的 MemberVO 列表。
		return HibernateUtil_CompositeQuery_CoachMember.getAllCoachMembers(map, sessionFactory.openSession());
	}
}
