/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.cursusSopra.model.Utilisateur;
/**
 * @author Julien Caillon
 */
public abstract class ActionSupportExtendedJSONOnly extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 9179808291856964976L;
	private Map<String, Object> sessionMap;

	// Utilisateur est valorise uniquement si l'user est logge
	private Utilisateur utilisateur = null;

	// l'idUtilisateur est utilise uniquement pour retrouver le panier de l'user dans la db
	protected long idUtilisateur = -1;

	public ActionSupportExtendedJSONOnly() {

//		sessionMap.put("LocEditId", 2);
//		int id = (Integer) sessionMap.get("LocEditId");

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

	public String prepareEditCustomLocationForm()throws Exception{
	    //----some code here------//
		sessionMap.put("LocEditId", 2);
	    return SUCCESS;
	}

	public String editCustomLocation() throws Exception{
	  int id = (Integer) sessionMap.get("LocEditId");
//	  sessionMap.remove("LocEditId");

	  return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.sessionMap = map;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public long getIdUtilisateur() {
		return idUtilisateur;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

}
