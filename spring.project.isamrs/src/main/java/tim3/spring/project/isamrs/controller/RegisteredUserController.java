package tim3.spring.project.isamrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.dto.CarDTO;
import tim3.spring.project.isamrs.dto.RegisteredUserDTO;
import tim3.spring.project.isamrs.model.Car;
import tim3.spring.project.isamrs.model.RegisteredUser;
import tim3.spring.project.isamrs.service.CarService;
import tim3.spring.project.isamrs.service.RegisteredUserService;

@RestController
public class RegisteredUserController {

	@Autowired
	RegisteredUserService registeredUserService;

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RegisteredUser> create(@RequestBody RegisteredUser registeredUser) {
		System.out.println("Register user called");
		System.out.println("Name: "+registeredUser.getName());
		System.out.println("Surname: "+registeredUser.getSurname());
		System.out.println("Email: "+registeredUser.getEmail());
		System.out.println("username: "+registeredUser.getUsername());
		System.out.println("password: "+registeredUser.getPassword());
		RegisteredUser user = registeredUserService.create(registeredUser);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RegisteredUser> loginUser(@RequestBody RegisteredUserDTO userDto) {
		System.out.println("Uslo u login user");
		
		RegisteredUser user = registeredUserService.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
		if (user != null) {
			System.out.println("Nadjen user");
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			
			return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
		}
		
		
		//RegisteredUser user =  new RegisteredUser();
		
	}
}
