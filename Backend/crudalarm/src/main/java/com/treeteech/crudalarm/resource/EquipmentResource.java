package com.treeteech.crudalarm.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.treeteech.crudalarm.dto.EquipmentDto;
import com.treeteech.crudalarm.error.ObjectNotFoundException;
import com.treeteech.crudalarm.model.Equipment;
import com.treeteech.crudalarm.service.EquipmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/equipments")
@CrossOrigin
public class EquipmentResource {

  @Autowired
  private EquipmentService equipService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Equipment> list(@Valid @PathVariable("id") Long id) {

    Equipment equipments = equipService.ListEquips(id);
    return ResponseEntity.ok().body(equipments);

  }

  
  @GetMapping
  public ResponseEntity<List<EquipmentDto>> listAll() {

    List<Equipment> equipment = equipService.findAll();
    List<EquipmentDto>  listDto = equipment.stream().map(equipments -> new EquipmentDto(equipments)).collect(Collectors.toList());
    return ResponseEntity.ok().body(listDto);

  }

  @RequestMapping(value = "/salvar", method = RequestMethod.POST)
  @ResponseBody
  // @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Equipment> addEquipments(@Valid @RequestBody Equipment equips) throws Exception {

    try {

      Equipment equip = equipService.insert(equips);

      return new ResponseEntity<>(equip, HttpStatus.CREATED);

    } catch (Exception e) {

      throw new ObjectNotFoundException(e.getMessage());

    }

  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<Equipment> updateEquip(@Valid @RequestBody Equipment equips,
      @Valid @PathVariable("id") Long id) {

    try {

      equips.setId(id);
      Equipment equip = equipService.update(equips);

      return new ResponseEntity<>(equip, HttpStatus.CREATED);

    } catch (Exception e) {

      throw new ObjectNotFoundException(e.getMessage());

    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity<Void> delete(@Valid @PathVariable("id") Long id) {

    equipService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
