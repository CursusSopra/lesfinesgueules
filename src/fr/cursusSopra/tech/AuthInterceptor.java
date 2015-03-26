package fr.cursusSopra.tech;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import fr.cursusSopra.action.LoginAction;

public class AuthInterceptor implements Interceptor {
	private static final Logger logger = LogManager
			.getLogger(AuthInterceptor.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// Mémorisation de l'action demandée
		ActionContext context = invocation.getInvocationContext();
	    HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);

		String queryString = request.getQueryString();
		HttpSession session = request.getSession();
		session.setAttribute("savedUrl", request.getRequestURI()
				+ (queryString == null ? "" : ("?" + queryString)));
		
		logger.info(request.getRequestURI());
		

		Map<String, Object> sessionAttributes = invocation
				.getInvocationContext().getSession();
		
		if (sessionAttributes == null
				|| sessionAttributes.get("authorized") == null) {
			return "failure";
		} else {
			if (sessionAttributes.get("authorized").equals("yes")) {
				return invocation.invoke();
			} else {
				return "failure";
			}
		}
	}

}
