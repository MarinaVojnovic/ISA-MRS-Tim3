package tim3.spring.project.isamrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.dto.CarDTO;
import tim3.spring.project.isamrs.model.Car;
import tim3.spring.project.isamrs.service.CarService;
import tim3.spring.project.isamrs.service.RentacarService;

@RestController
public class CarController {
	
	@Autowired
	CarService carService;

	@RequestMapping(value = "/createCar", method = RequestMethod.POST)
	public ResponseEntity<Car> create(@RequestBody CarDTO carDTO) {
		System.out.println("Create car called on baaaaaaaaaaaack");
		Car retVal = carService.create(new Car(carDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

}
