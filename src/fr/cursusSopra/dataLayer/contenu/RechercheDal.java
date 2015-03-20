package fr.cursusSopra.dataLayer.contenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import fr.cursusSopra.tech.PostgresConnection;

public class RechercheDal {
	
	private static Connection connection = PostgresConnection.getConnection();
	
	static private HashMap<Integer, String> listeTypes1;
	static private HashMap<Integer, String> listeTypes2;
	static private HashMap<Integer, String> listeProducteurs;
	
	static private final String rqType1 = "SELECT * FROM type1";
	static private final String rqType2 = "SELECT * FROM type2";
	static private final String rqProducteurs = "SELECT * FROM producteurs";
	
	
	static public HashMap<Integer, String> getType1() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(rqType1);
	
		listeTypes1 = new HashMap<Integer, String>();
	
		try {
			ResultSet rsQuery = ps
					.executeQuery();
			while (rsQuery.next()) {
				listeTypes1.put(rsQuery.getInt("id_type1"), rsQuery.getString("libelle1"));
			}
		} catch (Exception e) {

		}

		return listeTypes1;		
		
	}
	
	static public HashMap<Integer, String> getType2() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(rqType2);
	
		listeTypes2 = new HashMap<Integer, String>();
	
		try {
			ResultSet rsQuery = ps
					.executeQuery();
			while (rsQuery.next()) {
				listeTypes2.put(rsQuery.getInt("id_type2"), rsQuery.getString("libelle2"));
			}
		} catch (Exception e) {

		}

		return listeTypes2;		
		
	}
	
	static public HashMap<Integer, String> getProducteurs() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(rqProducteurs);
	
		listeProducteurs = new HashMap<Integer, String>();
	
		try {
			ResultSet rsQuery = ps
					.executeQuery();
			while (rsQuery.next()) {
				listeProducteurs.put(rsQuery.getInt("id_prodcteur"), rsQuery.getString("raison_sociale"));
			}
		} catch (Exception e) {

		}

		return listeProducteurs;		
		
	}

}
