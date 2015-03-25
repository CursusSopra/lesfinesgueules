/**
 * File modified by : Benoît
 */
package fr.cursusSopra.action.contenu;

import java.sql.SQLException;
import java.util.List;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.dataLayer.ProducteurDal;
import fr.cursusSopra.model.Commentaire;
import fr.cursusSopra.model.Produit;

public class DetailsProduitAction extends ActionSupportExtended {

	/* SERIAL ID */
	
	private static final long serialVersionUID = -1802942881247205702L;
	
	/* PROPERTIES */
	
	private long idProduit;
	private String designation;
	private String description;
	private double prix;
	private String photo;
	private boolean disponible;
	private long idType1;
	private long idType2;
	private long idProducteur;
	private List<Commentaire> listeCommentaires;
	
	private String raisonSociale;
	private String ligneAdresse1;
	private String ligneAdresse2;
	private String codePostal;
	private String ville;
	private int delaiLivraison;
	
	/* EXECUTE METHOD */
	
	@Override
	public String execute() throws SQLException {
		Produit produit = new Produit(idProduit);
		
		designation = produit.getDesignation();
		description = produit.getDescription();
		prix = produit.getPrix();
		photo = produit.getPhoto();
		disponible = produit.isDisponible();
		idType1 = produit.getType1();
		idType2 = produit.getType2();
		idProducteur = produit.getProducteur();
		listeCommentaires = produit.getListeCommentaires();
		
		ProducteurDal prod = new ProducteurDal(idProducteur);
		
		idProducteur = prod.getIdProducteur();
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

	// Modif pour l'affichage
	public String getPrix() {
		return String.format("%.2f", prix);
	}

	public String getPhoto() {
		return photo;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public long getIdType1() {
		return idType1;
	}

	public long getIdType2() {
		return idType2;
	}

	public long getIdProducteur() {
		return idProducteur;
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
