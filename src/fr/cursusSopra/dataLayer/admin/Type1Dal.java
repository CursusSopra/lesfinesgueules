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

public class Type1Dal extends DataLayerExtended{

	private final static String rqInsert = "INSERT INTO types1 (libelle1) VALUES(?)";
	private final static String rq = "SELECT * FROM types1";
	private final static String rqListeType2 = "SELECT * FROM types1 INNER JOIN types2 USING (id_type1) WHERE id_type1=?";

	private String libelle1;
	private long idType1;
	private static List<Type1> listeType1;
	private List<Type2> listeType2;
	
	public Type1Dal(long idType1) {
		this.idType1 = idType1;
		listeType2 = this.getListeType2();
	}
	
	public Type1Dal(String libelle1) {
		this.libelle1 = libelle1;
	}
	

	public long save() throws SQLException {
		
		//Génération de l'idType1 non utile dans le code, sert au débug
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
			System.out.println("Echec de la création de la liste type1");
		}finally{
			try{
				connection.close();
				System.out.println("fermeture de la connexion");
			}catch (SQLException e){
				System.out.println("echec de la fermeture de la connexion");
			}
		}
		
		
		return listeType1;
	}
	
	public List<Type2> getListeType2(){
		
		ArrayList<Type2> listeType2 = new ArrayList<Type2>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(rqListeType2);
			ps.setLong(1, idType1);
			ResultSet rs = ps.executeQuery();
			Type2 type2;
			
			while (rs.next()) {
				type2 = new Type2(rs.getLong("id_type2"),rs.getString("libelle2"));
				listeType2.add(type2);
			}
		} catch (SQLException e) {
			System.out.println("Echec de la création de la liste type1");
		}finally{
			try{
				connection.close();
				System.out.println("fermeture de la connexion");
			}catch (SQLException e){
				System.out.println("echec de la fermeture de la connexion");
			}
		}
		
		
		return listeType2;
	}
	
	public String getLibelle1() {return libelle1;}
	public long getIdType1() {return idType1;}
	public void setIdType1(long idType1) {this.idType1 = idType1;}
	public void setLibelle11(String libelle1) {this.libelle1 = libelle1;}

}
