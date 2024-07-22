package com.tia102g3.customizedcourse.model;

import java.util.List;

/**
 * ClassName： CustomizedCourseDAO
 * package：com.tia102g3.customizedcourse.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/19 @{TIME}
 * @Version 1.0
 */
public interface CustomizedCourseDAO {
    /**
     * 加入客製化課程
     * @param customizedCourse 客製化課程物件
     * @return 插入結果影響的行數
     */
    int insertCustomizedCourse(CustomizedCourse customizedCourse) throws Exception;

    /**
     * 更新客製化課程
     * @param customizedCourse 客製化課程物件
     * @return 更新結果影響的行數
     */
    int updateCustomizedCourse(CustomizedCourse customizedCourse) throws Exception;

    /**
     * 根據ID刪除客製化課程
     * @param customizeCourseID 客製化課程ID
     * @return 刪除結果影響的行數
     */
    int deleteCustomizedCourseByID(Integer customizeCourseID) throws Exception;

    /**
     * 根據ID查詢客製化課程
     * @param customizeCourseID 客製化課程ID
     * @return 查詢到的客製化課程物件
     */
    CustomizedCourse selectCustomizedCourseById(Integer customizeCourseID) throws Exception;

    /**
     * 查詢所有客製化課程
     * @return 所有客製化課程的列表
     */
    List<CustomizedCourse> getCustomizedCourseList() throws Exception;

    /**
     * 根據會員ID查詢客製化課程
     * @param memberID 會員ID
     * @return 查詢到的客製化課程列表
     */
    List<CustomizedCourse> getCustomizedCourseListByMemberID(Integer memberID) throws Exception;
}
