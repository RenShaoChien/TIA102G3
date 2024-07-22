package com.tia102g3.likefood.model;

import java.util.List;

public interface LikeFoodDAO {
	public List<LikeFoodVO> getAll();

	public Integer insert(LikeFoodVO foodLikeVO);

	public Integer update(LikeFoodVO foodLikeVO);

	public LikeFoodVO getOne(Integer memberID);
}
