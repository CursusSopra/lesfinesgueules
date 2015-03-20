package fr.cursusSopra.action;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@Results({ @Result(name = "success", location = "/views/test/test.jsp") })
public class TestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5716067716198227919L;

	@Action(value = "/test")
	public String execute() {
		return SUCCESS;
	}
}
