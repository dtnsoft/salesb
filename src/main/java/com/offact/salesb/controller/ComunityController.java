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
import com.offact.salesb.vo.common.SmsVO;
import com.offact.salesb.vo.comunity.AsVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class ComunityController {

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
	     * 글올리기
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/comunity/commentregistform")
	    public ModelAndView commentRegistForm(HttpServletRequest request, 
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
	        String staffYn = StringUtil.nvl((String) session.getAttribute("staffYn"));
	        
	        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

	 	       	mv.setViewName("/common/sessionOut");
	       		return mv;
			}

	        mv.setViewName("/comunity/commentRegistForm");
	        
	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return mv;
	    }

	    
			/**
		     * 글올리기
		     *
		     * @param request
		     * @param response
		     * @param model
		     * @param locale
		     * @return
		     * @throws BizException
		     */
		    @RequestMapping(value = "/comunity/comunityregist")
		    public ModelAndView comunityRegist(@ModelAttribute("MultipartFileVO") MultipartFileVO fileVO,
		    		                           HttpServletRequest request, 
		    		                           HttpServletResponse response,
		    		                           String fileName, 
		    		                           String extension, 
				                               String comment) throws BizException 
		    {
		        
		    	//log Controller execute time start
				String logid=logid();
				long t1 = System.currentTimeMillis();
				logger.info("["+logid+"] Controller start comment:"+comment);
				logger.info("["+logid+"] Controller start : fileVO" + fileVO);
		
		        ModelAndView mv = new ModelAndView();
		        
		        String fname ="";
		        
		      	// 사용자 세션정보
		        HttpSession session = request.getSession();
		        
		        String customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
		        String customerName = StringUtil.nvl((String) session.getAttribute("customerName")); 
		        String customerId = StringUtil.nvl((String) session.getAttribute("customerId"));
		        String groupId = StringUtil.nvl((String) session.getAttribute("groupId"));
		        String staffYn = StringUtil.nvl((String) session.getAttribute("staffYn"));
		        
		        //오늘 날짜
		        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
		        Date currentTime = new Date();
		        String strToday = simpleDateFormat.format(currentTime);
		        
		        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

		 	       	mv.setViewName("/common/sessionOut");
		       		return mv;
				}
		        
		        
		        ComunityVO comunityVO = new ComunityVO();
		        comunityVO.setCustomerKey(customerKey);
		        comunityVO.setCustomerId(customerId);
		        comunityVO.setGroupId(groupId);
		        comunityVO.setComment(comment);
		        comunityVO.setCommentType(staffYn);
		        
		        String imagePath="comunity/"+strToday+"/";

		        ResourceBundle rb = ResourceBundle.getBundle("config");
		        String uploadFilePath = rb.getString("offact.upload.path") + imagePath;

		        this.logger.debug("파일정보:" + fileName + extension);
		        this.logger.debug("file:" + fileVO);

		        try {
		        
			        if (fileName != null && fileName != "") {
				    	  
				        List<MultipartFile> files = fileVO.getFiles();
				        List fileNames = new ArrayList();
				        String orgFileName = null;
	
				        if ((files != null) && (files.size() > 0))
				        {
				          for (MultipartFile multipartFile : files)
				          {
				            orgFileName = multipartFile.getOriginalFilename();
				            this.logger.debug("orgFileName 1 :" + orgFileName);
				            orgFileName = t1 +"."+ extension;
				            this.logger.debug("orgFileName 2 :" + orgFileName);
				            
				            /*
				            String br="";
				            
				            if(!comment.equals("")){
				            	br="<br>";
				            }
				            
				            String imageAttach=br+"<img src='"+hostUrl+"/upload/"+imagePath+orgFileName+"' id='I"+orgFileName+"' /><script>"
				            		+ "if(document.all('I"+orgFileName+"').width>300){"
				            		+ "document.all('I"+orgFileName+"').width=300"
				            		+ "};</script>";
				            */
				            
				            comunityVO.setCommentImage(hostUrl+"/upload/"+imagePath+orgFileName);
				         		   
				            boolean check=setDirectory(uploadFilePath);
	
				            String filePath = uploadFilePath;
	
				            File file = new File(filePath + orgFileName);
				            multipartFile.transferTo(file);
				            fileNames.add(orgFileName);
				          }
				     
				        }
				        
				        fname = uploadFilePath + orgFileName;
	
			        }
		        }catch (Exception e){
		        	
		        	logger.info("["+logid+"][error] : "+e.getMessage()); 
		        	
		        }
		        
		        int retVal=this.comunitySvc.commentInsert(comunityVO);
		        
		        mv.setViewName("/comunity/fileResult");
		        
		       //log Controller execute time end
		      	long t2 = System.currentTimeMillis();
		      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
		      	
		        return mv;
		    }

	    /**
	     * 글올리기
	     * @param UserManageVO
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/comunity/commentregist", method = RequestMethod.POST)
	    public @ResponseBody
	    String commentRegist(@ModelAttribute("comunityVO") ComunityVO comunityVO, 
	    		          HttpServletRequest request, 
	    		          HttpServletResponse response) throws BizException
	    {
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start : ComunityVO" + comunityVO);
			
			int retVal=this.comunitySvc.commentInsert(comunityVO);
			
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

	      return ""+retVal;
	    }
	  

	    /**
	     * 상담등록
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/comunity/counselregistform")
	    public ModelAndView counselRegistForm(HttpServletRequest request, 
	    		                       HttpServletResponse response,
			                           String curPage,
			                           String groupId) throws BizException 
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start groupId:"+groupId);
	
	        ModelAndView mv = new ModelAndView();
	        
	      	// 사용자 세션정보
	        HttpSession session = request.getSession();
	        
	        String customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
	        String customerName = StringUtil.nvl((String) session.getAttribute("customerName")); 
	        String customerId = StringUtil.nvl((String) session.getAttribute("customerId"));
	        
	        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

	        	mv.setViewName("/common/sessionOut");
	       		return mv;
			}
	
	        mv.addObject("curPage",curPage);
	        mv.addObject("groupId",groupId);
	        mv.setViewName("/comunity/counselRegistForm");
	        
	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return mv;
	    }
    
	    /**
	     * 상담하기
	     * @param UserManageVO
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/comunity/counselregist", method = RequestMethod.POST)
	    public @ResponseBody
	    String counselRegist(@ModelAttribute("counselVO") CounselVO counselVO, 
	    		          HttpServletRequest request, 
	    		          HttpServletResponse response) throws BizException
	    {
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start : counselVO" + counselVO);
			
			int retVal=this.comunitySvc.counselInsert(counselVO);
			
			//이메일 리스틑 조회 user
			String emaillist="dev@salesb.co.kr;kevin.jeon@offact.com";
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
			mail.setMsg(" 1:1 문의내역입니다.<br>"+counselVO.getCounsel()+"<br>고객핸드폰번호:"+counselVO.getCustomerKey()+"<br><br><br>*1:1문의 답변은 아래 시스템에서 답변 가능하십니다.<br><a href='"+hostUrl+"/addon/smart/counselmanage' >1:1답변하려가기</a>");
			
			
			mail.setSubject("[애디스]1:1상담문의");
			
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
	     * 상담하기
	     * @param UserManageVO
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/comunity/counselmultiregist")
	    public @ResponseBody
	    ModelAndView counselMultiRegist(@ModelAttribute("MultipartFileVO") MultipartFileVO fileVO,
                 				  HttpServletRequest request, 
                 				  HttpServletResponse response,
                 				  String fileName, 
                 				  String extension,
                 				  String counsel) throws BizException
	    {
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start : counsel" + counsel);
			
			ModelAndView mv = new ModelAndView();
			
			String fname ="";
		        
	      	// 사용자 세션정보
	        HttpSession session = request.getSession();
	        
	        String customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
	        String customerName = StringUtil.nvl((String) session.getAttribute("customerName")); 
	        String customerId = StringUtil.nvl((String) session.getAttribute("customerId"));
	        String groupId = StringUtil.nvl((String) session.getAttribute("groupId"));
	        String staffYn = StringUtil.nvl((String) session.getAttribute("staffYn"));
	        
	        //오늘 날짜
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
	        Date currentTime = new Date();
	        String strToday = simpleDateFormat.format(currentTime);
	        
	        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

	 	       	mv.setViewName("/common/sessionOut");
	       		return mv;
			}

			CounselVO counselVO = new CounselVO();
			 
			counselVO.setCustomerKey(customerKey);
			counselVO.setGroupId(groupId);
		    counselVO.setCustomerId(customerId);
		    counselVO.setCounselState("01");
		    counselVO.setCounsel(counsel);
		        
		    String imagePath="counsel/"+strToday+"/";

		    ResourceBundle rb = ResourceBundle.getBundle("config");
		    String uploadFilePath = rb.getString("offact.upload.path") + imagePath;

		    this.logger.debug("파일정보:" + fileName + extension);
		    this.logger.debug("file:" + fileVO);

	        try {
	        
		        if (fileName != null && fileName != "") {
			    	  
			        List<MultipartFile> files = fileVO.getCfiles();
			        List fileNames = new ArrayList();
			        String orgFileName = null;

			        if ((files != null) && (files.size() > 0))
			        {
			          for (MultipartFile multipartFile : files)
			          {
			            orgFileName = multipartFile.getOriginalFilename();
			            this.logger.debug("orgFileName 1 :" + orgFileName);
			            orgFileName = t1 +"."+ extension;
			            this.logger.debug("orgFileName 2 :" + orgFileName);

			            counselVO.setCounselImage(hostUrl+"/upload/"+imagePath+orgFileName);
			         		   
			            boolean check=setDirectory(uploadFilePath);

			            String filePath = uploadFilePath;

			            File file = new File(filePath + orgFileName);
			            multipartFile.transferTo(file);
			            fileNames.add(orgFileName);
			          }
			     
			        }
			        
			        fname = uploadFilePath + orgFileName;

		        }
	        }catch (Exception e){
	        	
	        	logger.info("["+logid+"][error] : "+e.getMessage()); 
	        	
	        }
	        
			int retVal=this.comunitySvc.counselInsert(counselVO);

			//이메일 리스틑 조회 user
			String emaillist="dev@salesb.co.kr;kevin.jeon@offact.com";
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
			mail.setMsg(" 1:1 문의내역입니다.<br>"+counselVO.getCounsel()+"<br>고객핸드폰번호:"+counselVO.getCustomerKey()+"<br><br><br>*1:1문의 답변은 아래 시스템에서 답변 가능하십니다.<br><a href='"+hostUrl+"/addon/smart/counselmanage' >1:1답변하려가기</a>");
			
			
			mail.setSubject("[애디스]1:1상담문의");
			
			boolean counselResult=false;
	
			try{
				
				counselResult=mailSvc.sendMail(mail);
				
				logger.debug("mail result :"+counselResult);
				
				if(counselResult==false){
					
					mv.setViewName("/comunity/counselResult");
				        
			       //log Controller execute time end
			      	long t2 = System.currentTimeMillis();
			      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
			      	
			        return mv;
					
				}
				
			}catch(BizException e){
		       	
		    	e.printStackTrace();
		        String errMsg = e.getMessage();
		        try{errMsg = errMsg.substring(errMsg.lastIndexOf("exception"));}catch(Exception ex){}
				
		        mv.setViewName("/comunity/counselResult");
		        
		       //log Controller execute time end
		      	long t2 = System.currentTimeMillis();
		      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
		      	
		        return mv;
		    	
		    }

			 mv.setViewName("/comunity/counselResult");
		        
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
	    @RequestMapping(value = "/comunity/comunitylist")
	    public ModelAndView comunityList(String customerKey, 
	    								 String groupId, 
	    		                         HttpServletRequest request, 
	    		                         HttpServletResponse response) throws BizException 
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start : customerKey" + customerKey);

			ModelAndView mv = new ModelAndView();

			// 사용자 세션정보
	        HttpSession session = request.getSession();
	        
	        customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
	        String customerName = StringUtil.nvl((String) session.getAttribute("customerName")); 
	        String customerId = StringUtil.nvl((String) session.getAttribute("customerId"));
	        
	        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

	 	       	//mv.setViewName("/common/customerLoginForm");
	        	mv.setViewName("/common/sessionOut");
	        	return mv;
			}
	   		
	        List<ComunityVO> comunityList = new ArrayList();
	        
	        ComunityVO comunityVO = new ComunityVO();
	        comunityVO.setCustomerKey(customerKey);
	        comunityVO.setGroupId(groupId);

	        // 커뮤니티목록조회
	        comunityList = comunitySvc.getComunityListTalk(comunityVO);

	   	    mv.addObject("comunityList", comunityList);
	   	 
	   		mv.setViewName("/comunity/comunityList");
	   		
	   		return mv;
	    }
	    
	    /**
	     * 1:1 목록조회
	     * 
	     * @param UserManageVO
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/comunity/counsellist")
	    public ModelAndView counselList(@ModelAttribute("csConVO") CounselVO csConVO,
	    		                        HttpServletRequest request, 
	    		                        HttpServletResponse response) throws BizException 
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start : groupId" + csConVO);

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
	   		
	        List<CounselVO> counselList = new ArrayList();
	        
	        csConVO.setCustomerKey(customerKey);
	        csConVO.setGroupId(groupId);
	        
	        // 조회조건저장
	        mv.addObject("csConVO", csConVO);

	        // 페이징코드
	        csConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(csConVO.getCurPage(), csConVO.getRowCount()));
	        csConVO.setPage_limit_val2(StringUtil.nvl(csConVO.getRowCount(), "10"));
	        

	        // 커뮤니티목록조회
	        counselList = comunitySvc.getCounselList(csConVO);
	        

	        // totalCount 조회
	        String totalCount = String.valueOf(comunitySvc.getCounselCnt(csConVO));
	        mv.addObject("totalCount", totalCount);

	   	    mv.addObject("counselList", counselList);
	   	 
	   		mv.setViewName("/comunity/counselList");
	   		
	   		return mv;
	    }
	    
	    /**
	     * 1:1 목록조회
	     * 
	     * @param UserManageVO
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/comunity/replylist")
	    public ModelAndView replyList(String idx, 
	    		                         HttpServletRequest request, 
	    		                         HttpServletResponse response) throws BizException 
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start : idx" + idx);

			ModelAndView mv = new ModelAndView();
			
			// 사용자 세션정보
	        HttpSession session = request.getSession();
	        
	        String customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
	        String customerName = StringUtil.nvl((String) session.getAttribute("customerName")); 
	        String customerId = StringUtil.nvl((String) session.getAttribute("customerId"));
	        
	        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

	        	mv.setViewName("/common/sessionOut");
	       		return mv;
			}
	   		
			List<ComunityVO> comunityReply = new ArrayList();
			
			 ComunityVO comunityVO = new ComunityVO();
		     comunityVO.setUpidx(idx);

	        //품목 비고 정보
	        comunityReply=comunitySvc.getComunityReply(comunityVO);

	   	    mv.addObject("comunityReply", comunityReply);
	   	 
	   		mv.setViewName("/comunity/replyList");
	   		
	   		return mv;
	    }
	    
		/**
		 * 업로드 디렉토리 세팅
		 */
		private static boolean setDirectory( String directory) {
			File wantedDirectory = new File(directory);
			if (wantedDirectory.isDirectory())
				return true;
		    
			return wantedDirectory.mkdirs();
		}
	    
		/**
	     * 글올리기
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/comunity/imageview")
	    public ModelAndView imageView(HttpServletRequest request, 
	    		                      HttpServletResponse response,
	    		                      String imageurl) throws BizException 
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start imageView");
	
	        ModelAndView mv = new ModelAndView();
	        
	      	// 사용자 세션정보
	        HttpSession session = request.getSession();
	        
	        String customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
	        String customerName = StringUtil.nvl((String) session.getAttribute("customerName")); 
	        String customerId = StringUtil.nvl((String) session.getAttribute("customerId"));
	        String staffYn = StringUtil.nvl((String) session.getAttribute("staffYn"));
	        
	        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

	 	       	mv.setViewName("/common/sessionOut");
	       		return mv;
			}
	        
	        mv.addObject("imageurl", imageurl);

	        mv.setViewName("/comunity/imageView");
	        
	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return mv;
	    }
	    
		/**
	     * 답변보기
	     *
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/comunity/counseldetail")
	    public ModelAndView counselDetail(HttpServletRequest request, 
	    		                      HttpServletResponse response,
	    		                      String idx,
	    		                      String counselResult,
	    		                      String userName,
	    		                      String counsel,
	    		                      String curPage,
	    		                      String groupId,
	    		                      String counselImage,
	    		                      String counselResultImage) throws BizException 
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start idx"+idx);
			logger.info("["+logid+"] Controller start counselResult"+counselResult);
			logger.info("["+logid+"] Controller start userName"+userName);
	
	        ModelAndView mv = new ModelAndView();
	        
	      	// 사용자 세션정보
	        HttpSession session = request.getSession();
	        
	        String customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
	        String customerName = StringUtil.nvl((String) session.getAttribute("customerName")); 
	        String customerId = StringUtil.nvl((String) session.getAttribute("customerId"));
	        String staffYn = StringUtil.nvl((String) session.getAttribute("staffYn"));
	        
	        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

	 	       	mv.setViewName("/common/sessionOut");
	       		return mv;
			}
	        
	        mv.addObject("idx", idx);
	        mv.addObject("counselResult", counselResult);
	        mv.addObject("userName", userName);
	        mv.addObject("curPage", curPage);
	        mv.addObject("counsel", counsel);
	        mv.addObject("groupId", groupId);
	        mv.addObject("counselImage", counselImage);
	        mv.addObject("counselResultImage", counselResultImage);
	        
	        mv.setViewName("/comunity/counselDetail");
	        
	       //log Controller execute time end
	      	long t2 = System.currentTimeMillis();
	      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	      	
	        return mv;
	    }
	    
	    /**
	     * 핫딜
	     * 
	     * @param UserManageVO
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/comunity/hotdeal")
	    public ModelAndView hotDeal(HttpServletRequest request, 
	    		                         HttpServletResponse response) throws BizException 
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start : hotDeal");

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

	   		mv.setViewName("/comunity/hotDeal");
	   		
	   		return mv;
	    }
	    
	    /**
	     * 핫딜
	     * 
	     * @param UserManageVO
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/comunity/mhome")
	    public ModelAndView mHome(HttpServletRequest request, 
	    		                         HttpServletResponse response) throws BizException 
	    {
	        
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start : hotDeal");

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

	   		mv.setViewName("/comunity/mHome");
	   		
	   		return mv;
	    }
	    
	    /**
	     * 주문관리 목록조회
	     * 
	     * @param UserManageVO
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/comunity/orderlist")
	    public ModelAndView orderPageList(@ModelAttribute("asConVO") AsVO asConVO, 
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

	        mv.setViewName("/comunity/orderList");
	        
	        //log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	       	
	        return mv;
	    }
	    /**
	     * 판매관리 목록조회
	     * 
	     * @param UserManageVO
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/comunity/saleslist")
	    public ModelAndView salesPageList(@ModelAttribute("asConVO") AsVO asConVO, 
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

	        mv.setViewName("/comunity/salesList");
	        
	        //log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
	       	
	        return mv;
	    }
	    /**
		 * Simply selects the home view to render by returning its name.
		 * @throws BizException
		 */
	    @RequestMapping(value = "/comunity/orderkeydetail")
		public ModelAndView orderKeyDetail(HttpServletRequest request) throws BizException 
	    {
			
			ModelAndView mv = new ModelAndView();

			  // 사용자 세션정보
	        HttpSession session = request.getSession();
	        
	        String customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
	        String customerName = StringUtil.nvl((String) session.getAttribute("customerName")); 
	        String customerId = StringUtil.nvl((String) session.getAttribute("customerId"));
	        
	        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

	 	       	//mv.setViewName("/common/customerLoginForm");
	        	mv.setViewName("/common/sessionOut");
	        	return mv;
			}

	        mv.addObject("customerKey",customerKey);

			mv.setViewName("/comunity/orderKeyDetail");
			return mv;
		}
	    /**
		 * Simply selects the home view to render by returning its name.
		 * @throws BizException
		 */
	    @RequestMapping(value = "/comunity/orderdetail")
		public ModelAndView orderDetail(String asNo,
									 String curPage,
				                     HttpServletRequest request) throws BizException 
	    {
			
			ModelAndView mv = new ModelAndView();
			
			AsVO asVO = new AsVO();
			List<AsVO> asList = null;
			
			  // 사용자 세션정보
	        HttpSession session = request.getSession();
	        
	        String customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
	        String customerName = StringUtil.nvl((String) session.getAttribute("customerName")); 
	        String customerId = StringUtil.nvl((String) session.getAttribute("customerId"));
	        
	        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

	 	       	//mv.setViewName("/common/customerLoginForm");
	        	mv.setViewName("/common/sessionOut");
	        	return mv;
			}
			
	        asVO.setAsNo(asNo);
	        
	        asVO = asSvc.getAsDetail(asVO);
	        
	        asList = asSvc.getAsHistory(asVO);
	        
	        mv.addObject("curPage",curPage);
			mv.addObject("asVO", asVO);
			mv.addObject("asList", asList);

			mv.setViewName("/comunity/orderDetail");
			return mv;
		}
	    /**
		 * Simply selects the home view to render by returning its name.
		 * @throws BizException
		 */
	    @RequestMapping(value = "/comunity/salesdetail")
		public ModelAndView salesDetail(String asNo,
									 String curPage,
				                     HttpServletRequest request) throws BizException 
	    {
			
			ModelAndView mv = new ModelAndView();
			
			AsVO asVO = new AsVO();
			List<AsVO> asList = null;
			
			  // 사용자 세션정보
	        HttpSession session = request.getSession();
	        
	        String customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
	        String customerName = StringUtil.nvl((String) session.getAttribute("customerName")); 
	        String customerId = StringUtil.nvl((String) session.getAttribute("customerId"));
	        
	        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

	 	       	//mv.setViewName("/common/customerLoginForm");
	        	mv.setViewName("/common/sessionOut");
	        	return mv;
			}
			
	        asVO.setAsNo(asNo);
	        
	        asVO = asSvc.getAsDetail(asVO);
	        
	        asList = asSvc.getAsHistory(asVO);
	        
	        mv.addObject("curPage",curPage);
			mv.addObject("asVO", asVO);
			mv.addObject("asList", asList);

			mv.setViewName("/comunity/salesDetail");
			return mv;
		}
	    /**
	     * AS상태변경
	     *
	     * @param UserManageVO
	     * @param request
	     * @param response
	     * @param model
	     * @param locale
	     * @return
	     * @throws BizException
	     */
	    @RequestMapping(value = "/comunity/asstateprocess", method = RequestMethod.POST)
	    public @ResponseBody
	    String asStateProcess(String asNo,
	    		              String asState,
	    					  String asSubState,
	    					  String asHistory,
	    		              HttpServletRequest request, 
	    		              HttpServletResponse response) throws BizException
	    {
	    	//log Controller execute time start
			String logid=logid();
			long t1 = System.currentTimeMillis();
			logger.info("["+logid+"] Controller start : asNo" + asNo);


	        AsVO asVO = new AsVO();
	        
	        asVO.setAsNo(asNo);
	        asVO.setAsState(asState);
	        asVO.setAsSubState(asSubState);
	        asVO.setAsHistory(asHistory);
	        
	        int retVal=this.asSvc.asStateProc(asVO);

	        //SMS발송
	        
			//작업이력
	        /*
			WorkVO work = new WorkVO();
			work.setWorkUserId(strUserId);
			work.setWorkCategory("MU");
			work.setWorkCode("MU001");
			commonSvc.regiHistoryInsert(work);
			*/
	        
			//log Controller execute time end
	       	long t2 = System.currentTimeMillis();
	       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

	      return ""+retVal;
	    }
}
