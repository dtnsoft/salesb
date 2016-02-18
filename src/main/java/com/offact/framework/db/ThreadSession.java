package com.offact.framework.db;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

//import kr.or.cs.util.lang.BSSLoggingValueObject;

/**
 * 쓰레드 영역 안에서만 유효한 정보들을 유지하게 하는 객체
 * 이 객체의 사용은 소스상에서 데이터 전달의 흐름이 나타나지 않기 때문에
 * 시스템 차원의 데이터 처리가 아닌 일반 업무 개발에서의 사용은 지양해야 합니다.
 * @author lim
 */

public class ThreadSession {
	
	private static final Map<Thread, ThreadSession>	sessionMap	= new HashMap<Thread, ThreadSession>();
	
	private Thread			thread;
	private Locale			userLocale;
	private String			actionStep;
	private String			serviceStep;
	private String			pkey;
	private String			queryId;
	private boolean			transactionBegin;
	private String			dataSourceId;
	private String			userId;
	private String			userSlot;
	//private BSSLoggingValueObject bss;
	
	private ThreadSession(Thread thread) {
		this.thread	= thread;
//		this.bss = new BSSLoggingValueObject();
	}
	
	/**
	 * 쓰레드 세션을 만료 시킵니다.
	 */
	public static void doExpire() {
		synchronized (sessionMap) {
			sessionMap.remove(Thread.currentThread());
		}
	}


	/**
	 * 쓰레드에 속성용 맵을 할당하고 반환합니다.
	 * @return
	 */
	public static ThreadSession getSession() {
		Thread			thread;
		ThreadSession	rv;
		
		thread	= Thread.currentThread();
		synchronized (sessionMap) {
			rv		= sessionMap.get(thread);
			if (rv == null) {
				rv	= new ThreadSession(thread);
				sessionMap.put(thread, rv);
			}
		}
		return rv;
	}
	
	/**
	 * 모든 쓰레드에 설정된 속성들을 제거 합니다.
	 */
	public static void claearAll() {
		synchronized (sessionMap) {
			sessionMap.clear();
		}
	}
	
	/**
	 * 쓰레드 세션의 대상 쓰레드 반환
	 * @return
	 */
	public Thread getThread() {
		return thread;
	}

	/**
	 * 사용자 로케일 반환
	 * @return
	 */
	public Locale getUserLocale() {
		return userLocale;
	}

	/**
	 * 사용자 로케일 설정
	 * @param userLocale
	 */
	public void setUserLocale(Locale userLocale) {
		this.userLocale = userLocale;
	}
	
	/**
	 * 액션의 스텝을 설정합니다.
	 * @param step
	 */
	public void setActionStep(String step) {
		this.actionStep	= step;
	}
	
	/**
	 * 액션의 스텝을 얻습니다.
	 * @return
	 */
	public String getActionStep() {
		return this.actionStep;
	}
	
	/**
	 * 서비스의 스텝을 설정합니다.
	 * @param step
	 */
	public void setServiceStep(String step) {
		this.serviceStep	= step;
	}
	
	/**
	 * 서비스의 스텝을 얻습니다.
	 * @return
	 */
	public String getServiceStep() {
		return this.serviceStep;
	}
	
	/**
	 * 요청 내용의 프라이머리 키 설정
	 * @param pkey
	 */
	public void setPrimaryKey(String pkey) {
		this.pkey	= pkey;
	}
	
	/**
	 * 요청 내영의 프라이머리 키 조회
	 * @return
	 */
	public String getPrimaryKey() {
		return this.pkey;
	}
	
	/**
	 * 로그용 쿼리 아이디 설정
	 * @return
	 */
	public String getQueryId() {
		return queryId;
	}

	/**
	 * 로그용 쿼리 아이디 설정
	 * @param queryId
	 */
	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isTransactionBegin() {
		return transactionBegin;
	}

	/**
	 * 
	 * @param transactionBegin
	 */
	public void setTransactionBegin(boolean transactionBegin) {
		this.transactionBegin = transactionBegin;
	}

	/**
	 * 
	 * @return
	 */
	public String getDataSourceId() {
		return dataSourceId;
	}

	/**
	 * 
	 * @param dataSourceId
	 */
	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	/*
	public BSSLoggingValueObject getBss() {
		return bss;
	}
	*/

	/*
	public void setBss(BSSLoggingValueObject bss) {
		this.bss = bss;
	}
	*/

	/**
	 * 
	 * @return
	 */
	public String getUserSlot() {
		return userSlot;
	}

	/**
	 * 
	 * @param userSlot
	 */
	public void setUserSlot(String userSlot) {
		this.userSlot = userSlot;
	}

	/**
	 * 
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
