package fr.cursusSopra.dataLayer.utilisateurs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.cursusSopra.dataLayer.DataLayerExtended;

public class UtilisateurDal extends DataLayerExtended {

	
	private final static String rqInsert = 
			"INSERT INTO utilisateurs (nom, prenom, ligne_adresse_1, ligne_adresse_2, code_postal, ville, email, mdp, tel, photo, droits) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	private long idUtilisateur;
	
	private String nom;
	private String prenom;
	private String email;
	private String ligneAdresse1;
	private String ligneAdresse2;
	private String codePostal;
	private String ville;
	private String mdp;
	private String tel;
	private String photo;
	private int droits;
	
	
	//constructeur
	public UtilisateurDal(String nom, String prenom,
			String email, String ligneAdresse1, String ligneAdresse2,
			String codePostal, String ville, String mdp, String tel,
			String photo, int droits) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.ligneAdresse1 = ligneAdresse1;
		this.ligneAdresse2 = ligneAdresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.mdp = mdp;
		this.tel = tel;
		this.photo = photo;
		this.droits = droits;
	}
	
	//sauvegarde d'un utilisateur en BDD
	public long save() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(rqInsert, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, nom);
		ps.setString(2, prenom);
		ps.setString(3, ligneAdresse1);
		ps.setString(4, ligneAdresse2);
		ps.setString(5, codePostal);
		ps.setString(6, ville);
		ps.setString(7, email);
		ps.setString(8, mdp);
		ps.setString(9, tel);
		ps.setString(10, photo);
		ps.setInt(11, droits);
		
		ps.executeUpdate();
		
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			idUtilisateur = generatedKeys.getLong(1);
		}
		return idUtilisateur;
	}

}
