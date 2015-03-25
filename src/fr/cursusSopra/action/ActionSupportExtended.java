/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import fr.cursusSopra.model.Type1;
import fr.cursusSopra.model.Utilisateur;
import fr.cursusSopra.tech.Breadcrumbs;

/**
 * @author Julien Caillon
 */
public abstract class ActionSupportExtended extends ActionSupport {

	private static final long serialVersionUID = -4931119770070210257L;

	private List<Type1> listeType1;
	private List<Breadcrumbs> listeBreadcrumbs = new ArrayList<Breadcrumbs>();

	// Utilisateur est valorise uniquement si l'user est logge
	private Utilisateur utilisateur = null;

	// l'idUtilisateur est utilise uniquement pour retrouver le panier de l'user dans la db
	protected long idUtilisateur = -1;

	public ActionSupportExtended () {

		// base breadcrumb
		listeBreadcrumbs.add(new Breadcrumbs("Accueil", "retourIndex"));

		// liste des types (pour le menu navbar)
		listeType1 = Type1.getListeType1();

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


	public List<Breadcrumbs> getListeBreadcrumbs() {
		return listeBreadcrumbs;
	}

	public List<Type1> getListeType1() {
		return listeType1;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

}
