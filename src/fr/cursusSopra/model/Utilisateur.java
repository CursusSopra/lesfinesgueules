/**
 * Modified by Cecile
 */
package fr.cursusSopra.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.dataLayer.UtilisateurDal;
/**
 * 
 * @author Cécile
 *
 */
public class Utilisateur {
	
private long idUtilisateur;
	
	private String nom;
	private String prenom;
	private String email;
	private String ligneAdresse1;
	private String ligneAdresse2;
	private String codePostal;
	private String ville;
	private String mdp;
	private String tel;
	private String photo;
	private int droits;
	
	
	//accesseurs
	public long getIdUtilisateur() {return idUtilisateur;}
	public void setIdUtilisateur(long idUtilisateur) {this.idUtilisateur = idUtilisateur;}
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getLigneAdresse1() {return ligneAdresse1;}
	public void setLigneAdresse1(String ligneAdresse1) {this.ligneAdresse1 = ligneAdresse1;}
	public String getLigneAdresse2() {return ligneAdresse2;}
	public void setLigneAdresse2(String ligneAdresse2) {this.ligneAdresse2 = ligneAdresse2;}
	public String getCodePostal() {return codePostal;}
	public void setCodePostal(String codePostal) {this.codePostal = codePostal;}
	public String getVille() {return ville;}
	public void setVille(String ville) {this.ville = ville;}
	public String getMdp() {return mdp;}
	public void setMdp(String mdp) {this.mdp = mdp;}
	public String getTel() {return tel;}
	public void setTel(String tel) {this.tel = tel;}
	public String getPhoto() {return photo;}
	public void setPhoto(String photo) {this.photo = photo;}
	public int getDroits() {return droits;}
	public void setDroits(int droits) {this.droits = droits;}
	
	
	//constructeur
	public Utilisateur(String nom, String prenom, String ligneAdresse1, String codePostal, String ville, String email, String mdp, String tel,
			String photo, int droits) {
		this.nom = nom;
		this.prenom = prenom;
		this.ligneAdresse1 = ligneAdresse1;
		this.codePostal = codePostal;
		this.ville = ville;
		this.tel = tel;
		this.mdp = mdp;
		this.email = email;
		this.photo = photo;
		this.droits = droits;
	}
	
	//ctor 2
	public Utilisateur(long idUtilisateur, String nom, String prenom,
			String email) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	
	public void save() throws SQLException{
		UtilisateurDal ud = new UtilisateurDal(nom, prenom, ligneAdresse1, codePostal, ville, email, mdp, tel, photo, droits);
		ud.save();
	}

	//liste des utilisateurs
	public static List<Utilisateur> getListeUtilisateurs() throws SQLException{
		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
		List<UtilisateurDal> lud = UtilisateurDal.getListeUtilisateurs();
		
		for(int i = 0; i<lud.size(); i++){
			long idUtilisateur = lud.get(i).getIdUtilisateur();
			String nom = lud.get(i).getNom();
			String prenom = lud.get(i).getPrenom();
			String email = lud.get(i).getEmail();
			
			Utilisateur u = new Utilisateur(idUtilisateur, nom, prenom, email);
			listeUtilisateurs.add(u);
		}
		return listeUtilisateurs;
	}
	
}
