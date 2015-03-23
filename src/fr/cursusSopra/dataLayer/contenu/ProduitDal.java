package fr.cursusSopra.dataLayer.contenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.dataLayer.DataLayerExtended;
import fr.cursusSopra.model.Commentaire;
import fr.cursusSopra.tech.PostgresConnection;

public class ProduitDal extends DataLayerExtended {

	/* Properties */

	private static List<ProduitDal> listeProduitsDal;
	private final static String rqListe = "SELECT id_produit FROM produits INNER JOIN types2 using(id_type2) WHERE ? = ? ORDER BY designation";
	private final static String rqProduit = "SELECT * FROM produits "
			+ "INNER JOIN types2 USING(id_type2) "
			+ "INNER JOIN types1 USING(id_type1) " + "WHERE id_produit = ? ";

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
		ps = connection.prepareStatement(rqProduit);
		ps.setLong(1, idProduit);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			this.idProduit = rs.getLong("id_produit");
			designation = rs.getString("designation");
			description = rs.getString("designation");
			prix = rs.getDouble("prix");
			photo = rs.getString("photo");
			disponible = rs.getBoolean("disponible");
			type1 = rs.getLong("id_type1");
			type2 = rs.getLong("id_type2");
			producteur = rs.getLong("id_producteur");
			// this.listeCommentaires = rs.getListeCommentaires();
		}
	}

	/* Methods */

	static public List<ProduitDal> getListeProduitsDal(long idType1, long idType2, long idProducteur) {
		listeProduitsDal = new ArrayList<ProduitDal>();
		Connection c = PostgresConnection.GetConnexion();
		PreparedStatement ps;
		
		try {
			if (idType1 >= (long) 0 && idType2 == (long) -1 && idProducteur == (long) -1) {		// type1
				ps = c.prepareStatement("SELECT id_produit FROM produits "
						+ "INNER JOIN types2 using(id_type2) "
						+ "WHERE id_type1 = ? "
						+ "ORDER BY designation");
				ps.setLong(1, idType1);
			} else if(idType2 >= (long) 0 && idProducteur == (long) -1) {						// type2 (& type1)
				ps = c.prepareStatement("SELECT id_produit FROM produits "
						+ "WHERE id_type2 = ? "
						+ "ORDER BY designation");
				ps.setLong(1, idType2);
			} else if(idType1 >= (long) 0 && idType2 == (long) -1 && idProducteur >= (long) 0) {// type1 & producteur
				ps = c.prepareStatement("SELECT id_produit FROM produits "
						+ "INNER JOIN types2 using(id_type2) "
						+ "WHERE id_type1 = ? AND id_producteur = ? "
						+ "ORDER BY designation");
				ps.setLong(1, idType1);
				ps.setLong(2, idProducteur);
			} else if(idType2 >= (long) 0 && idProducteur >= (long) 0) {						// type2 & producteur (& type1)
				ps = c.prepareStatement("SELECT id_produit FROM produits "
						+ "WHERE id_type2 = ? AND id_producteur = ? "
						+ "ORDER BY designation");
				ps.setLong(1, idType2);
				ps.setLong(2, idProducteur);
			} else if(idProducteur >= (long) 0) {												// producteur
				ps = c.prepareStatement("SELECT id_produit FROM produits "
						+ "WHERE id_producteur = ? "
						+ "ORDER BY designation");
				ps.setLong(1, idProducteur);
			} else {																			// tous les produits
				ps = c.prepareStatement("SELECT id_produit FROM produits ORDER BY designation");
			}

			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
	
			while (rs.next()) {
				listeProduitsDal.add(new ProduitDal(rs.getLong("id_produit")));
			}
		} catch (SQLException e) {
			System.out.println("Connexion BDD impossible !");
		} finally {
			System.out.println("Fermeture de la connexion.");
			try {
				c.close();
			} catch (SQLException e1) {
				System.out.println("Fermeture de la connexion suite problème impossible !");
			}

			return listeProduitsDal;
		}
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

	public void setListeCommentaires(List<Commentaire> listeCommentaires) {
		this.listeCommentaires = listeCommentaires;
	}

}
