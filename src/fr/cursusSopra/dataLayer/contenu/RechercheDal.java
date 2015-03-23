package fr.cursusSopra.dataLayer.contenu;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import fr.cursusSopra.dataLayer.DataLayerExtended;


public class RechercheDal extends DataLayerExtended {
	
	private long idType1Dal;
	private String libelle1Dal;
	

	static private HashMap<Integer, String> listeTypes1;
	static private HashMap<Integer, String> listeTypes2;
	static private HashMap<Integer, String> listeProducteurs;
	
	static private final String rqType1 = "SELECT * FROM types1";
	static private final String rqType2 = "SELECT * FROM types2";
	static private final String rqProducteurs = "SELECT * FROM producteurs";

	
	public HashMap<Integer, String> getListeTypes(){
		
				
		PreparedStatement ps;
		
		try {
			ps = connection.prepareStatement(rqType1);
			ResultSet rsQuery = ps.executeQuery();
			
			listeTypes1 = new HashMap<Integer, String>();
			
			while (rsQuery.next()) {
				
				listeTypes1.put(rsQuery.getInt("id_type1"), rsQuery.getString("libelle1"));
			}
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		return listeTypes1;
	}


	


	public long getIdType1Dal() {
		return idType1Dal;
	}


	public String getLibelle1Dal() {
		return libelle1Dal;
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
