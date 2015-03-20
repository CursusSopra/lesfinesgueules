package fr.cursusSopra.action.admin;

import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

public class ProducteurAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String raisonSociale;
	private String siren;
	private String ligneAdresse1;
	private String ligneAdresse2;
	private String codePostal;
	private String ville;
	private String coordonneesGPS;
	private String description;
	private int delaiLivraison;
	
	//Fontion qui retournera le formulaire de création de producteur
	public String createProducteurForm() {
		return SUCCESS;
	}
	
	
	//Fonction d'ajout d'un producteur en BDD
	public String createProducteur() throws SQLException {
		
		return SUCCESS;
		
	}
	
}
