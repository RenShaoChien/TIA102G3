package com.tia102g3.food.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.utils.HibernateUtil;

public class FoodDAOImpl implements FoodDAO {
	
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public FoodDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public void insert(FoodVO foodVO) {
		getSession().save(foodVO);
	}

	@Override
	public void update(FoodVO foodVO) {
		getSession().update(foodVO);
	}
	
	@Override
	public FoodVO getOne(Integer foodNumber) {
		return getSession().get(FoodVO.class, foodNumber);
	}
	
	@Override
	public void delete(Integer foodNumber) {
		FoodVO foodVO = getSession().get(FoodVO.class, foodNumber);
		if(foodVO != null) {
			getSession().delete(foodVO);
		}
	}
	
	
	@Override
	public List<FoodVO> getAll() {
//		List<FoodVO> list = new ArrayList<FoodVO>();
//		FoodVO foodVO = null;

		return getSession().createQuery("from FoodVO", FoodVO.class).list();
	}

	public static void main(String[] args) {
		FoodDAOImpl dao = new FoodDAOImpl();
		
		// INSERT
//		FoodVO foodVO1 = new FoodVO();
//		foodVO1.setFoodNumber(4);
//		foodVO1.setFoodTypeNumber(3);
//		foodVO1.setFoodName("蘿蔔");
//		foodVO1.setFoodCalories(99);
//		dao.insert(foodVO1);
		
		// INSERT
//		FoodVO foodVO2 = new FoodVO();
//		foodVO2.setFoodNumber(4);
//		foodVO2.setFoodTypeNumber(4);
//		foodVO2.setFoodName("空心菜");
//		foodVO2.setFoodCalories(44);
//		dao.update(foodVO2);
		
		// GetOne
//		FoodVO foodVO3 = dao.getOne(2);
//		System.out.print(foodVO3.getFoodNumber() + ",");
//		System.out.print(foodVO3.getFoodTypeNumber() + ",");
//		System.out.print(foodVO3.getFoodName() + ",");
//		System.out.println(foodVO3.getFoodCalories() + ",");
//		System.out.println("=================================");
		
		// Query All
		List<FoodVO> list = dao.getAll();
		for (FoodVO aFood : list) {
			System.out.print(aFood.getFoodNumber() + ",");
			System.out.print(aFood.getFoodTypeNumber() + ",");
			System.out.print(aFood.getFoodName() + ",");
			System.out.print(aFood.getFoodCalories() + ",");
			System.out.println();

		}

	}

}
