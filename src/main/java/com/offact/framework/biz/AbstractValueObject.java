package com.offact.framework.biz;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
/**
 * Bean 객체를 위한 Value Object 추상클래스
 * @author lim
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractValueObject
	implements Serializable {
	
	/**
	 * toString()에서 VO의 값을 출력할때 제외 되어야할 값의 이름 셋을 보관합니다.
	 */
	private static final Set<String>	SKIP_PROP_NAME_SET	= new HashSet<String>();
	
	static {
		SKIP_PROP_NAME_SET.add("class");
	}

	/**
	 * Bean 객체를 String 형태로 제공
	 */
	@SuppressWarnings("unchecked")
	public String toString() {
		Map<String, ?>	beanProps;
		StringBuffer	rv;
		
		try {
			beanProps	= BeanUtils.describe(this);
		} catch (Exception e) {
			throw new RuntimeException("bean to string fail.", e);
		}
		rv	= new StringBuffer();
		rv.append(this.getClass().getName()).append('@').append(Integer.toHexString(this.hashCode())).append("[\n");
		for (Entry<String, ?> ent : beanProps.entrySet()) {
			String	propName;
			Object	propValue;
			String	propValueStr;
			
			propName		= ent.getKey();
			if (SKIP_PROP_NAME_SET.contains(propName)) {
				continue;
			}
			propValue		= ent.getValue();
			propValueStr	= propValue == null ? "[NULL]" : propValue.toString();
			rv.append(propName).append('=').append(propValueStr).append('\n');
		}
		rv.append("]\n");
		return rv.toString();
	}
}
