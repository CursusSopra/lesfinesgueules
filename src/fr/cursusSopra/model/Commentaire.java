/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.model;

import java.sql.Timestamp;

/**
 * @author Julien Caillon
 */
public class Commentaire {

	private long idCommentaire = -1;
	private long idUtilisateur;
	private String avis;
	private int note;
	private Timestamp tsCreation;

	/* etat :
	 * -1 comm rejete, 0 en attente de validation, 1 valide et visible
	 */
	private int etat = 0;

	/* isFromDb :
		boolean utilise pour savoir si je suis deja present dans la base (true) ou non (false)
	 */
	private boolean isFromDb = false;

	/**
	 * Creation d'un nouveau commentaire
	 * @param idUtilisateur
	 * @param avis
	 * @param note
	 */
	public Commentaire(long idUtilisateur, String avis, int note) {
		this.idUtilisateur = idUtilisateur;
		this.avis = avis;
		this.note = note;
	}


	/**
	 * Constructeur utilise lors de la recuperation d'un commentaire depuis la bdd
	 * @param idCommentaire
	 * @param idUtilisateur
	 * @param avis
	 * @param note
	 * @param tsCreation
	 * @param etat
	 * @param isFromDb
	 */
	public Commentaire(long idCommentaire, long idUtilisateur, String avis, int note, Timestamp tsCreation, int etat, boolean isFromDb) {
		super();
		this.idCommentaire = idCommentaire;
		this.idUtilisateur = idUtilisateur;
		this.avis = avis;
		this.note = note;
		this.tsCreation = tsCreation;
		this.etat = etat;
		this.isFromDb = isFromDb;
	}



	/**
	 * GETTERS / SETTERS
	 */

	public long getIdCommentaire() {
		return idCommentaire;
	}

	public void setIdCommentaire(long idCommentaire) {
		this.idCommentaire = idCommentaire;
	}

	public long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
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

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public boolean isFromDb() {
		return isFromDb;
	}

	public void setFromDb(boolean isFromDb) {
		this.isFromDb = isFromDb;
	}

	public Timestamp getTsCreation() {
		return tsCreation;
	}

	public void setTsCreation(Timestamp tsCreation) {
		this.tsCreation = tsCreation;
	}

}
