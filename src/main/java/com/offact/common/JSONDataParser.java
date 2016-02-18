/*================================
 * Class Name : JSONDataParser
 * Company    : (주)디지털베이시스템
 * Date		  : 2013-11-19
 * Engineer    : Hyeong-Geun, Jang (hgjang)
 * Description ---------------------------------------
 * 		JSON 파싱 관련 클래스
 * 		
 * History --------------------------------------------
 * 		2013-11-19		최초작성
 * ===============================*/
package com.offact.common;

import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONDataParser 
{
	// # JSON Parse 함수 > HashMap Return
	public HashMap<String, Object> jsonParse(String a_strJsonData, String a_strTagKey)
			throws Exception
	{
		String strKeyVal = "";
		Object objJsonData = "";
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();	
		hashMap = concatJsonData(a_strJsonData);
				
		Iterator<String> iterator = hashMap.keySet().iterator();
		
		while(iterator.hasNext())
		{
			strKeyVal = (String)iterator.next();
			objJsonData = hashMap.get(strKeyVal);
			
			if(strKeyVal.equals(a_strTagKey))
			{
				hashMap = concatJsonData(objJsonData.toString());			
				break;
			}
			else
			{
				hashMap = jsonParse(objJsonData.toString(), a_strTagKey);
			}
		}
						
		return hashMap;
	}
	
	public HashMap<String, Object> jsonParse(String a_strJsonData)
			throws Exception
	{
		HashMap<String, Object> hashmap = concatJsonData(a_strJsonData);
		return hashmap;
	}
	
	private HashMap<String, Object> concatJsonData(String a_strJsonData)
			throws Exception
	{
		String strKey = "";
		Object objVal = null;
		
		HashMap<String, Object> hMap = new HashMap<String, Object>();
		
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(a_strJsonData);
		JSONObject jsonObj = (JSONObject)obj;
		
		Iterator<String> iter = jsonObj.keySet().iterator();
				
		while(iter.hasNext())
		{
			strKey = (String)iter.next();
			objVal = jsonObj.get(strKey);
			
			hMap.put(strKey, objVal);
		}
				
		return hMap;
	}
}
