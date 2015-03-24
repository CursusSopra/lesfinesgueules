/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.action.utilisateurs;


import java.sql.SQLException;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.model.Commentaire;
import fr.cursusSopra.tech.TypeCommentaire;

public class CommentaireAction extends ActionSupportExtended{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private long idCommentaire;

	private String avis;
	private int note;


	//accesseurs commentaire
	public long getIdCommentaire() {return idCommentaire;}
	public void setIdCommentaire(long idCommentaire) {this.idCommentaire = idCommentaire;}

	public String getAvis() {return avis;}
	public void setAvis(String avis) {this.avis = avis;}
	public int getNote() {return note;}
	public void setNote(int note) {this.note = note;}


	//formulaire ajout commentaire
	public String createCommentaireForm() {
		return SUCCESS;
	}

	//ajout utilisateur en BDD
	public String createCommentaire() throws SQLException {
		Commentaire commentaire = new Commentaire(1, idUtilisateur, avis, note, TypeCommentaire.PRODUIT);
		commentaire.save();
			return SUCCESS;
	}

}
