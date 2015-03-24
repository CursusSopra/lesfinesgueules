/**
 * File modified by : Benoît
 */
package fr.cursusSopra.json;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Action;

import fr.cursusSopra.model.Produit;

public class JSONListeProduitsAction {
	
	private String idType1 = "-1";
	private String idType2 = "-1";
	private String idProducteur = "-1";
	private List<Produit> listeProduits = new ArrayList<>();

	public JSONListeProduitsAction() throws SQLException {
		long type1 = -2, type2 = -2, producteur = -2;
		
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
/*		
		System.out.println("idType1 = " + type1);
		System.out.println("idType2 = " + type2);
		System.out.println("idProducteur = " + producteur);
*/		
		try {
			setListeProduits(Produit.getListeProduits(type1, type2, producteur));
		} catch (Exception e) {
			System.out.println("getListeProduits impossible.");
		}
	}

	public String execute() {
		return Action.SUCCESS;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}
	
}
