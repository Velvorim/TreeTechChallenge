package com.treeteech.crudalarm.model;


import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "triggered_alarms")
public class TriggeredAlarm { 

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;

    @NotNull(message = "A Data de entrada do disparo do alarme não pode ser nula")
	@Column(name = "entry_date", nullable = false)
    private Date entryDate;

    @NotNull(message = "A Data de saida do disparo do alarme não pode ser nula")
	@Column(name = "exit_date", nullable = false)
    private Date exitDate;

	@Column(name = "alarm_status")
    private Boolean alarmStatus;

    @ManyToOne
    @JoinColumn(name = "alarm_id") 
    @JsonBackReference(value="alarmTriggeredId")
    private Alarm alarmTriggeredId;

    @ManyToOne          
    @JoinColumn(name = "equipment_id") 
    @JsonBackReference(value="equipmentTriggered")
    private Equipment equipmentTriggered;

    public TriggeredAlarm(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(java.util.Date entryDate) {
        this.entryDate = entryDate;
    }

    public java.util.Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(java.util.Date exitDate) {
        this.exitDate = exitDate;
    }

    public boolean getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(boolean alarmStatus) {
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
