package tim3.spring.project.isamrs.dto;

import java.util.ArrayList;
import java.util.List;

import tim3.spring.project.isamrs.model.Seat;

public class SeatsDTO {
	public List<Seat> firstClass=new ArrayList<Seat>();
	public List<Seat> businessClass=new ArrayList<Seat>();
	public List<Seat> economyClass=new ArrayList<Seat>();
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
