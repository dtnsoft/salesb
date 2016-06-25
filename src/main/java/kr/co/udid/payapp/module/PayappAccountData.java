package kr.co.udid.payapp.module;

/**
 * Simple 패이앱 결제 데이터
 * @author RED
 *
 */
public class PayappAccountData
{
	/**
	 * 상품명
	 */
	private String goodname;
	
	/**
	 * 상품 금액
	 */
	private int price;
	
	/**
	 * 수신 핸드폰
	 */
	private String recvphone;
	
	/**
	 * 메모
	 */
	private String memo;
	
	/**
	 * 피드백 URL
	 */
	private String feedBackUrl;
	
	/**
	 * 추가 값1
	 */
	private String val1;
	
	/**
	 * 추가 값2
	 */
	private String val2;
	
	/**
	 * SMS 발송 여부
	 */
	private boolean sendSms;
	
	public PayappAccountData (String goodname, int price, String recvphone)
	{
		super ();
		this.goodname = goodname;
		this.price = price;
		this.recvphone = recvphone;
		
		this.sendSms = true;
	}

	public String getGoodname ()
	{
		return goodname;
	}

	public void setGoodname (String goodname)
	{
		this.goodname = goodname;
	}

	public int getPrice ()
	{
		return price;
	}

	public void setPrice (int price)
	{
		this.price = price;
	}

	public String getRecvphone ()
	{
		return recvphone;
	}

	public void setRecvphone (String recvphone)
	{
		this.recvphone = recvphone;
	}

	public String getMemo ()
	{
		return memo;
	}

	public void setMemo (String memo)
	{
		this.memo = memo;
	}

	public String getFeedBackUrl ()
	{
		return feedBackUrl;
	}

	public void setFeedBackUrl (String feedBackUrl)
	{
		this.feedBackUrl = feedBackUrl;
	}

	public String getVal1 ()
	{
		return val1;
	}

	public void setVal1 (String val1)
	{
		this.val1 = val1;
	}

	public String getVal2 ()
	{
		return val2;
	}

	public void setVal2 (String val2)
	{
		this.val2 = val2;
	}

	public boolean isSendSms ()
	{
		return sendSms;
	}

	public void setSendSms (boolean sendSms)
	{
		this.sendSms = sendSms;
	}
	
	
}
