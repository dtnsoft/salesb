package com.offact.salesb.service.impl.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import kr.co.udid.payapp.module.PayappAccountData;
import kr.co.udid.payapp.module.PayappAccountResult;
import com.offact.salesb.service.order.PayappService;


/**
 * 패이앱 결재 Sv
 * 
 * @author RED
 * 
 */
@Service 
public class PayappServiceImpl implements PayappService
{
	/**
	 * 간단 결재 요청
	 * 
	 * @param payappID
	 * @param accountData
	 * @return
	 * @throws NotSetRequiredDataException
	 * @throws IOException
	 * @see red.udid.model.payapp.PayappSv#simpleAccount(red.udid.model.payapp.data.PayappInfo,
	 *      red.udid.model.payapp.data.PayappAccountData)
	 */
	@Override
	public PayappAccountResult requestAccount (String payappID, PayappAccountData accountData) throws IOException
	{
		String url = "http://api.payapp.kr/oapi/apiLoad.html";

		HttpClient client = HttpClientBuilder.create ().build ();
		HttpPost post = new HttpPost (url);

		// add header
		post.setHeader ("User-Agent", "Payapp Java Module");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair> ();
		
		urlParameters.add (new BasicNameValuePair ("cmd", "payrequest"));
		urlParameters.add (new BasicNameValuePair ("userid", payappID));
		urlParameters.add (new BasicNameValuePair ("goodname", accountData.getGoodname ()));
		urlParameters.add (new BasicNameValuePair ("price", String.valueOf (accountData.getPrice ())));
		urlParameters.add (new BasicNameValuePair ("recvphone", accountData.getRecvphone ()));
		urlParameters.add (new BasicNameValuePair ("memo", accountData.getMemo ()));
		
		// 피드백 URL
		if (!isEmptyStr (accountData.getFeedBackUrl  ()))
			urlParameters.add (new BasicNameValuePair ("feedbackurl", accountData.getFeedBackUrl ()));
		
		// 임의 변수 1
		if (!isEmptyStr (accountData.getVal1  ()))
			urlParameters.add (new BasicNameValuePair ("var1", accountData.getVal1 ()));
		
		// 임의 변수 2
		if (!isEmptyStr (accountData.getVal2  ()))
			urlParameters.add (new BasicNameValuePair ("var2", accountData.getVal2 ()));
		
		// SMS 발송 여부
		if (!accountData.isSendSms ())
			urlParameters.add (new BasicNameValuePair ("smsuse", "n"));

		post.setEntity (new UrlEncodedFormEntity (urlParameters));

		HttpResponse response = null;

		try
		{
			response = client.execute (post);
		}
		catch (ClientProtocolException e)
		{
		}

		BufferedReader rd = new BufferedReader (new InputStreamReader (response.getEntity ().getContent ()));

		// 결과를 받아온다.
		String line = rd.readLine ();
		
		rd.close ();
		
		String state = "";
		String errorMessage = "";
		String mulNo = "";
		String payUrl = "";
		String errNo = "";
		
		// 결과 데이터 파싱
		String [] responseDatas = line.split ("&");
		
		for (String r : responseDatas)
		{
			String [] keyData = r.split ("=");
			
			if (keyData.length != 2)
				continue;

			// 상태값
			if (keyData[0].equals ("state"))
				state = keyData[1];
			// 에러 메시지
			else if (keyData[0].equals ("errorMessage"))
				errorMessage = URLDecoder.decode (keyData[1], "UTF-8");
			else if (keyData[0].equals ("mul_no"))
				mulNo = keyData[1];
			// 페이앱 URL
			else if (keyData[0].equals ("payurl"))
				payUrl = URLDecoder.decode (keyData[1], "UTF-8");
			// 에러 번호
			else if (keyData[0].equals ("errno"))
				errNo = keyData[1];
		}
		
		// 결과를 생성한다.
		return new PayappAccountResult (state.equals ("1"), errorMessage, payUrl, mulNo, errNo);
	}

	/**
	 * 문자열 null 체크  null 이거나 빈공백이면 true 리턴 
	 * @param str
	 * @return
	 */
	private static boolean isEmptyStr (String str)
	{
		if (str == null)
			return true;
		
		if (str.equals (""))
			return true;
		
		return false;
	}
}
