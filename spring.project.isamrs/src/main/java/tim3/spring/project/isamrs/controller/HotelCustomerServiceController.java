package tim3.spring.project.isamrs.controller;

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
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.dto.HotelCustomerServiceDTO;
import tim3.spring.project.isamrs.model.Hotel;
import tim3.spring.project.isamrs.model.HotelAdmin;
import tim3.spring.project.isamrs.model.HotelCustomerService;
import tim3.spring.project.isamrs.service.HotelCustomerServiceService;
import tim3.spring.project.isamrs.service.HotelService;
import tim3.spring.project.isamrs.service.impl.CustomUserDetailsService;

@RestController
public class HotelCustomerServiceController {

	@Autowired
	HotelService hotelService;

	@Autowired
	HotelCustomerServiceService hotelCustomerServiceService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@PostMapping(value = "/createHotelCustomerService", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_HOTEL_ADMIN')")
	public ResponseEntity<HotelCustomerService> create(@RequestBody HotelCustomerServiceDTO hotelCustomerServiceDTO) {
		HotelAdmin user = (HotelAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		HotelCustomerService newHotelCustomerService = new HotelCustomerService(hotelCustomerServiceDTO);
		newHotelCustomerService.setHotel(user.getHotel());

		HotelCustomerService retVal = hotelCustomerServiceService.create(newHotelCustomerService);
		user.getHotel().getHotelCustomerServices().add(retVal);
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getHotelCustomerServices", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_HOTEL_ADMIN')")
	public ResponseEntity<List<HotelCustomerService>> getHotelCustomerServices() {

		HotelAdmin user = (HotelAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		List<HotelCustomerService> retVal = hotelCustomerServiceService.findByHotel(user.getHotel());

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/getConcreteHotelCustomerServices/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HotelCustomerService>> getConcreteHotelCustomerServices(@PathVariable Long hotelId) {

		Hotel hotel = hotelService.getOne(hotelId);

		List<HotelCustomerService> retVal = hotelCustomerServiceService.findByHotel(hotel);

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteHotelCustomerService/{id}")
	@PreAuthorize("hasRole('ROLE_HOTEL_ADMIN')")
	public ResponseEntity<HotelCustomerService> deleteHotelCustomerService(@PathVariable String id) {
		HotelAdmin ha = (HotelAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		HotelCustomerService h = hotelCustomerServiceService.getOne(Long.parseLong(id));
		if (h == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		ha.getHotel().getHotelCustomerServices().remove(h);
		hotelCustomerServiceService.delete(Long.parseLong(id));
		return new ResponseEntity<>(h, HttpStatus.OK);
	}

	@PutMapping(value = "/saveEditedHotelCustomerService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_HOTEL_ADMIN')")
	public ResponseEntity<HotelCustomerService> saveEditedHotelCustomerService(
			@RequestBody HotelCustomerServiceDTO hotelCustomerService) {
		HotelCustomerService h = hotelCustomerServiceService.getOne(hotelCustomerService.getId());
		if (h == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		h.setId(hotelCustomerService.getId());
		h.setName(hotelCustomerService.getName());
		h.setPrice(hotelCustomerService.getPrice());

		HotelCustomerService editedHotelCustomerService = hotelCustomerServiceService.save(h);
		return new ResponseEntity<>(editedHotelCustomerService, HttpStatus.OK);
	}

	@GetMapping(value = "/findHotelCustomerService/{id}")
	public ResponseEntity<HotelCustomerService> findHotelCustomerService(@PathVariable long id) {
		HotelCustomerService h = hotelCustomerServiceService.getOne(id);
		if (h != null) {
			return new ResponseEntity<>(h, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
