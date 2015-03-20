package fr.cursusSopra.action;

<<<<<<< HEAD
=======
//import org.apache.struts2.convention.annotation.Action;
//import org.apache.struts2.convention.annotation.ParentPackage;
//import org.apache.struts2.convention.annotation.Result;
//import org.apache.struts2.convention.annotation.Results;
>>>>>>> b581ab5395edbb4ae5da4528a4085191d5de6705

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
