package com.treeteech.crudalarm.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.treeteech.crudalarm.model.Equipment;
import com.treeteech.crudalarm.model.Log;
import com.treeteech.crudalarm.repositories.EquipmentRepository;
import com.treeteech.crudalarm.repositories.LogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipRepo;

    @Autowired
    private LogRepository logRepo;

    public Equipment ListEquips(Long Id) {
        Optional<Equipment> equipment = equipRepo.findById(Id);
        Log insertAlarmLog = new Log();
        Date datenow = new Date();
        insertAlarmLog.setOperation("get equipments");
        insertAlarmLog.setDate(datenow);

        logRepo.save(insertAlarmLog);
        return equipment.orElseThrow(() -> new com.treeteech.crudalarm.error.ObjectNotFoundException(
                "Equipamento não encontrado ! Id: " + Id + ", Tipo " + Equipment.class.getName()));
    }

    public List<Equipment> findAll() {

        Log insertAlarmLog = new Log();
        Date datenow = new Date();
        insertAlarmLog.setOperation("list all equipments");
        insertAlarmLog.setDate(datenow);

        logRepo.save(insertAlarmLog);
        return equipRepo.findAll();
    }

    public Equipment insert(Equipment equipment) throws Exception {

        try {
            Log insertAlarmLog = new Log();
            Date datenow = new Date();
            insertAlarmLog.setOperation("insert equipment");
            insertAlarmLog.setDate(datenow);

            logRepo.save(insertAlarmLog);
            return equipRepo.save(equipment);
        } catch (Exception e) {
            throw new Exception("Equipamento já cadastrado");
        }

    }

    public Equipment update(Equipment equipment) throws Exception {
        try {
            ListEquips(equipment.getId());
            Log insertAlarmLog = new Log();
            Date datenow = new Date();
            insertAlarmLog.setOperation("update equipment");
            insertAlarmLog.setDate(datenow);

            logRepo.save(insertAlarmLog);
            return equipRepo.save(equipment);
        } catch (Exception e) {
            throw new Exception("Problema ao alterar o equipamento");
        }
    }

    public void delete(Long id) {
        ListEquips(id);
        Log insertAlarmLog = new Log();
        Date datenow = new Date();
        insertAlarmLog.setOperation("delete equipment");
        insertAlarmLog.setDate(datenow);

        logRepo.save(insertAlarmLog);
        equipRepo.deleteById(id);
    }

}
