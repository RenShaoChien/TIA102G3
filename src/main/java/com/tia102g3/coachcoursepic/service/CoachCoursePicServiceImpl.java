package com.tia102g3.coachcoursepic.service;

import com.tia102g3.coachcoursepic.model.CoachCoursePic;
import com.tia102g3.coachcoursepic.model.CoachCoursePicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName： CoachCoursePicServiceImpl
 * package：com.tia102g3.coachcoursepic.service
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
@Service
public class CoachCoursePicServiceImpl implements CoachCoursePicService  {
    @Autowired
    private CoachCoursePicDAO ccpDAO;

    @Override
    @Transactional
    public CoachCoursePic findCoachCoursePicById(Integer picID) {
        return ccpDAO.getReferenceById(picID);
    }
}
