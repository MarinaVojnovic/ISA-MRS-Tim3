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
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.dto.MessageDTO;
import tim3.spring.project.isamrs.dto.UserDTO;
import tim3.spring.project.isamrs.model.Airline;
import tim3.spring.project.isamrs.model.AirlineAdmin;
import tim3.spring.project.isamrs.model.Authority;
import tim3.spring.project.isamrs.model.Hotel;
import tim3.spring.project.isamrs.model.HotelAdmin;
import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.Rentacar;
import tim3.spring.project.isamrs.model.RentacarAdmin;
import tim3.spring.project.isamrs.model.User;
import tim3.spring.project.isamrs.model.UserRoleName;
import tim3.spring.project.isamrs.model.UserTokenState;
import tim3.spring.project.isamrs.security.TokenHelper;
import tim3.spring.project.isamrs.security.auth.JwtAuthenticationRequest;
import tim3.spring.project.isamrs.service.AirlineService;
import tim3.spring.project.isamrs.service.HotelService;
import tim3.spring.project.isamrs.service.RentacarService;
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
	private AirlineService airlineService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private RentacarService rentacarService;

	@PostMapping(value = "auth/registerAirlineAdmin")
	public ResponseEntity<?> registerAirlineAdmin(@RequestBody UserDTO user) {
		if (this.userDetailsService.usernameTaken(user.getUsername())) {
			return new ResponseEntity<>(new MessageDTO("Username is already taken.", "Error"), HttpStatus.OK);
		}

		AirlineAdmin aa = new AirlineAdmin();
		aa.setUsername(user.getUsername());
		aa.setId(null);
		aa.setEmail(user.getEmail());
		aa.setPassword(this.userDetailsService.encodePassword(user.getPassword()));
		List<Authority> authorities = new ArrayList<>();
		Authority a = new Authority();
		a.setName(UserRoleName.ROLE_AIRLINE_ADMIN);
		authorities.add(a);
		aa.setAuthorities(authorities);
		aa.setEnabled(true);
		aa.setFirstName(user.getFirstName());
		aa.setLastName(user.getLastName());
		aa.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
		aa.setPhoneNumber(user.getPhoneNumber());
		aa.setFirstTime(true);

		Airline airline = airlineService.getOne(Long.parseLong(user.getAdminId()));
		aa.setAirline(airline);
		airline.getAirlineAdmins().add(aa);

		if (this.userDetailsService.saveUser(aa)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.OK);
	}

	@PostMapping(value = "auth/registerHotelAdmin")
	public ResponseEntity<?> registerHotelAdmin(@RequestBody UserDTO user) {
		if (this.userDetailsService.usernameTaken(user.getUsername())) {
			return new ResponseEntity<>(new MessageDTO("Username is already taken.", "Error"), HttpStatus.OK);
		}

		HotelAdmin ha = new HotelAdmin();
		ha.setUsername(user.getUsername());
		ha.setId(null);
		ha.setEmail(user.getEmail());
		ha.setPassword(this.userDetailsService.encodePassword(user.getPassword()));
		List<Authority> authorities = new ArrayList<>();
		Authority a = new Authority();
		a.setName(UserRoleName.ROLE_HOTEL_ADMIN);
		authorities.add(a);
		ha.setAuthorities(authorities);
		ha.setEnabled(true);
		ha.setFirstName(user.getFirstName());
		ha.setLastName(user.getLastName());
		ha.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
		ha.setPhoneNumber(user.getPhoneNumber());
		ha.setFirstTime(true);

		Hotel hotel = hotelService.getOne(Long.parseLong(user.getAdminId()));
		ha.setHotel(hotel);
		hotel.getHotelAdmins().add(ha);

		if (this.userDetailsService.saveUser(ha)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.OK);
	}

	@PostMapping(value = "auth/registerRentacarAdmin")
	public ResponseEntity<?> registerRentacarAdmin(@RequestBody UserDTO user) {
		if (this.userDetailsService.usernameTaken(user.getUsername())) {
			return new ResponseEntity<>(new MessageDTO("Username is already taken.", "Error"), HttpStatus.OK);
		}

		RentacarAdmin ra = new RentacarAdmin();
		ra.setUsername(user.getUsername());
		ra.setId(null);
		ra.setEmail(user.getEmail());
		ra.setPassword(this.userDetailsService.encodePassword(user.getPassword()));
		List<Authority> authorities = new ArrayList<>();
		Authority a = new Authority();
		a.setName(UserRoleName.ROLE_RENTACAR_ADMIN);
		authorities.add(a);
		ra.setAuthorities(authorities);
		ra.setEnabled(true);
		ra.setFirstName(user.getFirstName());
		ra.setLastName(user.getLastName());
		ra.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
		ra.setPhoneNumber(user.getPhoneNumber());
		ra.setFirstTime(true);

		Rentacar rentacar = rentacarService.getOne(Long.parseLong(user.getAdminId()));
		ra.setRentacar(rentacar);
		rentacar.getRentacarAdmins().add(ra);

		if (this.userDetailsService.saveUser(ra)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.OK);
	}

	@PostMapping(value = "auth/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO user) {
		if (this.userDetailsService.usernameTaken(user.getUsername())) {
			return new ResponseEntity<>(new MessageDTO("Username is already taken.", "Error"), HttpStatus.OK);
		}

		RegularUser newUser = new RegularUser();
		newUser.setUsername(user.getUsername());
		newUser.setId(null);
		newUser.setEmail(user.getEmail());
		newUser.setPassword(this.userDetailsService.encodePassword(user.getPassword()));
		List<Authority> authorities = new ArrayList<>();
		Authority a = new Authority();
		a.setName(UserRoleName.ROLE_USER);
		authorities.add(a);
		newUser.setAuthorities(authorities);
		newUser.setEnabled(false);
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
		newUser.setPhoneNumber(user.getPhoneNumber());
		newUser.setFirstTime(true);

		if (this.userDetailsService.saveUser(newUser)) {
			return new ResponseEntity<>(newUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping(value = "auth/registerSystemAdmin")
	public ResponseEntity<?> registerSystemAdmin(@RequestBody UserDTO user) {
		if (this.userDetailsService.usernameTaken(user.getUsername())) {
			return new ResponseEntity<>(new MessageDTO("Username is already taken.", "Error"), HttpStatus.OK);
		}

		User sa = new User();
		sa.setUsername(user.getUsername());
		sa.setId(null);
		sa.setEmail(user.getEmail());
		sa.setPassword(this.userDetailsService.encodePassword(user.getPassword()));
		List<Authority> authorities = new ArrayList<>();
		Authority a = new Authority();
		a.setName(UserRoleName.ROLE_SYSTEM_ADMIN);
		authorities.add(a);
		sa.setAuthorities(authorities);
		sa.setEnabled(true);
		sa.setFirstName(user.getFirstName());
		sa.setLastName(user.getLastName());
		sa.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
		sa.setPhoneNumber(user.getPhoneNumber());
		sa.setFirstTime(true);

		if (this.userDetailsService.saveUser(sa)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.OK);
	}

	@PostMapping(value = "auth/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) throws AuthenticationException, IOException {

		final Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>(new MessageDTO("Wrong username or password.", "Error"), HttpStatus.OK);
		} catch (DisabledException e) {
			return new ResponseEntity<>(new MessageDTO("Account is not verified. Check your email.", "Error"),
					HttpStatus.OK);
		}
		User user = (User) authentication.getPrincipal();

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
		} else if (user instanceof RegularUser) {
			userType = UserRoleName.ROLE_USER;
		} else {
			userType = UserRoleName.ROLE_SYSTEM_ADMIN;
		}

		// Vrati token kao odgovor na uspesno autentifikaciju
		return new ResponseEntity<>(new UserTokenState(jwt, expiresIn, userType), HttpStatus.OK);
	}
}