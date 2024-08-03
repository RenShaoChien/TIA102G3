package com.tia102g3.sportevent.model;

import com.tia102g3.basedao.BaseDAO;
import com.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ClassName： SportEventDAOImpl
 * package：com.tia102g3.sportevent.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/18 @{TIME}
 * @Version 1.0
 */
@Repository
public class SportEventDAOImpl extends BaseDAO<SportEvent> implements SportEventDAO {
    @Autowired
    private JDBCUtils jdbcUtils;

    @Override
    public int insertSportEvent(SportEvent sportEvent) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "insert into sport_event (sportEventName, sportTypes, sportEquipment) values (?,?,?)";
        return super.update(conn, sql, sportEvent.getSportEventName(), sportEvent.getSportTypes(), sportEvent.getSportEquipment());
    }

    @Override
    public int deleteSportEventByID(Integer sportEventID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "delete from sport_event where sportEventID=?";
        return super.update(conn, sql, sportEventID);
    }

    @Override
    public int updateSportEvent(SportEvent sportEvent) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "update sport_event set sportEventName=?, sportTypes=?, sportEquipment=? where sportEventID=?";
        return super.update(conn, sql, sportEvent.getSportEventName(), sportEvent.getSportTypes(), sportEvent.getSportEquipment(), sportEvent.getSportEventID());
    }

    @Override
    public SportEvent selectSportEventByID(Integer sportEventID) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "select * from sport_event where sportEventID=?";
        return super.getInstance(conn, sql, sportEventID);
    }

    @Override
    public List<SportEvent> selectAllSportEvents() throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "select * from sport_event";
        return super.getForList(conn, sql);
    }

    @Override
    public Set<String> selectSportEquipmentsSet() throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "select sportEquipment from sport_event";
        List<SportEvent> sportEvents = super.getForList(conn, sql);
        Set<String> sportEquipmentSet = new HashSet<>();
        for (SportEvent sportEvent : sportEvents) {
            sportEquipmentSet.add(sportEvent.getSportEquipment());
        }
        return sportEquipmentSet;
    }

    @Override
    public Set<SportEvent> selectSportEventsNameFromTypeSet(String type) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "select sportEventName from sport_event where sportTypes = ?";
        Set<SportEvent> sportEventNamesSet = new HashSet<>();
        List<SportEvent> result = super.getForList(conn, sql, type);
        sportEventNamesSet.addAll(result);
        return sportEventNamesSet;
    }

    @Override
    public Set<SportEvent> selectSportEquipmentFromTypeSet(String sportType) throws Exception {
        Connection conn = jdbcUtils.getConnection();
        String sql = "select sportEquipment from sport_event where sportTypes = ?";
        Set<SportEvent> sportEquipmentSet = new HashSet<>();
        List<SportEvent> result = super.getForList(conn, sql, sportType);
        for (SportEvent sportEvent : result) {
            sportEquipmentSet.add(sportEvent);
        }
        return sportEquipmentSet;
    }
}
