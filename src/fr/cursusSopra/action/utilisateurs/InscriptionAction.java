/**
 * Modified by Cecile
 */
package fr.cursusSopra.action.utilisateurs;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.dataLayer.UtilisateurDal;
import fr.cursusSopra.model.Utilisateur;


/**
 * 
 * @author Cécile
 *
 */

public class InscriptionAction extends ActionSupportExtended{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5788642102104502456L;
	private static Logger logger = Logger.getLogger(Utilisateur.class);
	
	private long idUtilisateur;
	
	private String nom;
	private String prenom;
	private String ligneAdresse1;
	private String ligneAdresse2;
	private String codePostal;
	private String ville;
	private String email;
	private String mdp;
	private String tel;
	private String photo;
	private int droits;
	
	private List<UtilisateurDal> listeUtilisateurs;
	
	public List<UtilisateurDal> getListeUtilisateurs() {
		return listeUtilisateurs;
	}
	public void setListeUtilisateurs(List<UtilisateurDal> listeUtilisateurs) {
		this.listeUtilisateurs = listeUtilisateurs;
	}
	//accesseurs de l'utilisateur
	@Override
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
	

	//formulaire inscription utilisateur
	public String createProfilForm() {
		return SUCCESS;
	}
	
	//ajout utilisateur en BDD
	public String createProfil() throws SQLException {
		
		Utilisateur utilisateur = new Utilisateur(nom, prenom, ligneAdresse1,
				codePostal, ville, email, mdp, tel, photo, droits);
		
		utilisateur.save();

		return SUCCESS;
	}
	
	
	public String modifyProfilForm() throws SQLException {
		
		Utilisateur utilisateur = new Utilisateur(idUtilisateur);
		
		nom = utilisateur.getNom();
		prenom = utilisateur.getPrenom();
		ligneAdresse1 = utilisateur.getLigneAdresse1();
		codePostal = utilisateur.getCodePostal();
		ville = utilisateur.getVille();
		tel = utilisateur.getTel();
			
		return SUCCESS;
	}
	
	public String modifyProfil() throws SQLException {	

		Utilisateur utilisateur = new Utilisateur(idUtilisateur);
		
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setLigneAdresse1(ligneAdresse1);
		utilisateur.setCodePostal(codePostal);
		utilisateur.setVille(ville);
		utilisateur.setTel(tel);
		
		utilisateur.update(idUtilisateur);
		
		return SUCCESS;
	}
	
}
