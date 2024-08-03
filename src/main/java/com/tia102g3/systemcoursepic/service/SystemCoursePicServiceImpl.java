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

    @Override
    public int insertSystemCoursePic(SystemCoursePic systemCoursePic) throws Exception {

        return systemCoursePicDAO.insertSystemCoursePic(systemCoursePic);
    }

    @Override
    public int deleteSystemCoursePicByID(Integer systemCoursePicID) throws Exception {

        return systemCoursePicDAO.deleteSystemCoursePicByID(systemCoursePicID);
    }

    @Override
    public int updateSystemCoursePic(SystemCoursePic systemCoursePic) throws Exception {


        return systemCoursePicDAO.updateSystemCoursePic(systemCoursePic);
    }

    @Override
    public SystemCoursePic selectSystemCoursePicByID(Integer systemCoursePicID) throws Exception {


        return systemCoursePicDAO.selectSystemCoursePicByID(systemCoursePicID);
    }
}
