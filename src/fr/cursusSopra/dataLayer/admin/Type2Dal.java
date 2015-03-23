package fr.cursusSopra.dataLayer.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.dataLayer.DataLayerExtended;
import fr.cursusSopra.model.Type2;
import fr.cursusSopra.tech.PostgresConnection;

public class Type2Dal extends DataLayerExtended {

	private final static String rqInsert = "INSERT INTO types2 (libelle2) VALUES(?)";
	private final static String rq = "SELECT * FROM types1";

	private String type2;
	private long idType2;
	private static List<Type2> listeType2;
	

	public Type2Dal(String type2) {
		this.type2 = type2;
	}
	

	public long save() throws SQLException {

		PreparedStatement ps = connection.prepareStatement(rqInsert,
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, type2);
		ps.executeUpdate();
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			setIdType2(generatedKeys.getLong("id_type2"));
		}
		return idType2;
	}

	public String getType2() {return type2;}
	public long getIdType2() {return idType2;}

	public void setIdType2(long idType2) {this.idType2 = idType2;}
	public void setType2(String type2) {this.type2 = type2;}

	public static List<Type2> getListeType2() throws SQLException {
		
		listeType2 = new ArrayList<Type2>();
		Connection connection = PostgresConnection.GetConnexion();
		
		Statement state = connection.createStatement();
		ResultSet rs = state.executeQuery(rq);
		Type2 type2;
		
		while (rs.next()) {
			type2 = new Type2(rs.getLong("id_type2"),rs.getString("libelle2"));
			listeType2.add(type2);
		}
		return listeType2;
	}
}
