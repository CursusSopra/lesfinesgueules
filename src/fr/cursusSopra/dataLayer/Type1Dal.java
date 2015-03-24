/**
 *  Modified By Julien J
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
public class Type1Dal extends DataLayerExtended {

	private final static String rqInsert = "INSERT INTO types1 (libelle1) VALUES(?)";
	private final static String rq = "SELECT * FROM types1";
	private final static String rqListeType2 = "SELECT * FROM types2 WHERE id_type1=?";
	private final static String rqType1 = "SELECT * FROM types1 WHERE id_type1=?";
	
	private String libelle1;
	private long idType1;
	private static List<Type1> listeType1;
	private List<Type2> listeType2;

	public Type1Dal(long idType1) {
		this.idType1 = idType1;
		listeType2 = this.getListeType2();
		try {
			Connection connection = PostgresConnection.GetConnexion();
			System.out.println("post create Statement");
			PreparedStatement ps = connection.prepareStatement(rqType1);
			
			ps.setLong(1, idType1);
			ResultSet rs = ps.executeQuery();
			

			if (rs.next()) {
				libelle1 = rs.getString("libelle1");
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("Echec de la récupération du type1");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("echec de la fermeture de la connexion");
			}
		}
	}

	public Type1Dal(String libelle1) {
		this.libelle1 = libelle1;
	}

	public long save() throws SQLException {

		// Génération de l'idType1 non utile dans le code, sert au débug
		PreparedStatement ps = connection.prepareStatement(rqInsert,
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, libelle1);

		ps.executeUpdate();
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			setIdType1(generatedKeys.getLong("id_type1"));
		}

		return idType1;
	}

	public static List<Type1> getListeType1Dal() {

		listeType1 = new ArrayList<Type1>();

		Connection connection = PostgresConnection.GetConnexion();
		Statement state;

		try {

			state = connection.createStatement();
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
				connection.close();
			} catch (SQLException e) {
				System.out.println("echec de la fermeture de la connexion");
			}
		}

		return listeType1;
	}

	public List<Type2> getListeType2() {

		ArrayList<Type2> lT2 = new ArrayList<Type2>();

		try {
			PreparedStatement ps = connection.prepareStatement(rqListeType2);
			ps.setLong(1, idType1);
			ResultSet rs = ps.executeQuery();
			Type2 type2;

			while (rs.next()) {
				
				String lib2 = rs.getString("libelle2");
				Long idt2 = rs.getLong("id_type2");
				
				type2 = new Type2(idt2,lib2);
				lT2.add(type2);
				
			}
			
		} catch (SQLException e) {
			System.out.println("Echec de la création de la liste des types 2 de ce type1");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("echec de la fermeture de la connexion");
			}
		}

		return lT2;
	}

	public String getLibelle1() {
		return libelle1;
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

}
