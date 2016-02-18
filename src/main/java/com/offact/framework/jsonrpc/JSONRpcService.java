package com.offact.framework.jsonrpc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

public class JSONRpcService {

	private final Logger logger = Logger.getLogger(getClass());

	public String callServiceRpc(HashMap<String, String> data, String cls, String method){

		logger.info(this.getClass() + " callServiceRpc data==>" + data);

//		String cls		= "className";	// 호출할 class 명
//		String method	= "methodName";	// 호출할 method 명

		String pre = "params[";
		String post = "]";

		Map<String, String> requestParams = new HashMap<String, String>();
		StringBuffer service = new StringBuffer();
		Iterator<String> iter = data.keySet().iterator();
		while ( iter.hasNext() ) {
			String key = iter.next();
			String value = data.get(key);

			service.append(pre);
			service.append(key);
			service.append(post);

			requestParams.put(service.toString(), value);

			service.setLength(0);
		}

		requestParams.put("cls", cls);
		requestParams.put("method", method);

		RpcHelper rpcHelper = new RpcHelper();
		Map result = null;
		try {
			result = rpcHelper.callServiceRpcPost(requestParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.makeResultString(result);
	}




	public String callServiceRpc(HashMap<String, String> metaData,HashMap<String, String> data, String cls, String method){

		logger.info(this.getClass() + " callServiceRpc data==>" + data);

//		String cls		= "className";	// 호출할 class 명
//		String method	= "methodName";	// 호출할 method 명

		String pre = "params[";
		String post = "]";

		Map<String, String> requestParams = new HashMap<String, String>();
		StringBuffer service = new StringBuffer();
		Iterator<String> iter = data.keySet().iterator();
		while ( iter.hasNext() ) {
			String key = iter.next();
			String value = data.get(key);

			service.append(pre);
			service.append(key);
			service.append(post);

			requestParams.put(service.toString(), value);

			service.setLength(0);
		}

		requestParams.put("cls", cls);
		requestParams.put("method", method);

		RpcHelper rpcHelper = new RpcHelper();
		Map result = null;
		try {


			result = rpcHelper.callServiceRpcPost(metaData,requestParams);



		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.makeResultString(result);
	}



	public String callBillingRpc(HashMap<String, String> data, String cls, String method) throws Exception {

//		String cls		= "className";	// 호출할 class 명
//		String method	= "methodName";	// 호출할 method 명

		data.put("cls", cls);
		data.put("method", method);

		RpcHelper rpcHelper = new RpcHelper();
		Map result = rpcHelper.callBillingRpcPost(data);

		return this.makeResultString(result);
	}

	protected String makeResultString(Map data) {

		RpcHelper rpcHelper = new RpcHelper();

		Map tmp = (Map) data.get("error");
		if ( tmp != null ) {
			Map result = new HashMap();
			result.put("message", tmp.get("message"));
			result.put("result", false);

			return rpcHelper.getJSONStringByObject(result);
		}
		else {
			if ( data.containsKey("result") ) {
				if ( data.get("result") instanceof String ) {
					return (String) data.get("result");
				}
				else if ( data.get("result") instanceof Boolean ) {
					return ((Boolean) data.get("result")).toString();
				}
				return rpcHelper.getJSONStringByObject(data.get("result"));
			}
		}

		return "";
	}
	
	public String callServiceRpc(HashMap<String, String> data, String host, String cls, String method){

		logger.info(this.getClass() + " callServiceRpc data==>" + data);
		
//		String cls		= "className";	// 호출할 class 명
//		String method	= "methodName";	// 호출할 method 명
		
		String pre = "params[";
		String post = "]";
		
		Map<String, String> requestParams = new HashMap<String, String>();
		StringBuffer service = new StringBuffer();
		Iterator<String> iter = data.keySet().iterator();
		while ( iter.hasNext() ) {
			String key = iter.next();
			String value = data.get(key);

			service.append(pre);
			service.append(key);
			service.append(post);
			
			requestParams.put(service.toString(), value);
			
			service.setLength(0);
		}
		
		requestParams.put("cls", cls);
		requestParams.put("method", method);

		RpcHelper rpcHelper = new RpcHelper();
		Map result = null;
		try {
			result = rpcHelper.callServiceRpcPost(requestParams, host);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.makeResultString(result);
	}
}
