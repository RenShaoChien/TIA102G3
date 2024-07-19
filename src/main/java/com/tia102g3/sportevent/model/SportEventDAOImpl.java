package com.tia102g3.sportevent.model;

import com.tia102g3.basedao.BaseDAO;
import com.utils.HibernateUtil;
import com.utils.JDBCUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;

/**
 * ClassName： SportEventDAOImpl
 * package：com.tia102g3.sportevent.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/18 @{TIME}
 * @Version 1.0
 */
public class SportEventDAOImpl extends BaseDAO<SportEvent> implements SportEventDAO {
    private SessionFactory sessionFactory;

    public SportEventDAOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int insertSportEvent(SportEvent sportEvent) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into sport_event (sportEventName, sportTypes, sportEquipment) values (?,?,?)";
        return super.update(conn, sql, sportEvent.getSportEventName(), sportEvent.getSportTypes(), sportEvent.getSportEquipment());
    }

    @Override
    public int deleteSportEventByID(Integer sportEventID) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "delete from sport_event where sportEventID=?";
        return super.update(conn, sql, sportEventID);
    }

    @Override
    public int updateSportEvent(SportEvent sportEvent) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "update sport_event set sportEventName=?, sportTypes=?, sportEquipment=? where sportEventID=?";
        return super.update(conn, sql, sportEvent.getSportEventName(), sportEvent.getSportTypes(), sportEvent.getSportEquipment(), sportEvent.getSportEventID());
    }

    @Override
    public SportEvent selectSportEventByID(Integer sportEventID) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "select * from sport_event where sportEventID=?";
        return super.getInstance(conn, sql, sportEventID);
    }
}
