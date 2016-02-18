package com.offact.salesb.service.impl.comunity;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonAdminDao;
import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.salesb.service.comunity.ComunityService;
import com.offact.salesb.vo.comunity.AsVO;
import com.offact.salesb.vo.comunity.ComunityVO;
import com.offact.salesb.vo.comunity.CounselVO;

/**
 * @author 4530
 *
 */
@Service
public class ComunityServiceImpl implements ComunityService {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SqlSessionCommonDao commonDao;

	@Autowired
	private SqlSessionCommonAdminDao commonSubDao;		//multi datasource test

	@Override
	public int commentInsert(ComunityVO comunity) throws BizException {
		return commonDao.update("Comunity.commentInsert", comunity);
	}
	
	@Override
	public List<ComunityVO> getComunity(ComunityVO comunity) throws BizException {
   	
       List<ComunityVO> comunityList = commonDao.selectList("Comunity.getComment", comunity);

       return comunityList;
	}
	
	@Override
	public List<ComunityVO> getComunityList(ComunityVO comunity) throws BizException {
   	
       List<ComunityVO> comunityList = commonDao.selectList("Comunity.getCommentList", comunity);

       return comunityList;
	}
	
	@Override
	public List<ComunityVO> getComunityListTalk(ComunityVO comunity) throws BizException {
   	
       List<ComunityVO> comunityList = commonDao.selectList("Comunity.getCommentListTalk", comunity);

       return comunityList;
	}
	
	@Override
	public int counselInsert(CounselVO counsel) throws BizException {
		return commonDao.update("Counsel.counselInsert", counsel);
	}
	
	@Override
	public List<CounselVO> getCounselList(CounselVO counsel) throws BizException {
   	
       List<CounselVO> counselList = commonDao.selectList("Counsel.getCounselList", counsel);

       return counselList;
	}
	
    @Override
    public int getCounselCnt(CounselVO counsel) throws BizException {
        return commonDao.selectOne("Counsel.getCounselCnt", counsel);
    }
    
    @Override
    public List<ComunityVO> getComunityReply(ComunityVO comunity) throws BizException {
    	
        List<ComunityVO> comunityList = commonDao.selectList("Comunity.getComunityReply", comunity);

        return comunityList;
    }

}
