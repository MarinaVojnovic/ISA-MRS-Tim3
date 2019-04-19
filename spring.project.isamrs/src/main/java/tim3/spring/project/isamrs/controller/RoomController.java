package tim3.spring.project.isamrs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.dto.RoomDTO;
import tim3.spring.project.isamrs.model.Room;
import tim3.spring.project.isamrs.service.RoomService;

@RestController
public class RoomController {

	@Autowired
	RoomService roomService;

	@PostMapping(value = "/createRoom", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Room> create(@RequestBody RoomDTO roomDTO) {
		Room retVal = roomService.create(new Room(roomDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getRooms")
	public ResponseEntity<List<Room>> getRooms() {
		System.out.println("Get rooms pozvano");
		List<Room> rooms = roomService.getAll();
		return new ResponseEntity<>(rooms, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteRoom/{roomId}")
	public ResponseEntity<Room> deleteRoom(@PathVariable String roomId) {
		System.out.println("Uslo u deleting room");
		System.out.println(Long.parseLong(roomId) + 1);
		Room room = roomService.getOne(Long.parseLong(roomId));
		if (room == null) {
			System.out.println("uslo u null deo");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		roomService.delete(Long.parseLong(roomId));
		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}

	@GetMapping(value = "/findRoom/{roomId}")
	public ResponseEntity<Room> findRoom(@PathVariable long roomId) {
		System.out.println("Uslo u find room");
		Room room = roomService.getOne(roomId);
		if (room != null) {
			return new ResponseEntity<>(room, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/saveEditedRoom", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
