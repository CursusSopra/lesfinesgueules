package fr.cursusSopra.dataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.model.Producteur;
import fr.cursusSopra.tech.PostgresConnection;

/**
 * 
 * @author Nicolas
 *
 */
public class ProducteurDal extends DataLayerExtended {
		
	private final static String rqInsert = 
		"INSERT INTO producteurs (raison_sociale, siren, ligne_adresse1, ligne_adresse2, code_postal, ville, gpslat, gpslong, description, delai_livraison, photo) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	private final static String rq = "SELECT * FROM producteurs";
	private final static String rqProducteur = "SELECT * FROM producteurs WHERE id_producteur = ?";
	
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
	
	private static List<ProducteurDal> listeProducteurDal;
	
	
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
	public ProducteurDal(long idProducteur, String raisonSociale, String siren) {
		this.idProducteur = idProducteur;
		this.raisonSociale = raisonSociale;
		this.siren = siren;
	}
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
	
	public ProducteurDal(long idProducteur){
		try {
			PreparedStatement ps = connection.prepareStatement(rqProducteur);
			ps.setLong(1, idProducteur);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				raisonSociale = rs.getString("raison_sociale");
				siren = rs.getString("siren");
				ligneAdresse1 = rs.getString("ligne_adresse1");
				ligneAdresse2 = rs.getString("ligne_adresse2");
				codePostal = rs.getString("code_postal");
				ville = rs.getString("ville");
				latitude = rs.getString("gpslat");
				longitude = rs.getString("gpslong");
				description = rs.getString("description");
				delaiLivraison = rs.getInt("delai_livraison");
				photo = rs.getString("raison_sociale");
				
			}
		} catch (SQLException e) {
			System.out.println("Echec de la création de la liste type1");
		}finally{
			try{
				connection.close();
				System.out.println("fermeture de la connexion");
			}catch (SQLException e){
				System.out.println("echec de la fermeture de la connexion");
			}
		}
		
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
	
	//Liste de producteur à afficher pour créer un produit
	public static List<ProducteurDal> getListeProducteurDal(){
		
		listeProducteurDal = new ArrayList<ProducteurDal>();
		
		Connection connection = PostgresConnection.GetConnexion();
		Statement state;
		
		try {
			state = connection.createStatement();
			ResultSet rs = state.executeQuery(rq);
			ProducteurDal prod;
			
			while (rs.next()) {
				prod = new ProducteurDal(rs.getLong("id_producteur"), rs.getString("raison_sociale"), rs.getString("siren"));
				listeProducteurDal.add(prod);
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
		return listeProducteurDal;
	}
}