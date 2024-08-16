package hibernate.util.CompositeQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.courseorder.model.CourseOrder;
import com.tia102g3.member.model.Member;

public class HibernateUtil_CompositeQuery_CourseOrder {

	public static Predicate getPredicateForCourseOrder(CriteriaBuilder builder, Root<CourseOrder> root, String columnName, String value) {
	    Predicate predicate = null;
	    String[] parts = columnName.split("\\.");  // 支持嵌套屬性，例如 "member.memberID"
	    
	    if (parts.length == 1) {
	        // 單層屬性
	        switch (parts[0]) {
	            case "courseOrderID":
	            case "price":
	            case "status":
	                predicate = builder.equal(root.get(parts[0]), Integer.valueOf(value));
	                break;
	            case "orderDate":
	                predicate = builder.equal(root.get(parts[0]), java.sql.Date.valueOf(value));
	                break;
	            default:
	                break;
	        }
	    } else if (parts.length == 2) {
	        // 嵌套屬性
	        if ("member".equals(parts[0]) && "memberID".equals(parts[1])) {
	            Join<CourseOrder, Member> memberJoin = root.join("member");
	            predicate = builder.equal(memberJoin.get("memberID"), value);
	        } else if ("coachCourse".equals(parts[0]) && "id".equals(parts[1])) {
	            Join<CourseOrder, CoachCourse> coachCourseJoin = root.join("coachCourse");
	            predicate = builder.equal(coachCourseJoin.get("id"), Integer.valueOf(value));
	        }
	    }
	    return predicate;
	}

    @SuppressWarnings("unchecked")
    public static List<CourseOrder> getAllCourseOrders(Map<String, String[]> map, Session session) {
        Transaction tx = session.beginTransaction();
        List<CourseOrder> list = null;

        try {
            // 創建 CriteriaBuilder
            CriteriaBuilder builder = session.getCriteriaBuilder();
            // 創建 CriteriaQuery
            CriteriaQuery<CourseOrder> criteriaQuery = builder.createQuery(CourseOrder.class);
            // 創建 Root
            Root<CourseOrder> root = criteriaQuery.from(CourseOrder.class);

            List<Predicate> predicateList = new ArrayList<>();

            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue()[0];
                if (value != null && !value.trim().isEmpty() && !"action".equals(key)) {
                    Predicate predicate = getPredicateForCourseOrder(builder, root, key, value.trim());
                    if (predicate != null) {
                        predicateList.add(predicate);
                    }
                }
            }

            if (!predicateList.isEmpty()) {
                criteriaQuery.where(predicateList.toArray(new Predicate[0]));
            }
            criteriaQuery.orderBy(builder.asc(root.get("courseOrderID"))); // 可以按需要修改排序字段

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
