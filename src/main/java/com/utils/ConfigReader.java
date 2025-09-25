package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties properties;
	
	//load properties file
	 static {
		
		properties = new Properties();
		
		try {
			FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
			properties.load(ip);
		}
		catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load config.properties file");
		}
	}
	
	//get configuration from property file
	public static String getConfig(String key) {
		return properties.getProperty(key);
	}
}
