package com.tia102g3.sportevent.service;

import com.tia102g3.sportevent.model.SportEvent;

import java.util.List;
import java.util.Set;

/**
 * ClassName： SportEventService
 * package：com.tia102g3.sportevent.service
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/18 @{TIME}
 * @Version 1.0
 */
public interface SportEventService {
    int insertSportEvent(SportEvent sportEvent) throws Exception;
    int updateSportEvent(SportEvent sportEvent) throws Exception;
    int deleteSportEvent(Integer sportEventID) throws Exception;
    SportEvent getSportEventByID(Integer sportEventID) throws Exception;

    Set<String> getSportEquipmentsSet() throws Exception;

    List<SportEvent> getSportEventsList() throws Exception;

    Set<SportEvent> getSportEventsFromTypeSet(String sportType) throws Exception ;

    Set<SportEvent> getSportEquipmentFromTypeSet(String sportType) throws Exception;
}
