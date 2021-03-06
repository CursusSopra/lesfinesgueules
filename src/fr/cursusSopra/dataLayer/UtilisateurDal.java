/**
 * Modified by Cecile
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
 * @author Cécile
 *
 */
public class UtilisateurDal extends DataLayerExtended {

	
	private final static String rqInsert = 
			"INSERT INTO utilisateurs (nom, prenom, ligne_adresse1, code_postal, ville, email, mdp, tel, photo, droits) VALUES (?,?,?,?,?,?,?,?,?,?)";

	private final static String rqListeUtilisateurs = 
			"SELECT * FROM utilisateurs";
	
	private final static String rqUpdate = 
			"UPDATE utilisateurs SET (nom, prenom, ligne_adresse1, code_postal, ville, tel) = (?,?,?,?,?,?) WHERE id_utilisateur = ?";
	
	private final static String rqSelect =
			"SELECT id_utilisateur, nom, prenom, ligne_adresse1, code_postal, ville, tel FROM utilisateurs WHERE id_utilisateur = ?";
	
	
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
	
	
	//Accesseurs
	public long getIdUtilisateur() {return idUtilisateur;}
	public void setIdUtilisateur(long idUtilisateur) {this.idUtilisateur = idUtilisateur;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getLigneAdresse1() {return ligneAdresse1;}
	public void setLigneAdresse1(String ligneAdresse1) {this.ligneAdresse1 = ligneAdresse1;}
	public String getLigneAdresse2() {return ligneAdresse2;}
	public void setLigneAdresse2(String ligneAdresse2) {this.ligneAdresse2 = ligneAdresse2;}
	public String getCodePostal() {return codePostal;}
	public void setCodePostal(String codePostal) {this.codePostal = codePostal;}
	public String getVille() {return ville;}
	public void setVille(String ville) {this.ville = ville;}
	public String getMdp() {return mdp;}
	public void setMdp(String mdp) {this.mdp = mdp;}
	public String getTel() {return tel;}
	public void setTel(String tel) {this.tel = tel;}
	public String getPhoto() {return photo;}
	public void setPhoto(String photo) {this.photo = photo;}
	public int getDroits() {return droits;}
	public void setDroits(int droits) {this.droits = droits;}

//-----------CONSTRUCTEURS-------------
	//constructeur complet
	public UtilisateurDal(String nom, String prenom, String ligneAdresse1,
			String codePostal, String ville, String email, String mdp, String tel,
			String photo, int droits) {
		this.nom = nom;
		this.prenom = prenom;
		this.ligneAdresse1 = ligneAdresse1;
		this.ligneAdresse2 = ligneAdresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.email = email;
		this.mdp = mdp;
		this.tel = tel;
		this.photo = photo;
		this.droits = droits;
	}
	
	//constructeur liste utilisateur avec nom, prenom, email affichés
	public UtilisateurDal(Long idUtilisateur, String nom, String prenom, String email) {
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	//constructeur update
	public UtilisateurDal(String nom, String prenom, String ligneAdresse1,
			String codePostal, String ville, String tel) {
		this.nom = nom;
		this.prenom = prenom;
		this.ligneAdresse1 = ligneAdresse1;
		this.codePostal = codePostal;
		this.ville = ville;
		this.tel = tel;
	}
	
	//constructeur détail utilisateur
	public UtilisateurDal(long idUtilisateur, String nom, String prenom, String ligneAdresse1,
			String codePostal, String ville, String tel) {
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.ligneAdresse1 = ligneAdresse1;
		this.codePostal = codePostal;
		this.ville = ville;
		this.tel = tel;
	}
	
	//constructeur retour formulaire pré-rempli
	public UtilisateurDal(long idUtilisateur) {
		
		try{PreparedStatement ps = connection.prepareStatement(rqSelect);
		ps.setLong(1, idUtilisateur);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			this.idUtilisateur = rs.getLong("id_utilisateur");
			nom = rs.getString("nom");
			prenom = rs.getString("prenom");
			ligneAdresse1 = rs.getString("ligne_adresse1");
			codePostal = rs.getString("code_postal");
			ville = rs.getString("ville");
			tel = rs.getString("tel");
			}
		} catch (SQLException e) {
		} finally {
			try{connection.close();
			}catch (SQLException e){
			}
		}	
	}
	
//----------METHODES------------
	
		//sauvegarde de l'utilisateur en BDD
		public long save() throws SQLException {
			PreparedStatement ps = connection.prepareStatement(rqInsert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, nom);
			ps.setString(2, prenom);
			ps.setString(3, ligneAdresse1);
			ps.setString(4, codePostal);
			ps.setString(5, ville);
			ps.setString(6, email);
			ps.setString(7, mdp);
			ps.setString(8, tel);
			ps.setString(9, "");
			ps.setInt(10, 0);
			
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				idUtilisateur = generatedKeys.getLong(1);
				
			ps.close();
			}
			return idUtilisateur;
		}
		
		//update utilisateur
		public long update(long idUtilisateur) throws SQLException {
			PreparedStatement ps = connection.prepareStatement(rqUpdate);
			ps.setString(1, nom);
			ps.setString(2, prenom);
			ps.setString(3, ligneAdresse1);
			ps.setString(4, codePostal);
			ps.setString(5, ville);
			ps.setString(6, tel);
			ps.setLong(7, idUtilisateur);
			ps.executeUpdate();
			ps.close();
			return idUtilisateur;
		}
	
		//info profil utilisateur
		public long select() throws SQLException {
			PreparedStatement ps = connection.prepareStatement(rqSelect);
			ps.setLong(1, idUtilisateur);
			ps.executeUpdate();
			ps.close();
			return idUtilisateur;
		}
		
		//authentification
		public static boolean isInBase(String email, String mdp) {
			Connection c = PostgresConnection.GetConnexion();
			String rq = "SELECT count(*) nb FROM utilisateurs WHERE email=? AND mdp=?";
			boolean res = false;
			try {
				PreparedStatement ps = c.prepareStatement(rq);
				ps.setString(1, email);
				ps.setString(2, mdp);
				ResultSet rs = ps.executeQuery();
				
				rs.next();
				long nb = rs.getLong("nb");
				res = nb == 1;
				System.out.println(nb);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}

			return res;
		}
		
		//liste des utilisateurs
		private static List<UtilisateurDal> listeUtilisateursDal;

		public static List<UtilisateurDal> getListeUtilisateurs() throws SQLException {
			listeUtilisateursDal = new ArrayList<UtilisateurDal>();
			
			Connection c = PostgresConnection.GetConnexion();
			
			try{
			Statement ps = c.createStatement();
			ResultSet rs = ps.executeQuery(rqListeUtilisateurs);
			
				while (rs.next()) {
					UtilisateurDal ud = new UtilisateurDal(rs.getLong("id_utilisateur"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"));
					
					listeUtilisateursDal.add(ud);
				}
			}catch (SQLException e) {
				e.printStackTrace();
				}finally {
					try{
					c.close();
					}catch (SQLException e){
					}
			}
			return listeUtilisateursDal;
		}
				
}
