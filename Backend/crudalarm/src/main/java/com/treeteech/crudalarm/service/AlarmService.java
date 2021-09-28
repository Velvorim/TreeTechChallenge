package com.treeteech.crudalarm.service;

import java.util.List;
import java.util.Optional;

import com.treeteech.crudalarm.model.Alarm;
import com.treeteech.crudalarm.model.Equipment;
import com.treeteech.crudalarm.repositories.AlarmRepository;
import com.treeteech.crudalarm.repositories.EquipmentRepository;
import com.treeteech.crudalarm.error.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AlarmService {

    @Autowired
    private AlarmRepository alarmRepo;

    @Autowired
    private EquipmentRepository equipRepo;

    @Autowired
	private EmailService emailService;

    public Alarm ListAlarms(String alarmDescription) {
        Optional<Alarm> alarm = alarmRepo.findByAlarmDescription(alarmDescription);
        return alarm.orElseThrow(() -> new ObjectNotFoundException(
                "Alarme não encontrado ! Descrição: " + alarmDescription + ", Tipo " + Alarm.class.getName()));
    }

    public List<Alarm> findAll() {

        return alarmRepo.findAll();
    }

    //public Page<Alarm> findPage(Long id,Integer page, Integer linesPerPage, String orderBy, String direction){

      //  PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        //return alarmRepo.findAll(pageRequest);
    
    //}

    public List<Alarm> mostTriggeredAlarms(){
        List<Alarm> list = alarmRepo.mostTriggeredAlarms(PageRequest.of(0,3));
        return list;
    } 


    public Alarm ListAlarmsId(Long id) {
        Optional<Alarm> alarm = alarmRepo.findById(id);
        return alarm.orElseThrow(() -> new ObjectNotFoundException(
                "Alarme não encontrado ! id: " + id + ", Tipo " + Alarm.class.getName()));
    }

    public Alarm insertAlarms(Alarm alarm) throws Exception {
        Optional<Equipment> existingEquip = equipRepo.findById(alarm.getAssociatedEquipment().getId());

        if (existingEquip.isPresent()) {
            Alarm newAlarm = new Alarm();

            newAlarm.setAlarmDescription(alarm.getAlarmDescription());
            newAlarm.setClassification(alarm.getClassification());
            newAlarm.setRegistrationDate(alarm.getRegistrationDate());
            newAlarm.setAssociatedEquipment(existingEquip.get());

         
            if(alarm.getClassification().toLowerCase().equals("alto")){
                emailService.sendEmail();
            }

            alarmRepo.save(newAlarm);

            return newAlarm;

        } else {
            throw new Exception("O id do equipamento não foi encontrado!");
        }
    }

    public Alarm updateAlarms(Alarm alarm) throws Exception {
        Optional<Equipment> existingEquip = equipRepo.findById(alarm.getAssociatedEquipment().getId());

        if (existingEquip.isPresent()) {
            Alarm newAlarm = ListAlarmsId(alarm.getId());

            newAlarm.setAlarmDescription(alarm.getAlarmDescription());
            newAlarm.setClassification(alarm.getClassification());
            newAlarm.setRegistrationDate(alarm.getRegistrationDate());
            newAlarm.setAssociatedEquipment(existingEquip.get());

            if(alarm.getClassification().toLowerCase().equals("alto")){
                emailService.sendEmail();
            }

            alarmRepo.save(newAlarm);

            return newAlarm;

        } else {
            throw new Exception("O alarme não foi cadastrado!");
        }
    }

    public void delete(Long id) {
        ListAlarmsId(id);
        alarmRepo.deleteById(id);
    }

}
