/**
 * File modified by : Benoît
 */
package fr.cursusSopra.dataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.model.Commentaire;
import fr.cursusSopra.tech.PostgresConnection;

/**
 * @author Benoît
 */

public class ProduitDal {

	/* Properties */
	private static List<ProduitDal> listeProduitsDalRandom;
	
	private final static String rqProduit = "SELECT * FROM produits "
			+ "INNER JOIN types2 USING(id_type2) "
			+ "INNER JOIN types1 USING(id_type1) " + "WHERE id_produit = ? ";
	private final static String rqInsert = 
			"INSERT INTO produits (id_producteur, id_type2, description, prix, designation, photo, disponible) VALUES (?,?,?,?,?,?,?)";
	private final static String rqRandom= "SELECT * FROM produits WHERE disponible=true ORDER BY RANDOM() LIMIT 5";
	
	private long idProduit;
	private String designation;
	private String description;
	private double prix;
	private String photo;
	private boolean disponible;
	private long type1;
	private long type2;
	private long producteur;
	private List<Commentaire> listeCommentaires;
	
	/* Constructors */
	
	public ProduitDal(long idProduit) throws SQLException {
		PreparedStatement ps;
		Connection connection = PostgresConnection.GetConnexion();
		ps = connection.prepareStatement(rqProduit);
		ps.setLong(1, idProduit);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			this.idProduit = rs.getLong("id_produit");
			designation = rs.getString("designation");
			description = rs.getString("description");
			prix = rs.getDouble("prix");
			photo = rs.getString("photo");
			disponible = rs.getBoolean("disponible");
			type1 = rs.getLong("id_type1");
			type2 = rs.getLong("id_type2");
			producteur = rs.getLong("id_producteur");
		//	this.listeCommentaires = rs.getListeCommentaires();
		}
		ps.close();
		connection.close();
	}
	
	public ProduitDal(long producteur, long type2, double prix, String designation, boolean disponible, String description, String photo) {
		this.designation = designation;
		this.prix = prix;
		this.disponible = disponible;
		this.type2 = type2;
		this.producteur = producteur;
		this.description = description;
		this.photo = photo;
	}
	
	public ProduitDal(long idProduit, long producteur, long type2, double prix, String designation, boolean disponible, String description, String photo) {
		this.idProduit = idProduit;
		this.designation = designation;
		this.prix = prix;
		this.disponible = disponible;
		this.type2 = type2;
		this.producteur = producteur;
		this.description = description;
		this.photo = photo;
	}
	
	/* Methods */
	
	public long save() throws SQLException {
		Connection connection = PostgresConnection.GetConnexion();
		PreparedStatement ps = connection.prepareStatement(rqInsert, Statement.RETURN_GENERATED_KEYS);
		ps.setLong   (1, producteur);
		ps.setLong   (2, type2);
		ps.setString (3, description);
		ps.setDouble (4, prix);
		ps.setString (5, designation);
		ps.setString (6, photo);
		ps.setBoolean(7, disponible);
		
		ps.executeUpdate();
		
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			idProduit = generatedKeys.getLong(1);
			System.out.println(idProduit);
		}
		ps.close();
		connection.close();
		return idProduit;
	}
	
	static public List<ProduitDal> getListeProduitsDal(long idType1, long idType2, long idProducteur) {
		// List properties
		List<ProduitDal> listeProduitsDal;
		listeProduitsDal = new ArrayList<ProduitDal>();
		
		// Connection properties
		Connection c = PostgresConnection.GetConnexion();
		PreparedStatement ps = null;
		
		// Getting information from database
		try {
			if (idType1 >= 0 && idType2 == -1 && idProducteur == -1) {			// type1
				ps = c.prepareStatement("SELECT * FROM produits "
						+ "INNER JOIN types2 using(id_type2) "
						+ "WHERE id_type1 = ? "
						+ "ORDER BY designation");
				ps.setLong(1, idType1);
			} else if(idType2 >= 0 && idProducteur == -1) {						// type2 (& type1)
				ps = c.prepareStatement("SELECT * FROM produits "
						+ "WHERE id_type2 = ? "
						+ "ORDER BY designation");
				ps.setLong(1, idType2);
			} else if(idType1 >= 0 && idType2 == -1 && idProducteur >= 0) {		// type1 & producteur
				ps = c.prepareStatement("SELECT * FROM produits "
						+ "INNER JOIN types2 using(id_type2) "
						+ "WHERE id_type1 = ? AND id_producteur = ? "
						+ "ORDER BY designation");
				ps.setLong(1, idType1);
				ps.setLong(2, idProducteur);
			} else if(idType2 >= 0 && idProducteur >= 0) {						// type2 & producteur (& type1)
				ps = c.prepareStatement("SELECT * FROM produits "
						+ "WHERE id_type2 = ? AND id_producteur = ? "
						+ "ORDER BY designation");
				ps.setLong(1, idType2);
				ps.setLong(2, idProducteur);
			} else if(idProducteur >= 0) {										// producteur
				ps = c.prepareStatement("SELECT * FROM produits "
						+ "WHERE id_producteur = ? "
						+ "ORDER BY designation");
				ps.setLong(1, idProducteur);
			} else {															// tous les produits
				ps = c.prepareStatement("SELECT * FROM produits ORDER BY designation");
//				ps = c.prepareStatement("SELECT id_produit FROM produits ORDER BY designation");
			}
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			
			// Filling the list by instantiating new 'ProduitDal' object (go to database each time a product is created)
			while (rs.next()) {
//				listeProduitsDal.add(new ProduitDal(rs.getLong("id_produit")));
				long idProduit = rs.getLong("id_produit");
				String designation = rs.getString("designation");
				String description = rs.getString("description");
				double prix = rs.getDouble("prix");
				String photo = rs.getString("photo");
				boolean disponible = rs.getBoolean("disponible");
				long type2 = rs.getLong("id_type2");
				long producteur = rs.getLong("id_producteur");
				
				listeProduitsDal.add(new ProduitDal(idProduit, producteur, type2, prix, designation, disponible, description, photo));
			}
		} catch (SQLException e) {
			System.out.println("Connexion BDD impossible !");
		} finally {
			try {
				ps.close();
				c.close();
				System.out.println("Fermeture de la connexion ProduitDal.");
			} catch (Exception e1) {
				System.out.println("Fermeture de la connexion impossible !");
			}
		}
		return listeProduitsDal;
	}
	
	public static List<ProduitDal> getListeProduitsDalRandom() {
		
		listeProduitsDalRandom = new ArrayList<ProduitDal>();
		Connection connec = PostgresConnection.GetConnexion();
		PreparedStatement ps;
		
		try {
			ps = connec.prepareStatement(rqRandom);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				long idProd=rs.getLong("id_produit");
				//System.out.println(idProd);
				listeProduitsDalRandom.add(new ProduitDal(idProd));
				//System.out.println("------------");
			}
		} catch (SQLException e) {
			System.out.println("Connexion BDD impossible !");
		} finally {
			try {
				connec.close();
				System.out.println("Fermeture de la connexion.");
			} catch (Exception e1) {
				System.out.println("Fermeture de la connexion impossible !");
			}
		}
		return listeProduitsDalRandom;
	}

	/* Accessors */
	public long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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

	public void setPrix(Double prix) {
		this.prix = prix;
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

	public long getType1() {
		return type1;
	}

	public void setType1(long type1) {
		this.type1 = type1;
	}

	public long getType2() {
		return type2;
	}

	public void setType2(long type2) {
		this.type2 = type2;
	}

	public long getProducteur() {
		return producteur;
	}

	public void setProducteur(long producteur) {
		this.producteur = producteur;
	}

	public List<Commentaire> getListeCommentaires() {
		return listeCommentaires;
	}

}

