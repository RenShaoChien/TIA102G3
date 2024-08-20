package com.tia102g3.member.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tia102g3.member.model.Member;
import com.tia102g3.member.model.MemberRepository;

import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Member;

@Service("memberService")
public class MemberService {

    @Autowired
    MemberRepository repository; // 自動注入 MemberRepository 物件，負責會員資料的 CRUD 操作

    @Autowired
    private SessionFactory sessionFactory; // 自動注入 Hibernate 的 SessionFactory 物件，用於創建 Hibernate Session

    /**
     * 新增會員的方法
     * @param member 需要新增的會員物件
     */
    public void addMember(Member member) {
        repository.save(member); // 使用 repository 保存會員資料
    }
    
    /**
     * 根據會員 ID 查找會員資料
     * @param memberID 會員 ID
     * @return 會員物件，如果找不到則返回 null
     */
    public Member findById(Integer memberID) {
        return repository.findById(memberID).orElse(null); // 根據 ID 查找會員，如果不存在則返回 null
    }
    
    /**
     * 根據電子郵件查找會員
     * @param email 會員電子郵件
     * @return 會員物件，如果找不到則返回 null
     */
    public Member findByEmail(String email) {
        return repository.findByEmail(email); // 根據電子郵件查找會員
    }
    
    /**
     * 檢查帳號是否存在
     * @param account 會員帳號
     * @return 如果帳號存在則返回 true，否則返回 false
     */
    public boolean existsByAccount(String account) {
        return repository.existsByAccount(account); // 檢查帳號是否存在
    }
    
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    /**
     * 保存會員資料（新增或更新）
     * @param member 需要保存的會員物件
     */
    public void saveMember(Member member) {
        if (member.getMemberID() != null && repository.existsById(member.getMemberID())) {
            // 如果會員 ID 存在且在資料庫中已經存在，則更新會員資料
            updateMember(member);
        } else {
            // 如果會員 ID 不存在，則新增會員資料
            addMember(member);
        }
    }

    /**
     * 更新會員資料的方法
     * @param member 需要更新的會員物件
     */
    public void updateMember(Member member) {
        repository.save(member); // 使用 repository 更新會員資料
    }
    
    /**
     * 刪除會員的方法，根據會員 ID
     * @param memberID 需要刪除的會員 ID
     */
    public void deleteMember(Integer memberID) {
        if (repository.existsById(memberID)) // 檢查該會員 ID 是否存在
            repository.deleteByMemberID(memberID); // 若存在則刪除會員資料
    }

    /**
     * 根據會員 ID 取得會員資料的方法
     * @param memberID 會員 ID
     * @return 會員物件，如果找不到則返回 null
     */
    public Member getOneMember(Integer memberID) {
        Optional<Member> optional = repository.findById(memberID); // 查找會員資料，回傳 Optional 物件
        return optional.orElse(null); // 若資料存在則回傳，否則回傳 null
    }

    /**
     * 取得所有會員資料的方法
     * @return 所有會員資料的列表
     */
    public List<Member> getAll() {
        return repository.findAll(); // 使用 repository 取得所有會員資料
    }

    /**
     * 根據查詢參數獲取會員列表
     * @param map 查詢參數的 Map
     * @return 會員列表
     */
    public List<Member> getAll(Map<String, String[]> map) {
        // 傳入查詢參數 map 和新開啟的 Hibernate Session，返回查詢結果的 Member 列表。
        return HibernateUtil_CompositeQuery_Member.getAllMembers(map, sessionFactory.openSession());
    }

	@Transactional
	public void updateMemberByCourseOrder(Member member) {
		repository.update(member.getReceiver(), member.getReceiverAddress(), member.getReceiverPhone(), member.getMemberID());
	}
}
