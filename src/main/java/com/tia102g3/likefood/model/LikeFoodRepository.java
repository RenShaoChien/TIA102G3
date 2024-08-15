package com.tia102g3.likefood.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeFoodRepository extends JpaRepository<LikeFoodVO, Integer> {

    List<LikeFoodVO> findByMemberID(Integer memberID);
    
    List<LikeFoodVO> findByMemberIDOrderByFoodPreferenceDesc(Integer memberID);
    
}
