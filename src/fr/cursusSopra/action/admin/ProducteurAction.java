/**
 * Modified by Nicolas
 */
package fr.cursusSopra.action.admin;

import java.io.File;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.model.Producteur;
import fr.cursusSopra.tech.FormTools;

public class ProducteurAction extends ActionSupportExtended implements
		ServletRequestAware {

	// Define a static logger variable so that it references the
	// Logger instance named "MyApp".
	private static final Logger logger = LogManager
			.getLogger(ProducteurAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	private long idProducteur = 0;
	// private String photo;
	private boolean fromDb = false;

	private boolean raisonSocialeOK;
	private boolean sirenOK;
	private boolean ligneAdresse1OK;
	private boolean codePostalOK;
	private boolean villeOK;
	private boolean coordonneesGPSOK;
	private boolean descriptionOK;
	private boolean delaiLivraisonOK;
	private boolean latitudeOK;
	private boolean longitudeOK;
	private boolean imageOK;

	private boolean firstDisplay = true;
	private boolean saisieOK = false;

	public boolean isSaisieOK() {
		return saisieOK;
	}

	// Fontion qui retournera le formulaire de création de producteur
	public String createProducteurForm() {
		return SUCCESS;
	}

	private File userImage;
	private String userImageContentType;
	private String userImageFileName;
	private String imageName;

	private HttpServletRequest servletRequest;
	
	// private String filePath =
	// servletRequest.getSession().getServletContext().getRealPath("/images");
	private String lienPhoto;

	// Fonction d'ajout d'un producteur en BDD
	public String createProducteur() {

		raisonSocialeOK = (FormTools.isStrNotEmpty(raisonSociale) && raisonSociale.length() < 50);
		sirenOK = (FormTools.isStrNotEmpty(siren) && siren.length() < 50);
		ligneAdresse1OK = (FormTools.isStrNotEmpty(ligneAdresse1) && ligneAdresse1.length() < 50);
		codePostalOK = FormTools.isZipValid(codePostal);
		villeOK = (FormTools.isStrNotEmpty(ville) && ville.length() < 50);
		latitudeOK = (FormTools.isStrNotEmpty(latitude) && latitude.length() < 13);
		longitudeOK = (FormTools.isStrNotEmpty(longitude) && longitude.length() < 13);
		descriptionOK = FormTools.isStrNotEmpty(description);
		delaiLivraisonOK = (delaiLivraison > 0);
		imageOK = FormTools.isStrNotEmpty(userImageFileName);
		
		firstDisplay = raisonSocialeOK && sirenOK && ligneAdresse1OK
				&& codePostalOK && villeOK && latitudeOK && longitudeOK
				&& descriptionOK && delaiLivraisonOK && imageOK;
		
		// long idProducteur = 0;
		
		if (firstDisplay) {
			saisieOK = true;
			try {
				
				String[] tokens = userImageFileName.split("\\.(?=[^\\.]+$)");
				imageName = UUID.randomUUID() + "." + tokens[1];
				String filePath = servletRequest.getSession()
						.getServletContext().getRealPath("/content/images");
				File fileToCreate = new File(filePath, imageName);
				FileUtils.copyFile(this.userImage, fileToCreate);

				// System.out.println("Server path:" + filePath);
				// File fileToCreate = new File(filePath, this.photoFileName);
				// FileUtils.copyFile(this.photo, fileToCreate);
				//
				lienPhoto = imageName;
//				logger.info(servletRequest.getSession().getServletContext()
//						.getContextPath());

			} catch (Exception e) {
				e.printStackTrace();
				addActionError(e.getMessage());

				return INPUT;
			}

			Producteur prod = new Producteur(raisonSociale, siren,
					ligneAdresse1, ligneAdresse2, codePostal, ville, latitude,
					longitude, description, delaiLivraison, lienPhoto);
			prod.setFromDb(this.isFromDb());
			prod.setIdProducteur(idProducteur);

			try {
				prod.save();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			idProducteur = prod.getIdProducteur();
			System.out.println(idProducteur);
		}
		return firstDisplay ? (idProducteur != 0 ? SUCCESS : NONE) : ERROR;
	}

	public String modifyProducteur() {
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
		imageName = producteur.getPhoto();

		return SUCCESS;
	}
	
	public String getRaisonSociale() {
		return raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public String getSiren() {
		return siren;
	}

	public void setSiren(String siren) {
		this.siren = siren;
	}

	public String getLigneAdresse1() {
		return ligneAdresse1;
	}

	public void setLigneAdresse1(String ligneAdresse1) {
		this.ligneAdresse1 = ligneAdresse1;
	}

	public String getLigneAdresse2() {
		return ligneAdresse2;
	}

	public void setLigneAdresse2(String ligneAdresse2) {
		this.ligneAdresse2 = ligneAdresse2;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDelaiLivraison() {
		return delaiLivraison;
	}

	public void setDelaiLivraison(int delaiLivraison) {
		this.delaiLivraison = delaiLivraison;
	}

	public long getIdProducteur() {
		return idProducteur;
	}

	public void setIdProducteur(long idProducteur) {
		this.idProducteur = idProducteur;
	}

	public boolean isRaisonSocialeOK() {
		return raisonSocialeOK;
	}

	public boolean isSirenOK() {
		return sirenOK;
	}

	public boolean isLigneAdresse1OK() {
		return ligneAdresse1OK;
	}

	public boolean isCodePostalOK() {
		return codePostalOK;
	}

	public boolean isVilleOK() {
		return villeOK;
	}

	public boolean isCoordonneesGPSOK() {
		return coordonneesGPSOK;
	}

	public boolean isDescriptionOK() {
		return descriptionOK;
	}

	public boolean isDelaiLivraisonOK() {
		return delaiLivraisonOK;
	}

	public boolean isLatitudeOK() {
		return latitudeOK;
	}

	public boolean isLongitudeOK() {
		return longitudeOK;
	}

	public boolean isImageOK() {
		return imageOK;
	}

	public boolean isFirstDisplay() {
		return firstDisplay;
	}

	public boolean isFromDb() {
		return fromDb;
	}

	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
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

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;

	}
}
