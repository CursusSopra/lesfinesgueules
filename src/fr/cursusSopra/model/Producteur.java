package fr.cursusSopra.model;

import java.sql.SQLException;

import fr.cursusSopra.dataLayer.admin.ProducteurDal;

public class Producteur {
	
	private long idProducteur;
	
	private String raisonSociale;
	private String siren;
	private String ligneAdresse1;
	private String ligneAdresse2;
	private String codePostal;
	private String ville;
	private String latitude;
	private String longitude;
	private String description;
	private int delaiLivraison;
	private String photo;
	
	
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
	public String getLatitude() {return latitude;}
	public void setLatitude(String latitude) {this.latitude = latitude;}
	public String getLongitude() {return longitude;}
	public void setLongitude(String longitude) {this.longitude = longitude;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public int getDelaiLivraison() {return delaiLivraison;}
	public void setDelaiLivraison(int delaiLivraison) {this.delaiLivraison = delaiLivraison;}
	public long getIdProducteur() {return idProducteur;}
	public void setIdProducteur(long idProducteur) {this.idProducteur = idProducteur;}
	public String getPhoto() {return photo;}
	public void setPhoto(String photo) {this.photo = photo;}
	
	
	public Producteur(String raisonSociale, String siren, String ligneAdresse1,
			String codePostal, String ville, String latitude, String longitude, 
			String description, int delaiLivraison, String photo) {
		
		this.raisonSociale = raisonSociale;
		this.siren = siren;
		this.ligneAdresse1 = ligneAdresse1;
		this.codePostal = codePostal;
		this.ville = ville;
		this.latitude = latitude;
		this.longitude = longitude;
		this.description = description;
		this.delaiLivraison = delaiLivraison;
		this.photo = photo;
	}
	
	public Producteur(String raisonSociale, String siren, String ligneAdresse1, 
			String codePostal, String ville, String latitude, String longitude,
			String description, int delaiLivraison) {
		
		this.raisonSociale = raisonSociale;
		this.siren = siren;
		this.ligneAdresse1 = ligneAdresse1;
		this.codePostal = codePostal;
		this.ville = ville;
		this.latitude = latitude;
		this.longitude = longitude;
		this.description = description;
		this.delaiLivraison = delaiLivraison;
	}
	
	public Producteur(long idProducteur, String raisonSociale, String siren) {
		this.idProducteur = idProducteur;
		this.raisonSociale = raisonSociale;
		this.siren = siren;
	}
	
	public void save() throws SQLException{
			
		ProducteurDal pd = new ProducteurDal(raisonSociale, siren, ligneAdresse1, codePostal, ville, latitude, longitude, description, delaiLivraison, photo);
		
		if(ligneAdresse2 != null){
			pd.setLigneAdresse2(ligneAdresse2);
		}
		
		idProducteur = pd.save();
		//System.out.println(idProducteur);
	}
}
