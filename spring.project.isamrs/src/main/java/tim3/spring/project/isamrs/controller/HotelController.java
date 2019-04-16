package tim3.spring.project.isamrs.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim3.spring.project.comparator.HotelsComparatorAddress;
import tim3.spring.project.comparator.HotelsComparatorName;
import tim3.spring.project.isamrs.dto.HotelDTO;
import tim3.spring.project.isamrs.model.Hotel;
import tim3.spring.project.isamrs.service.HotelService;

@RestController
public class HotelController {
	@Autowired
	HotelService hotelService;

	@RequestMapping(value = "/getAllHotels", method = RequestMethod.GET)
	public ResponseEntity<List<Hotel>> getAllHotels() {
		List<Hotel> hotels = hotelService.getAll();
		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}

	@RequestMapping(value = "/createHotel", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> create(@RequestBody HotelDTO hotelDTO) {
		Hotel retVal = hotelService.create(new Hotel(hotelDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/findHotel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> findRentacar() {
		Hotel hotel = hotelService.getOne(1);
		if (hotel != null) {
			return new ResponseEntity<>(hotel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/saveChangesHotel", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
	@RequestMapping(value="/showHotels/{criteria}", method = RequestMethod.GET)
	public ResponseEntity<List<Hotel>> showHotels(@PathVariable String criteria) {
		System.out.println("Show hotels pozvano");
		List<Hotel> hotels = hotelService.getAll();
		/*
		 * //List<Hotel> hotels = new ArrayList<Hotel>(); Hotel h1=new
		 * Hotel("AHotelOne", "CAddressOne", "CPromoDesOne",null,null); Hotel h2=new
		 * Hotel("BHotelTwo", "BAddressTwo", "BPromoDesTwo",null,null); Hotel h3=new
		 * Hotel("CHotelTwo", "AAddressTwo", "APromoDesTwo",null,null); hotels.add(h1);
		 * hotels.add(h2); hotels.add(h3);
		 */
		
		if (criteria.equals("sortByName")) {
			Collections.sort(hotels, new HotelsComparatorName());
		}else if (criteria.equals("sortByAddress")) {
			Collections.sort(hotels, new HotelsComparatorAddress());
		}
		return new ResponseEntity<>(hotels, HttpStatus.OK); 
	}
}
