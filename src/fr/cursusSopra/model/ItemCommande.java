/**
 * File modified by : Benoît
 */
package fr.cursusSopra.model;

import java.sql.Timestamp;
/**
 * @author Julien C
 *
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
	
	
	/**
	 *  constructeur d'un nouvel ItemCommande
	 * @param idUtilisateur
	 * @param idProduit
	 * @param quantite
	 */
	public ItemCommande(long idProduit, long idCommande, int quantite) {
		this.idProduit = idProduit;
		this.idCommande = idCommande;
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
	 */
	public ItemCommande(long idItemCommande, long idProduit, long idCommande, int quantite, Timestamp tsCreation, boolean isFromDb) {
		this.idItemCommande = idItemCommande;
		this.idProduit = idProduit;
		this.idCommande = idCommande;
		this.quantite = quantite;
		this.tsCreation = tsCreation;
		this.isFromDb = isFromDb;
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
	
}
