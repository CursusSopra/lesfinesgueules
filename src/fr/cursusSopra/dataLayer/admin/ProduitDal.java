package fr.cursusSopra.dataLayer.admin;

import fr.cursusSopra.dataLayer.DataLayerExtended;

public class ProduitDal extends DataLayerExtended {
	
	private final static String rqInsert = 
			"INSERT INTO produits (id_producteur, id_type2, description, prix, designation, photo, disponible) VALUES (?,?,?,?,?,?,?)";
	
	private long idProduit;
		
	private long idProducteur;
	private long idType2;
	private String description;
	private double prix;
	private String designation;
	private String photo;
	private boolean disponible;
	
}
