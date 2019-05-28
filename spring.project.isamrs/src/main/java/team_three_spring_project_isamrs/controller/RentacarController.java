package team_three_spring_project_isamrs.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team_three_spring_project_isamrs.comparator.RentACarComparatorAddress;
import team_three_spring_project_isamrs.comparator.RentACarComparatorName;
import team_three_spring_project_isamrs.dto.RentacarDTO;
import team_three_spring_project_isamrs.dto.ReportHotelAttendanceDTO;
import team_three_spring_project_isamrs.dto.ReportRentacarAttendanceDTO;
import team_three_spring_project_isamrs.model.CarReservation;
import team_three_spring_project_isamrs.model.HotelAdmin;
import team_three_spring_project_isamrs.model.Rentacar;
import team_three_spring_project_isamrs.model.RentacarAdmin;
import team_three_spring_project_isamrs.model.RoomReservation;
import team_three_spring_project_isamrs.service.CarReservationService;
import team_three_spring_project_isamrs.service.RentacarService;
import team_three_spring_project_isamrs.service.impl.CustomUserDetailsService;

@RestController
public class RentacarController {

	@Autowired
	RentacarService rentacarService;
	
	@Autowired
	CarReservationService carReservationService;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@GetMapping(value = "/reportRentacarAttendance")
	@PreAuthorize("hasRole('ROLE_RENTACAR_ADMIN')")
	public ResponseEntity<ReportRentacarAttendanceDTO> reportRentacarAttendance() throws ParseException {
		System.out.println("report rentacar attendance called");
		ReportRentacarAttendanceDTO retVal = new ReportRentacarAttendanceDTO();
		long DAY_IN_MILI = 86400000;
		Date currentDate = new Date();
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM");
		Date today = df1.parse(df1.format(currentDate));
		Date thisMonth = df2.parse(df2.format(currentDate));
		List<CarReservation> allReservations = carReservationService.getAll();
		Date workWith = new Date();
		Date workWith2 = new Date();
		Date startDate = new Date();
		Date endDate = new Date();
		// daily
		for (int i = 1; i < 8; i++) {
			int number = 0;
			workWith = new Date(today.getTime() - i * DAY_IN_MILI);
			retVal.getDailyLabels().add(df1.format(workWith));
			for (CarReservation carReservation : allReservations) {
				startDate = df1.parse(carReservation.getStartDate().toString());
				endDate = df1.parse(carReservation.getEndDate().toString());
				if (!startDate.after(workWith) && !endDate.before(workWith)) {
					number += carReservation.getNumOfPass();
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
			for (CarReservation carReservation : allReservations) {
				startDate = df1.parse(carReservation.getStartDate().toString());
				endDate = df1.parse(carReservation.getEndDate().toString());
				if (!startDate.after(workWith) && !endDate.before(workWith2)) {
					number += carReservation.getNumOfPass();
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
			for (CarReservation carReservation : allReservations) {
				startDate = df1.parse(carReservation.getStartDate().toString());
				endDate = df1.parse(carReservation.getEndDate().toString());
				if (!startDate.after(workWith) && !endDate.before(workWith2)) {
					number += carReservation.getNumOfPass();
				}
			}
			retVal.getMonthlyValues().add(number);
			thisMonth = df2.parse(df2.format(new Date(thisMonth.getTime() - DAY_IN_MILI)));
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/findRentacarAmount/{startDate}/{endDate}")
	@PreAuthorize("hasRole('ROLE_RENTACAR_ADMIN')")
	public ResponseEntity<Double> findRentacarAmount(@PathVariable String startDate, @PathVariable String endDate)
			throws ParseException {
		RentacarAdmin ra = (RentacarAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<CarReservation> allReservations = carReservationService.findByRentacarRes(ra.getRentacar());
		List<CarReservation> retVal = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate1 = new Date();
		Date endDate1 = new Date();
		Date startDate2 = new Date();
		Date endDate2 = new Date();
		for (CarReservation carReservation : allReservations) {
			startDate1 = df.parse(carReservation.getStartDate().toString());
			endDate1 = df.parse(carReservation.getEndDate().toString());
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
			retVal.add(carReservation);
		}
		double value = 0;
		for (CarReservation carReservation : retVal) {
			value += carReservation.getPrice();
		}
		return new ResponseEntity<>(value, HttpStatus.OK);
	}

	@GetMapping(value = "/findConcreteRentacar/{id}")
	public ResponseEntity<Rentacar> findConcreteRentacar(@PathVariable String id) {
		Rentacar retVal = rentacarService.getOne(Long.parseLong(id));
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllRentacars")
	public ResponseEntity<List<Rentacar>> getAllRentacars() {
		List<Rentacar> rentacars = rentacarService.getAll();
		return new ResponseEntity<>(rentacars, HttpStatus.OK);
	}

	@PostMapping(value = "/createRentacar", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<Rentacar> create(@RequestBody RentacarDTO rentacarDTO) {
		Rentacar retVal = rentacarService.create(new Rentacar(rentacarDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@GetMapping(value = "/gradeRentacar/{id}/{grade}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Rentacar> create(@PathVariable Long id, @PathVariable Integer grade) {
		Rentacar rentacar = rentacarService.getOne(id);
		rentacar.setScore(rentacar.getScore() + grade);
		rentacar.setNumber(rentacar.getNumber() + 1);
		rentacarService.save(rentacar);
		return new ResponseEntity<>(rentacar, HttpStatus.CREATED);
	}

	@GetMapping(value = "/findRentacar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rentacar> findRentacar() {
		RentacarAdmin rentacarAdmin = (RentacarAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Rentacar rentacar = rentacarAdmin.getRentacar();
		if (rentacar != null) {
			return new ResponseEntity<>(rentacar, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/saveChangesRentACar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_RENTACAR_ADMIN')")
	public ResponseEntity<Rentacar> saveChangesRentACar(@RequestBody RentacarDTO rentacar) {
		RentacarAdmin rentacarAdmin = (RentacarAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Rentacar r = rentacarAdmin.getRentacar();
		if (r == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		r.setName(rentacar.getRentacarNameRegister());
		r.setAddress(rentacar.getRentacarAddressRegister());
		r.setPromotionalDescription(rentacar.getRentacarPromotionalDescription());
		r.setCity(rentacar.getCity());

		rentacarAdmin.setRentacar(r);

		Rentacar rent = rentacarService.save(r);
		return new ResponseEntity<>(rent, HttpStatus.OK);
	}

	@GetMapping(value = "/showRentACars/{criteria}")
	public ResponseEntity<List<Rentacar>> showRentACars(@PathVariable String criteria) {
		List<Rentacar> rentACars = rentacarService.getAll();
		if (criteria.equals("sortByNameRentACars")) {
			Collections.sort(rentACars, new RentACarComparatorName());
		} else if (criteria.equals("sortByAddressRentACars")) {
			Collections.sort(rentACars, new RentACarComparatorAddress());
		}
		return new ResponseEntity<>(rentACars, HttpStatus.OK);
	}

	@GetMapping(value = "/findRentacars/{field}")
	public ResponseEntity<List<Rentacar>> findRentacars(@PathVariable String field) {
		List<Rentacar> rentACars = (List<Rentacar>) rentacarService.findByName(field);
		if (rentACars.size() == 0) {
			rentACars = (List<Rentacar>) rentacarService.findByCity(field);
		}

		
		return new ResponseEntity<>(rentACars, HttpStatus.OK);
	}

	@GetMapping(value = "/findRentacar/{address}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Rentacar>> searchFlights(@PathVariable String address) {
		List<Rentacar> allRentacars = rentacarService.getAll();
		List<Rentacar> rentacars = new ArrayList<Rentacar>();
		/*
		 * String grad = address.split(", ")[1].split(" ")[1]; System.out.println(grad);
		 * for (Rentacar rent : allRentacars) { if (rent.getAddress().contains(grad)) {
		 * rentacars.add(rent); } }
		 */
		/*
		 * for (Rentacar r : allRentacars) { if (r.getCity() != null) { if
		 * (r.getCity().equalsIgnoreCase(address)) { rentacars.add(r); } }
		 * 
		 * }
		 */
		
		rentacars=rentacarService.findByCity(address);

		return new ResponseEntity<>(rentacars, HttpStatus.OK);
	}
}
