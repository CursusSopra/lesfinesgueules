/**
 * File modified by : Benoît
 */
package fr.cursusSopra.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.dataLayer.ProduitDal;
/**
 * 
 * @author Benoît
 *
 */
public class Produit {
	
	/* PROPERTIES */
	
	private long idProduit;
	private String designation;
	private String description;
	private double prix;
	private String photo;
	private boolean disponible;
	private long type1;
	private long type2;
	private long producteur;
	private List<Commentaire> listeCommentaires;
	
	/* CONSTRUCTORS */
	
	public Produit(String designation, String description, double prix, String photo, boolean disponible, long type1, long type2, long producteur) {
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.photo = photo;
		this.disponible = disponible;
		this.type1 = type1;
		this.type2 = type2;
		this.producteur = producteur;
	}
	
	public Produit(long idProduit, String designation, String description, double prix, String photo, boolean disponible, long type1, long type2, long producteur) {
		this.idProduit = idProduit;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.photo = photo;
		this.disponible = disponible;
		this.type1 = type1;
		this.type2 = type2;
		this.producteur = producteur;
		
		listeCommentaires = new ArrayList<Commentaire>();
	}
	
	public Produit(long idProduit) throws SQLException {
		ProduitDal pdal = new ProduitDal(idProduit);
		
		this.idProduit = pdal.getIdProduit();
		this.designation = pdal.getDesignation();
		this.description = pdal.getDescription();
		this.prix = pdal.getPrix();
		this.photo = pdal.getPhoto();
		this.disponible = pdal.isDisponible();
		this.type1 = pdal.getType1();
		this.type2 = pdal.getType2();
		this.producteur = pdal.getProducteur();
		
		listeCommentaires = new ArrayList<Commentaire>();
	}
	
	public Produit(long producteur, long type2, double prix, String designation, boolean disponible) {
		this.designation = designation;
		this.prix = prix;
		this.disponible = disponible;
		this.type2 = type2;
		this.producteur = producteur;
	}

	/* METHODS */
	
	public static List<Produit> getListeProduits(long idType1, long idType2, long idProducteur) {
		List<Produit> listeProduits = new ArrayList<Produit>();
		List<ProduitDal> lpdal = ProduitDal.getListeProduitsDal(idType1, idType2, idProducteur);
		
		for (int i = 0; i < lpdal.size(); i++) {
			long idProduit = lpdal.get(i).getIdProduit();
			String designation = lpdal.get(i).getDesignation();
			String description = lpdal.get(i).getDescription();
			double prix = lpdal.get(i).getPrix();
			String photo = lpdal.get(i).getPhoto();
			boolean disponible = lpdal.get(i).isDisponible();
			long type1 = lpdal.get(i).getType1();
			long type2 = lpdal.get(i).getType2();
			long producteur = lpdal.get(i).getProducteur();
			
			Produit p = new Produit(idProduit, designation, description, prix, photo, disponible, type1, type2, producteur);
			listeProduits.add(p);
		}
		return listeProduits;
	}
	
	public void save() throws SQLException{
			
		ProduitDal pd = new ProduitDal(producteur, type2, prix, designation, disponible);
		
		if(description != null){
			pd.setDescription(description);
		}
		if(photo != null){
			pd.setPhoto(photo);
		}
		
		idProduit = pd.save();
		//System.out.println(idProduit);
	}
	
	/* ACCESSORS */
	
	public long getIdProduit() {
		return idProduit;
	}
	
	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public long getType1() {
		return type1;
	}

	public void setType1(long type1) {
		this.type1 = type1;
	}

	public long getType2() {
		return type2;
	}

	public void setType2(long type2) {
		this.type2 = type2;
	}

	public long getProducteur() {
		return producteur;
	}

	public void setProducteur(long producteur) {
		this.producteur = producteur;
	}

	public List<Commentaire> getListeCommentaires() {
		return listeCommentaires;
	}

	public void setListeCommentaires(List<Commentaire> listeCommentaires) {
		this.listeCommentaires = listeCommentaires;
	}
	
}
