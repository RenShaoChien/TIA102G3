package com.tia102g3.likefood.model;

import java.util.List;

public class LikeFoodServiceImpl implements LikeFoodService {

	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private LikeFoodDAO dao;

	public LikeFoodServiceImpl() {
			super();
			dao = new LikeFoodDAOImpl();
		}

	@Override
	public List<LikeFoodVO> getAllLikeFood() {
		return dao.getAll();
	}

}
