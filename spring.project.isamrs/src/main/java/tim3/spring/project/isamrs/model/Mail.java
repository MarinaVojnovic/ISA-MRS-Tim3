package tim3.spring.project.isamrs.model;

public class Mail {
	public String emailAddress;
	public String subject;
	public String body;
	
	Mail(){
		
	}
	
	Mail(String emailAddress, String subject, String body){
		this.emailAddress=emailAddress;
		this.subject=subject;
		this.body=body;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}