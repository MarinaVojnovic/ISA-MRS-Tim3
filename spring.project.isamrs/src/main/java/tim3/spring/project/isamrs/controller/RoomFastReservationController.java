package tim3.spring.project.isamrs.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.dto.MessageDTO;
import tim3.spring.project.isamrs.dto.RoomFastReservationDTO;
import tim3.spring.project.isamrs.model.Hotel;
import tim3.spring.project.isamrs.model.HotelAdmin;
import tim3.spring.project.isamrs.model.HotelCustomerService;
import tim3.spring.project.isamrs.model.Room;
import tim3.spring.project.isamrs.model.RoomFastReservation;
import tim3.spring.project.isamrs.model.RoomReservation;
import tim3.spring.project.isamrs.service.HotelCustomerServiceService;
import tim3.spring.project.isamrs.service.HotelService;
import tim3.spring.project.isamrs.service.RoomFastReservationService;
import tim3.spring.project.isamrs.service.RoomReservationService;
import tim3.spring.project.isamrs.service.RoomService;
import tim3.spring.project.isamrs.service.impl.CustomUserDetailsService;

@RestController
public class RoomFastReservationController {
	@Autowired
	RoomService roomService;

	@Autowired
	RoomFastReservationService roomFastReservationService;

	@Autowired
	HotelCustomerServiceService hotelCustomerServiceService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	RoomReservationService roomReservationService;

	@Autowired
	HotelService hotelService;

	@PostMapping(value = "/createRoomFastReservation", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_HOTEL_ADMIN')")
	public ResponseEntity<MessageDTO> create(@RequestBody RoomFastReservationDTO roomFastReservationDTO)
			throws ParseException {
		Room room = roomService.getOne(roomFastReservationDTO.getRoomId());
		List<RoomFastReservation> reservations = roomFastReservationService.findByRoom(room);
		List<RoomReservation> reservations2 = roomReservationService.findByRoomsContaining(room);
		Boolean correctDate = true;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date startDate = new Date();
		Date endDate = new Date();
		try {
			startDate = df.parse(roomFastReservationDTO.getStartDate());
			endDate = df.parse(roomFastReservationDTO.getEndDate());
		} catch (Exception e) {
			correctDate = false;
		}
		if (!correctDate) {
			return new ResponseEntity<>(new MessageDTO("Bad format of dates!", "Error"), HttpStatus.OK);
		}
		for (RoomFastReservation roomFastReservation : reservations) {
			Date startDate2 = df.parse(roomFastReservation.getStartDate());
			Date endDate2 = df.parse(roomFastReservation.getEndDate());
			if (!(startDate.getTime() < startDate2.getTime() && endDate.getTime() < startDate2.getTime())
					&& !(startDate.getTime() > endDate2.getTime() && endDate.getTime() > endDate2.getTime())) {
				return new ResponseEntity<>(new MessageDTO(
						"This room already have been fast reserved by this period, pick some other dates!", "Error"),
						HttpStatus.OK);
			}
		}
		for (RoomReservation roomReservation : reservations2) {
			Date startDate2 = df.parse(roomReservation.getStartDate());
			Date endDate2 = df.parse(roomReservation.getEndDate());
			if (!(startDate.getTime() < startDate2.getTime() && endDate.getTime() < startDate2.getTime())
					&& !(startDate.getTime() > endDate2.getTime() && endDate.getTime() > endDate2.getTime())) {
				return new ResponseEntity<>(new MessageDTO(
						"This room already have been reserved by this period, pick some other dates!", "Error"),
						HttpStatus.OK);
			}
		}
		RoomFastReservation retVal = new RoomFastReservation();
		retVal.setRoom(room);
		retVal.setReserved(false);
		retVal.setStartDate(roomFastReservationDTO.getStartDate());
		retVal.setEndDate(roomFastReservationDTO.getEndDate());
		retVal.setDiscount(roomFastReservationDTO.getDiscount());
		double price = room.getPrice();
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
	@PreAuthorize("hasRole('ROLE_HOTEL_ADMIN')")
	public ResponseEntity<List<RoomFastReservation>> getRoomFastReservations() {
		HotelAdmin hotelAdmin = (HotelAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		List<RoomFastReservation> retVal = roomFastReservationService.findByHotel(hotelAdmin.getHotel());

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/getConcreteRoomFastReservations/{hotelId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RoomFastReservation>> getConcreteRoomFastReservations(@PathVariable Long hotelId) {

		Hotel h = hotelService.getOne(hotelId);

		List<RoomFastReservation> retVal = roomFastReservationService.findByHotel(h);

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
