package team_three_spring_project_isamrs.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import team_three_spring_project_isamrs.dto.CarReservationDTO;
import team_three_spring_project_isamrs.dto.MessageDTO;
import team_three_spring_project_isamrs.model.Car;
import team_three_spring_project_isamrs.model.CarReservation;
import team_three_spring_project_isamrs.model.FlightReservation;
import team_three_spring_project_isamrs.model.RegularUser;
import team_three_spring_project_isamrs.model.Rentacar;
import team_three_spring_project_isamrs.service.CarReservationService;
import team_three_spring_project_isamrs.service.CarService;
import team_three_spring_project_isamrs.service.RentacarService;
import team_three_spring_project_isamrs.service.impl.CustomUserDetailsService;

@RestController
public class CarReservationController {

	@Autowired
	CarReservationService carReservationService;
	
	@Autowired
	CarService carService;
	
	@Autowired
	RentacarService rentacarService;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@SuppressWarnings("deprecation")
	@PutMapping(value = "/putCarOnFastRes/{carId}/{startDate}/{endDate}/{price}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_RENTACAR_ADMIN')")
	public ResponseEntity<?> putCarOnFastRes(@PathVariable Long carId, @PathVariable String startDate, @PathVariable String endDate, @PathVariable Double price) {
		System.out.println("USLO U PUT CAR ON FAST RES");
		@SuppressWarnings("deprecation")
		Date startDatee = new Date(Integer.parseInt(startDate.split("\\-")[0]) - 1900,
				Integer.parseInt(startDate.split("\\-")[1]) - 1,
				Integer.parseInt(startDate.split("\\-")[2]));
		@SuppressWarnings("deprecation")
		Date endDatee = new Date(Integer.parseInt(endDate.split("\\-")[0]) - 1900,
				Integer.parseInt(endDate.split("\\-")[1]) - 1,
				Integer.parseInt(endDate.split("\\-")[2]));
		
		Car c = carService.getOne(carId); 
		Boolean dozvola=true;
		for (CarReservation res : c.getReservations()) {
			if (res.getStartDate().compareTo(startDatee)<0 && res.getEndDate().compareTo(startDatee)<0) {
				dozvola=false;
			}
			if (res.getStartDate().compareTo(endDatee)<0 && res.getEndDate().compareTo(endDatee)>0) {
				dozvola=false;
			}
			if (res.getStartDate().compareTo(endDatee)<0 && res.getEndDate().compareTo(endDatee)<0) {
				dozvola=false;
			}
		}
		
		if (dozvola==true) {
			c.setOnFastRes(true);
			c.setFastResStartDate(startDatee);
			c.setFastResEndDate(endDatee);
			c.setFastResPrice(price);
			carService.save(c);
			return new ResponseEntity<>(c, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new MessageDTO("Car is reserved, it cannot be put on fast res.", "Error"),
					HttpStatus.OK);
		}
	  
	}

	@GetMapping(value = "/findRentacarFromRes/{resId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rentacar> findRentacarFromRes(@PathVariable Long resId) {
		CarReservation res = carReservationService.getOne(resId);
		Rentacar rentacar = res.getRentacarRes();
		return new ResponseEntity<>(rentacar, HttpStatus.OK);
	}
	
	@GetMapping(value = "/findCarFromRes/{resId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Car> findCarFromRes(@PathVariable Long resId) {
		CarReservation res = carReservationService.getOne(resId);
		Car car = res.getCar();
		return new ResponseEntity<>(car, HttpStatus.OK);
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping(value = "/createCarReservation/{carId}/{startDate}/{endDate}/{passengers}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<CarReservation> create(@PathVariable Long carId, @PathVariable String startDate,
			 @PathVariable String endDate, @PathVariable Integer passengers) {
		System.out.println("Uslo u creating car reservation");
		RegularUser user = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		Integer numberOfRes = user.getCarReservations().size()+user.getFlightReservations().size()+user.getRoomReservations().size();
		Integer discount;
		if (numberOfRes==3) {
			discount=5;
		}else if(numberOfRes==10) {
			discount=10;
		}else if (numberOfRes==30) {
			discount=20;
		}else if(numberOfRes==100) {
			discount=40;
		}else {
			discount=0;
		}
		
		@SuppressWarnings("deprecation")
		Date startDatee = new Date(Integer.parseInt(startDate.split("\\-")[0]) - 1900,
				Integer.parseInt(startDate.split("\\-")[1]) - 1,
				Integer.parseInt(startDate.split("\\-")[2]));
		@SuppressWarnings("deprecation")
		Date endDatee = new Date(Integer.parseInt(endDate.split("\\-")[0]) - 1900,
				Integer.parseInt(endDate.split("\\-")[1]) - 1,
				Integer.parseInt(endDate.split("\\-")[2]));
		
		List<FlightReservation> flightRes=user.getFlightReservations();
		System.out.println("AA");
		FlightReservation lastRes=flightRes.get(0);
		for (FlightReservation fr : flightRes) {
			if (fr.getId() > lastRes.getId()) {
				lastRes=fr;
			}
		}
		System.out.println("BB");
		CarReservationDTO dto = new CarReservationDTO(carId,startDatee,endDatee,passengers);
		CarReservation newCarRes = new CarReservation(dto);
		Car car = carService.getOne(dto.getCarId());
		newCarRes.setFlightId(lastRes.getId());
		newCarRes.setCar(car);
		if (discount==0) {
			newCarRes.setPrice(car.getPrice());
		}else {
			newCarRes.setPrice(car.getPrice()*(100-discount)/100);
		}
		
		newCarRes.setRegularUser(user);
		newCarRes.setNumOfPass(passengers);
		newCarRes.setDiscount(discount);
		System.out.println("CC");
		//newCarRes.setResFlight(last);
		System.out.println("DD");
		Rentacar rentacar = rentacarService.getOne(car.getRentacar().getId());
		newCarRes.setRentacarRes(rentacar);
		//last.getCarReservations().add(newCarRes);
		//System.out.println(last.getId()+"       "+"jjjjjj");
		
		
		carReservationService.save(newCarRes);
		System.out.println("hhhhh");
		
		user.getCarReservations().add(newCarRes);
		System.out.println("ssss");
		rentacar.getCarReservations().add(newCarRes);
		System.out.println("rrrr");
		car.getReservations().add(newCarRes);
		System.out.println("EE");
		
		System.out.println("FF");
		
		return new ResponseEntity<>(newCarRes, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping(value = "/createCarReservationFast/{carId}/{startDate}/{endDate}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<CarReservation> createCarReservationFast(@PathVariable Long carId, @PathVariable String startDate,
			 @PathVariable String endDate) {
		System.out.println("Uslo u creating car reservation fast");
		RegularUser user = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		@SuppressWarnings("deprecation")
		Date startDatee = new Date(Integer.parseInt(startDate.split("\\-")[0]) - 1900,
				Integer.parseInt(startDate.split("\\-")[1]) - 1,
				Integer.parseInt(startDate.split("\\-")[2]));
		@SuppressWarnings("deprecation")
		Date endDatee = new Date(Integer.parseInt(endDate.split("\\-")[0]) - 1900,
				Integer.parseInt(endDate.split("\\-")[1]) - 1,
				Integer.parseInt(endDate.split("\\-")[2]));
		List<FlightReservation> flightRes=user.getFlightReservations();
		System.out.println("AA");
		FlightReservation lastRes=flightRes.get(0);
		for (FlightReservation fr : flightRes) {
			if (fr.getId() > lastRes.getId()) {
				lastRes=fr;
			}
		}
		
		CarReservation newCarRes = new CarReservation(startDatee,endDatee);
		Car car = carService.getOne(carId);
		newCarRes.setFlightId(lastRes.getId());
		newCarRes.setCar(car);
		newCarRes.setPrice(car.getFastResPrice());
		newCarRes.setRegularUser(user);
		Rentacar rentacar = rentacarService.getOne(car.getRentacar().getId());
		newCarRes.setRentacarRes(rentacar);
	
		
		carReservationService.save(newCarRes);
		user.getCarReservations().add(newCarRes);
		rentacar.getCarReservations().add(newCarRes);
		car.getReservations().add(newCarRes);
		
		return new ResponseEntity<>(newCarRes, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/cancelCarReservation/{resId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> cancelCarReservation(@PathVariable Long resId) {
		System.out.println("Uslo u cancel car reservation");
		RegularUser user = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		int brojac=-1;
		System.out.println("A");
		/*
		 * for (CarReservation cr : user.getCarReservations()) { brojac++; if
		 * (cr.getId()==resId) { user.getCarReservations().remove(brojac); } }
		 */
		System.out.println("B");
		userDetailsService.saveUser(user);
		CarReservation carRes = carReservationService.getOne(resId);
		Car car = carRes.getCar();
		int brojac2=-1;
		  for (CarReservation cr : car.getReservations())
		  { 
			  brojac2++;
			  if (cr.getId()==resId) {
		  car.getReservations().remove(brojac2); } }
		  System.out.println("C");
		carService.save(car);
		System.out.println("D");
		carReservationService.delete(resId);
		System.out.println("F");
		
	
		return new ResponseEntity<>(carRes, HttpStatus.OK);
		
		
		
	}
}
