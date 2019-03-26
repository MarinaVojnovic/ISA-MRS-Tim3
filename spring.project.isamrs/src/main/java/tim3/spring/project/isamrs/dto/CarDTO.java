package tim3.spring.project.isamrs.dto;

import javax.persistence.Column;

public class CarDTO {
	
	private String name;
	private Double price;
	private Integer year;
	
	
	public CarDTO() {
		super();
	}
	
	public CarDTO(String name, Double price, Integer year) {
		super();
		this.name = name;
		this.price = price;
		this.year = year;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
}
