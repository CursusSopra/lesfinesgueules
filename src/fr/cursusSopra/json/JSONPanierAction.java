/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.json;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Action;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.model.Commande;
import fr.cursusSopra.model.ItemCommande;
/**
 * @author Julien Caillon
 */
public class JSONPanierAction extends ActionSupportExtended {

	private static final long serialVersionUID = -1505158767409145518L;

	private int nbItems = 0;
	private List<ItemCommande> listeItems = new ArrayList<ItemCommande>();
	private double coutTotal = 0;

	// proprietes utilisees pour ajouter et enlever un item
	private boolean updateSuccessful = false;
	private long idProduit;
	private int quantite;

	public String addItem() {
		System.out.println("je dois ajouter " + quantite + " fois, le produit id " + idProduit);
		updateSuccessful = true;
		return Action.SUCCESS;
	}

	public String removeItem() {
		System.out.println("je dois enlever " + quantite + " fois, le produit id " + idProduit);
		updateSuccessful = true;
		return Action.SUCCESS;
	}

	/**
	 * envoi les data necessaire pour creer le panier de l'utilisateur dans la navBar
	 * @return
	 */
	public String dataPanier() {
		Commande panier = new Commande(idUtilisateur);
		listeItems = panier.getListeItems();
		nbItems = listeItems.size();
		coutTotal = panier.totalPrixCommande();
		return Action.SUCCESS;
	}


	/**
	 * GETTERS / SETTERS
	 */

	public int getNbItems() {
		return nbItems;
	}

	public List<ItemCommande> getListeItems() {
		return listeItems;
	}

	public double getCoutTotal() {
		return coutTotal;
	}

	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public boolean isUpdateSuccessful() {
		return updateSuccessful;
	}
}
