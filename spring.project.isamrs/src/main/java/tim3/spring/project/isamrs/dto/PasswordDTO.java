package tim3.spring.project.isamrs.dto;

public class PasswordDTO {
	private String password;

	public PasswordDTO() {
		
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PasswordDTO(String password) {
		super();
		this.password = password;
	}
	
}
