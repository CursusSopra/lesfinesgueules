/**
 * File modified by : Benoît
 */
package fr.cursusSopra.action.admin;

import java.sql.SQLException;
import java.util.List;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.dataLayer.Type1Dal;
import fr.cursusSopra.model.Producteur;
import fr.cursusSopra.model.Produit;
import fr.cursusSopra.model.Type1;
import fr.cursusSopra.tech.FormTools;

public class ProduitAction extends ActionSupportExtended {

	private static final long serialVersionUID = 1L;
	
	private long idProduit;
	
	private long idProducteur;
	private long idType2;
	private String description;
	private double prix;
	private String designation;
	private String photo;
	private boolean disponible;
	
	private List<Type1> listeType1;
	private List<Producteur> listeProducteur;
	
	private boolean idProducteurOK;
	private boolean idType2OK;
	private boolean prixOK;
	private boolean designationOK;
	
	private boolean firstDisplay = true;
	
	//ACCESSEURS
	public long getIdProducteur() {return idProducteur;}
	public void setIdProducteur(long idProducteur) {this.idProducteur = idProducteur;}
	public long getIdType2() {return idType2;}
	public void setIdType2(long idType2) {this.idType2 = idType2;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public double getPrix() {return prix;}
	public void setPrix(double prix) {this.prix = prix;}
	public String getDesignation() {return designation;}
	public void setDesignation(String designation) {this.designation = designation;}
	public String getPhoto() {return photo;}
	public void setPhoto(String photo) {this.photo = photo;}
	public boolean isDisponible() {return disponible;}
	public void setDisponible(boolean disponible) {this.disponible = disponible;}
	public List<Type1> getListeType1() {return listeType1;}
	public void setListeType1(List<Type1> listeType1) {this.listeType1 = listeType1;}
	public List<Producteur> getListeProducteur() {return listeProducteur;}
	public void setListeProducteur(List<Producteur> listeProducteur) {this.listeProducteur = listeProducteur;}
	
	//ACCESSEURS BOOLEAN
	public long getIdProduit() {return idProduit;}
	public boolean isIdProducteurOK() {return idProducteurOK;}
	public boolean isIdType2OK() {return idType2OK;}
	public boolean isPrixOK() {return prixOK;}
	public boolean isDesignationOK() {return designationOK;}
	public boolean isFirstDisplay() {return firstDisplay;}
	
	
	//Affichage du formulaire de création de produit
	public String createProduitForm() throws SQLException {
		//listeType2 = Type2Dal.getListeType2();
		listeType1 = Type1Dal.getListeType1();
		listeProducteur = Producteur.getListeProducteur();
		return SUCCESS;
	}
	
	//Fonction d'ajout d'un produit en BDD
	public String createProduit() throws SQLException {
		
		idProducteurOK = (idProducteur > 0);
		idType2OK = (idType2 > 0);
		prixOK = (prix > 0);
		designationOK = (FormTools.isStrNotEmpty(designation) && designation.length() < 50);
		
		firstDisplay = idProducteurOK && idType2OK && prixOK && designationOK;
		
		long idProduit = 0;
		
		if(firstDisplay){
			Produit prod = new Produit(idProducteur, idType2, prix, designation, disponible);
			
			if(description != null){
				prod.setDescription(description);
			}
			if(photo != null){
				prod.setPhoto(photo);
			}
			
			prod.save();
			idProduit = prod.getIdProduit();
			//System.out.println(idProduit);
		}
		
		return firstDisplay ? (idProduit != 0 ? SUCCESS : NONE) : ERROR;
	}
	
}
