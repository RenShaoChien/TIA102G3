package com.tia102g3.coachcertificate.service;

import com.tia102g3.coachcertificate.model.CoachCertificateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName： CoachCertificateServiceImpl
 * package：com.tia102g3.coachcertificate.service
 * Description： 教練證書服務實現類
 *
 * @Author 任少騫
 * @Create 2024/7/19 @{TIME}
 * @Version 1.0
 */
@Service
public class CoachCertificateServiceImpl implements CoachCertificateService {
    @Autowired
    private CoachCertificateDAO dao;


}
