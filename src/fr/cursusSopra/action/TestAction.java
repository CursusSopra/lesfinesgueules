package fr.cursusSopra.action;


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
