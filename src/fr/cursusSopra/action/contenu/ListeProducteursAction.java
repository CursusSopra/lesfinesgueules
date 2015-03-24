/**
 * File modified by : Benoît
 */
package fr.cursusSopra.action.contenu;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Action;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.model.Producteur;

public class ListeProducteursAction extends ActionSupportExtended {

	/* SERIAL ID */
	
	private static final long serialVersionUID = -6208526933135099313L;
	
	/* PROPERTIES*/
	
	private List<Producteur> listeProducteurs = new ArrayList<>();

	/* METHODS */

	@Override
	public String execute() {
		setListeProducteurs();
		return Action.SUCCESS;
	}
	
	/* ACCESSORS */

	public List<Producteur> getListeProducteurs() {
		return listeProducteurs;
	}

	private void setListeProducteurs() {
		listeProducteurs = Producteur.getListeProducteur();
	}
}
