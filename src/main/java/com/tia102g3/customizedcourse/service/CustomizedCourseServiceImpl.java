package com.tia102g3.customizedcourse.service;

import com.tia102g3.customizedcourse.model.CustomizedCourseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
