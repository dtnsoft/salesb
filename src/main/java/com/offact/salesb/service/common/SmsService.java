/**
 *
 */
package com.offact.salesb.service.common;

import org.json.simple.JSONArray;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.common.SmsVO;
/**
 * @author 4530
 *
 */
public interface SmsService {

	public SmsVO sendSms(SmsVO sms)  throws BizException;

}
