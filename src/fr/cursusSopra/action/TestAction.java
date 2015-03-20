package fr.cursusSopra.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

//@ParentPackage("default")
//@Results({ @Result(name = "success", type="tiles", location = "test") })
public class TestAction extends ActionSupportExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5716067716198227919L;

//	@Action(value = "test")
	public String execute() {
		return SUCCESS;
	}
}
