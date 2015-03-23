package fr.cursusSopra.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.dataLayer.utilisateurs.ItemCommandeDal;
import fr.cursusSopra.tech.PostgresConnection;

public class ItemCommande {

	private long id = -1;
	private long idUtilisateur;
	private long idProduit;
	
	/* etat :
	  -1 = commande dans le panier, non validee
		0 = commande validee en cours de livraison
		1 = commande livree, archivee
	 */
	private int etat;
	private int quantite;
	private int moyenPaiement;
	private Timestamp tsCreation;
	private Timestamp tsValidation;
	private Timestamp tsArchivage;
	
	/* isFromDb :
	  	boolean utilise pour savoir si cet itemcommande est deja present dans la base (true) ou non (false)
	 */
	private boolean isFromDb = false;
	
	/**
	 * recuperer un itemcommande dans la base via son id
	 * @param id
	 */
	public ItemCommande(long id) {
		this.id = id;

		try {
			new ItemCommandeDal(this, id);
		} catch (SQLException e) {
			System.err.print(String.format("ERREUR : Impossible de trouver l'id : %d .\n", id));
		}
	}
	
	/**
	 *  creation d'un itemcommande
	 * @param idUtilisateur
	 * @param idProduit
	 * @param quantite
	 */
	public ItemCommande(long idUtilisateur, long idProduit, int quantite) {
		this.idUtilisateur = idUtilisateur;
		this.idProduit = idProduit;
		this.quantite = quantite;
		
		// par defaut, la commande est dans le panier, non validee
		this.etat = -1;
	}
	
	
	/**
	 *  Sauvegarde de l'item (ou update si deja existant) dans la db
	 * @return long qui contient l'id de l'item sauver (ou 0 si le save est un echec)
	 */
	public long save() {

		// On obtient l'objet connection à la BDD
		Connection connection = PostgresConnection.GetConnexion();
		
		try {
			// On commence la transaction
			// On passe en mode non auto-commit
			connection.setAutoCommit(false);

			id = new ItemCommandeDal(this).save();
			
			// On committe toutes les mises à jour
			connection.commit();
			
		} catch (SQLException e) {
			if (connection != null) {
	            try {
	            	// Si il y a eu une erreur durant l'une des requetes "insert into" alors on fait un roll back
	                System.err.print("La transaction est annulée.\n");
	                id = -1;	                
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
		
		// save what only belongs to the class "cinema"
		return id;
	}
	
	
	/**
	 *  delete l'itemCommande dans la base
	 * @return boolean avec true si reussite et false si echec
	 */
	public boolean delete() {
		boolean isDeleted = false;
		try {
			isDeleted = new ItemCommandeDal(this).delete();
		} catch (SQLException e) {
			System.err.print(String.format("ERREUR : Impossible de supprimer l'itemcommande id : %d .\n", id));
		}
		return isDeleted;
	}
	
	
	/**
	 *  retourne la liste des commandes existantes dans la db pour un utilisateur specifique
	 * @param id
	 * @return List<ItemCommande>
	 */
	public static List<ItemCommande> getListeCommandes(long id) {
		List<ItemCommande> mylist = new ArrayList<ItemCommande>();
		try {
			mylist = ItemCommandeDal.getListeCommandes(id, 2);
		} catch (SQLException e) {
			System.err.print(String.format("ERREUR : Impossible de recuperer les commande de l'id : %d .\n", id));
		}
		return mylist;
	}
	
	
	/**
	 * retourne la liste des commandes existantes dans la db pour un utilisateur specifique et un etat specifique
	 * @param id
	 * @param etat
	 * @return List<ItemCommande>
	 */
	public static List<ItemCommande> getListeCommandes(long id, int etat) {
		List<ItemCommande> mylist = new ArrayList<ItemCommande>();
		try {
			mylist = ItemCommandeDal.getListeCommandes(id, etat);
		} catch (SQLException e) {
			System.err.print(String.format("ERREUR : Impossible de recuperer les commande de l'id : %d .\n", id));
		}
		return mylist;
	}
	
	
	/**
	 * GETTERS / SETTERS
	 */
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public long getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public int getMoyenPaiement() {
		return moyenPaiement;
	}
	public void setMoyenPaiement(int moyenPaiement) {
		this.moyenPaiement = moyenPaiement;
	}
	public Timestamp getTsCreation() {
		return tsCreation;
	}
	public void setTsCreation(Timestamp tsCreation) {
		this.tsCreation = tsCreation;
	}
	public Timestamp getTsValidation() {
		return tsValidation;
	}
	public void setTsValidation(Timestamp tsValidation) {
		this.tsValidation = tsValidation;
	}
	public Timestamp getTsArchivage() {
		return tsArchivage;
	}
	public void setTsArchivage(Timestamp tsArchivage) {
		this.tsArchivage = tsArchivage;
	}

	public boolean isFromDb() {
		return isFromDb;
	}

	public void setFromDb(boolean isFromDb) {
		this.isFromDb = isFromDb;
	}
	
}
