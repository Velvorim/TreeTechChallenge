package com.treeteech.crudalarm.repositories;




import com.treeteech.crudalarm.model.TriggeredAlarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriggeredAlarmRepository extends JpaRepository<TriggeredAlarm, Long>{
                                                                              

}
