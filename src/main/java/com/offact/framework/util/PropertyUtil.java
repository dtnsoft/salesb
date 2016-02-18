package com.offact.framework.util;

import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class PropertyUtil {

	public static String PropertiesRead(String filePath, String key) {
		Properties propertis = new Properties();
		
		try {
			FileInputStream in = new FileInputStream(filePath);
			propertis.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String result = propertis.getProperty(key);
		return result;
	}
	
	
}
