/**
 *
 */
package com.offact.salesb.service.common;

import org.json.simple.JSONArray;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.common.EmailVO;
/**
 * @author 4530
 *
 */
public interface MailService {

	public boolean sendMail(EmailVO mail)  throws BizException;

}
