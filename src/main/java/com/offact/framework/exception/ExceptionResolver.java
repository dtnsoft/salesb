package com.offact.framework.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.offact.framework.constants.SPErrors;


/**
 * @author lim
 * Business Error Handling
 */
public class ExceptionResolver implements HandlerExceptionResolver{
	
	private final Logger logger = Logger.getLogger(getClass());
	
	private String view = null;
	
	public void setView(String view) {
		this.view = view;
	}
	
	public ModelAndView resolveException(HttpServletRequest request,
										 HttpServletResponse response, 
										 Object obj, 
										 Exception exception) {
		
		ModelAndView returnMV = new ModelAndView(); 
		
		if (exception instanceof BizException ) {
        	BizException bizException = (BizException)exception;
        	returnMV.addObject("errorCode", bizException.getErrorCode());
        	returnMV.addObject("errorDesc", bizException.getDetailMessage());
//        	권한 오류일 경우 
			if (StringUtils.equals(bizException.getErrorCode(),
					SPErrors.NEED_LOGIN.getCode())
					|| StringUtils.equals(bizException.getErrorCode(),
							SPErrors.NO_CLUB_MEMBER.getCode())
					|| StringUtils.equals(bizException.getErrorCode(),
							SPErrors.NO_CLUB_BOARD_READ_AUTH.getCode())
					|| StringUtils.equals(bizException.getErrorCode(),
							SPErrors.NO_CLUB_BOARD_WRITE_AUTH.getCode())
					|| StringUtils.equals(bizException.getErrorCode(),
							SPErrors.NO_CLUB_BOARD_RP_WRITE_AUTH.getCode())) {
				returnMV.setViewName("errors/warning");
			} else {
				returnMV.setViewName("errors/errors");
			}
        	
		} else {
			/*
			returnMV.addObject("errorCode", SPErrors.UNKNOWN_ERROR.getCode());
			returnMV.addObject("errorDesc", SPErrors.UNKNOWN_ERROR.getDescription());
			*/
			returnMV.addObject("errorCode", SPErrors.UNKNOWN_ERROR.getCode());
			returnMV.addObject("errorDesc", exception.getMessage());
			
			returnMV.setViewName("errors/500");
		}
		
		if (logger.isEnabledFor(Level.ERROR)) {
			logger.error(exception.getMessage());
			exception.printStackTrace();
		}
		
		return returnMV;
	}
	
}
