package tim3.spring.project.isamrs.dto;

public class CarDTO {

	private long id;
	private String name;
	private Double price;
	private Integer year;

	public CarDTO() {
		super();
	}

	public CarDTO(String name, Double price, Integer year, Long id) {
		super();
		this.id = id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
