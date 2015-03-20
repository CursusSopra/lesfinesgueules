package fr.cursusSopra.action;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public abstract class ActionSupportExtended extends ActionSupport {

	private static final long serialVersionUID = -4931119770070210257L;

	private Date dateDuJour;
	private final String lang = "en";
	
	public ActionSupportExtended () {
		
		dateDuJour = new Date();
		
	}

	public Date getDateDuJour() {
		return dateDuJour;
	}

	public String getLang() {
		return lang;
	}
}
	