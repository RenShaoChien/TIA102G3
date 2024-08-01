package com.tia102g3.member.model;

import com.tia102g3.basedao.BaseDAO;
import com.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

/**
 * ClassName： MemberDAOImpl
 * package：com.tia102g3.member.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/28 上午12:26
 * @Version 1.0
 */
@Repository
public class MemberDAOImpl extends BaseDAO<Member> implements MemberDAO {
    @Autowired
    private JDBCUtils jdbcUtils;
    @Override
    public int insertMember(Member member) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "insert into member (personalPhotos, name, account, password, email, gender, phone, address, bD, regDate, receiver, receiverAddress, receiverPhone, cardName, cardValidTime, cardLast3No, cardPhone) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return update(conn, sql, member.getPersonalPhotos(), member.getName(), member.getAccount(), member.getPassword(), member.getEmail(), member.getGender(), member.getPhone(), member.getAddress(), member.getBD(), member.getRegDate(), member.getReceiver(), member.getReceiverAddress(), member.getReceiverPhone(), member.getCardName(), member.getCardValidTime(), member.getCardLast3No(), member.getCardPhone());
    }

    @Override
    public int updateMember(Member member) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "update member set personalPhotos=?, name=?, account=?, password=?, email=?, gender=?, phone=?, address=?, bD=?, cMember=?, receiver=?, receiverAddress=?, receiverPhone=?, cardName=?, cardValidTime=?, cardLast3No=?, cardPhone=? where memberID=?";
        return update(conn, sql, member.getPersonalPhotos(), member.getName(), member.getAccount(), member.getPassword(), member.getEmail(), member.getGender(), member.getPhone(), member.getAddress(), member.getBD(), member.getCoachMember().getCMemberID(), member.getReceiver(), member.getReceiverAddress(), member.getReceiverPhone(), member.getCardName(), member.getCardValidTime(), member.getCardLast3No(), member.getCardPhone(), member.getMemberID());
    }

    @Override
    public int deleteMemberByID(Integer memberID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "delete from member where memberID=?";
        return update(conn, sql, memberID);
    }

    @Override
    public Member getMemberByID(Integer memberID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "select * from member where memberID=?";
        return super.getInstance(conn, sql, memberID);
    }

    @Override
    public List<Member> getMembersList() throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "select * from member";
        return super.getForList(conn, sql);
    }
}
