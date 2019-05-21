package team_three_spring_project_isamrs.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import team_three_spring_project_isamrs.dto.MessageDTO;
import team_three_spring_project_isamrs.dto.ReportHotelAttendanceDTO;
import team_three_spring_project_isamrs.dto.RoomReservationDTO;
import team_three_spring_project_isamrs.model.Hotel;
import team_three_spring_project_isamrs.model.HotelAdmin;
import team_three_spring_project_isamrs.model.HotelCustomerService;
import team_three_spring_project_isamrs.model.RegularUser;
import team_three_spring_project_isamrs.model.Room;
import team_three_spring_project_isamrs.model.RoomFastReservation;
import team_three_spring_project_isamrs.model.RoomReservation;
import team_three_spring_project_isamrs.service.HotelCustomerServiceService;
import team_three_spring_project_isamrs.service.RoomFastReservationService;
import team_three_spring_project_isamrs.service.RoomReservationService;
import team_three_spring_project_isamrs.service.RoomService;
import team_three_spring_project_isamrs.service.impl.CustomUserDetailsService;

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

	@DeleteMapping(value = "/cancelHotelReservation/{resId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> cancelHotelReservation(@PathVariable Long resId) {
		System.out.println("Uslo u cancel hotel reservation");
		RegularUser user = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		int brojac = -1;
		System.out.println("A");
		/*
		 * for (CarReservation cr : user.getCarReservations()) { brojac++; if
		 * (cr.getId()==resId) { user.getCarReservations().remove(brojac); } }
		 */
		System.out.println("B");
		RoomReservation roomRes = roomReservationService.getOne(resId);
		roomReservationService.delete(resId);

		return new ResponseEntity<>(roomRes, HttpStatus.OK);

	}

	@GetMapping(value = "/findHotelFromRes/{resId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> findHotelFromRes(@PathVariable Long resId) {
		RoomReservation res = roomReservationService.getOne(resId);
		Hotel hotel = res.getHotel();
		return new ResponseEntity<>(hotel, HttpStatus.OK);
	}

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
		long numberOfNights = ((df.parse(roomReservationDTO.getEndDate()).getTime()
				- df.parse(roomReservationDTO.getStartDate()).getTime() - 1000 * 60) / 86400000) + 1;
		double price = 0;
		if (!roomReservationDTO.getHotelCustomerServices().equals("")) {
			String[] hcsIds = roomReservationDTO.getHotelCustomerServices().split(" ");
			for (String hcsId : hcsIds) {
				HotelCustomerService hcs = hotelCustomerServiceService.getOne(Long.parseLong(hcsId));
				retVal.getHotelCustomerServices().add(hcs);
				price += hcs.getPrice();
			}
		}
		if (retVal.getHotelCustomerServices().size() >= roomReservationDTO.getNumberHotelDiscount()) {
			retVal.setDiscount(retVal.getDiscount() + 10);
		}
		int numOfPass = 0;
		if (!roomReservationDTO.getRoomIds().equals("")) {
			String[] roomIds = roomReservationDTO.getRoomIds().split(" ");
			for (String roomId : roomIds) {
				Room room = roomService.getOne(Long.parseLong(roomId));
				retVal.setHotel(room.getHotel());
				retVal.getRooms().add(room);
				price += room.getPrice() * numberOfNights;
				numOfPass += room.getNumberPeople();
			}
		}
		retVal.setPrice((price / 100) * (100 - retVal.getDiscount()));
		retVal.setNumOfPass(numOfPass);
		roomReservationService.create(retVal);
		return new ResponseEntity<>(new MessageDTO("Succesfully made reservation!", "Error"), HttpStatus.CREATED);
	}

	@PostMapping(value = "/createRoomReservationFast/{roomFastReservationId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<MessageDTO> create(@PathVariable Long roomFastReservationId) throws ParseException {

		RegularUser user = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		RoomFastReservation rfr = null;
		try {
			rfr = roomFastReservationService.getOne(roomFastReservationId);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new MessageDTO("This reservation have just been reserved or deleted, you were so close :(",
							"warning"),
					HttpStatus.OK);
		}
		rfr.setReserved(true);
		RoomReservation retVal = new RoomReservation();
		retVal.setRegularUser(user);
		retVal.setHotel(rfr.getRoom().getHotel());
		for (HotelCustomerService hcsss : rfr.getHotelCustomerServices()) {
			retVal.getHotelCustomerServices().add(hcsss);
		}
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
		return new ResponseEntity<>(new MessageDTO("Succesfully made fast reservation!", "success"),
				HttpStatus.CREATED);
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

	@GetMapping(value = "/findHotelAmount/{startDate}/{endDate}")
	@PreAuthorize("hasRole('ROLE_HOTEL_ADMIN')")
	public ResponseEntity<Double> findHotelAmount(@PathVariable String startDate, @PathVariable String endDate)
			throws ParseException {
		HotelAdmin ha = (HotelAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<RoomReservation> allReservations = roomReservationService.findByHotel(ha.getHotel());
		List<RoomReservation> retVal = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate1 = new Date();
		Date endDate1 = new Date();
		Date startDate2 = new Date();
		Date endDate2 = new Date();
		for (RoomReservation roomReservation : allReservations) {
			startDate1 = df.parse(roomReservation.getStartDate().substring(0, 10));
			endDate1 = df.parse(roomReservation.getEndDate().substring(0, 10));
			if (!startDate.equals("0000-00-00") && !endDate.equals("0000-00-00")) {
				startDate2 = df.parse(startDate);
				endDate2 = df.parse(endDate);
				if (startDate2.getTime() >= endDate2.getTime()
						|| (startDate2.getTime() < startDate1.getTime() && endDate2.getTime() < startDate1.getTime())
						|| (startDate2.getTime() > endDate1.getTime() && endDate2.getTime() > endDate1.getTime())) {
					continue;
				}
			} else if (startDate.equals("0000-00-00") && !endDate.equals("0000-00-00")) {
				endDate2 = df.parse(endDate);
				if (endDate2.getTime() < startDate1.getTime()) {
					continue;
				}
			} else if (!startDate.equals("0000-00-00") && endDate.equals("0000-00-00")) {
				startDate2 = df.parse(startDate);
				if (endDate1.getTime() < startDate2.getTime()) {
					continue;
				}
			}
			retVal.add(roomReservation);
		}
		double value = 0;
		for (RoomReservation roomReservation : retVal) {
			value += roomReservation.getPrice();
		}
		return new ResponseEntity<>(value, HttpStatus.OK);
	}

	@GetMapping(value = "/reportHotelAttendance")
	@PreAuthorize("hasRole('ROLE_HOTEL_ADMIN')")
	public ResponseEntity<ReportHotelAttendanceDTO> reportHotelAttendance() throws ParseException {
		ReportHotelAttendanceDTO retVal = new ReportHotelAttendanceDTO();
		long DAY_IN_MILI = 86400000;
		Date currentDate = new Date();
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM");
		Date today = df1.parse(df1.format(currentDate));
		Date thisMonth = df2.parse(df2.format(currentDate));
		List<RoomReservation> allReservations = roomReservationService.getAll();
		Date workWith = new Date();
		Date workWith2 = new Date();
		Date startDate = new Date();
		Date endDate = new Date();
		// daily
		for (int i = 1; i < 8; i++) {
			int number = 0;
			workWith = new Date(today.getTime() - i * DAY_IN_MILI);
			retVal.getDailyLabels().add(df1.format(workWith));
			for (RoomReservation roomReservation : allReservations) {
				startDate = df1.parse(roomReservation.getStartDate());
				endDate = df1.parse(roomReservation.getEndDate());
				if (!startDate.after(workWith) && !endDate.before(workWith)) {
					number += roomReservation.getNumOfPass();
				}
			}
			retVal.getDailyValues().add(number);
		}
		// weekly
		for (int i = 0; i < 7; i++) {
			int number = 0;
			workWith = new Date(today.getTime() - (i * 7 + 1) * DAY_IN_MILI);
			workWith2 = new Date(today.getTime() - (7 * i + 7) * DAY_IN_MILI);
			retVal.getWeeklyLabels().add(df1.format(workWith2) + " to " + df1.format(workWith));
			for (RoomReservation roomReservation : allReservations) {
				startDate = df1.parse(roomReservation.getStartDate());
				endDate = df1.parse(roomReservation.getEndDate());
				if (!startDate.after(workWith) && !endDate.before(workWith2)) {
					number += roomReservation.getNumOfPass();
				}
			}
			retVal.getWeeklyValues().add(number);
		}
		// monthly
		for (int i = 0; i < 7; i++) {
			int number = 0;
			workWith = new Date(thisMonth.getTime() - DAY_IN_MILI);
			workWith2 = df2.parse(df2.format(workWith));
			retVal.getMonthlyLabels().add(df2.format(workWith2));
			for (RoomReservation roomReservation : allReservations) {
				startDate = df1.parse(roomReservation.getStartDate());
				endDate = df1.parse(roomReservation.getEndDate());
				if (!startDate.after(workWith) && !endDate.before(workWith2)) {
					number += roomReservation.getNumOfPass();
				}
			}
			retVal.getMonthlyValues().add(number);
			thisMonth = df2.parse(df2.format(new Date(thisMonth.getTime() - DAY_IN_MILI)));
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

}
