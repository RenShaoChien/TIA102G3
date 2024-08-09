package com.tia102g3.membermenulist.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberMenuListService")
public class MemberMenuListService {

    @Autowired
    MemberMenuListRepository repository;

    public void deleteMemberMenuList(Integer menuListSN) {
        repository.deleteById(menuListSN);
    }

    public List<MemberMenuListVO> getAll() {
        return repository.findAll();
    }

    public void addMemberMenuList(MemberMenuListVO memberMenuList) {
        repository.save(memberMenuList);
    }

    public void updateMemberMenuList(MemberMenuListVO memberMenuList) {
        repository.save(memberMenuList);
    }
    
    
    public MemberMenuListVO getOneMemberMenuList(Integer menuListSN) {
        Optional<MemberMenuListVO> optional = repository.findById(menuListSN);

        return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
    }

}

