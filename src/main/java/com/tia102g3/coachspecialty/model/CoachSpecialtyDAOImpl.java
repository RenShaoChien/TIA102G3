package com.tia102g3.coachspecialty.model;

import com.tia102g3.basedao.BaseDAO;
import com.utils.HibernateUtil;
import com.utils.JDBCUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

/**
 * ClassName： CoachSpecialtyDAOImpl
 * package：com.tia102g3.coachspecialty.model
 * Description： 教練專長資料訪問物件實現類
 *
 * @Author 任少騫
 * @Create 2024/7/19 @{TIME}
 * @Version 1.0
 */
@Repository
public class CoachSpecialtyDAOImpl extends BaseDAO<CoachSpecialty> implements CoachSpecialtyDAO {
    @Autowired
    private JDBCUtils jdbcUtils;
    @Override
    public int insertCoachSpecialty(CoachSpecialty coachSpecialty) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "INSERT INTO coach_specialty (cMemberID, sportEventID) VALUES (?, ?)";
        return update(conn, sql, coachSpecialty.getCMember().getCMemberID(), coachSpecialty.getSportEvent().getSportEventID());
    }

    @Override
    public int updateCoachSpecialty(CoachSpecialty coachSpecialty) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "UPDATE coach_specialty SET cMemberID=?, sportEventID=? WHERE coachSpecialtyID=?";
        return update(conn, sql, coachSpecialty.getCMember().getCMemberID(), coachSpecialty.getSportEvent().getSportEventID(), coachSpecialty.getCoachSpecialtyID());
    }

    @Override
    public int deleteCoachSpecialtyByID(Integer coachSpecialtyID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "DELETE FROM coach_specialty WHERE coachSpecialtyID=?";
        return update(conn, sql, coachSpecialtyID);
    }

    @Override
    public CoachSpecialty selectCoachSpecialtyByID(Integer coachSpecialtyID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "SELECT * FROM coach_specialty WHERE coachSpecialtyID=?";
        return getInstance(conn, sql, coachSpecialtyID);
    }

    @Override
    public List<CoachSpecialty> getAllCoachSpecialties() throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "SELECT * FROM coach_specialty";
        return getForList(conn, sql);
    }

    @Override
    public List<CoachSpecialty> getCoachSpecialtiesByCMemberID(Integer cMemberID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "SELECT * FROM coach_specialty WHERE cMemberID=?";
        return getForList(conn, sql, cMemberID);
    }
}
