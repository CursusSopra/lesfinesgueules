package fr.cursusSopra.dataLayer.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.dataLayer.DataLayerExtended;
import fr.cursusSopra.model.Type1;
import fr.cursusSopra.model.Type2;
import fr.cursusSopra.tech.PostgresConnection;

public class Type2Dal extends DataLayerExtended {

	private final static String rqInsert = "INSERT INTO types2 (libelle2, id_type1) VALUES(?,?)";
	private final static String rq = "SELECT * FROM types1";

	private String type2;
	private long idType2;
	private long idType1;
	private static List<Type2> listeType2;
	

	public Type2Dal(String type2, long idType1) {
		this.type2 = type2;
		this.idType1=idType1;
	}
	

	public long save() throws SQLException {

		try {
			System.out.println(idType1);
			PreparedStatement ps = connection.prepareStatement(rqInsert,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, type2);
			ps.setLong(2, idType1);
			ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				setIdType2(generatedKeys.getLong("id_type2"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch (SQLException e){
				
			}
		}
		
		return idType2;
	}

	public String getType2() {return type2;}
	public long getIdType2() {return idType2;}

	public void setIdType2(long idType2) {this.idType2 = idType2;}
	public void setType2(String type2) {this.type2 = type2;}
	public long getIdType1() {return idType1;}
	public void setIdType1(long idType1) {this.idType1 = idType1;}

	public static List<Type2> getListeType2() throws SQLException {
		
		listeType2 = new ArrayList<Type2>();
		Connection connection = PostgresConnection.GetConnexion();
		
		try {
			Statement state = connection.createStatement();
			ResultSet rs = state.executeQuery(rq);
			Type2 type2;
			
			while (rs.next()) {
				type2 = new Type2(rs.getLong("id_type2"),rs.getString("libelle2"),rs.getLong("id_type1"));
				listeType2.add(type2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch (SQLException e){
				
			}
		}
		
		return listeType2;
	}
}
