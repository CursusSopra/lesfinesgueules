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
	private List<Breadcrumbs> listeBreadcrumbs;
	private Utilisateur utilisateur;

	protected long idUtilisateur = 1;

	public ActionSupportExtended () {

		listeBreadcrumbs = new ArrayList<Breadcrumbs>();

		listeBreadcrumbs.add(new Breadcrumbs("Accueil", "retourIndex"));

		listeType1 = Type1.getListeType1();

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
