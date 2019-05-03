package tim3.spring.project.isamrs.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import tim3.spring.project.isamrs.dto.RoomReservationDTO;
import tim3.spring.project.isamrs.model.HotelCustomerService;
import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.Room;
import tim3.spring.project.isamrs.model.RoomFastReservation;
import tim3.spring.project.isamrs.model.RoomReservation;
import tim3.spring.project.isamrs.service.HotelCustomerServiceService;
import tim3.spring.project.isamrs.service.RoomFastReservationService;
import tim3.spring.project.isamrs.service.RoomReservationService;
import tim3.spring.project.isamrs.service.RoomService;
import tim3.spring.project.isamrs.service.impl.CustomUserDetailsService;

@RestController
public class RoomReservationController {
	@Autowired
	RoomService roomService;

	@Autowired
	RoomFastReservationService roomFastReservationService;

	@Autowired
	HotelCustomerServiceService hotelCustomerServiceService;

	@Autowired
	RoomReservationService roomReservationService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@PostMapping(value = "/createRoomReservationRegular", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<MessageDTO> create(@RequestBody RoomReservationDTO roomReservationDTO) throws ParseException {

		RegularUser user = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Boolean correctDate = true;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			df.parse(roomReservationDTO.getStartDate());
			df.parse(roomReservationDTO.getEndDate());
		} catch (Exception e) {
			correctDate = false;
		}
		if (!correctDate) {
			return new ResponseEntity<>(new MessageDTO("Bad format of dates!", "Error"), HttpStatus.OK);
		}
		RoomReservation retVal = new RoomReservation();
		retVal.setRegularUser(user);
		retVal.setFastReserved(false);
		retVal.setRoomFastReservationId(0L);
		retVal.setStartDate(roomReservationDTO.getStartDate());
		retVal.setEndDate(roomReservationDTO.getEndDate());
		retVal.setDiscount(roomReservationDTO.getDiscount());
		double price = 0;
		if (!roomReservationDTO.getHotelCustomerServices().equals("")) {
			String[] hcsIds = roomReservationDTO.getHotelCustomerServices().split(" ");
			for (String hcsId : hcsIds) {
				HotelCustomerService hcs = hotelCustomerServiceService.getOne(Long.parseLong(hcsId));
				retVal.getHotelCustomerServices().add(hcs);
				price += hcs.getPrice();
			}
		}
		if (!roomReservationDTO.getRoomIds().equals("")) {
			String[] roomIds = roomReservationDTO.getHotelCustomerServices().split(" ");
			for (String roomId : roomIds) {
				Room room = roomService.getOne(Long.parseLong(roomId));
				retVal.setHotel(room.getHotel());
				retVal.getRooms().add(room);
				price += room.getPrice();
			}
		}
		retVal.setPrice((price / 100) * (100 - roomReservationDTO.getDiscount()));
		retVal.setNumOfPass(roomReservationDTO.getNumOfPass());
		roomReservationService.create(retVal);
		return new ResponseEntity<>(new MessageDTO("Succesfully made reservation!", "Error"), HttpStatus.CREATED);
	}

	@PostMapping(value = "/createRoomReservationFast/{roomFastReservationId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<MessageDTO> create(@PathVariable Long roomFastReservationId) throws ParseException {

		RegularUser user = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		RoomFastReservation rfr = roomFastReservationService.getOne(roomFastReservationId);
		rfr.setReserved(true);
		RoomReservation retVal = new RoomReservation();
		retVal.setRegularUser(user);
		retVal.setHotel(rfr.getRoom().getHotel());
		retVal.setHotelCustomerServices(rfr.getHotelCustomerServices());
		retVal.getRooms().add(rfr.getRoom());
		retVal.setFastReserved(true);
		retVal.setRoomFastReservationId(roomFastReservationId);
		retVal.setNumOfPass(rfr.getRoom().getNumberPeople());
		retVal.setStartDate(rfr.getStartDate());
		retVal.setEndDate(rfr.getEndDate());
		retVal.setPrice(rfr.getNewPrice());
		retVal.setDiscount(rfr.getDiscount());
		roomReservationService.create(retVal);
		roomFastReservationService.save(rfr);
		return new ResponseEntity<>(new MessageDTO("Succesfully made fast reservation!", "Error"), HttpStatus.CREATED);
	}

	@GetMapping(value = "/getRoomReservations", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<RoomReservation>> getRoomReservations() {

		RegularUser user = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		List<RoomReservation> retVal = roomReservationService.findByRegularUser(user);

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@DeleteMapping(value = "/cancelRoomReservation/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<RoomReservation> cancelRoomReservation(@PathVariable String id) {
		RoomReservation r = roomReservationService.getOne(Long.parseLong(id));
		if (r == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (r.getFastReserved()) {
			RoomFastReservation rfr = roomFastReservationService.getOne(r.getRoomFastReservationId());
			rfr.setReserved(false);
			roomFastReservationService.save(rfr);
		}
		roomReservationService.delete(Long.parseLong(id));
		return new ResponseEntity<>(r, HttpStatus.OK);
	}

	@GetMapping(value = "/findRoomReservation/{id}")
	public ResponseEntity<RoomReservation> findRoomReservation(@PathVariable long id) {
		RoomReservation r = roomReservationService.getOne(id);
		if (r != null) {
			return new ResponseEntity<>(r, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
