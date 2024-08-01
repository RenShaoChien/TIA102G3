package com.tia102g3.sportevent.model;

import java.util.List;

/**
 * ClassName： SportEventDAO
 * package：com.tia102g3.sportevent.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/18 @{TIME}
 * @Version 1.0
 */
public interface SportEventDAO {

    int insertSportEvent(SportEvent sportEvent) throws Exception;

    int deleteSportEventByID(Integer sportEventID) throws Exception;

    int updateSportEvent(SportEvent sportEvent) throws Exception;

    SportEvent selectSportEventByID(Integer sportEventID) throws Exception;

    List<SportEvent> selectAllSportEvents() throws Exception;
}
