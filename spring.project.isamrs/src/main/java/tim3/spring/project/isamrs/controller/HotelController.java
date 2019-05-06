package tim3.spring.project.isamrs.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.isamrs.comparator.HotelsComparatorAddress;
import tim3.spring.project.isamrs.comparator.HotelsComparatorName;
import tim3.spring.project.isamrs.dto.HotelDTO;
import tim3.spring.project.isamrs.model.Hotel;
import tim3.spring.project.isamrs.model.HotelAdmin;
import tim3.spring.project.isamrs.service.HotelService;
import tim3.spring.project.isamrs.service.impl.CustomUserDetailsService;

@RestController
public class HotelController {
	@Autowired
	HotelService hotelService;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@GetMapping(value = "/findConcreteHotel/{id}")
	public ResponseEntity<Hotel> findConcreteHotel(@PathVariable String id) {
		Hotel retVal = hotelService.getOne(Long.parseLong(id));
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllHotels")
	public ResponseEntity<List<Hotel>> getAllHotels() {
		List<Hotel> hotels = hotelService.getAll();
		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}

	@PostMapping(value = "/createHotel", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<Hotel> create(@RequestBody HotelDTO hotelDTO) {
		Hotel retVal = hotelService.create(new Hotel(hotelDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@GetMapping(value = "/findHotel", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_HOTEL_ADMIN')")
	public ResponseEntity<Hotel> findHotel() {
		HotelAdmin hotelAdmin = (HotelAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Hotel hotel = hotelAdmin.getHotel();
		if (hotel != null) {
			return new ResponseEntity<>(hotel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/saveChangesHotel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_HOTEL_ADMIN')")
	public ResponseEntity<Hotel> saveChangesHotel(@RequestBody HotelDTO hotel) {
		HotelAdmin hotelAdmin = (HotelAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Hotel h = hotelAdmin.getHotel();
		if (h == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		h.setName(hotel.getHotelNameRegister());
		h.setAddress(hotel.getHotelAddressRegister());
		h.setPromotionalDescription(hotel.getHotelPromotionalDescription());

		hotelAdmin.setHotel(h);

		Hotel hotel2 = hotelService.save(h);
		return new ResponseEntity<>(hotel2, HttpStatus.OK);
	}

	@GetMapping(value = "/showHotels/{criteria}")
	public ResponseEntity<List<Hotel>> showHotels(@PathVariable String criteria) {
		List<Hotel> hotels = hotelService.getAll();
		if (criteria.equals("sortByNameHotels")) {
			Collections.sort(hotels, new HotelsComparatorName());
		} else if (criteria.equals("sortByAddressHotels")) {
			Collections.sort(hotels, new HotelsComparatorAddress());
		}
		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}

	@GetMapping(value = "/findHotels/{field}")
	public ResponseEntity<List<Hotel>> findHotels(@PathVariable String field) {
		List<Hotel> hotels = (List<Hotel>) hotelService.findByName(field);
		if (hotels.size() == 0) {
			hotels = (List<Hotel>) hotelService.findByAddress(field);
		}

		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}
}
