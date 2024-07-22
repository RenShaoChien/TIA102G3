package com.tia102g3.coachcertificate.service;

import com.tia102g3.coachcertificate.model.CoachCertificate;
import com.tia102g3.coachcertificate.model.CoachCertificateDAO;

import java.util.List;

/**
 * ClassName： CoachCertificateServiceImpl
 * package：com.tia102g3.coachcertificate.service
 * Description： 教練證書服務實現類
 *
 * @Author 任少騫
 * @Create 2024/7/19 @{TIME}
 * @Version 1.0
 */
public class CoachCertificateServiceImpl implements CoachCertificateService {
    private CoachCertificateDAO dao;

    @Override
    public int insertCoachCertificate(CoachCertificate coachCertificate) throws Exception {
        return dao.insertCoachCertificate(coachCertificate);
    }

    @Override
    public int updateCoachCertificate(CoachCertificate coachCertificate) throws Exception {
        return dao.updateCoachCertificate(coachCertificate);
    }

    @Override
    public int deleteCoachCertificateByID(Integer coachCertificateID) throws Exception {
        return dao.deleteCoachCertificateByID(coachCertificateID);
    }

    @Override
    public CoachCertificate getCoachCertificateById(Integer coachCertificateID) throws Exception {
        return dao.selectCoachCertificateById(coachCertificateID);
    }

    @Override
    public List<CoachCertificate> getCoachCertificatesList() throws Exception {
        return dao.getCoachCertificateList();
    }

    @Override
    public List<CoachCertificate> getCoachCertificatesListByCMemberID(Integer cMemberID) throws Exception {
        return dao.getCoachCertificateListByCMemberId(cMemberID);
    }
}
