package tim3.spring.project.isamrs.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.dto.MessageDTO;
import tim3.spring.project.isamrs.dto.UserDTO;
import tim3.spring.project.isamrs.model.AirlineAdmin;
import tim3.spring.project.isamrs.model.Authority;
import tim3.spring.project.isamrs.model.User;
import tim3.spring.project.isamrs.model.UserRoleName;
import tim3.spring.project.isamrs.model.UserTokenState;
import tim3.spring.project.isamrs.security.TokenHelper;
import tim3.spring.project.isamrs.security.auth.JwtAuthenticationRequest;
import tim3.spring.project.isamrs.service.UserService;
import tim3.spring.project.isamrs.service.impl.CustomUserDetailsService;

@RestController
public class AuthenticationController {
	@Autowired
	TokenHelper tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	/*
	@RequestMapping(value = "auth/register", method = RequestMethod.POST)
	public ResponseEntity<?> register(@Valid @RequestBody User user) {
		if (this.userDetailsService.usernameTaken(user.getEmail()) == true) {
			return new ResponseEntity<MessageDTO>(new MessageDTO("Email is taken.", "Error"), HttpStatus.OK);
		}

		RegisteredUser ru = new RegisteredUser();
		ru.setId(null);
		ru.setEmail(user.getEmail());
		ru.setPassword(this.userDetailsService.encodePassword(user.getPassword()));
		ru.setAddress(user.getAddress());
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setType(UserType.ROLE_REGISTEREDUSER);
		authorities.add(a);
		ru.setAuthorities(authorities);
		ru.setDiscountPoints(0);
		ru.setEnabled(false);
		ru.setFriends(new HashSet<RegisteredUser>());
		ru.setFirstName(user.getFirstName());
		ru.setLastName(user.getLastName());
		ru.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
		ru.setPhoneNumber(user.getPhoneNumber());
		ru.setUserReservations(new HashSet<UserReservation>());
		ru.setServiceGrades(new HashSet<ServiceGrade>());

		if (this.userDetailsService.saveUser(ru)) {
			mailService.sendMailAsync(ru);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "auth/registerAirlineAdmin", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody UserDTO user) {
		if (this.userDetailsService.usernameTaken(user.getUsername()) == true) {
			return new ResponseEntity<MessageDTO>(new MessageDTO("Username is already taken.", "Error"), HttpStatus.OK);
		}
		
		AirlineAdmin aa = new AirlineAdmin();
		aa.setUsername(user.getUsername());
		aa.setId(null);
		aa.setEmail(user.getEmail());
		aa.setPassword(this.userDetailsService.encodePassword(user.getPassword()));
		//ru.setAddress(user.getAddress());
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setName(UserRoleName.ROLE_AIRLINE_ADMIN);
		authorities.add(a);
		aa.setAuthorities(authorities);
		//ru.setDiscountPoints(0);
		aa.setEnabled(false);
		//ru.setFriends(new HashSet<RegisteredUser>());
		aa.setFirstName(user.getFirstName());
		aa.setLastName(user.getLastName());
		aa.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
		aa.setPhoneNumber(user.getPhoneNumber());
		//ru.setUserReservations(new HashSet<UserReservation>());
		//ru.setServiceGrades(new HashSet<ServiceGrade>());
		aa.setAirline(null);

		if (this.userDetailsService.saveUser(aa)) {
			//mailService.sendMailAsync(ru);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	@RequestMapping(value = "auth/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) throws AuthenticationException, IOException {

		final Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return new ResponseEntity<MessageDTO>(new MessageDTO("Wrong email or password.", "Error"), HttpStatus.OK);
		}
		User user = (User) authentication.getPrincipal();

		/*  ZA EMAIL
		if (!user.isEnabled()) {
			return new ResponseEntity<MessageDTO>(new MessageDTO("Account is not verified. Check your email.", "Error"),
					HttpStatus.OK);
		}
		*/
		// Ubaci username + password u kontext
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();
		UserRoleName userType = null;

		/*  ISPRAVITITITITITII
		if (user instanceof RegisteredUser) {
			userType = UserType.ROLE_REGISTEREDUSER;
		} else if (user instanceof HotelAdmin) {
			userType = UserType.ROLE_HOTELADMIN;
		} else if (user instanceof RentACarAdmin) {
			userType = UserType.ROLE_RENTADMIN;
		} else if (user instanceof AirlineAdmin) {
			userType = UserType.ROLE_AIRADMIN;
		} else {
			userType = UserType.ROLE_SYSADMIN;
		}*/

		// Vrati token kao odgovor na uspesno autentifikaciju
		return new ResponseEntity<UserTokenState>(new UserTokenState(jwt, expiresIn, userType), HttpStatus.OK);
	}
}