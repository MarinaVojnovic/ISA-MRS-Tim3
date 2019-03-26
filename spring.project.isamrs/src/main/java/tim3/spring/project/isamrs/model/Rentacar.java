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

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "promotional_description")
	private String promotionalDescription;

	@JsonIgnore
	@OneToMany(mappedBy = "rentacar", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<RentacarCustomerService> rentacarCustomerServices = new HashSet<RentacarCustomerService>();

	@JsonIgnore
	@OneToMany(mappedBy = "rentacar", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Car> cars = new HashSet<Car>();

	@JsonIgnore
	@OneToMany(mappedBy = "rentacar", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<BranchOffice> branches = new HashSet<BranchOffice>();

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
		this.rentacarCustomerServices = new HashSet<RentacarCustomerService>();
		this.cars = new HashSet<Car>();
		this.branches = new HashSet<BranchOffice>();
	}

	public Rentacar(String name, String address, String promotionalDescription,
			Set<RentacarCustomerService> rentacarCustomerServices, Set<Car> cars, Set<BranchOffice> branches) {
		super();
		this.name = name;
		this.address = address;
		this.promotionalDescription = promotionalDescription;
		this.rentacarCustomerServices = rentacarCustomerServices;
		this.cars = cars;
		this.branches = branches;
	}
}
