/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package com.tia102g3.foodlist.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

//import hibernate.util.HibernateUtil;
import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery; //Hibernate 5.2 開始 取代原 org.hibernate.Criteria 介面
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.criteria.Predicate;
import javax.persistence.Query; //Hibernate 5 開始 取代原 org.hibernate.Query 介面

//import com.emp.model.EmpVO;
//import com.dept.model.DeptVO;

public class HibernateUtil_CompositeQuery_FoodList {
    
    
    public static List<FoodListVO> findByFoodNumbers(List<Integer> foodNumbers, Session session) {
        // 获取CriteriaBuilder实例
        CriteriaBuilder cb = session.getCriteriaBuilder();

        // 创建CriteriaQuery对象，并指定返回类型
        CriteriaQuery<FoodListVO> query = cb.createQuery(FoodListVO.class);

        // 设置查询的根对象，即表对应的实体类
        Root<FoodListVO> foodListRoot = query.from(FoodListVO.class);

        // 创建条件，查询foodNumber在指定列表中的数据
        Predicate foodNumberPredicate = foodListRoot.get("foodVO").get("foodNumber").in(foodNumbers);


        // 将条件添加到查询中
        query.where(foodNumberPredicate);

        // 执行查询并返回结果
        return session.createQuery(query).getResultList();
    }
    
    
    public static List<FoodListVO> findByFoodNumbersExcludingMenus(List<Integer> foodNumbers, Session session) {
        // 获取CriteriaBuilder实例
        CriteriaBuilder cb = session.getCriteriaBuilder();

        // 创建主查询的CriteriaQuery对象，并指定返回类型
        CriteriaQuery<FoodListVO> mainQuery = cb.createQuery(FoodListVO.class);

        // 设置查询的根对象，即表对应的实体类
        Root<FoodListVO> foodListRoot = mainQuery.from(FoodListVO.class);

        // 创建子查询，用于查询指定foodNumbers对应的menuNumbers
        Subquery<Integer> subquery = mainQuery.subquery(Integer.class);
        Root<FoodListVO> subqueryRoot = subquery.from(FoodListVO.class);

        // 子查询条件，查询foodNumber在指定列表中的menuNumber
        subquery.select(subqueryRoot.get("menuVO").get("menuNumber"))
                .where(subqueryRoot.get("foodVO").get("foodNumber").in(foodNumbers));

        // 主查询条件，排除在子查询中找到的menuNumbers
        Predicate excludeMenuNumbersPredicate = foodListRoot.get("menuVO").get("menuNumber").in(subquery).not();

        // 将条件添加到主查询中
        mainQuery.where(excludeMenuNumbersPredicate);

        // 执行查询并返回结果
        return session.createQuery(mainQuery).getResultList();
    }

}
