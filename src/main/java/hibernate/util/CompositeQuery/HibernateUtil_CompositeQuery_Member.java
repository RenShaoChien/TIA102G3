package hibernate.util.CompositeQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tia102g3.member.model.Member;

public class HibernateUtil_CompositeQuery_Member {

	public static Predicate getPredicateForMember(CriteriaBuilder builder, Root<Member> root, String columnName, String value) {
	    Predicate predicate = null;

	    switch (columnName) {
	        case "memberID":
	            predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
	            break;
	        case "regDate": // 修正為註冊日期字段
	            try {
	                java.sql.Date sqlDate = java.sql.Date.valueOf(value);
	                predicate = builder.equal(root.get(columnName), sqlDate);
	            } catch (IllegalArgumentException e) {
	                // 處理日期格式錯誤
	                predicate = builder.disjunction(); // 返回一個總是為假的條件
	            }
	            break;
	        case "name":
	        case "email":
	            predicate = builder.like(root.get(columnName), "%" + value + "%");
	            break;
	        // 添加更多字段的處理邏輯
	        default:
	            // 處理未知字段或忽略
	            break;
	    }

	    return predicate;
	}

    @SuppressWarnings("unchecked")
    public static List<Member> getAllMembers(Map<String, String[]> map, Session session) {
        Transaction tx = session.beginTransaction();
        List<Member> list = null;

        try {
            // 創建 CriteriaBuilder
            CriteriaBuilder builder = session.getCriteriaBuilder();
            // 創建 CriteriaQuery
            CriteriaQuery<Member> criteriaQuery = builder.createQuery(Member.class);
            // 創建 Root
            Root<Member> root = criteriaQuery.from(Member.class);

            List<Predicate> predicateList = new ArrayList<>();

            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue()[0];
                if (value != null && !value.trim().isEmpty() && !"action".equals(key)) {
                    Predicate predicate = getPredicateForMember(builder, root, key, value.trim());
                    if (predicate != null) {
                        predicateList.add(predicate);
                    }
                }
            }

            if (!predicateList.isEmpty()) {
                criteriaQuery.where(predicateList.toArray(new Predicate[0]));
            }
            criteriaQuery.orderBy(builder.asc(root.get("memberID"))); // 可以按需要修改排序字段

            // 創建 Query
            javax.persistence.Query query = session.createQuery(criteriaQuery);
            list = query.getResultList();

            tx.commit();
        } catch (RuntimeException ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }

        return list;
    }
}
