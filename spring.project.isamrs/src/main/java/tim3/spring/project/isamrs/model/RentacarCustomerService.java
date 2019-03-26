package tim3.spring.project.isamrs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(catalog = "dbtim3", name = "rentacar_customer_service")
public class RentacarCustomerService {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	Rentacar rentacar;

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
