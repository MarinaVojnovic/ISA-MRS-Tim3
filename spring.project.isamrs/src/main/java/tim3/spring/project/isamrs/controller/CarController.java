package tim3.spring.project.isamrs.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

import tim3.spring.project.isamrs.dto.CarDTO;
import tim3.spring.project.isamrs.dto.MessageDTO;
import tim3.spring.project.isamrs.model.BranchOffice;
import tim3.spring.project.isamrs.model.Car;
import tim3.spring.project.isamrs.model.CarReservation;
import tim3.spring.project.isamrs.model.Rentacar;
import tim3.spring.project.isamrs.model.RentacarAdmin;
import tim3.spring.project.isamrs.service.CarService;
import tim3.spring.project.isamrs.service.RentacarService;
import tim3.spring.project.isamrs.service.impl.CustomUserDetailsService;

@RestController
public class CarController {

	@Autowired
	CarService carService;

	@Autowired
	RentacarService rentacarService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@GetMapping(value = "/gradeCar/{id}/{grade}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Car> create(@PathVariable Long id, @PathVariable Integer grade) {
		System.out.println("Uslo u grade car");
		Car car = carService.getOne(id);
		car.setScore(car.getScore() + grade);
		car.setNumber(car.getNumber() + 1);
		carService.save(car);
		return new ResponseEntity<>(car, HttpStatus.CREATED);
	}

	@GetMapping(value = "/findConcreteCars/{rentacarId}")
	public ResponseEntity<Set<Car>> findConcreteCars(@PathVariable String rentacarId) {
		Rentacar retVal = rentacarService.getOne(Long.parseLong(rentacarId));
		return new ResponseEntity<>(retVal.getCars(), HttpStatus.OK);
	}

	@GetMapping(value = "/getAllCars", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Car>> getAllCars() {
		List<Car> cars = carService.getAll();
		return new ResponseEntity<>(cars, HttpStatus.OK);
	}

	@PostMapping(value = "/createCar", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_RENTACAR_ADMIN')")
	public ResponseEntity<Car> create(@RequestBody CarDTO carDTO) {
		System.out.println("Uslo u car dto");
		RentacarAdmin user = (RentacarAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Car newCar = new Car(carDTO);
		newCar.setRentacar(user.getRentacar());
		Car retVal = carService.create(newCar);
		user.getRentacar().getCars().add(retVal);
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getCars")
	public ResponseEntity<List<Car>> getCars() {
		RentacarAdmin ra = (RentacarAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println("Usernameeeee: " + ra.getUsername());
		List<Car> cars = carService.findByRentacar(ra.getRentacar());
		System.out.println("Cars size: " + cars.size());
		return new ResponseEntity<>(cars, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteCar/{carId}")
	@PreAuthorize("hasRole('ROLE_RENTACAR_ADMIN')")
	public ResponseEntity<?> deleteCar(@PathVariable String carId) {
		RentacarAdmin ra = (RentacarAdmin) this.userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Car car = carService.getOne(Long.parseLong(carId));
		if (car == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Date now = new Date();
		Boolean dozvola = true;
		for (CarReservation r : car.getReservations()) {
			if (r.getEndDate().compareTo(now) > 0) {
				dozvola = false;
			}
		}
		if (dozvola == true) {
			ra.getRentacar().getCars().remove(car);
			carService.delete(Long.parseLong(carId));
			return new ResponseEntity<>(car, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new MessageDTO("Car is reserved, it cannot be deleted.", "Error"),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/findCar/{carId}")
	public ResponseEntity<Car> findCar(@PathVariable long carId) {
		Car car = carService.getOne(carId);
		if (car != null) {
			return new ResponseEntity<>(car, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings("deprecation")
	@GetMapping(value = "/findSuitCars/{rentacarId}/{startDate}/{endDate}/{startCity}/{endCity}/{carType}/{passengers}/{fromPrice}/{toPrice}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Car>> findSuitCars(@PathVariable Long rentacarId, @PathVariable String startDate,
			@PathVariable String endDate, @PathVariable String startCity, @PathVariable String endCity,
			@PathVariable String carType, @PathVariable Integer passengers, @PathVariable Double fromPrice,
			@PathVariable Double toPrice)

	{
		List<Car> theFinalList = new ArrayList<Car>();
		List<Car> finalList = new ArrayList<Car>();
		List<Car> lista1 = new ArrayList<Car>();
		List<Car> lista2 = new ArrayList<Car>();
		Date startDatee = new Date(Integer.parseInt(startDate.split("\\-")[0]) - 1900,
				Integer.parseInt(startDate.split("\\-")[1]) - 1, Integer.parseInt(startDate.split("\\-")[2]));
		Date endDatee = new Date(Integer.parseInt(endDate.split("\\-")[0]) - 1900,
				Integer.parseInt(endDate.split("\\-")[1]) - 1, Integer.parseInt(endDate.split("\\-")[2]));

		System.out.println("RENTACAR ID" + rentacarId);
		Rentacar rentacar = rentacarService.getOne(rentacarId);
		List<Car> cars = carService.findByRentacar(rentacar);

		Boolean mesto1 = false;
		Boolean mesto2 = false;
		for (BranchOffice b : rentacar.getBranches()) {
			if (b.getCity().equalsIgnoreCase(startCity)) {
				mesto1 = true;
			}
			if (b.getCity().equalsIgnoreCase(endCity)) {
				mesto2 = true;
			}
		}

		if (mesto1 == true && mesto2 == true) {
			System.out.println("Uslo u glavni if");
			for (Car c : cars) {
				if (c.getCarType().equalsIgnoreCase(carType) && c.getSeats() >= passengers) {
					lista1.add(c);
				}
			}

			System.out.println("Lista 1 size: " + lista1.size());

			if (fromPrice != -1) {

				for (Car c : lista1) {
					if (c.getPrice() >= fromPrice) {
						lista2.add(c);
					}
				}
			} else {
				for (Car c : lista1) {
					lista2.add(c);
				}
			}
			System.out.println("Lista 2 size: " + lista2.size());

			if (toPrice != -1) {
				for (Car c : lista2) {
					if (c.getPrice() <= toPrice) {
						finalList.add(c);
					}
				}
			} else {
				for (Car c : lista2) {
					finalList.add(c);
				}
			}
			System.out.println("Final lista size: " + finalList.size());

			for (Car car : finalList) {
				Boolean dozvola = true;
				for (CarReservation res : car.getReservations()) {
					if (startDatee.compareTo(res.getStartDate()) < 0 && endDatee.compareTo(res.getEndDate()) < 0
							&& endDatee.compareTo(res.getStartDate()) > 0) {
						dozvola = false;
					} else if (startDatee.compareTo(res.getStartDate()) <= 0
							&& endDatee.compareTo(res.getEndDate()) >= 0) {
						dozvola = false;
					} else if (startDatee.compareTo(res.getStartDate()) >= 0
							&& endDatee.compareTo(res.getEndDate()) >= 0
							&& startDatee.compareTo(res.getEndDate()) < 0) {
						dozvola = false;
					} else if (startDatee.compareTo(res.getStartDate()) >= 0
							&& endDatee.compareTo(res.getEndDate()) <= 0) {
						dozvola = false;
					} else {
						dozvola = true;
					}
				}
				if (dozvola == true) {
					theFinalList.add(car);
				}
			}
		}

		int brojac = -1;
		for (int i = 0; i < theFinalList.size(); i++) {
			brojac++;
			if (theFinalList.get(i).getOnFastRes() == true) {
				theFinalList.remove(brojac);
			}
		}

		System.out.println("Uslo u find suitable cars");

		return new ResponseEntity<>(theFinalList, HttpStatus.OK);

	}

	@SuppressWarnings("deprecation")
	@GetMapping(value = "/findSuitCarsFast/{rentacarId}/{startDate}/{endDate}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<Car>> findSuitCarsFast(@PathVariable String rentacarId, @PathVariable String startDate,
			@PathVariable String endDate) {
		System.out.println("uSLO U FIND SUITABLE CARS FAST");

		List<Car> theFinalList = new ArrayList<Car>();

		Date startDatee = new Date(Integer.parseInt(startDate.split("\\-")[0]) - 1900,
				Integer.parseInt(startDate.split("\\-")[1]) - 1, Integer.parseInt(startDate.split("\\-")[2]));
		Date endDatee = new Date(Integer.parseInt(endDate.split("\\-")[0]) - 1900,
				Integer.parseInt(endDate.split("\\-")[1]) - 1, Integer.parseInt(endDate.split("\\-")[2]));

		System.out.println("RENTACAR ID" + rentacarId);
		Rentacar rentacar = rentacarService.getOne(Long.parseLong(rentacarId));
		System.out.println("RENTACAR NAME " + rentacar.getName());
		List<Car> cars = carService.findByRentacar(rentacar);
		System.out.println("Preslo get cars");
		for (Car car : cars) {
			Boolean dozvola = true;
			for (CarReservation res : car.getReservations()) {
				if (endDate != "-1") {
					if (startDatee.compareTo(res.getStartDate()) < 0 && endDatee.compareTo(res.getEndDate()) < 0
							&& endDatee.compareTo(res.getStartDate()) > 0) {
						dozvola = false;
					} else if (startDatee.compareTo(res.getStartDate()) <= 0
							&& endDatee.compareTo(res.getEndDate()) >= 0) {
						dozvola = false;
					} else if (startDatee.compareTo(res.getStartDate()) >= 0
							&& endDatee.compareTo(res.getEndDate()) >= 0
							&& startDatee.compareTo(res.getEndDate()) < 0) {
						dozvola = false;
					} else if (startDatee.compareTo(res.getStartDate()) >= 0
							&& endDatee.compareTo(res.getEndDate()) <= 0) {
						dozvola = false;
					} else {
						dozvola = true;
					}
				} else {
					if (startDatee.compareTo(res.getStartDate()) >= 0 && startDatee.compareTo(res.getEndDate()) < 0) {
						dozvola = false;
					}
				}

			}
			if (dozvola == true) {
				theFinalList.add(car);
			}
		}

		int brojac = -1;
		for (int i = 0; i < theFinalList.size(); i++) {
			brojac++;
			if (theFinalList.get(i).getOnFastRes() == false) {
				theFinalList.remove(brojac);
			}
		}

		return new ResponseEntity<>(theFinalList, HttpStatus.OK);

	}

	@PutMapping(value = "/saveEditedCar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_RENTACAR_ADMIN')")
	public ResponseEntity<Car> saveChangesRentACar(@RequestBody CarDTO car) {
		Car c = carService.getOne(car.getId());
		if (c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		c.setId(car.getId());
		c.setName(car.getName());
		c.setPrice(car.getPrice());
		c.setYear(car.getYear());
		c.setCarType(car.getCarType());
		c.setBrand(car.getBrand());
		c.setModel(car.getModel());
		c.setSeats(car.getSeats());

		Car editedCar = carService.save(c);
		return new ResponseEntity<>(editedCar, HttpStatus.OK);
	}

	@GetMapping(value = "/searchCarUnregistered/{brand}/{lowestPrice}/{highestPrice}")
	public ResponseEntity<List<Car>> searchCarUnregistered(@PathVariable String brand, @PathVariable Double lowestPrice,
			@PathVariable Double highestPrice) {
		List<Car> allCars = carService.getAll();
		List<Car> retVal = new ArrayList<>();
		for (Car car : allCars) {
			if (!brand.equals("0")) {
				if (!brand.equals(car.getBrand())) {
					continue;
				}
			}
			if (lowestPrice != -1) {
				if (lowestPrice > car.getPrice()) {
					continue;
				}
			}
			if (highestPrice != -1) {
				if (highestPrice < car.getPrice()) {
					continue;
				}
			}
			retVal.add(car);
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

}
