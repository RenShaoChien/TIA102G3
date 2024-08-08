package com.tia102g3.systemcoursepic.service;

import com.tia102g3.systemcoursepic.model.SystemCoursePic;
import com.tia102g3.systemcoursepic.model.SystemCoursePicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName： SystemCoursePicServiceImpl
 * package：com.tia102g3.systemcoursepic.service
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/18 @{TIME}
 * @Version 1.0
 */
@Service
public class SystemCoursePicServiceImpl implements SystemCoursePicService {
    @Autowired
    private SystemCoursePicDAO systemCoursePicDAO;

    public SystemCoursePic selectSystemCoursePicByID(Integer systemCoursePicID) {
        return systemCoursePicDAO.getReferenceById(systemCoursePicID);
    }

}
