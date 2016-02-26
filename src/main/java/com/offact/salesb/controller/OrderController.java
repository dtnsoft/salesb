package com.offact.salesb.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

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

import com.offact.framework.util.CipherDecipherUtil;
import com.offact.framework.constants.CodeConstant;
import com.offact.framework.exception.BizException;
import com.offact.framework.jsonrpc.JSONRpcService;
import com.offact.framework.util.StringUtil;
import com.offact.salesb.service.UserMenuService;
import com.offact.salesb.service.common.MailService;
import com.offact.salesb.vo.CustomerVO;
import com.offact.salesb.vo.UserMenuVO;
import com.offact.salesb.vo.common.EmailVO;
import com.offact.common.JSONDataParser;

/**
 * Handles requests for the application home page.
 */
@Controller

public class OrderController {

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
    private MailService mailSvc;
	
	/**
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/order/ordertest")
    public ModelAndView orderTest(HttpServletRequest request, 
    		                      HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start orderTest");

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
        
        String keyvalue="";
        
        String encryptValue = "";

        String encrypt = "";

        encryptValue="01067471995|9999999999|465834833";
        
        encrypt = CipherDecipherUtil.encrypt(encryptValue, "We are sales and livingsocials !");
        
        logger.info("["+logid+"] CipherDecipherUtil encrypt::"+encrypt);
        
        keyvalue = CipherDecipherUtil.decrypt(encrypt, "We are sales and livingsocials !");

        
        logger.info("["+logid+"] CipherDecipherUtil decrypt::"+keyvalue);
        
        mv.addObject("keyvalue", encrypt);

        mv.setViewName("/order/orderTest");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }

    /**
     * pay app 결과
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/order/payapporderresult")
    public ModelAndView payAppOrderResult(HttpServletRequest request, 
    		                       HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();

        ModelAndView mv = new ModelAndView();

        mv.setViewName("/order/payAppOrderResult");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * paypal 결과페이지
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/order/paypalorderresult")
    public ModelAndView paypalOrderResult(HttpServletRequest request, 
    		                       HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();

        ModelAndView mv = new ModelAndView();

        mv.addObject("hostUrl", hostUrl);
		mv.addObject("domainUrl", domainUrl);
		
        mv.setViewName("/order/paypalOrderResult");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    
    /**
     * 페이팔 주문결과받기
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/order/orderurl"})
    public @ResponseBody
    Map<Object, Object> orderUrl(String orderurl,
            HttpServletRequest request, 
            HttpServletResponse response) throws BizException
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		
		logger.info("["+logid+"] Controller start orderurl="+orderurl);

        String retVal=orderurl;
        
        String inputLine = null;
	    StringBuilder responseResult = new StringBuilder();
	    Map rtnMap = new HashMap();
	    List rtnSuccessList = new ArrayList();

		try{
 
	        URL url = new URL(orderurl);

			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.addRequestProperty("Content-Type", "text/xml");
			
			// 리턴된 결과 읽기
		 
		     
		    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    
		    int rcount=0;
		    String resultKey="";

	        while ((inputLine = in.readLine()) != null) {

	        	logger.info("["+logid+"] Controller inputLine::"+inputLine);
	        	
	        	if(rcount==0){
	        		
	        		resultKey=inputLine;
	        		
	        		if(inputLine.equals("SUCCESS")){
	        			rtnMap.put("result", "SUCCESS"); 
	        		}else{
	        			rtnMap.put("result", "FAIL"); 
	        		}
	        		
	        	}
	        	
	        	if(resultKey.equals("SUCCESS") && rcount>0){
	        		
	        		String name="";
		        	String value="";
		        	
		        	String[] inputLines=null;
		        	
		        	inputLines=inputLine.split("=");
		        	
		        	rtnMap.put(inputLines[0], inputLines[1]); 
		        	
		        	logger.info("["+logid+"] inputLine[][]"+inputLines[0]+"->"+inputLines[1]);
	        	}
	        	
	        	rcount++;
	        	
		    }
	        
	        in.close();
	        
		}catch(Exception e){

			logger.info("["+logid+"] Controller error");
		}
		
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return rtnMap;
    }
	
    /**
     * 주문키 생성
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/order/orderkeycreate"})
    public @ResponseBody
    String orderKeyCreate(String customerKey,
    		String productCode,
    		String email,
            HttpServletRequest request, 
            HttpServletResponse response) throws BizException
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		
		logger.info("["+logid+"] Controller start customerKey="+customerKey);
		
		String emaillist="dev@addys.co.kr;kevin.jeon@offact.com";
		productCode="90030114";
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        
        String s_customerKey = StringUtil.nvl((String) session.getAttribute("customerKey")); 
        String customerKey2 = StringUtil.nvl((String) session.getAttribute("customerKey2")); 
        
        logger.info("["+logid+"] Controller start customerKey2="+customerKey2);
        
        if(customerKey.equals("") || customerKey.equals("null") || customerKey.equals(null)){

        	customerKey=s_customerKey;
        	
        	
        	if(customerKey2.equals("") || customerKey2.equals("null") || customerKey2.equals(null)){
               
        	}else{
        		emaillist=customerKey2; 
        	}
 
		}

        String keyvalue="";
        
        String encryptValue = "";

        String encrypt = "";
        
        String token=tokenCreate();

        encryptValue=token+"|"+productCode+"|"+customerKey;
        
        encrypt = CipherDecipherUtil.encrypt(encryptValue, "We are sales and livingsocials !");
        
        logger.info("["+logid+"] CipherDecipherUtil encrypt::"+encrypt);
        
        String saleUrl=domainUrl+"/?key="+encrypt;
       
        String shortUrl="";

        try{

            String requesturl = "http://api.bit.ly/v3/shorten?login=o_5m2bo33ffv&apiKey=R_1f43162e07a6453d93a6b256dd895d2d&format=&longUrl="+saleUrl;
            BufferedReader input = null;
            
        	String line = "";

            URL url = new URL(requesturl);
            input = new BufferedReader(new InputStreamReader(url.openStream()));

            line = input.readLine();

            Object obj = JSONValue.parse(line);
            
            JSONObject object = (JSONObject)obj;

            logger.info("["+logid+"] CipherDecipherUtil encrypt::"+object.get("status_code"));
            logger.info("["+logid+"] CipherDecipherUtil encrypt::"+object.get("data"));
            
            Object obj2 = JSONValue.parse(object.get("data").toString());
            
            JSONObject object2 = (JSONObject)obj2;
            logger.info("["+logid+"] CipherDecipherUtil url::"+object2.get("url"));
            
            shortUrl=object2.get("url").toString();
            
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
        
      //이메일 리스틑 조회 user
		
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
        szContent += "<td height='30' class='tit_black_b'>[sales baron] 주문키생성 안내메일</td>";
        szContent += "</tr>";
        szContent += "<tr>";
        szContent += "<td height='1' bgcolor='a9a9a9'></td>";
        szContent += "</tr>";
        szContent += "</table>";
        szContent += "<table width='500' cellspacing='0' cellpadding='0' style='margin-top:18px'>";
        szContent += "<tr>";
        szContent += "<td valign='top' style='padding:10px 0 0 0'>구매하신 상품(아이피타임 무선 랜카드 N150UA)에 대한 주문키가 생성되었습니다.<br><br>아래 개인 판매 URL을 이용하여 동일 상품을 판매 진행 가능하십니다.<br><br>1.SNS에 아래 주문 URL을 포함하여 개인 상품평(상품사진 포함)및 소개등록<br>2.상대방이 소개글을 확인 후 개인 주문 URL을 통해 구매하시면 구매금액의 10%를 환급 받으실 수 있습니다.<br><br>개인 판매 URL : <font color='bule'>"+shortUrl+"</font><br><br>판매내역 확인하러 가기 =><a href='"+domainUrl+"'>http://salesb.net</a></td>";
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
		
		mail.setSubject("[sales baron] 주문키생성 안내메일");
		
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
        
        //keyvalue = CipherDecipherUtil.decrypt(encrypt, "We are sales and livingsocials !");

        
        //logger.info("["+logid+"] CipherDecipherUtil decrypt::"+keyvalue);
        
	
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return shortUrl;
    }
	
    public String tokenCreate(){
	    
    	String token="123456789";
		
    	Random rand = new Random(12);
		rand.setSeed(System.currentTimeMillis());
		
		token=""+rand.nextInt(1000000000);
		logger.info("##### create token :: " + token);
    	
    	return token;
    }
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
	@RequestMapping(value = "/orderintro", method = RequestMethod.GET)
	public ModelAndView orderintro(HttpServletRequest request,
			                   HttpServletResponse response,  
			                   String key) throws BizException 
	{

		logger.info("Welcome intro");
		
		ModelAndView  mv = new ModelAndView();

		mv.addObject("key", key);
    	mv.setViewName("/order/intro");

		return mv;
	}
    
    
    /**
     * 고객판매페이지
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/custmobilesale")
    public ModelAndView custMobileSale( String key,
    		
							    	    String req_tx ,
							    	    String res_cd,
							    	    String tran_cd,
							    	    String ordr_idxx,
							    	    String good_name,
							    	    String good_mny,  
							    	    String buyr_name,
							    	    String buyr_tel1,
							    	    String buyr_tel2,
							    	    String buyr_mail,
							    	    String use_pay_method,
							    	    String ipgm_date,
							    		String enc_info,  
							    	    String enc_data, 
							    	    String van_code,
							    	    String cash_yn,
							    	    String cash_tr_code,
							    	    String param_opt_1,
							    	    String param_opt_2,
							    	    String param_opt_3,
    		
    		                       HttpServletRequest request, 
    		                       HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		
		logger.info("["+logid+"] Controller start key="+key);


        ModelAndView mv = new ModelAndView();

        String keyvalue = CipherDecipherUtil.decrypt(key, "We are sales and livingsocials !");
        
        logger.info("["+logid+"] CipherDecipherUtil keyvalue::"+keyvalue);

        String[] keys=null;
        
        keys=keyvalue.split("\\|");
        
        logger.info("["+logid+"] CipherDecipherUtil decrypt1::"+keys[0]);
        logger.info("["+logid+"] CipherDecipherUtil decrypt2::"+keys[1]);
        logger.info("["+logid+"] CipherDecipherUtil decrypt3::"+keys[2]);
        
        mv.addObject("token", keys[0]);
        mv.addObject("productCode", keys[1]);
        mv.addObject("productName", "좋은상품");
        mv.addObject("productPrice", "1500");
        mv.addObject("customerKey", keys[2]);
        mv.addObject("key", key);
        
        mv.addObject("req_tx", req_tx);
        mv.addObject("res_cd", res_cd);
        mv.addObject("tran_cd", tran_cd);
        mv.addObject("ordr_idxx", ordr_idxx);
        mv.addObject("good_name", good_name);
        mv.addObject("good_mny", good_mny);
        mv.addObject("buyr_name", buyr_name);
        mv.addObject("buyr_tel1", buyr_tel1);
        mv.addObject("buyr_tel2", buyr_tel2);
        mv.addObject("buyr_mail", buyr_mail);
        mv.addObject("use_pay_method", use_pay_method);
        
        mv.addObject("ipgm_date", ipgm_date);
        mv.addObject("enc_info", enc_info);
        mv.addObject("enc_data", enc_data);
        mv.addObject("van_code", van_code);
        mv.addObject("cash_yn", cash_yn);
        mv.addObject("cash_tr_code", cash_tr_code);
        mv.addObject("param_opt_1", param_opt_1);
        mv.addObject("param_opt_2", param_opt_2);
        mv.addObject("param_opt_3", param_opt_3);
        
        mv.setViewName("/order/custMobileSale");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    
    /**
     * 고객판매페이지
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/custsale")
    public ModelAndView custSale( String key,
    		                       HttpServletRequest request, 
    		                       HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		
		logger.info("["+logid+"] Controller start key="+key);


        ModelAndView mv = new ModelAndView();

        String keyvalue = CipherDecipherUtil.decrypt(key, "We are sales and livingsocials !");
        
        logger.info("["+logid+"] CipherDecipherUtil keyvalue::"+keyvalue);

        String[] keys=null;
        
        keys=keyvalue.split("\\|");
        
        logger.info("["+logid+"] CipherDecipherUtil decrypt1::"+keys[0]);
        logger.info("["+logid+"] CipherDecipherUtil decrypt2::"+keys[1]);
        logger.info("["+logid+"] CipherDecipherUtil decrypt3::"+keys[2]);
        
        mv.addObject("token", keys[0]);
        mv.addObject("productCode", keys[1]);
        mv.addObject("productName", "좋은상품");
        mv.addObject("productPrice", "1500");
        mv.addObject("customerKey", keys[2]);

        mv.setViewName("/order/custSale");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
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
    @RequestMapping(value = "/order/kcporderresult")
    public ModelAndView kcpOrderResult(String site_cd,
							    		String req_tx,
							    		String use_pay_method,
							    		String bSucc,
							    		String amount,
							    		String res_cd,
							    		String res_msg,
							    		String ordr_idxx,
							    		String tno,
							    		String good_mny,
							    		String good_name,
							    		String buyr_name,
							    		String buyr_tel1,
							    		String buyr_tel2,
							    		String buyr_mail,
							    		String app_time,
							    		String card_cd,
							    		String card_name,
							    		String app_no,
							    		String noinf,
							    		String quota,
							    		String partcanc_yn,
							    		String card_bin_type_01,
							    		String card_bin_type_02,
							    		String bank_name,
							    		String bank_code,
							    		String bankname,
							    		String depositor,
							    		String account,
							    		String va_date,
							    		String pnt_issue,
							    		String pnt_app_time,
							    		String pnt_app_no,
							    		String pnt_amount,
							    		String add_pnt,
							    		String use_pnt,
							    		String rsv_pnt,
							    		String commid,
							    		String mobile_no,
							    		String tk_van_code,
							    		String tk_app_no,
							    		String cash_yn,
							    		String cash_authno,
							    		String cash_tr_code,
							    		String cash_id_info,
							    		HttpServletRequest request, 
							    		HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();

        ModelAndView mv = new ModelAndView();
        
        logger.info("["+logid+"] kcpOrderResult req_tx::"+req_tx);
        logger.info("["+logid+"] kcpOrderResult use_pay_method::"+use_pay_method);
        logger.info("["+logid+"] kcpOrderResult bSucc::"+bSucc);
        logger.info("["+logid+"] kcpOrderResult amount::"+amount);
        logger.info("["+logid+"] kcpOrderResult res_cd::"+res_cd);
        logger.info("["+logid+"] kcpOrderResult res_msg::"+res_msg);
        logger.info("["+logid+"] kcpOrderResult buyr_name::"+buyr_name);
        
        String de_buyr_name="";
        
        try{

        logger.info("["+logid+"] @@@@@@@@@@ res_msg::"+URLDecoder.decode(res_msg, "UTF-8"));
        logger.info("["+logid+"] @@@@@@@@@@ res_msg::"+URLDecoder.decode(res_msg, "EUC-KR"));
        logger.info("["+logid+"] @@@@@@@@@@ buyr_name::"+URLDecoder.decode(buyr_name, "UTF-8"));
        logger.info("["+logid+"] @@@@@@@@@@ buyr_name::"+URLDecoder.decode(buyr_name, "EUC-KR"));

        de_buyr_name=URLDecoder.decode(res_msg, "EUC-KR");
       
        }catch(Exception e){
        	e.printStackTrace();
        }
        mv.addObject("req_tx", req_tx);
		mv.addObject("use_pay_method", use_pay_method);
		mv.addObject("bSucc", bSucc);
		mv.addObject("amount", amount);
		mv.addObject("res_cd", res_cd);
		mv.addObject("res_msg", res_msg);
		mv.addObject("ordr_idxx", ordr_idxx);
		mv.addObject("tno", tno);
		mv.addObject("good_mny", good_mny);
		mv.addObject("good_name", good_name);
		mv.addObject("buyr_name", buyr_name);
		mv.addObject("buyr_tel1", buyr_tel1);
		mv.addObject("buyr_tel2", buyr_tel2);
		mv.addObject("buyr_mail", buyr_mail);
		mv.addObject("app_time", app_time);
		mv.addObject("card_cd", card_cd);
		mv.addObject("card_name", card_name);
		mv.addObject("app_no", app_no);
		mv.addObject("noinf", noinf);
		mv.addObject("quota", quota);
		mv.addObject("partcanc_yn", partcanc_yn);
		mv.addObject("card_bin_type_01", card_bin_type_01);
		mv.addObject("card_bin_type_02", card_bin_type_02);
		mv.addObject("bank_name", bank_name);
		mv.addObject("bank_code", bank_code);
		mv.addObject("bankname", bankname);
		mv.addObject("depositor", depositor);
		mv.addObject("account", account);
		mv.addObject("va_date", va_date);
		mv.addObject("pnt_issue", pnt_issue);
		mv.addObject("pnt_app_time", pnt_app_time);
		mv.addObject("pnt_app_no", pnt_app_no);
		mv.addObject("pnt_amount", pnt_amount);
		mv.addObject("add_pnt", add_pnt);
		mv.addObject("use_pnt", use_pnt);
		mv.addObject("rsv_pnt", rsv_pnt);
		mv.addObject("commid", commid);
		mv.addObject("mobile_no", mobile_no);
		mv.addObject("tk_van_code", tk_van_code);
		mv.addObject("tk_app_no", tk_app_no);
		mv.addObject("cash_yn", cash_yn);
		mv.addObject("cash_authno", cash_authno);
		mv.addObject("cash_tr_code", cash_tr_code);
		mv.addObject("cash_id_info", cash_id_info);
		
		mv.addObject("hostUrl", hostUrl);
		mv.addObject("domainUrl", domainUrl);

        mv.setViewName("/order/kcpOrderResult");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    
}
