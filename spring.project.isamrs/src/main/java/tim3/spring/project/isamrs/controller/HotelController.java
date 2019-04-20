package tim3.spring.project.isamrs.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import tim3.spring.project.isamrs.service.HotelService;

@RestController
public class HotelController {
	@Autowired
	HotelService hotelService;

	@GetMapping(value = "/getAllHotels")
	public ResponseEntity<List<Hotel>> getAllHotels() {
		List<Hotel> hotels = hotelService.getAll();
		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}

	@PostMapping(value = "/createHotel", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> create(@RequestBody HotelDTO hotelDTO) {
		Hotel retVal = hotelService.create(new Hotel(hotelDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@GetMapping(value = "/findHotel", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> findRentacar() {
		Hotel hotel = hotelService.getOne(1);
		if (hotel != null) {
			return new ResponseEntity<>(hotel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/saveChangesHotel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> saveChangesRentACar(@RequestBody Hotel hotel) {
		Hotel h = hotelService.getOne(hotel.getId());
		if (h == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		h.setId(hotel.getId());
		h.setName(hotel.getName());
		h.setAddress(hotel.getAddress());
		h.setPromotionalDescription(hotel.getPromotionalDescription());
		h.setHotelCustomerServices(hotel.getHotelCustomerServices());

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
