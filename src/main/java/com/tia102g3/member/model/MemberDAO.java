package com.tia102g3.member.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClassName： MemberDAO
 * package：com.tia102g3.member.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/28 上午12:24
 * @Version 1.0
 */
public interface MemberDAO extends JpaRepository<Member, Integer> {

}
