package hibernate.util.CompositeQuery;

//import hibernate.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query; //Hibernate 5 開始 取代原 org.hibernate.Query 介面
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery; //Hibernate 5.2 開始 取代原 org.hibernate.Criteria 介面
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tia102g3.resmap.model.ResMapVO;
import com.tia102g3.restifo.model.RestIfoVO;

public class HibernateUtil_CompositeQuery_rest_lfo {

	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<RestIfoVO> root, String columnName,
			String value) {

		Predicate predicate = null;

		if ("restLoc".equals(columnName)) // 用於Integer
			predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
		else if ("restName".equals(columnName) || "restAddress".equals(columnName) || "restTime".equals(columnName)
				|| "restTel".equals(columnName))// 用於varchar
			predicate = builder.like(root.get(columnName), "%" + value + "%");
		else if ("mapID".equals(columnName)) {
			ResMapVO resMapVO = new ResMapVO();
			resMapVO.setMapID(Integer.valueOf(value));
			predicate = builder.equal(root.get("resMapVO"), resMapVO);
		}

		return predicate;
	}

	@SuppressWarnings("unchecked")
	public static List<RestIfoVO> getAllC(Map<String, String[]> map, Session session) {

//		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<RestIfoVO> list = null;
		try {
			// 【●創建 CriteriaBuilder】
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// 【●創建 CriteriaQuery】
			CriteriaQuery<RestIfoVO> criteriaQuery = builder.createQuery(RestIfoVO.class);
			// 【●創建 Root】
			Root<RestIfoVO> root = criteriaQuery.from(RestIfoVO.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();

			Set<String> keys = map.keySet();
			int count = 0;
			for (String key : keys) {
				String value = map.get(key)[0];
				if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
					count++;
					predicateList.add(get_aPredicate_For_AnyDB(builder, root, key, value.trim()));
					System.out.println("有送出查詢資料的欄位數count = " + count);
				}
			}
			System.out.println("predicateList.size()=" + predicateList.size());
			criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
			criteriaQuery.orderBy(builder.asc(root.get("empno")));
			// 【●最後完成創建 javax.persistence.Query●】
			Query query = session.createQuery(criteriaQuery); // javax.persistence.Query; //Hibernate 5 開始 取代原
																// org.hibernate.Query 介面
			list = query.getResultList();

			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			throw ex; // System.out.println(ex.getMessage());
		} finally {
			session.close();
			// HibernateUtil.getSessionFactory().close();
		}

		return list;
	}
}
