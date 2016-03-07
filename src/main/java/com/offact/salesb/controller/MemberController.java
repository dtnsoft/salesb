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
import com.offact.salesb.service.common.CommonService;
import com.offact.salesb.service.common.MailService;
import com.offact.salesb.service.business.ProductService;
import com.offact.salesb.service.member.TokenService;
import com.offact.salesb.vo.CustomerVO;
import com.offact.salesb.vo.MultipartFileVO;
import com.offact.salesb.vo.common.EmailVO;
import com.offact.salesb.vo.common.SmsVO;
import com.offact.salesb.vo.common.WorkVO;
import com.offact.salesb.vo.business.ProductMasterVO;
import com.offact.salesb.vo.member.TokenVO;
import com.offact.salesb.vo.manage.UserManageVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class MemberController {

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
    private MailService mailSvc;
    
    @Autowired
    private CommonService commonSvc;
    
    @Autowired
    private TokenService tokenSvc;

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
    @RequestMapping(value = "/member/mytokenmanage")
    public ModelAndView myTokenManage(HttpServletRequest request, 
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

 	       	mv.setViewName("/common/customerLoginForm");
        	return mv;
		}
        
        mv.setViewName("/member/myTokenManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    
    /**
     * 나의 토근 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/member/mytokenlist")
    public ModelAndView myTokenList(@ModelAttribute("tokenConVO") TokenVO tokenConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : tokenConVO" + tokenConVO);

        ModelAndView mv = new ModelAndView();

     // 사용자 세션정보
        HttpSession session = request.getSession();
        
        String customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
        String customerName = StringUtil.nvl((String) session.getAttribute("customerName")); 
        String customerId = StringUtil.nvl((String) session.getAttribute("customerId"));
        String groupId = StringUtil.nvl((String) session.getAttribute("groupId"));
        
        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

 	       	mv.setViewName("/common/customerLoginForm");
        	return mv;
		}
        
        List<TokenVO> tokenList = null;
        
        tokenConVO.setCustomerKey(customerKey);
        tokenConVO.setGroupId(groupId);
        
        // 조회조건저장
        mv.addObject("tokenConVO", tokenConVO);

        // 페이징코드
        tokenConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(tokenConVO.getCurPage(), tokenConVO.getRowCount()));
        tokenConVO.setPage_limit_val2(StringUtil.nvl(tokenConVO.getRowCount(), "10"));
        
        // 사용자목록조회
        tokenList = tokenSvc.getTokenPageList(tokenConVO);
        mv.addObject("tokenList", tokenList);

        // totalCount 조회
        String totalCount ="0";// String.valueOf(asSvc.getAsCnt(asConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("member/myTokenList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    
    /**
     * 상품제작
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/member/goodsmakeform")
    public ModelAndView goodsMakeForm(HttpServletRequest request, 
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

 	       	mv.setViewName("/common/customerLoginForm");

        	return mv;
		}
        
        mv.setViewName("/member/goodsMakeForm");
        
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
    @RequestMapping(value = "/member/goodsmake")
    public ModelAndView goodsMake(@ModelAttribute("MultipartFileVO") MultipartFileVO fileVO,
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
        
        
        TokenVO tokenVO = new TokenVO();
        tokenVO.setCreateUserId(customerKey);
        
        String imagePath="member/"+strToday+"/";

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
		            
		            tokenVO.setImage1(hostUrl+"/upload/"+imagePath+orgFileName);
		         		   
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
        
        int retVal= 0;//this.comunitySvc.commentInsert(comunityVO);
        
        mv.setViewName("/member/fileResult");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
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
     * 이미지보기
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/member/imageview")
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

        mv.setViewName("/member/imageView");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    
   
}
