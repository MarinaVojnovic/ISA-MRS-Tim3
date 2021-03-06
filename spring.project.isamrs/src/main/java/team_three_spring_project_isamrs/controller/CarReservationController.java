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
	public ResponseEntity<?> putCarOnFastRes(@PathVariable Long carId, @PathVariable String startDate,
			@PathVariable String endDate, @PathVariable Double price) {
		@SuppressWarnings("deprecation")
		Date startDatee = new Date(Integer.parseInt(startDate.split("\\-")[0]) - 1900,
				Integer.parseInt(startDate.split("\\-")[1]) - 1, Integer.parseInt(startDate.split("\\-")[2]));
		System.out.println("AAAAA");
		@SuppressWarnings("deprecation")
		Date endDatee = new Date(Integer.parseInt(endDate.split("\\-")[0]) - 1900,
				Integer.parseInt(endDate.split("\\-")[1]) - 1, Integer.parseInt(endDate.split("\\-")[2]));

		System.out.println("BBBB");
		Car c = carService.getOne(carId);
		System.out.println("CCCC");
		Boolean dozvola = true;
		System.out.println("DDDD");
		for (CarReservation res : c.getReservations()) {
			if (res.getStartDate().compareTo(startDatee) <= 0 && res.getEndDate().compareTo(endDatee) >= 0 && startDatee.compareTo(res.getEndDate())<= 0) {
				dozvola = false;
			}
			if (res.getStartDate().compareTo(endDatee) <= 0 && res.getEndDate().compareTo(endDatee) >= 0) {
				dozvola = false;
			}
			if (startDatee.compareTo(res.getStartDate()) <= 0 && endDatee.compareTo(res.getEndDate())<= 0 && endDatee.compareTo(res.getStartDate())>= 0) {
				dozvola = false;
			}
		}

		System.out.println("EEEE");
		if (dozvola) {
			c.setOnFastRes(true);
			c.setFastResStartDate(startDatee);
			c.setFastResEndDate(endDatee);
			c.setFastResPrice(price);
			carService.save(c);
			System.out.println("a");
			return new ResponseEntity<>(c, HttpStatus.OK);
		} else {
			System.out.println("b");
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
	@PostMapping(value = "/createCarReservation/{carId}/{startDate}/{endDate}/{passengers}/{flight_res}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<CarReservation> create(@PathVariable Long carId, @PathVariable String startDate,
			@PathVariable String endDate, @PathVariable Integer passengers, @PathVariable String flight_res) {
		RegularUser user = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		Integer flights = 0;
		if (user.getFlightReservations() != null && !user.getFlightReservations().isEmpty()) {
			flights = user.getFlightReservations().size();

		}
		Integer cars = 0;
		if (user.getCarReservations() != null && !user.getCarReservations().isEmpty()) {
			cars = user.getCarReservations().size();
		}
		Integer hotels = 0;
		if (user.getRoomReservations() != null && !user.getRoomReservations().isEmpty()) {
			hotels = user.getRoomReservations().size();
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

		@SuppressWarnings("deprecation")
		Date startDatee = new Date(Integer.parseInt(startDate.split("\\-")[0]) - 1900,
				Integer.parseInt(startDate.split("\\-")[1]) - 1, Integer.parseInt(startDate.split("\\-")[2]));
		@SuppressWarnings("deprecation")
		Date endDatee = new Date(Integer.parseInt(endDate.split("\\-")[0]) - 1900,
				Integer.parseInt(endDate.split("\\-")[1]) - 1, Integer.parseInt(endDate.split("\\-")[2]));

		List<FlightReservation> flightRes = user.getFlightReservations();

		FlightReservation lastRes = null;
		if (flight_res.equals("1")) {
			lastRes = flightRes.get(0);
			for (FlightReservation fr : flightRes) {
				if (fr.getId() > lastRes.getId()) {
					lastRes = fr;
				}
			}
		}

		CarReservationDTO dto = new CarReservationDTO(carId, startDatee, endDatee, passengers);
		CarReservation newCarRes = new CarReservation(dto);
		Car car = null;
		try {
			car = carService.getOne(dto.getCarId());
		}catch(Exception e) {
			return null;
		}
				
		if (flight_res.equals("1")) {
			newCarRes.setFlightId(lastRes.getId());
		} else {
			newCarRes.setFlightId(Long.parseLong("-1"));
		}

		newCarRes.setCar(car);
		if (discount == 0) {
			newCarRes.setPrice(car.getPrice());
		} else {
			newCarRes.setPrice(car.getPrice() * (100 - discount) / 100);
		}

		newCarRes.setRegularUser(user);
		newCarRes.setNumOfPass(passengers);
		newCarRes.setDiscount(discount);

		Rentacar rentacar = rentacarService.getOne(car.getRentacar().getId());
		newCarRes.setRentacarRes(rentacar);

		carReservationService.save(newCarRes);

		user.getCarReservations().add(newCarRes);
		rentacar.getCarReservations().add(newCarRes);
		car.getReservations().add(newCarRes);

		return new ResponseEntity<>(newCarRes, HttpStatus.CREATED);
	}

	@SuppressWarnings("deprecation")
	@PostMapping(value = "/createCarReservationFast/{carId}/{startDate}/{endDate}/{flightRes}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<CarReservation> createCarReservationFast(@PathVariable Long carId,
			@PathVariable String startDate, @PathVariable String endDate, @PathVariable String flightRes) {
		RegularUser user = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		@SuppressWarnings("deprecation")
		Date startDatee = new Date(Integer.parseInt(startDate.split("\\-")[0]) - 1900,
				Integer.parseInt(startDate.split("\\-")[1]) - 1, Integer.parseInt(startDate.split("\\-")[2]));

		@SuppressWarnings("deprecation")
		Date endDatee = new Date(Integer.parseInt(endDate.split("\\-")[0]) - 1900,
				Integer.parseInt(endDate.split("\\-")[1]) - 1, Integer.parseInt(endDate.split("\\-")[2]));

		List<FlightReservation> flightRes2 = user.getFlightReservations();

		FlightReservation lastRes = null;
		if (flightRes.equals("1")) {
			lastRes = flightRes2.get(0);
			for (FlightReservation fr : flightRes2) {
				if (fr.getId() > lastRes.getId()) {
					lastRes = fr;
				}
			}
		}

		CarReservation newCarRes = new CarReservation(startDatee, endDatee);
		Car car = null;
		try {
			System.out.println("Okejjjj jee");
			car = carService.getOne(carId);
		}catch(Exception e) {
			System.out.println("Nije okejjjjj");
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
				
		
		
		boolean dozvola=true;
		for (CarReservation res : car.getReservations()) {
			
				if (res.getStartDate().compareTo(startDatee) <= 0 && res.getEndDate().compareTo(endDatee) >= 0 && startDatee.compareTo(res.getEndDate())<= 0) {
					dozvola = false;
				}
				if (res.getStartDate().compareTo(endDatee) <= 0 && res.getEndDate().compareTo(endDatee) >= 0) {
					dozvola = false;
				}
				if (startDatee.compareTo(res.getStartDate()) <= 0 && endDatee.compareTo(res.getEndDate())<= 0 && endDatee.compareTo(res.getStartDate())>= 0) {
					dozvola = false;
				}
			

		}
		if (dozvola == true) {
			if (flightRes.equals("1")) {
				newCarRes.setFlightId(lastRes.getId());
			} else {
				newCarRes.setFlightId(Long.parseLong("-1"));
			}

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
		}else {
			System.out.println("USLO U DOZVOLA JEDNAKO FALSE");
			newCarRes=null;
			return new ResponseEntity<>(null, HttpStatus.OK);
		}

		
	}

	@DeleteMapping(value = "/cancelCarReservation/{resId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> cancelCarReservation(@PathVariable Long resId) {
		RegularUser user = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		userDetailsService.saveUser(user);
		CarReservation carRes = carReservationService.getOne(resId);
		Car car = carRes.getCar();
		int brojac2 = -1;
		for (CarReservation cr : car.getReservations()) {
			brojac2++;
			if (cr.getId().equals(resId)) {
				car.getReservations().remove(brojac2);
			}
		}
		carService.save(car);
		carReservationService.delete(resId);

		return new ResponseEntity<>(carRes, HttpStatus.OK);

	}
}
