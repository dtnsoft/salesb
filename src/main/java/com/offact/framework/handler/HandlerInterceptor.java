package com.offact.framework.handler;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.offact.framework.constants.CodeConstant;
import com.offact.salesb.vo.UserVO;


/**
 * @author lim
 * 
 */
public class HandlerInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = Logger.getLogger(HandlerInterceptor.class);
	
//	@Value("#{config['LoginCheckUrl']}")
//	String propertyValue;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session  =  request.getSession(false);
		String rootPath = request.getContextPath();
		
		String url = request.getServletPath();
		
		if (url.equals("/login") || url.equals("/loginProc")) {
			return true;
		}
		
		StringBuffer loginPageUrl = new StringBuffer();
		loginPageUrl.append(rootPath + "/login?prevPage=" + url);
				
//		String[] loginCheckPages = propertyValue.split(",");
		
		boolean loginCheckYN = false;
		
//		for (int i = 0; i < loginCheckPages.length; i++) {
//			if (url.equals(loginCheckPages[i])) {
//				loginCheckYN = true;
//				break;
//			}
//		}
		
		// session을통한 userId의 확인
		if (session == null) {
			if (loginCheckYN == true) {
				String loginUrl = makeLoginUrlAndParameter(loginPageUrl, request);
				response.sendRedirect(loginUrl);
				return false;
			}
		} else {
			UserVO userInfo = (UserVO)session.getAttribute(CodeConstant.SESSION_USER_INFO);
			
			if (userInfo == null) {
				if (loginCheckYN == true) {
					String loginUrl = makeLoginUrlAndParameter(loginPageUrl, request);
					response.sendRedirect(loginUrl);
					return false;
				}
			}else if (userInfo != null && url.equals("/login")) {
				response.sendRedirect("/cs/index");
			}
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String url = request.getServletPath();
		
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
	/**
	 * 
	 * @param loginUrl
	 * @param request
	 * @return
	 */
	private String makeLoginUrlAndParameter(StringBuffer loginUrl, HttpServletRequest request) {
		
		Enumeration paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String paramName = (String)paramNames.nextElement();
			logger.debug("#########paramName############====>" + paramName);
			String[] paramValues = request.getParameterValues(paramName);
			for (String param : paramValues) {
				loginUrl.append("&"+paramName+"="+param);
			}
		}
		return loginUrl.toString();
	}
}
