package com.orangehrm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class ConfigReader {

	private static final String CONFIG_FILE_PATH = System.getProperty("user.dir") + "\\resources\\config.properties";
	private static Properties properties;

	static {
		properties = new Properties();
		try {
			FileInputStream fi = new FileInputStream(CONFIG_FILE_PATH);
			if (fi != null) {
				properties.load(fi);
				fi.close();
			} else {
				throw new RuntimeException("config.properties not found!");
			}
		} catch (Exception e) {
			throw new RuntimeException("failed to read config.properties");

		}
	}

	public static String getProperties(String key) {
		return properties.getProperty(key);
	}
	public static int getImplicitWait() {
		return Integer.parseInt(properties.getProperty("implicit.wait"));
	}
	public static int getExplicitWait() {
		return Integer.parseInt(properties.getProperty("explicit.wait"));
	}
	public static String getDefaultUserName() {
		return properties.getProperty("default.username");
	}
	public static String getDefaultPassword() {
		return properties.getProperty("default.password");
	}
	public static String getDefaultInvalidUserName() {
		return properties.getProperty("invalid.username");
	}
	public static String getDefaultInvalidPassword() {
		return properties.getProperty("invalid.password");
	}
	public static String getLoginURL() {
		return properties.getProperty("url.login");
	}

	


	

}
