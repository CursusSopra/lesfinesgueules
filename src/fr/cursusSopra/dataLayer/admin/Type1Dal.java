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
import fr.cursusSopra.tech.PostgresConnection;

public class Type1Dal extends DataLayerExtended{

	private final static String rqInsert = "INSERT INTO types1 (libelle1) VALUES(?)";
	private final static String rq = "SELECT * FROM types1";

	private String libelle1;
	private long idType1;
	private static List<Type1> listeType1;
	

	public Type1Dal(String libelle1) {
		this.libelle1 = libelle1;
	}
	

	public long save() throws SQLException {

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

	public static List<Type1> getListeType1(){
		
		listeType1 = new ArrayList<Type1>();
		
		Connection connection = PostgresConnection.GetConnexion();
		Statement state;
		
		try {
			state = connection.createStatement();
			ResultSet rs = state.executeQuery(rq);
			Type1 type1;
			
			while (rs.next()) {
				type1 = new Type1(rs.getLong("id_type1"),rs.getString("libelle1"));
				listeType1.add(type1);
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
		
		
		return listeType1;
	}
	
	public String getLibelle11() {return libelle1;}
	public long getIdType1() {return idType1;}
	public void setIdType1(long idType1) {this.idType1 = idType1;}
	public void setLibelle11(String libelle1) {this.libelle1 = libelle1;}

}
