package fr.cursusSopra.action.contenu;

import java.util.List;

import fr.cursusSopra.action.*;
import fr.cursusSopra.model.Produit;

public class ListeProduitsAction extends ActionSupportExtended {
	
	/* Serial ID */
	
	private static final long serialVersionUID = -293629928669269942L;
	
	/* Properties */
	
	
	private String idType1 = "-1";
	private String idType2 = "-1";
	private String idProducteur = "-1";

	private List<Produit> listeProduits;

	/* Execute Method */
	
	public String execute() {
	/*	long type1 = -2, type2 = -2, producteur = -2;
		
		try {
			type1 = Long.parseLong(idType1);
		} catch (NumberFormatException e) {
			type1 = -1;
			System.out.println("Conversion String > Long impossible pour idType1.");
		}
		try {
			type2 = Long.parseLong(idType2);
		} catch (NumberFormatException e) {
			type2 = -1;
			System.out.println("Conversion String > Long impossible pour idType2.");
		}
		try {
			producteur = Long.parseLong(idProducteur);
		} catch (NumberFormatException e) {
			type2 = -1;
			System.out.println("Conversion String > Long impossible pour idType2.");
		}
		
		System.out.println("idType1 = " + type1);
		System.out.println("idType2 = " + type2);
		System.out.println("idProducteur = " + producteur);
		
		try {
			setListeProduits(Produit.getListeProduits(type1, type2, producteur));
		} catch (Exception e) {
			System.out.println("getListeProduits impossible.");
		}
	*/
		return SUCCESS;
	}
	
	/* Accessors */

	public void setIdType1(String idType1) {
		this.idType1 = idType1;
	}
	
	public void setIdType2(String idType2) {
		this.idType2 = idType2;
	}
	
	public void setIdProducteur(String idProducteur) {
		this.idProducteur = idProducteur;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}
	
}
