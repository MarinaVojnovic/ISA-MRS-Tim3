package tim3.spring.project.isamrs.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import tim3.spring.project.isamrs.dto.MessageDTO;
import tim3.spring.project.isamrs.dto.RoomDTO;
import tim3.spring.project.isamrs.model.Hotel;
import tim3.spring.project.isamrs.model.HotelAdmin;
import tim3.spring.project.isamrs.model.Room;
import tim3.spring.project.isamrs.model.RoomFastReservation;
import tim3.spring.project.isamrs.model.RoomReservation;
import tim3.spring.project.isamrs.service.HotelService;
import tim3.spring.project.isamrs.service.RoomFastReservationService;
import tim3.spring.project.isamrs.service.RoomReservationService;
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

	@Autowired
	RoomFastReservationService roomFastReservationService;

	@Autowired
	RoomReservationService roomReservationService;

	@GetMapping(value = "/findConcreteRooms/{hotelId}")
	public ResponseEntity<Set<Room>> findConcreteRooms(@PathVariable String hotelId) {
		Hotel retVal = hotelService.getOne(Long.parseLong(hotelId));
		return new ResponseEntity<>(retVal.getRooms(), HttpStatus.OK);
	}

	@GetMapping(value = "/searchRoomToReserve/{startDate}/{endDate}/{lowestPrice}/{highestPrice}/{hotelId}")
	public ResponseEntity<?> findRoomsToReserve(@PathVariable String startDate, @PathVariable String endDate,
			@PathVariable Double lowestPrice, @PathVariable Double highestPrice, @PathVariable Long hotelId)
			throws ParseException {
		List<Room> allRooms = roomService.findByHotel(hotelService.getOne(hotelId));
		List<Room> retVal = new ArrayList<>();
		Boolean correctDate = true;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date startDatee = new Date();
		Date endDatee = new Date();
		try {
			startDatee = df.parse(startDate);
			endDatee = df.parse(endDate);
		} catch (Exception e) {
			correctDate = false;
		}
		if (!correctDate) {
			return new ResponseEntity<>(new MessageDTO("Bad format of dates!", "Error"), HttpStatus.OK);
		}
		for (Room room : allRooms) {
			Boolean toContinue = false;
			List<RoomReservation> allRoomReservations = roomReservationService.findByRoomsContaining(room);
			List<RoomFastReservation> allRoomFastReservations = roomFastReservationService.findByRoom(room);
			if (lowestPrice != -1) {
				if (lowestPrice > room.getPrice()) {
					continue;
				}
			}
			if (highestPrice != -1) {
				if (highestPrice < room.getPrice()) {
					continue;
				}
			}
			for (RoomReservation roomReservation : allRoomReservations) {
				Date startDate2 = df.parse(roomReservation.getStartDate());
				Date endDate2 = df.parse(roomReservation.getEndDate());
				if (!(startDatee.getTime() < startDate2.getTime() && endDatee.getTime() < startDate2.getTime())
						&& !(startDatee.getTime() > endDate2.getTime() && endDatee.getTime() > endDate2.getTime())) {
					toContinue = true;
					break;
				}
			}
			if (toContinue) {
				continue;
			}
			for (RoomFastReservation roomFastReservation : allRoomFastReservations) {
				Date startDate2 = df.parse(roomFastReservation.getStartDate());
				Date endDate2 = df.parse(roomFastReservation.getEndDate());
				if (!(startDatee.getTime() < startDate2.getTime() && endDatee.getTime() < startDate2.getTime())
						&& !(startDatee.getTime() > endDate2.getTime() && endDatee.getTime() > endDate2.getTime())) {
					toContinue = true;
					break;
				}
			}
			if (toContinue) {
				continue;
			}
			retVal.add(room);
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllRooms", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Room>> getAllRooms() {
		List<Room> rooms = roomService.getAll();
		return new ResponseEntity<>(rooms, HttpStatus.OK);
	}

	@PostMapping(value = "/createRoom", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_HOTEL_ADMIN')")
	public ResponseEntity<MessageDTO> create(@RequestBody RoomDTO roomDTO) {
		HotelAdmin user = (HotelAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Set<Room> allRooms = user.getHotel().getRooms();
		for (Room room : allRooms) {
			if (room.getRoomNumber() == roomDTO.getRoomNumberRegister()) {
				return new ResponseEntity<>(new MessageDTO("This number for this hotel is already taken", "Error"),
						HttpStatus.OK);
			}
		}
		Room newRoom = new Room(roomDTO);
		newRoom.setHotel(user.getHotel());
		Room retVal = roomService.create(newRoom);
		user.getHotel().getRooms().add(retVal);
		return new ResponseEntity<>(new MessageDTO("Successful adding room, congratulations!", "Error"),
				HttpStatus.CREATED);
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
	public ResponseEntity<MessageDTO> deleteRoom(@PathVariable String roomId) {
		HotelAdmin ha = (HotelAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Room room = roomService.getOne(Long.parseLong(roomId));
		if (room == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<RoomFastReservation> check1 = roomFastReservationService.findByRoom(room);
		List<RoomReservation> check2 = roomReservationService.findByRoomsContaining(room);
		if (!check1.isEmpty() || !check2.isEmpty()) {
			return new ResponseEntity<>(new MessageDTO("This room is reserved so it cant be deleted", "Error"),
					HttpStatus.OK);
		}
		ha.getHotel().getRooms().remove(room);
		roomService.delete(Long.parseLong(roomId));
		return new ResponseEntity<>(new MessageDTO("Successfully deleted room!", "Error"), HttpStatus.OK);
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
	public ResponseEntity<MessageDTO> saveChangesRoom(@RequestBody RoomDTO roomDTO) {
		Room r = roomService.getOne(roomDTO.getId());
		if (r == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		HotelAdmin user = (HotelAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		Integer oldRoomNumber = r.getRoomNumber();
		Set<Room> allRooms = user.getHotel().getRooms();
		for (Room room : allRooms) {
			if (room.getRoomNumber() == roomDTO.getRoomNumberRegister() && oldRoomNumber != room.getRoomNumber()) {
				return new ResponseEntity<>(new MessageDTO("This number for this hotel is already taken", "Error"),
						HttpStatus.OK);
			}
		}

		List<RoomFastReservation> check1 = roomFastReservationService.findByRoom(r);
		List<RoomReservation> check2 = roomReservationService.findByRoomsContaining(r);
		if (!check1.isEmpty() || !check2.isEmpty()) {
			return new ResponseEntity<>(new MessageDTO("This room is reserved so it cant be edited", "Error"),
					HttpStatus.OK);
		}

		r.setId(roomDTO.getId());
		r.setRoomNumber(roomDTO.getRoomNumberRegister());
		r.setPrice(roomDTO.getRoomPriceRegister());
		r.setNumberPeople(roomDTO.getRoomPeopleNumberRegister());

		roomService.save(r);
		return new ResponseEntity<>(new MessageDTO("Room successfully edited, congratulations!", "Error"),
				HttpStatus.OK);
	}

	@GetMapping(value = "/searchRoomUnregistered/{numberPeople}/{lowestPrice}/{highestPrice}")
	public ResponseEntity<List<Room>> searchRoomUnregistered(@PathVariable Integer numberPeople,
			@PathVariable Double lowestPrice, @PathVariable Double highestPrice) {
		List<Room> allRooms = roomService.getAll();
		List<Room> retVal = new ArrayList<>();
		for (Room room : allRooms) {
			if (numberPeople != 0) {
				if (numberPeople != room.getNumberPeople()) {
					continue;
				}
			}
			if (lowestPrice != -1) {
				if (lowestPrice > room.getPrice()) {
					continue;
				}
			}
			if (highestPrice != -1) {
				if (highestPrice < room.getPrice()) {
					continue;
				}
			}
			retVal.add(room);
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
}
