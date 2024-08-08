package com.tia102g3.food.model;

import com.utils.HibernateUtil;
import org.hibernate.Session;

public class FoodTest {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			
			session.beginTransaction();
			
//			Book book = session.get(Book.class, 1);
//			System.out.println(book.getBookIsbn().getIsbn());
			FoodDAOImpl dao = new FoodDAOImpl();
//			dao.getAll();
			
			// Query All
//			List<FoodVO> list = dao.getAll();
//			for (FoodVO aFood : list) {
//				System.out.print(aFood.getFoodNumber() + ",");
//				System.out.print(aFood.getFoodTypeNumber() + ",");
//				System.out.print(aFood.getFoodName() + ",");
//				System.out.print(aFood.getFoodCalories() + ",");
//				System.out.println();
//			}
			
			// GetOne
			FoodVO foodVO3 = dao.getOne(3);
			System.out.print(foodVO3.getFoodNumber() + ",");
			System.out.print(foodVO3.getFoodTypeNumber() + ",");
			System.out.print(foodVO3.getFoodName() + ",");
			System.out.println(foodVO3.getFoodCalories() + ",");
			System.out.println("=================================");
			
			// INSERT
//			FoodVO foodVO1 = new FoodVO();
////			foodVO1.setFoodNumber(4);
//			foodVO1.setFoodTypeNumber(3);
//			foodVO1.setFoodName("蘿蔔35");
//			foodVO1.setFoodCalories(3535);
//			dao.insert(foodVO1);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
	}
	
	
}
