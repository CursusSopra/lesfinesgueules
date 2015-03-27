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
	
	/* FILTER PROPERTIES */
	
	private long idType1 = -1;
	private long idType2 = -1;
	private long idProducteur = -1;
	
	/* DISPLAY PROPERTIES */
	
	private int displayNumber = 0;
	private int pageNumber = 1;
	private int numberOfPages = 1;
	
	/* OUTPUT LIST */
	
	private List<Produit> listeProduits = new ArrayList<>();

	public JSONListeProduitsAction() throws SQLException {
	}
	
	/* EXECUTE METHOD */
	
	public String execute() {
		try {
			setListeProduits(Produit.getListeProduits(idType1, idType2, idProducteur));
		} catch (Exception e) {
			System.out.println("getListeProduits impossible.");
		}
		
		filterDisplay();
		
		System.out.println("pageNumber = " + pageNumber);
		System.out.println("numberOfPages = " + numberOfPages);
		System.out.println("displayNumber = " + displayNumber);
		
		return Action.SUCCESS;
	}
	
	/* METHODS */
	
	private void filterDisplay() {
		int lpLength = listeProduits.size();
		numberOfPages = (int) Math.ceil(((double) lpLength) / ((double) displayNumber));
		System.out.println("===================================");
		System.out.println("lpLength = " + lpLength);
		System.out.println("displayNumber = " + displayNumber);
		System.out.println("numberOfPages = " + numberOfPages);
		System.out.println("===================================");
		
		int minProduitIndex = (pageNumber - 1) * displayNumber;
		int maxProduitIndex = (pageNumber * displayNumber) > lpLength ? lpLength : pageNumber * displayNumber;
		
		List<Produit> tempList = new ArrayList<Produit>();
		
		for (int i = minProduitIndex; i < maxProduitIndex; i++) {
			tempList.add(listeProduits.get(i));
		}
		
		listeProduits = tempList;
	}
	
	/* OUTPUT LIST */

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	private void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}
	
	/* FILTER ACCESSORS */
	
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
	
	/* DISPLAY ACCESSORS */
	
	public void setDisplayNumber(String displayNumber) {
		try {
			this.displayNumber = Integer.parseInt(displayNumber);
			System.out.println("Conversion String > Int faite pour displayNumber | displayNumber = " + this.displayNumber);
		} catch (NumberFormatException e) {
			this.displayNumber = 0;
			e.printStackTrace();
			System.out.println("Conversion String > Int impossible pour displayNumber.");
		}
	}
	
	public void setPageNumber(String pageNumber) {
		try {
			this.pageNumber = Integer.parseInt(pageNumber);
			System.out.println("Conversion String > Int faite pour pageNumber | pageNumber = " + this.pageNumber);
		} catch (NumberFormatException e) {
			this.pageNumber = 0;
			e.printStackTrace();
			System.out.println("Conversion String > Int impossible pour pageNumber.");
		}
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
	
	public int getDisplayNumber() {
		return displayNumber;
	}
	
	public int getNumberOfPages() {
		return numberOfPages;
	}
	
}
