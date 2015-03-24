/**
 * Modified by Cecile
 */
package fr.cursusSopra.action.utilisateurs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.model.Utilisateur;

/**
 * 
 * @author Cécile
 *
 */

public class ListeUtilisateursAction extends ActionSupportExtended{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5788642102104502456L;
	

	private List<Utilisateur> listeUtilisateurs = new ArrayList<>();

	//accesseurs
	public List<Utilisateur> getListeUtilisateurs() {return listeUtilisateurs;}
	public void setListeUtilisateurs(List<Utilisateur> listeUtilisateurs) {this.listeUtilisateurs = listeUtilisateurs;}

	
	//methodes
	public String createListProfil() throws SQLException {
		listeUtilisateurs = Utilisateur.getListeUtilisateurs();
		return SUCCESS;
	}
	
}
