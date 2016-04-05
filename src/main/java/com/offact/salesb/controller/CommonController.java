package com.offact.salesb.controller;

import javax.net.ssl.HttpsURLConnection;

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
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
import java.util.Random;
import java.io.BufferedReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.security.SecureRandom;
import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.*;
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
import com.offact.framework.util.CipherDecipherUtil;
import com.offact.framework.util.StringUtil;
import com.offact.framework.util.XmlUtil;
import com.offact.salesb.service.CustomerService;
import com.offact.salesb.service.business.ProductService;
import com.offact.salesb.service.common.CommonService;
import com.offact.salesb.service.common.CommonService;
import com.offact.salesb.service.common.MailService;
import com.offact.salesb.service.common.SmsService;
import com.offact.salesb.service.common.UserService;
import com.offact.salesb.service.manage.UserManageService;
import com.offact.salesb.service.member.TokenService;
import com.offact.salesb.service.order.OrderService;
import com.offact.salesb.vo.CustomerVO;
import com.offact.salesb.vo.business.ProductMasterVO;
import com.offact.salesb.vo.common.EmailVO;
import com.offact.salesb.vo.common.GroupVO;
import com.offact.salesb.vo.common.SmsVO;
import com.offact.salesb.vo.common.UserVO;
import com.offact.salesb.vo.common.WorkVO;
import com.offact.salesb.vo.manage.UserManageVO;
import com.offact.salesb.vo.member.TokenVO;
import com.offact.salesb.vo.order.OrderVO;
import com.kakao.*;

/**
 * Handles requests for the application home page.
 */
@Controller

public class CommonController {

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
	
    @Value("#{config['offact.domain.url']}")
    private String domainUrl;
    
    @Value("#{config['offact.host.url']}")
    private String hostUrl;
    
    @Value("#{config['oauth.redirect.url']}")
    private String redirectUrl;
    
    @Value("#{config['oauth.redirect2.url']}")
    private String redirectUrl2;
    
    @Value("#{config['oauth.kakao.client_id']}")
    private String kakaoclient_id;
    
    @Value("#{config['oauth.naver.client_id']}")
    private String naverclient_id;
    
    @Value("#{config['oauth.naver.client_secret']}")
    private String naverclient_secret;
    
    @Value("#{config['oauth.facebook.fbAppId']}")
    private String facebookfbAppId;
    
    @Value("#{config['oauth.google.client_id']}")
    private String googleclient_id;
    
    @Value("#{config['oauth.pinterest.client_id']}")
    private String pinterestclient_id;
	
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
    
    @Value("#{config['offact.sms.sendno']}")
    private String sendno;
    
	@Value("#{config['offact.mail.orderfromemail']}")
    private String orderfromemail;
    
    @Value("#{config['offact.mail.ordersubject']}")
    private String ordersubject;
    
    @Autowired
    private CommonService commonSvc;
    
	@Autowired
	private CustomerService customerSvc;
	
    @Autowired
    private SmsService smsSvc;
    
	@Autowired
	private UserService userSvc;
	
	@Autowired
	private UserManageService userManageSvc;
	
    @Autowired
    private TokenService tokenSvc;

    @Autowired
    private OrderService orderSvc;

    @Autowired
    private ProductService productSvc;
    
    @Autowired
    private MailService mailSvc;
    
    public String generateState()
    {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
    
    private static final String DEFAULT_ENCODING = "UTF-8";
    
    private String url;
    private MultipartEntityBuilder params;

    
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(HttpServletRequest request,
			                   HttpServletResponse response,  
			                   Model model, 
			                   Locale locale) throws BizException 
	{

		logger.info("Welcome admin");
		
		ModelAndView  mv = new ModelAndView();

    	mv.setViewName("/admin/index");

		return mv;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/intro", method = RequestMethod.GET)
	public ModelAndView intro(HttpServletRequest request,
			                   HttpServletResponse response,  
			                   Model model, 
			                   Locale locale) throws BizException 
	{

		logger.info("Welcome intro");
		
		ModelAndView  mv = new ModelAndView();

    	mv.setViewName("/common/intro");

		return mv;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/customerloginform", method = RequestMethod.GET)
	public ModelAndView customerLoginForm(HttpServletRequest request,
			                   HttpServletResponse response,  
			                   String key,
			                   Model model, 
			                   Locale locale) throws BizException 
	{

    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : customerloginform");
		
		logger.info("customer key ::"+key);
		
		ModelAndView  mv = new ModelAndView();
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        
        String customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
        
        logger.info("customerKey:"+customerKey);
        
        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

        	//조직정보 조회
        	//GroupVO group = new GroupVO();
        	
        	//List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        	//mv.addObject("group_comboList", group_comboList);

        	String state = generateState();

        	session = request.getSession(true);
			session.setAttribute("state", state);
			session.setAttribute("key", StringUtil.nvl(key,"N"));
			
			mv.addObject("key", StringUtil.nvl(key,"N"));
			mv.addObject("state", state);
			mv.addObject("hostUrl", hostUrl);
			mv.addObject("domainUrl", domainUrl);
			mv.addObject("redirectUrl", redirectUrl);
			mv.addObject("redirectUrl2", redirectUrl2);
			mv.addObject("kakaoclient_id", kakaoclient_id);
			mv.addObject("naverclient_id", naverclient_id);
			mv.addObject("naverclient_secret", naverclient_secret);
			mv.addObject("facebookfbAppId", facebookfbAppId);
			mv.addObject("googleclient_id", googleclient_id);
			mv.addObject("pinterestclient_id", pinterestclient_id);
        	
 	       	mv.setViewName("/common/customerLoginForm");
       		return mv;
		}
        
		 if(StringUtil.nvl(key,"N").equals("N")){

	        mv.setViewName("salesb/top");

		}else{

			logger.info("["+logid+"] Controller start key="+key);

	        String keyvalue = CipherDecipherUtil.decrypt(key, "We are sales and livingsocials !");
	        
	        logger.info("["+logid+"] CipherDecipherUtil keyvalue::"+keyvalue);

	        String[] keys=null;
	        
	        keys=keyvalue.split("\\|");
	        
	        logger.info("["+logid+"] CipherDecipherUtil token::"+keys[0]);
	        logger.info("["+logid+"] CipherDecipherUtil productkey::"+keys[1]);
	        logger.info("["+logid+"] CipherDecipherUtil tokenemail::"+keys[2]);
	        logger.info("["+logid+"] CipherDecipherUtil tokenphone::"+keys[3]);
	        
	        //1) tokenkey / activeyn 조회
	        // activeyn 이 n 이면 비활성화 안내처리
	        // tokenkey 와 customerkey가 없는경우 잘못된 경로 안내
	        
	        CustomerVO custVo = new CustomerVO();
	        custVo.setSbEmail(keys[2]);
	        custVo.setSbPhoneNumber(keys[3]);
	        custVo.setSearchType("03");
	  
	        //고객키 조회
	        custVo=customerSvc.getCustKeyInfo(custVo);
	        
	        if(custVo==null){
	        	
	          //고객정보 오류 잘못된 접근 안내
	    	  mv.addObject("ordermessage", "고객정보가 일치하지 않습니다.(정상경로 여부를 확인하세요");
	          
	          mv.setViewName("/common/intro");
	          
	         //log Controller execute time end
	        	long t2 = System.currentTimeMillis();
	        	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	        	
	          return mv;
	          
	        }
	        
	        if(custVo.getUsedYn().equals("N")){
	        	
	        	  //고객정보 활성화 오류 잘못된 접근 안내
		    	  mv.addObject("ordermessage", "고객정보가 활성화 되지 않습니다.(고객 인증 여부를 확인하세요");
		          
		          mv.setViewName("/common/intro");
		          
		         //log Controller execute time end
		        	long t2 = System.currentTimeMillis();
		        	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
		        	
		          return mv;
	        	
	        }

	        TokenVO tokenVo = new TokenVO();
	        
	        tokenVo.setToken(keys[0]);
	        tokenVo.setProductCode(keys[1]);
	        tokenVo.setCustomerKey(custVo.getCustomerKey());
	        
	        tokenVo=tokenSvc.getTokenCheck(tokenVo);
			
	        if(tokenVo==null){
	        	
	        	//token 정보 오류 잘못된 접근 안내
	         	mv.addObject("ordermessage", "토큰정보가 일치하지 않습니다.(정상경로 여부를 확인하세요");
	            
	            mv.setViewName("/common/intro");
	            
	           //log Controller execute time end
	          	long t2 = System.currentTimeMillis();
	          	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	          	
	            return mv;
			}
	        
	        if(tokenVo.getActiveYn().equals("N")){
	        	
	        	//token 정보 오류 잘못된 접근 안내
	         	mv.addObject("ordermessage", "토큰정보가 활성화 되지 않았습니다.(토큰 사용여부를 확인하세요");
	            
	            mv.setViewName("/common/intro");
	            
	           //log Controller execute time end
	          	long t2 = System.currentTimeMillis();
	          	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	          	
	            return mv;
			}
	        
	        //2)productkey로 상품정보 조히 (상품명 /상품각격 등)
	        ProductMasterVO goodsVo = new ProductMasterVO();
	        
	        goodsVo.setIdx(keys[1]);
	        
	        goodsVo=productSvc.getProductDetail(goodsVo);

	        mv.addObject("token", tokenVo);
	        mv.addObject("goods", goodsVo);
	        mv.addObject("customer", custVo);
	        mv.addObject("productCode", keys[1]);
	        mv.addObject("tokenemail", keys[2]);
	        mv.addObject("tokenphone", keys[3]);
	        mv.addObject("key", key);

	        mv.setViewName("/order/custMobileSale");

		}

		return mv;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/customerregistform", method = RequestMethod.GET)
	public ModelAndView customerRegistForm(String type ,
			                   HttpServletRequest request,
			                   HttpServletResponse response,  
			                   String key,
			                   Model model, 
			                   Locale locale) throws BizException 
	{
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : customerloginform");
		
		logger.info("customer key ::"+key);

		ModelAndView  mv = new ModelAndView();
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        

        session = request.getSession(true);
		session.setAttribute("key", StringUtil.nvl(key,"N"));

		mv.addObject("key", StringUtil.nvl(key,"N"));
		mv.addObject("type", type);
		
    	mv.setViewName("/common/customerRegistForm");

		return mv;
	}
	
	 /**
     * 고객 등록
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/common/customerregist", method = RequestMethod.POST)
    public @ResponseBody
    String  customerRegist(@ModelAttribute("customerVo") CustomerVO customerVo, 
									   HttpServletRequest request) throws BizException 
    {
		
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : customerVO" + customerVo);
    			
		int retVal=-1;
		String token="";

		//등록여부 확인
		customerVo.setSearchType("01");
		CustomerVO customerChk = customerSvc.getCustKeyInfo(customerVo);
				
		if(customerChk != null) {
			
			token=customerChk.getToken();
			customerVo.setCustomerKey(customerChk.getCustomerKey());
			
			if(!token.equals(customerVo.getToken())){ //인증실패-인증번호 다름
				
				//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	            
		       	return "3";
				
			}
			
			
		}else{//인증실패-등록사용자 정보없음
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
            
	       	return "2";
			
		}

		String mc="";
		String keyvalue="";
	    String encryptValue = "";
        String encrypt = "";
        
        encryptValue=token+"|"+customerVo.getSbPhoneNumber()+"|"+customerVo.getSbEmail()+"|"+customerVo.getSbPw();
	        
	    encrypt = CipherDecipherUtil.encrypt(encryptValue, "We are sales and livingsocials !");
	        
	    logger.info("["+logid+"] CipherDecipherUtil encrypt::"+encrypt);

		//이메일 리스틑 조회 user
		String emaillist=customerVo.getSbEmail();
		String cclist="";
		
		String [] getToMails=emaillist.split(";");
    	String [] getToMail_Ccs=cclist.split(";");
		
		//email 전송
		EmailVO mail = new EmailVO();
		
		List<String> toEmails= new ArrayList();
		List<String> toEmail_Ccs= new ArrayList();
		List<String> attcheFileName= new ArrayList();
		List<File> files = new ArrayList();

		for(int m=0;m<getToMails.length;m++){	
			toEmails.add(getToMails[m]);	
		}
		
		for(int c=0;c<getToMail_Ccs.length;c++){	
			toEmail_Ccs.add(getToMail_Ccs[c]);	
		}

		//attcheFileName.add(orderCode+".html");
		//files.add(file);
		//메일발송
		mail.setToEmails(toEmails);
		mail.setToEmail_Ccs(toEmail_Ccs);
		mail.setAttcheFileName(attcheFileName);
		mail.setFile(files);

		mail.setFromEmail(orderfromemail);
		
		String szContent = "";
		
		szContent += "<html xmlns='http://www.w3.org/1999/xhtml'>";
	    szContent += "<head>";
        szContent += "<meta http-equiv='Content-Type' content='text/html; charset=euc-kr' />";
        szContent += "<title>[sales baron]</title>";
        szContent += "<link href='"+hostUrl+"/salesb/css/issue_style.css' rel='stylesheet' type='text/css' />";
        szContent += "<style type='text/css'>";
        szContent += "<!--";
        szContent += "body {";
        szContent += "background-color: #ffffff;";
        szContent += "}";
        szContent += "-->";
        szContent += "</style></head>";
		
        szContent += "<body>";
        szContent += "<table width='630' cellspacing='0' cellpadding='0' align='center' >";
        szContent += "<tr>";
        szContent += "<td><img src='"+hostUrl+"/salesb/images/error/error_boxtop.gif' width='630' height='32' /></td>";
        szContent += "</tr>";
        szContent += "<tr>";
        szContent += "<td align='center' background='"+hostUrl+"/salesb/images/error/error_boxcen.gif'><table width='500' cellspacing='0' cellpadding='0'>";
        szContent += "<tr>";
        szContent += "<td height='30' class='tit_black_b'>[sales baron] 고객 등록 인증메일</td>";
        szContent += "</tr>";
        szContent += "<tr>";
        szContent += "<td height='1' bgcolor='a9a9a9'></td>";
        szContent += "</tr>";
        szContent += "</table>";
        szContent += "<table width='500' cellspacing='0' cellpadding='0' style='margin-top:18px'>";
        szContent += "<tr>";
        szContent += "<td valign='top' style='padding:10px 0 0 0'>고객번호("+customerVo.getSbPhoneNumber()+")에 대한 salseb 가입내용을 확인 부탁드립니다.<br><br>가입 내용이 맞을경우 아래 인증하기를 클릭하여 고객정보를 활성화 하시기 바랍니다.<br><br><a href='"+hostUrl+"/salesb/common/mailconfirm?mc="+encrypt+"'><font color='bule'>[ 인증하기 ]</font></a><br><br>salesb 바로가기 =><a href='"+domainUrl+"'>http://salesb.net</a><br></td>";
        szContent += "</tr>";
        szContent += "</table></td>";
        szContent += "</tr>";
        szContent += "<tr>";
        szContent += "<td><img src='"+hostUrl+"/salesb/images/error/error_boxbot.gif' width='630' height='32' /></td>";
        szContent += "</tr>";
        szContent += "<tr>";
        szContent += "<td height='300'>&nbsp;</td>";
        szContent += "</tr>";
        szContent += "</table>";
        szContent += "</body>";
        szContent += "</html>";
        
		mail.setMsg(szContent);
		
		mail.setSubject("[sales baron] 고객 등록 인증메일");
		
		boolean counselResult=false;

		try{
			
			counselResult=mailSvc.sendMail(mail);
			
			logger.debug("mail result :"+counselResult);
			
			if(counselResult==false){
				
				//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

		        return "-1";
				
			}
			
			retVal=1;
			
		}catch(BizException e){
	       	
	    	e.printStackTrace();
	        String errMsg = e.getMessage();
	        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds [errorMsg] : "+errMsg);

	       	return "-1";
	    	
	    }

		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return ""+retVal;
	}
    
	 /**
     * 고객 등록
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/common/mailconfirm"})
    public @ResponseBody
    ModelAndView mailConfirm(String mc,
            HttpServletRequest request, 
            HttpServletResponse response) throws BizException
    {
		
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : mc" + mc);
		
		ModelAndView  mv = new ModelAndView();
    			
		int retVal=-1;
		String token="";
		String email="";
		
		String key_token="";
		String key_phone="";
		String key_email="";
		String key_password="";
		
		String keyvalue = CipherDecipherUtil.decrypt(mc, "We are sales and livingsocials !");
	        
        logger.info("["+logid+"] CipherDecipherUtil keyvalue::"+keyvalue);

        String[] keys=null;
        
        keys=keyvalue.split("\\|");
        
        logger.info("["+logid+"] CipherDecipherUtil token::"+keys[0]);
        logger.info("["+logid+"] CipherDecipherUtil phone::"+keys[1]);
        logger.info("["+logid+"] CipherDecipherUtil email::"+keys[2]);
        logger.info("["+logid+"] CipherDecipherUtil pw::"+keys[3]);
	    
        key_token=keys[0];
        key_phone=keys[1];
        key_email=keys[2];
        key_password=keys[3];
        
        CustomerVO customerVo = new CustomerVO();

		//등록여부 확인
		customerVo.setSearchType("01");
		customerVo.setSbPhoneNumber(key_phone);
		CustomerVO customerChk = customerSvc.getCustKeyInfo(customerVo);
				
		if(customerChk != null) {
			
			token=customerChk.getToken();
		    email=customerChk.getSbEmail();
			customerVo.setCustomerKey(customerChk.getCustomerKey());
			customerVo.setSbPw(key_password);
			
			if(!token.equals(key_token)){ //인증실패-인증번호 다름
				
				//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	            
		        //고객인증 오류 잘못된 접근 안내
	    	    mv.addObject("message", "인증실패-인증번호 다름");
	            mv.setViewName("/common/mailConfirm");
	        	return mv;
				
			}
			
			if(!email.equals(key_email)){ //인증실패-이메일 다름
				
				//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	            
		        //고객인증 오류 잘못된 접근 안내
	    	    mv.addObject("message", "인증실패-이메일 다름");
	    	    mv.setViewName("/common/mailConfirm");
	        	return mv;
				
			}
			
			
		}else{//인증실패-등록사용자 정보없음
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
            
	        //고객인증 오류 잘못된 접근 안내
    	    mv.addObject("message", "인증실패-등록사용자 정보없음");
    	    mv.setViewName("/common/mailConfirm");
        	return mv;
			
		}

		retVal = customerSvc.customerRegistUpdate(customerVo);	
		
		if(retVal<=0){
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	       	
			//고객인증 오류 잘못된 접근 안내
    	    mv.addObject("message", "인증업데이트 오류");
    	    mv.setViewName("/common/mailConfirm");
        	return mv;
		}

		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

        //고객인증성공안내
	    mv.addObject("message", "인증이 성공했습니다.salesb를 정상적으로 사용가능하십니다.");
	    mv.setViewName("/common/mailConfirm");
    	return mv;
	}
    
    /**
     * 고객 등록
     *
     * @param 
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/common/gettokenconfirm", method = RequestMethod.POST)
    public @ResponseBody
    String  getTokenConfirm(@ModelAttribute("customerVo") CustomerVO customerVo, 
									   HttpServletRequest request) throws BizException 
    {
		
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : customerVO" + customerVo);
    			
		int retVal=-1;
		String token="";

        //등록여부 확인
		customerVo.setSearchType("01");
		CustomerVO customerChk = customerSvc.getCustKeyInfo(customerVo);
	
		//CustomerVO customerChk = customerSvc.getTokenInfo(customerVo);	
				
		if(customerChk != null) {
			
			token=customerChk.getToken();
			
			if(!token.equals(customerVo.getToken())){ //인증실패-인증번호 다름
				
				//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	            
		       	return "3";
				
			}
			
			
		}else{//인증실패-등록사용자 정보없음
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
            
	       	return "2";
			
		}

		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return "1";
	}
    /**
     * 임시 비밀번호 발급
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/common/gettoken", method = RequestMethod.POST)
    public @ResponseBody
    String  getToken(@ModelAttribute("customerVo") CustomerVO customerVo, 
									   HttpServletRequest request) throws BizException 
    {
		
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : customerVO" + customerVo);
    			
		int retVal=-1;
		String customerKey="";
		String useYn="";
		
		String token="";
		token=tokenCreate();
		
		if(token.length()==3){
			token=token+"0";
		}
        //등록여부 확인
		customerVo.setSearchType("01");
		CustomerVO customerChk = customerSvc.getCustKeyInfo(customerVo);
		
		if(customerChk != null) {
			
			useYn=customerChk.getUsedYn();
			
			if(useYn.equals("Y")){
				//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	            
		       	return "1";
			}else{
				
				customerVo.setCustomerKey(customerChk.getCustomerKey());
				customerVo.setToken(token);

				retVal = customerSvc.tokenUpdate(customerVo);	
				logger.debug("#########getTokenUpdate retVal"+retVal);
				
			}
			
		}else{
			
			customerVo.setToken(token);

			retVal = customerSvc.tokenInsert(customerVo);	
			logger.debug("#########getTokenInsert retVal"+retVal);
			
		}
	
			try{
				//SMS발송
				SmsVO smsVO = new SmsVO();
				SmsVO resultSmsVO = new SmsVO();
				
				//즉시전송 세팅
				smsVO.setSmsDirectYn("Y");
				
				smsVO.setSmsId(smsId);
				smsVO.setSmsPw(smsPw);
				smsVO.setSmsType(smsType);
				smsVO.setSmsTo(customerVo.getSbPhoneNumber());
				smsVO.setSmsFrom(sendno);
				smsVO.setSmsMsg("["+token+"]salesb 에서 발송된 인증번호입니다");

				logger.debug("#########devOption :"+devOption);
				String[] devSmss= devSms.split("\\^");
				
	    		if(devOption.equals("true")){
					for(int i=0;i<devSmss.length;i++){
						
						if(devSmss[i].equals(customerVo.getCustomerKey().trim().replace("-", ""))){
							resultSmsVO=smsSvc.sendSms(smsVO);
						}
					}
				}else{
					resultSmsVO=smsSvc.sendSms(smsVO);
				}
	
				logger.debug("sms resultSmsVO.getResultCode() :"+resultSmsVO.getResultCode());
				logger.debug("sms resultSmsVO.getResultMessage() :"+resultSmsVO.getResultMessage());
				logger.debug("sms resultSmsVO.getResultLastPoint() :"+resultSmsVO.getResultLastPoint());
				
			}catch(BizException e){
				
				logger.info("["+logid+"] Controller SMS전송오류");
				//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	            
		       	return "-1";
				
			}
			
		
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return "0";
	}
    /**
     * 임시 비밀번호 발급
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/common/getpwtoken", method = RequestMethod.POST)
    public @ResponseBody
    String  getPwToken(@ModelAttribute("customerVo") CustomerVO customerVo, 
									   HttpServletRequest request) throws BizException 
    {
		
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : customerVO" + customerVo);
    			
		int retVal=-1;
		
        //등록여부 확인
		customerVo.setSearchType("01");
		CustomerVO customerChk = customerSvc.getCustKeyInfo(customerVo);
		
		if(customerChk == null) {
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
            
	       	return "1";
			
		}
		
		String token="";
		token=tokenCreate();
		
		customerVo.setToken(token);
		customerVo.setCustomerKey(customerChk.getCustomerKey());

		retVal = customerSvc.tokenUpdate(customerVo);	
		logger.debug("#########getToken retVal"+retVal);
		
			try{
				//SMS발송
				SmsVO smsVO = new SmsVO();
				SmsVO resultSmsVO = new SmsVO();
				
				//즉시전송 세팅
				smsVO.setSmsDirectYn("Y");
				
				smsVO.setSmsId(smsId);
				smsVO.setSmsPw(smsPw);
				smsVO.setSmsType(smsType);
				smsVO.setSmsTo(customerVo.getSbPhoneNumber());
				smsVO.setSmsFrom(sendno);
				smsVO.setSmsMsg("["+token+"] salesb 에서 발송된 인증번호입니다");

				logger.debug("#########devOption :"+devOption);
				String[] devSmss= devSms.split("\\^");
				
	    		if(devOption.equals("true")){
					for(int i=0;i<devSmss.length;i++){
						
						if(devSmss[i].equals(customerVo.getCustomerKey().trim().replace("-", ""))){
							resultSmsVO=smsSvc.sendSms(smsVO);
						}
					}
				}else{
					resultSmsVO=smsSvc.sendSms(smsVO);
				}
	
				logger.debug("sms resultSmsVO.getResultCode() :"+resultSmsVO.getResultCode());
				logger.debug("sms resultSmsVO.getResultMessage() :"+resultSmsVO.getResultMessage());
				logger.debug("sms resultSmsVO.getResultLastPoint() :"+resultSmsVO.getResultLastPoint());
				
			}catch(BizException e){
				
				logger.info("["+logid+"] Controller SMS전송오류");
				//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	            
		       	return "-1";
				
			}
			
		
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return "0";
	}
	 /**
     * 임시 비밀번호 발급
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/common/temppassword", method = RequestMethod.POST)
    public @ResponseBody
    String  tempPassword(@ModelAttribute("customerVo") CustomerVO customerVo, 
									   HttpServletRequest request) throws BizException 
    {
		
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : customerVO" + customerVo);
    			
		int retVal=-1;
		String token="";
		token=tokenCreate();
		String temppassword="";
		temppassword=tokenCreate();

		//등록여부 확인
		customerVo.setSearchType("01");
		CustomerVO customerChk = customerSvc.getCustKeyInfo(customerVo);
				
		if(customerChk != null) {
			
			token=customerChk.getToken();
			
			if(!token.equals(customerVo.getToken())){ //인증실패-인증번호 다름
				
				//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	            
		       	return "3";
				
			}
		
		}else{//사용자 정보없음
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
            
	       	return "2";

		}
		
		customerVo.setPw_modifyYn("Y");
		customerVo.setCustomerPw(temppassword);
		customerVo.setCustomerKey(customerChk.getCustomerKey());

		retVal = customerSvc.customerUpdateProc(customerVo);
		
		if(retVal>0){
			retVal=1;
		}
		
		logger.debug("#########customerUpdateProc retVal"+retVal);
		
			try{
				//SMS발송
				SmsVO smsVO = new SmsVO();
				SmsVO resultSmsVO = new SmsVO();
				
				//즉시전송 세팅
				smsVO.setSmsDirectYn("Y");
				
				smsVO.setSmsId(smsId);
				smsVO.setSmsPw(smsPw);
				smsVO.setSmsType(smsType);
				smsVO.setSmsTo(customerVo.getSbPhoneNumber());
				smsVO.setSmsFrom(sendno);
				smsVO.setSmsMsg("애디스에서 발송된 임시 비밀번호 입니다 ["+temppassword+"]");

				logger.debug("#########devOption :"+devOption);
				String[] devSmss= devSms.split("\\^");
				
	    		if(devOption.equals("true")){
					for(int i=0;i<devSmss.length;i++){
						
						if(devSmss[i].equals(customerVo.getCustomerKey().trim().replace("-", ""))){
							resultSmsVO=smsSvc.sendSms(smsVO);
						}
					}
				}else{
					resultSmsVO=smsSvc.sendSms(smsVO);
				}
	
				logger.debug("sms resultSmsVO.getResultCode() :"+resultSmsVO.getResultCode());
				logger.debug("sms resultSmsVO.getResultMessage() :"+resultSmsVO.getResultMessage());
				logger.debug("sms resultSmsVO.getResultLastPoint() :"+resultSmsVO.getResultLastPoint());
				
			}catch(BizException e){
				
				logger.info("["+logid+"] Controller SMS전송오류");
				//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	            
		       	return "-1";
			}
			
		
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return ""+retVal;
	}
    /**
     * 고객 패스워드 변경
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/common/customerpwmodify", method = RequestMethod.POST)
    public @ResponseBody
    String  customerPwModify(@ModelAttribute("customerVo") CustomerVO customerVo, 
									   HttpServletRequest request) throws BizException 
    {
		
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : customerVO" + customerVo);
    			
		int retVal=-1;
		String token="";
		
		//등록여부 확인
		customerVo.setSearchType("01");
		CustomerVO customerChk = customerSvc.getCustKeyInfo(customerVo);
				
		if(customerChk != null) {
			
			token=customerChk.getToken();
			
			if(!token.equals(customerVo.getToken())){ //인증실패-인증번호 다름
				
				//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	            
		       	return "3";
				
			}
			
			
		}else{//인증실패-등록사용자 정보없음
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
            
	       	return "2";
			
		}

		customerVo.setPw_modifyYn("Y");
		customerVo.setCustomerKey(customerChk.getCustomerKey());

		retVal=this.customerSvc.customerUpdateProc(customerVo);
		
		if(retVal>0){
			retVal=1;
		}
		
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return ""+retVal;
	}
    
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/customerpwform", method = RequestMethod.GET)
	public ModelAndView customerPwForm(String type ,
			                   HttpServletRequest request,
			                   HttpServletResponse response,  
			                   Model model, 
			                   Locale locale) throws BizException 
	{

		logger.info("Welcome customer");
		
		ModelAndView  mv = new ModelAndView();
		
		mv.addObject("type", type);
		
    	mv.setViewName("/common/customerPwForm");

		return mv;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/surveyloginform", method = RequestMethod.GET)
	public ModelAndView surveyLoginForm(HttpServletRequest request,
			                   HttpServletResponse response,  
			                   Model model, 
			                   Locale locale) throws BizException 
	{

		logger.info("Welcome customer");
		
		ModelAndView  mv = new ModelAndView();
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        
        String customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
        
        logger.info("customerKey:"+customerKey);
        
        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

        	//조직정보 조회
        	GroupVO group = new GroupVO();
        	
        	List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        	mv.addObject("group_comboList", group_comboList);
        	
 	       	mv.setViewName("/common/surveyLoginForm");
       		return mv;
		}

		mv.setViewName("survey/surveyManage");

		return mv;
	}
	
	/**
	 * Login 처리
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "/customer/login", method = RequestMethod.POST)
	public ModelAndView salesblogin(String loginType ,
			                       HttpServletRequest request,
			                       HttpServletResponse response) throws Exception
	{
		
		//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start loginType::"+loginType);
		
		ModelAndView  mv = new ModelAndView();
		
		String memberType = StringUtil.nvl(request.getParameter("memberType"));
		String sbPhoneNumber = StringUtil.nvl(request.getParameter("sbPhoneNumber"));
		String sbPw = StringUtil.nvl(request.getParameter("sbPw"));
		String groupId = StringUtil.nvl(request.getParameter("groupId"),"SM001");
		String groupName = StringUtil.nvl(request.getParameter("groupName"),"스마트매장");
		
		String id = StringUtil.nvl(request.getParameter("id"),sbPhoneNumber);
		String name = StringUtil.nvl(request.getParameter("name"),"");
		String restfulltype = StringUtil.nvl(request.getParameter("restfulltype"),"일반");
		String photo = StringUtil.nvl(request.getParameter("photo"),"");
		
		logger.info(">>>> sbPhoneNumber :"+sbPhoneNumber);
		logger.info(">>>> sbPw :"+sbPw);

		String strMainUrl = "";
		
		// # 2. 넘겨받은 아이디로 데이터베이스를 조회하여 사용자가 있는지를 체크한다.
		CustomerVO customerVo = new CustomerVO();
		customerVo.setSbPhoneNumber(sbPhoneNumber);
		customerVo.setInCustomerPw(sbPw);
		
		CustomerVO customerChk = customerSvc.getCustomer(customerVo);		

		String customerKey="";
		String lastLat = "";
		String lastlon = "";
	
		if(customerChk != null)
		{

			//패스워드 체크
			if(!customerChk.getCustomerPw().equals(customerChk.getInCustomerPw())){
				
				logger.info(">>> 비밀번호 오류");
				strMainUrl = "common/loginFail";
				
				mv.addObject("memberType", memberType);
				mv.addObject("loginType", loginType);
				mv.addObject("sbPhoneNumber", sbPhoneNumber);
				
				mv.setViewName(strMainUrl);
				
				//log Controller execute time end
		      	long t2 = System.currentTimeMillis();
		      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
		      	
				return mv;
				
			}

			customerKey = customerChk.getCustomerKey();
			sbPhoneNumber = customerChk.getSbPhoneNumber();
			lastLat = customerChk.getLastLat();
			lastlon=customerChk.getLastlon();

			// # 3. Session 객체에 셋팅
			// 사용자 세션정보
	        HttpSession session = request.getSession();
	        
	        String key = StringUtil.nvl((String) session.getAttribute("key")); 

			session = request.getSession(false);
			
			if(session != null)
			{
				session.invalidate();
			}
				
				session = request.getSession(true);
				
				session.setAttribute("memberType", memberType);
				session.setAttribute("customerKey", customerKey);
				session.setAttribute("sbPhoneNumber", sbPhoneNumber);
				session.setAttribute("lastLat", lastLat);
				session.setAttribute("lastlon", lastlon);
				
				session.setAttribute("id", id);
				session.setAttribute("name", name);
				session.setAttribute("restfulltype", restfulltype);
				session.setAttribute("photo", photo);

				 if(StringUtil.nvl(key,"N").equals("N")){
					
					if("01".equals(memberType)){
						strMainUrl = "salesb/topBusiness";
					}else{
						
						//token list 조회
						strMainUrl = "salesb/top";
					}

				}else{

					logger.info("["+logid+"] Controller start key="+key);

			        String keyvalue = CipherDecipherUtil.decrypt(key, "We are sales and livingsocials !");
			        
			        logger.info("["+logid+"] CipherDecipherUtil keyvalue::"+keyvalue);

			        String[] keys=null;
			        
			        keys=keyvalue.split("\\|");
			        
			        logger.info("["+logid+"] CipherDecipherUtil token::"+keys[0]);
			        logger.info("["+logid+"] CipherDecipherUtil productkey::"+keys[1]);
			        logger.info("["+logid+"] CipherDecipherUtil tokenemail::"+keys[2]);
			        logger.info("["+logid+"] CipherDecipherUtil tokenphone::"+keys[3]);
			        
			        //1) tokenkey / activeyn 조회
			        // activeyn 이 n 이면 비활성화 안내처리
			        // tokenkey 와 customerkey가 없는경우 잘못된 경로 안내
			        
			        CustomerVO custVo = new CustomerVO();
			        custVo.setSbEmail(keys[2]);
			        custVo.setSbPhoneNumber(keys[3]);
			        custVo.setSearchType("03");
			  
			        //고객키 조회
			        custVo=customerSvc.getCustKeyInfo(custVo);
			        
			        if(custVo==null){
			        	
			          //고객정보 오류 잘못된 접근 안내
			    	  mv.addObject("ordermessage", "고객정보가 일치하지 않습니다.(정상경로 여부를 확인하세요");
			          
			          mv.setViewName("/common/intro");
			          
			         //log Controller execute time end
			        	long t2 = System.currentTimeMillis();
			        	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
			        	
			          return mv;
			          
			        }
			        
			        if(custVo.getUsedYn().equals("N")){
			        	
			        	  //고객정보 활성화 오류 잘못된 접근 안내
				    	  mv.addObject("ordermessage", "고객정보가 활성화 되지 않습니다.(고객 인증 여부를 확인하세요");
				          
				          mv.setViewName("/common/intro");
				          
				         //log Controller execute time end
				        	long t2 = System.currentTimeMillis();
				        	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
				        	
				          return mv;
			        	
			        }

			        TokenVO tokenVo = new TokenVO();
			        
			        tokenVo.setToken(keys[0]);
			        tokenVo.setProductCode(keys[1]);
			        tokenVo.setCustomerKey(custVo.getCustomerKey());
			        
			        tokenVo=tokenSvc.getTokenCheck(tokenVo);
					
			        if(tokenVo==null){
			        	
			        	//token 정보 오류 잘못된 접근 안내
			         	mv.addObject("ordermessage", "토큰정보가 일치하지 않습니다.(정상경로 여부를 확인하세요");
			            
			            mv.setViewName("/common/intro");
			            
			           //log Controller execute time end
			          	long t2 = System.currentTimeMillis();
			          	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
			          	
			            return mv;
					}
			        
			        if(tokenVo.getActiveYn().equals("N")){
			        	
			        	//token 정보 오류 잘못된 접근 안내
			         	mv.addObject("ordermessage", "토큰정보가 활성화 되지 않았습니다.(토큰 사용여부를 확인하세요");
			            
			            mv.setViewName("/common/intro");
			            
			           //log Controller execute time end
			          	long t2 = System.currentTimeMillis();
			          	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
			          	
			            return mv;
					}
			        
			        //2)productkey로 상품정보 조히 (상품명 /상품각격 등)
			        ProductMasterVO goodsVo = new ProductMasterVO();
			        
			        goodsVo.setIdx(keys[1]);
			        
			        goodsVo=productSvc.getProductDetail(goodsVo);
	
			        mv.addObject("token", tokenVo);
			        mv.addObject("goods", goodsVo);
			        mv.addObject("customer", custVo);
			        mv.addObject("productCode", keys[1]);
			        mv.addObject("tokenemail", keys[2]);
			        mv.addObject("tokenphone", keys[3]);
			        mv.addObject("key", key);

			        strMainUrl = "order/custMobileSale";

				}

				
			} else {//고객 정보가 없는경우
				
				logger.info(">>> 고객 정보 없음");
				strMainUrl = "common/loginFail";

			}

			mv.setViewName(strMainUrl);
			
			//log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
			return mv;
		}
	/**
	 * Login 처리
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("null")
    @RequestMapping({"/customer/snslogin"})
	public ModelAndView snslogin( HttpServletRequest request,
			                      HttpServletResponse response) throws Exception
	{
		
		//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start snslogin::");
		
		ModelAndView  mv = new ModelAndView();

		String loginType = StringUtil.nvl(request.getParameter("loginType"));
		String memberType = StringUtil.nvl(request.getParameter("memberType"));
		String sbPhoneNumber = StringUtil.nvl(request.getParameter("sbPhoneNumber"));
		String sbPw = StringUtil.nvl(request.getParameter("sbPw"));
		String groupId = StringUtil.nvl(request.getParameter("groupId"),"SM001");
		String groupName = StringUtil.nvl(request.getParameter("groupName"),"스마트매장");
		
		String id = StringUtil.nvl(request.getParameter("id"),sbPhoneNumber);
		String name = StringUtil.nvl(request.getParameter("name"),"");
		String restfulltype = StringUtil.nvl(request.getParameter("restfulltype"),"일반");
		String photo = StringUtil.nvl(request.getParameter("photo"),"");
		String access_token = StringUtil.nvl(request.getParameter("access_token"),"");
		
		logger.info(">>>> sbPhoneNumber :"+sbPhoneNumber);
		logger.info(">>>> sbPw :"+sbPw);
		logger.info(">>>> id :"+id);
		logger.info(">>>> name :"+name);
		logger.info(">>>> restfulltype :"+restfulltype);
		logger.info(">>>> photo :"+photo);
		logger.info(">>>> access_token :"+access_token);
	
		String strMainUrl = "";
		
		CustomerVO customerVo = new CustomerVO();
		customerVo.setSbPhoneNumber(sbPhoneNumber);
		customerVo.setInCustomerPw(sbPw);
		
		CustomerVO customerChk = customerSvc.getCustomer(customerVo);		

		String customerKey="";
		String lastLat = "";
		String lastlon = "";
	
		if(customerChk != null)
		{
			customerKey = customerChk.getCustomerKey();
			sbPhoneNumber = customerChk.getSbPhoneNumber();
			lastLat = customerChk.getLastLat();
			lastlon=customerChk.getLastlon();

			// # 3. Session 객체에 셋팅
			// 사용자 세션정보
	        HttpSession session = request.getSession();
	        
	        String key = StringUtil.nvl((String) session.getAttribute("key")); 

			session = request.getSession(false);
			
			if(session != null)
			{
				session.invalidate();
			}
				
				session = request.getSession(true);
				
				session.setAttribute("memberType", memberType);
				session.setAttribute("customerKey", customerKey);
				session.setAttribute("sbPhoneNumber", sbPhoneNumber);
				session.setAttribute("lastLat", lastLat);
				session.setAttribute("lastlon", lastlon);
				
				session.setAttribute("id", id);
				session.setAttribute("name", name);
				session.setAttribute("restfulltype", restfulltype);
				session.setAttribute("photo", photo);
				session.setAttribute("access_token", access_token);
				
				if(restfulltype.equals("kakao")){
					session.setAttribute("access_token_kakao", access_token);
				}else if(restfulltype.equals("facebook")){
					session.setAttribute("access_token_facebook", access_token);
				}if(restfulltype.equals("naver")){
					session.setAttribute("access_token_naver", access_token);
				}
				
				 if(StringUtil.nvl(key,"N").equals("N")){
					
					//token list 조회
					strMainUrl = "salesb/top";

				}else{

					logger.info("["+logid+"] Controller start key="+key);

			        String keyvalue = CipherDecipherUtil.decrypt(key, "We are sales and livingsocials !");
			        
			        logger.info("["+logid+"] CipherDecipherUtil keyvalue::"+keyvalue);

			        String[] keys=null;
			        
			        keys=keyvalue.split("\\|");
			        
			        logger.info("["+logid+"] CipherDecipherUtil token::"+keys[0]);
			        logger.info("["+logid+"] CipherDecipherUtil productkey::"+keys[1]);
			        logger.info("["+logid+"] CipherDecipherUtil tokenemail::"+keys[2]);
			        logger.info("["+logid+"] CipherDecipherUtil tokenphone::"+keys[3]);
			        
			        //1) tokenkey / activeyn 조회
			        // activeyn 이 n 이면 비활성화 안내처리
			        // tokenkey 와 customerkey가 없는경우 잘못된 경로 안내
			        
			        CustomerVO custVo = new CustomerVO();
			        custVo.setSbEmail(keys[2]);
			        custVo.setSbPhoneNumber(keys[3]);
			        custVo.setSearchType("03");
			  
			        //고객키 조회
			        custVo=customerSvc.getCustKeyInfo(custVo);
			        
			        if(custVo==null){
			        	
			          //고객정보 오류 잘못된 접근 안내
			    	  mv.addObject("ordermessage", "고객정보가 일치하지 않습니다.(정상경로 여부를 확인하세요");
			          
			          mv.setViewName("/common/intro");
			          
			         //log Controller execute time end
			        	long t2 = System.currentTimeMillis();
			        	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
			        	
			          return mv;
			          
			        }
			        
			        if(custVo.getUsedYn().equals("N")){
			        	
			        	  //고객정보 활성화 오류 잘못된 접근 안내
				    	  mv.addObject("ordermessage", "고객정보가 활성화 되지 않습니다.(고객 인증 여부를 확인하세요");
				          
				          mv.setViewName("/common/intro");
				          
				         //log Controller execute time end
				        	long t2 = System.currentTimeMillis();
				        	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
				        	
				          return mv;
			        	
			        }

			        TokenVO tokenVo = new TokenVO();
			        
			        tokenVo.setToken(keys[0]);
			        tokenVo.setProductCode(keys[1]);
			        tokenVo.setCustomerKey(custVo.getCustomerKey());
			        
			        tokenVo=tokenSvc.getTokenCheck(tokenVo);
					
			        if(tokenVo==null){
			        	
			        	//token 정보 오류 잘못된 접근 안내
			         	mv.addObject("ordermessage", "토큰정보가 일치하지 않습니다.(정상경로 여부를 확인하세요");
			            
			            mv.setViewName("/common/intro");
			            
			           //log Controller execute time end
			          	long t2 = System.currentTimeMillis();
			          	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
			          	
			            return mv;
					}
			        
			        if(tokenVo.getActiveYn().equals("N")){
			        	
			        	//token 정보 오류 잘못된 접근 안내
			         	mv.addObject("ordermessage", "토큰정보가 활성화 되지 않았습니다.(토큰 사용여부를 확인하세요");
			            
			            mv.setViewName("/common/intro");
			            
			           //log Controller execute time end
			          	long t2 = System.currentTimeMillis();
			          	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
			          	
			            return mv;
					}
			        
			        //2)productkey로 상품정보 조히 (상품명 /상품각격 등)
			        ProductMasterVO goodsVo = new ProductMasterVO();
			        
			        goodsVo.setIdx(keys[1]);
			        
			        goodsVo=productSvc.getProductDetail(goodsVo);

			        mv.addObject("token", tokenVo);
			        mv.addObject("goods", goodsVo);
			        mv.addObject("customer", custVo);
			        mv.addObject("productCode", keys[1]);
			        mv.addObject("tokenemail", keys[2]);
			        mv.addObject("tokenphone", keys[3]);
			        mv.addObject("key", key);

			        strMainUrl = "order/custMobileSale";

				}
				
			} else {//고객 정보가 없는경우
				
				logger.info(">>> 고객 정보 없음");
				strMainUrl = "common/loginFail";

			}

			mv.setViewName(strMainUrl);
			
			//log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
			return mv;
		}
		/**
		 * Login 처리
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception 
		 */
		@SuppressWarnings("null")
		@RequestMapping(value = "/facebooklogin", method = RequestMethod.GET)
		public ModelAndView facebookLogin(HttpServletRequest request,
				                          HttpServletResponse response) throws Exception
		{
			
			//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start facebookLogin::"+request.getQueryString());
			
			ModelAndView  mv = new ModelAndView();
			
			mv.addObject("hostUrl", hostUrl);
			mv.addObject("domainUrl", domainUrl);
			mv.addObject("redirectUrl", redirectUrl);
			
			mv.addObject("facebookfbAppId", facebookfbAppId);
			
			mv.setViewName("common/oauth2facebook");
			
			//log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
			return mv;
		}
		
		/**
		 * Login 처리
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception 
		 */
		@SuppressWarnings("null")
		@RequestMapping(value = "/googlelogin", method = RequestMethod.GET)
		public ModelAndView googleLogin(HttpServletRequest request,
				                          HttpServletResponse response) throws Exception
		{
			
			//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start googleLogin::"+request.getQueryString());
			
			ModelAndView  mv = new ModelAndView();
		
			mv.addObject("hostUrl", hostUrl);
			mv.addObject("domainUrl", domainUrl);
			mv.addObject("redirectUrl", redirectUrl);
			
			mv.addObject("googleclient_id", googleclient_id);
			
			mv.setViewName("common/oauth2google");
			
			//log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
			return mv;
		}
		
		/**
		 * Login 처리
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception 
		 */
		@SuppressWarnings("null")
		@RequestMapping(value = "/pinlogin", method = RequestMethod.GET)
		public ModelAndView pinLogin(String totalurl,
				                     HttpServletRequest request,
				                     HttpServletResponse response) throws Exception
		{
			
			//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start totalurl::"+totalurl);
			
			ModelAndView  mv = new ModelAndView();
		
			mv.addObject("totalurl", totalurl);
			mv.addObject("hostUrl", hostUrl);
			mv.addObject("domainUrl", domainUrl);
			mv.addObject("redirectUrl", redirectUrl);
			mv.addObject("redirectUrl2", redirectUrl2);
			
			mv.addObject("pinterestclient_id", pinterestclient_id);
			
			mv.setViewName("common/oauth2pinterest");
			
			//log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
			return mv;
		}
		
		/**
		 * Login 처리
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception 
		 */
		@SuppressWarnings("null")
		@RequestMapping(value = "/kakaologin", method = RequestMethod.GET)
		public ModelAndView kakaoLogin(HttpServletRequest request,
				                          HttpServletResponse response) throws Exception
		{
			
			//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start kakaoLogin::"+request.getQueryString());
			
			ModelAndView  mv = new ModelAndView();
			
			mv.addObject("hostUrl", hostUrl);
			mv.addObject("domainUrl", domainUrl);
			mv.addObject("redirectUrl", redirectUrl);
			
			mv.addObject("kakaoclient_id", kakaoclient_id);
			
			mv.setViewName("common/oauth2kakao");
			
			//log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
			return mv;
		}
		
		/**
		 * Login 처리
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception 
		 */
		@SuppressWarnings("null")
		@RequestMapping(value = "/kakaostory", method = RequestMethod.GET)
		public ModelAndView kakaoStory(HttpServletRequest request,
				                          HttpServletResponse response) throws Exception
		{
			
			//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start kakaoStory::"+request.getQueryString());
			
			ModelAndView  mv = new ModelAndView();
			
			mv.addObject("hostUrl", hostUrl);
			mv.addObject("domainUrl", domainUrl);
			mv.addObject("redirectUrl", redirectUrl);
			
			mv.addObject("kakaoclient_id", kakaoclient_id);
			
			mv.setViewName("common/oauth2kakaostory");
			
			//log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
			return mv;
		}
		
		/**
		 * Login 처리
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception 
		 */
		@SuppressWarnings("null")
		@RequestMapping(value = "/naverlogin", method = RequestMethod.GET)
		public ModelAndView naverLogin(HttpServletRequest request,
				                          HttpServletResponse response) throws Exception
		{
			
			//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			
			
			ModelAndView  mv = new ModelAndView();
			
			// 사용자 세션정보
	        HttpSession session = request.getSession();
	        
	        String state = StringUtil.nvl((String) session.getAttribute("state")); 
	        
	        logger.info("["+logid+"] Controller start naverlogin state::"+state);
			
	        mv.addObject("state", state);
			mv.addObject("hostUrl", hostUrl);
			mv.addObject("domainUrl", domainUrl);
			mv.addObject("redirectUrl", redirectUrl);
			
			mv.addObject("naverclient_id", naverclient_id);
			mv.addObject("naverclient_secret", naverclient_secret);
			
			mv.setViewName("common/oauth2naver");
			
			//log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
			return mv;
		}
		
		/**
		 * Logout 처리
		 * @param request
		 * @return
		 * @throws Exception 
		 */
		@RequestMapping(value = "/common/logout")
		public ModelAndView logout(String loginType ,
				                   HttpServletRequest request) throws BizException
		{
			
			logger.info("Good bye salesb! ");
	
			HttpSession session = request.getSession(false);

		 	session.removeAttribute("customerKey");
		 	session.removeAttribute("customerId");
	        session.removeAttribute("customerName");
	        session.removeAttribute("customerKey1");
	        session.removeAttribute("customerKey2");
	        session.removeAttribute("customerKey3");
	        session.removeAttribute("customerKey4");
	        session.removeAttribute("customerKey5");
	        session.removeAttribute("customerKey6");
	        session.removeAttribute("customerKey7");
	        session.removeAttribute("customerKey8");
	        session.removeAttribute("customerKey9");
	        session.removeAttribute("customerKey10");
	        session.removeAttribute("staffYn");
	        session.removeAttribute("groupId");
	        session.removeAttribute("groupName");
	        
	        session.removeAttribute("id");
	        session.removeAttribute("name");
	        session.removeAttribute("restfulltype");
	        session.removeAttribute("photo");
	        session.removeAttribute("access_token");
	        session.removeAttribute("access_token_kakao");
	        session.removeAttribute("access_token_facebook");
	        session.removeAttribute("access_token_naver");
	        
	        session.removeAttribute("key");

	        logger.info("logout ok!");
	        
	        ModelAndView mv = new ModelAndView();

	        mv.setViewName("/common/intro");
	        /*
        	String state = generateState();

        	session = request.getSession(true);
			session.setAttribute("state", state);

			mv.addObject("state", state);
			mv.addObject("hostUrl", hostUrl);
			mv.addObject("domainUrl", domainUrl);
			mv.addObject("redirectUrl", redirectUrl);
			mv.addObject("redirectUrl2", redirectUrl2);
			mv.addObject("kakaoclient_id", kakaoclient_id);
			mv.addObject("naverclient_id", naverclient_id);
			mv.addObject("naverclient_secret", naverclient_secret);
			mv.addObject("facebookfbAppId", facebookfbAppId);
			mv.addObject("googleclient_id", googleclient_id);
			mv.addObject("pinterestclient_id", pinterestclient_id);

		 	mv.setViewName("/common/customerLoginForm");
	*/
			return mv;
		}
		
		/**
		 * Logout 처리
		 * @param request
		 * @return
		 * @throws Exception 
		 */
		@RequestMapping(value = "/common/businesslogout")
		public ModelAndView businesslogout(HttpServletRequest request) throws BizException
		{
			
			logger.info("Good bye  business! ");

			HttpSession session = request.getSession(false);
			
			String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
			String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
			String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
			
		 	session.removeAttribute("strUserId");
	        session.removeAttribute("strUserName");
	        session.removeAttribute("strGroupId");
	        session.removeAttribute("strGroupName");
	        session.removeAttribute("strAuthId");
	        session.removeAttribute("strAuthName");
	        session.removeAttribute("strExcelAuth");
	        session.removeAttribute("strPassword");
	        session.removeAttribute("strOfficePhone");
	        session.removeAttribute("strOfficePhoneFormat");
	        session.removeAttribute("strMobliePhone");
	        session.removeAttribute("strMobliePhoneFormat");
	        session.removeAttribute("strEmail");
	        session.removeAttribute("strIp");
	        session.removeAttribute("strAuth");
	        session.removeAttribute("sClientIP");
	        session.removeAttribute("pwdChangeDateTime");
	        session.removeAttribute("pwCycleDate");
	        
	        session.removeAttribute("key");
	        
	        //작업이력
			/*		
			UserVO userState =new UserVO();
			userState.setUserId(strUserId);
			userState.setLoginYn("N");
			userState.setIp(strIp);
			userState.setConnectIp(sClientIP);
			userSvc.regiLoginYnUpdate(userState);
	
			WorkVO work = new WorkVO();
			work.setWorkUserId(strUserId);
			work.setWorkIp(strIp);
			work.setWorkCategory("CM");
			work.setWorkCode("CM002");
			commonSvc.regiHistoryInsert(work);
		*/
	        ModelAndView mv = new ModelAndView();
	       // mv.setViewName("common/customerLoginForm");

	        mv.setViewName("/common/intro");

			return mv;
		}
		/**
	     * 고객정보 수정폼
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/common/customermodifyform")
	    public ModelAndView customerModifyForm(HttpServletRequest request, 
	    		                       HttpServletResponse response,
			                           String customerKey) throws BizException 
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start customerKey:"+customerKey);
	
	        ModelAndView mv = new ModelAndView();
	        
	     // 사용자 세션정보
	        HttpSession session = request.getSession();
	        
	        customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
	        String customerName = StringUtil.nvl((String) session.getAttribute("customerName")); 
	        String customerId = StringUtil.nvl((String) session.getAttribute("customerId"));
	        String sbPhoneNumber =  StringUtil.nvl((String) session.getAttribute("sbPhoneNumber"));
	        
	        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

	 	       	mv.setViewName("/common/customerLoginForm");
	        	return mv;
			}
	        
			CustomerVO customerVo = new CustomerVO();
			customerVo.setSearchType("00");
			customerVo.setCustomerKey(customerKey);
			
			CustomerVO customer = customerSvc.getCustKeyInfo(customerVo);	
  
			mv.addObject("customer", customer);
	        mv.setViewName("/common/customerModifyForm");
	        
	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return mv;
	    }
	    /**
	     * 고객정보(사업자) 수정폼
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/common/businessmodifyform")
	    public ModelAndView businessModifyForm(HttpServletRequest request, 
	    		                       HttpServletResponse response,
			                           String customerKey) throws BizException 
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start customerKey:"+customerKey);
	
	        ModelAndView mv = new ModelAndView();
	        
	     // 사용자 세션정보
	        HttpSession session = request.getSession();
	        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
	        String strUserName = StringUtil.nvl((String) session.getAttribute("strUserName")); 
	        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
	        String strAuthId = StringUtil.nvl((String) session.getAttribute("strAuthId"));
	        
	        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){

	        	mv.setViewName("/common/customerLoginForm");
	        	return mv;
			}
	        
	        UserVO userChkVo = new UserVO();
			userChkVo.setUserId(strUserId);
			
			UserVO userChk = userSvc.getUser(userChkVo);		
	        
			mv.addObject("business", userChk);
	        mv.setViewName("/common/businessModifyForm");
	        
	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return mv;
	    }
	    /**
		 * 패스워드 체크
		 */
		@RequestMapping("/common/passwordcheck")
		public @ResponseBody
		String passwordCheck(@RequestParam(value = "curPwd") String curPwd) 
		{

			logger.info("[pwd]" + curPwd);
			
			CustomerVO customerVo = new CustomerVO();
			customerVo.setCurPwd(curPwd);

			try{
	        	
				customerVo = customerSvc.getEncPassword(customerVo);

		    }catch(BizException e){
		       	
		    	e.printStackTrace();
		    }

			return ""+customerVo.getEncPwd();

		 }
		
		 /**
		 * ajax test
		 */
		@RequestMapping("/common/userpwcheck")
		public @ResponseBody
		String userPwCheck(@RequestParam(value = "curPwd") String curPwd) 
		{

			logger.info("[pwd]" + curPwd);
			
			UserVO userVo =new UserVO();
			userVo.setCurPwd(curPwd);

			try{
	        	
				userVo = commonSvc.getEncPassword(userVo);

		    }catch(BizException e){
		       	
		    	e.printStackTrace();
		    }

			return ""+userVo.getEncPwd();

		 }
		/**
	     * 고객정보 수정처리
	     *
	     * @param UserManageVO
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/common/customermodify", method = RequestMethod.POST)
	    public @ResponseBody
	    String customerModify(@ModelAttribute("customerVO") CustomerVO customerVO, 
	    		          HttpServletRequest request, 
	    		          HttpServletResponse response) throws BizException
	    {
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start : customerVO" + customerVO);
			
			int retVal=this.customerSvc.customerUpdateProc(customerVO);

			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

	      return ""+retVal;
	    }

		/**
	     * 사업자정보 수정처리
	     *
	     * @param UserManageVO
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/common/businessmodify", method = RequestMethod.POST)
	    public @ResponseBody
	    String businessModify(@ModelAttribute("userVO") UserManageVO userVO, 
	    		          HttpServletRequest request, 
	    		          HttpServletResponse response) throws BizException
	    {
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start : userVO" + userVO);
			
			int retVal=this.userManageSvc.userUpdateProc(userVO);
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

	      return ""+retVal;
	    }
	    
	    public String tokenCreate(){
	    
	    	String token="1234";
			
	    	Random rand = new Random(12);
			rand.setSeed(System.currentTimeMillis());
			
			token=""+rand.nextInt(10000);
			logger.info("##### create token :: " + token);
	    	
	    	return token;
	    }
	    
		/**
		 * Simply selects the home view to render by returning its name.
		 * @throws BizException
		 */
		@RequestMapping(value = "/customerprivateinfo", method = RequestMethod.GET)
		public ModelAndView customerPrivateInfo(String type ,
				                   HttpServletRequest request,
				                   HttpServletResponse response,  
				                   Model model, 
				                   Locale locale) throws BizException 
		{

			logger.info("Welcome customer");
			
			ModelAndView  mv = new ModelAndView();

	    	mv.setViewName("/common/customerPrivateInfo");

			return mv;
		}
		
		/* RestFull 정보받기
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping({"/common/restfullinfo"})
	    public @ResponseBody
	    JSONObject restFullInfo(String restfullurl,
	            HttpServletRequest request, 
	            HttpServletResponse response) throws BizException
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			
			logger.info("["+logid+"] Controller start restfullurl="+restfullurl);

			 JSONObject object =null;
			 String inputLine = null;
			 String content = "";

		    try{

	            BufferedReader input = null;
	
	            URL url = new URL(restfullurl);
	            input = new BufferedReader(new InputStreamReader(url.openStream()));

	            while ((inputLine = input.readLine()) != null) {
	            	
	            	 content += inputLine;
	            }
	            
	            input.close();

	            logger.info("["+logid+"] content::"+content);
	            
	            Object obj = JSONValue.parse(content);
	            
	            object = (JSONObject)obj;

	            /*
	            JSONArray array = (JSONArray)obj;
	            this.logger.debug("array ==>" + array);
	            List jasonList = new ArrayList();

	            Object arryObj = null;

	            for (int i = 0; i < array.size(); i++)
	            {
	              arryObj = JSONValue.parse(array.get(i).toString());
	              JSONObject arryObject = (JSONObject)arryObj;
	              jasonList.add(arryObject);
	            }
	            */
	        	    
	          }
	          catch (Exception e) {
	            e.printStackTrace();
	          }
			
	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return object;
	    }
	    
	    /* RestFull 정보받기
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping({"/common/restfullinfo2"})
	    public @ResponseBody
	    JSONObject restFullInfo2(String restfullurl,
	            HttpServletRequest request, 
	            HttpServletResponse response) throws BizException
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			
			logger.info("["+logid+"] Controller start restFullInfo2="+restfullurl);

			 JSONObject object =null;
			 JSONObject object2 =null;
			 String inputLine = null;
			 String content = "";

		    try{

	            BufferedReader input = null;
	
	            URL url = new URL(restfullurl);
	            input = new BufferedReader(new InputStreamReader(url.openStream()));

	            while ((inputLine = input.readLine()) != null) {
	            	
	            	 content += inputLine;
	            }
	            
	            input.close();

	            logger.info("["+logid+"] content::"+content);
	            
	            Object obj = JSONValue.parse(content);
	            
	            object = (JSONObject)obj;

	            logger.info("["+logid+"] restFullInfo2::"+object.get("data"));
	            
	            Object obj2 = JSONValue.parse(object.get("data").toString());
	            
	            object2 = (JSONObject)obj2;
	            logger.info("["+logid+"] CipherDecipherUtil url::"+object2.get("url"));
	            logger.info("["+logid+"] CipherDecipherUtil first_name::"+object2.get("first_name"));
	            logger.info("["+logid+"] CipherDecipherUtil first_name::"+object2.get("id"));

	            /*
	            JSONArray array = (JSONArray)obj;
	            this.logger.debug("array ==>" + array);
	            List jasonList = new ArrayList();

	            Object arryObj = null;

	            for (int i = 0; i < array.size(); i++)
	            {
	              arryObj = JSONValue.parse(array.get(i).toString());
	              JSONObject arryObject = (JSONObject)arryObj;
	              jasonList.add(arryObject);
	            }
	            */
	        	    
	          }
	          catch (Exception e) {
	            e.printStackTrace();
	          }
			
	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return object2;
	    }
	    
	    /* RestFull 정보받기
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping({"/common/restfullinfo3"})
	    public @ResponseBody
	    JSONObject restFullInfo3(String restfullurl3,
	    		String access_token,
	            HttpServletRequest request, 
	            HttpServletResponse response) throws BizException
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			
			logger.info("["+logid+"] Controller start restfullinfo3 "+restfullurl3);
			logger.info("["+logid+"] Controller start access_token "+access_token);

			 JSONObject object =null;
			 JSONObject object2 =null;
			 String inputLine = null;
			 String content = "";

		    try{

	            BufferedReader input = null;
	
	            URL url = new URL(restfullurl3);
	            
	            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	           
	            connection.setRequestProperty("Authorization", access_token);

		    	connection.setDoOutput(true);
		    	
		    	input= new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

	            while ((inputLine = input.readLine()) != null) {
	            	
	            	 content += inputLine;
	            }
	            
	            input.close();

	            logger.info("["+logid+"] content::"+content);
	            
	            Object obj = JSONValue.parse(content);
	            
	            object = (JSONObject)obj;

	            logger.info("["+logid+"] restfullinfo3 id::"+object.get("id"));
	            logger.info("["+logid+"] restfullinfo3 properties::"+object.get("properties"));
	            
	            Object obj2 = JSONValue.parse(object.get("properties").toString());
	            
	            object2 = (JSONObject)obj2;
	            
	            object2.put("id", object.get("id"));
	            
	            logger.info("["+logid+"] CipherDecipherUtil nickname::"+object2.get("nickname"));
	            logger.info("["+logid+"] CipherDecipherUtil custom_field1::"+object2.get("custom_field1"));
	            logger.info("["+logid+"] CipherDecipherUtil custom_field2::"+object2.get("custom_field2"));

	            /*
	            JSONArray array = (JSONArray)obj;
	            this.logger.debug("array ==>" + array);
	            List jasonList = new ArrayList();

	            Object arryObj = null;

	            for (int i = 0; i < array.size(); i++)
	            {
	              arryObj = JSONValue.parse(array.get(i).toString());
	              JSONObject arryObject = (JSONObject)arryObj;
	              jasonList.add(arryObject);
	            }
	            */
	        	    
	          }
	          catch (Exception e) {
	            e.printStackTrace();
	          }
			
	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return object2;
	    }
	    
	    /* RestFull 정보받기
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping({"/common/restkakaostory"})
	    public @ResponseBody
	    JSONObject restKakaoStory(String kaostoryurl,
	    		String access_token,
	    		String content,
	    		String image1 ,
	    		String image2 ,
	    		String image3 ,
	    		String image4 ,
	    		String image5 ,
	    		String android_exec_param,
	    		String ios_exec_param ,
	            HttpServletRequest request, 
	            HttpServletResponse response) throws BizException
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			
			logger.info("["+logid+"] Controller start kaostory "+kaostoryurl);
			logger.info("["+logid+"] Controller start access_token "+access_token);

			    JSONObject object =null;
			    
			    KakaoRestApiHelper apiHelper = new KakaoRestApiHelper();
			    
			    // access token 지정
		        apiHelper.setAccessToken(access_token);

			  
			    final String TEST_MYSTORY_ID = "[TEST MY STORY ID]";
		        final String TEST_SCRAP_URL = "https://kapi.kakao.com";

		        ResourceBundle rb = ResourceBundle.getBundle("config");
	            String uploadFilePath = rb.getString("offact.upload.path") + "goods";
		        // on Linux or Mac
	            File TEST_UPLOAD_FILE1=null;
	            File TEST_UPLOAD_FILE2=null;
	            File TEST_UPLOAD_FILE3=null;
	            File TEST_UPLOAD_FILE4=null;
	            File TEST_UPLOAD_FILE5=null;
	            
	            int index1=image1.indexOf("goods");
	            int index2=image2.indexOf("goods");
	            int index3=image3.indexOf("goods");
	            int index4=image4.indexOf("goods");
	            int index5=image5.indexOf("goods");
	            
	            logger.info("["+logid+"] Controller index1 "+index1);
	            logger.info("["+logid+"] Controller index2 "+index2);
	            logger.info("["+logid+"] Controller index3 "+index3);
	            logger.info("["+logid+"] Controller index4 "+index4);
	            logger.info("["+logid+"] Controller index5 "+index5);
	            
	            if(index1>0){image1=image1.substring(index1+5); TEST_UPLOAD_FILE1 = new File(uploadFilePath+image1);}
	            if(index2>0){image2=image2.substring(index2+5); TEST_UPLOAD_FILE2 = new File(uploadFilePath+image2);}
	            if(index3>0){image3=image3.substring(index3+5); TEST_UPLOAD_FILE3 = new File(uploadFilePath+image3);}
	            if(index4>0){image4=image4.substring(index4+5); TEST_UPLOAD_FILE4 = new File(uploadFilePath+image4);}
	            if(index5>0){image5=image1.substring(index5+5); TEST_UPLOAD_FILE5 = new File(uploadFilePath+image5);}
	            
	            logger.info("["+logid+"] Controller image1 "+image1);
	            logger.info("["+logid+"] Controller image2 "+image2);
	            logger.info("["+logid+"] Controller image3 "+image3);
	            logger.info("["+logid+"] Controller image4 "+image4);
	            logger.info("["+logid+"] Controller image5 "+image5);

		        File[] fiiles = new File[]{TEST_UPLOAD_FILE1, TEST_UPLOAD_FILE2,TEST_UPLOAD_FILE3, TEST_UPLOAD_FILE4, TEST_UPLOAD_FILE5} ;
		        
		        Map<String, String> paramMap;
		        
		        // 스토리 포스팅 공통 파라미터. 필요한 것만 선택하여 사용.
		        paramMap = new HashMap<String, String>();
		        paramMap.put("permission", "A"); // A : 전체공개, F: 친구에게만 공개, M: 나만보기
		        paramMap.put("enable_share", "true"); // 공개 기능 허용 여부
		        paramMap.put("android_exec_param", "cafe_id=1234"); // 앱 이동시 추가 파라미터
		        paramMap.put("ios_exec_param", "cafe_id=1234");
		        paramMap.put("android_market_param", "cafe_id=1234");
		        paramMap.put("ios_market_param", "cafe_id=1234");

		        // 글 포스팅일 경우에는 content는 필수지만 링크/사진 포스팅일 때는 옵션.
		        paramMap.put("content", content);

		        String result;
			 
			  // 사진 포스팅 (최대 10개까지 가능)
		        String uploadedImageObj = apiHelper.uploadMulti(fiiles);
		        if (uploadedImageObj != null) {
		        	logger.info("["+logid+"] uploaded file(s) successfully.");
		        	logger.info("["+logid+"] uploadedImageObj : "+uploadedImageObj);
		            paramMap.put("image_url_list", uploadedImageObj);
		            result = apiHelper.postPhoto(paramMap);
		            if (result != null && result.indexOf("code\":-") == -1) {
		                String postedId = result.split("\"")[3];
		                logger.info("["+logid+"] postedId:" + postedId);
		                object.put("id", postedId);
		                /*
		                if (apiHelper.deleteMyStory(postedId).equals(""))
		                    System.out.println("deleted test my story " + postedId);
		            */
		            }

		        } else {
		            System.out.println("failed to upload");
		        }
			
	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return object;
	    }
	    
	    /* RestFull 정보받기
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping({"/common/restkakaostorymulti"})
	    public @ResponseBody
	    JSONObject restKakaoStoryMulti(String kaostorymultiurl,
	    		String kaostoryurl,
	    		String access_token,
	    		String content,
	    		String file1,
	    		String file2 ,
	    		String file3 ,
	    		String android_exec_param,
	    		String ios_exec_param ,
	            HttpServletRequest request, 
	            HttpServletResponse response) throws BizException
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			
			logger.info("["+logid+"] Controller start kaostory "+kaostoryurl);
			logger.info("["+logid+"] Controller start access_token "+access_token);

			 JSONObject object =null;
			 String inputLine = null;
			 String resultcontent = "";
			 
			 String CRLF = "\r\n";
		     String TWO_HYPHENS = "--";
		     String BOUNDARY = "---------------------------012345678901234567890123456";
		     HttpsURLConnection conn = null;
		     DataOutputStream dos = null;
		     FileInputStream fis = null;
     
	         int bytesRead, bytesAvailable, bufferSize;
	         byte[] buffer;
	         int MAX_BUFFER_SIZE = 1 * 1024 * 1024;
	         
	         String image_url_list="";

		    try{

	            BufferedReader input = null;
	
	            URL mUrl = new URL(kaostorymultiurl);
	            
	            conn = (HttpsURLConnection)mUrl.openConnection();
	            
	            conn.setDoInput(true);
	            conn.setDoOutput(true);
	            conn.setUseCaches(false);
	            conn.setRequestMethod("POST");

	            conn.setRequestProperty("Connection", "Keep-Alive");
	            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + 
	                                      BOUNDARY);
	            conn.setRequestProperty("Authorization", access_token);
	            conn.setRequestProperty("Cache-Control", "no-cache");
	       
	            dos = new DataOutputStream(conn.getOutputStream());
 
	            ResourceBundle rb = ResourceBundle.getBundle("config");
	            String uploadFilePath = rb.getString("offact.upload.path") + "goods";
	            
	            //File[] files={new File(uploadFilePath+"/salesbaron.jpg"), new File(uploadFilePath+"/salesbaron.jpg")};
	            File[] files={new File(uploadFilePath+"/salesbaron.jpg")};


	            for (File f : files) {
	                dos.writeBytes(TWO_HYPHENS + BOUNDARY + CRLF);
	                dos.writeBytes("Content-Disposition: form-data; name=\"file\";" +
	                                " filename=\"" + f.getName() + "\"" + CRLF);
	                dos.writeBytes(CRLF);
	                fis = new FileInputStream(f);
	                bytesAvailable = fis.available();
	                bufferSize = Math.min(bytesAvailable, MAX_BUFFER_SIZE);
	                buffer = new byte[bufferSize];
	                bytesRead = fis.read(buffer, 0, bufferSize);
	                
	                while (bytesRead > 0) {
	                    dos.write(buffer, 0, bufferSize);
	                    bytesAvailable = fis.available();
	                    bufferSize = Math.min(bytesAvailable, MAX_BUFFER_SIZE);
	                    bytesRead = fis.read(buffer, 0, bufferSize);
	                }
	                
	                logger.info("["+files.length+"]@" );
	                
	                dos.writeBytes(CRLF);
	            }

	            // finish delimiter
	            dos.writeBytes(TWO_HYPHENS + BOUNDARY + TWO_HYPHENS + CRLF);
	            logger.info("["+logid+"] TWO_HYPHENS "+TWO_HYPHENS + BOUNDARY + TWO_HYPHENS + CRLF);
	            
	            fis.close();
	            dos.flush();
	            dos.close();
 	    
		    } catch (MalformedURLException ex) {
	            ex.printStackTrace();
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	        } finally {
	            if (fis != null) try { fis.close(); } catch (IOException ignore) { }
	            if (dos != null) try { dos.close(); } catch (IOException ignore) { }
	        }

	        // Response
	        InputStream inputStream = null;
	        BufferedReader reader = null;
	        try {
	            inputStream = new BufferedInputStream(conn.getInputStream());
	            reader = new BufferedReader(new InputStreamReader(inputStream));
	            String line;
	            StringBuilder builder = new StringBuilder();
	            while ((line = reader.readLine()) != null) {
	                builder.append(line).append("\n");
	                logger.info("[line] "+line);
	            }
	            image_url_list=line;
	            logger.info("[builder] "+builder);
 
	            reader.close();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try { reader.close(); } catch (IOException ignore) {}
	            }
	            if (inputStream != null) {
	                try { inputStream.close(); } catch (IOException ignore) {}
	            }
	            conn.disconnect();
	        }
	        
	        try{

	            BufferedReader input = null;
	
	            URL url = new URL(kaostoryurl);
	            
	            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	            
	            connection.setRequestMethod("POST");
	           
	            connection.setRequestProperty("Authorization", access_token);

		    	connection.setDoOutput(true);
		    	
		    	 // 파라미터 전송.
	            StringBuilder sb = new StringBuilder();
	            sb.append("content=")
	              .append(content)
	              .append("&image_url_list=")
	              .append(image_url_list)
	              .append("&android_exec_param=")
	              .append(android_exec_param)
	              .append("&ios_exec_param=")
	              .append(ios_exec_param);
	            
	            
	            OutputStream writer = connection.getOutputStream();
	            writer.write(sb.toString().getBytes("UTF-8"));
	            writer.flush();
	            writer.close();
	            
	            //////////////////////////////////////////////////
	            ////////////////// If GET Method  ////////////////            
	            //String urlStrings = "http://......";
	            //urlStrings += "&sender=";
	            //HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            //////////////////////////////////////////////////
	            int responseCode = connection.getResponseCode();   
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	            	
	                // 발송성공..
		                input= new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

			            while ((inputLine = input.readLine()) != null) {
			            	
			            	resultcontent += inputLine;
			            }
			            
			            input.close();
		            
	            } else {
	                // 발송실패..
	            }


	            logger.info("["+logid+"] content::"+resultcontent);
	            
	            Object obj = JSONValue.parse(resultcontent);
	            
	            object = (JSONObject)obj;

	            logger.info("["+logid+"] restfullinfo3 id::"+object.get("id"));
  	    
	          }
	          catch (Exception e) {
	            e.printStackTrace();
	          }

	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return object;
	    }
	    
	    
	    /* RestFull 정보받기
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping({"/common/restfullinfo4"})
	    public @ResponseBody
	    JSONObject restFullInfo4(String restfullurl3,
	    		String access_token,
	            HttpServletRequest request, 
	            HttpServletResponse response) throws BizException
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			
			logger.info("["+logid+"] Controller start restfullinfo4 "+restfullurl3);
			logger.info("["+logid+"] Controller start access_token "+access_token);

			 JSONObject object =new JSONObject();
			 String inputLine = null;
			 String content = "";

		    try{

	            BufferedReader input = null;
	
	            URL url = new URL(restfullurl3);
	            
	            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	           
	            connection.setRequestProperty("Authorization",access_token);

		    	connection.setDoOutput(true);
		    	
		    	input= new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

	            while ((inputLine = input.readLine()) != null) {
	            	
	            	 content += inputLine;
	            }
	            
	            input.close();

	            logger.info("["+logid+"] content::"+content);
	            
		        Element root = XmlUtil.loadStringDocument(content.toString());
		       	   
	       	    String resultcode =XmlUtil.getTagValue(root, "resultcode");
	       	    String message =XmlUtil.getTagValue(root, "message");
	       	    String id =XmlUtil.getTagValue(root, "id");
	       	    String nickname =XmlUtil.getTagValue(root, "nickname");
	       	    String profile_image =XmlUtil.getTagValue(root, "profile_image");
	       	    
	       	    logger.info("["+logid+"] resultcode::"+resultcode);
	       	    logger.info("["+logid+"] message::"+message);
	            
	            //<?xml version="1.0" encoding="UTF-8" ?><data><result><resultcode>00</resultcode><message>success</message></result><response><email><![CDATA[ranrhdwn76@naver.com]]></email><nickname><![CDATA[ranrhd****]]></nickname><enc_id><![CDATA[fa53b513469bce9ab8f80513c9bd691256416ec50676dc8fc264b7713e4e1077]]></enc_id><profile_image><![CDATA[https://phinf.pstatic.net/contactthumb/profile/blog/1/13/ranrhdwn76.jpg?type=s80]]></profile_image><age><![CDATA[40-49]]></age><gender>F</gender><id><![CDATA[57891596]]></id><name><![CDATA[김정란]]></name><birthday><![CDATA[02-08]]></birthday></response></data>

	            object.put("resultcode", resultcode);
	            object.put("id", id);
	            object.put("nickname", nickname);
	            object.put("profile_image", profile_image);
	            
	            logger.info("["+logid+"] CipherDecipherUtil nickname::"+object.get("nickname"));
	            logger.info("["+logid+"] CipherDecipherUtil id::"+object.get("id"));
	            logger.info("["+logid+"] CipherDecipherUtil profile_image::"+object.get("profile_image"));

	          }
	          catch (Exception e) {
	            e.printStackTrace();
	          }
			
	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return object;
	    }
	    
	    /* RestFull 정보받기
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping({"/common/facebookpost"})
	    public @ResponseBody
	    JSONObject facebookPost(String facebookurl,
	    		String message,
	    		String access_token,
	    		String picture,
	    		String link,
	    		String caption,
	    		String discription,
	    		String source,
	            HttpServletRequest request, 
	            HttpServletResponse response) throws BizException
	    {

	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			
			logger.info("["+logid+"] Controller start facebookurl "+facebookurl);
			logger.info("["+logid+"] Controller start message "+message);
			logger.info("["+logid+"] Controller start access_token "+access_token);

			 JSONObject object =null;
			 String inputLine = null;
			 String content = "";

			 try{

		            BufferedReader input = null;
		
		            URL url = new URL(facebookurl);
		            
		            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		            
		            connection.setRequestMethod("POST");

			    	connection.setDoOutput(true);
			    	
			    	 // 파라미터 전송.
		            StringBuilder sb = new StringBuilder();
		            sb.append("message=")
		              .append(message)
		              .append("&picture=")
		              .append(picture)
		              .append("&link=")
		              .append(link)
		              .append("&caption=")
		              .append(caption)
		              .append("&discription=")
		              .append(discription)
		              .append("&source=")
		              .append(source)
		              .append("&access_token=")
		              .append(access_token);

		            OutputStream writer = connection.getOutputStream();
		            writer.write(sb.toString().getBytes("UTF-8"));
		            writer.flush();
		            writer.close();
		            
		            //////////////////////////////////////////////////
		            ////////////////// If GET Method  ////////////////            
		            //String urlStrings = "http://......";
		            //urlStrings += "&sender=";
		            //HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		            //////////////////////////////////////////////////
		            int responseCode = connection.getResponseCode();   
		            if (responseCode == HttpURLConnection.HTTP_OK) {
		            	
		                // 발송성공..
			                input= new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

				            while ((inputLine = input.readLine()) != null) {
				            	
				            	content += inputLine;
				            }
				            
				            input.close();
			            
		            } else {
		                // 발송실패..
		            }


		            logger.info("["+logid+"] content::"+content);
		            
		            Object obj = JSONValue.parse(content);
		            
		            object = (JSONObject)obj;

		            logger.info("["+logid+"] restfullinfo3 id::"+object.get("id"));
	  	    
		          }
		          catch (Exception e) {
		            e.printStackTrace();
		          }
			
	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return object;
	    }
	    
		/* chat test (cross domain)
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping({"/common/chattest"})
	    public @ResponseBody
	    String chatTest(String redirecturl,
	            HttpServletRequest request, 
	            HttpServletResponse response) throws BizException
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			
			logger.info("["+logid+"] Controller start chatTest="+redirecturl);

			 JSONObject object =null;
			 String inputLine = null;
			 String content = "";

		    try{

	            BufferedReader input = null;
	
	            URL url = new URL(redirecturl);
	            input = new BufferedReader(new InputStreamReader(url.openStream()));

	            while ((inputLine = input.readLine()) != null) {
	            	
	            	 content += inputLine;
	            }
	            
	            input.close();

	            logger.info("["+logid+"] content::"+content);
    
	          }
	          catch (Exception e) {
	            e.printStackTrace();
	          }
			
	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return content;
	    }
	    
	    
	    /**
		 * (사업자) Login 처리
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception 
		 */
		@SuppressWarnings("null")
		@RequestMapping(value = "/business/login", method = RequestMethod.POST)
		public ModelAndView businesslogin(HttpServletRequest request,
				                          HttpServletResponse response) throws Exception
		{
			
			//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start");
			
			ModelAndView  mv = new ModelAndView();

			String workCode="CM001";
			
			String strUserId = StringUtil.nvl(request.getParameter("businessId"));
			String strUserPw = StringUtil.nvl(request.getParameter("businessPw"));
			String sClientIP = StringUtil.nvl(request.getParameter("sClientIP"));
			
			logger.info(">>>> userId :"+strUserId);
			logger.info(">>>> userPw :"+strUserPw);
			logger.info(">>>> sClientIP :"+sClientIP);
			
			String ip = request.getHeader("X-Forwarded-For");

			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 

			    ip = request.getHeader("Proxy-Client-IP"); 
			    logger.info(">>>> Proxy-Client-IP :"+ip);

			} 

			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 

			    ip = request.getHeader("WL-Proxy-Client-IP"); 
			    logger.info(">>>> WL-Proxy-Client-IP :"+ip);

			} 

			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 

			    ip = request.getHeader("HTTP_CLIENT_IP"); 
			    logger.info(">>>> HTTP_CLIENT_IP :"+ip);

			} 

			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 

			    ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
			    logger.info(">>>> HTTP_X_FORWARDED_FOR :"+ip);

			} 

			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 

			    ip = request.getRemoteAddr(); 
			    logger.info(">>>> RemoteAddr :"+ip);

			}

			String strMainUrl = "";
			
			// # 2. 넘겨받은 아이디로 데이터베이스를 조회하여 사용자가 있는지를 체크한다.
			UserVO userChkVo = new UserVO();
			userChkVo.setUserId(strUserId);
			userChkVo.setInPassword(strUserPw);
			UserVO userChk = userSvc.getUser(userChkVo);		

			String strUserName = "";
			String strGroupId = "";
			String strGroupName = "";
			String strAuthId = "";
			String strAuth = "";
			String strAuthName = "";
			String strExcelAuth = "";
			String strPassword = "";
			String strOfficePhone = "";
			String strOfficePhoneFormat = "";
			String strMobliePhone = "";
			String strMobliePhoneFormat = "";
			String strEmail = "";
			String strIp = "";
			String strPwdChangeDateTime = "";
			String strPwCycleDate = "";
			String smsAlarmYn = "";
			String smsAlarmPoint = "";
			
			strIp = ip;//Client 외부IP or G/W
			
			//서버 IP/MAC정보
			
			sClientIP = request.getLocalAddr();
			/*
			InetAddress serverIp;
			serverIp=InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(serverIp);
			byte[] mac = network.getHardwareAddress();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
			}
			logger.info(">>>> [Current MAC address ] ");
			logger.info("@@@@ [serverIp] :"+serverIp);
			logger.info("@@@@ [MAC addres] :"+sb.toString());
			*/
			logger.info(">>>> [strIp] :"+strIp);
			logger.info(">>>> [sClientIP] :"+sClientIP);
		
			if(userChk != null)
			{
				//패스워드 체크
				if(!userChk.getPassword().equals(userChk.getInPassword())){
					
					logger.info(">>> 비밀번호 오류");
					strMainUrl = "admin/loginFail";
					workCode="CM006";
					
					mv.addObject("userId", strUserId);
					
					mv.setViewName(strMainUrl);
					
					//작업이력
					WorkVO work = new WorkVO();
					work.setWorkUserId(strUserId);
					work.setWorkIp(strIp);
					work.setWorkCategory("CM");
					work.setWorkCode(workCode);
					commonSvc.regiHistoryInsert(work);
					
					//log Controller execute time end
			      	long t2 = System.currentTimeMillis();
			      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
			      	
					return mv;
					
				}
				
				strUserId= userChk.getUserId();
				strUserName = userChk.getUserName();
				strGroupId = userChk.getGroupId();
				strGroupName = userChk.getGroupName();
				strAuthId = userChk.getAuthId();
				strAuthName = userChk.getAuthName();
				strExcelAuth = userChk.getExcelAuth();
				strPassword = userChk.getPassword();
				strOfficePhone = userChk.getOfficePhone();
				strOfficePhoneFormat = userChk.getOfficePhoneFormat();
				strMobliePhone = userChk.getMobliePhone();
				strMobliePhoneFormat = userChk.getMobliePhoneFormat();
				strEmail = userChk.getEmail();
				strAuth =userChk.getAuth();
				strPwdChangeDateTime =userChk.getPwdChangeDateTime();
				strPwCycleDate =userChk.getPwCycleDate();
				smsAlarmYn =userChk.getSmsAlarmYn();
				smsAlarmPoint =userChk.getSmsAlarmPoint();

				// # 3. Session 객체에 셋팅
				
				HttpSession session = request.getSession(false);
				
				if(session != null)
				{
					session.invalidate();
				}
					
					session = request.getSession(true);
					session.setAttribute("strUserId", strUserId);
					session.setAttribute("strUserName", strUserName);
					session.setAttribute("strGroupId", strGroupId);
					session.setAttribute("strGroupName", strGroupName);
					session.setAttribute("strAuthId", strAuthId);
					session.setAttribute("strAuthName", strAuthName);
					session.setAttribute("strExcelAuth", strExcelAuth);
					session.setAttribute("strPassword", strPassword);
					session.setAttribute("strOfficePhone", strOfficePhone);
					session.setAttribute("strOfficePhoneFormat", strOfficePhoneFormat);
					session.setAttribute("strMobliePhone", strMobliePhone);
					session.setAttribute("strMobliePhoneFormat", strMobliePhoneFormat);
					session.setAttribute("strEmail", strEmail);
					session.setAttribute("strIp", strIp);
					session.setAttribute("strAuth", strAuth);
					session.setAttribute("sClientIP", sClientIP);
					session.setAttribute("pwdChangeDateTime", strPwdChangeDateTime);
					session.setAttribute("pwCycleDate", strPwCycleDate);
					session.setAttribute("smsAlarmYn", smsAlarmYn);
					session.setAttribute("smsAlarmPoint", smsAlarmPoint);
					
					//로그인 상태처리		
					
					userChk.setUserId(strUserId);
					userChk.setLoginYn("Y");
					userChk.setIp(strIp);
					userChk.setConnectIp(sClientIP);
					try{
						userSvc.regiLoginYnUpdate(userChk);
					}catch(Exception e){
						logger.debug("[Error]USER SQL lock 오류");
					}
			        
					mv.addObject("userId", strUserId);
			
			        UserManageVO userConVO = new UserManageVO();
			        
			        userConVO.setUserId(strUserId);
			        userConVO.setGroupId(strGroupId);

			        // 조회조건저장
			        mv.addObject("userConVO", userConVO);

			        //조직정보 조회
			        GroupVO group = new GroupVO();
			        group.setGroupId(strGroupId);
			        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
			        mv.addObject("group_comboList", group_comboList);
			        
			        strMainUrl = "salesb/topBusiness";

						
				} else {//app 상요자 정보가 없는경우
		
					logger.info(">>> 상담App 사용자 정보 없음");
					strMainUrl = "common/loginFail";
					workCode="CM005";
				}
				
			    //작업이력
				WorkVO work = new WorkVO();
				work.setWorkUserId(strUserId);
				work.setWorkIp(strIp);
				work.setWorkCategory("CM");
				work.setWorkCode(workCode);
				commonSvc.regiHistoryInsert(work);
			
				mv.addObject("userId", strUserId);
				
				mv.setViewName(strMainUrl);
				
				//log Controller execute time end
		      	long t2 = System.currentTimeMillis();
		      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
		      	
				return mv;
			}
		
			/**
		     * 이미지 보기
		     *
		     * @param request
		     * @param response
		     * @param model
		     * @param locale
		     * @return
		     * @throws BizException
		     */
		    @RequestMapping(value = "/common/imageview")
		    public ModelAndView imageView(HttpServletRequest request, 
		    		                      HttpServletResponse response,
		    		                      String imageurl) throws BizException 
		    {
		        
		    	//log Controller execute time start
				String logid=logid();
				long t1 = System.currentTimeMillis();
				logger.info("["+logid+"] Controller start imageView");
		
		        ModelAndView mv = new ModelAndView();
		      
		        mv.addObject("imageurl", imageurl);
	
		        mv.setViewName("/common/imageView");
		        
		       //log Controller execute time end
		      	long t2 = System.currentTimeMillis();
		      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
		      	
		        return mv;
		    }
		    
		    /* facebooklogin 정보받기
		     *
		     * @param request
		     * @param response
		     * @param model
		     * @param locale
		     * @return
		     * @throws BizException
		     */
		    @RequestMapping({"/common/facebooklogin"})
		    public @ResponseBody
		    JSONObject facebookLogin(String restfullurl,
		            HttpServletRequest request, 
		            HttpServletResponse response) throws BizException
		    {
		        
		    	//log Controller execute time start
				String logid=logid();
				long t1 = System.currentTimeMillis();
				
				logger.info("["+logid+"] Controller start facebooklogin "+restfullurl);

				 JSONObject object =null;
				 String inputLine = null;
				 String content = "";

			    try{

		            BufferedReader input = null;
		
		            URL url = new URL(restfullurl);
		            input = new BufferedReader(new InputStreamReader(url.openStream()));

		            while ((inputLine = input.readLine()) != null) {
		            	
		            	 content += inputLine;
		            }
		            
		            input.close();

		            logger.info("["+logid+"] content::"+content);
		            
		            Object obj = JSONValue.parse(content);
		            
		            object = (JSONObject)obj;
		            
		            logger.info("["+logid+"] id::"+object.get("id"));
		            
		            CustomerVO custVo = new CustomerVO();

		            custVo.setSearchType("06");
		            custVo.setSocialId3((String)object.get("id"));
		            
		            //고객키 조회
		            custVo=customerSvc.getCustKeyInfo(custVo);
		            
		            if(custVo==null){
		            	
		            	 object.put("customerKey", "N");
		            	 
		            }else{
		            	
		            	 object.put("customerKey", custVo.getCustomerKey());
		            }

		            /*
		            JSONArray array = (JSONArray)obj;
		            this.logger.debug("array ==>" + array);
		            List jasonList = new ArrayList();

		            Object arryObj = null;

		            for (int i = 0; i < array.size(); i++)
		            {
		              arryObj = JSONValue.parse(array.get(i).toString());
		              JSONObject arryObject = (JSONObject)arryObj;
		              jasonList.add(arryObject);
		            }
		            */
		        	    
		          }
		          catch (Exception e) {
		            e.printStackTrace();
		          }
				
		       //log Controller execute time end
		      	long t2 = System.currentTimeMillis();
		      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
		      	
		        return object;
		    }
		    /**
		     * 고객정보 수정처리
		     *
		     * @param UserManageVO
		     * @param request
		     * @param response
		     * @param model
		     * @param locale
		     * @return
		     * @throws BizException
		     */
		    @RequestMapping(value = "/common/facebooklink", method = RequestMethod.POST)
		    public @ResponseBody
		    String facebookLink(@ModelAttribute("customerVO") CustomerVO customerVO, 
		    		          HttpServletRequest request, 
		    		          HttpServletResponse response) throws BizException
		    {
		    	//log Controller execute time start
				String logid=logid();
				long t1 = System.currentTimeMillis();
				logger.info("["+logid+"] Controller start : customerVO" + customerVO);
				
				 String linkResult="";
				 int retVal=0;
				 
				 String socialId3=customerVO.getSocialId3();
				 
			     //고객키 조회
				 customerVO.setInCustomerPw(customerVO.getSbPw());
				 
				 customerVO=customerSvc.getCustKeyInfo(customerVO);
   
			     if(customerVO==null){
			        	
			    	linkResult="00";//고객정보 미등록
			    	 
					//log Controller execute time end
			       	long t2 = System.currentTimeMillis();
			       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

			        return linkResult;
			     }
			     
			     if(customerVO.getUsedYn().equals("N")){
			    	 
			    	 linkResult="01";//고객정보 비활성화
			    	 
			     
			     }else{
			    	 
			    	//패스워드 체크
					if(!customerVO.getCustomerPw().equals(customerVO.getInCustomerPw())){
						
						linkResult="02";//패스워드 오류
						
					}else{
					 
				    	 customerVO.setSocialId3(socialId3);
				    	 retVal=this.customerSvc.socialId3UpdateProc(customerVO);
				    	 
				    	 if(retVal<=0){
				    		 linkResult="03";//고객정보 연동실패
				    	 }else{
				    		 linkResult=customerVO.getCustomerKey();//고객정보 연동성공
				    	 }
				    	 
					}
			    	 
			     }

				//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

		        return linkResult;
		    }
		    
		    /* RestFull 정보받기
		     *
		     * @param request
		     * @param response
		     * @param model
		     * @param locale
		     * @return
		     * @throws BizException
		     */
		    @RequestMapping({"/common/kakaologin"})
		    public @ResponseBody
		    JSONObject kakaoLogin(String restfullurl3,
		    		String access_token,
		            HttpServletRequest request, 
		            HttpServletResponse response) throws BizException
		    {
		        
		    	//log Controller execute time start
				String logid=logid();
				long t1 = System.currentTimeMillis();
				
				logger.info("["+logid+"] Controller start restfullinfo3 "+restfullurl3);
				logger.info("["+logid+"] Controller start access_token "+access_token);

				 JSONObject object =null;
				 JSONObject object2 =null;
				 String inputLine = null;
				 String content = "";

			    try{

		            BufferedReader input = null;
		
		            URL url = new URL(restfullurl3);
		            
		            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		           
		            connection.setRequestProperty("Authorization", access_token);

			    	connection.setDoOutput(true);
			    	
			    	input= new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

		            while ((inputLine = input.readLine()) != null) {
		            	
		            	 content += inputLine;
		            }
		            
		            input.close();

		            logger.info("["+logid+"] content::"+content);
		            
		            Object obj = JSONValue.parse(content);
		            
		            object = (JSONObject)obj;

		            logger.info("["+logid+"] restfullinfo3 id::"+object.get("id"));
		            logger.info("["+logid+"] restfullinfo3 properties::"+object.get("properties"));
		            
		            Object obj2 = JSONValue.parse(object.get("properties").toString());
		            
		            object2 = (JSONObject)obj2;
		            
		            String kakaoId=String.valueOf(object.get("id"));
		            
		            object2.put("id", object.get("id"));
		            
		            logger.info("["+logid+"] CipherDecipherUtil nickname::"+object2.get("nickname"));
		            logger.info("["+logid+"] CipherDecipherUtil custom_field1::"+object2.get("custom_field1"));
		            logger.info("["+logid+"] CipherDecipherUtil custom_field2::"+object2.get("custom_field2"));
	
		            
		            CustomerVO custVo = new CustomerVO();
		            
		            custVo.setSearchType("04");
		            custVo.setSocialId1(kakaoId);
		            
		            //고객키 조회
		            custVo=customerSvc.getCustKeyInfo(custVo);
		            
		            if(custVo==null){
		            	
		            	object2.put("customerKey", "N");
		            	 
		            }else{
		            	
		            	object2.put("customerKey", custVo.getCustomerKey());
		            }
		           
		            /*
		            JSONArray array = (JSONArray)obj;
		            this.logger.debug("array ==>" + array);
		            List jasonList = new ArrayList();

		            Object arryObj = null;

		            for (int i = 0; i < array.size(); i++)
		            {
		              arryObj = JSONValue.parse(array.get(i).toString());
		              JSONObject arryObject = (JSONObject)arryObj;
		              jasonList.add(arryObject);
		            }
		            */
		        	    
		          }
		          catch (Exception e) {
		            e.printStackTrace();
		          }
				
		       //log Controller execute time end
		      	long t2 = System.currentTimeMillis();
		      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
		      	
		        return object2;
		    }
		    
		    /**
		     * 고객정보 수정처리
		     *
		     * @param UserManageVO
		     * @param request
		     * @param response
		     * @param model
		     * @param locale
		     * @return
		     * @throws BizException
		     */
		    @RequestMapping(value = "/common/kakaolink", method = RequestMethod.POST)
		    public @ResponseBody
		    String kakaoLink(@ModelAttribute("customerVO") CustomerVO customerVO, 
		    		          HttpServletRequest request, 
		    		          HttpServletResponse response) throws BizException
		    {
		    	//log Controller execute time start
				String logid=logid();
				long t1 = System.currentTimeMillis();
				logger.info("["+logid+"] Controller start : customerVO" + customerVO);
				
				 String linkResult="";
				 int retVal=0;
				 
				 String socialId1=customerVO.getSocialId1();
				 
			     //고객키 조회
				 customerVO.setInCustomerPw(customerVO.getSbPw());
				 
				 customerVO=customerSvc.getCustKeyInfo(customerVO);
   
			     if(customerVO==null){
			        	
			    	linkResult="00";//고객정보 미등록
			    	 
					//log Controller execute time end
			       	long t2 = System.currentTimeMillis();
			       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

			        return linkResult;
			     }
			     
			     if(customerVO.getUsedYn().equals("N")){
			    	 
			    	 linkResult="01";//고객정보 비활성화
			    	 
			     
			     }else{
			    	 
			    	//패스워드 체크
					if(!customerVO.getCustomerPw().equals(customerVO.getInCustomerPw())){
						
						linkResult="02";//패스워드 오류
						
					}else{
					 
				    	 customerVO.setSocialId1(socialId1);
				    	 retVal=this.customerSvc.socialId1UpdateProc(customerVO);
				    	 
				    	 if(retVal<=0){
				    		 linkResult="03";//고객정보 연동실패
				    	 }else{
				    		 linkResult=customerVO.getCustomerKey();//고객정보 연동성공
				    	 }
				    	 
					}
			    	 
			     }

				//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

		        return linkResult;
		    }
		    
		    /* RestFull 정보받기
		     *
		     * @param request
		     * @param response
		     * @param model
		     * @param locale
		     * @return
		     * @throws BizException
		     */
		    @RequestMapping({"/common/naverlogin"})
		    public @ResponseBody
		    JSONObject naverLogin(String restfullurl3,
		    		String access_token,
		            HttpServletRequest request, 
		            HttpServletResponse response) throws BizException
		    {
		        
		    	//log Controller execute time start
				String logid=logid();
				long t1 = System.currentTimeMillis();
				
				logger.info("["+logid+"] Controller start restfullinfo4 "+restfullurl3);
				logger.info("["+logid+"] Controller start access_token "+access_token);

				 JSONObject object =new JSONObject();
				 String inputLine = null;
				 String content = "";

			    try{

		            BufferedReader input = null;
		
		            URL url = new URL(restfullurl3);
		            
		            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		           
		            connection.setRequestProperty("Authorization",access_token);

			    	connection.setDoOutput(true);
			    	
			    	input= new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

		            while ((inputLine = input.readLine()) != null) {
		            	
		            	 content += inputLine;
		            }
		            
		            input.close();

		            logger.info("["+logid+"] content::"+content);
		            
			        Element root = XmlUtil.loadStringDocument(content.toString());
			       	   
		       	    String resultcode =XmlUtil.getTagValue(root, "resultcode");
		       	    String message =XmlUtil.getTagValue(root, "message");
		       	    String id =XmlUtil.getTagValue(root, "id");
		       	    String nickname =XmlUtil.getTagValue(root, "nickname");
		       	    String profile_image =XmlUtil.getTagValue(root, "profile_image");
		       	    
		       	    logger.info("["+logid+"] resultcode::"+resultcode);
		       	    logger.info("["+logid+"] message::"+message);
		            
		            //<?xml version="1.0" encoding="UTF-8" ?><data><result><resultcode>00</resultcode><message>success</message></result><response><email><![CDATA[ranrhdwn76@naver.com]]></email><nickname><![CDATA[ranrhd****]]></nickname><enc_id><![CDATA[fa53b513469bce9ab8f80513c9bd691256416ec50676dc8fc264b7713e4e1077]]></enc_id><profile_image><![CDATA[https://phinf.pstatic.net/contactthumb/profile/blog/1/13/ranrhdwn76.jpg?type=s80]]></profile_image><age><![CDATA[40-49]]></age><gender>F</gender><id><![CDATA[57891596]]></id><name><![CDATA[김정란]]></name><birthday><![CDATA[02-08]]></birthday></response></data>
   
		            object.put("resultcode", resultcode);
		            object.put("id", id);
		            object.put("nickname", nickname);
		            object.put("profile_image", profile_image);
		            
		       	    CustomerVO custVo = new CustomerVO();
		            
		            custVo.setSearchType("05");
		            custVo.setSocialId2(id);
		            
		            //고객키 조회
		            custVo=customerSvc.getCustKeyInfo(custVo);
		            
		            if(custVo==null){
		            	
		            	object.put("customerKey", "N");
		            	 
		            }else{
		            	
		            	object.put("customerKey", custVo.getCustomerKey());
		            }
		            
		            logger.info("["+logid+"] CipherDecipherUtil nickname::"+object.get("nickname"));
		            logger.info("["+logid+"] CipherDecipherUtil id::"+object.get("id"));
		            logger.info("["+logid+"] CipherDecipherUtil profile_image::"+object.get("profile_image"));

		          }
		          catch (Exception e) {
		            e.printStackTrace();
		          }
				
		       //log Controller execute time end
		      	long t2 = System.currentTimeMillis();
		      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
		      	
		        return object;
		    }
		    
		    /**
		     * 고객정보 수정처리
		     *
		     * @param UserManageVO
		     * @param request
		     * @param response
		     * @param model
		     * @param locale
		     * @return
		     * @throws BizException
		     */
		    @RequestMapping(value = "/common/naverlink", method = RequestMethod.POST)
		    public @ResponseBody
		    String naverLink(@ModelAttribute("customerVO") CustomerVO customerVO, 
		    		          HttpServletRequest request, 
		    		          HttpServletResponse response) throws BizException
		    {
		    	//log Controller execute time start
				String logid=logid();
				long t1 = System.currentTimeMillis();
				logger.info("["+logid+"] Controller start : customerVO" + customerVO);
				
				 String linkResult="";
				 int retVal=0;
				 
				 String socialId2=customerVO.getSocialId2();
				 
			     //고객키 조회
				 customerVO.setInCustomerPw(customerVO.getSbPw());
				 
				 customerVO=customerSvc.getCustKeyInfo(customerVO);
   
			     if(customerVO==null){
			        	
			    	linkResult="00";//고객정보 미등록
			    	 
					//log Controller execute time end
			       	long t2 = System.currentTimeMillis();
			       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

			        return linkResult;
			     }
			     
			     if(customerVO.getUsedYn().equals("N")){
			    	 
			    	 linkResult="01";//고객정보 비활성화
			    	 
			     
			     }else{
			    	 
			    	//패스워드 체크
					if(!customerVO.getCustomerPw().equals(customerVO.getInCustomerPw())){
						
						linkResult="02";//패스워드 오류
						
					}else{
					 
				    	 customerVO.setSocialId2(socialId2);
				    	 retVal=this.customerSvc.socialId2UpdateProc(customerVO);
				    	 
				    	 if(retVal<=0){
				    		 linkResult="03";//고객정보 연동실패
				    	 }else{
				    		 linkResult=customerVO.getCustomerKey();//고객정보 연동성공
				    	 }
				    	 
					}
			    	 
			     }

				//log Controller execute time end
		       	long t2 = System.currentTimeMillis();
		       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

		        return linkResult;
		    }
		    
		    /* RestFull 정보받기
		     *
		     * @param request
		     * @param response
		     * @param model
		     * @param locale
		     * @return
		     * @throws BizException
		     */
		    @RequestMapping({"/common/restnavercagegory"})
		    public @ResponseBody
		    JSONArray restNaverCagegory(String list_category_api_uri,
		    		String access_token,
		            HttpServletRequest request, 
		            HttpServletResponse response) throws BizException
		    {
		        
		    	//log Controller execute time start
				String logid=logid();
				long t1 = System.currentTimeMillis();
				
				logger.info("["+logid+"] Controller start list_category_api_uri "+list_category_api_uri);
				logger.info("["+logid+"] Controller start access_token "+access_token);
				 JSONArray array =null;
				 JSONObject object =new JSONObject();
				 JSONObject object2 =new JSONObject();
				 String inputLine = null;
				 String content = "";

			    try{

		            BufferedReader input = null;
		
		            URL url = new URL(list_category_api_uri);
		            
		            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		           
		            connection.setRequestProperty("Authorization",access_token);

			    	connection.setDoOutput(true);
			    	
			    	input= new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

		            while ((inputLine = input.readLine()) != null) {
		            	
		            	 content += inputLine;
		            }
		            
		            input.close();

		            logger.info("["+logid+"] content::"+content);
		            
		            Object obj = JSONValue.parse(content);
		            
		            logger.info("["+logid+"] ####################################################################################");

		            object = (JSONObject)obj;

		            logger.info("["+logid+"] message::"+object.get("message"));

		            Object obj2 = JSONValue.parse(object.get("message").toString()); 
		            object2 = (JSONObject)obj2;
		            logger.info("["+logid+"] object2::"+object2);
		            logger.info("["+logid+"] result::"+object2.get("result"));

		            Object obj3 = JSONValue.parse(object2.get("result").toString()); 

		            array = (JSONArray)obj3;
		            this.logger.debug("array ==>" + array);
		            List jasonList = new ArrayList();

		            Object arryObj = null;

		            for (int i = 0; i < array.size(); i++)
		            {
		              arryObj = JSONValue.parse(array.get(i).toString());
		              JSONObject arryObject = (JSONObject)arryObj;
		              jasonList.add(arryObject);
		            }


		          }
		          catch (Exception e) {
		            e.printStackTrace();
		          }
				
		       //log Controller execute time end
		      	long t2 = System.currentTimeMillis();
		      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
		      	
		        return array;
		    }
		    
		    
		    /* RestFull 정보받기
		     *
		     * @param request
		     * @param response
		     * @param model
		     * @param locale
		     * @return
		     * @throws BizException
		     */
		    @RequestMapping({"/common/restnaverpost2"})
		    public @ResponseBody
		    JSONObject restNaverPost2(String write_post_api_uri,
		    		String access_token,
		            HttpServletRequest request, 
		            HttpServletResponse response) throws BizException
		    {
		        
		    	//log Controller execute time start
				String logid=logid();
				long t1 = System.currentTimeMillis();
				
				logger.info("["+logid+"] Controller start write_post_api_uri "+write_post_api_uri);
				logger.info("["+logid+"] Controller start access_token "+access_token);
				
				/*
				 JSONArray array =null;
				 JSONObject object =new JSONObject();
				 JSONObject object2 =new JSONObject();
				 String inputLine = null;
				 String content = "";

			    try{

		            BufferedReader input = null;
		
		            URL url = new URL(write_post_api_uri);
		            
		            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		           
		            connection.setRequestProperty("Authorization",access_token);

			    	connection.setDoOutput(true);
			    	
			    	input= new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

		            while ((inputLine = input.readLine()) != null) {
		            	
		            	 content += inputLine;
		            }
		            
		            input.close();

		            logger.info("["+logid+"] content::"+content);
		            
		           

		          }
		          catch (Exception e) {
		            e.printStackTrace();
		          }
	*/

				 JSONObject object =null;
				 String inputLine = null;
				 String resultcontent = "";
				 
				 String CRLF = "\r\n";
			     String TWO_HYPHENS = "--";
			     String BOUNDARY = "---------------------------012345678901234567890123456";
			     HttpsURLConnection conn = null;
			     DataOutputStream dos = null;
			     FileInputStream fis = null;
	     
		         int bytesRead, bytesAvailable, bufferSize;
		         byte[] buffer;
		         int MAX_BUFFER_SIZE = 1 * 1024 * 1024;
		         
		         String image_url_list="";

			    try{

		            BufferedReader input = null;
		           
		            URL mUrl = new URL(write_post_api_uri);
		            
		            conn = (HttpsURLConnection)mUrl.openConnection();
		            
		            conn.setDoInput(true);
		            conn.setDoOutput(true);
		            conn.setUseCaches(false);
		            conn.setRequestMethod("POST");

		            conn.setRequestProperty("Connection", "Keep-Alive");
		            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + 
		                                      BOUNDARY);
		            conn.setRequestProperty("Authorization", access_token);
		            conn.setRequestProperty("Cache-Control", "no-cache");
		       
		            dos = new DataOutputStream(conn.getOutputStream());
		            
		            Map<String, String> paramMap;
			        
			        // 포스팅 공통 파라미터. 필요한 것만 선택하여 사용.
			        paramMap = new HashMap<String, String>();
			        paramMap.put("title", "SALESBTITLE"); // 
			        paramMap.put("contents", "SALESBARONTEST2SALESBARONTEST3");
			       
		            KakaoRestApiHelper apiHelper = new KakaoRestApiHelper();
		            String params=apiHelper.mapToParams(paramMap);
		            logger.info("["+params+"]params" );
		            //dos.writeBytes(params);
		         
		            ResourceBundle rb = ResourceBundle.getBundle("config");
		            String uploadFilePath = rb.getString("offact.upload.path") + "goods";
		            
		            //File[] files={new File(uploadFilePath+"/salesbaron.jpg"), new File(uploadFilePath+"/salesbaron.jpg")};
		            File[] files={new File(uploadFilePath+"/salesbaron.jpg")};


		            for (File f : files) {
		                dos.writeBytes(TWO_HYPHENS + BOUNDARY + CRLF);
		                dos.writeBytes("Content-Disposition: form-data; name=\"image\";" +
		                                " filename=\"" + f.getName() + "\"" + CRLF);
		                dos.writeBytes(CRLF);
		                fis = new FileInputStream(f);
		                bytesAvailable = fis.available();
		                bufferSize = Math.min(bytesAvailable, MAX_BUFFER_SIZE);
		                buffer = new byte[bufferSize];
		                bytesRead = fis.read(buffer, 0, bufferSize);
		                
		                while (bytesRead > 0) {
		                    dos.write(buffer, 0, bufferSize);
		                    bytesAvailable = fis.available();
		                    bufferSize = Math.min(bytesAvailable, MAX_BUFFER_SIZE);
		                    bytesRead = fis.read(buffer, 0, bufferSize);
		                }
		                
		                logger.info("["+files.length+"]@" );
		                logger.info("["+f.getName()+"]@" );
		                
		                dos.writeBytes(CRLF);
		            }

		            // finish delimiter
		            dos.writeBytes(TWO_HYPHENS + BOUNDARY + TWO_HYPHENS + CRLF);
		            logger.info("["+logid+"] TWO_HYPHENS "+TWO_HYPHENS + BOUNDARY + TWO_HYPHENS + CRLF);

		            fis.close();
		            dos.flush();
		            dos.close();
	 	    
			    } catch (MalformedURLException ex) {
		            ex.printStackTrace();
		        } catch (IOException ioe) {
		            ioe.printStackTrace();
		        } finally {
		            if (fis != null) try { fis.close(); } catch (IOException ignore) { }
		            if (dos != null) try { dos.close(); } catch (IOException ignore) { }
		        }

		        // Response
		        InputStream inputStream = null;
		        BufferedReader reader = null;
		        try {
		            inputStream = new BufferedInputStream(conn.getInputStream());
		            reader = new BufferedReader(new InputStreamReader(inputStream));
		            String line;
		            StringBuilder builder = new StringBuilder();
		            while ((line = reader.readLine()) != null) {
		                builder.append(line).append("\n");
		                logger.info("[line] "+line);
		            }
		            image_url_list=line;
		            logger.info("[builder] "+builder);
	 
		            reader.close();
		            
		        } catch (IOException e) {
		            e.printStackTrace();
		        } finally {
		            if (reader != null) {
		                try { reader.close(); } catch (IOException ignore) {}
		            }
		            if (inputStream != null) {
		                try { inputStream.close(); } catch (IOException ignore) {}
		            }
		            conn.disconnect();
		        }	 

		       //log Controller execute time end
		      	long t2 = System.currentTimeMillis();
		      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
		      	
		        return object;
		    }
		    
		    /* RestFull 정보받기
		     *
		     * @param request
		     * @param response
		     * @param model
		     * @param locale
		     * @return
		     * @throws BizException
		     */
		    @RequestMapping({"/common/restnaverpost"})
		    public @ResponseBody
		    JSONObject restNaverPost(String write_post_api_uri,
		    		String access_token,
		            HttpServletRequest request, 
		            HttpServletResponse response) throws BizException
		    {
		        
		    	//log Controller execute time start
				String logid=logid();
				long t1 = System.currentTimeMillis();
				
				logger.info("["+logid+"] Controller start write_post_api_uri "+write_post_api_uri);
				logger.info("["+logid+"] Controller start access_token "+access_token);

				 JSONObject object =null;
				 String inputLine = null;
				 String resultcontent = "";
				 
				 String CRLF = "\r\n";
			     String TWO_HYPHENS = "--";
			     String BOUNDARY = "---------------------------012345678901234567890123456";
			     HttpsURLConnection conn = null;
			     DataOutputStream dos = null;
			     FileInputStream fis = null;
	     
		         int bytesRead, bytesAvailable, bufferSize;
		         byte[] buffer;
		         int MAX_BUFFER_SIZE = 1 * 1024 * 1024;
		         
		         String image_url_list="";

			    try{

		            BufferedReader input = null;
		           
		            URL mUrl = new URL(write_post_api_uri);
		            
		            conn = (HttpsURLConnection)mUrl.openConnection();
		            
		            conn.setDoInput(true);
		            conn.setDoOutput(true);
		            conn.setUseCaches(false);
		            conn.setRequestMethod("POST");

		            conn.setRequestProperty("Connection", "Keep-Alive");
		            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + 
		                                      BOUNDARY);
		            conn.setRequestProperty("Authorization", access_token);
		            conn.setRequestProperty("Cache-Control", "no-cache");
		       
		            dos = new DataOutputStream(conn.getOutputStream());
		            
		            Map<String, String> paramMap;
			        
			        // 포스팅 공통 파라미터. 필요한 것만 선택하여 사용.
			        paramMap = new HashMap<String, String>();
			        paramMap.put("title", "SALESBTITLE"); // 
			        paramMap.put("contents", "SALESBARONTEST2SALESBARONTEST3");
			       
		            KakaoRestApiHelper apiHelper = new KakaoRestApiHelper();
		            String params=apiHelper.mapToParams(paramMap);
		            logger.info("["+params+"]params" );
		            //dos.writeBytes(params);
		         
		            ResourceBundle rb = ResourceBundle.getBundle("config");
		            String uploadFilePath = rb.getString("offact.upload.path") + "goods";
		            
		            //File[] files={new File(uploadFilePath+"/salesbaron.jpg"), new File(uploadFilePath+"/salesbaron.jpg")};
		            File[] files={new File(uploadFilePath+"/salesbaron.jpg")};


		            for (File f : files) {
		                dos.writeBytes(TWO_HYPHENS + BOUNDARY + CRLF);
		                dos.writeBytes("Content-Disposition: form-data; name=\"image\";" +
		                                " filename=\"" + f.getName() + "\"" + CRLF);
		                dos.writeBytes(CRLF);
		                fis = new FileInputStream(f);
		                bytesAvailable = fis.available();
		                bufferSize = Math.min(bytesAvailable, MAX_BUFFER_SIZE);
		                buffer = new byte[bufferSize];
		                bytesRead = fis.read(buffer, 0, bufferSize);
		                
		                while (bytesRead > 0) {
		                    dos.write(buffer, 0, bufferSize);
		                    bytesAvailable = fis.available();
		                    bufferSize = Math.min(bytesAvailable, MAX_BUFFER_SIZE);
		                    bytesRead = fis.read(buffer, 0, bufferSize);
		                }
		                
		                logger.info("["+files.length+"]@" );
		                logger.info("["+f.getName()+"]@" );
		                
		                dos.writeBytes(CRLF);
		            }

		            // finish delimiter
		            dos.writeBytes(TWO_HYPHENS + BOUNDARY + TWO_HYPHENS + CRLF);
		            logger.info("["+logid+"] TWO_HYPHENS "+TWO_HYPHENS + BOUNDARY + TWO_HYPHENS + CRLF);

		            fis.close();
		            dos.flush();
		            dos.close();
	 	    
			    } catch (MalformedURLException ex) {
		            ex.printStackTrace();
		        } catch (IOException ioe) {
		            ioe.printStackTrace();
		        } finally {
		            if (fis != null) try { fis.close(); } catch (IOException ignore) { }
		            if (dos != null) try { dos.close(); } catch (IOException ignore) { }
		        }

		        // Response
		        InputStream inputStream = null;
		        BufferedReader reader = null;
		        try {
		            inputStream = new BufferedInputStream(conn.getInputStream());
		            reader = new BufferedReader(new InputStreamReader(inputStream));
		            String line;
		            StringBuilder builder = new StringBuilder();
		            while ((line = reader.readLine()) != null) {
		                builder.append(line).append("\n");
		                logger.info("[line] "+line);
		            }
		            image_url_list=line;
		            logger.info("[builder] "+builder);
	 
		            reader.close();
		            
		        } catch (IOException e) {
		            e.printStackTrace();
		        } finally {
		            if (reader != null) {
		                try { reader.close(); } catch (IOException ignore) {}
		            }
		            if (inputStream != null) {
		                try { inputStream.close(); } catch (IOException ignore) {}
		            }
		            conn.disconnect();
		        }	 

		       //log Controller execute time end
		      	long t2 = System.currentTimeMillis();
		      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
		      	
		        return object;
		    }
		    

}
