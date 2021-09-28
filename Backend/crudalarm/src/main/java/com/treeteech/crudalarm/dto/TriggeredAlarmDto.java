package com.treeteech.crudalarm.dto;

import java.util.Date;

import com.treeteech.crudalarm.model.Alarm;
import com.treeteech.crudalarm.model.Equipment;
import com.treeteech.crudalarm.model.TriggeredAlarm;

public class TriggeredAlarmDto {

    private Long id;
    private Date entryDate;
    private Date exitDate;
    private Boolean alarmStatus;
    private Alarm alarmTriggeredId;
    private Equipment equipmentTriggered;

    public TriggeredAlarmDto(){

    }

    public TriggeredAlarmDto(TriggeredAlarm triggeredAlarm){
        id = triggeredAlarm.getId();
        entryDate = triggeredAlarm.getEntryDate();
        exitDate = triggeredAlarm.getExitDate();
        alarmStatus = triggeredAlarm.getAlarmStatus();
        alarmTriggeredId = triggeredAlarm.getAlarmTriggeredId();
        equipmentTriggered = triggeredAlarm.getEquipmentTriggered();
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    public Boolean getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(Boolean alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public Alarm getAlarmTriggeredId() {
        return alarmTriggeredId;
    }

    public void setAlarmTriggeredId(Alarm alarmTriggeredId) {
        this.alarmTriggeredId = alarmTriggeredId;
    }

    public Equipment getEquipmentTriggered() {
        return equipmentTriggered;
    }

    public void setEquipmentTriggered(Equipment equipmentTriggered) {
        this.equipmentTriggered = equipmentTriggered;
    }

    
}
