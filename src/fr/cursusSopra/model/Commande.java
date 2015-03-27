/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.dataLayer.CommandeDal;
import fr.cursusSopra.dataLayer.ItemCommandeDal;
import fr.cursusSopra.tech.EtatCommande;
import fr.cursusSopra.tech.PostgresConnection;
/**
 * @author Julien Caillon
 */
public class Commande {

	private long idCommande = -1;
	private long idUtilisateur;
	private EtatCommande etat = EtatCommande.PANIER;
	private Timestamp tsValidation;
	private Timestamp tsArchivage;
	private int moyenPaiement;

	private double coutTotal = 0;
	private double fraisPort = 0;

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
			System.err.print(String.format("ERREUR 01 : Impossible de trouver la commande l'user id : %d .\n", idUtilisateur));
		}
		// maybe we didn't find the commande panier in the db? np we still have an object "commande" correctly instanciated
		this.idUtilisateur = idUtilisateur;
		// get la liste des items enfants
		getMyListOfItems();
		coutTotal = this.calculTotalPrixCommande();
		fraisPort = this.calculFraisDePort();
	}


	/**
	 * recupere un objet commande definie dans la bdd avec l'id idCommande (utilise dans listerCommandesPassees!)
	 * @param idUtilisateur
	 * @param idCommande
	 */
	public Commande(long idUtilisateur, long idCommande) {
		try {
			new CommandeDal(this).select(idUtilisateur, idCommande);
		} catch (SQLException e) {
			System.err.print(String.format("ERREUR 02 : Impossible de trouver la commande id : %d .\n", idCommande));
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
			System.err.print(String.format("ERREUR 03 : lors de l'importation des items de la commande id : %d .\n", idCommande));
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
			System.err.print(String.format("ERREUR 04 : lors de l'importation des commandes passees de l'user id : %d .\n", idUtilisateur));
			e.printStackTrace();
		}
		return mylist;
	}


	/**
	 * Ajout d'un item a la commande
	 * @param idProduit
	 * @param quantite
	 */
	public void addItemCommande(long idProduit, int quantite) {
		// can only modify the panier! :p
		if (etat != EtatCommande.PANIER) return;

		// soit on ajoute la nouvelle quantite a un item existant, soit un cree un item
		boolean found = false;
		for (ItemCommande item : ListeItems) {
			if (item.getIdProduit() == idProduit) {
				found = true;
				quantite = quantite + item.getQuantite();
				if (quantite <= 999) {
					item.setQuantite(quantite);
				} else {
					quantite = 999;
				}
				this.save();
			}
		}
		if (!found) {
			ListeItems.add(new ItemCommande(idProduit, quantite));
			this.save();
			getMyListOfItems();
		}
	}


	/**
	 * Suppression d'un item de la commande
	 * @param idProduit
	 * @param quantite
	 */
	public void removeItemCommande(long idProduit, int quantite) {
		// can only modify the panier! :p
		if (etat != EtatCommande.PANIER) return;

		for (ItemCommande item : ListeItems) {
			if (item.getIdProduit() == idProduit) {
				quantite = item.getQuantite() - quantite;
				if (quantite > 0) {
					item.setQuantite(quantite);
					this.save();
				} else {
					//  on supprime l'item de la base et on recree la liste des items de cette commande ensuite
					try {
						new ItemCommandeDal(new ItemCommande(item.getIdItemCommande())).delete();
					} catch (SQLException e) {
						System.err.print(String.format("ERREUR 05 : lors de la suppression de l'item id : %d .\n", item.getIdItemCommande()));
					}
					getMyListOfItems();
				}
			}
		}
	}


	/**
	 * calcul le prix total de this commande
	 * @return
	 */
	public double calculTotalPrixCommande() {
		double coutTot = -1;
		try {
			coutTot = new CommandeDal(this).totalPrixCommande();
		} catch (SQLException e) {
			System.err.print(String.format("ERREUR 08 : lors du calcul du prix de la commande id : %d .\n", this.getIdCommande()));
		}
		return coutTot;
	}


	/** calcul les frais de port
	 * frais de port = 5% du prix total, ou 0 apres 50euros
	 * @return
	 */
	public double calculFraisDePort() {
		double coutTot = this.calculTotalPrixCommande();
		return (coutTot > 50) ? 0 : coutTot * 0.05;
	}


	/**
	 *  Sauvegarde de l'item (ou update si deja existant) dans la db
	 * @return long qui contient l'id de l'item sauver (ou 0 si le save est un echec)
	 */
	public long save() {

		// On obtient l'objet connection à la BDD
		Connection connection = PostgresConnection.GetConnexion();

		try {
			// On commence la transaction, on passe en mode non auto-commit
			connection.setAutoCommit(false);

			// on essaie de sauver la commande
			idCommande = new CommandeDal(this).save();

			// on essaie de save les items de cette commande
			for(ItemCommande item : ListeItems) {
				new ItemCommandeDal(item).save(idCommande, connection);
			}

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
				connection.close();
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

			// on essaie de delete les items de cette commande (inutile avec les delete on cascade des cles etrangeres mais bon..)
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
				connection.close();
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

	public String getTsValidation() {
		return new SimpleDateFormat("yyyy-MM-dd @ HH:mm").format(tsValidation);
	}

	public void setTsValidation(Timestamp tsValidation) {
		this.tsValidation = tsValidation;
	}

	public String getTsArchivage() {
		return new SimpleDateFormat("yyyy-MM-dd @ HH:mm").format(tsArchivage);
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

	public EtatCommande getEtat() {
		return etat;
	}

	public void setEtat(EtatCommande etat) {
		this.etat = etat;
	}

	public void setEtat(int etat) {
		this.etat = EtatCommande.intToEtatCommande(etat);
	}

	public double getCoutTotal() {
		return coutTotal;
	}

	public double getFraisPort() {
		return fraisPort;
	}

}
