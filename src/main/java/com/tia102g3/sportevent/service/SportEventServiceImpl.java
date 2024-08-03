package com.tia102g3.sportevent.service;

import com.tia102g3.sportevent.model.SportEvent;
import com.tia102g3.sportevent.model.SportEventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * ClassName： SportEventServiceImpl
 * package：com.tia102g3.sportevent.service
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/18 @{TIME}
 * @Version 1.0
 */
@Service
public class SportEventServiceImpl implements SportEventService {

    @Autowired
    private SportEventDAO sportEventDAO;

    @Override
    public int insertSportEvent(SportEvent sportEvent) throws Exception {
        return sportEventDAO.insertSportEvent(sportEvent);
    }

    @Override
    public int updateSportEvent(SportEvent sportEvent) throws Exception {
        return sportEventDAO.updateSportEvent(sportEvent);
    }

    @Override
    public int deleteSportEvent(Integer sportEventID) throws Exception {
        return sportEventDAO.deleteSportEventByID(sportEventID);
    }

    @Override
    public SportEvent getSportEventByID(Integer sportEventID) throws Exception {
        return sportEventDAO.selectSportEventByID(sportEventID);
    }

    @Override
    public Set<String> getSportEquipmentsSet() throws Exception {
        return sportEventDAO.selectSportEquipmentsSet();
    }

    @Override
    public List<SportEvent> getSportEventsList() throws Exception {
        return sportEventDAO.selectAllSportEvents();
    }

    @Override
    public Set<SportEvent> getSportEventsFromTypeSet(String sprotType) throws Exception {
        return sportEventDAO.selectSportEventsNameFromTypeSet(sprotType);
    }

    @Override
    public Set<SportEvent> getSportEquipmentFromTypeSet(String sportType) throws Exception {
        return sportEventDAO.selectSportEquipmentFromTypeSet(sportType);
    }
}
