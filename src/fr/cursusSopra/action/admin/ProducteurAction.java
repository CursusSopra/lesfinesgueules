package fr.cursusSopra.action.admin;

import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

import fr.cursusSopra.model.Producteur;
import fr.cursusSopra.tech.FormTools;

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
	private long idProducteur;
	
	private boolean  raisonSocialeOK;
	private boolean  sirenOK;
	private boolean  ligneAdresse1OK;
	private boolean  ligneAdresse2OK;
	private boolean  codePostalOK;
	private boolean  villeOK;
	private boolean  coordonneesGPSOK;
	private boolean  descriptionOK;
	private boolean  delaiLivraisonOK;
	
	private boolean saveBDD = false;
	
	
	public String getRaisonSociale() {return raisonSociale;}
	public void setRaisonSociale(String raisonSociale) {this.raisonSociale = raisonSociale;}
	public String getSiren() {return siren;}
	public void setSiren(String siren) {this.siren = siren;}
	public String getLigneAdresse1() {return ligneAdresse1;}
	public void setLigneAdresse1(String ligneAdresse1) {this.ligneAdresse1 = ligneAdresse1;}
	public String getLigneAdresse2() {return ligneAdresse2;}
	public void setLigneAdresse2(String ligneAdresse2) {this.ligneAdresse2 = ligneAdresse2;}
	public String getCodePostal() {return codePostal;}
	public void setCodePostal(String codePostal) {this.codePostal = codePostal;}
	public String getVille() {return ville;}
	public void setVille(String ville) {this.ville = ville;}
	public String getCoordonneesGPS() {return coordonneesGPS;}
	public void setCoordonneesGPS(String coordonneesGPS) {this.coordonneesGPS = coordonneesGPS;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public int getDelaiLivraison() {return delaiLivraison;}
	public void setDelaiLivraison(int delaiLivraison) {this.delaiLivraison = delaiLivraison;}
	public long getIdProducteur() {return idProducteur;}
	public void setIdProducteur(long idProducteur) {this.idProducteur = idProducteur;}
	
	public boolean isRaisonSocialeOK() {return raisonSocialeOK;}
	public boolean isSirenOK() {return sirenOK;}
	public boolean isLigneAdresse1OK() {return ligneAdresse1OK;}
	public boolean isLigneAdresse2OK() {return ligneAdresse2OK;}
	public boolean isCodePostalOK() {return codePostalOK;}
	public boolean isVilleOK() {return villeOK;}
	public boolean isCoordonneesGPSOK() {return coordonneesGPSOK;}
	public boolean isDescriptionOK() {return descriptionOK;}
	public boolean isDelaiLivraisonOK() {return delaiLivraisonOK;}


	//Fontion qui retournera le formulaire de création de producteur
	public String createProducteurForm() {
		return SUCCESS;
	}
	
	
	//Fonction d'ajout d'un producteur en BDD
	public String createProducteur() throws SQLException {
		
		raisonSocialeOK = FormTools.isStrNotEmpty(raisonSociale);
		sirenOK = FormTools.isStrNotEmpty(siren);
		ligneAdresse1OK = FormTools.isStrNotEmpty(ligneAdresse1);
		ligneAdresse2OK = FormTools.isStrNotEmpty(ligneAdresse2);
		codePostalOK = FormTools.isZipValid(codePostal);
		villeOK = FormTools.isStrNotEmpty(ville);
		coordonneesGPSOK = FormTools.isStrNotEmpty(coordonneesGPS);
		descriptionOK = FormTools.isStrNotEmpty(description);
		
		saveBDD = raisonSocialeOK && sirenOK && ligneAdresse1OK && ligneAdresse2OK && codePostalOK &&
				villeOK && coordonneesGPSOK && descriptionOK;
		
		long idProducteur = 0;
		
		if(saveBDD){
			// On instancie un objet 'Producteur'
			Producteur prod = new Producteur(raisonSociale, siren, ligneAdresse1, ligneAdresse2, codePostal, ville, coordonneesGPS, description, delaiLivraison);
			
			// On lui demande gentiment de se sauvegarder en BDD
			prod.save();
			idProducteur = prod.getIdProducteur();
			System.out.println(idProducteur);
		}
		return saveBDD ? (idProducteur != 0 ? SUCCESS : NONE) : ERROR;
	}
	
}
