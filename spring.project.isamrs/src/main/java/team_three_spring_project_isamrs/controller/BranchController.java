package team_three_spring_project_isamrs.controller;

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

import team_three_spring_project_isamrs.dto.BranchDTO;
import team_three_spring_project_isamrs.model.BranchOffice;
import team_three_spring_project_isamrs.model.Rentacar;
import team_three_spring_project_isamrs.model.RentacarAdmin;
import team_three_spring_project_isamrs.service.BranchService;
import team_three_spring_project_isamrs.service.RentacarService;
import team_three_spring_project_isamrs.service.impl.CustomUserDetailsService;

@RestController
public class BranchController {

	@Autowired
	RentacarService rentacarService;

	@Autowired
	BranchService branchService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@PostMapping(value = "/createBranch", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_RENTACAR_ADMIN')")
	public ResponseEntity<BranchOffice> create(@RequestBody BranchDTO branchDTO) {
		RentacarAdmin user = (RentacarAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		BranchOffice newBranch = new BranchOffice(branchDTO);
		newBranch.setRentacar(user.getRentacar());

		BranchOffice retVal = branchService.create(newBranch);
		user.getRentacar().getBranches().add(retVal);
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getBranches", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_RENTACAR_ADMIN')")
	public ResponseEntity<List<BranchOffice>> getBranches() {

		RentacarAdmin user = (RentacarAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		List<BranchOffice> retVal = branchService.findByRentacar(user.getRentacar());

		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getConcreteBranches/{rentacarId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BranchOffice>> getConcreteBranches(@PathVariable Long rentacarId) {

		Rentacar rentacar = rentacarService.getOne(rentacarId);

		List<BranchOffice> retVal = branchService.findByRentacar(rentacar);

		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/deleteBranch/{id}")
	@PreAuthorize("hasRole('ROLE_RENTACAR_ADMIN')")
	public ResponseEntity<BranchOffice> deleteBranch(@PathVariable String id) {
		RentacarAdmin ra = (RentacarAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		BranchOffice b = branchService.getOne(Long.parseLong(id));
		if (b == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		ra.getRentacar().getBranches().remove(b);
		branchService.delete(Long.parseLong(id));
		return new ResponseEntity<>(b, HttpStatus.OK);
	}

	@PutMapping(value = "/saveEditedBranch", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_RENTACAR_ADMIN')")
	public ResponseEntity<BranchOffice> saveEditedBranch(@RequestBody BranchDTO branch) {
		BranchOffice b = branchService.getOne(branch.getId());
		if (b == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		b.setId(branch.getId());
		b.setCity(branch.getCity());
		b.setAddress(branch.getAddress());

		BranchOffice editedBranch = branchService.save(b);
		return new ResponseEntity<>(editedBranch, HttpStatus.OK);
	}

	@GetMapping(value = "/findBranch/{id}")
	public ResponseEntity<BranchOffice> findCar(@PathVariable long id) {
		BranchOffice b = branchService.getOne(id);
		if (b != null) {
			return new ResponseEntity<>(b, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
