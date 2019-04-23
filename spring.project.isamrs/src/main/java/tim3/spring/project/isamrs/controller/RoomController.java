package tim3.spring.project.isamrs.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.dto.RoomDTO;
import tim3.spring.project.isamrs.model.Hotel;
import tim3.spring.project.isamrs.model.HotelAdmin;
import tim3.spring.project.isamrs.model.Room;
import tim3.spring.project.isamrs.service.HotelService;
import tim3.spring.project.isamrs.service.RoomService;
import tim3.spring.project.isamrs.service.impl.CustomUserDetailsService;

@RestController
public class RoomController {

	@Autowired
	RoomService roomService;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	HotelService hotelService;
	
	@GetMapping(value = "/findConcreteRooms/{hotelId}")
	public ResponseEntity<Set<Room>> findConcreteRooms(@PathVariable String hotelId) {
		Hotel retVal = hotelService.getOne(Long.parseLong(hotelId));
		return new ResponseEntity<>(retVal.getRooms(), HttpStatus.OK);
	}

	@GetMapping(value = "/getAllRooms", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Room>> getAllRooms() {
		List<Room> rooms = roomService.getAll();
		return new ResponseEntity<>(rooms, HttpStatus.OK);
	}

	@PostMapping(value = "/createRoom", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_HOTEL_ADMIN')")
	public ResponseEntity<Room> create(@RequestBody RoomDTO roomDTO) {
		HotelAdmin user = (HotelAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Room newRoom = new Room(roomDTO);
		newRoom.setHotel(user.getHotel());
		Room retVal = roomService.create(newRoom);
		user.getHotel().getRooms().add(retVal);
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getRooms")
	public ResponseEntity<List<Room>> getRooms() {
		HotelAdmin ha = (HotelAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Room> rooms = roomService.findByHotel(ha.getHotel());
		return new ResponseEntity<>(rooms, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteRoom/{roomId}")
	@PreAuthorize("hasRole('ROLE_HOTEL_ADMIN')")
	public ResponseEntity<Room> deleteRoom(@PathVariable String roomId) {
		HotelAdmin ha = (HotelAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Room room = roomService.getOne(Long.parseLong(roomId));
		if (room == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		ha.getHotel().getRooms().remove(room);
		roomService.delete(Long.parseLong(roomId));
		return new ResponseEntity<>(room, HttpStatus.OK);
	}

	@GetMapping(value = "/findRoom/{roomId}")
	public ResponseEntity<Room> findRoom(@PathVariable long roomId) {
		Room room = roomService.getOne(roomId);
		if (room != null) {
			return new ResponseEntity<>(room, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/saveEditedRoom", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_HOTEL_ADMIN')")
	public ResponseEntity<Room> saveChangesRoom(@RequestBody RoomDTO room) {
		Room r = roomService.getOne(room.getId());
		if (r == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		r.setId(room.getId());
		r.setRoomNumber(room.getRoomNumberRegister());
		r.setPrice(room.getRoomPriceRegister());
		r.setNumberPeople(room.getRoomPeopleNumberRegister());

		Room editedRoom = roomService.save(r);
		return new ResponseEntity<>(editedRoom, HttpStatus.OK);
	}
}
