/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.action.utilisateurs;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.model.Commande;
import fr.cursusSopra.tech.EtatCommande;

public class PanierAction extends ActionSupportExtended {

	private static final long serialVersionUID = -7191306713805918718L;

	private boolean updateSuccessful = false;
	private Commande panier;
	private int moyenPaiement;

	public String validerCommande() {
		// changer l'etat de la commande
		panier = new Commande(idUtilisateur);
		panier.setEtat(EtatCommande.VALIDEE);
		panier.setMoyenPaiement(moyenPaiement);
		panier.save();
		updateSuccessful = true;
		return SUCCESS;
	}

	public Commande getPanier() {
		return panier;
	}

	public boolean isUpdateSuccessful() {
		return updateSuccessful;
	}

	public int getMoyenPaiement() {
		return moyenPaiement;
	}

	public void setMoyenPaiement(int moyenPaiement) {
		this.moyenPaiement = moyenPaiement;
	}
}
