/**
 *
 */
package com.offact.salesb.service.common;

import org.json.simple.JSONArray;

import com.offact.framework.exception.BizException;

/**
 * @author 4530
 *
 */
public interface BatchService {

	public int insertBatchTest1() throws BizException;

	public void insertBatchTest2(JSONArray arrayGetAllCategory) throws BizException;

}
