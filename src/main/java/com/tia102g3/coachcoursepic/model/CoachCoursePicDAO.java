package com.tia102g3.coachcoursepic.model;

import com.tia102g3.coachcourse.model.CoachCourse;

import java.util.List;

/**
 * ClassName： CoachCoursePicDAO
 * package：com.tia102g3.coachcoursepic
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/16 @{TIME}
 * @Version 1.0
 */
public interface CoachCoursePicDAO {
    /**
     * 獲取所有 CoachCoursePic。
     * @return 所有 CoachCoursePic 的列表。
     */
    List<CoachCoursePic> getAllList() throws Exception;

    /**
     * 根據 ID 獲取 CoachCourse。
     * @param coachCoursePicID coachCoursePic 的 ID。
     * @return 對應的 CoachCourse，如果不存在則返回 null。
     */
    CoachCoursePic getOneByID(Integer coachCoursePicID) throws Exception;

    /**
     * 新增 coachCoursePic。
     * @param coachCoursePic 要保存的 coachCoursePic。
     * @return 受影響的行數。
     */
    int insert(CoachCoursePic coachCoursePic) throws Exception;

    /**
     * 更新現有的 coachCoursePic。
     * @param coachCoursePic 要更新的 coachCoursePic。
     * @return 受影響的行數。
     */
    int update(CoachCoursePic coachCoursePic) throws Exception;

    /**
     * 根據 ID 刪除 CoachCourse。
     * @param coachCoursePicID 要刪除的 coachCoursePic 的 ID。
     * @return 受影響的行數。
     */
    int deleteById(Integer coachCoursePicID) throws Exception;
}
