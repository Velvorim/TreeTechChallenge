package com.treeteech.crudalarm.service;

import java.util.List;
import java.util.Optional;

import com.treeteech.crudalarm.error.ObjectNotFoundException;
import com.treeteech.crudalarm.model.Alarm;
import com.treeteech.crudalarm.model.Equipment;
import com.treeteech.crudalarm.model.TriggeredAlarm;
import com.treeteech.crudalarm.repositories.AlarmRepository;
import com.treeteech.crudalarm.repositories.EquipmentRepository;
import com.treeteech.crudalarm.repositories.TriggeredAlarmRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TriggeredAlarmService {

    @Autowired
    private TriggeredAlarmRepository triggerRepo;

    @Autowired
    private AlarmRepository alarmRepo;

    @Autowired
    private EquipmentRepository equipRepo;

    public TriggeredAlarm ListTriggerdAlarm(Long Id) {
        Optional<TriggeredAlarm> triggeredAlarm = triggerRepo.findById(Id);
        return triggeredAlarm.orElseThrow(() -> new ObjectNotFoundException(
                "Alarme Disparado não encontrado ! Id: " + Id + ", Tipo " + Alarm.class.getName()));
    }

    // public Page<TriggeredAlarm> search(Integer page, Integer linesPerPage, String orderBy, String direction){

    //     PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
    //     return triggerRepo.findAll(pageRequest);
        
    // }

    public List<TriggeredAlarm> searchAll() {

        return triggerRepo.findAll();
    }

    public TriggeredAlarm insertTriggerdAlarm(TriggeredAlarm triggeredAlarm) throws Exception {
        Optional<Equipment> existingEquip = equipRepo.findById(triggeredAlarm.getEquipmentTriggered().getId());
        Optional<Alarm> existingAlarm = alarmRepo.findById(triggeredAlarm.getAlarmTriggeredId().getId());

        if (existingAlarm.isPresent() && existingEquip.isPresent()) {
            TriggeredAlarm newTriggerAlarm = new TriggeredAlarm();

            newTriggerAlarm.setEntryDate(triggeredAlarm.getEntryDate());
            newTriggerAlarm.setExitDate(triggeredAlarm.getExitDate());
            newTriggerAlarm.setAlarmStatus(triggeredAlarm.getAlarmStatus());
            newTriggerAlarm.setEquipmentTriggered(existingEquip.get());
            newTriggerAlarm.setAlarmTriggeredId(existingAlarm.get());

            triggerRepo.save(newTriggerAlarm);

            return newTriggerAlarm;

        } else {
            throw new Exception("O Alarme disparado não foi cadastrado!");
        }
    }

    public TriggeredAlarm updateTriggerdAlarm(TriggeredAlarm triggeredAlarm) throws Exception {
        Optional<Equipment> existingEquip = equipRepo.findById(triggeredAlarm.getEquipmentTriggered().getId());
        Optional<Alarm> existingAlarm = alarmRepo.findById(triggeredAlarm.getAlarmTriggeredId().getId());

        if (existingAlarm.isPresent() && existingEquip.isPresent()) {
            TriggeredAlarm newTriggerAlarm = ListTriggerdAlarm(triggeredAlarm.getId());

            newTriggerAlarm.setEntryDate(triggeredAlarm.getEntryDate());
            newTriggerAlarm.setExitDate(triggeredAlarm.getExitDate());
            newTriggerAlarm.setAlarmStatus(triggeredAlarm.getAlarmStatus());
            newTriggerAlarm.setEquipmentTriggered(existingEquip.get());
            newTriggerAlarm.setAlarmTriggeredId(existingAlarm.get());

            triggerRepo.save(newTriggerAlarm);

            return newTriggerAlarm;

        } else {
            throw new Exception("O Alarme disparado não foi cadastrado!");
        }
    }

    public void delete(Long id) {
        ListTriggerdAlarm(id);
        triggerRepo.deleteById(id);
    }

}
