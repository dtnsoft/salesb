package com.offact.salesb.service.impl.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.salesb.service.common.BatchService;

/**
 * @author 4530
 *
 */
@Service
public class BatchServiceImpl implements BatchService {

    private final Logger 			
    batchloger = Logger.getLogger("batchlog");

	@Autowired
	private SqlSessionCommonDao commonDao;

	@Override
	public int insertBatchTest1() throws BizException {
			  return commonDao.insert("Batch.insertBatchTest1");

	}

	@Override
	public void insertBatchTest2(JSONArray arrayGetAllCategory) throws BizException {
		
		Object arryObjGetAllCategory=null;
		for (int i=0;i<arrayGetAllCategory.size();i++){
			arryObjGetAllCategory = JSONValue.parse(arrayGetAllCategory.get(i).toString());
			batchloger.debug("INSERT : " + arryObjGetAllCategory);
            commonDao.insert("Batch.insertTbPreCategory",arryObjGetAllCategory);
		}
		batchloger.debug("EXECUTE QUERY : Batch.insertBatchTest2");
		commonDao.insert("Batch.insertBatchTest2");
	}

}
