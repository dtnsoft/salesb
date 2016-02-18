package com.offact.salesb.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.offact.framework.constants.CodeConstant;
import com.offact.framework.exception.BizException;
import com.offact.framework.jsonrpc.JSONRpcService;
import com.offact.framework.util.StringUtil;
import com.offact.salesb.service.CustomerService;
import com.offact.salesb.service.comunity.ComunityService;
import com.offact.salesb.vo.CustomerVO;
import com.offact.salesb.vo.comunity.ComunityVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class BoardController {

	private final Logger logger = Logger.getLogger(getClass());
	/*
    * log id 생성 
    */
	public String logid(){
		
		double id=Math.random();
		long t1 = System.currentTimeMillis ( ); 
		
		String logid=""+t1+id;
		
		return logid;
	}
	
	@Value("#{config['offact.host.url']}")
	private String host_url;

	@Autowired
	private CustomerService customerSvc;
	
	@Autowired
	private ComunityService comunitySvc;
	

	    /**
	     * 커뮤니티 전광판
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/board/main")
	    public ModelAndView main(HttpServletRequest request, 
	    		                       String groupId,
	    		                       HttpServletResponse response) throws BizException 
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start main");
	
	        ModelAndView mv = new ModelAndView();

	        mv.setViewName("/board/main");
	        mv.addObject("groupId", StringUtil.nvl(groupId,"SM001"));
	        
	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return mv;
	    }
	    
	    /**
	     * 커뮤니티 전광판
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/board/board")
	    public ModelAndView board(HttpServletRequest request,
	    							   String groupId,
	    		                       HttpServletResponse response) throws BizException 
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start board");
	
	        ModelAndView mv = new ModelAndView();

	        mv.setViewName("/board/board");
	        mv.addObject("groupId", StringUtil.nvl(groupId,"SM001"));
	        
	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return mv;
	    }
	    
	    /**
	     * 커뮤니티 전광판
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/board/event")
	    public ModelAndView event(HttpServletRequest request,
	    							   String groupId,
	    		                       HttpServletResponse response) throws BizException 
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start board");
	
	        ModelAndView mv = new ModelAndView();

	        mv.setViewName("/board/event");
	        mv.addObject("groupId", StringUtil.nvl(groupId,"SM001"));
	        
	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return mv;
	    }
	    
	    /**
	     * 커뮤니티 목록조회
	     * 
	     * @param UserManageVO
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/board/comunitylist")
	    public ModelAndView comunityList(@ModelAttribute("comunityVO") ComunityVO comunityVO, 
	    								 String groupId,
	    		                         HttpServletRequest request, 
	    		                         HttpServletResponse response) throws BizException 
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start : userConVO" + comunityVO);

			ModelAndView mv = new ModelAndView();
	   		
	        List<ComunityVO> comunityList = new ArrayList();
	        comunityVO.setGroupId(groupId);

	        // 커뮤니티목록조회
	        comunityList = comunitySvc.getComunity(comunityVO);

	   	    mv.addObject("comunityList", comunityList);
	   	 
	   		mv.setViewName("/board/comunityList");
	   		
	   		return mv;
	    }
}
