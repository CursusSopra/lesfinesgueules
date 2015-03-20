package fr.cursusSopra.action.utilisateurs;

import com.opensymphony.xwork2.ActionSupport;


public class InscriptionAction extends ActionSupport{
	
	
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
