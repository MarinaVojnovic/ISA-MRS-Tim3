package team_three_spring_project_isamrs.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team_three_spring_project_isamrs.comparator.HotelsComparatorAddress;
import team_three_spring_project_isamrs.comparator.HotelsComparatorName;
import team_three_spring_project_isamrs.dto.HotelDTO;
import team_three_spring_project_isamrs.model.Airline;
import team_three_spring_project_isamrs.model.Hotel;
import team_three_spring_project_isamrs.model.HotelAdmin;
import team_three_spring_project_isamrs.model.Room;
import team_three_spring_project_isamrs.service.HotelService;
import team_three_spring_project_isamrs.service.RoomService;
import team_three_spring_project_isamrs.service.impl.CustomUserDetailsService;

@RestController
public class HotelController {
	@Autowired
	HotelService hotelService;
	
	@Autowired
	RoomService roomService;

	@Autowired
	private CustomUserDetailsService userDetailsService;


	@GetMapping(value = "/gradeHotel/{id}/{grade}/{roomsGrades}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Hotel> gradeHotel(@PathVariable Long id, @PathVariable Integer grade, @PathVariable String roomsGrades) {
		System.out.println("Uslo u grade hotel     "+id);
		Hotel hotel = hotelService.getOne(id);
		System.out.println(hotel.getName());
		System.out.println("A");
		hotel.setScore(hotel.getScore()+grade);
		System.out.println("B");
		hotel.setGradeNumber(hotel.getGradeNumber()+1);
		System.out.println("C");
		
		System.out.println("D");
		String[] lista = roomsGrades.split("\\|");
		List<String> array = new ArrayList(Arrays.asList(lista));
		for (String par : array) {
			Room room = roomService.findByRoomNumberAndHotel(Integer.parseInt(par.split("\\,")[0].trim()), hotel);
			
			room.setGradeNumber(room.getGradeNumber()+1);
			room.setScore(room.getScore()+Double.parseDouble(par.split("\\,")[1]));
			roomService.save(room);
		}
		
		hotelService.save(hotel);
		return new ResponseEntity<>(hotel, HttpStatus.CREATED);
	}
	
	
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
		Hotel h = hotelService.getOne(hotelAdmin.getHotel().getId());
		if (h == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		h.setName(hotel.getHotelNameRegister());
		h.setAddress(hotel.getHotelAddressRegister());
		h.setPromotionalDescription(hotel.getHotelPromotionalDescription());
		h.setCity(hotel.getCity());

		hotelAdmin.setHotel(h);
		Hotel hotel2 = null;

		try {
			hotel2 = hotelService.save(h);
		} catch (JpaSystemException e) {
		}
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
		List<Hotel> hotels = hotelService.findByName(field);
		if (hotels.isEmpty()) {
			hotels = hotelService.findByCity(field);
		}

		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}

	@GetMapping(value = "/findHotelByDestination/{address}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Hotel>> findHotelsByDestination(@PathVariable String address) {
		List<Hotel> allHotels = hotelService.getAll();
		List<Hotel> hotels = new ArrayList<Hotel>();

		/*
		 * for (Hotel h : allHotels) { if (h.getCity() != null) { if
		 * (h.getCity().equalsIgnoreCase(address)) { hotels.add(h); } }
		 * 
		 * }
		 */
		
		hotels=hotelService.findByCity(address);
		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}
}
