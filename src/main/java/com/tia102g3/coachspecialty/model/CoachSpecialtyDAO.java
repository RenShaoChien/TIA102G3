package com.tia102g3.coachspecialty.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClassName： CoachSpecialtyDAO
 * package：com.tia102g3.coachspecialty.model
 * Description： 教練專長資料訪問物件介面
 *
 * @Author 任少騫
 * @Create 2024/7/19 @{TIME}
 * @Version 1.0
 */
public interface CoachSpecialtyDAO extends JpaRepository<CoachSpecialty, Integer> {

}
