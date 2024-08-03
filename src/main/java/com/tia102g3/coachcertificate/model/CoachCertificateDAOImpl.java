package com.tia102g3.coachcertificate.model;

import com.tia102g3.basedao.BaseDAO;
import com.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

/**
 * ClassName： CoachCertificateDAOImpl
 * package：com.tia102g3.coachcertificate.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/19 @{TIME}
 * @Version 1.0
 */
@Repository
public class CoachCertificateDAOImpl extends BaseDAO<CoachCertificate> implements CoachCertificateDAO {
    @Autowired
    private JDBCUtils jdbcUtils;
//
//    @Autowired
//    private SessionFactory sessionFactory;

    @Override
    public int insertCoachCertificate(CoachCertificate coachCertificate) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "INSERT INTO coach_certificate (cMemberID, certificateName, certificatePic) VALUES (?, ?, ?)";
        int result = update(conn, sql, coachCertificate.getCMember().getCMemberID(), coachCertificate.getCertificateName(), coachCertificate.getCertificatePic());
        return result;
    }


    @Override
    public int updateCoachCertificate(CoachCertificate coachCertificate) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "UPDATE coach_certificate SET cMemberID=?, certificateName=?, certificatePic=? WHERE coachCertificateID=?";
        int result = update(conn, sql, coachCertificate.getCMember().getCMemberID(), coachCertificate.getCertificateName(), coachCertificate.getCertificatePic(), coachCertificate.getCoachCertificateID());
        return result;
    }

    @Override
    public int deleteCoachCertificateByID(Integer coachCertificateID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "DELETE FROM coach_certificate WHERE coachCertificateID=?";
        int result = update(conn, sql, coachCertificateID);
        return result;
    }

    @Override
    public CoachCertificate selectCoachCertificateById(Integer coachCertificateID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "SELECT * FROM coach_certificate WHERE coachCertificateID=?";
        CoachCertificate result = getInstance(conn, sql, coachCertificateID);
        return result;
    }

    @Override
    public List<CoachCertificate> getCoachCertificateList() throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "SELECT * FROM coach_certificate";
        List<CoachCertificate> result = getForList(conn, sql);
        return result;
    }

    @Override
    public List<CoachCertificate> getCoachCertificateListByCMemberId(Integer cMemberID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "SELECT * from coach_certificate WHERE cMemberID = ?";
        List<CoachCertificate> result = getForList(conn, sql, cMemberID);
        return result;
    }
}