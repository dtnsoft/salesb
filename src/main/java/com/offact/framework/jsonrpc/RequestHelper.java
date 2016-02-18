package com.offact.framework.jsonrpc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class RequestHelper {

	private Logger logger = Logger.getLogger(getClass());

	public String sendHttpPost(String destURL, int port, String protocol, String url, Map<String, String> data) throws Exception {
		logger.debug(this.getClass() + " sendHttpPost data==>" + data);
		
		HttpHost targetHost = new HttpHost(destURL, port, protocol);
		HttpPost post = new HttpPost(url);

		int requestID = new Random().nextInt() + 1;
		if ( requestID < 0 ) {
			requestID = requestID * -1;
		}

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> keys = data.keySet();
		Iterator<String> iter = keys.iterator();
		while ( iter.hasNext() ) {
			String k = iter.next();
			String v = data.get(k);
			nvps.add(new BasicNameValuePair(k, v));
		}

        post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

		DefaultHttpClient httpclient = new DefaultHttpClient();

		String result = "";

		try {
			logger.debug(this.getClass() + " sendHttpPost data==> STATE 00 destURL:" + destURL +" port :" + port+" protocol :"+protocol);
			HttpResponse response = httpclient.execute(targetHost, post);
			logger.debug(this.getClass() + " sendHttpPost data==> STATE 01 response)" + response);
			logger.debug(this.getClass() + " sendHttpPost data==> STATE 01 response.getEntity()" + response.getEntity());
			result = EntityUtils.toString(response.getEntity());
			logger.debug(this.getClass() + " sendHttpPost data==> STATE 02 result :" + result);
		} catch(Exception e){
			logger.error("connection error : "+e);
		}finally {
			httpclient.getConnectionManager().shutdown();
		}
		
		return result;
	}
}
