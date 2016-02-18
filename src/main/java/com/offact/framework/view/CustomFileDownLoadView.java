package com.offact.framework.view;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class CustomFileDownLoadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		byte[] bytes = (byte[])model.get("bytes");
		String fileName = (String)model.get("fileName");
		if(bytes.length > 0) {
			
			// byte --> InputStream 타입으로 변환
			InputStream is = new ByteArrayInputStream(bytes);
			
			String contentType = URLConnection.guessContentTypeFromStream(is);
			logger.debug("contentType: " + contentType);
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setContentType(contentType);
			response.setContentLength((int)bytes.length);
			response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			ServletOutputStream os = response.getOutputStream();
			
			FileCopyUtils.copy(is, os);
			
			os.flush();
		}
	}
	
}
