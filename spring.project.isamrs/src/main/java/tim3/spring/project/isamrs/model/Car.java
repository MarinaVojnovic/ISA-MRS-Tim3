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

	@Column(name = "carYear")
	private Integer year;

	@ManyToOne(fetch = FetchType.EAGER)
	Rentacar rentacar;

	public Car() {

	}

	public Car(CarDTO carDto) {
		this.name = carDto.getName();
		this.price = carDto.getPrice();
		this.year = carDto.getYear();
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((rentacar == null) ? 0 : rentacar.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (rentacar == null) {
			if (other.rentacar != null)
				return false;
		} else if (!rentacar.equals(other.rentacar))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
}
