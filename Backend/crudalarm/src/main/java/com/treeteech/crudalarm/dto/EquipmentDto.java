package com.treeteech.crudalarm.dto;

import java.util.Date;

import com.treeteech.crudalarm.model.Equipment;

public class EquipmentDto {

    private Long id;
    private int serialNumber;
    private String equipName;
    private String type;
    private Date registrationDate;

    public EquipmentDto(){
        
    }

    public EquipmentDto(Equipment equipment){
        id = equipment.getId();
        serialNumber = equipment.getSerialNumber();
        equipName = equipment.getEquipName();
        type = equipment.getType();
        registrationDate = equipment.getRegistrationDate();
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

}
