package com.tia102g3.coachcoursepic.service;

import com.tia102g3.coachcoursepic.model.CoachCoursePic;
import com.tia102g3.coachcoursepic.model.CoachCoursePicDAO;

import java.util.List;

/**
 * ClassName： CoachCoursePicServiceImpl
 * package：com.tia102g3.coachcoursepic.service
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
public class CoachCoursePicServiceImpl implements CoachCoursePicService  {
    private CoachCoursePicDAO coachCoursePicDAO;

    @Override
    public int insertCoachCoursePic(CoachCoursePic coachCoursePic) throws Exception {
        return coachCoursePicDAO.insert(coachCoursePic);
    }

    @Override
    public int updateCoachCoursePic(CoachCoursePic coachCoursePic) throws Exception {
        return coachCoursePicDAO.update(coachCoursePic);
    }

    @Override
    public int deleteCoachCoursePicByID(Integer coachCoursePicID) throws Exception {
        return coachCoursePicDAO.deleteById(coachCoursePicID);
    }

    @Override
    public CoachCoursePic getCoachCoursePicByID(Integer coachCoursePicID) throws Exception {
        return coachCoursePicDAO.getOneByID(coachCoursePicID);
    }

    @Override
    public List<CoachCoursePic> getAllCoachCoursePics() throws Exception {
        return coachCoursePicDAO.getAllList();
    }
}
