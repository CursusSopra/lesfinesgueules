package fr.cursusSopra.tech;

public class Breadcrumbs {
	
	private String nom;
	private String action;
	
	public Breadcrumbs(String nom, String action) {
		super();
		this.nom = nom;
		this.action = action;
	}

	public String getNom() {
		return nom;
	}
	
	public String getAction() {
		return action;
	}
	
}
