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

	private String libelle1;
	private List<Type1> listeType1;
	private long idType1;
	private long pk;
	
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
		long l = libelle1.length();

		if (l > 0) {
			l = new Type1Dal(libelle1).save();
		}
		return (l > 0) ? SUCCESS : ERROR;
	}
	

	/**
	 * Action permettant d'acceder au formulaire de modification des type1(s)
	 *
	 * @return the string
	 */
	public String modifyType1Form() {
		listeType1 = Type1.getListeType1();
		return SUCCESS;
	}
	
	/**
	 * Action permettant de valider la modification d'un type1
	 *
	 * @return the string
	 * @throws SQLException 
	 */
	public String modifyType1() throws SQLException {
		
		System.out.println("Libelle1 :" + libelle1);
		System.out.println("PK :" + pk);
		System.out.println("idType1 :" + idType1);
		
		long l = libelle1.length();

		if (l > 0) {
			Type1 type1 = new Type1(pk);
			type1.setLibelle1(libelle1);
			type1.modify();
		}
		listeType1 = Type1.getListeType1();
		
		return SUCCESS;
		//return (l > 0) ? SUCCESS : ERROR;
	}

	public String getLibelle1() {
		return libelle1;
	}

	public void setLibelle1(String libelle1) {
		this.libelle1 = libelle1;
	}

	@Override
	public List<Type1> getListeType1() {
		return listeType1;
	}

	public long getIdType1() {
		return idType1;
	}

	public void setIdType1(long idType1) {
		this.idType1 = idType1;
	}

	public long getPk() {
		return pk;
	}

	public void setPk(long pk) {
		this.pk = pk;
	}


}
