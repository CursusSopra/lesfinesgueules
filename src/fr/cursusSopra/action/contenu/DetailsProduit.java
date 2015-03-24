package fr.cursusSopra.action.contenu;

import java.sql.SQLException;
import java.util.List;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.dataLayer.ProducteurDal;
import fr.cursusSopra.model.Commentaire;
import fr.cursusSopra.model.Produit;

public class DetailsProduit extends ActionSupportExtended {

	/* SERIAL ID */
	
	private static final long serialVersionUID = -1802942881247205702L;
	
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
	
	private String raisonSociale;
	private String ligneAdresse1;
	private String ligneAdresse2;
	private String codePostal;
	private String ville;
	private int delaiLivraison;
	
	/* EXECUTE METHOD */
	
	public String execute() throws SQLException {
		Produit produit = new Produit(idProduit);
		
		designation = produit.getDesignation();
		description = produit.getDescription();
		prix = produit.getPrix();
		photo = produit.getPhoto();
		disponible = produit.isDisponible();
		type1 = produit.getType1();
		type2 = produit.getType2();
		producteur = produit.getProducteur();
		listeCommentaires = produit.getListeCommentaires();
		
		ProducteurDal prod = new ProducteurDal(producteur);
		
		raisonSociale = prod.getRaisonSociale();
		ligneAdresse1 = prod.getLigneAdresse1();
		ligneAdresse2 = prod.getLigneAdresse2();
		codePostal = prod.getCodePostal();
		ville = prod.getVille();
		delaiLivraison = prod.getDelaiLivraison();
		
		return SUCCESS;
	}
	
	/* ACCESSORS */

	public void setIdProduit(String idProduit) {
		try {
			this.idProduit = Long.parseLong(idProduit);
		} catch (Exception e) {
			System.out.println("Conversion impossible");
		}
	}

	public long getIdProduit() {
		return idProduit;
	}

	public String getDesignation() {
		return designation;
	}

	public String getDescription() {
		return description;
	}

	public double getPrix() {
		return prix;
	}

	public String getPhoto() {
		return photo;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public long getType1() {
		return type1;
	}

	public long getType2() {
		return type2;
	}

	public long getProducteur() {
		return producteur;
	}

	public List<Commentaire> getListeCommentaires() {
		return listeCommentaires;
	}

	public String getRaisonSociale() {
		return raisonSociale;
	}

	public String getLigneAdresse1() {
		return ligneAdresse1;
	}

	public String getLigneAdresse2() {
		return ligneAdresse2;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public String getVille() {
		return ville;
	}

	public int getDelaiLivraison() {
		return delaiLivraison;
	}
	
}
