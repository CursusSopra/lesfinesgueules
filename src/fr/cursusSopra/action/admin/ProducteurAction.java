/**
 * Modified by Nicolas
 */
package fr.cursusSopra.action.admin;

import java.io.File;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.model.Producteur;
import fr.cursusSopra.tech.FormTools;

public class ProducteurAction extends ActionSupportExtended implements ServletRequestAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private File photo;
    private String photoContentType;
    private String photoFileName;
 
    private HttpServletRequest servletRequest;
    private String filePath = servletRequest.getSession().getServletContext().getRealPath("/images");
    private String lienPhoto;
	
	private Producteur producteur;
	
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
//	private String photo;
	private boolean fromDb = false;
	
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
	
	
	public boolean isFromDb() {
		return fromDb;
	}
	public File getPhoto() {
		return photo;
	}
	public void setPhoto(File photo) {
		this.photo = photo;
	}
	public String getPhotoContentType() {
		return photoContentType;
	}
	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}
	public String getPhotoFileName() {
		return photoFileName;
	}
	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}
	public void setFromDb(boolean fromDb) {
		this.fromDb = fromDb;
	}
	public String getLienPhoto() {
		return lienPhoto;
	}
	public void setLienPhoto(String lienPhoto) {
		this.lienPhoto = lienPhoto;
	}
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
		
//		long idProducteur = 0;
		
		if(firstDisplay){

			try {
				
	            System.out.println("Server path:" + filePath);
	            File fileToCreate = new File(filePath, this.photoFileName);
	            FileUtils.copyFile(this.photo, fileToCreate);
	            
	            lienPhoto = filePath + this.photoFileName;
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            addActionError(e.getMessage());
	 
	            return INPUT;
	        }
			
			Producteur prod = new Producteur(raisonSociale, siren, ligneAdresse1, ligneAdresse2, codePostal, ville, latitude, longitude, 
						description, delaiLivraison, lienPhoto);
			prod.setFromDb(this.isFromDb());
			
			prod.save();
			idProducteur = prod.getIdProducteur();
			System.out.println(idProducteur);
		}
		return firstDisplay ? (idProducteur != 0 ? SUCCESS : NONE) : ERROR;
	}
	
	
	public String modifyProducteur(){
		producteur = new Producteur(idProducteur);
		producteur.setFromDb(true);
		this.fromDb = producteur.isFromDb();
		raisonSociale = producteur.getRaisonSociale();
		siren = producteur.getSiren();
		ligneAdresse1 = producteur.getLigneAdresse1();
		ligneAdresse2 = producteur.getLigneAdresse2();
		codePostal = producteur.getCodePostal();
		ville = producteur.getVille();
		latitude = producteur.getLatitude();
		longitude = producteur.getLongitude();
		description = producteur.getDescription();
		delaiLivraison = producteur.getDelaiLivraison();
		filePath = producteur.getPhoto();
		
		return SUCCESS;
	}
	@Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
 
    }
}
