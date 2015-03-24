package fr.cursusSopra.dataLayer.utilisateurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.dataLayer.DataLayerExtended;
import fr.cursusSopra.model.ItemCommande;
import fr.cursusSopra.tech.PostgresConnection;

public class ItemCommandeDal extends DataLayerExtended {

	private final static String rqInsert = "INSERT INTO items_commandes (id_produit, id_commande, quantite) VALUES(?, ?, ?)";
	private final static String rqUpdate = "UPDATE items_commandes SET (quantite) = (?) WHERE id_item_commande = ?;";
	private final static String rqDelete = "DELETE FROM items_commandes WHERE id_item_commande = ?;";

	private final static String rqSelectList = "SELECT id_item_commande, id_produit, id_commande, quantite, ts_creation FROM items_commandes WHERE id_commande = ? ORDER BY ts_creation;";

	private ItemCommande LocItem;

	/**
	 * constructeur
	 * @param LocItemC
	 */
	public ItemCommandeDal(ItemCommande LocItemC) {
		this.LocItem = LocItemC;
	}

	/**
	 * retourne la liste des itemscommandes appartenant a la commande "id_commande" SELECT * FROM items_commandes WHERE id_commande = ?
	 * @param idCommande
	 * @param etat
	 * @return List<ItemCommande>
	 * @throws SQLException
	 */
	public static List<ItemCommande> getListeItemsCommandes(long idCommande) throws SQLException {

		List<ItemCommande> myList = new ArrayList<ItemCommande>();

		Connection connection = PostgresConnection.GetConnexion();
		PreparedStatement ps = connection.prepareStatement(rqSelectList);
		ps.setLong(1, idCommande);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			ItemCommande ic = new ItemCommande(rs.getLong("id_item_commande"), rs.getLong("id_produit"), rs.getLong("id_commande"),
					rs.getInt("quantite"), rs.getTimestamp("ts_creation"), true);
			myList.add(ic);
		}

		ps.close();
		rs.close();

		return myList;
	}

	/**
	 * Sauvegarde d'un nouvel item ou update d'un item deja existant dans la db
	 * @return long qui contient l'id de l'item sauver (ou -1 si le save est un echec)
	 * @throws SQLException
	 */
	public long save() throws SQLException {

		long newId = -1;

		// do we need to create this item or just update an existing one?
		if (LocItem.isFromDb()) {

			// UPDATE
			PreparedStatement ps = connection.prepareStatement(rqUpdate);
			ps.setInt(1, LocItem.getQuantite());
			ps.setLong(2, LocItem.getIdItemCommande());

			int i = ps.executeUpdate();

			if (i == 1) {
				newId = LocItem.getIdItemCommande();
			}

			ps.close();

		} else {

			// INSERT
			PreparedStatement ps = connection.prepareStatement(rqInsert, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, LocItem.getIdProduit());
			ps.setLong(2, LocItem.getIdCommande());
			ps.setInt(3, LocItem.getQuantite());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				newId = rs.getLong(1);
				LocItem.setFromDb(true);
				LocItem.setIdItemCommande(newId);
			}

			ps.close();
			rs.close();
		}

		return newId;
	}
	

	/**
	 * Delete l'item dans la db
	 * @return boolean avec true si reussite et false si echec
	 * @throws SQLException
	 */
	public boolean delete() throws SQLException {

		boolean isDeleted = false;

		PreparedStatement ps = connection.prepareStatement(rqDelete);
		ps.setLong(1, LocItem.getIdItemCommande());
		int i = ps.executeUpdate();
		ps.close();

		if (i == 1) {
			isDeleted = true;
			LocItem.setFromDb(false);
		}

		return isDeleted;
	}
	

	/**
	 * GETTERS / SETTERS
	 */

	public ItemCommande getLocItem() {
		return LocItem;
	}

	public void setLocItem(ItemCommande locItem) {
		LocItem = locItem;
	}

}
