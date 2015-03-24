/**
 * File modified by : Benoît
 */
package fr.cursusSopra.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.dataLayer.ProducteurDal;
/**
 * 
 * @author Nicolas
 *
 */
public class Producteur {
	
	/* PROPERTIES */
	
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
	
	/* ACCESSORS */
	
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
	
	/* CONSTRUCTORS */
	
	public Producteur(String raisonSociale, String siren, String ligneAdresse1, String ligneAdresse2, String codePostal, String ville, String latitude, String longitude, String description, int delaiLivraison, String photo) {
		this.raisonSociale = raisonSociale;
		this.siren = siren;
		this.ligneAdresse1 = ligneAdresse1;
		this.ligneAdresse2 = ligneAdresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.latitude = latitude;
		this.longitude = longitude;
		this.description = description;
		this.delaiLivraison = delaiLivraison;
		this.photo = photo;
	}
	
	public Producteur(long idProducteur, String raisonSociale, String siren, String ligneAdresse1, String ligneAdresse2, String codePostal, String ville, String latitude, String longitude, String description, int delaiLivraison, String photo) {
		this.idProducteur = idProducteur;
		this.raisonSociale = raisonSociale;
		this.siren = siren;
		this.ligneAdresse1 = ligneAdresse1;
		this.ligneAdresse2 = ligneAdresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.latitude = latitude;
		this.longitude = longitude;
		this.description = description;
		this.delaiLivraison = delaiLivraison;
		this.photo = photo;
	}
	
	public Producteur(long idProducteur) {
		ProducteurDal pdal = new ProducteurDal(idProducteur);
		
		this.raisonSociale = pdal.getRaisonSociale();
		this.siren = pdal.getSiren();
		this.ligneAdresse1 = pdal.getLigneAdresse1();
		this.ligneAdresse2 = pdal.getLigneAdresse2();
		this.codePostal = pdal.getCodePostal();
		this.ville = pdal.getVille();
		this.latitude = pdal.getLatitude();
		this.longitude = pdal.getLongitude();
		this.description = pdal.getDescription();
		this.delaiLivraison = pdal.getDelaiLivraison();
		this.photo = pdal.getPhoto();
	}

	/* STATIC METHODS */
	
	public static List<Producteur> getListeProducteur() {
		List<Producteur> listeProducteur = new ArrayList<Producteur>();
		List<ProducteurDal> lpdal = ProducteurDal.getListeProducteurDal();
		
		for (int i = 0; i < lpdal.size(); i++) {
			long idProducteur = lpdal.get(i).getIdProducteur();
			String raisonSociale = lpdal.get(i).getRaisonSociale();
			String siren = lpdal.get(i).getSiren();
			String ligneAdresse1 = lpdal.get(i).getLigneAdresse1();
			String ligneAdresse2 = lpdal.get(i).getLigneAdresse2();
			String codePostal = lpdal.get(i).getCodePostal();
			String ville = lpdal.get(i).getVille();
			String latitude = lpdal.get(i).getLatitude();
			String longitude = lpdal.get(i).getLongitude();
			String description = lpdal.get(i).getDescription();
			int delaiLivraison = lpdal.get(i).getDelaiLivraison();
			String photo = lpdal.get(i).getPhoto();
			
			Producteur p = new Producteur(idProducteur, raisonSociale, siren, ligneAdresse1, ligneAdresse2, codePostal, ville, latitude, longitude, description, delaiLivraison, photo);
			listeProducteur.add(p);
		}
		return listeProducteur;
	}
	
	/* METHODS */
	
	public void save() throws SQLException{
		ProducteurDal pd = new ProducteurDal(raisonSociale, siren, ligneAdresse1, ligneAdresse2, codePostal, ville, latitude, longitude, description, delaiLivraison, photo);
		idProducteur = pd.save();
	}
}
