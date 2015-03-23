package fr.cursusSopra.action.admin;

import java.sql.SQLException;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.model.Producteur;
import fr.cursusSopra.tech.FormTools;

public class ProducteurAction extends ActionSupportExtended {
	
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
	private String longitude;
	private String latitude;
	private String description;
	private int delaiLivraison;
	private long idProducteur;
	private String photo = "photo";
	
	private boolean  raisonSocialeOK;
	private boolean  sirenOK;
	private boolean  ligneAdresse1OK;
	private boolean  codePostalOK;
	private boolean  villeOK;
	private boolean  coordonneesGPSOK;
	private boolean  descriptionOK;
	private boolean  delaiLivraisonOK;
	private boolean latitudeOK;
	private boolean longitudeOK;
	
	private boolean firstDisplay = true;
	
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
	public String getLongitude() {return longitude;}
	public void setLongitude(String longitude) {this.longitude = longitude;}
	public String getLatitude() {return latitude;}
	public void setLatitude(String latitude) {this.latitude = latitude;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public int getDelaiLivraison() {return delaiLivraison;}
	public void setDelaiLivraison(int delaiLivraison) {this.delaiLivraison = delaiLivraison;}
	public long getIdProducteur() {return idProducteur;}
	public void setIdProducteur(long idProducteur) {this.idProducteur = idProducteur;}
	
	public boolean isRaisonSocialeOK() {return raisonSocialeOK;}
	public boolean isSirenOK() {return sirenOK;}
	public boolean isLigneAdresse1OK() {return ligneAdresse1OK;}
	public boolean isCodePostalOK() {return codePostalOK;}
	public boolean isVilleOK() {return villeOK;}
	public boolean isCoordonneesGPSOK() {return coordonneesGPSOK;}
	public boolean isDescriptionOK() {return descriptionOK;}
	public boolean isDelaiLivraisonOK() {return delaiLivraisonOK;}
	public boolean isLatitudeOK() {return latitudeOK;}
	public boolean isLongitudeOK() {return longitudeOK;}
	
	public boolean isFirstDisplay() {return firstDisplay;}
	
	
	//Fontion qui retournera le formulaire de création de producteur
	public String createProducteurForm() {
		return SUCCESS;
	}
	
	//Fonction d'ajout d'un producteur en BDD
	public String createProducteur() throws SQLException {
		
		raisonSocialeOK = (FormTools.isStrNotEmpty(raisonSociale) && raisonSociale.length() < 50);
		sirenOK = (FormTools.isStrNotEmpty(siren) && siren.length() < 50);
		ligneAdresse1OK = (FormTools.isStrNotEmpty(ligneAdresse1) && ligneAdresse1.length() < 50);
		codePostalOK = FormTools.isZipValid(codePostal);
		villeOK = (FormTools.isStrNotEmpty(ville) && ville.length() < 50);
		latitudeOK = (FormTools.isStrNotEmpty(latitude) && latitude.length() < 13);
		longitudeOK = (FormTools.isStrNotEmpty(longitude) && longitude.length() < 13);
		descriptionOK = FormTools.isStrNotEmpty(description);
		delaiLivraisonOK = (delaiLivraison > 0);
		
		firstDisplay = raisonSocialeOK && sirenOK && ligneAdresse1OK && codePostalOK &&
				villeOK && latitudeOK && longitudeOK && descriptionOK && delaiLivraisonOK;
		
		long idProducteur = 0;
		
		if(firstDisplay){
			// On instancie un objet 'Producteur'
			Producteur prod = new Producteur(raisonSociale, siren, ligneAdresse1, codePostal, ville, latitude, longitude, 
						description, delaiLivraison, photo);
			
			if(ligneAdresse2.length() != 0){
				prod.setLigneAdresse2(ligneAdresse2);
			}
			
			
			// On lui demande gentiment de se sauvegarder en BDD
			prod.save();
			idProducteur = prod.getIdProducteur();
			System.out.println(idProducteur);
		}
		return firstDisplay ? (idProducteur != 0 ? SUCCESS : NONE) : ERROR;
	}
}
