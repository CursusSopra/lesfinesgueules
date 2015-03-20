package fr.cursusSopra.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import fr.cursusSopra.tech.Breadcrumbs;

public abstract class ActionSupportExtended extends ActionSupport {

	private static final long serialVersionUID = -4931119770070210257L;

	private Date dateDuJour;
	private final String lang = "en";
	
	private List<Breadcrumbs> listeBreadcrumbs;
	
	public ActionSupportExtended () {
		
		dateDuJour = new Date();
		listeBreadcrumbs = new ArrayList<Breadcrumbs>();
		
		listeBreadcrumbs.add(new Breadcrumbs("Accueil", "retourIndex"));
		
	}

	public Date getDateDuJour() {
		return dateDuJour;
	}

	public String getLang() {
		return lang;
	}

	public List<Breadcrumbs> getListeBreadcrumbs() {
		return listeBreadcrumbs;
	}
	
	
}
	