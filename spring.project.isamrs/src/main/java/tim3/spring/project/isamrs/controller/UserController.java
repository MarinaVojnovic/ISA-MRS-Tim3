package tim3.spring.project.isamrs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.dto.UserDTO;
import tim3.spring.project.isamrs.model.User;
import tim3.spring.project.isamrs.model.UserRoleName;
import tim3.spring.project.isamrs.model.UserTokenState;
import tim3.spring.project.isamrs.security.TokenHelper;
import tim3.spring.project.isamrs.service.UserService;
import tim3.spring.project.isamrs.service.impl.CustomUserDetailsService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	TokenHelper tokenUtils;

	@Autowired
	private UserService userService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@RequestMapping(value = "/confirmRegistration/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> showRentACars(@PathVariable Long id) {
		System.out.println("Uslo u confirm message registration.");
		System.out.println("Id: " + id);
		User user = (User) userDetailsService.loadUserById(id);
		System.out.println(user.getFirstName());
		user.setEnabled(true);
		userService.save(user);
		return new ResponseEntity<>(
				"You have successfully confired your registration. You can now go to login page and use application.",
				HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/getLogged", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getLogged() {
		User user = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		UserDTO retVal = new UserDTO(user.getUsername(), "", user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getPhoneNumber());
		return new ResponseEntity<UserDTO>(retVal, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/editUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserTokenState> editUser(@RequestBody UserDTO userEdit) {
		User user = (User) this.userDetailsService.loadUserByUsername(userEdit.getUsername());

		user.setPassword(this.userDetailsService.encodePassword(userEdit.getPassword()));
		user.setFirstName(userEdit.getFirstName());
		user.setLastName(userEdit.getLastName());
		user.setEmail(userEdit.getEmail());
		user.setPhoneNumber(userEdit.getPhoneNumber());
		this.userDetailsService.saveUser(user);

		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();
		UserRoleName userType = null;

		return new ResponseEntity<UserTokenState>(new UserTokenState(jwt, expiresIn, userType), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/all")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<User> loadAll() {
		return this.userService.findAll();
	}
}
