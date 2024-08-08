package com.tia102g3.foodlist.model;

import com.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class FoodListTest {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

			session.beginTransaction();
			
			FoodListDAO dao = new FoodListDAOImpl();

			// Query All
//			List<FoodListVO> list = dao.getAll();
//			for (FoodListVO foodlist : list) {
//				System.out.println(foodlist.toString());
//				System.out.println("=================================");
//			}

			// GetOne
//			HealthStatusVO healthStatus = dao.getOne(1);
////			System.out.println(healthStatus.getHeight());
//			System.out.println(healthStatus.toString());
//			System.out.println("=================================");

			// INSERT
//			HealthStatusVO healthStatusVO1 = new HealthStatusVO();
//			HealthStatusVO healthStatusVO1 = new HealthStatusVO(7, 177, 46, 21.6, 1400, 1500, 5);

//			healthStatusVO1.setMemberID(5);
//			healthStatusVO1.setHeight(155);
//			healthStatusVO1.setWeight(45);
//			healthStatusVO1.setBmi(20.5);
//			healthStatusVO1.setBmr(1500);
//			healthStatusVO1.setTdee(1600);
//			healthStatusVO1.setIntensity(2);

//			dao.insert(healthStatusVO1);

			// update time test
//			HealthStatusVO healthStatusVO2 = dao.getOne(6);
//			healthStatusVO2.setCreate_dt(new Timestamp(System.currentTimeMillis()));
//			dao.update(healthStatusVO2);			
//			System.out.println(healthStatusVO2.toString());
//			System.out.println("=================================");

//			HealthStatusService dao2 = new HealthStatusServiceImpl();
//			// Query All
//			List<HealthStatusVO> list = dao2.getAllHealthStatus();
//			for (HealthStatusVO health : list) {
//				System.out.println(health.toString());
//				System.out.println("=================================");
//			}
			
			FoodListService dao2 = new FoodListServiceImpl();
			List<FoodListVO> list2 = dao2.getAllFoodList();
			for(FoodListVO foodList : list2) {
				System.out.println(foodList.toString());
			}
			

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}

	}
}
