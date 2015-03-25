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
	
	private long idType1 = -1;
	private long idType2 = -1;
	private long idProducteur = -1;
	private List<Produit> listeProduits = new ArrayList<>();

	public JSONListeProduitsAction() throws SQLException {
//		System.out.println("idType1 = " + idType1);
//		System.out.println("idType2 = " + idType2);
//		System.out.println("idProducteur = " + idProducteur);
	}

	public String execute() {
		try {
			setListeProduits(Produit.getListeProduits(idType1, idType2, idProducteur));
		} catch (Exception e) {
			System.out.println("getListeProduits impossible.");
		}
		return Action.SUCCESS;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	private void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}
	
	public void setIdType1(String idType1) {
		try {
			this.idType1 = Long.parseLong(idType1);
			System.out.println("Conversion String > Long faite pour idType1 | idType1 = " + this.idType1);
		} catch (NumberFormatException e) {
			this.idType1 = -1;
			e.printStackTrace();
			System.out.println("Conversion String > Long impossible pour idType1.");
		}
	}
	
	public void setIdType2(String idType2) {
		try {
			this.idType2 = Long.parseLong(idType2);
			System.out.println("Conversion String > Long faite pour idType2 | idType2 = " + this.idType2);
		} catch (NumberFormatException e) {
			this.idType2 = -1;
			System.out.println("Conversion String > Long impossible pour idType2.");
		}
	}
	
	public void setIdProducteur(String idProducteur) {
		try {
			this.idProducteur = Long.parseLong(idProducteur);
			System.out.println("Conversion String > Long faite pour idProducteur | idProducteur = " + this.idProducteur);
		} catch (NumberFormatException e) {
			this.idProducteur = -1;
			System.out.println("Conversion String > Long impossible pour idProducteur.");
		}
	}
	
}
