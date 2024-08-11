package com.tia102g3.sportevent.service;

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

    Set<String> getSportEquipmentsSet() ;

    Set<String> getSportEventsFromTypeSet(String sportType)  ;

    Set<String> getSportEquipmentFromTypeSet(String sportType);

    Set<String> getEquipmentBySportEvent(String sportEventName);
}
