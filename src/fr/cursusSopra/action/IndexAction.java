package fr.cursusSopra.action;

import java.sql.SQLException;
import java.util.List;

import fr.cursusSopra.model.Produit;

public class IndexAction extends ActionSupportExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2639338600299902248L;
	
	
	private List<Produit> listeProduitsRandom;
	
	@Override
	public String execute() throws SQLException {
		listeProduitsRandom = Produit.getListeProduitsRandom();
		
		return SUCCESS;
	}

	//ACCESSORS
	public List<Produit> getListeProduitsRandom() {
		return listeProduitsRandom;
	}

	public void setListeProduitsRandom(List<Produit> listeProduitsRandom) {
		this.listeProduitsRandom = listeProduitsRandom;
	}
	
	
}
