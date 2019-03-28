package tim3.spring.project.isamrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.dto.CarDTO;
import tim3.spring.project.isamrs.model.Car;
import tim3.spring.project.isamrs.service.CarService;

@RestController
public class CarController {
	
	@Autowired
	CarService carService;

	@RequestMapping(value = "/createCar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Car> create(@RequestBody CarDTO carDTO) {
		Car retVal = carService.create(new Car(carDTO));
		System.out.println("POSTSSSS");
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

}
