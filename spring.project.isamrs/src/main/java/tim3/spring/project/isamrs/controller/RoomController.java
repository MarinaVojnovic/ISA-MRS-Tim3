package tim3.spring.project.isamrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.dto.RoomDTO;
import tim3.spring.project.isamrs.model.Room;
import tim3.spring.project.isamrs.service.RoomService;

@RestController
public class RoomController {

	@Autowired
	RoomService roomService;
	
	@RequestMapping(value = "/createRoom", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Room> create(@RequestBody RoomDTO roomDTO) {
		Room retVal = roomService.create(new Room(roomDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}
}
