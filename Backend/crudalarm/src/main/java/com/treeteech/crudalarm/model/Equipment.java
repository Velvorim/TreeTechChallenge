package com.treeteech.crudalarm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "equipments")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "O Número de série não pode ser nulo")
    @Column(name = "serial_number", nullable = false)
    private int serialNumber;

    @NotNull(message = "O Número de série não pode ser nulo")
    @NotEmpty(message = "O Número de série não pode estar vazio")
    @Column(name = "equipment_name", nullable = false)
    private String equipName;

    @NotNull(message = "O tipo de equipamento não pode ser nulo")
    @NotEmpty(message = "O tipo de equipamento não pode estar vazio")
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull(message = "A Data de registro do equipamento não pode ser nula")
    @Column(name = "registration_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm")
    private Date registrationDate;

    @OneToMany(mappedBy = "associatedEquipment", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REMOVE })
    @JsonManagedReference(value = "associatedEquipment")
    private List<Alarm> alarms = new ArrayList<Alarm>();

    @OneToMany(mappedBy = "equipmentTriggered", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REMOVE })
    @JsonManagedReference(value = "equipmentTriggered")
    private List<TriggeredAlarm> triggeredAlarm = new ArrayList<TriggeredAlarm>();

    public Equipment() {

    }

    public List<Alarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        id = Id;
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
