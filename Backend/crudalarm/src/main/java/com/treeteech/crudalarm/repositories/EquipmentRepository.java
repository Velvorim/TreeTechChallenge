package com.treeteech.crudalarm.repositories;



import java.util.Optional;

import com.treeteech.crudalarm.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    
    Optional<Equipment> findById(Long Id);
}
