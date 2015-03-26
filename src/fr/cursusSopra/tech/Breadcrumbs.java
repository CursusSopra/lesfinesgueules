/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.tech;

/**
 * @author Julien Caillon
 */
public class Breadcrumbs {

	private String nom;
	private String action;
	private String queryString;

	public Breadcrumbs(String nom, String action, String queryString) {
		super();
		this.nom = nom;
		this.action = action;
		this.queryString = queryString;
	}

	public String getNom() {
		return nom;
	}

	public String getAction() {
		return action;
	}

	public String getQueryString() {
		return queryString;
	}

}
