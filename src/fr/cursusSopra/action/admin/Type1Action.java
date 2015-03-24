/**
 *  Modified By Julien J
 */
package fr.cursusSopra.action.admin;

import java.sql.SQLException;
import java.util.List;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.dataLayer.Type1Dal;
import fr.cursusSopra.model.Type1;

public class Type1Action extends ActionSupportExtended {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1884153199479981890L;

	private String type1;
	private List<Type1> listeType1;

	/**
	 * Action permettant d'acceder au formulaire d'ajoût d'un type1
	 *
	 * @return the string
	 */
	public String createType1Form() {
		return SUCCESS;
	}
	
	/**
	 * Action permettant de valider l'ajoût d'un nouveau type1
	 *
	 * @return the string
	 * @throws SQLException the SQL exception
	 */
	public String createType1() throws SQLException {
		long l = type1.length();

		if (l > 0) {
			l = new Type1Dal(type1).save();
		}
		return (l > 0) ? SUCCESS : ERROR;
	}
	

	/**
	 * Action permettant d'acceder au formulaire de modification des type1(s)
	 *
	 * @return the string
	 */
	public String modifyType1Form() {
		setListeType1(Type1.getListeType1());
		return SUCCESS;
	}
	
	/**
	 * Action permettant de valider la modification d'un type1
	 *
	 * @return the string
	 */
	public String modifyType1() {
		setListeType1(Type1.getListeType1());
		return SUCCESS;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public List<Type1> getListeType1() {
		return listeType1;
	}

	public void setListeType1(List<Type1> listeType1) {
		this.listeType1 = listeType1;
	}

}
