/**
 * File modified by : Benoît
 */
package fr.cursusSopra.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.dataLayer.CommandeDal;
import fr.cursusSopra.dataLayer.ItemCommandeDal;
import fr.cursusSopra.tech.PostgresConnection;
/**
 * @author Julien C
 */
public class Commande {

	private long idCommande = -1;
	private long idUtilisateur;
	
	/* etat :
	  -1 = commande dans le panier, non validee
		0 = commande validee en cours de livraison
		1 = commande livree, archivee
	 */
	private int etat = -1;
	private Timestamp tsValidation;
	private Timestamp tsArchivage;
	private int moyenPaiement;
	
	/* isFromDb :
  		boolean utilise pour savoir si je suis deja present dans la base (true) ou non (false)
	 */
	private boolean isFromDb = false;
	
	// une commande a sa liste des itemsCommandes
	private List<ItemCommande> ListeItems;

	
	/**
	 * cree un objet commande PANIER de l'utilisateur (etat = -1)
	 * @param idCommande
	 */
	public Commande(long idUtilisateur) {
		try {
			new CommandeDal(this).utilisateur(idUtilisateur);
		} catch (SQLException e) {
			System.err.print(String.format("ERREUR : Impossible de trouver la commande l'user id : %d .\n", idUtilisateur));
		}
		// maybe we didn't find the commande panier in the db? np we still have an object "commande" correctly instanciated
		this.idUtilisateur = idUtilisateur;
		// get la liste des items enfants
		getMyListOfItems();
	}
	
	
	/**
	 * recupere un objet commande definie dans la bdd avec l'id id
	 * @param idUtilisateur
	 * @param idCommande
	 */
	public Commande(long idUtilisateur, long idCommande) {
		try {
			new CommandeDal(this).select(idUtilisateur, idCommande);
		} catch (SQLException e) {
			System.err.print(String.format("ERREUR : Impossible de trouver la commande id : %d .\n", idCommande));
		}
		// get la liste des items enfants
		getMyListOfItems();
	}
	
	
	/**
	 * enrichie la liste "ListeItems" avec la liste des items appartenant a this Commande
	 */
	public void getMyListOfItems() {
		ListeItems = new ArrayList<ItemCommande>();
		try {
			ListeItems = ItemCommandeDal.getListeItemsCommandes(idCommande);
		} catch (SQLException e) {
			System.err.print(String.format("ERREUR : lors de l'importation des items de la commande id : %d .\n", idCommande));
		}
	}
	
	
	/**
	 * renvoi une liste des commandes passees par l'utilisateur idUtilisateur (deja validees ou archivees, pas la commande panier)
	 * @return
	 */
	public static List<Commande> listerCommandesPassees(long idUtilisateur) {
		List<Commande> mylist = new ArrayList<Commande>();
		try {
			mylist = CommandeDal.getListeCommandes(idUtilisateur);
		} catch (SQLException e) {
			System.err.print(String.format("ERREUR : lors de l'importation des commandes de l'user id : %d .\n", idUtilisateur));
		}
		return mylist;
	}
	
	
	/**
	 * Ajout d'un item a la commande
	 * @param idProduit
	 * @param idCommande
	 * @param quantite
	 */
	public void addItemCommande(long idProduit, long idCommande, int quantite) {
		//TODO : check if etat = -1 otherwise leave
		ListeItems.add(new ItemCommande(idProduit, idCommande, quantite));
	}
	
	
	/**
	 * Suppression d'un item de la commande
	 * @param idItemCommande
	 */
	public void deleteItemCommande(long idItemCommande) {
		//  on supprime l'item de la base et on recree la liste des items de cette commande ensuite
		ItemCommande ic = new ItemCommande(idItemCommande);
		try {
			new ItemCommandeDal(ic).delete();
		} catch (SQLException e) {
			System.err.print(String.format("ERREUR : lors de la suppression de l'item id : %d .\n", idItemCommande));
		}
		getMyListOfItems();
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

			// on essaie de save les items de cette commande
			for(ItemCommande item : ListeItems) {
				new ItemCommandeDal(item).save();
			}
			
			// ensuite on essaie de sauver la commande
			idCommande = new CommandeDal(this).save();
			
			// On committe toutes les mises à jour
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
	            try {
	            	// Si il y a eu une erreur durant l'une des requetes "insert into" alors on fait un roll back
	                System.err.print("La transaction est annulée.\n");
	                idCommande = -1;	                
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
		
		return idCommande;
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

			// on essaie de save les items de cette commande
			for(ItemCommande item : ListeItems) {
				new ItemCommandeDal(item).delete();
			}
			
			// ensuite on essaie de delete la commande
			isDeleted = new CommandeDal(this).delete();
			
			// On committe toutes les mises à jour
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
	            try {
	            	// Si il y a eu une erreur durant l'une des requetes "insert into" alors on fait un roll back
	                System.err.print("La transaction est annulée.\n");
	                idCommande = -1;	                
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

	public long getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(long id) {
		this.idCommande = id;
	}

	public long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
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

	public int getMoyenPaiement() {
		return moyenPaiement;
	}

	public void setMoyenPaiement(int moyenPaiement) {
		this.moyenPaiement = moyenPaiement;
	}

	public boolean isFromDb() {
		return isFromDb;
	}

	public void setFromDb(boolean isFromDb) {
		this.isFromDb = isFromDb;
	}

	public List<ItemCommande> getListeItems() {
		return ListeItems;
	}
}
