package com.treeteech.crudalarm.dto;

import java.util.Date;

import com.treeteech.crudalarm.model.Alarm;
import com.treeteech.crudalarm.model.Equipment;

public class AlarmDto {
    
    private Long id;
    private String alarmDescription;
    private String classification;
    private Date registrationDate;
    private Equipment associatedEquipment;

    public AlarmDto(){

    }

    public AlarmDto(Alarm alarm){
        id = alarm.getId();
        alarmDescription = alarm.getAlarmDescription();
        classification = alarm.getClassification();
        registrationDate = alarm.getRegistrationDate();
        associatedEquipment = alarm.getAssociatedEquipment();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlarmDescription() {
        return alarmDescription;
    }

    public void setAlarmDescription(String alarmDescription) {
        this.alarmDescription = alarmDescription;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Equipment getAssociatedEquipment() {
        return associatedEquipment;
    }

    public void setAssociatedEquipment(Equipment associatedEquipment) {
        this.associatedEquipment = associatedEquipment;
    }

    
}
