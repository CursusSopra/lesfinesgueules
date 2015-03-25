/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.json;

import com.opensymphony.xwork2.Action;

import fr.cursusSopra.action.ActionSupportExtended;
/**
 * @author Julien Caillon
 */
public class JSONPanierAction extends ActionSupportExtended {

	private static final long serialVersionUID = -1505158767409145518L;

	private String val;

	public String addItem() {
		System.out.println("2");
		val = "additem";
		return Action.SUCCESS;
	}

	public String removeItem() {
		System.out.println("3");
		val = "removeItem";
		return Action.SUCCESS;
	}

	public String dataPanier() {
		System.out.println("4");
		val = "YOOO";
		return Action.SUCCESS;
	}


	/**
	 * GETTERS / SETTERS
	 */

	public String getVal() {
		return val;
	}

}
