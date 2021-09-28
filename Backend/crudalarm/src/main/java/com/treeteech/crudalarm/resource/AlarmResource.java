package com.treeteech.crudalarm.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.treeteech.crudalarm.dto.AlarmDto;
import com.treeteech.crudalarm.error.ObjectNotFoundException;
import com.treeteech.crudalarm.model.Alarm;
import com.treeteech.crudalarm.service.AlarmService;

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
@RequestMapping(value = "/alarms")
@CrossOrigin
public class AlarmResource {
    
    @Autowired
	private AlarmService alarmService;

    @GetMapping(value = "/{descricao}")
	public ResponseEntity<Alarm> list(@Valid @PathVariable("descricao") String descricao) {

		Alarm alarms = alarmService.ListAlarms(descricao);
		return ResponseEntity.ok().body(alarms);
		
	}

  @GetMapping
  public ResponseEntity<List<AlarmDto>> listAll() {

    List<Alarm> listAlarms = alarmService.findAll();
    List<AlarmDto>  listDto = listAlarms.stream().map(alarm -> new AlarmDto(alarm)).collect(Collectors.toList());
    return ResponseEntity.ok().body(listDto);

  }

  @RequestMapping(value="/salvar", method = RequestMethod.POST)
  @ResponseBody
  //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Alarm> addAlarms(@Valid @RequestBody Alarm alarms) throws Exception {

    try {

		  Alarm alarm = alarmService.insertAlarms(alarms);

      return new ResponseEntity<>(alarm, HttpStatus.CREATED);

    } catch (Exception e) {

      throw new ObjectNotFoundException(e.getMessage());

    }

  }


  @RequestMapping(value="/{id}", method = RequestMethod.PUT)
  @ResponseBody
  //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Alarm> updateAlarms(@Valid @RequestBody Alarm alarms, 
  @Valid @PathVariable("id") Long id){

    try {

      alarms.setId(id);
		  Alarm alarm = alarmService.updateAlarms(alarms);

      return new ResponseEntity<>(alarm, HttpStatus.CREATED);

    } catch (Exception e) {

      throw new ObjectNotFoundException(e.getMessage());

    }
  }

  @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity<Void> delete(@Valid @PathVariable("id") Long id) {

    alarmService.delete(id);
    return ResponseEntity.noContent().build();
  }
  


  @RequestMapping(value="/mostTriggeredAlarms", method=RequestMethod.GET)
   public ResponseEntity<List<Alarm>> mostTriggeredAlarms(){
    return  ResponseEntity.ok(alarmService.mostTriggeredAlarms());
 }
}
