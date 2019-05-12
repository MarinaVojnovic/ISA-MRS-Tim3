package team_three_spring_project_isamrs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import team_three_spring_project_isamrs.dto.BranchDTO;

@Entity
@Table(catalog = "dbtim3", name = "branch_office")
public class BranchOffice {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "city")
	private String city;

	@Column(name = "address")
	private String address;
	
	@ManyToOne(fetch = FetchType.EAGER)
	Rentacar rentacar;
	
	
	public BranchOffice() {
		
	}
	
	public BranchOffice(BranchDTO dto) {
		this.city=dto.getCity();
		this.address=dto.getAddress();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Rentacar getRentacar() {
		return rentacar;
	}

	public void setRentacar(Rentacar rentacar) {
		this.rentacar = rentacar;
	}
}
