package com.tia102g3.member.model;

import java.util.List;

/**
 * ClassName： MemberDAO
 * package：com.tia102g3.member.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/28 上午12:24
 * @Version 1.0
 */
public interface MemberDAO {
    int insertMember(Member member) throws Exception;
    int updateMember(Member member) throws Exception;
    int deleteMemberByID(Integer memberID) throws Exception;
    Member getMemberByID(Integer memberID) throws Exception;
    List<Member> getMembersList() throws Exception;
}
