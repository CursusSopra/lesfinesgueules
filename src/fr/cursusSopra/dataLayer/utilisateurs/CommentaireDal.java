package fr.cursusSopra.dataLayer.utilisateurs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.cursusSopra.dataLayer.DataLayerExtended;

public class CommentaireDal extends DataLayerExtended {

	
	private final static String rqInsert = "INSERT INTO commentaires (avis, note, etat) VALUES (?,?,?)";
	
	private long idCommentaire;
	
	private String avis;
	private int note;
	private int etat;
	
	//constructeur
	public CommentaireDal(String avis, int note, int etat) {
		this.avis = avis;
		this.note = note;
		this.etat = etat;
	}
	
	//sauvegarde d'un commentaire en bdd
	public long save() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(rqInsert, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, avis);
		ps.setInt(2, note);
		ps.setInt(3, 0);
		
		ps.executeUpdate();
		
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			idCommentaire = generatedKeys.getLong(1);
		}
		return idCommentaire;
	}

}
