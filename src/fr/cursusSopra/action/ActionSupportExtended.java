/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.action;

import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.model.Type1;
import fr.cursusSopra.tech.Breadcrumbs;

/**
 * @author Julien Caillon
 */
public abstract class ActionSupportExtended extends ActionSupportExtendedJSONOnly {

	private static final long serialVersionUID = -4931119770070210257L;

	private List<Type1> listeType1 = new ArrayList<Type1>();
	private List<Breadcrumbs> listeBreadcrumbs = new ArrayList<Breadcrumbs>();

	public ActionSupportExtended () {

		// base breadcrumb
		listeBreadcrumbs.add(new Breadcrumbs("Accueil", "retourIndex", null));

//		listeBreadcrumbs.add(new Breadcrumbs("fromage", "listeProduits", "?idType2=1"));

		// liste des types (pour le menu navbar)
		listeType1 = Type1.getListeForNavBar();
	}

	public List<Breadcrumbs> getListeBreadcrumbs() {
		return listeBreadcrumbs;
	}

	public List<Type1> getListeType1() {
		return listeType1;
	}

}
