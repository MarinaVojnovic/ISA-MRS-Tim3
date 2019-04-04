package tim3.spring.project.isamrs.dto;

public class SearchFlightDTO {
	private int startDestination;
	private int finalDestination;
	private String startDate;
	private String endDate;
	
	public int getStartDestination() {
		return startDestination;
	}
	public void setStartDestination(int startDestination) {
		this.startDestination = startDestination;
	}
	public int getFinalDestination() {
		return finalDestination;
	}
	public void setFinalDestination(int finalDestination) {
		this.finalDestination = finalDestination;
	}
	public String getStartDate() {
		return startDate;
	}
	void setStartDate(String startDate) {
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
	
	public SearchFlightDTO(int startDestination, int finalDestination, String startDate, String endDate) {
		super();
		this.startDestination = startDestination;
		this.finalDestination = finalDestination;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	

}
