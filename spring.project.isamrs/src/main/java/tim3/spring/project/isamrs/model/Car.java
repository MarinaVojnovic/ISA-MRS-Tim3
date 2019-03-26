package tim3.spring.project.isamrs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import tim3.spring.project.isamrs.dto.CarDTO;

@Entity
@Table(catalog = "dbtim3", name = "car")
public class Car {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "year")
	private Integer year;

	@ManyToOne(fetch = FetchType.EAGER)
	Rentacar rentacar;

	public Car(CarDTO carDto) {
		this.name=carDto.getName();
		this.price=carDto.getPrice();
		this.year=carDto.getYear();
		this.rentacar=null;
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

	public Rentacar getRentacar() {
		return rentacar;
	}

	public void setRentacar(Rentacar rentacar) {
		this.rentacar = rentacar;
	}
}
