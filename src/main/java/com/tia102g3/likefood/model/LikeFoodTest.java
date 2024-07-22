package com.tia102g3.likefood.model;

import java.util.List;

import org.hibernate.Session;

import com.tia102g3.menu.model.MenuDAO;
import com.tia102g3.menu.model.MenuDAOImpl;
import com.tia102g3.menu.model.MenuService;
import com.tia102g3.menu.model.MenuServiceImpl;
import com.tia102g3.menu.model.MenuVO;

import com.utils.HibernateUtil;

public class LikeFoodTest {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

			session.beginTransaction();

			LikeFoodDAO dao = new LikeFoodDAOImpl();

			// Query All by MemberMenuListDAO
//			List<LikeFoodVO> list = dao.getAll();
//			for (LikeFoodVO likeFoodVOList : list) {
//				System.out.println(likeFoodVOList.toString());
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
