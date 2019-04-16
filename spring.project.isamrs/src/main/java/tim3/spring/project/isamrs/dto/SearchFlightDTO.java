package tim3.spring.project.isamrs.dto;

public class SearchFlightDTO {
	private int startDestination;
	private int finalDestination;
	private String startDate;
	private String endDate;
	private String from;
	private String to;
	private String fromL;
	private String toL;
	private String name;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFromL() {
		return fromL;
	}
	public void setFromL(String fromL) {
		this.fromL = fromL;
	}
	public String getToL() {
		return toL;
	}
	public void setToL(String toL) {
		this.toL = toL;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public SearchFlightDTO(int startDestination, int finalDestination, String startDate, String endDate, String from,
			String to, String fromL, String toL, String name) {
		super();
		this.startDestination = startDestination;
		this.finalDestination = finalDestination;
		this.startDate = startDate;
		this.endDate = endDate;
		this.from = from;
		this.to = to;
		this.fromL = fromL;
		this.toL = toL;
		this.name = name;
	}
	
	
	

}
