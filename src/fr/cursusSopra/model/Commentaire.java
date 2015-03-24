package fr.cursusSopra.model;

import java.sql.SQLException;

import fr.cursusSopra.dataLayer.utilisateurs.CommentaireDal;


public class Commentaire {
	
	
	private String avis;
	private int note;
	private int etat;
	
	
	//constructeur
	public Commentaire(String avis, int note, int etat) {
		this.avis = avis;
		this.note = note;
		this.etat = etat;
	}

	//accesseurs
	public String getAvis() {return avis;}
	public void setAvis(String avis) {this.avis = avis;}
	public int getNote() {return note;}
	public void setNote(int note) {this.note = note;}

	//sauvegarde en bdd
	public void save() throws SQLException{
		CommentaireDal cd = new CommentaireDal(avis, note, etat);
		cd.save();
	}

}
