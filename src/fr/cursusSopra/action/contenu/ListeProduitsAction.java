/**
 * File modified by : Benoît
 */
package fr.cursusSopra.action.contenu;

import fr.cursusSopra.action.ActionSupportExtended;

public class ListeProduitsAction extends ActionSupportExtended {
	
	/* Serial ID */
	
	private static final long serialVersionUID = -293629928669269942L;
	
	/* Properties */
	
	private long idType1;
	private long idType2;
	private long idProducteur;
	
	/* Execute Method */
	
	@Override
	public String execute() {
		return SUCCESS;
	}
	
	/* Accessors */

	public void setIdType1(String idType1) {
		try {
			this.idType1 = Long.parseLong(idType1);
		} catch (Exception e) {
			System.out.println("Conversion impossible");
		}
	}
	
	public void setIdType2(String idType2) {
		try {
			this.idType2 = Long.parseLong(idType2);
		} catch (Exception e) {
			System.out.println("Conversion impossible");
		}
	}
	
	public void setIdProducteur(String idProducteur) {
		try {
			this.idProducteur = Long.parseLong(idProducteur);
		} catch (Exception e) {
			System.out.println("Conversion impossible");
		}
	}
	
	public long getIdType1() {
		return idType1;
	}
	
	public long getIdType2() {
		return idType2;
	}
	
	public long getIdProducteur() {
		return idProducteur;
	}
}
