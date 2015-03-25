/**
 * Modified by Nicolas
 */
package fr.cursusSopra.dataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.tech.PostgresConnection;

/**
 * 
 * @author Nicolas
 *
 */
public class ProducteurDal extends DataLayerExtended {
	
	/* PROPERTIES */
	
	private final static String rqInsert = 
			"INSERT INTO "
			+ "producteurs (raison_sociale, siren, ligne_adresse1, ligne_adresse2, code_postal, ville, gpslat, gpslong, description, delai_livraison, photo) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private final static String rqProducteur = 
			"SELECT id_producteur, raison_sociale, siren, ligne_adresse1, ligne_adresse2, code_postal, ville, gpslat, gpslong, description, delai_livraison, photo "
			+ "FROM producteurs "
			+ "WHERE id_producteur = ?";
	private final static String rqListeProducteurs = 
			"SELECT id_producteur "
			+ "FROM producteurs";
	private final static String rqUpdate = 
			"UPDATE "
			+ "producteurs (raison_sociale, siren, ligne_adresse1, ligne_adresse2, code_postal, ville, gpslat, gpslong, description, delai_livraison, photo) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?) "
			+ "WHERE id_producteur = ?";
	
	
	private long	idProducteur;
	private String	raisonSociale;
	private String	siren;
	private String	ligneAdresse1;
	private String	ligneAdresse2;
	private String	codePostal;
	private String	ville;
	private String	latitude;
	private String	longitude;
	private String	description;
	private int		delaiLivraison;
	private String	photo;
	private boolean fromDb = false;
	
	private static List<ProducteurDal> listeProducteursDal;
	
	/* ACCESSORS */
	
	public long		getIdProducteur() {return idProducteur;}
	public void		setIdProducteur(long idProducteur) {this.idProducteur = idProducteur;}
	public String	getRaisonSociale() {return raisonSociale;}
	public void		setRaisonSociale(String raisonSociale) {this.raisonSociale = raisonSociale;}
	public String	getSiren() {return siren;}
	public void		setSiren(String siren) {this.siren = siren;}
	public String	getLigneAdresse1() {return ligneAdresse1;}
	public void		setLigneAdresse1(String ligneAdresse1) {this.ligneAdresse1 = ligneAdresse1;}
	public String	getLigneAdresse2() {return ligneAdresse2;}
	public void		setLigneAdresse2(String ligneAdresse2) {this.ligneAdresse2 = ligneAdresse2;}
	public String	getCodePostal() {return codePostal;}
	public void		setCodePostal(String codePostal) {this.codePostal = codePostal;}
	public String	getVille() {return ville;}
	public void		setVille(String ville) {this.ville = ville;}
	public String	getLatitude() {return latitude;}
	public void		setLatitude(String latitude) {this.latitude = latitude;}
	public String	getLongitude() {return longitude;}
	public void		setLongitude(String longitude) {this.longitude = longitude;}
	public String	getDescription() {return description;}
	public void		setDescription(String description) {this.description = description;}
	public int		getDelaiLivraison() {return delaiLivraison;}
	public void		setDelaiLivraison(int delaiLivraison) {this.delaiLivraison = delaiLivraison;}
	public String	getPhoto() {return photo;}
	public void		setPhoto(String photo) {this.photo = photo;}
	
	/* CONSTRUCTORS */

	public boolean isFromDb() {
		return fromDb;
	}
	public void setFromDb(boolean fromDb) {
		this.fromDb = fromDb;
	}
	public ProducteurDal(String raisonSociale, String siren, String ligneAdresse1, String ligneAdresse2, String codePostal, String ville, String latitude, String longitude, String description, int delaiLivraison, String photo) {
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
	
	public ProducteurDal(long idProducteur){
		try {
			PreparedStatement ps = connection.prepareStatement(rqProducteur);
			ps.setLong(1, idProducteur);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				this.idProducteur = rs.getLong("id_producteur");
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
				photo = rs.getString("photo");
			}
		} catch (SQLException e) {
			System.out.println("Echec récupération producteur");
		} finally {
			try{
				System.out.println("Fermeture de la connexion (ProducteurDal - ProducteurDal)");
				connection.close();
			}catch (SQLException e){
				System.out.println("Echec de la fermeture de la connexion");
			}
		}
	}
	
	/* METHODS */
	
	public long save() throws SQLException {
		if(!fromDb){
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
		
		}
		else{
			
			PreparedStatement ps = connection.prepareStatement(rqInsert);
			
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
			ps.setLong(12, idProducteur);
			
			ps.executeUpdate();
			
		}
		return idProducteur;
	}
	
	/* STATIC METHODS */

	public static List<ProducteurDal> getListeProducteurDal(){
		
		listeProducteursDal = new ArrayList<ProducteurDal>();
		
		Connection connection = PostgresConnection.GetConnexion();
		Statement state;
		
		try {
			state = connection.createStatement();
			ResultSet rs = state.executeQuery(rqListeProducteurs);
			
			while (rs.next()) {
				listeProducteursDal.add(new ProducteurDal(rs.getLong("id_producteur")));
			}
		} catch (SQLException e) {
			System.out.println("Echec récupération liste des producteurs.");
		}finally{
			try{
				System.out.println("Fermeture de la connexion (ProducteurDal - getListeProducteurDal)");
				connection.close();
			}catch (SQLException e){
				System.out.println("Echec de la fermeture de la connexion.");
			}
		}
		return listeProducteursDal;
	}
}
