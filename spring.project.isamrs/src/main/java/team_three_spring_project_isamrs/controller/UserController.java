package team_three_spring_project_isamrs.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import team_three_spring_project_isamrs.comparator.FriendsComparatorNameSurname;
import team_three_spring_project_isamrs.dto.FastReservationDTO;
import team_three_spring_project_isamrs.dto.FinishedReservationDTO;
import team_three_spring_project_isamrs.dto.FlightReservationDTO;
import team_three_spring_project_isamrs.dto.FlightReservationReturnDTO;
import team_three_spring_project_isamrs.dto.FriendRequestDTO;
import team_three_spring_project_isamrs.dto.InvitedFriendDTO;
import team_three_spring_project_isamrs.dto.PasswordDTO;
import team_three_spring_project_isamrs.dto.RoomReservationDTO;
import team_three_spring_project_isamrs.dto.UserDTO;
import team_three_spring_project_isamrs.model.CarReservation;
import team_three_spring_project_isamrs.model.Flight;
import team_three_spring_project_isamrs.model.FlightReservation;
import team_three_spring_project_isamrs.model.FriendRequest;
import team_three_spring_project_isamrs.model.HotelReservation;
import team_three_spring_project_isamrs.model.RegularUser;
import team_three_spring_project_isamrs.model.RoomReservation;
import team_three_spring_project_isamrs.model.Seat;
import team_three_spring_project_isamrs.model.User;
import team_three_spring_project_isamrs.model.UserRoleName;
import team_three_spring_project_isamrs.model.UserTokenState;
import team_three_spring_project_isamrs.security.TokenHelper;
import team_three_spring_project_isamrs.service.CarReservationService;
import team_three_spring_project_isamrs.service.FlightReservationService;
import team_three_spring_project_isamrs.service.FlightService;
import team_three_spring_project_isamrs.service.FriendRequestService;
import team_three_spring_project_isamrs.service.RegularUserService;
import team_three_spring_project_isamrs.service.RoomReservationService;
import team_three_spring_project_isamrs.service.SeatService;
import team_three_spring_project_isamrs.service.UserService;
import team_three_spring_project_isamrs.service.impl.CustomUserDetailsService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	CarReservationService carReservationService;

	@Autowired
	TokenHelper tokenUtils;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private RegularUserService regularUserService;

	@Autowired
	private FriendRequestService friendRequestService;

	@Autowired
	private FlightReservationService flightReservationService;

	@Autowired
	private RoomReservationService roomReservationService;

	@Autowired
	private SeatService seatService;

	@Autowired
	private FlightService flightService;

	@GetMapping(value = "/confirmRegistration/{id}")
	public RedirectView confirmRegistration(@PathVariable Long id) {
		User user = (User) userDetailsService.loadUserById(id);
		if (user != null) {
			user.setEnabled(true);
			userDetailsService.saveUser(user);
			return new RedirectView("http://localhost:8080/confirmedAccount.html");
		}
		return null;
	}

	@GetMapping(value = "/getLogged", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getLogged() {
		User user = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		UserDTO retVal = new UserDTO(user.getUsername(), "", user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getPhoneNumber(), "");
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@PutMapping(value = "/editUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

		return new ResponseEntity<>(new UserTokenState(jwt, expiresIn, userType), HttpStatus.OK);
	}

	@GetMapping(value = "/findFriends/{name}/{surname}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<RegularUser>> findFriends(@PathVariable String name, @PathVariable String surname) {
		List<RegularUser> users;
		if (surname.equals("no_surname")) {
			users = this.userService.findByFirstName(name);
		} else {
			users = this.userService.findByFirstNameAndLastName(name, surname);
		}
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		for (RegularUser u : users) {
			if (u.getUsername().equals(logged.getUsername())) {
				users.remove(u);
				break;
			}
		}

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping(value = "/user/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	@PostMapping(value = "/sendFriendRequest/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<FriendRequest> sendFriendRequest(@PathVariable Long userId) {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		RegularUser ru = this.regularUserService.findById(userId);

		FriendRequest retVal = friendRequestService
				.create(new FriendRequest(new FriendRequestDTO((RegularUser) logged, ru, false)));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);

	}

	@GetMapping(value = "/getFriendRequests")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<FriendRequest>> getFriendRequest() {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<FriendRequest> users = friendRequestService.findByReceivedAndAccepted((RegularUser) logged, false);

		return new ResponseEntity<>(users, HttpStatus.OK);

	}

	@PutMapping(value = "/acceptFriend/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<FriendRequest> acceptFriendRequest(@PathVariable Long userId) {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		RegularUser ru = this.regularUserService.findById(userId);
		FriendRequest fr = this.friendRequestService.findByReceivedAndSentAndAccepted((RegularUser) logged,
				(RegularUser) ru, false);
		fr.setAccepted(true);
		this.friendRequestService.save(fr);

		return new ResponseEntity<>(fr, HttpStatus.OK);

	}

	@DeleteMapping(value = "/rejectFriend/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<FriendRequest> rejectFriendRequest(@PathVariable Long userId) {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		RegularUser ru = this.regularUserService.findById(userId);
		FriendRequest fr = this.friendRequestService.findByReceivedAndSentAndAccepted((RegularUser) logged,
				(RegularUser) ru, false);
		this.friendRequestService.delete(fr.getId());
		return new ResponseEntity<>(fr, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllFriends")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<RegularUser>> getAllFriends() {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<RegularUser> regular = new ArrayList<>();
		List<FriendRequest> friends = this.friendRequestService.findByReceivedAndAccepted((RegularUser) logged, true);
		for (FriendRequest f : friends) {
			regular.add(f.getSent());
		}
		List<FriendRequest> friends2 = this.friendRequestService.findBySentAndAccepted((RegularUser) logged, true);
		for (FriendRequest f : friends2) {
			regular.add(f.getReceived());
		}
		Collections.sort(regular, new FriendsComparatorNameSurname());
		return new ResponseEntity<>(regular, HttpStatus.OK);
	}

	@GetMapping(value = "/getMyResCars")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<CarReservation>> getMyResCars() {
		RegularUser logged = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<CarReservation> res = carReservationService.findByRegularUser(logged);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping(value = "/getMyResHotels")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<RoomReservationDTO>> getMyResHotels() {
		RegularUser logged = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<RoomReservation> res = new ArrayList<RoomReservation>();
		res = roomReservationService.findByRegularUser(logged);
		List<RoomReservationDTO> resDto = new ArrayList<>();
		for (RoomReservation r : res) {
			RoomReservationDTO dto = new RoomReservationDTO(r);
			resDto.add(dto);
		}
		return new ResponseEntity<>(resDto, HttpStatus.OK);
	}

	@GetMapping(value = "/getMyResFlights")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<FlightReservationDTO>> getMyResFlights() {
		RegularUser logged = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		List<FlightReservation> res = flightReservationService.findByNameAndLastName(logged.getFirstName(),
				logged.getLastName());

		List<FlightReservationDTO> resDTO = new ArrayList<>();
		for (FlightReservation fr : res) {
			FlightReservationDTO dto = new FlightReservationDTO(fr);
			resDTO.add(dto);
		}
		return new ResponseEntity<>(resDTO, HttpStatus.OK);

	}

	@GetMapping(value = "/getAllFriendRequests")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<FriendRequest>> getAllFriendRequests() {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<FriendRequest> friends = this.friendRequestService.findBySentAndAccepted((RegularUser) logged, false);
		List<FriendRequest> friend2 = this.friendRequestService.findBySentAndAccepted((RegularUser) logged, true);
		List<FriendRequest> friend3 = this.friendRequestService.findByReceivedAndAccepted((RegularUser) logged, true);
		for (FriendRequest f : friend2) {
			friends.add(f);
		}
		for (FriendRequest f : friend3) {
			friends.add(f);
		}
		return new ResponseEntity<>(friends, HttpStatus.OK);
	}

	@DeleteMapping(value = "/removeFriend/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<FriendRequest> removeFriend(@PathVariable Long userId) {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		RegularUser ru = this.regularUserService.findById(userId);
		FriendRequest fr = this.friendRequestService.findByReceivedAndSentAndAccepted((RegularUser) logged, ru, true);
		if (fr == null) {
			fr = this.friendRequestService.findByReceivedAndSentAndAccepted(ru, (RegularUser) logged, true);
		}
		this.friendRequestService.delete(fr.getId());
		return new ResponseEntity<>(fr, HttpStatus.OK);
	}

	@GetMapping(value = "/user/all")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<User> loadAll() {
		return this.userService.findAll();
	}

	@GetMapping(value = "/isFirstTime")
	public ResponseEntity<Boolean> isFirstTime() {
		User user = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		if (user.getFirstTime()) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.OK);
	}

	@PutMapping(value = "/changePasswordFirstTime", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserTokenState> changePasswordFirstTime(@RequestBody PasswordDTO userEdit) {
		User user = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		user.setFirstTime(false);
		user.setPassword(this.userDetailsService.encodePassword(userEdit.getPassword()));

		this.userDetailsService.saveUser(user);

		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();
		UserRoleName userType = null;

		return new ResponseEntity<>(new UserTokenState(jwt, expiresIn, userType), HttpStatus.OK);
	}

	@PostMapping(value = "/makeReservation")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<FlightReservationReturnDTO> makeReservation(
			@RequestBody FlightReservationDTO flightReservation) {
		int brPasosa = Integer.parseInt(flightReservation.getPassportNum());
		String idjeviPutnika = flightReservation.getUsers();
		String sedista = flightReservation.getSeats();
		Long let = Long.valueOf(flightReservation.getFlight());
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		RegularUser user = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		Integer flights = 0;
		if (user.getFlightReservations() != null && user.getFlightReservations().size() != 0) {
			flights = user.getFlightReservations().size();

		}
		Integer cars = 0;
		if (user.getCarReservations() != null && user.getCarReservations().size() != 0) {
			cars = user.getCarReservations().size();
		}
		Integer hotels = 0;
		if (user.getRoomReservations() != null && user.getRoomReservations().size() != 0) {
			hotels = user.getRoomReservations().size();
		}

		Integer discount;
		Integer numberOfRes = flights + cars + hotels;
		if (numberOfRes >= 3 && numberOfRes < 10) {
			discount = 5;
		} else if (numberOfRes >= 10 && numberOfRes < 30) {
			discount = 10;
		} else if (numberOfRes >= 30 && numberOfRes < 100) {
			discount = 20;
		} else if (numberOfRes >= 100) {
			discount = 40;
		} else {
			discount = 0;
		}

		Flight fl = this.flightService.getOne(let);
		List<FlightReservation> flightReservations = new ArrayList<>();
		List<InvitedFriendDTO> invited = new ArrayList<>();
		FlightReservation fr = this.flightReservationService.create(new FlightReservation(fl.getCost(),
				(RegularUser) logged, fl, this.seatService.getOne(Long.parseLong(sedista.split(" ")[0])), true,
				brPasosa, new Date(), logged.getFirstName(), logged.getLastName(), discount));

		Double newPrice = 0.0;
		if (discount != 0) {
			newPrice = fl.getCost() * (100 - discount) / 100;
			fr.setPrice(newPrice);
		}
		flightReservations.add(fr);
		String[] idjevi = idjeviPutnika.split(" ");
		String[] sed = sedista.split(" ");
		Seat s = this.seatService.getOne(Long.parseLong(sed[0]));
		s.setTaken(true);
		this.seatService.save(s);
		if (!idjeviPutnika.equals("")) {
			for (int i = 0; i < idjevi.length; i++) {
				FlightReservation fri = this.flightReservationService.create(
						new FlightReservation(fl.getCost(), this.regularUserService.findById(Long.parseLong(idjevi[i])),
								fl, this.seatService.getOne(Long.parseLong(sed[i + 1])), false, 0, new Date(),
								this.regularUserService.findById(Long.parseLong(idjevi[i])).getFirstName(),
								this.regularUserService.findById(Long.parseLong(idjevi[i])).getLastName()));
				flightReservations.add(fr);
				Seat friendSeat = this.seatService.getOne(Long.parseLong(sed[i + 1]));
				friendSeat.setTaken(true);
				this.seatService.save(friendSeat);
				InvitedFriendDTO inv = new InvitedFriendDTO(
						(this.regularUserService.findById(Long.parseLong(idjevi[i]))).getEmail(), fri.getId());
				invited.add(inv);

			}
		}
		FlightReservationReturnDTO frd = new FlightReservationReturnDTO(invited, fr.getId());
		return new ResponseEntity<>(frd, HttpStatus.CREATED);

	}

	@GetMapping(value = "/acceptFlightReservation/{id}")
	public RedirectView acceptFlightReservation(@PathVariable Long id) {
		FlightReservation res = this.flightReservationService.getOne(id);

		if (res != null) {
			res.setConfirmed(true);
			this.flightReservationService.save(res);
			return new RedirectView("http://localhost:8080/acceptedReservation.html");
		}
		return null;
	}

	@GetMapping(value = "/rejectFlightReservation/{id}")
	public RedirectView rejectFlightReservation(@PathVariable Long id) {
		FlightReservation res = this.flightReservationService.getOne(id);
		if (res != null) {
			Seat s = this.seatService.getOne(res.getSeat().getId());
			s.setTaken(false);
			this.seatService.save(s);
			this.flightReservationService.delete(res.getId());
			return new RedirectView("http://localhost:8080/rejectedReservation.html");
		}
		return null;
	}

	@GetMapping(value = "/finishReservation/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<FinishedReservationDTO> finishReservation(@PathVariable Long id) {
		FlightReservation fr = this.flightReservationService.getOne(id);
		String message = "You have successfully reserved flight from: "
				+ fr.getFlightReservation().getStartAirline().getCity() + " to "
				+ fr.getFlightReservation().getFinalAirline().getCity() + " on "
				+ fr.getFlightReservation().getDateOfStart();
		String email = fr.getRegularUserFlightReservation().getEmail();
		FinishedReservationDTO dto = new FinishedReservationDTO(fr, new CarReservation(), new HotelReservation(),
				message, email);
		return new ResponseEntity<>(dto, HttpStatus.OK);

	}

	@GetMapping(value = "/fastReservationAirline/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<FastReservationDTO>> fastReservationAirline(@PathVariable Long id) {
		List<Seat> sedista = this.seatService.findByQuickBooking(true);
		List<FastReservationDTO> brza = new ArrayList<FastReservationDTO>();
		for (Seat s : sedista) {
			if (s.getFlight().getAirline().getId().equals(id)) {
				brza.add(new FastReservationDTO(s.getId(), s.getFlight().getNumber(),
						s.getFlight().getStartAirline().getCity(), s.getFlight().getFinalAirline().getCity(),
						s.getFlight().getDateOfStart().toString(), s.getFlight().getDateOfEnd().toString(),
						s.getFlight().getLengthOfFlight(), s.getFlight().getCost(), s.getDiscount()));
			}
		}
		return new ResponseEntity<>(brza, HttpStatus.OK);

	}

	@PostMapping(value = "/reserveFastFlight/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<FlightReservation> reserveFastFlight(@PathVariable Long id) {
		Seat s = this.seatService.getOne(id);
		RegularUser user = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		FlightReservation fr = this.flightReservationService.create(new FlightReservation(s.getFlight().getCost(), user,
				s.getFlight(), s, true, 0, new Date(), user.getFirstName(), user.getLastName(), s.getDiscount()));
		s.setTaken(true);
		s.setQuickBooking(false);
		this.seatService.save(s);

		return new ResponseEntity<>(fr, HttpStatus.OK);

	}

}
