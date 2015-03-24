/**
 * File modified by : Julien Caillon
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
 * @author Julien Caillon
 */
public class CommentaireDal extends DataLayerExtended {

	private final static String rqSelect = "SELECT id_commentaire, id_utilisateur, avis, note, etat, ts_creation FROM commentaires WHERE id_commentaire = ?;";
	private final static String rqInsert = "INSERT INTO commentaires (id_utilisateur, avis, note, etat) VALUES (?, ?, ?, ?)";
	private final static String rqUpdate = "UPDATE commentaires SET (avis, note, etat) = (?, ?, ?) WHERE id_commentaire = ?;";
	private final static String rqDelete = "DELETE FROM commentaires WHERE id_commentaire = ?;";

	// "SELECT id_commentaire FROM commentaires_produits WHERE id_produit = ? ORDER BY ts_creation;";
	private final static String rqSelListCommsProd = "SELECT id_commentaire, id_utilisateur, avis, note, etat, ts_creation FROM commentaires WHERE id_commentaire IN(SELECT id_commentaire FROM commentaires_produits WHERE id_produit = ?) ORDER BY ts_creation;";

	private Commentaire LocItem;

	/**
	 * Constructeur
	 * @param locItem
	 */
	public CommentaireDal(Commentaire locItem) {
		super();
		LocItem = locItem;
	}

	/**
	 * methode pour recuperer le commentaire avec l'id "idCommentaire"
	 * SELECT * FROM commentaires WHERE idCommentaire = ?
	 * @param LocItem
	 * @param idUtilisateur
	 * @throws SQLException
	 */
	public void select(long idCommentaire) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(rqSelect);
		ps.setLong(1, idCommentaire);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			LocItem.setIdCommentaire(idCommentaire);
			LocItem.setIdUtilisateur(rs.getLong("id_utilisateur"));
			LocItem.setAvis(rs.getString("avis"));
			LocItem.setNote(rs.getInt("note"));
			LocItem.setEtat(rs.getInt("etat"));
			LocItem.setTsCreation(rs.getTimestamp("ts_creation"));
			rs.close();
			ps.close();
			LocItem.setFromDb(true);
		}
	}


	/**
	 * recupere la liste des commentaires appartenant au produit "idProduit"
	 * SELECT * FROM commentaires WHERE id_commentaire IN(SELECT id_commentaire FROM commentaires_produits WHERE id_produit = ?) ORDER BY ts_creation
	 * @param idProduit
	 * @return
	 * @throws SQLException
	 */
	public static List<Commentaire> getListeCommsDal(long idProduit) throws SQLException {

		List<Commentaire> myList = new ArrayList<Commentaire>();

		Connection connection = PostgresConnection.GetConnexion();
		PreparedStatement ps = connection.prepareStatement(rqSelListCommsProd);
		ps.setLong(1, idProduit);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			myList.add(new Commentaire(
					rs.getLong("id_commentaire"),
					rs.getLong("id_utilisateur"),
					rs.getString("avis"),
					rs.getInt("note"),
					rs.getTimestamp("ts_creation"),
					rs.getInt("etat"),
					true));
		}

		ps.close();
		rs.close();

		return myList;
	}


	/**
	 *  Sauvegarde d'un nouvel item ou update d'un item deja existant dans la db
	 * @return long qui contient l'id de l'item sauve (ou -1 si le save est un echec)
	 * @throws SQLException
	 */
	public long save() throws SQLException {

		long newId = -1;

		// do we need to create this item or just update an existing one?
		if(LocItem.isFromDb()) {

			// UPDATE
			PreparedStatement ps = connection.prepareStatement(rqUpdate);
			ps.setString(1,  LocItem.getAvis());
			ps.setInt(2,  LocItem.getNote());
			ps.setInt(3,  LocItem.getEtat());

			int i = ps.executeUpdate();

			if (i == 1) {
				newId = LocItem.getIdCommentaire();
			}

			ps.close();

		} else {

			// INSERT
			PreparedStatement ps = connection.prepareStatement(rqInsert, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1,  LocItem.getIdUtilisateur());
			ps.setString(2,  LocItem.getAvis());
			ps.setInt(3,  LocItem.getNote());
			ps.setInt(4,  LocItem.getEtat());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				newId = rs.getLong(1);
				LocItem.setFromDb(true);
				LocItem.setIdCommentaire(newId);
			}

			ps.close();
			rs.close();
		}

		return newId;
	}


	/**
	 *  Delete l'item dans la db
	 * @return boolean avec true si reussite et false si echec
	 * @throws SQLException
	 */
	public boolean delete() throws SQLException {

		boolean isDeleted = false;

		PreparedStatement ps = connection.prepareStatement(rqDelete);
		ps.setLong(1,  LocItem.getIdCommentaire());
		int i = ps.executeUpdate();
		ps.close();

		if (i == 1) {
			isDeleted= true;
			LocItem.setFromDb(false);
		}

		return isDeleted;
	}
}
