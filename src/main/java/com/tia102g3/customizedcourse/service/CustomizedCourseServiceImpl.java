package com.tia102g3.customizedcourse.service;

import com.tia102g3.customizedcourse.model.CustomizedCourse;
import com.tia102g3.customizedcourse.model.CustomizedCourseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName： CustomizedCourseServiceImpl
 * package：com.tia102g3.customizedcourse.service
 * Description： 客製化課程服務實現類
 *
 * @Author 任少騫
 * @Create 2024/7/19 @{TIME}
 * @Version 1.0
 */
@Service
public class CustomizedCourseServiceImpl implements CustomizedCourseService {
    @Autowired
    private CustomizedCourseDAO dao;

    @Override
    public int insertCustomizedCourse(CustomizedCourse customizedCourse) throws Exception {
        return dao.insertCustomizedCourse(customizedCourse);
    }

    @Override
    public int updateCustomizedCourse(CustomizedCourse customizedCourse) throws Exception {
        return dao.updateCustomizedCourse(customizedCourse);
    }

    @Override
    public int deleteCustomizedCourseByID(Integer customizeCourseID) throws Exception {
        return dao.deleteCustomizedCourseByID(customizeCourseID);
    }

    @Override
    public CustomizedCourse getCustomizedCourseById(Integer customizeCourseID) throws Exception {
        return dao.selectCustomizedCourseById(customizeCourseID);
    }

    @Override
    public List<CustomizedCourse> getCustomizedCourseList() throws Exception {
        return dao.getCustomizedCourseList();
    }

    @Override
    public List<CustomizedCourse> getCustomizedCourseListByMemberID(Integer memberID) throws Exception {
        return dao.getCustomizedCourseListByMemberID(memberID);
    }
}
