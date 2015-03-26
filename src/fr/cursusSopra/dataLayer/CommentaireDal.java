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
import fr.cursusSopra.tech.TypeCommentaire;

/**
 * @author Julien Caillon
 */
public class CommentaireDal extends DataLayerExtended {

	private final static String rqInsert = "INSERT INTO commentaires (id_utilisateur, avis, note, etat) VALUES (?, ?, ?, ?)";
	private final static String rqUpdate = "UPDATE commentaires SET (avis, note, etat) = (?, ?, ?) WHERE id_commentaire = ?;";
	private final static String rqDelete = "DELETE FROM commentaires WHERE id_commentaire = ?;";

	private final static String rqGenInsert = "INSERT INTO xTABLEJOINTUREx (id_commentaire, xIDx) VALUES (?, ?)";
	private final static String rqGenDelete = "DELETE FROM xTABLEJOINTUREx WHERE id_commentaire = ? AND xIDx = ?;";

	private final static String rqSelListCommsProd = "SELECT id_commentaire, id_utilisateur, avis, note, etat, ts_creation, nom, prenom, photo FROM commentaires INNER JOIN utilisateurs USING(id_utilisateur) WHERE id_commentaire IN(SELECT id_commentaire FROM xTABLEJOINTUREx WHERE xIDx = ?) ORDER BY ts_creation;";

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
	 * recupere la liste des commentaires appartenant au "typeCommentaire" avec l'id "idType"
	 * exemple pour les commentaires_produits
	 * SELECT * FROM commentaires WHERE id_commentaire IN(SELECT id_commentaire FROM commentaires_produits WHERE id_produit = ?) ORDER BY ts_creation
	 * @param idType
	 * @return
	 * @throws SQLException
	 */
	public static List<Commentaire> getListeCommsDal(long idType, TypeCommentaire type) throws SQLException {

		List<Commentaire> myList = new ArrayList<Commentaire>();

		Connection connection = PostgresConnection.GetConnexion();
		PreparedStatement ps = connection.prepareStatement(changeType(rqSelListCommsProd, type));
		ps.setLong(1, idType);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			myList.add(new Commentaire(
					rs.getLong("id_commentaire"),
					rs.getLong("id_utilisateur"),
					rs.getString("avis"),
					rs.getInt("note"),
					rs.getTimestamp("ts_creation"),
					rs.getInt("etat"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("photo"),
					true,
					idType,
					type));
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

			// UPDATE, dans la table "commentaires" uniquement
			PreparedStatement ps = connection.prepareStatement(rqUpdate);
			ps.setString(1,  LocItem.getAvis());
			ps.setInt(2,  LocItem.getNote());
			ps.setInt(3,  LocItem.getEtat().toInt());

			int i = ps.executeUpdate();

			if (i == 1) {
				newId = LocItem.getIdCommentaire();
			}

			ps.close();

		} else {

			// INSERT
			// premiere partie, dans la table "commentaires"
			PreparedStatement ps = connection.prepareStatement(rqInsert, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1,  LocItem.getIdUtilisateur());
			ps.setString(2,  LocItem.getAvis());
			ps.setInt(3,  LocItem.getNote());
			ps.setInt(4,  LocItem.getEtat().toInt());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				newId = rs.getLong(1);
				LocItem.setIdCommentaire(newId);
			}
			ps.close();
			rs.close();

			// deuxieme partie dans la table "commentaires_TYPECOMMENTAIRE"
			if (newId > 0) {
				ps = connection.prepareStatement(changeType(rqGenInsert, LocItem.getType()));
				ps.setLong(1,  newId);
				ps.setLong(2,  LocItem.getIdType());
				if (ps.executeUpdate() == 1) LocItem.setFromDb(true);
				ps.close();
			}
		}

		return newId;
	}


	/**
	 *  Delete l'item dans la db
	 * @return boolean avec true si reussite et false si echec
	 * @throws SQLException
	 */
	public boolean delete() throws SQLException {

		boolean isDeleted = true;

		// premiere partie, dans la table "commentaires"
		PreparedStatement ps = connection.prepareStatement(rqDelete);
		ps.setLong(1,  LocItem.getIdCommentaire());
		isDeleted = isDeleted && (ps.executeUpdate() == 1);
		ps.close();

		// deuxieme partie dans la table "commentaires_TYPECOMMENTAIRE"
		ps = connection.prepareStatement(changeType(rqGenDelete, LocItem.getType()));
		ps.setLong(1,  LocItem.getIdCommentaire());
		ps.setLong(2,  LocItem.getIdType());
		isDeleted = isDeleted && (ps.executeUpdate() == 1);
		ps.close();

		if (isDeleted) LocItem.setFromDb(false);

		return isDeleted;
	}


	/**
	 * Permet d'avoir des requetes generiques avec les champs "xTABLEJOINTUREx" et "xIDx" qui sont remplaces
	 * dynamiquement par cette methode privee
	 * @param rq
	 * @param type
	 * @return
	 */
	private static String changeType(String rq, TypeCommentaire type) {
		String newrq = rq.replace("xTABLEJOINTUREx", TypeCommentaire.PRODUIT.getNomTable());
		return newrq.replace("xIDx", TypeCommentaire.PRODUIT.getNomId());
	}
}
