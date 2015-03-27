/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.action.utilisateurs;


import java.util.List;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.model.Commande;

public class CommandeAction extends ActionSupportExtended {

	private static final long serialVersionUID = 1L;

	private List<Commande> listeCommandesPassees;

	public CommandeAction() {

		listeCommandesPassees = Commande.listerCommandesPassees(idUtilisateur);

	}

	public List<Commande> getListeCommandesPassees() {
		return listeCommandesPassees;
	}

}
