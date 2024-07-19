package com.tia102g3.sportevent.service;

import com.tia102g3.sportevent.model.SportEvent;

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
}
