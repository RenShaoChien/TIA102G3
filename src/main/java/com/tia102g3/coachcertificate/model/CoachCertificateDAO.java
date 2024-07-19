package com.tia102g3.coachcertificate.model;

import java.util.List;

/**
 * ClassName： CoachCertificateDAO
 * package：com.tia102g3.coachcertificate.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/19 @{TIME}
 * @Version 1.0
 */
public interface CoachCertificateDAO {
    /**
     * 加入教練證書
     * @param coachCertificate 教練證書物件
     * @return 插入結果影響的行數
     */
    int insertCoachCertificate(CoachCertificate coachCertificate) throws Exception;

    /**
     * 更新教練證書
     * @param coachCertificate 教練證書物件
     * @return 更新結果影響的行數
     */
    int updateCoachCertificate(CoachCertificate coachCertificate) throws Exception;

    /**
     * 根據ID刪除教練證書
     * @param coachCertificateID 教練證書ID
     * @return 刪除結果影響的行數
     */
    int deleteCoachCertificateByID(Integer coachCertificateID) throws Exception;

    /**
     * 根據ID查詢教練證書
     * @param coachCertificateID 教練證書ID
     * @return 查詢到的教練證書物件
     */
    CoachCertificate selectCoachCertificateById(Integer coachCertificateID) throws Exception;

    /**
     * 查詢所有教練證書
     * @return 所有教練證書的列表
     */
    List<CoachCertificate> getCoachCertificateList() throws Exception;

    /**
     * 根據教練會員ID查詢教練證書
     * @param cMemberID 教練會員ID
     * @return 查詢到的教練證書列表
     */
    List<CoachCertificate> getCoachCertificateListByCMemberId(Integer cMemberID) throws Exception;
}
