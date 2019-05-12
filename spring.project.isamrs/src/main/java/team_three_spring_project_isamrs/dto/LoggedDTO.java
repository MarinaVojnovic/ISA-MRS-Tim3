package team_three_spring_project_isamrs.dto;

public class LoggedDTO {
	private String token;

	public LoggedDTO(String token) {
		super();
		this.token = token;
	}

	public LoggedDTO() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
