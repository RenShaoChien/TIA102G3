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

import com.tia102g3.courseorder.model.CourseOrder;

public class HibernateUtil_CompositeQuery_CourseOrder {

    public static Predicate getPredicateForCourseOrder(CriteriaBuilder builder, Root<CourseOrder> root, String columnName, String value) {
        Predicate predicate = null;

        switch (columnName) {
            case "courseOrderID":
            case "memberID":
            case "coachCourseID":
            case "price":
            case "status":
                predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
                break;
            case "orderDate":
                predicate = builder.equal(root.get(columnName), java.sql.Date.valueOf(value));
                break;
            default:
                // 處理未知字段或忽略
                break;
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
