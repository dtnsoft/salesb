package com.offact.salesb.service.impl.history;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.salesb.service.history.SmsHistoryService;
import com.offact.salesb.vo.history.SmsHistoryVO;

import com.whois.whoisSMS;

import javax.mail.internet.*;

/**
 * @author 4530
 *
 */
@Service
public class SmsHistoryServiceImpl implements SmsHistoryService {

	private final Logger        logger = Logger.getLogger(getClass());

  @Autowired
    private SqlSessionCommonDao commonDao;

  @Override
  public List<SmsHistoryVO> getSmsHistoryPageList(SmsHistoryVO smscondition) throws BizException {
  	
      List<SmsHistoryVO> smsList = commonDao.selectList("SmsHistory.getSmsHistoryPageList", smscondition);

      return smsList;
  }

  @Override
  public int getSmsHistoryCnt(SmsHistoryVO smscondition) throws BizException {
      return commonDao.selectOne("SmsHistory.getSmsHistoryCnt", smscondition);
  }

}