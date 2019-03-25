package tim3.spring.project.isamrs.dto;

import tim3.spring.project.isamrs.model.Airline;

public class AirlineDTO {
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private long id;
	private String name;
	
	public AirlineDTO(Airline airline) {
		this.id = airline.getId();
	}
}
