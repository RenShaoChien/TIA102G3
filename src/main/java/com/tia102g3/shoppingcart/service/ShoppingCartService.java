package com.tia102g3.shoppingcart.service;

import com.tia102g3.shoppingcart.dao.ShoppingCartDAO;
import com.tia102g3.shoppingcart.dao.ShoppingCartDAOImpl;
import com.tia102g3.shoppingcart.entity.ShoppingCartVO;

import java.util.List;

public class ShoppingCartService {

	private ShoppingCartDAO dao;

	public ShoppingCartService() {
		dao = new ShoppingCartDAOImpl();
	}

	public ShoppingCartVO insertSCart(Integer memberID, Integer productID, Integer quantity) {

		ShoppingCartVO sc = new ShoppingCartVO();

		sc.setMemberID(memberID);
		sc.setProductID(productID);
		sc.setQuantity(quantity);
		dao.insert(sc);

		return sc;
	}

	public ShoppingCartVO updateSCart(Integer shoppingCartID, Integer memberID, Integer productID, Integer quantity) {

		ShoppingCartVO sc = new ShoppingCartVO();

		sc.setShoppingCartID(shoppingCartID);
		sc.setMemberID(memberID);
		sc.setProductID(productID);
		sc.setQuantity(quantity);
		dao.update(sc);

		return sc;
	}

	public void deleteSCart(Integer sc) {
		dao.delete(sc);
	}

	public ShoppingCartVO findByPK(Integer shoppingCartID) {
		return dao.findByPK(shoppingCartID);
	}

	public List<ShoppingCartVO> getAll() {
		return dao.getAll();
	}
}
