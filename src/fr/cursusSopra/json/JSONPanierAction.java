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

	public String addItem() {
		return Action.SUCCESS;
	}

	public String removeItem() {
		return Action.SUCCESS;
	}

	public String dataPanier() {

		System.out.println("dataPanier??!" + idUtilisateur);

		Commande panier = new Commande(idUtilisateur);

		listeItems = panier.getListeItems();
		nbItems = listeItems.size();

		System.out.println(nbItems);
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

}
