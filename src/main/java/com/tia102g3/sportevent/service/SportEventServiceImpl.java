package com.tia102g3.sportevent.service;

import com.tia102g3.sportevent.model.SportEventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public Set<String> getSportEquipmentsSet() {
        return sportEventDAO.selectSportEquipmentsSet();
    }

    public Set<String> getSportEventsFromTypeSet(String sprotType) {
        return sportEventDAO.getSetSportEventNamesBySportTypes(sprotType);
    }

    public Set<String> getSportEquipmentFromTypeSet(String sportType)  {
        return sportEventDAO.getSetSportEquipmentBySportTypes(sportType);
    }

    @Override
    public Set<String> getEquipmentBySportEvent(String sportEventName) {
        return sportEventDAO.getSetSportEquipmentBySportEventName(sportEventName);
    }
}
