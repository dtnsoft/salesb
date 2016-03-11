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
    @Value("#{config['oauth.facebook.fbAppId']}")
    private String facebookfbAppId;
    
    @Value("#{config['oauth.kakao.client_id']}")
    private String kakaoclient_id;
    
    @Value("#{config['oauth.redirect.url']}")
    private String redirectUrl;
    
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
    public ModelAndView goodsMakeForm(String tokenKey,
    		                          HttpServletRequest request, 
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
        
        TokenVO token = new TokenVO();
        
        token.setTokenkey(tokenKey);
        
        token = tokenSvc.getTokenDetail(token);

        mv.addObject("token", token);
		mv.addObject("redirectUrl", redirectUrl);
		mv.addObject("kakaoclient_id", kakaoclient_id);
		mv.addObject("facebookfbAppId", facebookfbAppId);
		
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
    @RequestMapping(value = "/member/goodsmake", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView goodsMake(@ModelAttribute("MultipartFileVO") MultipartFileVO fileVO,
    		                      String tokenkey,
    		                      String image1,
    		                      String image2,
    		                      String image3,
    		                      String image4,
    		                      String image5,
    		                      String productEtc,
		                          HttpServletRequest request, 
		                          HttpServletResponse response,
		                          String fileName, 
		                          String extension) throws BizException 
    {  
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		
		ModelAndView mv = new ModelAndView();
		
		logger.info("["+logid+"] Controller start : fileVO" + fileVO);
		logger.info("["+logid+"] Controller start tokenkey:"+tokenkey);
		logger.info("["+logid+"] Controller start image1:"+image1);
		logger.info("["+logid+"] Controller start image2:"+image2);
		logger.info("["+logid+"] Controller start image3:"+image3);
		logger.info("["+logid+"] Controller start image4:"+image4);
		logger.info("["+logid+"] Controller start image5:"+image5);
		logger.info("["+logid+"] Controller start productEtc:"+productEtc);

        String fname ="";
        
      	// 사용자 세션정보
        HttpSession session = request.getSession();
        
        String customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
        
        TokenVO tokenVO = new TokenVO();
        
        tokenVO.setTokenkey(tokenkey);
        tokenVO.setImage1(image1);
        tokenVO.setImage2(image2);
        tokenVO.setImage3(image3);
        tokenVO.setImage4(image4);
        tokenVO.setImage5(image5);
        tokenVO.setProductEtc(productEtc);
        
        tokenVO.setUpdateUserId(customerKey);
        
        String imagePath="goods/"+strToday+"/";

        ResourceBundle rb = ResourceBundle.getBundle("config");
        String uploadFilePath = rb.getString("offact.upload.path") + imagePath;

        this.logger.debug("파일정보:" + fileName + extension);
        this.logger.debug("file:" + fileVO);

        try {
        
	        if (fileName != null && fileName != "") {
		    	  
		        List<MultipartFile> files = fileVO.getFiles();
		        List fileNames = new ArrayList();
		        String orgFileName = null;
		        String realFileName = null;
		        
		        int fileIndex=1;

		        if ((files != null) && (files.size() > 0))
		        {
		          for (MultipartFile multipartFile : files)
		          {
		            orgFileName = multipartFile.getOriginalFilename();
		            this.logger.debug("orgFileName  :" + orgFileName);
		            realFileName = t1 + fileIndex +"."+ extension;
		            this.logger.debug("realFileName  :" + realFileName);
		            
		            if(!orgFileName.equals("")){
 
			            boolean check=setDirectory(uploadFilePath);
	
			            String filePath = uploadFilePath;
	
			            File file = new File(filePath + realFileName);
			            multipartFile.transferTo(file);
			            fileNames.add(realFileName);
			            
			            if(fileIndex==1){
			            	tokenVO.setImage1(hostUrl+"/upload/"+imagePath+realFileName);
			            }else if (fileIndex==2){
			            	tokenVO.setImage2(hostUrl+"/upload/"+imagePath+realFileName);
			            }else if (fileIndex==3){
			            	tokenVO.setImage3(hostUrl+"/upload/"+imagePath+realFileName);
			            }else if (fileIndex==4){
			            	tokenVO.setImage4(hostUrl+"/upload/"+imagePath+realFileName);
			            }else if (fileIndex==5){
			            	tokenVO.setImage5(hostUrl+"/upload/"+imagePath+realFileName);
			            }

		            }
		            
		            fileIndex++;
		            
		          }
		     
		        }

	        }
        }catch (Exception e){
        	
        	logger.info("["+logid+"][error] : "+e.getMessage()); 
        	
        }
        
        this.logger.debug("getImage1  :" + tokenVO.getImage1());
        this.logger.debug("getImage2  :" + tokenVO.getImage2());
        this.logger.debug("getImage3  :" + tokenVO.getImage3());
        this.logger.debug("getImage4  :" + tokenVO.getImage4());
        this.logger.debug("getImage5  :" + tokenVO.getImage5());
        
        int retVal= tokenSvc.tokenUpdateProc(tokenVO);
        
        mv.addObject("tokenVO", tokenVO);
		
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
