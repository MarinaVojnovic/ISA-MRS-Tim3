package tim3.spring.project.isamrs.dto;

public class SearchFlightDTO {
	private String startDestination;
	private String finalDestination;
	private String startDate;
	private String endDate;
	
	public String getStartDestination() {
		return startDestination;
	}
	public void setStartDestination(String startDestination) {
		this.startDestination = startDestination;
	}
	public String getFinalDestination() {
		return finalDestination;
	}
	public void setFinalDestination(String finalDestination) {
		this.finalDestination = finalDestination;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public SearchFlightDTO() {
		super();
	}
	
	public SearchFlightDTO(String startDestination, String finalDestination, String startDate, String endDate) {
		super();
		this.startDestination = startDestination;
		this.finalDestination = finalDestination;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	

}
