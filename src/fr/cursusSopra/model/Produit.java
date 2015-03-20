package fr.cursusSopra.model;

import java.util.List;

public class Produit {
	
	/* PROPERTIES */
	
	private String designation;
	private String description;
	private Number prix;
	private String photo;
	private boolean disponible;
	private String type1;
	private String type2;
	private List<Commentaire> listeCommentaires;
	
	/* CONSTRUCTOR */
	
	public Produit(String designation, String description, Number prix, String photo, boolean disponible, String type1, String type2) {
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.photo = photo;
		this.disponible = disponible;
		this.type1 = type1;
		this.type2 = type2;
	}

	/* ACCESSORS */

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Number getPrix() {
		return prix;
	}

	public void setPrix(Number prix) {
		this.prix = prix;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}
	
}
