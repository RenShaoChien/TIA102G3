package com.tia102g3.coachmember.model;

import com.tia102g3.basedao.BaseDAO;
import com.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

/**
 * ClassName： CoachMemberDAOImpl
 * package：com.tia102g3.coachmember.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/27 @{TIME}
 * @Version 1.0
 */
@Repository
public class CoachMemberDAOImpl extends BaseDAO<CoachMember> implements CoachMemberDAO {
    @Autowired
    private JDBCUtils jdbcUtils;
    @Override
    public int insetCoachMember(CoachMember coachMember) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "inset into coach_member(profilePic, status) values(?,?)";
        return super.update(conn, sql, coachMember.getProfilePic(), coachMember.getStatus());
    }

    @Override
    public int updateCoachMember(CoachMember coachMember) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "update coach_member set profilePic = ?, status = ? where cMemberID =?";

        return super.update(conn, sql, coachMember.getProfilePic(), coachMember.getStatus(), coachMember.getCMemberID() );
    }

    @Override
    public CoachMember getCoachMemberById(Integer cMemberID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "select * from coach_member where cMemberID =?";
        return super.getInstance(conn, sql, cMemberID);
    }

    @Override
    public int deleteCoachMember(Integer cMemberID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "delete from coach_member where cMemberID =?";
        return super.update(conn, sql, cMemberID);
    }

    @Override
    public List<CoachMember> getAllCoachMembersList() throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "select * from coach_member";
        return super.getForList(conn, sql);
    }
}
