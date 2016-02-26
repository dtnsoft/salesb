package com.offact.salesb.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.offact.framework.constants.CodeConstant;
import com.offact.framework.exception.BizException;
import com.offact.framework.jsonrpc.JSONRpcService;
import com.offact.framework.util.StringUtil;
import com.offact.salesb.service.CustomerService;
import com.offact.salesb.service.common.MailService;
import com.offact.salesb.service.comunity.ComunityService;
import com.offact.salesb.service.comunity.AsService;
import com.offact.salesb.vo.CustomerVO;
import com.offact.salesb.vo.MultipartFileVO;
import com.offact.salesb.vo.comunity.ComunityVO;
import com.offact.salesb.vo.comunity.CounselVO;
import com.offact.salesb.vo.common.EmailVO;
import com.offact.salesb.vo.common.GroupVO;
import com.offact.salesb.vo.common.SmsVO;
import com.offact.salesb.vo.common.UserVO;
import com.offact.salesb.vo.common.WorkVO;
import com.offact.salesb.vo.comunity.AsVO;
import com.offact.salesb.vo.manage.UserManageVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class BusinessController {

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
    private String hostUrl;

	@Value("#{config['offact.mail.orderfromemail']}")
    private String orderfromemail;
    
    @Value("#{config['offact.mail.ordersubject']}")
    private String ordersubject;
    
    @Value("#{config['offact.dev.option']}")
    private String devOption;
    
    @Value("#{config['offact.dev.sms']}")
    private String devSms;
    
    @Value("#{config['offact.sms.smsid']}")
    private String smsId;
    
    @Value("#{config['offact.sms.smspw']}")
    private String smsPw;
    
    @Value("#{config['offact.sms.smstype']}")
    private String smsType;
    
	@Autowired
	private CustomerService customerSvc;
	
	@Autowired
	private ComunityService comunitySvc;
	
    @Autowired
    private AsService asSvc;
	
    @Autowired
    private MailService mailSvc;
   
    /**
     * 상품관리 화면 로딩
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/business/goodsmanage")
    public ModelAndView goodsManage(HttpServletRequest request, 
    		                       HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start ");

        ModelAndView mv = new ModelAndView();
        
        // 사용자 세션정보
        HttpSession session = request.getSession();
        
        String customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
        String customerName = StringUtil.nvl((String) session.getAttribute("customerName")); 
        String customerId = StringUtil.nvl((String) session.getAttribute("customerId"));
        String groupId = StringUtil.nvl((String) session.getAttribute("groupId"));
        
        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

 	       	//mv.setViewName("/common/customerLoginForm");
        	mv.setViewName("/common/sessionOut");
        	return mv;
		}

        mv.setViewName("/business/goodsManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 등록상품 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/business/goodspagelist")
    public ModelAndView goodsPageList(@ModelAttribute("asConVO") AsVO asConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : asConVO" + asConVO);

        ModelAndView mv = new ModelAndView();

     // 사용자 세션정보
        HttpSession session = request.getSession();
        
        String customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
        String customerName = StringUtil.nvl((String) session.getAttribute("customerName")); 
        String customerId = StringUtil.nvl((String) session.getAttribute("customerId"));
        String groupId = StringUtil.nvl((String) session.getAttribute("groupId"));
        
        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

 	       	//mv.setViewName("/common/customerLoginForm");
        	mv.setViewName("/common/sessionOut");
        	return mv;
		}
        
        List<AsVO> asList = null;
        
        asConVO.setCustomerKey(customerKey);
        asConVO.setGroupId(groupId);
        
        // 조회조건저장
        mv.addObject("asConVO", asConVO);

        // 페이징코드
        asConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(asConVO.getCurPage(), asConVO.getRowCount()));
        asConVO.setPage_limit_val2(StringUtil.nvl(asConVO.getRowCount(), "10"));
        
        // 사용자목록조회
        asList = asSvc.getAsList(asConVO);
        mv.addObject("asList", asList);

        // totalCount 조회
        String totalCount = String.valueOf(asSvc.getAsCnt(asConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("/business/goodsPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    /**
     * 개별 상품등록
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/business/goodsregistform")
    public ModelAndView goodsRegistForm(HttpServletRequest request, 
    		                       HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start ");

        ModelAndView mv = new ModelAndView();
        
        // 사용자 세션정보
        HttpSession session = request.getSession();
        
        String customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
        String customerName = StringUtil.nvl((String) session.getAttribute("customerName")); 
        String customerId = StringUtil.nvl((String) session.getAttribute("customerId"));
        String groupId = StringUtil.nvl((String) session.getAttribute("groupId"));
        
        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

 	       	//mv.setViewName("/common/customerLoginForm");
        	mv.setViewName("/common/sessionOut");
        	return mv;
		}

        mv.setViewName("/business/goodsRegistForm");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
}
