package com.tia102g3.systemcoursepic.service;

import com.tia102g3.systemcoursepic.model.SystemCoursePic;

/**
 * ClassName： SystemCoursePicService
 * package：com.tia102g3.systemcoursepic.service
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
public interface SystemCoursePicService {
    SystemCoursePic selectSystemCoursePicByID(Integer systemCoursePicID);
}
