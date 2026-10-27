package com.rtdas.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SessionInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// ignore login page
		if (request.getServletPath().contains("login.epx")) { 
			return true;
		} else {

			Object accountId = request.getSession().getAttribute("userId");
			if (accountId == null) {
                System.out.println("----------in session------------"+request.getContextPath());
				//response.sendRedirect(request.getContextPath());
				request.logout();
				response.sendRedirect(request.getContextPath());
				
				return false;
			} else {
				return true;
			}
		}
	}
	// override postHandle() and afterCompletion()

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}
}
