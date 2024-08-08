package com.tia102g3.membermenulist.model;

import com.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class MemberMenuListTest {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			
			session.beginTransaction();
			
			MemberMenuListDAO dao = new MemberMenuListDAOImpl();
			
			// Query All by MemberMenuListDAO
//			List<MemberMenuListVO> list = dao.getAll();
//			for (MemberMenuListVO memberMenuListVO : list) {
//				System.out.println(memberMenuListVO.toString());
//				System.out.println("=================================");
//			}
			
			// Query All by MemberMenuListService
			MemberMenuListService dao2 = new MemberMenuListServiceImpl();
			List<MemberMenuListVO> list2 = dao2.getAllMenuList();
			for (MemberMenuListVO memberMenuListVO : list2) {
				System.out.println(memberMenuListVO.toString());
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
