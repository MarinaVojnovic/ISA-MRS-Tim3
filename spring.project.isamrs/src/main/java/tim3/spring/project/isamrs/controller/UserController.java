package tim3.spring.project.isamrs.controller;

import java.security.Principal;
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
import tim3.spring.project.isamrs.service.UserService;
import tim3.spring.project.isamrs.service.impl.CustomUserDetailsService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@RequestMapping(method = RequestMethod.GET, value = "/getLogged", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getLogged() {
		User user = (User) this.userDetailsService.loadUserByUsername(
				((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
		UserDTO retVal = new UserDTO(user.getUsername(), "", user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getPhoneNumber());
		return new ResponseEntity<UserDTO>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/editUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> editUser(@RequestBody UserDTO userEdit) {
		User user = (User) this.userDetailsService.loadUserByUsername(
				((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
		
		user.setPassword(this.userDetailsService.encodePassword(userEdit.getPassword()));
		user.setFirstName(userEdit.getFirstName());
		user.setLastName(userEdit.getLastName());
		user.setEmail(userEdit.getEmail());
		user.setPhoneNumber(userEdit.getPhoneNumber());	
		
		if (this.userDetailsService.saveUser(user)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
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

	/*
	 * We are not using userService.findByUsername here(we could), so it is good
	 * that we are making sure that the user has role "ROLE_USER" to access this
	 * endpoint.
	 */
	@RequestMapping("/whoami")
	@PreAuthorize("hasRole('ROLE_USER')")
	public User user(Principal user) {
		return this.userService.findByUsername(user.getName());
	}
}
