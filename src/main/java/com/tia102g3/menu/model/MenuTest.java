package com.tia102g3.menu.model;

import java.util.List;

import org.hibernate.Session;

import com.tia102g3.membermenulist.model.MemberMenuListDAO;
import com.tia102g3.membermenulist.model.MemberMenuListDAOImpl;
import com.tia102g3.membermenulist.model.MemberMenuListService;
import com.tia102g3.membermenulist.model.MemberMenuListServiceImpl;
import com.tia102g3.membermenulist.model.MemberMenuListVO;

import com.utils.HibernateUtil;

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
