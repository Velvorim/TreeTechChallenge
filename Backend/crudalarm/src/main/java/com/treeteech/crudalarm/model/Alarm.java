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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "alarms")
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "A descrição do alarme não pode ser nula")
    @NotEmpty(message = "A descrição do alarme não pode estar vazia")
    @Column(name = "alarm_description", nullable = false)
    private String alarmDescription;

    @NotNull(message = "A classificação do alarme não pode ser nula")
    @NotEmpty(message = "A classificação do alarme não pode estar vazia")
    @Column(name = "classification", nullable = false)
    private String classification;

    @NotNull(message = "A Data de registro não pode ser nula")
    @Column(name = "registration_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm")
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    @JsonBackReference(value = "associatedEquipment")
    private Equipment associatedEquipment;
 

    @OneToMany(mappedBy = "alarmTriggeredId", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REMOVE })
    @JsonManagedReference(value = "alarmTriggeredId")
    private List<TriggeredAlarm> triggeredAlarm = new ArrayList<TriggeredAlarm>();

    public Alarm() {

    }

    public List<TriggeredAlarm> getTriggeredAlarm() {
        return triggeredAlarm;
    }

    public void setTriggeredAlarm(List<TriggeredAlarm> triggeredAlarm) {
        this.triggeredAlarm = triggeredAlarm;
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

    public Equipment getAssociatedEquipment() {
        return associatedEquipment;
    }

    public void setAssociatedEquipment(Equipment associatedEquipment) {
        this.associatedEquipment = associatedEquipment;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

}
