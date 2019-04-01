package tim3.spring.project.isamrs.dto;

public class RegisteredUserDTO {
	String username;
	String password;
	
	public RegisteredUserDTO(){
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RegisteredUserDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
}
