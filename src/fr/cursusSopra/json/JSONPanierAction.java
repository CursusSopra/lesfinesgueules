/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.json;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Action;

import fr.cursusSopra.action.ActionSupportExtendedJSONOnly;
import fr.cursusSopra.model.Commande;
import fr.cursusSopra.model.ItemCommande;
/**
 * @author Julien Caillon
 */
public class JSONPanierAction extends ActionSupportExtendedJSONOnly {

	private static final long serialVersionUID = -1505158767409145518L;

	// proprietes renvoyees dans le JSON
	private int nbItems = 0;
	private List<ItemCommande> listeItems = new ArrayList<ItemCommande>();
	private double coutTotal = 0;
	private double fraisPort = 0;
	private boolean updateSuccessful = false;

	// proprietes utilisees pour ajouter et enlever un item
	private long idProduit;
	private int quantite;

	private Commande panier;

	/**
	 * Instancie le panier de l'utilisateur courant
	 * @return
	 */
	public JSONPanierAction() {
		panier = new Commande(idUtilisateur);
	}

	/** ajoute un item idProduit, x quantite
	 * @return
	 */
	public String addItem() {
		panier.addItemCommande(idProduit, quantite);
		computePanier();
		updateSuccessful = true;
		return Action.SUCCESS;
	}

	/** enleve un item idProduit, x quantite
	 * @return
	 */
	public String removeItem() {
		panier.removeItemCommande(idProduit, quantite);
		computePanier();
		updateSuccessful = true;
		return Action.SUCCESS;
	}

	public String dataPanier() {
		computePanier();
		return Action.SUCCESS;
	}

	/** calcul les valeurs a renvoyees dans le JSON
	 *
	 */
	public void computePanier() {
		listeItems = panier.getListeItems();
		nbItems = listeItems.size();
		coutTotal = panier.calculTotalPrixCommande();
		fraisPort = panier.calculFraisDePort();
	}


	/**
	 * GETTERS, donnees renvoyees dans le JSON
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

	public double getFraisPort() {
		return fraisPort;
	}

	public boolean isUpdateSuccessful() {
		return updateSuccessful;
	}


	/**
	 * SETTERS, donnees envoyees par l'utilisateur
	 */
	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}
