package fr.cursusSopra.model;

public class Commentaire {
	
	/* PROPERTIES */
	
	private String prenom;
	private String avis;
	private int note;
	
	/* CONSTRUCTOR */
	
	public Commentaire(String prenom, String avis, int note) {
		super();
		this.prenom = prenom;
		this.avis = avis;
		this.note = note;
	}

	/* ACCESSORS */
	
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAvis() {
		return avis;
	}

	public void setAvis(String avis) {
		this.avis = avis;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}
	
	
}
