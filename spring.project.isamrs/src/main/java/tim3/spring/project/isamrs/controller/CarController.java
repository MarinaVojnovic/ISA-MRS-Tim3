package tim3.spring.project.isamrs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.dto.CarDTO;
import tim3.spring.project.isamrs.model.Car;
import tim3.spring.project.isamrs.model.RentacarAdmin;
import tim3.spring.project.isamrs.model.User;
import tim3.spring.project.isamrs.service.CarService;
import tim3.spring.project.isamrs.service.UserService;
import tim3.spring.project.isamrs.service.impl.CustomUserDetailsService;

@RestController
public class CarController {
	
	@Autowired
	CarService carService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@RequestMapping(value = "/createCar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Car> create(@RequestBody CarDTO carDTO) {
		System.out.println("Uslo u create car");
		RentacarAdmin user = (RentacarAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println("HEJ");
		System.out.println(user.getUsername());
		Car newCar=new Car(carDTO);
		newCar.setRentacar(user.getRentacar());
		Car retVal = carService.create(newCar);
		user.getRentacar().getCars().add(retVal);
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}
	@RequestMapping(value="/getCars",method = RequestMethod.GET)
	public ResponseEntity<List<Car>> getCars() {
		System.out.println("Get cars pozvano");
		//List<Car> cars = carService.getAll();
		RentacarAdmin ra = (RentacarAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(ra.getUsername());
		List<Car> cars=carService.findByRentacar(ra.getRentacar());
		System.out.println("proslo find by rentacar");
		return new ResponseEntity<>(cars, HttpStatus.OK); 
	}
	
	@RequestMapping(value="/deleteCar/{carId}")
	public ResponseEntity<Car> deleteCar(@PathVariable String carId){
		System.out.println("Uslo u deleting car");
		System.out.println(Long.parseLong(carId)+1);
		Car car = carService.getOne(Long.parseLong(carId));
		if (car == null) {
			System.out.println("uslo u null deo");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		carService.delete(Long.parseLong(carId));
		return new ResponseEntity<>(car, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findCar/{carId}", method = RequestMethod.GET)
	public ResponseEntity<Car> findCar(@PathVariable long carId) {
		System.out.println("Uslo u find car");
		Car car = carService.getOne(carId);
		if (car != null) {
			return new ResponseEntity<>(car, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/saveEditedCar", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
