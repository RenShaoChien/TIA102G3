package com.tia102g3.coachcoursepic.service;

import com.tia102g3.coachcoursepic.model.CoachCoursePic;

import java.util.List;

/**
 * ClassName： CoachCoursePicService
 * package：com.tia102g3.coachcoursepic.service
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
public interface CoachCoursePicService {
    /**
     * 新增圖片
     * @param coachCoursePic
     * @return
     * @throws Exception
     */
    int insertCoachCoursePic(CoachCoursePic coachCoursePic) throws Exception;

    /**
     * 更新圖片
     * @param coachCoursePic
     * @return
     * @throws Exception
     */
    int updateCoachCoursePic(CoachCoursePic coachCoursePic) throws Exception;

    /**
     * 刪除圖片
     * @param coachCoursePicID
     * @return
     * @throws Exception
     */
    int deleteCoachCoursePicByID(Integer coachCoursePicID) throws Exception;

    /**
     * 根據ID查詢圖片
     * @param coachCoursePicID
     * @return
     * @throws Exception
     */
    CoachCoursePic getCoachCoursePicByID(Integer coachCoursePicID) throws Exception;

    /**
     * 查詢所有圖片
     * @return
     * @throws Exception
     */
    List<CoachCoursePic> getAllCoachCoursePics() throws Exception;
}
