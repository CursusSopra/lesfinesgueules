/**
 *  Modified By Julien J
 */
package fr.cursusSopra.action.tests;

import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.model.Produit;
import fr.cursusSopra.model.Type1;

public class InlineFormAction extends ActionSupportExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8950811738996199296L;
	
	private List<Type1> listeType1;
	private long idType1 = -1;
	private long idType2 = -1;
	private long idProducteur = -1;
	private List<Produit> listeProduits = new ArrayList<>();
	
	@Override
	public String execute() {
		try {
			setListeProduits(Produit.getListeProduits(idType1, idType2, idProducteur));
		} catch (Exception e) {
			System.out.println("getListeProduits impossible.");
		}
		return SUCCESS;
	}

	@Override
	public List<Type1> getListeType1() {
		return listeType1;
	}

	public void setListeType1(List<Type1> listeType1) {
		this.listeType1 = listeType1;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}
	
	
}
