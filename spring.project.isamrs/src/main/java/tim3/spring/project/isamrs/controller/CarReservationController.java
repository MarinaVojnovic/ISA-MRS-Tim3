package tim3.spring.project.isamrs.controller;

import java.util.Date;

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

import tim3.spring.project.isamrs.dto.CarReservationDTO;
import tim3.spring.project.isamrs.dto.MessageDTO;
import tim3.spring.project.isamrs.model.Car;
import tim3.spring.project.isamrs.model.CarReservation;
import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.Rentacar;
import tim3.spring.project.isamrs.service.CarReservationService;
import tim3.spring.project.isamrs.service.CarService;
import tim3.spring.project.isamrs.service.RentacarService;
import tim3.spring.project.isamrs.service.impl.CustomUserDetailsService;

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
		@SuppressWarnings("deprecation")
		Date startDatee = new Date(Integer.parseInt(startDate.split("\\-")[0]) - 1900,
				Integer.parseInt(startDate.split("\\-")[1]) - 1,
				Integer.parseInt(startDate.split("\\-")[2]));
		@SuppressWarnings("deprecation")
		Date endDatee = new Date(Integer.parseInt(endDate.split("\\-")[0]) - 1900,
				Integer.parseInt(endDate.split("\\-")[1]) - 1,
				Integer.parseInt(endDate.split("\\-")[2]));
		
		
		CarReservationDTO dto = new CarReservationDTO(carId,startDatee,endDatee,passengers);
		System.out.println("A");
		CarReservation newCarRes = new CarReservation(dto);
		System.out.println("B");
		System.out.println("Car id from dto:"+dto.getCarId());
		Car car = carService.getOne(dto.getCarId());
		System.out.println("C");
		newCarRes.setCar(car);
		newCarRes.setPrice(car.getPrice());
		newCarRes.setRegularUser(user);
		Rentacar rentacar = rentacarService.getOne(car.getRentacar().getId());
		System.out.println("D");
		System.out.println(newCarRes.getEndDate());
		newCarRes.setRentacarRes(rentacar);
		System.out.println("FFF");
		
		carReservationService.save(newCarRes);
		System.out.println("E");
		user.getCarReservations().add(newCarRes);
		System.out.println("F");
		rentacar.getCarReservations().add(newCarRes);
		System.out.println("G");
		car.getReservations().add(newCarRes);
		System.out.println("H");
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
		
		
		CarReservation newCarRes = new CarReservation(startDatee,endDatee);
		Car car = carService.getOne(carId);
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
		for (CarReservation cr : user.getCarReservations()) {
			if (cr.getId()==resId) {
				user.getCarReservations().remove(cr);
			}
		}
		userDetailsService.saveUser(user);
		CarReservation carRes = carReservationService.getOne(resId);
		Car car = carRes.getCar();
		for (CarReservation cr : car.getReservations()) {
			if (cr.getId()==resId) {
				car.getReservations().remove(cr);
			}
		}
		carService.save(car);
		carReservationService.delete(resId);
		
	
		return new ResponseEntity<>(carRes, HttpStatus.OK);
		
		
		
	}
}
