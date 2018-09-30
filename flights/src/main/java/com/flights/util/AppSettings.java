package com.flights.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Class to get the basic Application Settings stored in application.properties
 * file. Here there is getInstance() method which is called from application
 * web initialize listener to load the file settings in properties object instance.
 * Then with getSetting() method you can have any setting value.
 * @author VISHAL
 *
 */
public class AppSettings {
	static final Logger logger = Logger.getLogger(AppSettings.class);
	private static final String APP_SETTINGS_FILE = "application.properties";
	private static AppSettings instance;
	private Properties settings;
	
	private AppSettings() {
		
	}
	
	/**
	 * To be called in web context initialized listener
	 * @return AppSettings object having properties loaded from file
	 * @throws IOException if the file application.properties does not exist
	 */	
	public static AppSettings getInstance() throws IOException  {
		
		if (instance == null) {
			instance = new AppSettings();
			instance.settings = new Properties();
			InputStream inputStream = AppSettings.class.getClassLoader()
					.getResourceAsStream(APP_SETTINGS_FILE);
			instance.settings.load(inputStream);			
		}
		return instance;
	}
	
	/**
	 * To be called in web context initialized listener in context OTHER THAN epctx <br>
	 * If you are calling this method directly, without calling getInstance() method,
	 * then it is also OK, because this method already have a call of that method
	 * @param fileName String pass file name in the context for properties to load from
	 * @return AppSettings object having properties loaded from file
	 * @throws IOException if the file 'fileName' does not exist
	 */	
	public static AppSettings getInstance(String fileName) throws IOException  {
		
		// called this method to set default settings from epctx
		getInstance();
		
		InputStream inputStream = AppSettings.class.getClassLoader()
				.getResourceAsStream(fileName);
		Properties contextProperties = new Properties();
		contextProperties.load(inputStream);
		Iterator<Object> keys = contextProperties.keySet().iterator();
		
		while(keys.hasNext()) {
			String key = (String) keys.next();			
			instance.put(key, contextProperties.getProperty(key));
		}
		
		return instance;
	}
	
	public String getValue(String key, String defaultValue) {
		return this.settings.getProperty(key, defaultValue);
	}
	
	public String getValue(String key) {
		return this.settings.getProperty(key);
	}
	
	/**
	 * Get the Application Setting value from application.properties file
	 * @param key String
	 * @return String settingValue
	 */
	public static String getSetting(String key) {
		try {
			return AppSettings.getInstance().getValue(key);
		} catch (IOException ex) {
			return "";
		}
	}
	
	/**
	 * Get the Application Setting value from application.properties file
	 * also you can pass the defaultValue where it will return the same if 
	 * the key does not exist in file
	 * @param key String key
	 * @return String settingValue
	 */
	public static String getSetting(String key, String defaultValue) {
		try {
			return AppSettings.getInstance().getValue(key, defaultValue);
		} catch (IOException ex) {
			return defaultValue;
		}
	}
	public void clear() throws Throwable {
		try{
			settings.clear();
			settings = null;
			instance = null;
		}catch(Exception e){
			logger.error("Exception in finalize of AppSettings ",e);
		}
	}
	
	public void put(String key, String value) {
		this.settings.put(key, value);
	}
}