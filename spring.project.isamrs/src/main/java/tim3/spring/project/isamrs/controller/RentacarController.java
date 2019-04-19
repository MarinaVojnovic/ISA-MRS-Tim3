package tim3.spring.project.isamrs.controller;

import java.util.ArrayList;
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

import tim3.spring.project.comparator.RentACarComparatorAddress;
import tim3.spring.project.comparator.RentACarComparatorName;
import tim3.spring.project.isamrs.dto.RentacarDTO;
import tim3.spring.project.isamrs.model.Rentacar;
import tim3.spring.project.isamrs.service.RentacarService;

@RestController
public class RentacarController {

	@Autowired
	RentacarService rentacarService;

	@RequestMapping(value = "/getAllRentacars", method = RequestMethod.GET)
	public ResponseEntity<List<Rentacar>> getAllRentacars() {
		List<Rentacar> rentacars = rentacarService.getAll();
		return new ResponseEntity<>(rentacars, HttpStatus.OK);
	}

	@RequestMapping(value = "/createRentacar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rentacar> create(@RequestBody RentacarDTO rentacarDTO) {
		Rentacar retVal = rentacarService.create(new Rentacar(rentacarDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/findRentacar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rentacar> findRentacar() {
		Rentacar car = rentacarService.getOne(1);
		if (car != null) {
			return new ResponseEntity<>(car, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/saveChangesRentACar", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rentacar> saveChangesRentACar(@RequestBody Rentacar rentacar) {
		Rentacar r = rentacarService.getOne(rentacar.getId());
		if (r == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		r.setId(rentacar.getId());
		r.setName(rentacar.getName());
		r.setAddress(rentacar.getAddress());
		r.setBranches(rentacar.getBranches());
		r.setPromotionalDescription(rentacar.getPromotionalDescription());
		r.setRentacarCustomerServices(rentacar.getRentacarCustomerServices());

		Rentacar rent = rentacarService.save(r);
		return new ResponseEntity<>(rent, HttpStatus.OK);
	}

	@RequestMapping(value = "/showRentACars/{criteria}", method = RequestMethod.GET)
	public ResponseEntity<List<Rentacar>> showRentACars(@PathVariable String criteria) {
		System.out.println("Show rent a cars pozvano");
		List<Rentacar> rentACars = rentacarService.getAll();
		if (criteria.equals("sortByNameRentACars")) {
			Collections.sort(rentACars, new RentACarComparatorName());
		} else if (criteria.equals("sortByAddressRentACars")) {
			Collections.sort(rentACars, new RentACarComparatorAddress());
		}
		return new ResponseEntity<>(rentACars, HttpStatus.OK);
	}

	@RequestMapping(value = "/findRentacars/{field}", method = RequestMethod.GET)
	public ResponseEntity<List<Rentacar>> findRentacars(@PathVariable String field) {
		System.out.println("Show rent a cars pozvano");
		List<Rentacar> rentACars = new ArrayList<Rentacar>();
		rentACars = (List<Rentacar>) rentacarService.findByName(field);
		if (rentACars.size() == 0) {
			rentACars = (List<Rentacar>) rentacarService.findByAddress(field);
		}

		return new ResponseEntity<>(rentACars, HttpStatus.OK);
	}
}
