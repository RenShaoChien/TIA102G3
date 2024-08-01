package com.tia102g3.shoppingcart.dao;

import java.util.List;

import com.tia102g3.shoppingcart.entity.ShoppingCartVO;

public class TestHibernateDAO {
	
	public static void main(String[] arg) throws Exception {
		ShoppingCartDAO dao = new ShoppingCartDAOImpl();
		
		//新增
//		ShoppingCartVO sc1 = new ShoppingCartVO();
//		sc1.setMemberID(1);
//		sc1.setProductID(2);
//		sc1.setQuantity(15);
//		dao.insert(sc1);
		
		//修改
//		ShoppingCart sc2 = new ShoppingCart();
//		sc2.setShoppingCartID(6);
//		sc2.setMemberID(1);
//		sc2.setProductID(1);
//		sc2.setQuantity(100);
//		dao.update(sc2);
		
		// 刪除
//		dao.delete(6);
		
		// 查詢單筆
//		ShoppingCart sc3 = dao.findByPrimaryKey(7);
//		System.out.print(sc3.getShoppingCartID() + ",");
//		System.out.print(sc3.getMemberID() + ",");
//		System.out.print(sc3.getProductID() + ",");
//		System.out.print(sc3.getQuantity() + ",");
//		System.out.println("---------------------");
		
		//查詢多筆
		List<ShoppingCartVO> list = dao.getAll();
		for (ShoppingCartVO sc : list) {
			System.out.print(sc.getShoppingCartID() + ",");
			System.out.print(sc.getMemberID() + ",");
			System.out.print(sc.getProductID() + ",");
			System.out.print(sc.getQuantity() + ",");
			System.out.println();
		}
		
		
	}

}
