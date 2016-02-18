package com.offact.framework.view;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.offact.salesb.controller.HomeController;
import com.offact.framework.constants.CodeConstant;
import com.offact.framework.util.ImageUtil;

/**
 * blob view
 * @author lim
 * @since  2013.1.25
 * @version 1.0
 */
public class CustomBlobView extends AbstractView{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
										   HttpServletRequest request, 
										   HttpServletResponse response) throws Exception {
		// 이미지 resize
		int maxWidth = CodeConstant.THUMBNAIL_WIDTH;
		
		byte[] bytes = (byte[])model.get("bytes");
		
		if(bytes.length > 0) {
			
			// byte --> InputStream 타입으로 변환
			InputStream is = new ByteArrayInputStream(bytes);
			
			String contentType = URLConnection.guessContentTypeFromStream(is);
			logger.debug("contentType: " + contentType);
			
			// 이미지일때만 처리한다. 그렇지않으면?
			if ( contentType.equals("image/jpeg") || 
				 contentType.equals("image/png")  ||
				 contentType.equals("image/bmp")  ||
				 contentType.equals("image/gif") ) {
				
				boolean isThumbnail = (Boolean)model.get("isThumbnail");
				logger.debug("isThumbnail : " + isThumbnail);
//				썸네일 경우에도 원본 이미지가  게시판 목록 사이즈보다 작을 경우 축소시키지 않는다.
				if (isThumbnail) {
					bytes = ImageUtil.makeThumbnailImage(bytes, maxWidth);
					is = new ByteArrayInputStream(bytes);
				}
			}
				
			response.setContentType(contentType);
			response.setContentLength((int)bytes.length);
			
			ServletOutputStream os = response.getOutputStream();
			
			FileCopyUtils.copy(is, os);
			
			os.flush();			
		}
	}

}
