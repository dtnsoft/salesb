package com.offact.framework.db;

/**
 * 아무일도 하지 않는 파라메터 조작 객체입니다.
 * 기본 마운트 용입니다.
 * @author lim
 *
 */
public class ParameterNoAffecter
	implements ParameterAffecter {
	
	/**
	 * 아무일도 하지 않습니다.
	 */
	public Object doAffectParameter(Object srcParam) {
		return srcParam;
	}
	
	/**
	 * 아무일도 하지 않습니다.
	 */
	public void doFeedbackParameter(Object srcParam, Object affectedParam) {
		// don't anything.
	}
}
