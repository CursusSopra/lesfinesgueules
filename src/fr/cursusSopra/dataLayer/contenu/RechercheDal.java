package fr.cursusSopra.dataLayer.contenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import fr.cursusSopra.dataLayer.DataLayerExtended;
import fr.cursusSopra.tech.PostgresConnection;

public class RechercheDal extends DataLayerExtended {
	
	private long idType1;
	private String libelle1;
	

	static private HashMap<Integer, String> listeTypes1;
	static private HashMap<Integer, String> listeTypes2;
	static private HashMap<Integer, String> listeProducteurs;
	
	static private final String rqType1 = "SELECT * FROM types1";
	static private final String rqType2 = "SELECT * FROM types2";
	static private final String rqProducteurs = "SELECT * FROM producteurs";

	
	public void getType1(){
		
		long idType1;
		String libelle1;
		
		PreparedStatement ps;
		
		try {
			ps = connection.prepareStatement(rqType1);
			ResultSet rsQuery = ps
					.executeQuery();
			while (rsQuery.next()) {
				idType1 = rsQuery.getInt("id_type1");
				libelle1 = rsQuery.getString("libelle1");
			}
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		
	}


	public long getIdType1() {
		return idType1;
	}


	public String getLibelle1() {
		return libelle1;
	}


	public static HashMap<Integer, String> getListeTypes1() {
		return listeTypes1;
	}
	
	
//	public HashMap<Integer, String> getType1() throws SQLException {
//		
//		PreparedStatement ps = connection.prepareStatement(rqType1);
//	
//		listeTypes1 = new HashMap<Integer, String>();
//	
//		try {
//			ResultSet rsQuery = ps
//					.executeQuery();
//			while (rsQuery.next()) {
//				listeTypes1.put(rsQuery.getInt("id_type1"), rsQuery.getString("libelle1"));
//			}
//		} catch (Exception e) {
//
//		}
//
//		return listeTypes1;		
//		
//	}
	
//	static public HashMap<Integer, String> getType2() throws SQLException {
//		
//		Connection connection = PostgresConnection.getInstance().getConnection();
//		
//		PreparedStatement ps = connection.prepareStatement(rqType2);
//	
//		listeTypes2 = new HashMap<Integer, String>();
//	
//		try {
//			ResultSet rsQuery = ps
//					.executeQuery();
//			while (rsQuery.next()) {
//				listeTypes2.put(rsQuery.getInt("id_type2"), rsQuery.getString("libelle2"));
//			}
//		} catch (Exception e) {
//
//		}
//
//		return listeTypes2;		
//		
//	}
//	
//	static public HashMap<Integer, String> getProducteurs() throws SQLException {
//		
//		Connection connection = PostgresConnection.getInstance().getConnection();
//		
//		PreparedStatement ps = connection.prepareStatement(rqProducteurs);
//	
//		listeProducteurs = new HashMap<Integer, String>();
//	
//		try {
//			ResultSet rsQuery = ps
//					.executeQuery();
//			while (rsQuery.next()) {
//				listeProducteurs.put(rsQuery.getInt("id_prodcteur"), rsQuery.getString("raison_sociale"));
//			}
//		} catch (Exception e) {
//
//		}
//
//		return listeProducteurs;		
//		
//	}

}
