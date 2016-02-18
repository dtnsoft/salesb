package com.offact.salesb.controller;

import java.io.File;
import java.io.FileInputStream;
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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import com.offact.framework.util.StringUtil;
import com.offact.framework.constants.CodeConstant;
import com.offact.framework.exception.BizException;
import com.offact.framework.jsonrpc.JSONRpcService;
import com.offact.salesb.service.common.CommonService;
import com.offact.salesb.service.common.UserService;
import com.offact.salesb.service.history.WorkHistoryService;
import com.offact.salesb.service.history.SmsHistoryService;
import com.offact.salesb.vo.common.CodeVO;
import com.offact.salesb.vo.common.GroupVO;
import com.offact.salesb.vo.common.UserVO;
import com.offact.salesb.vo.common.WorkVO;
import com.offact.salesb.vo.history.WorkHistoryVO;
import com.offact.salesb.vo.history.SmsHistoryVO;
import com.offact.salesb.vo.manage.UserManageVO;
import com.offact.salesb.vo.manage.CompanyManageVO;
import com.offact.salesb.vo.master.ProductMasterVO;
import com.offact.salesb.vo.MultipartFileVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class HistoryController {

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
	
    @Autowired
    private CommonService commonSvc;

	@Autowired
	private UserService userSvc;
	
    @Autowired
    private WorkHistoryService workHistorySvc;
    
    @Autowired
    private SmsHistoryService smsHistorySvc;
    
    /**
     * 업무히스토리 로딩
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/history/workhistorymanage")
    public ModelAndView workHistoryManage(HttpServletRequest request, 
    		                       HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start ");

        ModelAndView mv = new ModelAndView();
        
     // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        Date deliveryTime = new Date();
        int movedate=-7;//(1:내일 ,-1:어제)
        
        deliveryTime.setTime(currentTime.getTime()+(1000*60*60*24)*movedate);
        
        String strToday = simpleDateFormat.format(currentTime);
        String strDeliveryDay = simpleDateFormat.format(deliveryTime);
       
        WorkHistoryVO workConVO = new WorkHistoryVO();
        
        workConVO.setUserId(strUserId);
        workConVO.setGroupId(strGroupId);
        
        workConVO.setStart_workDate(strDeliveryDay);
        workConVO.setEnd_workDate(strToday);
           
        //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId(strGroupId);
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
        
        // 공통코드 조회 (작업구분)
        CodeVO code = new CodeVO();
        code.setCodeGroupId("SE01");
        List<CodeVO> code_comboList = commonSvc.getCodeComboList(code);
        mv.addObject("code_comboList", code_comboList);

        // 조회조건저장
        mv.addObject("workConVO", workConVO);
        
        mv.setViewName("/history/workHistoryManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 업무히스토리 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/history/workhistorypagelist")
    public ModelAndView workHistoryPageList(@ModelAttribute("workConVO") WorkHistoryVO workConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : workConVO" + workConVO);

        ModelAndView mv = new ModelAndView();
    
        // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}

        List<WorkHistoryVO> workList = null;

        // 조회조건 null 일때 공백처리
        if (workConVO.getSearchGubun() == null) {
        	workConVO.setSearchGubun("01");
        }
        
        // 조회값 null 일때 공백처리
        if (workConVO.getSearchValue() == null) {
        	workConVO.setSearchValue("");
        }
        
        // 조회조건저장
        mv.addObject("workConVO", workConVO);

        // 페이징코드
        workConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(workConVO.getCurPage(), workConVO.getRowCount()));
        workConVO.setPage_limit_val2(StringUtil.nvl(workConVO.getRowCount(), "10"));
        
        // 업무목록조회
        workList = workHistorySvc.getWorkHistoryPageList(workConVO);
        mv.addObject("workList", workList);

        // totalCount 조회
        String totalCount = String.valueOf(workHistorySvc.getWorkHistoryCnt(workConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("/history/workHistoryPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
    @RequestMapping(value = "/history/workcodeoption")
    public ModelAndView workCodeOption(String workCategory, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
	{

    	logger.info("workcodeoption");
		ModelAndView mv = new ModelAndView();
		
		CodeVO code = new CodeVO();
		List<CodeVO> code_workcodelist =null;
		
		if(workCategory.equals("")){
			code.setCodeGroupId("SE02");
			code_workcodelist = commonSvc.getCodeComboList(code);
		}else{
			code.setCodeId(workCategory);
			code_workcodelist = commonSvc.getWorkCodeList(code);	
		}

        mv.addObject("code_workcodelist", code_workcodelist);
		
		mv.setViewName("history/workCodeOption");
		return mv;
	}
    
    /**
     * 업무히스토리 로딩
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/history/smshistorymanage")
    public ModelAndView smsHistoryManage(HttpServletRequest request, 
    		                       HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start ");

        ModelAndView mv = new ModelAndView();
        
       // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}
        
        //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        Date deliveryTime = new Date();
        int movedate=-7;//(1:내일 ,-1:어제)
        
        deliveryTime.setTime(currentTime.getTime()+(1000*60*60*24)*movedate);
        
        String strToday = simpleDateFormat.format(currentTime);
        String strDeliveryDay = simpleDateFormat.format(deliveryTime);
       
        SmsHistoryVO smsConVO = new SmsHistoryVO();
        
        smsConVO.setUserId(strUserId);
        smsConVO.setGroupId(strGroupId);
        
        smsConVO.setStart_smsDate(strDeliveryDay);
        smsConVO.setEnd_smsDate(strToday);
           
        //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId(strGroupId);
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
        
     
        // 조회조건저장
        mv.addObject("smsConVO", smsConVO);
        
        mv.setViewName("/history/smsHistoryManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 업무히스토리 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/history/smshistorypagelist")
    public ModelAndView smsHistoryPageList(@ModelAttribute("smsConVO") SmsHistoryVO smsConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : smsConVO" + smsConVO);

        ModelAndView mv = new ModelAndView();
      
        // 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        String strGroupId = StringUtil.nvl((String) session.getAttribute("strGroupId"));
        String strIp = StringUtil.nvl((String) session.getAttribute("strIp"));
        String sClientIP = StringUtil.nvl((String) session.getAttribute("sClientIP"));
        
        if(strUserId.equals("") || strUserId.equals("null") || strUserId.equals(null)){
        	
        	strIp = request.getRemoteAddr(); //로그인 상태처리		
    		UserVO userState =new UserVO();
    		userState.setUserId(strUserId);
    		userState.setLoginYn("N");
    		userState.setIp(strIp);
    		userState.setConnectIp(sClientIP);
    		userSvc.regiLoginYnUpdate(userState);
            
            //작업이력
	 		WorkVO work = new WorkVO();
	 		work.setWorkUserId(strUserId);
	 		work.setWorkIp(strIp);
	 		work.setWorkCategory("CM");
	 		work.setWorkCode("CM004");
	 		commonSvc.regiHistoryInsert(work);
    		
        	mv.setViewName("/addys/loginForm");
       		return mv;
		}

        List<SmsHistoryVO> smsList = null;

        // 조회조건 null 일때 공백처리
        if (smsConVO.getSearchGubun() == null) {
        	smsConVO.setSearchGubun("01");
        }
        
        // 조회값 null 일때 공백처리
        if (smsConVO.getSearchValue() == null) {
        	smsConVO.setSearchValue("");
        }
        
        // 조회조건저장
        mv.addObject("smsConVO", smsConVO);

        // 페이징코드
        smsConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(smsConVO.getCurPage(), smsConVO.getRowCount()));
        smsConVO.setPage_limit_val2(StringUtil.nvl(smsConVO.getRowCount(), "10"));
        
        // SMS목록조회
        smsList = smsHistorySvc.getSmsHistoryPageList(smsConVO);
        mv.addObject("smsList", smsList);

        // totalCount 조회
        String totalCount = String.valueOf(smsHistorySvc.getSmsHistoryCnt(smsConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("/history/smsHistoryPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
}
