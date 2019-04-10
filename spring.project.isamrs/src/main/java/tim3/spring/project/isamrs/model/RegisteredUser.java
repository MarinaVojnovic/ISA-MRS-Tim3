package tim3.spring.project.isamrs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(catalog = "dbtim3", name = "registered_users")
public class RegisteredUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	String name;
	
	@Column(name = "surname")
	String surname;
	
	@Column(name = "email")
	String email;
	
	@Column(name = "username")
	String username;
	
	@Column(name = "password")
	String password;
	
	@Column(name="city")
	String city;
	
	@Column(name="phone")
	String phone;
	
	@Column(name="firstTime")
	Boolean firstTime;
	
	public Boolean getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Boolean firstTime) {
		this.firstTime = firstTime;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public RegisteredUser() {
		
	}
	
	public RegisteredUser(String name, String surname, String email, String username, String password, String city, String phone, Boolean firstTime) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username=username;
		this.password = password;
		this.city=city;
		this.phone=phone;
		this.firstTime=firstTime;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
