package com.offact.framework.jsonrpc;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RpcHelper {

	private final Logger logger = Logger.getLogger(getClass());

	protected Map callRpcPost(String host, int port, String protocol, String url, Map<String, String> data) throws Exception {

		logger.debug(this.getClass() + " callRpcPost data==>" + data);

		RequestHelper requestHelper = new RequestHelper();

		// TODO: 여기서 만든 ID 와 호출 후 받아온 ID 를 비교해서 유효성 검사를 해야됨
		String requestId = this.makeId();
		data.put("id", requestId);

		String resultStr = requestHelper.sendHttpPost(host, port, protocol, url, data);
		
		logger.debug(this.getClass() + " callRpcPost resultStr==> " + resultStr);

		Map resultMap = this.getJSONObjectByString(resultStr);

		return resultMap;
	}

	public Map callBillingRpcPost(Map<String, String> data) throws Exception {
//		return this.callRpcPost("HOST", "PORT", "PROTOCOL", "URL", data);	// 각각에 맞게 설정을 넣어줌
		return this.callRpcPost("servicerpc-int.tmon.co.kr", 80, "http", "/rpc", data);	// 각각에 맞게 설정을 넣어줌
	}

	public Map callServiceRpcPost(Map<String, String> data) throws Exception {
//		return this.callRpcPost("HOST", "PORT", "PROTOCOL", "URL", data);	// 각각에 맞게 설정을 넣어줌
		return this.callRpcPost("servicerpc-int.tmon.co.kr", 80, "http", "/rpc", data);	// 각각에 맞게 설정을 넣어줌
	}


	public Map callServiceRpcPost(HashMap<String, String> metaData,  Map<String, String> data) throws Exception {
		String strUrl = metaData.get("strUrl");
		int intPort = Integer.parseInt(metaData.get("intPort"));
		String strProtocol = metaData.get("strProtocol");
		String strPath = metaData.get("strPath");
		logger.info(metaData);
		return this.callRpcPost(strUrl, intPort, strProtocol, strPath, data);	// 각각에 맞게 설정을 넣어줌
	}

	public Map callServiceRpcPost(Map<String, String> data, String host) throws Exception {
//		return this.callRpcPost("HOST", "PORT", "PROTOCOL", "URL", data);	// 각각에 맞게 설정을 넣어줌
		return this.callRpcPost(host, 80, "http", "/rpc", data);	// 각각에 맞게 설정을 넣어줌
	}
	
	private String makeId() {
		int id = new Random().nextInt() + 1;
		if ( id < 0 ) {
			id = id * -1;
		}
		return String.valueOf(id);
	}

	public Map getJSONObjectByString(String str) {

		ObjectMapper mapper = new ObjectMapper();
		Map result = null;

		try {
			result = mapper.readValue(str, HashMap.class);
		} catch (Exception e) {
			//logger.error(e.getMessage());
		}

		return result;
	}

	public String getJSONStringByObject(Object data) {

		ObjectMapper mapper = new ObjectMapper();
		String result = "";

		try {
			result = mapper.writeValueAsString(data);
		} catch (Exception e) {
			//logger.error(e.getMessage());
		}

		return result;
	}
}
