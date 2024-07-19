package com.tia102g3.sportevent.service;

import com.tia102g3.sportevent.model.SportEvent;
import com.tia102g3.sportevent.model.SportEventDAO;

/**
 * ClassName： SportEventServiceImpl
 * package：com.tia102g3.sportevent.service
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/18 @{TIME}
 * @Version 1.0
 */
public class SportEventServiceImpl implements SportEventService {

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
}
