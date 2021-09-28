package com.treeteech.crudalarm.service;

import java.util.List;
import java.util.Optional;

import com.treeteech.crudalarm.model.Equipment;
import com.treeteech.crudalarm.repositories.EquipmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipRepo;

    public Equipment ListEquips(Long Id) {
        Optional<Equipment> equipment = equipRepo.findById(Id);
        return equipment.orElseThrow(() -> new com.treeteech.crudalarm.error.ObjectNotFoundException(
                "Equipamento não encontrado ! Id: " + Id + ", Tipo " + Equipment.class.getName()));
    }

    public List<Equipment> findAll() {

        return equipRepo.findAll();
    }

    public Equipment insert(Equipment equipment) throws Exception {

        try {
            return equipRepo.save(equipment);
        } catch (Exception e) {
            throw new Exception("Equipamento já cadastrado");
        }

    }

    public Equipment update(Equipment equipment) throws Exception {
        try {
            ListEquips(equipment.getId());
            return equipRepo.save(equipment);
        } catch (Exception e) {
            throw new Exception("Problema ao alterar o equipamento");
        }
    }

    public void delete(Long id) {
        ListEquips(id);
        equipRepo.deleteById(id);
    }

  
}
