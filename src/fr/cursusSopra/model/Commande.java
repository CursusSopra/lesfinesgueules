package fr.cursusSopra.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Commande {

	private List<ItemCommande> ListeItemsCommandes;

	/**
	 * Constructeur
	 * @param idUtilisateur
	 */
	public Commande(List<ItemCommande> ListeItemsCommandes) {
		this.ListeItemsCommandes = ListeItemsCommandes;
	}

	/**
	 * Recupere la liste des commandes du panier (de l'utilisateur idUtilisateur)
	 */
	public static List<ItemCommande> listerCommandesPanier(long idUtilisateur) {
		return ItemCommande.getListeCommandes(idUtilisateur, -1);
	}

	/**
	 * Recupere une liste de liste des commandes passees groupees par data (de l'utilisateur idUtilisateur)
	 * @return
	 */
	public static List<Commande> listerCommandesPassees(long idUtilisateur) {
		
		// liste a renvoyer
		List<Commande> mylist = new ArrayList<Commande>();
		
		// recupere la liste de tous les items de l'utilisateur
		List<ItemCommande> listeItem = ItemCommande.getListeCommandes(idUtilisateur);

		// liste des dates de validation distinctes
		List<Timestamp> listeDate = new ArrayList<Timestamp>();
		for (ItemCommande item : listeItem) {
			if (!listeDate.contains(item.getTsValidation())) {
				listeDate.add(item.getTsValidation());
				System.out.println("Date : " + item.getTsValidation());
			}
		}
		
		for (Timestamp myDate : listeDate) {
			// creer une liste de ItemCommande avec les items qui correspondent a une date precise et un etat precis
			// puis on l'ajoute à List<Commande>
			List<ItemCommande> listeItemTemp = new ArrayList<ItemCommande>();
			for (ItemCommande item : listeItem) {
				if (item.getEtat() >= 0 && item.getTsValidation() == myDate) {
					listeItemTemp.add(item);
				}
			}
			mylist.add(new Commande(listeItemTemp));
		}
		
		return mylist;
	}

	
	/**
	 * GETTERS / SETTERS
	 */
	public List<ItemCommande> getListeItemsCommandes() {
		return ListeItemsCommandes;
	}

	public void setListeItemsCommandes(List<ItemCommande> listeItemsCommandes) {
		ListeItemsCommandes = listeItemsCommandes;
	}
}
