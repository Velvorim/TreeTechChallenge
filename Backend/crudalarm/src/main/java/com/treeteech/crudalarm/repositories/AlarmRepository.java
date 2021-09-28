package com.treeteech.crudalarm.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.treeteech.crudalarm.model.Alarm;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long>{
    Optional<Alarm> findByAlarmDescription(String alarmDescription);

    //@Query("SELECT a from Alarm as a inner join TriggeredAlarm on Alarm.id = TriggeredAlarm.alarmTriggeredId group by Alarm.id order by count(Alarm.id) Desc")
    @Query("SELECT ALARM FROM #{#entityName} ALARM inner join com.treeteech.crudalarm.model.TriggeredAlarm TRIGGERED on ALARM.id = TRIGGERED.alarmTriggeredId group by ALARM.id order by COUNT(ALARM.id) Desc ")
    List<Alarm>  mostTriggeredAlarms(Pageable pageable);

}
