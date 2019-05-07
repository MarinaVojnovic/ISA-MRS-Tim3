package tim3.spring.project.isamrs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tim3.spring.project.isamrs.dto.RentacarDTO;

@Entity
@Table(catalog = "dbtim3", name = "rentacar")
public class Rentacar {
	@Id
	@GeneratedValue
	private Long id;

	@JsonIgnore
	@OneToMany(mappedBy = "rentacarAdmin", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<RentacarAdmin> rentacarAdmins = new HashSet<>();

	@Column(name = "name")
	private String name;

	@Column(name = "city")
	private String city;

	@Column(name = "address")
	private String address;

	@Column(name = "score")
	private Double score;

	@Column(name = "number")
	private Integer number;

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name = "promotional_description")
	private String promotionalDescription;

	@JsonIgnore
	@OneToMany(mappedBy = "rentacar", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<RentacarCustomerService> rentacarCustomerServices = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "rentacar", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Car> cars = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "rentacarRes", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<CarReservation> carReservations = new HashSet<>();

	public Set<CarReservation> getCarReservations() {
		return carReservations;
	}

	public void setCarReservations(Set<CarReservation> carReservations) {
		this.carReservations = carReservations;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "rentacar", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<BranchOffice> branches = new HashSet<>();

	public Rentacar() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rentacar other = (Rentacar) obj;
		return id == other.id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPromotionalDescription() {
		return promotionalDescription;
	}

	public void setPromotionalDescription(String promotionalDescription) {
		this.promotionalDescription = promotionalDescription;
	}

	public Set<RentacarCustomerService> getRentacarCustomerServices() {
		return rentacarCustomerServices;
	}

	public void setRentacarCustomerServices(Set<RentacarCustomerService> rentacarCustomerServices) {
		this.rentacarCustomerServices = rentacarCustomerServices;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public Set<BranchOffice> getBranches() {
		return branches;
	}

	public void setBranches(Set<BranchOffice> branches) {
		this.branches = branches;
	}

	public Rentacar(RentacarDTO rentacarDTO) {
		this.name = rentacarDTO.getRentacarNameRegister();
		this.address = rentacarDTO.getRentacarAddressRegister();
		this.promotionalDescription = rentacarDTO.getRentacarPromotionalDescription();
		this.score = 0.0;
		this.number = 0;
		this.city = rentacarDTO.getCity();
	}

	public Rentacar(String name, String city, String address, String promotionalDescription,
			Set<RentacarCustomerService> rentacarCustomerServices, Set<Car> cars, Set<BranchOffice> branches,
			Double score, Integer number) {
		super();
		this.name = name;
		this.address = address;
		this.promotionalDescription = promotionalDescription;
		this.rentacarCustomerServices = rentacarCustomerServices;
		this.cars = cars;
		this.branches = branches;
		this.score = score;
		this.number = number;
		this.city = city;
	}

	public Set<RentacarAdmin> getRentacarAdmins() {
		return rentacarAdmins;
	}

	public void setRentacarAdmins(Set<RentacarAdmin> rentacarAdmins) {
		this.rentacarAdmins = rentacarAdmins;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
