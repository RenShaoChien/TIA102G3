package com.tia102g3.shoppingcart.dao;

import java.util.List;
import java.util.Map;

import com.tia102g3.shoppingcart.entity.ShoppingCartVO;


public interface ShoppingCartDAO {
	// 此介面定義對資料庫的相關存取抽象方法
	int insert(ShoppingCartVO sc);
	int update(ShoppingCartVO sc);
	int delete(Integer shoppingCartID);
	ShoppingCartVO findByPK(Integer shoppingCartID);
//	List<ShoppingCart> getAll(int currentPage);
	
//	long getTotal();
//	List<ShoppingCart> getByCompositeQuery(Map<String, String> query);
	List<ShoppingCartVO> getAll();
	
	
	
}
