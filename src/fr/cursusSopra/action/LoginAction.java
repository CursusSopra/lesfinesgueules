package fr.cursusSopra.action;

public class LoginAction extends ActionSupportExtended {
	private String email;
	private String mdp;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String execute() {
		return SUCCESS;
	}
	
	public String control() {
		
		if(email.equals("mail@mail.fr")) {		
			return SUCCESS;
		}
		
		return ERROR;
	}
}
