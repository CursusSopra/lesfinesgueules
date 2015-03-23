package fr.cursusSopra.action.admin;

import java.sql.SQLException;
import java.util.List;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.dataLayer.admin.Type1Dal;
import fr.cursusSopra.dataLayer.admin.Type2Dal;
import fr.cursusSopra.model.Type1;


public class Type2Action extends ActionSupportExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 860802915369885154L;

	private String type2;
	private List<Type1> listeType1;
	
	public String createType2Form() throws SQLException {
		
		setListeType1(Type1Dal.getListeType1());
		System.out.println(listeType1);
		
		return SUCCESS;
	}
	
	public String createType2() throws SQLException {
		
		setListeType1(Type1Dal.getListeType1());
		
		long l = type2.length();
		System.out.println(listeType1);

		if (l > 0) {
			l = new Type2Dal(type2).save();
			System.out.println(type2);
		}
		return (l > 0) ? SUCCESS : ERROR;
	}

	public String getType2() {return type2;}

	public void setType2(String type) {this.type2 = type;}
	public List<Type1> getListeType1() {return listeType1;}
	public void setListeType1(List<Type1> listeType1) {this.listeType1 = listeType1;}
}
