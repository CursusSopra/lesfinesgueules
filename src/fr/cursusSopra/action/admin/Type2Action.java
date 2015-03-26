/**
 *  Modified By Julien J
 */
package fr.cursusSopra.action.admin;

import java.sql.SQLException;
import java.util.List;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.dataLayer.Type2Dal;
import fr.cursusSopra.model.Type1;
import fr.cursusSopra.model.Type2;

public class Type2Action extends ActionSupportExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 860802915369885154L;

	private String libelle2;
	private List<Type1> listeType1;
	private long idType1;
	private long idType2;
	private String value;
	private long pk;
	private String libelle1;

	/**
	 * Action permettant d'acceder au formulaire d'ajoût d'un type2 Cette action
	 * charge la liste de tous les types1
	 *
	 * @return the string
	 * @throws SQLException
	 *             the SQL exception
	 */
	public String createType2Form() throws SQLException {
		listeType1 = Type1.getListeType1();

		return SUCCESS;
	}

	/**
	 * Action permettant de valider un ajoût d'un type2
	 *
	 * @return the string
	 * @throws SQLException
	 *             the SQL exception
	 */
	public String createType2() throws SQLException {

		listeType1 = Type1.getListeType1();

		long l = libelle2.length();

		if (l > 0) {
			l = new Type2Dal(libelle2, idType1).save();
			System.out.println(libelle2);
		}
		return (l > 0) ? SUCCESS : ERROR;
	}

	/**
	 * Action permettant d'acceder au formulaire de modification des type2(s)
	 *
	 * @return the string
	 */
	public String modifyType2Form() {
		System.out.println("Bienvenu dans l'action ModifyType2Form");
		listeType1 = Type1.getListeType1();
		return SUCCESS;
	}

	/**
	 * Action permettant de valider la modification d'un type2 associé à un type 1 choisi
	 *
	 * @return the string1
	 * @throws SQLException
	 */
	public String modifyType2() throws SQLException {
		System.out.println("méthode modifyType2 Bonjour!");
		idType2 =pk;
		libelle2= value;
		System.out.println("ID Type2 : " + idType2);
		System.out.println("Libelle Type2 : " +libelle2);
		long l = libelle2.length();

		if (l > 0) {
			Type2 type2 = new Type2(idType2);
			type2.setLibelle2(libelle2);
			type2.modify();
		}
		listeType1 = Type1.getListeType1();

		return SUCCESS;

	}

	public String getLibelle2() {
		return libelle2;
	}

	public void setLibelle2(String libelle2) {
		this.libelle2 = libelle2;
	}

	@Override
	public List<Type1> getListeType1() {
		return listeType1;
	}

	public void setListeType1(List<Type1> listeType1) {
		this.listeType1 = listeType1;
	}

	public void setIdType1(long idType1) {
		this.idType1 = idType1;
	}

	public long getIdType1() {
		return idType1;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getPk() {
		return pk;
	}

	public void setPk(long pk) {
		this.pk = pk;
	}

	public String getLibelle1() {
		return libelle1;
	}

	public void setLibelle1(String libelle1) {
		this.libelle1 = libelle1;
	}

	public long getIdType2() {
		return idType2;
	}

	public void setIdType2(long idType2) {
		this.idType2 = idType2;
	}

}
