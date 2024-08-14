package com.tia102g3.likefood.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("likeFoodService")
public class LikeFoodService {

    @Autowired
    LikeFoodRepository repository;

    public void deleteMemberID(Integer memberID) {
        repository.deleteById(memberID);
    }

    public List<LikeFoodVO> getAll() {
        return repository.findAll();
    }

    public void addLikeFood(LikeFoodVO likeFoodVO) {
        repository.save(likeFoodVO);
    }

    public void updateLikeFood(LikeFoodVO likeFoodVO) {
        repository.save(likeFoodVO);
    }
    
    
    public LikeFoodVO getOneLikeFood(Integer memberID) {
        Optional<LikeFoodVO> optional = repository.findById(memberID);

        return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
    }
    
    public List<LikeFoodVO> getByMemberID(Integer memberID) {
//        return repository.findByMemberID(memberID);
        return repository.findByMemberIDOrderByFoodPreferenceDesc(memberID);
    }

}
