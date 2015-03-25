/**
 * Modified by Nicolas
 */
package fr.cursusSopra.action.admin;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.model.Producteur;
import fr.cursusSopra.model.Produit;
import fr.cursusSopra.model.Type1;
import fr.cursusSopra.tech.FormTools;

public class ProduitAction extends ActionSupportExtended implements ServletRequestAware {
	
	// Define a static logger variable so that it references the
	// Logger instance named "MyApp".
	private static final Logger logger = LogManager.getLogger(ProducteurAction.class);
		
	private static final long serialVersionUID = 1L;
	
	private long idProduit;
	
	private long idProducteur;
	private long idType2;
	private String description;
	private double prix;
	private String designation;
	private String photo = "image";
	private boolean disponible;
	
	private List<Type1> listeType1;
	private List<Producteur> listeProducteur;
	
	private boolean idProducteurOK;
	private boolean idType2OK;
	private boolean prixOK;
	private boolean designationOK;
	
	private boolean firstDisplay = true;
	
	private File photoProduit;
	private String photoProduitContentType;
	private String photoProduitFileName;
	private String imageName;
	private String lienPhoto;

	private HttpServletRequest servletRequest;
	
	
	//Affichage du formulaire de création de produit
	public String createProduitForm() throws SQLException {
		//listeType2 = Type2Dal.getListeType2();
		listeType1 = Type1.getListeType1();
		listeProducteur = Producteur.getListeProducteur();
		return SUCCESS;
	}
	
	//Fonction d'ajout d'un produit en BDD
	public String createProduit() throws SQLException {
		
		idProducteurOK = (idProducteur > 0);
		idType2OK = (idType2 > 0);
		prixOK = (prix > 0);
		designationOK = (FormTools.isStrNotEmpty(designation) && designation.length() < 50);
		
		firstDisplay = idProducteurOK && idType2OK && prixOK && designationOK;
		
		long idProduit = 0;
		
		if(firstDisplay){
			
			try {
				String[] tokens = photoProduitFileName.split("\\.(?=[^\\.]+$)");
				imageName = UUID.randomUUID() + "." + tokens[1];
				String filePath = servletRequest.getSession().getServletContext().getRealPath("/content/images");
				File fileToCreate = new File(filePath, imageName);
				FileUtils.copyFile(this.photoProduit, fileToCreate);
				
				// System.out.println("Server path:" + filePath);
				// File fileToCreate = new File(filePath, this.photoFileName);
				// FileUtils.copyFile(this.photo, fileToCreate);
				lienPhoto = imageName;
//				logger.info(servletRequest.getSession().getServletContext()
//						.getContextPath());
			} catch (Exception e) {
				e.printStackTrace();
				addActionError(e.getMessage());
				return INPUT;
			}
			
			Produit prod = new Produit(idProducteur, idType2, prix, designation, disponible);
			
			if(description != null){
				prod.setDescription(description);
			}
			if(photo != null){
				prod.setPhoto(photo);
			}
			
			prod.save();
			idProduit = prod.getIdProduit();
			//System.out.println(idProduit);
		}
		
		return firstDisplay ? (idProduit != 0 ? SUCCESS : NONE) : ERROR;
	}
	
	//ACCESSEURS
	public long getIdProducteur() {
		return idProducteur;
	}

	public void setIdProducteur(long idProducteur) {
		this.idProducteur = idProducteur;
	}

	public long getIdType2() {
		return idType2;
	}

	public void setIdType2(long idType2) {
		this.idType2 = idType2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	@Override
	public List<Type1> getListeType1() {
		return listeType1;
	}

	public void setListeType1(List<Type1> listeType1) {
		this.listeType1 = listeType1;
	}

	public List<Producteur> getListeProducteur() {
		return listeProducteur;
	}

	public void setListeProducteur(List<Producteur> listeProducteur) {
		this.listeProducteur = listeProducteur;
	}

	// ACCESSEURS BOOLEAN
	public long getIdProduit() {
		return idProduit;
	}

	public boolean isIdProducteurOK() {
		return idProducteurOK;
	}

	public boolean isIdType2OK() {
		return idType2OK;
	}

	public boolean isPrixOK() {
		return prixOK;
	}

	public boolean isDesignationOK() {
		return designationOK;
	}

	public boolean isFirstDisplay() {
		return firstDisplay;
	}
	
	//ACCESSEURS UPLOAD DATA
	public File getPhotoProduit() {
		return photoProduit;
	}

	public void setPhotoProduit(File photoProduit) {
		this.photoProduit = photoProduit;
	}

	public String getPhotoProduitContentType() {
		return photoProduitContentType;
	}

	public void setPhotoProduitContentType(String photoProduitContentType) {
		this.photoProduitContentType = photoProduitContentType;
	}

	public String getPhotoProduitFileName() {
		return photoProduitFileName;
	}

	public void setPhotoProduitFileName(String photoProduitFileName) {
		this.photoProduitFileName = photoProduitFileName;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
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
