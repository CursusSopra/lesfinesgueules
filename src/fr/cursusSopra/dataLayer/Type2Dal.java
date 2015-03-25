/**
 *  Modified By Julien J
 */
package fr.cursusSopra.dataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Julien J
 *
 */
public class Type2Dal extends DataLayerExtended {

	private final static String rqInsert = "INSERT INTO types2 (libelle2, id_type1) VALUES(?,?)";
	private final static String rqType2 = "SELECT * FROM types2 WHERE id_type2=?";

	private String libelle2;
	private long idType2;
	private long idType1;

	public Type2Dal(long idType2) throws SQLException {
		this.idType1 = idType2;

		PreparedStatement ps = connection.prepareStatement(rqType2);
		ps.setLong(1, idType2);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			libelle2 = rs.getString("libelle2");
			idType1 = rs.getLong("id_type1");
		}

	}

	public Type2Dal(String type2, long idType1) {
		this.libelle2 = type2;
		this.idType1 = idType1;
	}

	public long save() throws SQLException {

		// Génération de l'idType2 non utile dans le code, sert au débug
		PreparedStatement ps = connection.prepareStatement(rqInsert,
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, libelle2);
		ps.setLong(2, idType1);
		ps.executeUpdate();
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			setIdType2(generatedKeys.getLong("id_type2"));
		}

		return idType2;
	}

	public long getIdType2() {
		return idType2;
	}

	public void setIdType2(long idType2) {
		this.idType2 = idType2;
	}

	public String getLibelle2() {
		return libelle2;
	}

	public void setLibelle2(String libelle2) {
		this.libelle2 = libelle2;
	}

	public long getIdType1() {
		return idType1;
	}

	public void setIdType1(long idType1) {
		this.idType1 = idType1;
	}

}
