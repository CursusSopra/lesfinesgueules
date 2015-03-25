package fr.cursusSopra.tech;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthInterceptor implements Interceptor {

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
