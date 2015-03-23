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

	private final static String rqSelect = "SELECT id_utilisateur, id_produit, etat, quantite, moyen_paiement, ts_creation, ts_validation, ts_archivage FROM items_commandes WHERE id_item_commande = ?;";
	private final static String rqInsert = "INSERT INTO items_commandes (id_utilisateur, id_produit, etat, quantite, moyen_paiement) VALUES(?, ?, ?, ?, ?)";
	private final static String rqUpdate = "UPDATE items_commandes SET (id_utilisateur, id_produit, etat, quantite, moyen_paiement) = (?, ?, ?, ?, ?) WHERE id_item_commande = ?;";
	private final static String rqSelectList = "SELECT id_item_commande, id_utilisateur, id_produit, etat, quantite, moyen_paiement, ts_creation, ts_validation, ts_archivage FROM items_commandes WHERE id_utilisateur = ? AND etat = ?;";
	private final static String rqSelectListComplete = "SELECT id_item_commande, id_utilisateur, id_produit, etat, quantite, moyen_paiement, ts_creation, ts_validation, ts_archivage FROM items_commandes WHERE id_utilisateur = ? ORDER BY ts_validation;";
	private final static String rqDelete = "DELETE FROM items_commandes WHERE id_item_commande = ?;";
	
	private ItemCommande LocItemC;
 
	/**
	 * constructeur pour recuperer un ItemCommande existant
	 * @param LocItemC
	 * @param id
	 * @throws SQLException
	 */
	public ItemCommandeDal(ItemCommande LocItemC, long id) throws SQLException {

		// Get the values for the cinema
		PreparedStatement ps = connection.prepareStatement(rqSelect);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			LocItemC.setEtat(rs.getInt("etat"));
			LocItemC.setIdProduit(rs.getLong("id_produit"));
			LocItemC.setIdUtilisateur(rs.getLong("id_utilisateur"));
			LocItemC.setMoyenPaiement(rs.getInt("moyen_paiement"));
			LocItemC.setQuantite(rs.getInt("quantite"));
			LocItemC.setTsArchivage(rs.getTimestamp("ts_archivage"));
			LocItemC.setTsCreation(rs.getTimestamp("ts_creation"));
			LocItemC.setTsValidation(rs.getTimestamp("ts_validation"));
			rs.close();
			ps.close();
			LocItemC.setFromDb(true);
		}
	}

	/**
	 *  constructeur qui sert pour la methode save()
	 * @param LocItemC
	 */
	public ItemCommandeDal(ItemCommande LocItemC) {
		this.LocItemC = LocItemC;
	}

	
	/**
	 *  contructeur vide
	 */
	public ItemCommandeDal() { }	

	
	/**
	 *  Sauvegarde d'un nouvel item ou update d'un item deja existant dans la db
	 * @return long qui contient l'id de l'item sauver (ou 0 si le save est un echec)
	 * @throws SQLException
	 */
	public long save() throws SQLException {
		
		long newId = 0;
		
		// do we need to create this item or just update an existing one?
		if(LocItemC.isFromDb()) {

			// UPDATE
			PreparedStatement ps = connection.prepareStatement(rqUpdate);
			ps.setLong(1,  LocItemC.getIdUtilisateur());
			ps.setLong(2,  LocItemC.getIdProduit());
			ps.setInt(3,  LocItemC.getEtat());
			ps.setInt(4,  LocItemC.getQuantite());
			ps.setInt(5,  LocItemC.getMoyenPaiement());
			ps.setLong(6,  LocItemC.getId());
			
			int i = ps.executeUpdate();
			
			if (i == 1) {
				newId = LocItemC.getId();
			}

			ps.close();
			
		} else {
			
			// INSERT
			PreparedStatement ps = connection.prepareStatement(rqInsert, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1,  LocItemC.getIdUtilisateur());
			ps.setLong(2,  LocItemC.getIdProduit());
			ps.setInt(3,  LocItemC.getEtat());
			ps.setInt(4,  LocItemC.getQuantite());
			ps.setInt(5,  LocItemC.getMoyenPaiement());
	
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				newId = rs.getLong(1);
				LocItemC.setFromDb(true);
				LocItemC.setId(newId);
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
		ps.setLong(1,  LocItemC.getId());
		int i = ps.executeUpdate();
		ps.close();
		
		if (i == 1) {
			isDeleted= true;
			LocItemC.setFromDb(false);
		}
		
		return isDeleted;
	}
	

	/**
	 *  retourne la liste des commandes existantes dans la db
	 * @param id
	 * @param etat
	 * @return List<ItemCommande>
	 * @throws SQLException
	 */
	public static List<ItemCommande> getListeCommandes(long id, int etat) throws SQLException {
		
		List<ItemCommande> myList = new ArrayList<ItemCommande>();
		
		Connection connection = PostgresConnection.GetConnexion();
		
		PreparedStatement ps;
		
		if (etat > 1) {
			ps = connection.prepareStatement(rqSelectListComplete);
			ps.setLong(1, id);
		} else {
			ps = connection.prepareStatement(rqSelectList);
			ps.setLong(1, id);
			ps.setInt(2, etat);
		}
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			
			ItemCommande ic = new ItemCommande(rs.getLong("id_utilisateur"), rs.getLong("id_produit"), rs.getInt("quantite"));
			
			ic.setEtat(rs.getInt("etat"));
			ic.setId(rs.getLong("id_item_commande"));
			ic.setMoyenPaiement(rs.getInt("moyen_paiement"));
			ic.setTsArchivage(rs.getTimestamp("ts_archivage"));
			ic.setTsCreation(rs.getTimestamp("ts_creation"));
			ic.setTsValidation(rs.getTimestamp("ts_validation"));
			ic.setFromDb(true);
			
			myList.add(ic);
		}
		
		ps.close();
		rs.close();

		return myList;
	}

	
	public ItemCommande getLocItemC() {
		return LocItemC;
	}

	public void setLocItemC(ItemCommande locItemC) {
		LocItemC = locItemC;
	}
	
}
