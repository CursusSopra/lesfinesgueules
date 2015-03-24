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


	public static List<Commentaire> getListeCommsProd(long idProduit) {
		List<Commentaire> mylist = new ArrayList<Commentaire>();
		try {
			mylist = CommentaireDal.getListeCommsDal(idProduit);
		} catch (SQLException e) {
			System.err.print(String.format("ERREUR 01 : lors de l'importation des commentaires du produit id : %d .\n", idProduit));
			e.printStackTrace();
		}
		return mylist;
	}


	/**
	 *  Sauvegarde de l'item (ou update si deja existant) dans la db
	 * @return long qui contient l'id de l'item sauver (ou -1 si le save est un echec)
	 */
	public long save() {

		// On obtient l'objet connection à la BDD
		Connection connection = PostgresConnection.GetConnexion();

		try {
			// On commence la transaction
			// On passe en mode non auto-commit
			connection.setAutoCommit(false);

			// on essaie de sauver la commande
			idCommentaire = new CommentaireDal(this).save();

			// On committe toutes les mises à jour
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
	            try {
	            	// Si il y a eu une erreur durant l'une des requetes "insert into" alors on fait un roll back
	                System.err.print("ERREUR 06 : La transaction est annulée.\n");
	                connection.rollback();
	    			e.printStackTrace();
	            } catch(SQLException e2) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	            }
			}
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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

		// On obtient l'objet connection à la BDD
		Connection connection = PostgresConnection.GetConnexion();

		try {
			// On commence la transaction
			// On passe en mode non auto-commit
			connection.setAutoCommit(false);

			// ensuite on essaie de delete la commande
			isDeleted = new CommentaireDal(this).delete();

			// On committe toutes les mises à jour
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
	            try {
	            	// Si il y a eu une erreur durant l'une des requetes "insert into" alors on fait un roll back
	                System.err.print("ERREUR 07 : La transaction est annulée.\n");
	                connection.rollback();
	    			e.printStackTrace();
	            } catch(SQLException e2) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	            }
			}
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

}
