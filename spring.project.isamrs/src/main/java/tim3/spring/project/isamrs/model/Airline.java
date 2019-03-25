package tim3.spring.project.isamrs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(catalog = "dbtim3", name = "airline") 
public class Airline {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name2")
	private String name;

	public Airline(String name) {
		super();
		this.name = name;
	}

	public Airline() {
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
	
	
}
