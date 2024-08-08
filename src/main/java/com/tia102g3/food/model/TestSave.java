package com.tia102g3.food.model;

import com.utils.HibernateUtil;
import org.hibernate.Session;

public class TestSave {
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			FoodVO foodVO = new FoodVO();
//			foodVO.setFoodNumber(33);
			foodVO.setFoodTypeNumber(2);
			foodVO.setFoodName("測試青菜34");
			foodVO.setFoodCalories(3434);
			
			session.save(foodVO);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}
		
		
	}
}
