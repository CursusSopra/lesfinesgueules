package fr.cursusSopra.action.contenu;

import java.sql.SQLException;
import java.util.List;

import fr.cursusSopra.action.*;
import fr.cursusSopra.model.Produit;

public class ListeProduitsAction extends ActionSupportExtended {
	
	/* Serial ID */
	
	private static final long serialVersionUID = -293629928669269942L;
	
	/* Properties */
	
	private long idType1 = -1;
	private long idType2 = -1;
	private List<Produit> listeProduits;

	/* Execute Method */
	
	public String execute() throws Exception {
		setListeProduits(Produit.getListeProduits(idType1, idType2));
		return SUCCESS;
	}
	
	/* Accessors */

	public void setType1(long type1) {
		this.idType1 = type1;
	}
	
	public void setType2(long type2) {
		this.idType2 = type2;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}
	
}
