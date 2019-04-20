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

import tim3.spring.project.isamrs.comparator.RentACarComparatorAddress;
import tim3.spring.project.isamrs.comparator.RentACarComparatorName;
import tim3.spring.project.isamrs.dto.RentacarDTO;
import tim3.spring.project.isamrs.model.Rentacar;
import tim3.spring.project.isamrs.service.RentacarService;

@RestController
public class RentacarController {

	@Autowired
	RentacarService rentacarService;

	@GetMapping(value = "/getAllRentacars")
	public ResponseEntity<List<Rentacar>> getAllRentacars() {
		List<Rentacar> rentacars = rentacarService.getAll();
		return new ResponseEntity<>(rentacars, HttpStatus.OK);
	}

	@PostMapping(value = "/createRentacar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rentacar> create(@RequestBody RentacarDTO rentacarDTO) {
		Rentacar retVal = rentacarService.create(new Rentacar(rentacarDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@GetMapping(value = "/findRentacar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rentacar> findRentacar() {
		Rentacar car = rentacarService.getOne(1);
		if (car != null) {
			return new ResponseEntity<>(car, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/saveChangesRentACar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

	@GetMapping(value = "/showRentACars/{criteria}")
	public ResponseEntity<List<Rentacar>> showRentACars(@PathVariable String criteria) {
		List<Rentacar> rentACars = rentacarService.getAll();
		if (criteria.equals("sortByNameRentACars")) {
			Collections.sort(rentACars, new RentACarComparatorName());
		} else if (criteria.equals("sortByAddressRentACars")) {
			Collections.sort(rentACars, new RentACarComparatorAddress());
		}
		return new ResponseEntity<>(rentACars, HttpStatus.OK);
	}

	@GetMapping(value = "/findRentacars/{field}")
	public ResponseEntity<List<Rentacar>> findRentacars(@PathVariable String field) {
		List<Rentacar> rentACars = (List<Rentacar>) rentacarService.findByName(field);
		if (rentACars.size() == 0) {
			rentACars = (List<Rentacar>) rentacarService.findByAddress(field);
		}

		return new ResponseEntity<>(rentACars, HttpStatus.OK);
	}
}
