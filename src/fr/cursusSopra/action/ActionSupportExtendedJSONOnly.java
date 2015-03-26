/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.action;

import com.opensymphony.xwork2.ActionSupport;

import fr.cursusSopra.model.Utilisateur;

/**
 * @author Julien Caillon
 */
public abstract class ActionSupportExtendedJSONOnly extends ActionSupport {

	private static final long serialVersionUID = 9179808291856964976L;

	// Utilisateur est valorise uniquement si l'user est logge
	private Utilisateur utilisateur = null;

	// l'idUtilisateur est utilise uniquement pour retrouver le panier de l'user dans la db
	protected long idUtilisateur = -1;

	public ActionSupportExtendedJSONOnly() {

		//TODO: en attendant le login fonctionnel, on set l'utilisateur a admin par defaut
		/*
		if (loginok) {
			idUtilisateur = Utilisateur(idUtilisateur);
			idUtilisateur = utilisateur.getIdUtilisateur();
		} else {
			boolean needToCreateNew = false;
			if (exists(cookie(utilisateur))) {
				idUtilisateur = cookie(utilisateur);
				if (!Utilisateur(idUtilisateur).existsInDb) {
					needToCreateNew = true;
				}
			}
			if (needToCreateNew) {
				// on ajoute un nouvel utilisateur fictif qui va servir uniquement a stocker le panier de la personne non logge
				idUtilisateur = Utilisateur("ghost").save();
				// on stocke l'idUtilisateur recupere dans un cookie pour que notre user se souvienne de son panier
				newcookie(idUtilisateur);
			}
		}
		*/
		idUtilisateur = 1;

	}

	public long getIdUtilisateur() {
		return idUtilisateur;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

}
