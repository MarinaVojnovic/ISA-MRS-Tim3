package tim3.spring.project.isamrs.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.dto.MessageDTO;
import tim3.spring.project.isamrs.dto.RoomFastReservationDTO;
import tim3.spring.project.isamrs.model.HotelCustomerService;
import tim3.spring.project.isamrs.model.Room;
import tim3.spring.project.isamrs.model.RoomFastReservation;
import tim3.spring.project.isamrs.service.HotelCustomerServiceService;
import tim3.spring.project.isamrs.service.RoomFastReservationService;
import tim3.spring.project.isamrs.service.RoomService;

@RestController
public class RoomFastReservationController {
	@Autowired
	RoomService roomService;

	@Autowired
	RoomFastReservationService roomFastReservationService;

	@Autowired
	HotelCustomerServiceService hotelCustomerServiceService;

	@PostMapping(value = "/createRoomFastReservation", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_HOTEL_ADMIN')")
	public ResponseEntity<MessageDTO> create(@RequestBody RoomFastReservationDTO roomFastReservationDTO)
			throws ParseException {
		Room room = roomService.getOne(roomFastReservationDTO.getRoomId());
		List<RoomFastReservation> reservations = roomFastReservationService.findByRoom(room);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = (Date) df.parse(roomFastReservationDTO.getStartDate());
		Date endDate = (Date) df.parse(roomFastReservationDTO.getEndDate());
		for (RoomFastReservation roomFastReservation : reservations) {
			if (!(startDate.getTime() < roomFastReservation.getStartDate().getTime()
					&& endDate.getTime() < roomFastReservation.getStartDate().getTime())
					&& !(startDate.getTime() > roomFastReservation.getEndDate().getTime()
							&& endDate.getTime() > roomFastReservation.getEndDate().getTime())) {
				return new ResponseEntity<>(new MessageDTO(
						"This room already have been fast reserved by this period, pick some other dates!", "Error"),
						HttpStatus.OK);
			}
		}
		RoomFastReservation retVal = new RoomFastReservation();
		retVal.setRoom(room);
		retVal.setStartDate(startDate);
		retVal.setEndDate(endDate);
		retVal.setDiscount(roomFastReservationDTO.getDiscount());
		double price = room.getPrice();
		System.out.println("Upsjelo");
		if (!roomFastReservationDTO.getHotelCustomerServices().equals("")) {
			String[] hcsIds = roomFastReservationDTO.getHotelCustomerServices().split(" ");
			for (String hcsId : hcsIds) {
				HotelCustomerService hcs = hotelCustomerServiceService.getOne(Long.parseLong(hcsId));
				retVal.getHotelCustomerServices().add(hcs);
				price += hcs.getPrice();
			}
		}
		retVal.setOriginalPrice(price);
		retVal.setNewPrice((price / 100) * (100 - roomFastReservationDTO.getDiscount()));
		roomFastReservationService.create(retVal);
		return new ResponseEntity<>(new MessageDTO("Succesfully added this room on fast reservation!", "Error"),
				HttpStatus.CREATED);
	}

	@GetMapping(value = "/getRoomFastReservations", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RoomFastReservation>> getRoomFastReservations() {

		List<RoomFastReservation> retVal = roomFastReservationService.getAll();

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteRoomFastReservation/{id}")
	@PreAuthorize("hasRole('ROLE_HOTEL_ADMIN')")
	public ResponseEntity<RoomFastReservation> deleteRoomFastReservation(@PathVariable String id) {
		RoomFastReservation r = roomFastReservationService.getOne(Long.parseLong(id));
		if (r == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		roomFastReservationService.delete(Long.parseLong(id));
		return new ResponseEntity<>(r, HttpStatus.OK);
	}

	@GetMapping(value = "/findRoomFastReservation/{id}")
	public ResponseEntity<RoomFastReservation> findRoomFastReservation(@PathVariable long id) {
		RoomFastReservation r = roomFastReservationService.getOne(id);
		if (r != null) {
			return new ResponseEntity<>(r, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
