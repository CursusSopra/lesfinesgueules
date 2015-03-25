package fr.cursusSopra.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

public class LoginAction extends ActionSupportExtended implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3771868804107524884L;

	private HttpServletRequest request;

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

		if (email.equals("mail@mail.fr")) {
			HttpSession session = request.getSession();
			session.setAttribute("authorized", "yes");
			return SUCCESS;
		}

		return ERROR;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}
}
