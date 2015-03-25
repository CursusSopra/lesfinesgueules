/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.model;

import java.sql.Timestamp;
/**
 * @author Julien Caillon
 */
public class ItemCommande {

	private long idItemCommande = -1;
	private long idProduit;
	private long idCommande;
	private int quantite;
	private Timestamp tsCreation;

	/* isFromDb :
	  	boolean utilise pour savoir si je suis deja present dans la base (true) ou non (false)
	 */
	private boolean isFromDb = false;

	// from produit
	private String designation;
	private double prix;

	/**
	 *  constructeur d'un nouvel ItemCommande (from scratch)
	 * @param idUtilisateur
	 * @param idProduit
	 * @param quantite
	 */
	public ItemCommande(long idProduit, int quantite) {
		this.idProduit = idProduit;
		this.quantite = quantite;
	}


	/**
	 * constructeur d'un ItemCommande complet
	 * @param idItemCommande
	 * @param idProduit
	 * @param idCommande
	 * @param quantite
	 * @param tsCreation
	 * @param isFromDb
	 * @param designation
	 * @param prix
	 */
	public ItemCommande(long idItemCommande, long idProduit, long idCommande, int quantite, Timestamp tsCreation, boolean isFromDb,
			String designation, double prix) {
		this.idItemCommande = idItemCommande;
		this.idProduit = idProduit;
		this.idCommande = idCommande;
		this.quantite = quantite;
		this.tsCreation = tsCreation;
		this.isFromDb = isFromDb;
		this.designation = designation;
		this.prix = prix;
	}

	/**
	 *  constructeur utilise pour le delete
	 * @param idUtilisateur
	 * @param idProduit
	 * @param quantite
	 */
	public ItemCommande(long idItemCommande) {
		this.idItemCommande = idItemCommande;
	}


	/**
	 * GETTERS / SETTERS
	 */

	public long getIdItemCommande() {
		return idItemCommande;
	}

	public void setIdItemCommande(long id) {
		this.idItemCommande = id;
	}

	public long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}

	public long getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(long idCommande) {
		this.idCommande = idCommande;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Timestamp getTsCreation() {
		return tsCreation;
	}

	public void setTsCreation(Timestamp tsCreation) {
		this.tsCreation = tsCreation;
	}

	public boolean isFromDb() {
		return isFromDb;
	}

	public void setFromDb(boolean isFromDb) {
		this.isFromDb = isFromDb;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

}
