/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.dataLayer;

import java.sql.Connection;
/**
 * @author Julien C
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.model.Commande;
import fr.cursusSopra.tech.PostgresConnection;
/**
 * @author Julien Caillon
 */
public class CommandeDal extends DataLayerExtended {

	private final static String rqSelPanier = "SELECT id_commande, moyen_paiement, ts_validation, ts_archivage FROM commandes WHERE id_utilisateur = ? AND etat = ?;";

	private final static String rqSelect = "SELECT etat, moyen_paiement, ts_validation, ts_archivage FROM commandes WHERE id_commande = ? AND id_utilisateur = ?;";

	private final static String rqInsert = "INSERT INTO commandes (id_utilisateur, etat, moyen_paiement) VALUES(?, ?, ?)";
	private final static String rqUpdate = "UPDATE commandes SET (etat, moyen_paiement) = (?, ?) WHERE id_commande = ?;";
	private final static String rqDelete = "DELETE FROM commandes WHERE id_commande = ?;";

	private final static String rqSelectList = "SELECT id_commande, etat, moyen_paiement, ts_validation, ts_archivage FROM commandes WHERE id_utilisateur = ? ORDER BY ts_validation;";

	private Commande LocItem;

	/**
	 *  constructeur qui sert pour la methode save()
	 * @param LocItemC
	 */
	public CommandeDal(Commande LocItemC) {
		this.LocItem = LocItemC;
	}


	/**
	 * methode pour recuperer la commande panier de l'utilisateur "idUtilisateur"
	 * SELECT * FROM commandes WHERE id_utilisateur = ? AND etat = -1
	 * @param LocItem
	 * @param idUtilisateur
	 * @return
	 * @throws SQLException
	 */
	public void utilisateur(long idUtilisateur) throws SQLException {
		int etat = -1;
		PreparedStatement ps = connection.prepareStatement(rqSelPanier);
		ps.setLong(1, idUtilisateur);
		ps.setInt(2, etat);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			LocItem.setIdCommande(rs.getLong("id_commande"));
			LocItem.setIdUtilisateur(idUtilisateur);
			LocItem.setEtat(etat);
			LocItem.setMoyenPaiement(rs.getInt("moyen_paiement"));
			LocItem.setTsValidation(rs.getTimestamp("ts_validation"));
			LocItem.setTsArchivage(rs.getTimestamp("ts_archivage"));
			LocItem.setFromDb(true);
		}
		rs.close();
		ps.close();
	}


	/**
	 * methode pour recuperer la commande avec l'id "id"
	 * SELECT * FROM commandes WHERE id_commande = ? AND id_utilisateur = ?
	 * @param LocItem
	 * @param idUtilisateur
	 * @throws SQLException
	 */
	public void select(long idUtilisateur, long idCommande) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(rqSelect);
		ps.setLong(1, idCommande);
		ps.setLong(2, idUtilisateur);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			LocItem.setIdCommande(idCommande);
			LocItem.setIdUtilisateur(idUtilisateur);
			LocItem.setEtat(rs.getInt("etat"));
			LocItem.setMoyenPaiement(rs.getInt("moyen_paiement"));
			LocItem.setTsValidation(rs.getTimestamp("ts_validation"));
			LocItem.setTsArchivage(rs.getTimestamp("ts_archivage"));
			LocItem.setFromDb(true);
		}
		rs.close();
		ps.close();
	}


	/**
	 *  retourne la liste des commandes appartenant a l'utilisateur "idUtilisateur" et qui ont un etat >= 0
	 * SELECT id_commande, etat, moyen_paiement, ts_validation, ts_archivage FROM commandes WHERE id_utilisateur = ? ORDER BY ts_validation
	 * @param idCommande
	 * @param etat
	 * @return List<ItemCommande>
	 * @throws SQLException
	 */
	public static List<Commande> getListeCommandes(long idUtilisateur) throws SQLException {

		List<Commande> myList = new ArrayList<Commande>();

		Connection connection = PostgresConnection.GetConnexion();
		PreparedStatement ps = connection.prepareStatement(rqSelectList);
		ps.setLong(1, idUtilisateur);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Commande com = new Commande(
					idUtilisateur,
					rs.getLong("id_commande"));
			com.setEtat(rs.getInt("etat"));
			com.setMoyenPaiement(rs.getInt("moyen_paiement"));
			com.setTsValidation(rs.getTimestamp("ts_validation"));
			com.setTsArchivage(rs.getTimestamp("ts_archivage"));
			com.setFromDb(true);
			myList.add(com);
		}

		ps.close();
		rs.close();
		connection.close();

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
			ps.setInt(1,  LocItem.getEtat());
			ps.setInt(2,  LocItem.getMoyenPaiement());
			ps.setLong(3,  LocItem.getIdCommande());

			int i = ps.executeUpdate();

			if (i == 1) {
				newId = LocItem.getIdCommande();
			}

			ps.close();

		} else {

			// INSERT
			PreparedStatement ps = connection.prepareStatement(rqInsert, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1,  LocItem.getIdUtilisateur());
			ps.setInt(2,  LocItem.getEtat());
			ps.setInt(3,  LocItem.getMoyenPaiement());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				newId = rs.getLong(1);
				LocItem.setFromDb(true);
				LocItem.setIdCommande(newId);
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

		boolean isDeleted = true;

		PreparedStatement ps = connection.prepareStatement(rqDelete);
		ps.setLong(1,  LocItem.getIdCommande());
		isDeleted = isDeleted && (ps.executeUpdate() == 1);
		ps.close();

		if (isDeleted) LocItem.setFromDb(false);

		return isDeleted;
	}
}
