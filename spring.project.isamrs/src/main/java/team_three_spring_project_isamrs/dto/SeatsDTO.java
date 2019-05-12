package team_three_spring_project_isamrs.dto;

import java.util.ArrayList;
import java.util.List;

import team_three_spring_project_isamrs.model.Seat;

public class SeatsDTO {
	private List<Seat> firstClass = new ArrayList<>();
	private List<Seat> businessClass = new ArrayList<>();
	private List<Seat> economyClass = new ArrayList<>();

	public SeatsDTO(List<Seat> firstClass, List<Seat> businessClass, List<Seat> economyClass) {
		super();
		this.firstClass = firstClass;
		this.businessClass = businessClass;
		this.economyClass = economyClass;
	}

	public SeatsDTO() {
		super();
	}

	public List<Seat> getFirstClass() {
		return firstClass;
	}

	public void setFirstClass(List<Seat> firstClass) {
		this.firstClass = firstClass;
	}

	public List<Seat> getBusinessClass() {
		return businessClass;
	}

	public void setBusinessClass(List<Seat> businessClass) {
		this.businessClass = businessClass;
	}

	public List<Seat> getEconomyClass() {
		return economyClass;
	}

	public void setEconomyClass(List<Seat> economyClass) {
		this.economyClass = economyClass;
	}

}
