package fr.cursusSopra.dataLayer.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.dataLayer.DataLayerExtended;
import fr.cursusSopra.model.Producteur;
import fr.cursusSopra.tech.PostgresConnection;

public class ProducteurDal extends DataLayerExtended {
		
	private final static String rqInsert = 
		"INSERT INTO producteurs (raison_sociale, siren, ligne_adresse1, ligne_adresse2, code_postal, ville, gpslat, gpslong, description, delai_livraison, photo) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	private final static String rq = "SELECT * FROM producteurs";
	
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
	
	private static List<Producteur> listeProducteur;
	
	
	//ACCESSEURS (A Simplifier)
	public long getIdProducteur() {return idProducteur;}
	public void setIdProducteur(long idProducteur) {this.idProducteur = idProducteur;}
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
	public String getPhoto() {return photo;}
	public void setPhoto(String photo) {this.photo = photo;}
	
	
	//Constructeur
	public ProducteurDal(String raisonSociale, String siren,
			String ligneAdresse1, String codePostal, String ville, 
			String latitude, String longitude, String description,
			int delaiLivraison, String photo) {
		
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
	
	public long save() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(rqInsert, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, raisonSociale);
		ps.setString(2, siren);
		ps.setString(3, ligneAdresse1);
		ps.setString(4, ligneAdresse2);
		ps.setString(5, codePostal);
		ps.setString(6, ville);
		ps.setString(7, latitude);
		ps.setString(8, longitude);
		ps.setString(9, description);
		ps.setInt   (10, delaiLivraison);
		ps.setString(11, photo);
		
		ps.executeUpdate();
		
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			idProducteur = generatedKeys.getLong(1);
			System.out.println(idProducteur);
		}
		return idProducteur;
	}
	
	
	public static List<Producteur> getListeProducteur(){
		
		listeProducteur = new ArrayList<Producteur>();
		
		Connection connection = PostgresConnection.GetConnexion();
		Statement state;
		
		try {
			state = connection.createStatement();
			ResultSet rs = state.executeQuery(rq);
			Producteur prod;
			
			while (rs.next()) {
				prod = new Producteur(rs.getLong("id_producteur"), rs.getString("raisonSociale"), rs.getString("siren"));
				listeProducteur.add(prod);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch (SQLException e){
				
			}
		}
		return listeProducteur;
	}
}
