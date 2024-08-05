package hibernate.util.CompositeQuery;

import java.util.*;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tia102g3.order.model.OrderVO;
//import com.tia102g3.member.model.MemberVO;


//缺MemberVO
public class HibernateUtilCompositeQuery_orderid {
	
	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<OrderVO> root, String columnName, String value) {
		
		Predicate predicate = null;
		
		if("orderID".equals(columnName))
			predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
		else if ("totalPrice".equals(columnName))
			predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
		else if ("status".equals(columnName))
			predicate = builder.equal(root.get(columnName), "%" + value + "%");
		else if("orderDate".equals(columnName))
			predicate = builder.equal(root.get(columnName), java.sql.Timestamp.valueOf(value));
//		else if ("memberID".equals(columnName)) {
//			MemberVO memberVO = new MemberVO();
//			memberVO.setMemberID(Integer.valueOf(value));
//			predicate = builder.equal(root.get("memberVO"), memberVO);
//		}
		
		return predicate;
		
	}

	@SuppressWarnings("unchecked")
	public static List<OrderVO> getAllC(Map<String, String[]>map, Session session){
		
		Transaction tx = session.beginTransaction();
		List<OrderVO> list = null;
		try {
			//創建CriteriaBuilder
			CriteriaBuilder builder = session.getCriteriaBuilder();
			//創建 CriteriaQuery
			CriteriaQuery<OrderVO> criteriaQuery = builder.createQuery(OrderVO.class);
			//創建root
			Root<OrderVO> root = criteriaQuery.from(OrderVO.class);
			
			List<Predicate> predicateList = new ArrayList<Predicate>();
			
			Set<String> keys = map.keySet();
			int count = 0;
			for (String key :keys) {
				String value = map.get(key)[0];
				if(value != null && value.trim().length() !=0 && !"action".equals(key)) {
					count++;
					predicateList.add(get_aPredicate_For_AnyDB(builder, root, key, value.trim()));
					System.out.println("有送出查詢資料的欄位數count = " + count);
				}
			}
			System.out.println("predicateList.size()="+predicateList.size());
			criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
			criteriaQuery.orderBy(builder.asc(root.get("orderID")));
			// 【●最後完成創建 javax.persistence.Query●】
			Query query = session.createQuery(criteriaQuery);
			list = query.getResultList();
			
			tx.commit();
		}catch (RuntimeException ex) {
			if(tx != null)
				tx.rollback();
			throw ex; // System.out.println(ex.getMessage());
		} finally {
			// HibernateUtil.getSessionFactory().close();
		}
		return list;
		
	}
	
}