package com.tia102g3.food.model;

import java.util.List;
import org.hibernate.Session;
import com.utils.HibernateUtil;

public class FoodServiceImpl {
	
	private FoodDAO dao;
	
	public FoodServiceImpl() {
		dao =  new FoodDAOImpl();
	}
	
	public FoodVO addFood(Integer foodNumber, Integer foodTypeNumber, 
			String foodName, Integer foodCalories) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			FoodVO foodVO = new FoodVO();
			foodVO.setFoodNumber(foodNumber);
			foodVO.setFoodTypeNumber(foodTypeNumber);
			foodVO.setFoodName(foodName);
			foodVO.setFoodCalories(foodCalories);
			dao.insert(foodVO);
			
			System.out.println(foodNumber);
			session.getTransaction().commit();
			return foodVO;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		
		// JDBC 
//		FoodVO foodVO = new FoodVO();
//		foodVO.setFoodNumber(foodNumber);
//		foodVO.setFoodTypeNumber(foodTypeNumber);
//		foodVO.setFoodName(foodName);
//		foodVO.setFoodCalories(foodCalories);
//		dao.insert(foodVO);
//		return foodVO;
		
	}
	
	public FoodVO updateFood(Integer foodNumber, Integer foodTypeNumber, 
			String foodName, Integer foodCalories) {
		FoodVO foodVO = new FoodVO();
		foodVO.setFoodNumber(foodNumber);
		foodVO.setFoodTypeNumber(foodTypeNumber);
		foodVO.setFoodName(foodName);
		foodVO.setFoodCalories(foodCalories);
		dao.update(foodVO);
		
		return foodVO;
	}
	
	public FoodVO getOneFood(Integer foodNumber) {
//		return dao.getOne(foodNumber);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			FoodVO foodVO = dao.getOne(foodNumber);
			System.out.println(foodNumber);
			session.getTransaction().commit();
			return foodVO;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}
	
	public void deleteFood(Integer foodNumber) {
		dao.delete(foodNumber);
	}
	
	public List<FoodVO> getAll(){
//		return dao.getAll();
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<FoodVO> list = dao.getAll();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}
	
}
