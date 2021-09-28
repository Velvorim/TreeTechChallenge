package com.treeteech.crudalarm.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.treeteech.crudalarm.dto.TriggeredAlarmDto;
import com.treeteech.crudalarm.error.ObjectNotFoundException;
import com.treeteech.crudalarm.model.TriggeredAlarm;
import com.treeteech.crudalarm.service.TriggeredAlarmService;

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
@RequestMapping(value = "/triggered-alarms")
@CrossOrigin
public class TriggeredAlarmResource {

  @Autowired
  private TriggeredAlarmService triggeredAlarmService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<TriggeredAlarm> list(@Valid @PathVariable("id") Long id) {

    TriggeredAlarm triggerdAlarms = triggeredAlarmService.ListTriggerdAlarm(id);
    return ResponseEntity.ok().body(triggerdAlarms);

  }

  @GetMapping
  public ResponseEntity<List<TriggeredAlarmDto>> listAll() {

    List<TriggeredAlarm> triggeredAlarm = triggeredAlarmService.searchAll();
    List<TriggeredAlarmDto>  listDto = triggeredAlarm.stream().map(triggeredAlarms -> new TriggeredAlarmDto(triggeredAlarms)).collect(Collectors.toList());
    return ResponseEntity.ok().body(listDto);

  }

  @RequestMapping(value = "/salvar", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<TriggeredAlarm> addTriggeredAlarms(@Valid @RequestBody TriggeredAlarm triggeredAlarms)
      throws Exception {

    try {

      TriggeredAlarm triggeredAlarm = triggeredAlarmService.insertTriggerdAlarm(triggeredAlarms);

      return new ResponseEntity<>(triggeredAlarm, HttpStatus.CREATED);

    } catch (Exception e) {

      throw new ObjectNotFoundException(e.getMessage());

    }

  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<TriggeredAlarm> updateTriggeredAlarms(@Valid @RequestBody TriggeredAlarm triggeredAlarms,
      @Valid @PathVariable("id") Long id) {

    try {

      triggeredAlarms.setId(id);
      TriggeredAlarm triggeredAlarm = triggeredAlarmService.updateTriggerdAlarm(triggeredAlarms);

      return new ResponseEntity<>(triggeredAlarm, HttpStatus.CREATED);

    } catch (Exception e) {

      throw new ObjectNotFoundException(e.getMessage());

    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity<Void> delete(@Valid @PathVariable("id") Long id) {

    triggeredAlarmService.delete(id);
    return ResponseEntity.noContent().build();
  }

  // @RequestMapping(value="/page", method=RequestMethod.GET)
	// public ResponseEntity<Page<TriggeredAlarmDto>> findPage(

	// 		@RequestParam(value="page", defaultValue="0") Integer page, 
	// 		@RequestParam(value="linesPerPage", defaultValue="3") Integer linesPerPage, 
	// 		@RequestParam(value="orderBy", defaultValue="id") String orderBy, 
	// 		@RequestParam(value="direction", defaultValue="DESC") String direction) {
	// 	Page<TriggeredAlarm> list = triggeredAlarmService.search(page, linesPerPage, orderBy, direction);
	// 	Page<TriggeredAlarmDto> listDto = list.map(obj -> new TriggeredAlarmDto(obj));  
	// 	return ResponseEntity.ok().body(listDto);
	// }

}
