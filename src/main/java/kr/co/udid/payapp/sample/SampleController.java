package kr.co.udid.payapp.sample;

import java.io.IOException;

import kr.co.udid.payapp.module.PayappAccountData;
import kr.co.udid.payapp.module.PayappAccountResult;
import kr.co.udid.payapp.module.PayappSv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 샘플 컨트롤러
 * @author RED
 *
 */
@Controller
public class SampleController
{
	/**
	 * 페이앱 서비스
	 */
	@Autowired private PayappSv payappSv;
	
	/**
	 * 인덱스
	 * @return
	 */
	@RequestMapping(value="/index.do")
	public String index ()
	{
		return "index"; 
	}
	
	/**
	 * 결제 요청 시작
	 * @param goodname
	 * @param price
	 * @param recvphone
	 * @param memo
	 * @return
	 */
	@RequestMapping(value="/requestPayapp.do")
	public ModelAndView requestPayappTest (
			
			/**
			 * 상품명
			 */
			@RequestParam(value="goodname", defaultValue="") String goodname
			
			/**
			 * 상품가격
			 */
			, @RequestParam(value="price", defaultValue="0") Integer price
			
			/**
			 * 연락처
			 */
			, @RequestParam(value="recvphone", defaultValue="") String recvphone
			
			/**
			 * 메모
			 */
			, @RequestParam(value="memo", defaultValue="") String memo
			)
	{
		ModelAndView mv = new ModelAndView ("requestPayapp"); 
		
		// 페이앱 아이디를 입력해주세요
		final String payappID = "payapptest";
		
		// 결제 요청 데이터
		// 필수 : 상품명, 상품 가격, 상대방 전화번호
		PayappAccountData accountData = new PayappAccountData (goodname, price.intValue (), recvphone);
		
		// 피드백 URL
		accountData.setFeedBackUrl ("");
		
		// SMS 발송 여부
		accountData.setSendSms (true);
		
		// 메모 설정
		accountData.setMemo (memo);
		
		// 에러 메시지
		String errorMsg = "";
		
		try
		{
			// 결제를 요청한다.
			PayappAccountResult result = payappSv.requestAccount (payappID, accountData);
			
			// 결제 성공 여부 판단.
			if (result.isSuccess ())
			{
				mv.addObject ("result", result);
			}
			else
			{
				errorMsg = result.getErrorMessage ();
			}
		}
		catch (IOException e)
		{
			errorMsg = "통신중 에러가 발생하였습니다.";
		}
		
		mv.addObject ("errorMsg", errorMsg);
		
		return mv; 
	}
}
