package com.tia102g3.member.service;

import com.tia102g3.member.model.Member;
import com.tia102g3.member.model.MemberRepository;
import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Member;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("memberService")
public class MemberService {

	@Autowired
	MemberRepository repository; // 自動注入 MemberRepository 物件

	@Autowired
	private SessionFactory sessionFactory; // 自動注入 Hibernate 的 SessionFactory 物件

	// 新增會員的方法
	public void addMember(Member member) {
		repository.save(member); // 使用 repository 保存會員資料
	}
	
	public Member findById(Integer memberID) {
		return repository.findById(memberID).orElse(null);
	}
	
	public boolean existsByAccount(String account) {
	    return repository.existsByAccount(account);
	}
	
	// 保存會員
	public void saveMember(Member member) {
	    if (member.getMemberID() != null && repository.existsById(member.getMemberID())) {
	        // 如果會員 ID 存在且在資料庫中已經存在，則更新會員資料
	        updateMember(member);
	    } else {
	        // 如果會員 ID 不存在，則新增會員資料
	        addMember(member);
	    }
	}

	// 更新會員資料的方法
	public void updateMember(Member member) {
		repository.save(member); // 使用 repository 更新會員資料
	}
	
	// 刪除會員的方法，根據會員 ID
	public void deleteMember(Integer memberID) {
		if (repository.existsById(memberID)) // 檢查該會員 ID 是否存在
			repository.deleteByMemberID(memberID); // 若存在則刪除會員資料
	}

	// 根據會員 ID 取得會員資料的方法
	public Member getOneMember(Integer memberID) {
		Optional<Member> optional = repository.findById(memberID); // 查找會員資料，回傳 Optional 物件
		return optional.orElse(null); // 若資料存在則回傳，否則回傳 null
	}

	// 取得所有會員資料的方法
	public List<Member> getAll() {
		return repository.findAll(); // 使用 repository 取得所有會員資料
	}

	// 調用 HibernateUtil_CompositeQuery_Emp3 的 getAllC 方法，
	public List<Member> getAll(Map<String, String[]> map) {
		// 傳入查詢參數 map 和新開啟的 Hibernate Session，返回查詢結果的 Member 列表。
		return HibernateUtil_CompositeQuery_Member.getAllMembers(map, sessionFactory.openSession());
	}

	@Transactional
	public void updateMemberByCourseOrder(Member member) {
		repository.update(member.getReceiver(), member.getReceiverAddress(), member.getReceiverPhone(), member.getMemberID());
	}
}
