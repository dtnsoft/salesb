package kr.co.udid.payapp.module;

import java.io.IOException;

/**
* 패이앱 Sv
 * @author RED
 *
 */
public interface PayappSv
{
	/**
	 * 페이앱 결제 요청
	 * 
	 * @param payappID : 페이앱 아이디
	 * @param accountData : 결재 정보
	 * @return
	 * @throws NotSetRequiredDataException 
	 * @throws IOException 
	 */
	PayappAccountResult requestAccount (String payappID, PayappAccountData accountData) throws IOException;

}
