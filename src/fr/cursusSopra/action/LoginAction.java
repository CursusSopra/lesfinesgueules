/**
 * Modified by Cecile
 */
package fr.cursusSopra.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import fr.cursusSopra.model.Utilisateur;

public class LoginAction extends ActionSupportExtended implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3771868804107524884L;
	
	private static final Logger logger = LogManager
			.getLogger(LoginAction.class);

	private HttpServletRequest request;

	private String email;
	private String mdp;
	private String savedURL;


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getSavedURL() {
		return savedURL;
	}

	@Override
	public String execute() {
		return SUCCESS;
	}

	public String control() {
		HttpSession session = request.getSession();
		
		if (Utilisateur.isInBase(email, mdp)) {
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
