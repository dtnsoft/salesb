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
import com.offact.salesb.service.manage.UserManageService;
import com.offact.salesb.service.manage.CustManageService;
import com.offact.salesb.service.manage.CompanyManageService;
import com.offact.salesb.service.history.WorkHistoryService;
import com.offact.salesb.vo.common.CodeVO;
import com.offact.salesb.vo.common.GroupVO;
import com.offact.salesb.vo.common.WorkVO;
import com.offact.salesb.vo.common.UserVO;
import com.offact.salesb.vo.history.WorkHistoryVO;
import com.offact.salesb.vo.manage.UserManageVO;
import com.offact.salesb.vo.manage.CustManageVO;
import com.offact.salesb.vo.manage.CompanyManageVO;
import com.offact.salesb.vo.master.ProductMasterVO;
import com.offact.salesb.vo.MultipartFileVO;

/**
 * Handles requests for the application home page.
 */
@Controller

public class ManageController {

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
    private UserManageService userManageSvc;
    
    @Autowired
    private CompanyManageService companyManageSvc;
    
    @Autowired
    private CustManageService custManageSvc;

	 /**
     * 사용자관리 화면 로딩
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/usermanage")
    public ModelAndView userManage(HttpServletRequest request, 
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
    		
        	mv.setViewName("/admin/loginForm");
       		return mv;
		}
        
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
       
        mv.setViewName("/manage/userManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 사용자관리 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/userpagelist")
    public ModelAndView userPageList(@ModelAttribute("userConVO") UserManageVO userConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : userConVO" + userConVO);

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
    		
        	mv.setViewName("/admin/loginForm");
       		return mv;
		}
        
        List<UserManageVO> userList = null;
        UserManageVO userDetail = null;

        // 사용여부 값 null 일때 공백처리
        if (userConVO.getCon_useYn() == null) {
            userConVO.setCon_useYn("Y");
        }

        // 그룹 값 null 일때 공백처리
        if (userConVO.getCon_groupId() == null) {
            userConVO.setCon_groupId("G00000");
        }
        
        // 조회조건 null 일때 공백처리
        if (userConVO.getSearchGubun() == null) {
            userConVO.setSearchGubun("02");
        }
        
        // 조회값 null 일때 공백처리
        if (userConVO.getSearchValue() == null) {
            userConVO.setSearchValue("system");
        }
        
        // 조회조건저장
        mv.addObject("userCon", userConVO);

        // 페이징코드
        userConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(userConVO.getCurPage(), userConVO.getRowCount()));
        userConVO.setPage_limit_val2(StringUtil.nvl(userConVO.getRowCount(), "10"));
        
        // 사용자목록조회
        userList = userManageSvc.getUserList(userConVO);
        mv.addObject("userList", userList);

        // totalCount 조회
        String totalCount = String.valueOf(userManageSvc.getUserCnt(userConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("/manage/userPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
    @RequestMapping(value = "/manage/userregform")
	public ModelAndView userRegForm(HttpServletRequest request) throws BizException 
    {
		
		ModelAndView mv = new ModelAndView();
		
		UserManageVO userVO = new UserManageVO();
		
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
    		
        	mv.setViewName("/admin/loginForm");
       		return mv;
		}
		
		userVO.setCreateUserId(strUserId);
		mv.addObject("userVO", userVO);
		
		//조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId("G00000");
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
        
        // 공통코드 조회 (관리권한)
        CodeVO code = new CodeVO();
        code.setCodeGroupId("AU01");
        List<CodeVO> code_comboList = commonSvc.getCodeComboList(code);
        mv.addObject("code_comboList", code_comboList);
		
		mv.setViewName("/manage/userRegForm");
		return mv;
	}
	 /**
     * 사용자관리 등록처리
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/userreg", method = RequestMethod.POST)
    public @ResponseBody
    String userReg(@ModelAttribute("userVO") UserManageVO userVO, 
    		       HttpServletRequest request, 
    		       HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : userVO" + userVO);

		int retVal=this.userManageSvc.userInsertProc(userVO);

		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        
		//작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory("MU");
		work.setWorkCode("MU001");
		commonSvc.regiHistoryInsert(work);
		
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return ""+retVal;
    }
	 /**
     * 사용자정 수정화면
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/usermodifyform")
	public ModelAndView userModifyForm(@RequestParam(value = "userId") String userId, 
									   HttpServletRequest request) throws BizException 
    {
		
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : userId" + userId);
    			
		ModelAndView mv = new ModelAndView();
		
		UserManageVO userVO = new UserManageVO();
		
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
    		
        	mv.setViewName("/admin/loginForm");
       		return mv;
		}
        
        userVO = userManageSvc.getUserDetail(userId);
		
		userVO.setUpdateUserId(strUserId);
		mv.addObject("userVO", userVO);
		
		 //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId("G00000");
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
        
        // 공통코드 조회 (관리권한)
        CodeVO code = new CodeVO();
        code.setCodeGroupId("AU01");
        List<CodeVO> code_comboList = commonSvc.getCodeComboList(code);
        mv.addObject("code_comboList", code_comboList);
		
		mv.setViewName("/manage/userModifyForm");
		
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
		return mv;
	}
    /**
     * 사용자관리 수정처리
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/usermodify", method = RequestMethod.POST)
    public @ResponseBody
    String userModify(@ModelAttribute("userVO") UserManageVO userVO, 
    		          HttpServletRequest request, 
    		          HttpServletResponse response,
    		          String workCode) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : userVO" + userVO);
		
		workCode=StringUtil.nvl(workCode,"MU002");
		String workCategory="MU";
		
		if(workCode.equals("CM003")){
			workCategory="CM";
		}else{
			workCategory="MU";
			workCode="MU002";	
		}

		int retVal=this.userManageSvc.userUpdateProc(userVO);
		
		String pw_modifyYn=	userVO.getPw_modifyYn();
		
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));
        
      //오늘 날짜
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date currentTime = new Date();
        String strToday = simpleDateFormat.format(currentTime);
        
        if(pw_modifyYn.equals("Y")){
        	session.setAttribute("pwdChangeDateTime", strToday);
        }
        
		//작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory(workCategory);
		work.setWorkCode(workCode);
		commonSvc.regiHistoryInsert(work);
		
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return ""+retVal;
    }
    /**
     * 사용자관리 삭제처리
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/manage/userdeletes"})
    public @ResponseBody
    String userDeletes(@ModelAttribute("userVO") UserManageVO userVO, 
    		           @RequestParam(value="arrDelUserId", required=false, defaultValue="") String arrDelUserId,
    		           HttpServletRequest request) throws BizException
    {
      
	    //log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : userVO" + userVO);
			
		// 사용자 세션정보
        HttpSession session = request.getSession();
        String strUserId = StringUtil.nvl((String) session.getAttribute("strUserId"));

	    logger.info("@#@#@# arrDelUserId : " + arrDelUserId);

        int retVal=this.userManageSvc.userDeleteProc(strUserId , arrDelUserId);

		//작업이력
		WorkVO work = new WorkVO();
		work.setWorkUserId(strUserId);
		work.setWorkCategory("MU");
		work.setWorkCode("MU003");
		commonSvc.regiHistoryInsert(work);
		
		//log Controller execute time end
	 	long t2 = System.currentTimeMillis();
	 	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

    return ""+retVal;
    }
    /**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
    @RequestMapping(value = "/manage/userexcelform")
	public ModelAndView userExcelForm(HttpServletRequest request) throws BizException 
    {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/manage/userExcelForm");
		
		return mv;
	}
    /**
     * 사용자관리 일괄등록
     *
     * @param MultipartFileVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/manage/userexcelimport"})
    public ModelAndView userExcelImport(@ModelAttribute("MultipartFileVO") MultipartFileVO fileVO, 
    		                            HttpServletRequest request, 
    		                            HttpServletResponse response, 
    		                            String fileName, 
    		                            String extension ) throws IOException, BizException
    {
      
      //log Controller execute time start
	  String logid=logid();
	  long t1 = System.currentTimeMillis();
	  logger.info("["+logid+"] Controller start : fileVO" + fileVO);
    			
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
  		
      	mv.setViewName("/admin/loginForm");
     		return mv;
		}
      ResourceBundle rb = ResourceBundle.getBundle("config");
      String uploadFilePath = rb.getString("offact.upload.path") + "excel/";
      
      this.logger.info("파일정보:" + fileName + extension);
      this.logger.info("file:" + fileVO);

      List userUploadList = new ArrayList();
      String excelInfo = "";//excel 추출데이타
      List rtnErrorList = new ArrayList(); //DB 에러 대상데이타
      List rtnSuccessList = new ArrayList(); //DB 성공 대상데이타

      if (fileName != null) {
    	  
        List<MultipartFile> files = fileVO.getFiles();
        List fileNames = new ArrayList();
        String orgFileName = null;

        if ((files != null) && (files.size() > 0))
        {
          for (MultipartFile multipartFile : files)
          {
            orgFileName = multipartFile.getOriginalFilename();
            String filePath = uploadFilePath;

            File file = new File(filePath + orgFileName);
            multipartFile.transferTo(file);
            fileNames.add(orgFileName);
          }
          
        }

        String fname = uploadFilePath + orgFileName;

        FileInputStream fileInput = null;

        fileInput = new FileInputStream(fname);

        try {
        	
	       XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
	       XSSFSheet sheet = workbook.getSheetAt(0);//첫번째 sheet
	   
	       int TITLE_POINT =2;//타이틀 항목위치
	       int ROW_START = 4;//data row 시작지점
	        
	       int TOTAL_ROWS=sheet.getPhysicalNumberOfRows(); //전체 ROW 수를 가져온다.
	       int TOTAL_CELLS=sheet.getRow(TITLE_POINT).getPhysicalNumberOfCells(); //전체 셀의 항목 수를 가져온다.
	        
	       XSSFCell myCell = null;
	      
	       this.logger.debug("TOTAL_ROWS :" + TOTAL_ROWS);
	       this.logger.debug("TOTAL_CELLS :" + TOTAL_CELLS);

           for (int rowcnt = ROW_START; rowcnt < TOTAL_ROWS; rowcnt++) {
        	   
        	 UserManageVO userManageVO = new UserManageVO();
             XSSFRow row = sheet.getRow(rowcnt);

             //cell type 구분하여 담기  
             String[] cellItemTmp = new String[TOTAL_CELLS]; 
             for(int cellcnt=0;cellcnt<TOTAL_CELLS;cellcnt++){
	            myCell = row.getCell(cellcnt); 
	            
	            if(myCell!=null){
	            	
		            if(myCell.getCellType()==0){ //cell type 이 숫자인경우

		            	String rawCell = String.valueOf(myCell.getNumericCellValue()); 
		            	int endChoice = rawCell.lastIndexOf("E");
		            	if(endChoice>0){
		            		rawCell= rawCell.substring(0, endChoice);
		            		rawCell= rawCell.replace(".", "");
		            	}
		            	cellItemTmp[cellcnt]=rawCell;
	    	
		            }else if(myCell.getCellType()==1){ //cell type 이 일반/문자 인경우
		            	cellItemTmp[cellcnt] = myCell.getStringCellValue(); 
		            }else{//그외 cell type
		            	cellItemTmp[cellcnt] = ""; 
		            }
		            this.logger.debug("row : ["+rowcnt+"] cell : ["+cellcnt+"] celltype : ["+myCell.getCellType()+"] ->"+ cellItemTmp[cellcnt]);
		            excelInfo="row : ["+rowcnt+"] cell : ["+cellcnt+"] celltype : ["+myCell.getCellType()+"] ->"+ cellItemTmp[cellcnt];
	            }else{
	            	
	            	cellItemTmp[cellcnt]="";
	            	
	            }
	         }
         
	         if(cellItemTmp.length>1 && cellItemTmp[1] != ""){
		        	 
	        	 if(cellItemTmp.length>1){ userManageVO.setUserName(cellItemTmp[1]);}
	        	 if(cellItemTmp.length>2){ userManageVO.setUserId(cellItemTmp[2]);}
	        	 if(cellItemTmp.length>3){ userManageVO.setMobliePhone(cellItemTmp[3]);}
	        	 if(cellItemTmp.length>4){ userManageVO.setGroupId(cellItemTmp[4]);}
	        	 if(cellItemTmp.length>5){ userManageVO.setDeletedYn(cellItemTmp[5]);}
	        	 if(cellItemTmp.length>6){ userManageVO.setPassword(cellItemTmp[6]);}

	             userManageVO.setCreateUserId(strUserId);
		 	     userManageVO.setUpdateUserId(strUserId);

		 	     userUploadList.add(userManageVO);
			 }
		     	
		    }
           }catch (Exception e){
  
        	   e.printStackTrace();
        	   excelInfo = excelInfo+"[error] : "+e.getMessage();
        	   UserManageVO userManageVO = new UserManageVO();
        	   userManageVO.setErrMsg(excelInfo);
	 
        	   this.logger.info("["+logid+"] Controller getErrMsg : "+userManageVO.getErrMsg());
     
        	   rtnErrorList.add(userManageVO);

        	   mv.addObject("rtnErrorList", rtnErrorList);
        	   mv.addObject("rtnSuccessList", rtnSuccessList);

        	   mv.setViewName("/manage/uploadResult");
	 
        	   //log Controller execute time end
        	   long t2 = System.currentTimeMillis();
        	   logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
           	
           		return mv;
	   
   			}
      }
     //DB처리
     Map rtmMap = this.userManageSvc.regiExcelUpload(userUploadList);

     rtnErrorList = (List)rtmMap.get("rtnErrorList");
     rtnSuccessList = (List)rtmMap.get("rtnSuccessList");

     this.logger.info("rtnErrorList.size() :"+ rtnErrorList.size()+"rtnSuccessList.size() :"+ rtnSuccessList.size());
  
     mv.addObject("rtnErrorList", rtnErrorList);
     mv.addObject("rtnSuccessList", rtnSuccessList);
     

	//작업이력
	 WorkVO work = new WorkVO();
	 work.setWorkUserId(strUserId);
	 work.setWorkCategory("MU");
	 work.setWorkCode("MU004");
	 commonSvc.regiHistoryInsert(work);
       
     mv.setViewName("/manage/uploadResult");

     //log Controller execute time end
     long t2 = System.currentTimeMillis();
     logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
 	
     return mv;
    }
    /**
     * 업체관리 화면 로딩
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/companymanage")
    public ModelAndView companyManage(HttpServletRequest request, 
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
    		
        	mv.setViewName("/admin/loginForm");
       		return mv;
		}
        
        CompanyManageVO companyConVO = new CompanyManageVO();
        
        companyConVO.setUserId(strUserId);
        companyConVO.setGroupId(strGroupId);

        // 조회조건저장
        mv.addObject("userConVO", companyConVO);
        
        mv.setViewName("/manage/companyManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 업체관리 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/companypagelist")
    public ModelAndView companyPageList(@ModelAttribute("companyConVO") CompanyManageVO companyConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : companyConVO" + companyConVO);

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
    		
        	mv.setViewName("/admin/loginForm");
       		return mv;
		}
        
        List<CompanyManageVO> companyList = null;

        // 조회조건 null 일때 공백처리
        if (companyConVO.getSearchGubun() == null) {
        	companyConVO.setSearchGubun("01");
        }
        
        // 조회값 null 일때 공백처리
        if (companyConVO.getSearchValue() == null) {
        	companyConVO.setSearchValue("");
        }
        
        // 조회조건저장
        mv.addObject("companyConVO", companyConVO);

        // 페이징코드
        companyConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(companyConVO.getCurPage(), companyConVO.getRowCount()));
        companyConVO.setPage_limit_val2(StringUtil.nvl(companyConVO.getRowCount(), "10"));
        
        // 업체목록조회
        companyList = companyManageSvc.getCompanyPageList(companyConVO);
        mv.addObject("companyList", companyList);

        // totalCount 조회
        String totalCount = String.valueOf(companyManageSvc.getCompanyCnt(companyConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("/manage/companyPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    /**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizException
	 */
    @RequestMapping(value = "/manage/companyexcelform")
	public ModelAndView companyExcelForm(HttpServletRequest request) throws BizException 
    {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/manage/companyExcelForm");
		
		return mv;
	}
    /**
     * 업체 일괄등록
     *
     * @param MultipartFileVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping({"/manage/companyexcelimport"})
    public ModelAndView companyExcelImport(@ModelAttribute("MultipartFileVO") MultipartFileVO fileVO, 
    		                            HttpServletRequest request, 
    		                            HttpServletResponse response, 
    		                            String fileName, 
    		                            String extension ) throws IOException, BizException
    {
      
        //log Controller execute time start
   	    String logid=logid();
        long t1 = System.currentTimeMillis();
        logger.info("["+logid+"] Controller start : fileVO" + fileVO);
      			
        ModelAndView mv = new ModelAndView();

        HttpSession session = request.getSession();
        String strUserId = (String)session.getAttribute("strUserId");

        ResourceBundle rb = ResourceBundle.getBundle("config");
        String uploadFilePath = rb.getString("offact.upload.path") + "excel/";
        
        this.logger.debug("파일정보:" + fileName + extension);
        this.logger.debug("file:" + fileVO);

        List excelUploadList = new ArrayList();//업로드 대상 데이타
        
        String excelInfo = "";//excel 추출데이타
        List rtnErrorList = new ArrayList(); //DB 에러 대상데이타
        List rtnSuccessList = new ArrayList(); //DB 성공 대상데이타

        if (fileName != null) {
      	  
          List<MultipartFile> files = fileVO.getFiles();
          List fileNames = new ArrayList();
          String orgFileName = null;

          if ((files != null) && (files.size() > 0))
          {
            for (MultipartFile multipartFile : files)
            {
              orgFileName = multipartFile.getOriginalFilename();
              String filePath = uploadFilePath;

              File file = new File(filePath + orgFileName);
              multipartFile.transferTo(file);
              fileNames.add(orgFileName);
            }
       
          }

          String fname = uploadFilePath + orgFileName;

          FileInputStream fileInput = null;

          fileInput = new FileInputStream(fname);

          XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
          XSSFSheet sheet = workbook.getSheetAt(0);//첫번째 sheet
     
          int TITLE_POINT =0;//타이틀 항목위치
          int ROW_START = 1;//data row 시작지점
          
          int TOTAL_ROWS=sheet.getPhysicalNumberOfRows(); //전체 ROW 수를 가져온다.
          int TOTAL_CELLS=sheet.getRow(TITLE_POINT).getPhysicalNumberOfCells(); //전체 셀의 항목 수를 가져온다.
          
          XSSFCell myCell = null;
        
          this.logger.debug("TOTAL_ROWS :" + TOTAL_ROWS);
          this.logger.debug("TOTAL_CELLS :" + TOTAL_CELLS);
              
              try {
    
   	           for (int rowcnt = ROW_START; rowcnt < TOTAL_ROWS; rowcnt++) {
   	             
   	             CompanyManageVO companyManageVO = new CompanyManageVO();
   	             XSSFRow row = sheet.getRow(rowcnt);

   	             //cell type 구분하여 담기  
                 String[] cellItemTmp = new String[TOTAL_CELLS]; 
	   	         
                 for(int cellcnt=0;cellcnt<TOTAL_CELLS;cellcnt++){
                	 
	   	            myCell = row.getCell(cellcnt); 
	   	            
	   	            if(myCell!=null){
                		
		   	            if(myCell.getCellType()==0){ //cell type 이 숫자인경우
		   	            	
		   	            	String rawCell = String.valueOf(myCell.getNumericCellValue());
			            	int endChoice = rawCell.lastIndexOf("E");
			            	if(endChoice>0){
			            		rawCell= rawCell.substring(0, endChoice);
				            	rawCell= rawCell.replace(".", "");
			            	}
			            	cellItemTmp[cellcnt]=rawCell;
			            	
		   	            }else if(myCell.getCellType()==1){ //cell type 이 일반/문자 인경우
		   	            	cellItemTmp[cellcnt] = myCell.getStringCellValue(); 
		   	            }else{//그외 cell type
		   	            	cellItemTmp[cellcnt] = ""; 
		   	            }
		   	            this.logger.debug("row : ["+rowcnt+"] cell : ["+cellcnt+"] celltype : ["+myCell.getCellType()+"] ->"+ cellItemTmp[cellcnt]);
		   	            excelInfo="row : ["+rowcnt+"] cell : ["+cellcnt+"] celltype : ["+myCell.getCellType()+"] ->"+ cellItemTmp[cellcnt];
                	}else{
                		
                		cellItemTmp[cellcnt] = "";

                	}
	   	         }
   	         
   	         if(cellItemTmp.length>0 && cellItemTmp[0] != ""){
   	        	 
   	        	 if(cellItemTmp.length>0){ companyManageVO.setCompanyCode(cellItemTmp[0]);}
   	        	 if(cellItemTmp.length>1){ companyManageVO.setCompanyName(cellItemTmp[1]);}
   	        	 if(cellItemTmp.length>2){ companyManageVO.setChargeName(cellItemTmp[2]);}
   	        	 if(cellItemTmp.length>3){ companyManageVO.setMobilePhone(cellItemTmp[3]);}
   	        	 if(cellItemTmp.length>4){ companyManageVO.setCompanyPhone(cellItemTmp[4]);}
   	        	 if(cellItemTmp.length>5){ companyManageVO.setFaxNumber(cellItemTmp[5]);}
   	        	 if(cellItemTmp.length>6){ companyManageVO.setEmail(cellItemTmp[6]);}
   	        	 if(cellItemTmp.length>7){ companyManageVO.setEmail_cc(cellItemTmp[7]);}
   	        	
   	        	 companyManageVO.setCreateUserId(strUserId);
   	        	 companyManageVO.setUpdateUserId(strUserId);
   	        	 companyManageVO.setDeletedYn("N");
   		
   		         excelUploadList.add(companyManageVO);
   		         }
   		     	
   		       }
              }catch (Exception e){
     
      	    	  excelInfo = excelInfo+"[error] : "+e.getMessage();
      	    	  CompanyManageVO companyManageVO = new CompanyManageVO();
      	    	  companyManageVO.setErrMsg(excelInfo);
       	 
      	    	  this.logger.info("["+logid+"] Controller getErrMsg : "+companyManageVO.getErrMsg());
            
      	    	  rtnErrorList.add(companyManageVO);

             mv.addObject("rtnErrorList", rtnErrorList);
             mv.addObject("rtnSuccessList", rtnSuccessList);

             mv.setViewName("/manage/uploadResult");
             
             //작업이력
     		 WorkVO work = new WorkVO();
     		 work.setWorkUserId(strUserId);
     		 work.setWorkCategory("MC");
     		 work.setWorkCode("MC001");
     		 commonSvc.regiHistoryInsert(work);
       	 
             //log Controller execute time end
             long t2 = System.currentTimeMillis();
             logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
     	 	
             return mv;
       	   
          	}
        }
        
        //DB처리
        Map rtmMap = this.companyManageSvc.regiExcelUpload(excelUploadList);

        rtnErrorList = (List)rtmMap.get("rtnErrorList");
        rtnSuccessList = (List)rtmMap.get("rtnSuccessList");

        this.logger.info("rtnErrorList.size() :"+ rtnErrorList.size()+"rtnSuccessList.size() :"+ rtnSuccessList.size());
     
        mv.addObject("rtnErrorList", rtnErrorList);
        mv.addObject("rtnSuccessList", rtnSuccessList);
          
        mv.setViewName("/manage/uploadResult");

        //log Controller execute time end
        long t2 = System.currentTimeMillis();
        logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
    	
        return mv;
    }
    /**
     * 업체관리 조회화면 로딩
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/companysearch")
    public ModelAndView companySearch(HttpServletRequest request, 
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
    		
        	mv.setViewName("/admin/loginForm");
       		return mv;
		}
        
        CompanyManageVO companyConVO = new CompanyManageVO();
        
        companyConVO.setUserId(strUserId);
        companyConVO.setGroupId(strGroupId);

        // 조회조건저장
        mv.addObject("userConVO", companyConVO);
        
        mv.setViewName("/common/companySearch");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 업체관리 조회목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/companysearchlist")
    public ModelAndView companySearchList(@ModelAttribute("companyConVO") CompanyManageVO companyConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : companyConVO" + companyConVO);

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
    		
        	mv.setViewName("/admin/loginForm");
       		return mv;
		}
        
        List<CompanyManageVO> companyList = null;

        // 조회조건 null 일때 공백처리
        if (companyConVO.getSearchGubun() == null) {
        	companyConVO.setSearchGubun("01");
        }
        
        // 조회값 null 일때 공백처리
        if (companyConVO.getSearchValue() == null) {
        	companyConVO.setSearchValue("");
        }
        
        // 조회조건저장
        mv.addObject("companyConVO", companyConVO);

        // 페이징코드
        companyConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(companyConVO.getCurPage(), companyConVO.getRowCount()));
        companyConVO.setPage_limit_val2(StringUtil.nvl(companyConVO.getRowCount(), "10"));
        
        // 업체목록조회
        companyList = companyManageSvc.getCompanyPageList(companyConVO);
        mv.addObject("companyList", companyList);

        // totalCount 조회
        String totalCount = String.valueOf(companyManageSvc.getCompanyCnt(companyConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("/common/companySearchList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    
    /**
     * 중복체크
     *
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/usercheck", method = RequestMethod.POST)
    public @ResponseBody
    String userCheck(@ModelAttribute("userVO") UserManageVO userVO, 
    		       HttpServletRequest request, 
    		       HttpServletResponse response) throws BizException
    {
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : userVO" + userVO);

		int retVal=this.userManageSvc.getCheckCnt(userVO);
		
		//log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");

      return ""+retVal;
    }
    
    /**
     * 고객 관리 화면 로딩
     *
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/custmanage")
    public ModelAndView custManage(HttpServletRequest request, 
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
    		
        	mv.setViewName("/admin/loginForm");
       		return mv;
		}
        
        CustManageVO custConVO = new CustManageVO();
        
        custConVO.setGroupId(strGroupId);

        // 조회조건저장
        mv.addObject("custConVO", custConVO);

        //조직정보 조회
        GroupVO group = new GroupVO();
        group.setGroupId(strGroupId);
        List<GroupVO> group_comboList = commonSvc.getGroupComboList(group);
        mv.addObject("group_comboList", group_comboList);
       
        mv.setViewName("/manage/custManage");
        
       //log Controller execute time end
      	long t2 = System.currentTimeMillis();
      	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
      	
        return mv;
    }
    /**
     * 고객관리 목록조회
     * 
     * @param UserManageVO
     * @param request
     * @param response
     * @param model
     * @param locale
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/manage/custpagelist")
    public ModelAndView custPageList(@ModelAttribute("custConVO") CustManageVO custConVO, 
    		                         HttpServletRequest request, 
    		                         HttpServletResponse response) throws BizException 
    {
        
    	//log Controller execute time start
		String logid=logid();
		long t1 = System.currentTimeMillis();
		logger.info("["+logid+"] Controller start : userConVO" + custConVO);

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
    		
        	mv.setViewName("/admin/loginForm");
       		return mv;
		}
        
        List<CustManageVO> custList = null;

        // 사용여부 값 null 일때 공백처리
        if (custConVO.getCon_useYn() == null) {
        	custConVO.setCon_useYn("Y");
        }
        
        // 조회조건 null 일때 공백처리
        if (custConVO.getSearchGubun() == null) {
        	custConVO.setSearchGubun("02");
        }
        
        // 조회값 null 일때 공백처리
        if (custConVO.getSearchValue() == null) {
        	custConVO.setSearchValue("system");
        }
        
        // 조회조건저장
        mv.addObject("custConVO", custConVO);

        // 페이징코드
        custConVO.setPage_limit_val1(StringUtil.getCalcLimitStart(custConVO.getCurPage(), custConVO.getRowCount()));
        custConVO.setPage_limit_val2(StringUtil.nvl(custConVO.getRowCount(), "10"));
        
        // 고객목록조회
        custList = custManageSvc.getCustList(custConVO);
        mv.addObject("custList", custList);

        // totalCount 조회
        String totalCount = String.valueOf(custManageSvc.getCustCnt(custConVO));
        mv.addObject("totalCount", totalCount);

        mv.setViewName("/manage/custPageList");
        
        //log Controller execute time end
       	long t2 = System.currentTimeMillis();
       	logger.info("["+logid+"] Controller end execute time:[" + (t2-t1)/1000.0 + "] seconds");
       	
        return mv;
    }
    
}
