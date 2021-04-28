package com.chen.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.chen.utils.ConfigManager;

public class ConfigManager {
private static Properties properties = null;
	
	
	static {
		InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream(
                "database.properties");
		
		if(is == null) {
			throw new RuntimeException("�Ҳ������ݿ���������ļ���");
		}
		properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e) {
			throw new RuntimeException("���ݿ����ò������ش���", e);
		}finally {
            try {
            	
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
	}
	

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
