/**
 *  Modified By Julien J
 */
package fr.cursusSopra.action.admin;

import java.sql.SQLException;
import java.util.List;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.dataLayer.Type2Dal;
import fr.cursusSopra.model.Type1;


public class Type2Action extends ActionSupportExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 860802915369885154L;

	private String type2;
	private List<Type1> listeType1;
	private long idType1;
	
	/**
	 * Action permettant d'acceder au formulaire d'ajoût d'un type2
	 * Cette action charge la liste de tous les types1
	 *
	 * @return the string
	 * @throws SQLException the SQL exception
	 */
	public String createType2Form() throws SQLException {
		listeType1 = Type1.getListeType1();
		
		return SUCCESS;
	}
	
	/**
	 * Action permettant de valider un ajoût d'un type2
	 *
	 * @return the string
	 * @throws SQLException the SQL exception
	 */
	public String createType2() throws SQLException {
		
		listeType1 = Type1.getListeType1();
		
		System.out.println(idType1);
		
		long l = type2.length();

		if (l > 0) {
			l = new Type2Dal(type2, idType1).save();
			System.out.println(type2);
		}
		return (l > 0) ? SUCCESS : ERROR;
	}

	public String getType2() {return type2;}
	public void setType2(String type) {this.type2 = type;}
	public List<Type1> getListeType1() {return listeType1;}
	public void setListeType1(List<Type1> listeType1) {this.listeType1 = listeType1;}
	public void setIdType1(long idType1) {this.idType1 = idType1;}
	public long getIdType1() {return idType1;}

}
