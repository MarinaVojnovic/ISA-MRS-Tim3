package team_three_spring_project_isamrs.controller;

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
import org.springframework.web.bind.annotation.RestController;

import team_three_spring_project_isamrs.dto.InvitedFriendDTO;
import team_three_spring_project_isamrs.dto.MessageDTO;
import team_three_spring_project_isamrs.dto.ReportFlightAttendanceDTO;
import team_three_spring_project_isamrs.model.Airline;
import team_three_spring_project_isamrs.model.AirlineAdmin;
import team_three_spring_project_isamrs.model.Car;
import team_three_spring_project_isamrs.model.CarReservation;
import team_three_spring_project_isamrs.model.Flight;
import team_three_spring_project_isamrs.model.FlightReservation;
import team_three_spring_project_isamrs.model.FriendRequest;
import team_three_spring_project_isamrs.model.RegularUser;
import team_three_spring_project_isamrs.model.Room;
import team_three_spring_project_isamrs.model.RoomReservation;
import team_three_spring_project_isamrs.model.Seat;
import team_three_spring_project_isamrs.service.CarReservationService;
import team_three_spring_project_isamrs.service.CarService;
import team_three_spring_project_isamrs.service.FlightReservationService;
import team_three_spring_project_isamrs.service.FlightService;
import team_three_spring_project_isamrs.service.FriendRequestService;
import team_three_spring_project_isamrs.service.RoomReservationService;
import team_three_spring_project_isamrs.service.SeatService;
import team_three_spring_project_isamrs.service.impl.CustomUserDetailsService;

@RestController
public class FlightReservationController {
	@Autowired
	FlightReservationService flightReservationService;

	@Autowired
	CarReservationService carReservationService;
	
	@Autowired
	RoomReservationService roomReservationService;

	@Autowired
	CarService carService;

	@Autowired
	FlightService flightService;

	@Autowired
	SeatService seatService;
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	FriendRequestService friendRequestService;

	@GetMapping(value = "/findAirlineFromRes/{resId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> findAirlineFromRes(@PathVariable Long resId) {
		FlightReservation res = flightReservationService.getOne(resId);
		Airline airline = res.getFlightReservation().getAirline();
		return new ResponseEntity<>(airline, HttpStatus.OK);
	}

	@GetMapping(value = "/findFlightFromRes/{resId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> findFlightFromRes(@PathVariable Long resId) {
		FlightReservation res = flightReservationService.getOne(resId);
		Flight flight = res.getFlightReservation();
		return new ResponseEntity<>(flight, HttpStatus.OK);
	}

	@DeleteMapping(value = "/cancelFlightReservation/{resId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> cancelFlightReservation(@PathVariable Long resId) {
		FlightReservation flightRes = flightReservationService.getOne(resId);

		flightReservationService.delete(resId);
		Long seatId = flightRes.getSeat().getId();
		Seat seat = seatService.getOne(seatId);
		seat.setTaken(false);
		seatService.save(seat);

		List<CarReservation> carReservations = carReservationService.findByFlightId(resId);
	
		if (carReservations != null) {
			for (CarReservation carRes : carReservations) {
				Car car = carRes.getCar();
				int brojac2 = -1;
				for (CarReservation cr : car.getReservations()) {
					brojac2++;
					if (cr.getId().equals(carRes.getId())) {
						car.getReservations().remove(brojac2);
					}
				}
				carService.save(car);
				carReservationService.delete(carRes.getId());
			}

		}
		
		List<RoomReservation> roomReservations = roomReservationService.findByFlightId(resId);
		System.out.println("Res id: "+resId);
		System.out.println(roomReservations.size());
		if (roomReservations != null) {
			for (RoomReservation roomRess : roomReservations) {
				roomReservationService.delete(roomRess.getId());
			}
			
				
				
			

		}
		return new ResponseEntity<>(flightRes, HttpStatus.OK);

	}

	@GetMapping(value = "/reportFlightAttendance")
	@PreAuthorize("hasRole('ROLE_AIRLINE_ADMIN')")
	public ResponseEntity<ReportFlightAttendanceDTO> reportFlightAttendance() throws ParseException {
		ReportFlightAttendanceDTO retVal = new ReportFlightAttendanceDTO();
		long DAY_IN_MILI = 86400000;
		Date currentDate = new Date();
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM");
		Date today = df1.parse(df1.format(currentDate));
		Date thisMonth = df2.parse(df2.format(currentDate));
		List<FlightReservation> allReservations = this.flightReservationService.getAll();
		Date workWith;
		Date workWith2;
		Date startDate;
		// daily
		for (int i = 1; i < 8; i++) {
			int number = 0;
			workWith = new Date(today.getTime() - i * DAY_IN_MILI);
			retVal.getDailyLabels().add(df1.format(workWith));
			for (FlightReservation flightReservation : allReservations) {
				startDate = flightReservation.getDateSold();
				if (df1.format(startDate).equals(df1.format(workWith))) {
					number += 1;
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
			for (FlightReservation flightReservation : allReservations) {
				startDate = flightReservation.getDateSold();
				if (!startDate.after(workWith) && !startDate.before(workWith2)) {
					number += 1;
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
			for (FlightReservation flightReservation : allReservations) {
				startDate = flightReservation.getDateSold();
				if (!startDate.after(workWith) && !startDate.before(workWith2)) {
					number += 1;
				}
			}
			retVal.getMonthlyValues().add(number);
			thisMonth = df2.parse(df2.format(new Date(thisMonth.getTime() - DAY_IN_MILI)));
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@PostMapping(value = "/myReservation/{passportNum}/{seatId}")
	public ResponseEntity<?> myReservation(@PathVariable int passportNum, @PathVariable long seatId) {
		RegularUser logged = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Seat s = null;
		try {
			s=this.seatService.getOne(seatId);
			if(s.getTaken()==true || s.getQuickBooking()==true || s==null) {
				return new ResponseEntity<>(new MessageDTO("This reservation has just been deleted,reserved or set to fast booking","warning"),HttpStatus.OK);
			}
			s.setTaken(true);
			this.seatService.save(s);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new MessageDTO("This reservation has just been deleted,reserved or set to fast booking","warning"),HttpStatus.OK);
		}
		Flight fl = s.getFlight();
		Double price = fl.getCost();
		s.setTaken(true);
		if (s.getDiscount() != 0) {
			price = (price * s.getDiscount()) / 100;
		}

		Integer flights = 0;
		if (logged.getFlightReservations() != null && !logged.getFlightReservations().isEmpty()) {
			flights = logged.getFlightReservations().size();

		}
		Integer cars = 0;
		if (logged.getCarReservations() != null && !logged.getCarReservations().isEmpty()) {
			cars = logged.getCarReservations().size();
		}
		Integer hotels = 0;
		if (logged.getRoomReservations() != null && !logged.getRoomReservations().isEmpty()) {
			hotels = logged.getRoomReservations().size();
		}

		Integer discount;
		Integer numberOfRes = flights + cars + hotels;
		if (numberOfRes >= 3 && numberOfRes < 10) {
			discount = 5;
		} else if (numberOfRes >= 10 && numberOfRes < 30) {
			discount = 10;
		} else if (numberOfRes >= 30 && numberOfRes < 100) {
			discount = 20;
		} else if (numberOfRes >= 100) {
			discount = 40;
		} else {
			discount = 0;
		}

		FlightReservation fr = this.flightReservationService.create(new FlightReservation(price, logged, fl, s, true,
				passportNum, new Date(), logged.getFirstName(), logged.getLastName(), discount));
		return new ResponseEntity<>(fr.getId(), HttpStatus.OK);
	}

	@PostMapping(value = "/reserveForFriend/{name}/{lastName}/{passportNumber}/{fromFriendList}/{seatId}")
	public ResponseEntity<?> reserveForFriend(@PathVariable String name, @PathVariable String lastName,
			@PathVariable int passportNumber, @PathVariable boolean fromFriendList, @PathVariable long seatId) {
		RegularUser logged = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		InvitedFriendDTO dto;
		FlightReservation reservation;
		int found = 0;
		RegularUser foundUser = new RegularUser();
		Seat s = null;
		try {
			s=this.seatService.getOne(seatId);
			if(s.getTaken()==true || s.getQuickBooking()==true || s==null) {
				return new ResponseEntity<>(new MessageDTO("This reservation has just been deleted or reserved","warning"),HttpStatus.OK);
			}
			s.setTaken(true);
			this.seatService.save(s);
		}catch(Exception e) {
			return new ResponseEntity<>(new MessageDTO("This reservation has just been deleted or reserved","warning"),HttpStatus.OK);
		}
		if (fromFriendList) {
			List<FriendRequest> fr = this.friendRequestService.findByReceivedAndAccepted(logged, true);
			List<FriendRequest> fr2 = this.friendRequestService.findBySentAndAccepted(logged, true);
			fr2.addAll(fr);
			for (FriendRequest f : fr2) {
				if ((f.getReceived().getFirstName().equalsIgnoreCase(name)
						&& f.getReceived().getLastName().equalsIgnoreCase(lastName)
						&& !f.getReceived().getId().equals(logged.getId()))) {
					found = 1;
					foundUser = f.getReceived();
					break;
				} else if (f.getSent().getFirstName().equalsIgnoreCase(name)
						&& f.getSent().getLastName().equalsIgnoreCase(lastName)
						&& !f.getSent().getId().equals(logged.getId())) {
					found = 1;
					foundUser = f.getSent();
					break;
				}
			}
		}
		double price = s.getFlight().getCost();
		if (s.getDiscount() != 0) {
			price = (price * s.getDiscount()) / 100;
		}
		if (found == 1) {
			s.setTaken(true);
			reservation = this.flightReservationService.create(new FlightReservation(price, foundUser, s.getFlight(), s,
					false, passportNumber, new Date(), foundUser.getFirstName(), foundUser.getLastName()));
			dto = new InvitedFriendDTO(foundUser.getEmail(), reservation.getId());
		} else {
			s.setTaken(true);
			reservation = this.flightReservationService.create(new FlightReservation(price, null, s.getFlight(), s,
					true, passportNumber, new Date(), name, lastName));
			dto = new InvitedFriendDTO("no", reservation.getId());
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	

	@GetMapping(value = "/findAirlineAmount/{startDate}/{endDate}")
	@PreAuthorize("hasRole('ROLE_AIRLINE_ADMIN')")
	public ResponseEntity<Double> findAirlineAmount(@PathVariable String startDate, @PathVariable String endDate)
			throws ParseException {
		AirlineAdmin aa = (AirlineAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<FlightReservation> allReservations = this.flightReservationService.getAll();
		List<FlightReservation> fromMyAirline = new ArrayList<>();
		for (FlightReservation fr : allReservations) {
			if (fr.getFlightReservation().getAirline().getId().equals(aa.getAirline().getId())) {
				fromMyAirline.add(fr);
			}
		}
		List<FlightReservation> retVal = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate1;
		Date startDate2;
		Date endDate2;
		for (FlightReservation fr : fromMyAirline) {
			startDate1 = fr.getDateSold();
			if (!startDate.equals("0000-00-00") && !endDate.equals("0000-00-00")) {
				startDate2 = df.parse(startDate);
				endDate2 = df.parse(endDate);
				if (startDate2.getTime() >= endDate2.getTime() || (startDate1.before(startDate2))
						|| (startDate1.after(endDate2))) {
					continue;
				}
			} else if (startDate.equals("0000-00-00") && !endDate.equals("0000-00-00")) {
				endDate2 = df.parse(endDate);
				if (endDate2.before(startDate1)) {
					continue;
				}
			} else if (!startDate.equals("0000-00-00") && endDate.equals("0000-00-00")) {
				startDate2 = df.parse(startDate);
				if (startDate2.after(startDate1)) {
					continue;
				}
			}
			retVal.add(fr);
		}
		double value = 0;
		for (FlightReservation fr : retVal) {
			value += fr.getPrice();
		}
		return new ResponseEntity<>(value, HttpStatus.OK);
	}
}
