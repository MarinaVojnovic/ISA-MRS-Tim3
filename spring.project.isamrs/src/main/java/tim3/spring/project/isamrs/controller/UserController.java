package tim3.spring.project.isamrs.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import tim3.spring.project.isamrs.comparator.FriendsComparatorNameSurname;
import tim3.spring.project.isamrs.dto.FlightReservationDTO;
import tim3.spring.project.isamrs.dto.FriendRequestDTO;
import tim3.spring.project.isamrs.dto.InvitedFriendDTO;
import tim3.spring.project.isamrs.dto.PasswordDTO;
import tim3.spring.project.isamrs.dto.UserDTO;
import tim3.spring.project.isamrs.model.CarReservation;
import tim3.spring.project.isamrs.model.Flight;
import tim3.spring.project.isamrs.model.FlightReservation;
import tim3.spring.project.isamrs.model.FriendRequest;
import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.Seat;
import tim3.spring.project.isamrs.model.User;
import tim3.spring.project.isamrs.model.UserRoleName;
import tim3.spring.project.isamrs.model.UserTokenState;
import tim3.spring.project.isamrs.repository.SeatRepository;
import tim3.spring.project.isamrs.security.TokenHelper;
import tim3.spring.project.isamrs.service.CarReservationService;
import tim3.spring.project.isamrs.service.FlightReservationService;
import tim3.spring.project.isamrs.service.FlightService;
import tim3.spring.project.isamrs.service.FriendRequestService;
import tim3.spring.project.isamrs.service.RegularUserService;
import tim3.spring.project.isamrs.service.SeatService;
import tim3.spring.project.isamrs.service.UserService;
import tim3.spring.project.isamrs.service.impl.CustomUserDetailsService;

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

		return new ResponseEntity<>(new UserTokenState(jwt, expiresIn, userType), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findFriends/{name}/{surname}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<RegularUser>> findFriends(@PathVariable String name, @PathVariable String surname) {
		List<RegularUser> users = new ArrayList<RegularUser>();
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

		return new ResponseEntity<List<RegularUser>>(users, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	@PostMapping(value = "/sendFriendRequest/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<FriendRequest> sendFriendRequest(@PathVariable Long userId) {
		System.out.println("TRAZIMO " + userId);
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

		return new ResponseEntity<List<FriendRequest>>(users, HttpStatus.OK);

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

		return new ResponseEntity<FriendRequest>(fr, HttpStatus.OK);

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
		return new ResponseEntity<FriendRequest>(fr, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllFriends")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<RegularUser>> getAllFriends() {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<RegularUser> regular = new ArrayList<RegularUser>();
		List<FriendRequest> friends = this.friendRequestService.findByReceivedAndAccepted((RegularUser) logged, true);
		for (FriendRequest f : friends) {
			regular.add(f.getSent());
		}
		List<FriendRequest> friends2 = this.friendRequestService.findBySentAndAccepted((RegularUser) logged, true);
		for (FriendRequest f : friends2) {
			regular.add(f.getReceived());
		}
		Collections.sort(regular, new FriendsComparatorNameSurname());
		return new ResponseEntity<List<RegularUser>>(regular, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getMyResCars")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<CarReservation>> getMyResCars() {
		System.out.println("Uslo u get my reservations cars");
		RegularUser logged = (RegularUser) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<CarReservation> res=new ArrayList<CarReservation>();
		res = carReservationService.findByRegularUser(logged);
		return new ResponseEntity<List<CarReservation>>(res, HttpStatus.OK);
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
		return new ResponseEntity<List<FriendRequest>>(friends, HttpStatus.OK);
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
		return new ResponseEntity<FriendRequest>(fr, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/all")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<User> loadAll() {
		return this.userService.findAll();
	}

	@RequestMapping("/whoami")
	@PreAuthorize("hasRole('ROLE_USER')")
	public User user(Principal user) {
		return this.userService.findByUsername(user.getName());
	}

	@GetMapping(value = "/isFirstTime")
	public ResponseEntity<Boolean> isFirstTime() {
		User user = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		if (user.getFirstTime() == true) {
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
	public ResponseEntity<List<InvitedFriendDTO>> makeReservation(@RequestBody FlightReservationDTO flightReservation) {
		int brPasosa=Integer.parseInt(flightReservation.getPassportNum());
		String idjeviPutnika=flightReservation.getUsers();
		String sedista=flightReservation.getSeats();
		Long let=Long.valueOf(flightReservation.getFlight());
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Flight fl=this.flightService.getOne(let);
		List<FlightReservation> flightReservations=new ArrayList<>();
		List<InvitedFriendDTO> invited=new ArrayList<>();
		FlightReservation fr=this.flightReservationService.create(new FlightReservation(fl.getCost(),(RegularUser)logged,fl,this.seatService.getOne(Long.parseLong(sedista.split(" ")[0])),true,brPasosa,logged.getFirstName(),logged.getLastName()));
		
		flightReservations.add(fr);
		String idjevi[]=idjeviPutnika.split(" ");
		String sed[]=sedista.split(" ");
		Seat s=this.seatService.getOne(Long.parseLong(sed[0]));
		s.setTaken(true);
		this.seatService.save(s);
		if(!idjeviPutnika.equals("")) {
		for(int i=0;i<idjevi.length;i++) {
			FlightReservation fri=this.flightReservationService.create(new FlightReservation(fl.getCost(),this.regularUserService.findById(Long.parseLong(idjevi[i])),fl,this.seatService.getOne(Long.parseLong(sed[i+1])),false,0,this.regularUserService.findById(Long.parseLong(idjevi[i])).getFirstName(),this.regularUserService.findById(Long.parseLong(idjevi[i])).getLastName()));
			flightReservations.add(fr);
			Seat friendSeat=this.seatService.getOne(Long.parseLong(sed[i+1]));
			friendSeat.setTaken(true);
			this.seatService.save(friendSeat);
			InvitedFriendDTO inv=new InvitedFriendDTO((this.regularUserService.findById(Long.parseLong(idjevi[i]))).getEmail(),fri.getId());
			invited.add(inv);
			
		}
		}
		return new ResponseEntity<List<InvitedFriendDTO>>(invited, HttpStatus.CREATED);

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
			Seat s=this.seatService.getOne(res.getSeat().getId());
			s.setTaken(false);
			this.seatService.save(s);
			this.flightReservationService.delete(res.getId());
			return new RedirectView("http://localhost:8080/rejectedReservation.html");
		}
		return null;
	}
}
