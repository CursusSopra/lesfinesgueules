package fr.cursusSopra.action.admin;

import java.sql.SQLException;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.dataLayer.admin.Type1Dal;

public class Type1Action extends ActionSupportExtended {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1884153199479981890L;

	private String type1;

	public String createType1Form() {
		return SUCCESS;
	}
	
	public String createType1() throws SQLException {
		long l = type1.length();

		if (l > 0) {
			l = new Type1Dal(type1).save();
		}
		return (l > 0) ? SUCCESS : ERROR;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

}
