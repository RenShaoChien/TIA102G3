package com.tia102g3.coachcertificate.service;

import com.tia102g3.coachcertificate.model.CoachCertificate;

import java.util.List;

/**
 * ClassName： CoachCertificateService
 * package：com.tia102g3.coachcertificate.service
 * Description： 教練證書服務介面
 *
 * @Author 任少騫
 * @Create 2024/7/19 @{TIME}
 * @Version 1.0
 */
public interface CoachCertificateService {
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
    CoachCertificate getCoachCertificateById(Integer coachCertificateID) throws Exception;

    /**
     * 查詢所有教練證書
     * @return 所有教練證書的列表
     */
    List<CoachCertificate> getCoachCertificatesList() throws Exception;

    /**
     * 根據教練會員ID查詢教練證書
     * @param cMemberID 教練會員ID
     * @return 查詢到的教練證書列表
     */
    List<CoachCertificate> getCoachCertificatesListByCMemberID(Integer cMemberID) throws Exception;
}
