package tim3.spring.project.isamrs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.dto.CarDTO;
import tim3.spring.project.isamrs.model.Car;
import tim3.spring.project.isamrs.model.RentacarAdmin;
import tim3.spring.project.isamrs.service.CarService;
import tim3.spring.project.isamrs.service.impl.CustomUserDetailsService;

@RestController
public class CarController {

	@Autowired
	CarService carService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@PostMapping(value = "/createCar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Car> create(@RequestBody CarDTO carDTO) {
		RentacarAdmin user = (RentacarAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Car newCar = new Car(carDTO);
		newCar.setRentacar(user.getRentacar());
		Car retVal = carService.create(newCar);
		user.getRentacar().getCars().add(retVal);
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getCars")
	public ResponseEntity<List<Car>> getCars() {
		RentacarAdmin ra = (RentacarAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Car> cars = carService.findByRentacar(ra.getRentacar());
		return new ResponseEntity<>(cars, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteCar/{carId}")
	public ResponseEntity<Car> deleteCar(@PathVariable String carId) {
		Car car = carService.getOne(Long.parseLong(carId));
		if (car == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		carService.delete(Long.parseLong(carId));
		return new ResponseEntity<>(car, HttpStatus.OK);
	}

	@GetMapping(value = "/findCar/{carId}")
	public ResponseEntity<Car> findCar(@PathVariable long carId) {
		Car car = carService.getOne(carId);
		if (car != null) {
			return new ResponseEntity<>(car, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/saveEditedCar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Car> saveChangesRentACar(@RequestBody Car car) {
		Car c = carService.getOne(car.getId());
		if (c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		c.setId(car.getId());
		c.setName(car.getName());
		c.setPrice(car.getPrice());
		c.setYear(car.getYear());

		Car editedCar = carService.save(c);
		return new ResponseEntity<>(editedCar, HttpStatus.OK);
	}

}
