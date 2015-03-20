package fr.cursusSopra.action.utilisateurs;

import java.sql.SQLException;

import fr.cursusSopra.action.ActionSupportExtended;


public class InscriptionAction extends ActionSupportExtended{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5788642102104502456L;
	
	private String nom;
	private String prenom;
	private String email;
	private String ligneAdresse1;
	private String ligneAdresse2;
	private String codePostal;
	private String ville;
	private String mdp;
	private String tel;
	
	
	
	//Fontion qui inscrit un utilisateur
	public String createProfilForm() {
		return SUCCESS;
	}
	
	
	//Fonction d'ajout d'un utilisateur en BDD
	public String createProfil() throws SQLException {
		
		return SUCCESS;
		
	}
	
	

}
