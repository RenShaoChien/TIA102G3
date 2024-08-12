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

import com.tia102g3.coachmember.model.CoachMember;

public class HibernateUtil_CompositeQuery_CoachMember {

    public static Predicate getPredicateForCoachMember(CriteriaBuilder builder, Root<CoachMember> root, String columnName, String value) {
        Predicate predicate = null;

        switch (columnName) {
            case "cMemberID":
                predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
                break;
            case "bD": // 假設是 Date
                predicate = builder.equal(root.get(columnName), java.sql.Date.valueOf(value));
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
    public static List<CoachMember> getAllCoachMembers(Map<String, String[]> map, Session session) {
        Transaction tx = session.beginTransaction();
        List<CoachMember> list = null;

        try {
            // 創建 CriteriaBuilder
            CriteriaBuilder builder = session.getCriteriaBuilder();
            // 創建 CriteriaQuery
            CriteriaQuery<CoachMember> criteriaQuery = builder.createQuery(CoachMember.class);
            // 創建 Root
            Root<CoachMember> root = criteriaQuery.from(CoachMember.class);

            List<Predicate> predicateList = new ArrayList<>();

            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue()[0];
                if (value != null && !value.trim().isEmpty() && !"action".equals(key)) {
                    Predicate predicate = getPredicateForCoachMember(builder, root, key, value.trim());
                    if (predicate != null) {
                        predicateList.add(predicate);
                    }
                }
            }

            if (!predicateList.isEmpty()) {
                criteriaQuery.where(predicateList.toArray(new Predicate[0]));
            }
            criteriaQuery.orderBy(builder.asc(root.get("cMemberID"))); // 可以按需要修改排序字段

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
