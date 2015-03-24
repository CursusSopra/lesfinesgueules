/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.dataLayer.CommentaireDal;
import fr.cursusSopra.tech.PostgresConnection;
import fr.cursusSopra.tech.TypeCommentaire;

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

	private long idType;
	private TypeCommentaire type;


	/**
	 * Creation d'un nouveau commentaire
	 * @param idUtilisateur
	 * @param avis
	 * @param note
	 */
	public Commentaire(long idType, long idUtilisateur, String avis, int note, TypeCommentaire type) {
		this.idType = idType;
		this.idUtilisateur = idUtilisateur;
		this.avis = avis;
		this.note = note;
		this.type = type;
	}


	/**
	 * Constructeur utilise lors de la recuperation d'un commentaire depuis la bdd (utilise dans getListeComms du DAL)
	 * @param idType
	 * @param idCommentaire
	 * @param idUtilisateur
	 * @param avis
	 * @param note
	 * @param tsCreation
	 * @param etat
	 * @param isFromDb
	 */
	public Commentaire(long idType, long idCommentaire, long idUtilisateur, String avis, int note, Timestamp tsCreation, int etat, boolean isFromDb, TypeCommentaire type) {
		this.idType = idType;
		this.idCommentaire = idCommentaire;
		this.idUtilisateur = idUtilisateur;
		this.avis = avis;
		this.note = note;
		this.tsCreation = tsCreation;
		this.etat = etat;
		this.isFromDb = isFromDb;
		this.type = type;
	}


	public static List<Commentaire> getListeCommentaires(long idType, TypeCommentaire type) {
		List<Commentaire> mylist = new ArrayList<Commentaire>();
		try {
			mylist = CommentaireDal.getListeCommsDal(idType, type);
		} catch (SQLException e) {
			System.err.print(String.format("ERREUR 01 : lors de l'importation des commentaires du type id : %d .\n", idType));
			e.printStackTrace();
		}
		return mylist;
	}


	/**
	 *  Sauvegarde de l'item (ou update si deja existant) dans la db
	 * @return long qui contient l'id de l'item sauver (ou -1 si le save est un echec)
	 */
	public long save() {
		Connection connection = PostgresConnection.GetConnexion();
		try {
			// On commence la transaction, on passe en mode non auto-commit
			connection.setAutoCommit(false);

			idCommentaire = new CommentaireDal(this).save();

			// On committe toutes les mises à jour
			connection.commit();
		} catch (SQLException e) {
			System.err.println("ERREUR 03 : lors de la sauvegarde d'un comm");
	    	e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return idCommentaire;
	}


	/**
	 *  delete l'itemCommande dans la base
	 * @return boolean avec true si reussite et false si echec
	 */
	public boolean delete() {
		boolean isDeleted = false;
		try {
			isDeleted = new CommentaireDal(this).delete();
		} catch (SQLException e) {
			System.err.print(String.format("ERREUR 01 : lors du delete du comm id : %d .\n", idCommentaire));
	    	e.printStackTrace();
		}
		return isDeleted;
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

	public long getIdType() {
		return idType;
	}

	public void setIdType(long idProduit) {
		this.idType = idProduit;
	}

	public TypeCommentaire getType() {
		return type;
	}

	public void setType(TypeCommentaire type) {
		this.type = type;
	}

}
