package com.offact.framework.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
	
	/**
	 * 이미지를 받아 지정된 크기 이상일 경우 resize 한다.
	 * @param imageContent
	 * @param maxWidth
	 * @return
	 * @throws IOException
	 */
	public static byte[] makeThumbnailImage(byte[] imageContent, int maxWidth)
			throws IOException {

		BufferedImage originalImg = ImageIO.read(new ByteArrayInputStream(
				imageContent));

		// get the center point for crop
		int[] centerPoint = { originalImg.getWidth() / 2,
				originalImg.getHeight() / 2 };

		// calculate crop area
		int cropWidth = originalImg.getWidth();
		int cropHeight = originalImg.getHeight();
		/*
		 * if( cropHeight > cropWidth * xyRatio ) { //long image cropHeight =
		 * (int) (cropWidth * xyRatio); } else { //wide image cropWidth = (int)
		 * ( (float) cropHeight / xyRatio) ; }
		 */
		// set target image size
		int targetWidth = cropWidth;
		int targetHeight = cropHeight;
		double xyRatio = (double) originalImg.getHeight()
				/ originalImg.getWidth();
		
		if (targetWidth > maxWidth) {
			// too big image
			targetWidth = maxWidth;
			targetHeight = (int) (targetWidth * xyRatio);
		}

		// processing image
		BufferedImage targetImage = new BufferedImage(targetWidth,
				targetHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = targetImage.createGraphics();
		graphics2D.setBackground(Color.WHITE);
		graphics2D.setPaint(Color.WHITE);
		graphics2D.fillRect(0, 0, targetWidth, targetHeight);
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(originalImg, 0, 0, targetWidth, targetHeight,
				centerPoint[0] - (int) (cropWidth / 2), centerPoint[1]
						- (int) (cropHeight / 2), centerPoint[0]
						+ (int) (cropWidth / 2), centerPoint[1]
						+ (int) (cropHeight / 2), null);

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(targetImage, "png", output);

		return output.toByteArray();
	}
}
