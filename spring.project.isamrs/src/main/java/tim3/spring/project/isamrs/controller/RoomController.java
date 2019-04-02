package tim3.spring.project.isamrs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value="/getRooms",method = RequestMethod.GET)
	public ResponseEntity<List<Room>> getRooms() {
		System.out.println("Get rooms pozvano");
		List<Room> rooms = roomService.getAll();
		return new ResponseEntity<>(rooms, HttpStatus.OK); 
	}
	
	@RequestMapping(value="/deleteRoom/{roomId}")
	public ResponseEntity<Room> deleteRoom(@PathVariable String roomId){
		System.out.println("Uslo u deleting room");
		System.out.println(Long.parseLong(roomId)+1);
		Room room = roomService.getOne(Long.parseLong(roomId));
		if (room == null) {
			System.out.println("uslo u null deo");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		roomService.delete(Long.parseLong(roomId));
		return new ResponseEntity<>(room, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findRoom/{roomId}", method = RequestMethod.GET)
	public ResponseEntity<Room> findRoom(@PathVariable long roomId) {
		System.out.println("Uslo u find room");
		Room room = roomService.getOne(roomId);
		if (room != null) {
			return new ResponseEntity<>(room, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/saveEditedRoom", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Room> saveChangesRoom(@RequestBody Room room) {
		Room r = roomService.getOne(room.getId());
		if (r == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		r.setId(room.getId());
		r.setRoomNumber(room.getRoomNumber());
		r.setPrice(room.getPrice());
		r.setNumberPeople(room.getNumberPeople());
		

		Room editedRoom = roomService.save(r);
		return new ResponseEntity<>(editedRoom, HttpStatus.OK);
	}
}
