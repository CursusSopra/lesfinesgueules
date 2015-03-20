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
	private String coordonneesGPS;
	private String description;
	private int delaiLivraison;
	
	
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
	
	
	public Producteur(String raisonSociale, String siren, String ligneAdresse1,
			String ligneAdresse2, String codePostal, String ville,
			String coordonneesGPS, String description, int delaiLivraison) {
		
		this.raisonSociale = raisonSociale;
		this.siren = siren;
		this.ligneAdresse1 = ligneAdresse1;
		this.ligneAdresse2 = ligneAdresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.coordonneesGPS = coordonneesGPS;
		this.description = description;
		this.delaiLivraison = delaiLivraison;
	}
	
	public void save() throws SQLException{
			
		ProducteurDal cd = new ProducteurDal(raisonSociale, siren, ligneAdresse1, ligneAdresse2, codePostal, ville, coordonneesGPS, description, delaiLivraison);
		idProducteur = cd.save();
		//System.out.println(idProducteur);
	}
}
