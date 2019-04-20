package tim3.spring.project.isamrs.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import tim3.spring.project.isamrs.dto.PasswordDTO;
import tim3.spring.project.isamrs.dto.UserDTO;
import tim3.spring.project.isamrs.model.Car;
import tim3.spring.project.isamrs.model.RentacarAdmin;
import tim3.spring.project.isamrs.dto.FriendRequestDTO;
import tim3.spring.project.isamrs.dto.UserDTO;
import tim3.spring.project.isamrs.model.FriendRequest;
import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.Rentacar;
import tim3.spring.project.isamrs.model.User;
import tim3.spring.project.isamrs.model.UserRoleName;
import tim3.spring.project.isamrs.model.UserTokenState;
import tim3.spring.project.isamrs.security.TokenHelper;
import tim3.spring.project.isamrs.service.CarService;
import tim3.spring.project.isamrs.service.FriendRequestService;
import tim3.spring.project.isamrs.service.RegularUserService;
import tim3.spring.project.isamrs.service.UserService;
import tim3.spring.project.isamrs.service.impl.CustomUserDetailsService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	TokenHelper tokenUtils;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private RegularUserService regularUserService;
	
	@Autowired
	private FriendRequestService friendRequestService;

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
				user.getPhoneNumber());
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
	public ResponseEntity<List<RegularUser>> findFriends(@PathVariable String name,@PathVariable String surname) {
		List<RegularUser> users=new ArrayList<RegularUser>();
		if(surname.equals("no_surname")) {
			users=this.userService.findByFirstName(name);
		}else {
			users=this.userService.findByFirstNameAndLastName(name, surname);
		}
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		for(RegularUser u: users) {
			if(u.getUsername().equals(logged.getUsername())) {
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
	
	@PostMapping(value="/sendFriendRequest/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<FriendRequest> sendFriendRequest(@PathVariable Long userId) {
		System.out.println("TRAZIMO "+userId);
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		RegularUser ru=this.regularUserService.findById(userId);
		
		FriendRequest retVal = friendRequestService.create(new FriendRequest(new FriendRequestDTO((RegularUser) logged,ru, false)));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
		
	}
	
	@GetMapping(value="/getFriendRequests")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<FriendRequest>> getFriendRequest() {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<FriendRequest> users= friendRequestService.findByReceivedAndAccepted((RegularUser) logged, false);
		
		return new ResponseEntity<List<FriendRequest>>(users, HttpStatus.OK);
		
	}
	
	@PutMapping(value="/acceptFriend/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<FriendRequest> acceptFriendRequest(@PathVariable Long userId) {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		RegularUser ru=this.regularUserService.findById(userId);
		FriendRequest fr=this.friendRequestService.findByReceivedAndSentAndAccepted((RegularUser) logged, (RegularUser) ru, false);
		fr.setAccepted(true);
		this.friendRequestService.save(fr);
		
		return new ResponseEntity<FriendRequest>(fr, HttpStatus.OK);
		
	}
	
	@DeleteMapping(value="/rejectFriend/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<FriendRequest> rejectFriendRequest(@PathVariable Long userId) {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		RegularUser ru=this.regularUserService.findById(userId);
		FriendRequest fr=this.friendRequestService.findByReceivedAndSentAndAccepted((RegularUser) logged, (RegularUser) ru, false);
		this.friendRequestService.delete(fr.getId());
		return new ResponseEntity<FriendRequest>(fr, HttpStatus.OK);
	}
	
	@GetMapping(value="/getAllFriends")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<RegularUser>> getAllFriends() {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<RegularUser> regular=new ArrayList<RegularUser>();
		List<FriendRequest> friends=this.friendRequestService.findByReceivedAndAccepted((RegularUser) logged, true);
		for(FriendRequest f: friends) {
			regular.add(f.getSent());
		}
		List<FriendRequest> friends2=this.friendRequestService.findBySentAndAccepted((RegularUser) logged, true);
		for(FriendRequest f: friends2) {
			regular.add(f.getReceived());
		}
		return new ResponseEntity<List<RegularUser>>(regular, HttpStatus.OK);
	}
	
	@GetMapping(value="/getAllFriendRequests")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<FriendRequest>> getAllFriendRequests() {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<FriendRequest> friends=this.friendRequestService.findBySentAndAccepted((RegularUser) logged, false);
		List<FriendRequest> friend2=this.friendRequestService.findBySentAndAccepted((RegularUser) logged, true);
		List<FriendRequest> friend3=this.friendRequestService.findByReceivedAndAccepted((RegularUser) logged, true);
		for(FriendRequest f: friend2) {
			friends.add(f);
		}
		for(FriendRequest f: friend3) {
			friends.add(f);
		}
		return new ResponseEntity<List<FriendRequest>>(friends, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/removeFriend/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<FriendRequest> removeFriend(@PathVariable Long userId) {
		User logged = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		RegularUser ru=this.regularUserService.findById(userId);
		FriendRequest fr=this.friendRequestService.findByReceivedAndSentAndAccepted((RegularUser) logged, ru, true);
		if(fr==null) {
			fr=this.friendRequestService.findByReceivedAndSentAndAccepted(ru,(RegularUser) logged, true);
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
	public ResponseEntity<Boolean> changePasswordFirstTime(@RequestBody PasswordDTO userEdit) {
		User user = (User) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		user.setFirstTime(false);
		user.setPassword(this.userDetailsService.encodePassword(userEdit.getPassword()));

		if (this.userDetailsService.saveUser(user)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.OK);
	}
}
