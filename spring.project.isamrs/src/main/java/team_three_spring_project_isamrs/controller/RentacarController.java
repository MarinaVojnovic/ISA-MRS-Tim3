package team_three_spring_project_isamrs.controller;

import java.util.ArrayList;
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

import team_three_spring_project_isamrs.comparator.RentACarComparatorAddress;
import team_three_spring_project_isamrs.comparator.RentACarComparatorName;
import team_three_spring_project_isamrs.dto.RentacarDTO;
import team_three_spring_project_isamrs.model.Rentacar;
import team_three_spring_project_isamrs.model.RentacarAdmin;
import team_three_spring_project_isamrs.service.RentacarService;
import team_three_spring_project_isamrs.service.impl.CustomUserDetailsService;

@RestController
public class RentacarController {

	@Autowired
	RentacarService rentacarService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@GetMapping(value = "/findConcreteRentacar/{id}")
	public ResponseEntity<Rentacar> findConcreteRentacar(@PathVariable String id) {
		Rentacar retVal = rentacarService.getOne(Long.parseLong(id));
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllRentacars")
	public ResponseEntity<List<Rentacar>> getAllRentacars() {
		List<Rentacar> rentacars = rentacarService.getAll();
		return new ResponseEntity<>(rentacars, HttpStatus.OK);
	}

	@PostMapping(value = "/createRentacar", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<Rentacar> create(@RequestBody RentacarDTO rentacarDTO) {
		Rentacar retVal = rentacarService.create(new Rentacar(rentacarDTO));
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@GetMapping(value = "/gradeRentacar/{id}/{grade}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Rentacar> create(@PathVariable Long id, @PathVariable Integer grade) {
		Rentacar rentacar = rentacarService.getOne(id);
		rentacar.setScore(rentacar.getScore() + grade);
		rentacar.setNumber(rentacar.getNumber() + 1);
		rentacarService.save(rentacar);
		return new ResponseEntity<>(rentacar, HttpStatus.CREATED);
	}

	@GetMapping(value = "/findRentacar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rentacar> findRentacar() {
		RentacarAdmin rentacarAdmin = (RentacarAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Rentacar rentacar = rentacarAdmin.getRentacar();
		if (rentacar != null) {
			return new ResponseEntity<>(rentacar, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/saveChangesRentACar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_RENTACAR_ADMIN')")
	public ResponseEntity<Rentacar> saveChangesRentACar(@RequestBody RentacarDTO rentacar) {
		RentacarAdmin rentacarAdmin = (RentacarAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Rentacar r = rentacarAdmin.getRentacar();
		if (r == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		r.setName(rentacar.getRentacarNameRegister());
		r.setAddress(rentacar.getRentacarAddressRegister());
		r.setPromotionalDescription(rentacar.getRentacarPromotionalDescription());
		r.setCity(rentacar.getCity());

		rentacarAdmin.setRentacar(r);

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

	@GetMapping(value = "/findRentacar/{address}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Rentacar>> searchFlights(@PathVariable String address) {
		List<Rentacar> allRentacars = rentacarService.getAll();
		List<Rentacar> rentacars = new ArrayList<Rentacar>();
		/*
		 * String grad = address.split(", ")[1].split(" ")[1]; System.out.println(grad);
		 * for (Rentacar rent : allRentacars) { if (rent.getAddress().contains(grad)) {
		 * rentacars.add(rent); } }
		 */
		for (Rentacar r : allRentacars) {
			if (r.getCity() != null) {
				if (r.getCity().equalsIgnoreCase(address)) {
					rentacars.add(r);
				}
			}

		}

		return new ResponseEntity<>(rentacars, HttpStatus.OK);
	}
}