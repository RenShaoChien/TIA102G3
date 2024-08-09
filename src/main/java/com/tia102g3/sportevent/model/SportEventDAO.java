package com.tia102g3.sportevent.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * ClassName： SportEventDAO
 * package：com.tia102g3.sportevent.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/18 @{TIME}
 * @Version 1.0
 */
public interface SportEventDAO extends JpaRepository<SportEvent, Integer> {

    @Transactional
    @Modifying
    @Query(value = "delete from sport_event where sportEventID=?1", nativeQuery = true)
    void deleteSportEventByID(Integer sportEventID);

    @Transactional(readOnly = true)
    @Query(value = "select sportEquipment from sport_event", nativeQuery = true)
    Set<String> getSportEquipmentsSet();

    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT se.sportEventName FROM SportEvent se WHERE se.sportTypes = :sportTypes")
    Set<String> getSetSportEventNamesBySportTypes(@Param("sportTypes") String sportTypes);

    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT se.sportEquipment FROM SportEvent se WHERE se.sportTypes = :sportTypes")
    Set<String> getSetSportEquipmentBySportTypes(@Param("sportTypes") String sportTypes);


    @Transactional(readOnly = true)
    @Query(value = "select DISTINCT sportEquipment from sport_event", nativeQuery = true)
    Set<String> selectSportEquipmentsSet();
}
