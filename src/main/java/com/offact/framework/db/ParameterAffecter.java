package com.offact.framework.db;

/**
 * CommonDAO를 통한 MyBatis 제어시
 * 전달되는 파라메터에 영향을 주는 조작자 객체가 구현해야할 인터페이스 입니다.
 * @author pat
 *
 */
public interface ParameterAffecter {
	
	/**
	 * MyBatis 제어 이전에 파라메터를 조작 해야 할 코드를 구현하십시오. 
	 * @param srcParam 전단될 원본 파라메터 객체
	 * @return 조작되고 난 후의 파라메터 객체
	 */
	public Object doAffectParameter(Object srcParam);
	
	/**
	 * MyBatis 제어 이후에 파라메터를 조잘 해야 항 코드를 구현하십시오. 
	 * @param srcParam CommonDAO에 전달되었던 원본 파라메터 객체
	 * @param affectedParam doAffectParameter에 의해 조작 되었던 객체
	 */
	public void doFeedbackParameter(Object srcParam, Object affectedParam);

}

