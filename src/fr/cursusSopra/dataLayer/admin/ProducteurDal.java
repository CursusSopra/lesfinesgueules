package fr.cursusSopra.dataLayer.admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.cursusSopra.dataLayer.DataLayerExtended;

public class ProducteurDal extends DataLayerExtended {
		
	private final static String rqInsert = 
			"INSERT INTO membres (raison_sociale, siren, ligne_adresse_1, ligne_adresse_2, code_postal, ville, gps, description, delai_livraison) VALUES (?,?,?,?,?,?,?,?,?)";
	
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
	
	//Constructeur
	public ProducteurDal(String raisonSociale, String siren,
			String ligneAdresse1, String ligneAdresse2, String codePostal,
			String ville, String coordonneesGPS, String description,
			int delaiLivraison) {
		
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
	
	public long save() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(rqInsert, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, raisonSociale);
		ps.setString(2, siren);
		ps.setString(3, ligneAdresse1);
		ps.setString(4, ligneAdresse2);
		ps.setString(5, codePostal);
		ps.setString(6, ville);
		ps.setString(7, coordonneesGPS);
		ps.setString(8, description);
		ps.setInt   (9, delaiLivraison);
		
		ps.executeUpdate();
		
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			idProducteur = generatedKeys.getLong(1);
			//System.out.println(idMembre);
		}
		return idProducteur;
	}
	
}
