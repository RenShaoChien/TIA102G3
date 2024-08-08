package com.tia102g3.menu.model;

import com.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class MenuTest {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

			session.beginTransaction();

			MenuDAO dao = new MenuDAOImpl();

			// Query All by MemberMenuListDAO
//			List<MenuVO> list = dao.getAll();
//			for (MenuVO memberVOList : list) {
//				System.out.println(memberVOList.toString());
//				System.out.println("=================================");
//			}

			// Query All by MemberMenuListService
			MenuService dao2 = new MenuServiceImpl();
			List<MenuVO> list2 = dao2.getAllMenu();
			for (MenuVO menuList : list2) {
				System.out.println(menuList.toString());
				System.out.println("=================================");
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
