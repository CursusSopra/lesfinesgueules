package fr.cursusSopra.action;

//import org.apache.struts2.convention.annotation.Result;
//import org.apache.struts2.convention.annotation.Results;

//@Results({ @Result(name = "success", type="tiles", location = "index") })
public class IndexAction extends ActionSupportExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2639338600299902248L;

	public String execute() {
		return SUCCESS;
	}

}
