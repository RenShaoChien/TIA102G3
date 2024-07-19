package com.tia102g3.coachspecialty.model;

import java.util.List;

/**
 * ClassName： CoachSpecialtyDAO
 * package：com.tia102g3.coachspecialty.model
 * Description： 教練專長資料訪問物件介面
 *
 * @Author 任少騫
 * @Create 2024/7/19 @{TIME}
 * @Version 1.0
 */
public interface CoachSpecialtyDAO {
    /**
     * 插入教練專長
     * @param coachSpecialty 教練專長物件
     * @return 插入結果影響的行數
     */
    int insertCoachSpecialty(CoachSpecialty coachSpecialty) throws Exception;

    /**
     * 更新教練專長
     * @param coachSpecialty 教練專長物件
     * @return 更新結果影響的行數
     */
    int updateCoachSpecialty(CoachSpecialty coachSpecialty) throws Exception;

    /**
     * 根據ID刪除教練專長
     * @param coachSpecialtyID 教練專長ID
     * @return 刪除結果影響的行數
     */
    int deleteCoachSpecialtyByID(Integer coachSpecialtyID) throws Exception;

    /**
     * 根據ID查詢教練專長
     * @param coachSpecialtyID 教練專長ID
     * @return 查詢到的教練專長物件
     */
    CoachSpecialty selectCoachSpecialtyByID(Integer coachSpecialtyID) throws Exception;

    /**
     * 查詢所有教練專長
     * @return 所有教練專長的列表
     */
    List<CoachSpecialty> getAllCoachSpecialties() throws Exception;

    /**
     * 根據教練會員ID查詢教練專長
     * @param cMemberID 教練會員ID
     * @return 查詢到的教練專長列表
     */
    List<CoachSpecialty> getCoachSpecialtiesByCMemberID(Integer cMemberID) throws Exception;
}
