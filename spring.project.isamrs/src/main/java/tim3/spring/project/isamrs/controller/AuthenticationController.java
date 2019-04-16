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
import tim3.spring.project.isamrs.model.HotelAdmin;
import tim3.spring.project.isamrs.model.RentacarAdmin;
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

	@RequestMapping(value = "auth/registerAirlineAdmin", method = RequestMethod.POST)
	public ResponseEntity<?> registerAirlineAdmin(@RequestBody UserDTO user) {
		if (this.userDetailsService.usernameTaken(user.getUsername()) == true) {
			return new ResponseEntity<MessageDTO>(new MessageDTO("Username is already taken.", "Error"), HttpStatus.OK);
		}

		AirlineAdmin aa = new AirlineAdmin();
		aa.setUsername(user.getUsername());
		aa.setId(null);
		aa.setEmail(user.getEmail());
		aa.setPassword(this.userDetailsService.encodePassword(user.getPassword()));
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setName(UserRoleName.ROLE_AIRLINE_ADMIN);
		authorities.add(a);
		aa.setAuthorities(authorities);
		aa.setEnabled(true);
		aa.setFirstName(user.getFirstName());
		aa.setLastName(user.getLastName());
		aa.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
		aa.setPhoneNumber(user.getPhoneNumber());
		aa.setAirline(null);

		if (this.userDetailsService.saveUser(aa)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	@RequestMapping(value = "auth/registerHotelAdmin", method = RequestMethod.POST)
	public ResponseEntity<?> registerHotelAdmin(@RequestBody UserDTO user) {
		if (this.userDetailsService.usernameTaken(user.getUsername()) == true) {
			return new ResponseEntity<MessageDTO>(new MessageDTO("Username is already taken.", "Error"), HttpStatus.OK);
		}

		HotelAdmin ha = new HotelAdmin();
		ha.setUsername(user.getUsername());
		ha.setId(null);
		ha.setEmail(user.getEmail());
		ha.setPassword(this.userDetailsService.encodePassword(user.getPassword()));
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setName(UserRoleName.ROLE_HOTEL_ADMIN);
		authorities.add(a);
		ha.setAuthorities(authorities);
		ha.setEnabled(true);
		ha.setFirstName(user.getFirstName());
		ha.setLastName(user.getLastName());
		ha.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
		ha.setPhoneNumber(user.getPhoneNumber());
		ha.setHotel(null);

		if (this.userDetailsService.saveUser(ha)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	@RequestMapping(value = "auth/registerRentacarAdmin", method = RequestMethod.POST)
	public ResponseEntity<?> registerRentacarAdmin(@RequestBody UserDTO user) {
		if (this.userDetailsService.usernameTaken(user.getUsername()) == true) {
			return new ResponseEntity<MessageDTO>(new MessageDTO("Username is already taken.", "Error"), HttpStatus.OK);
		}

		RentacarAdmin ra = new RentacarAdmin();
		ra.setUsername(user.getUsername());
		ra.setId(null);
		ra.setEmail(user.getEmail());
		ra.setPassword(this.userDetailsService.encodePassword(user.getPassword()));
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setName(UserRoleName.ROLE_RENTACAR_ADMIN);
		authorities.add(a);
		ra.setAuthorities(authorities);
		ra.setEnabled(true);
		ra.setFirstName(user.getFirstName());
		ra.setLastName(user.getLastName());
		ra.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
		ra.setPhoneNumber(user.getPhoneNumber());
		ra.setRentacar(null);

		if (this.userDetailsService.saveUser(ra)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	@RequestMapping(value = "auth/registerSystemAdmin", method = RequestMethod.POST)
	public ResponseEntity<?> registerSystemAdmin(@RequestBody UserDTO user) {
		if (this.userDetailsService.usernameTaken(user.getUsername()) == true) {
			return new ResponseEntity<MessageDTO>(new MessageDTO("Username is already taken.", "Error"), HttpStatus.OK);
		}

		User sa = new User();
		sa.setUsername(user.getUsername());
		sa.setId(null);
		sa.setEmail(user.getEmail());
		sa.setPassword(this.userDetailsService.encodePassword(user.getPassword()));
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setName(UserRoleName.ROLE_SYSTEM_ADMIN);
		authorities.add(a);
		sa.setAuthorities(authorities);
		sa.setEnabled(true);
		sa.setFirstName(user.getFirstName());
		sa.setLastName(user.getLastName());
		sa.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
		sa.setPhoneNumber(user.getPhoneNumber());

		if (this.userDetailsService.saveUser(sa)) {
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
			return new ResponseEntity<MessageDTO>(new MessageDTO("Wrong username or password.", "Error"),
					HttpStatus.OK);
		}
		User user = (User) authentication.getPrincipal();

		/*
		 * ZA EMAIL if (!user.isEnabled()) { return new ResponseEntity<MessageDTO>(new
		 * MessageDTO("Account is not verified. Check your email.", "Error"),
		 * HttpStatus.OK); }
		 */
		// Ubaci username + password u kontext
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();
		UserRoleName userType = null;

		if (user instanceof HotelAdmin) {
			userType = UserRoleName.ROLE_HOTEL_ADMIN;
		} else if (user instanceof RentacarAdmin) {
			userType = UserRoleName.ROLE_RENTACAR_ADMIN;
		} else if (user instanceof AirlineAdmin) {
			userType = UserRoleName.ROLE_AIRLINE_ADMIN;
		} else {
			userType = UserRoleName.ROLE_SYSTEM_ADMIN;
		}

		// Vrati token kao odgovor na uspesno autentifikaciju
		return new ResponseEntity<UserTokenState>(new UserTokenState(jwt, expiresIn, userType), HttpStatus.OK);
	}
}