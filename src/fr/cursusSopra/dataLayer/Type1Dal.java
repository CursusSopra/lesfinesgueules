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

import fr.cursusSopra.model.Type1;
import fr.cursusSopra.model.Type2;
import fr.cursusSopra.tech.PostgresConnection;

/**
 *
 * @author Julien J
 *
 */
public class Type1Dal {

	private final static String rqInsert = "INSERT INTO types1 (libelle1) VALUES(?)";
	private final static String rq = "SELECT * FROM types1";
	private final static String rqListeType2 = "SELECT * FROM types2 WHERE id_type1=?";
	private final static String rqType1 = "SELECT * FROM types1 WHERE id_type1=?";
	private final static String rqModify = "UPDATE types1 SET libelle1 = ? WHERE id_type1 = ?";

	private final static String rqSelAll = "SELECT libelle1, libelle2, id_type1, id_type2 FROM types2 INNER JOIN types1 USING(id_type1)";

	private String libelle1;
	private long idType1;
	private static List<Type1> listeType1;
	private List<Type2> listeType2;

	public Type1Dal(long idType1) throws SQLException {
		this.idType1 = idType1;
		setListeType2(this.recupListeType2());

		System.out.println("post create Statement");
		Connection connection = PostgresConnection.GetConnexion();
		PreparedStatement ps = connection.prepareStatement(rqType1);

		ps.setLong(1, idType1);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			System.out.println(rs.getString("libelle1"));
			libelle1 = rs.getString("libelle1");
		}

		 try {
			 ps.close();
			 connection.close();
		 } catch (SQLException e) {
			 System.out.println("echec de la fermeture de la connexion");
		 }

	}

	public Type1Dal(String libelle1) {
		this.libelle1 = libelle1;
	}

	public long save() throws SQLException {

		// Génération de l'idType1 non utile dans le code, sert au débug
		Connection connection = PostgresConnection.GetConnexion();
		PreparedStatement ps = connection.prepareStatement(rqInsert,
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, libelle1);

		ps.executeUpdate();
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			setIdType1(generatedKeys.getLong("id_type1"));
		}

		ps.close();
		connection.close();

		return idType1;
	}

	public void modify() throws SQLException {

		// Génération de l'idType1 non utile dans le code, sert au débug
		Connection connection = PostgresConnection.GetConnexion();
		PreparedStatement ps = connection.prepareStatement(rqModify);
		ps.setString(1, libelle1);
		ps.setLong(2, idType1);

		ps.executeUpdate();

		ps.close();
		connection.close();

	}

	public static List<Type1> getListeType1Dal() {

		listeType1 = new ArrayList<Type1>();

		Connection conn = PostgresConnection.GetConnexion();

		try {
			Statement state;
			state = conn.createStatement();
			ResultSet rs = state.executeQuery(rq);
			Type1 type1;

			while (rs.next()) {
				type1 = new Type1(rs.getLong("id_type1"),
						rs.getString("libelle1"));
				listeType1.add(type1);
			}

		} catch (SQLException e) {
			System.out.println("Echec de la création de la liste type1");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("echec de la fermeture de la connexion");
			}
		}

		return listeType1;
	}

	public List<Type2> recupListeType2() throws SQLException {

		ArrayList<Type2> lT2 = new ArrayList<Type2>();

		Connection connection = PostgresConnection.GetConnexion();
		PreparedStatement ps = connection.prepareStatement(rqListeType2);
		ps.setLong(1, idType1);
		ResultSet rs = ps.executeQuery();
		Type2 type2;

		while (rs.next()) {
			System.out.println(rs.getString("libelle2"));
			String lib2 = rs.getString("libelle2");
			Long idt2 = rs.getLong("id_type2");

			type2 = new Type2(idt2, lib2);
			lT2.add(type2);

		}

		ps.close();
		connection.close();

		return lT2;
	}

	public static List<Type1> getListeForNavBar() throws SQLException {

		listeType1 = new ArrayList<Type1>();

		Connection conn = PostgresConnection.GetConnexion();
		conn.setClientInfo("ApplicationName", Thread.currentThread().getName());

		Statement state;
		state = conn.createStatement();
		ResultSet rs = state.executeQuery(rqSelAll);

		long idt1Prev = -1;
		Type1 type1 = null;
		while (rs.next()) {
			// Si rupture sur id_type1, on crée un nouvel objet
			long idt1 = rs.getLong("id_type1");
			if (idt1Prev != idt1) {
				// On ajoute type1 à la collection finale
				listeType1.add(type1);

				// On en créé un nouveau
				type1 = new Type1(idt1, rs.getString("libelle1"));

				// On créé sa liste de type2
				type1.setListeType2(new ArrayList<Type2>());

				// On mémorise l'identifiant comme ancien
				idt1Prev = idt1;
			}

			// On créé un objet type2 à toutes les itérations
			Type2 type2 = new Type2(rs.getLong("id_type2"),
					rs.getString("libelle2"));
			// Et on l'ajoute à la collection
			type1.getListeType2().add(type2);
		}

		rs.close();
		state.close();
		conn.close();

		return listeType1;
	}

	public String getLibelle1() {
		return libelle1;
	}

	public void setLibelle1(String libelle1) {
		this.libelle1 = libelle1;
	}

	public long getIdType1() {
		return idType1;
	}

	public void setIdType1(long idType1) {
		this.idType1 = idType1;
	}

	public void setLibelle11(String libelle1) {
		this.libelle1 = libelle1;
	}

	public List<Type2> getListeType2() {
		return listeType2;
	}

	public void setListeType2(List<Type2> listeType2) {
		this.listeType2 = listeType2;
	}

}
