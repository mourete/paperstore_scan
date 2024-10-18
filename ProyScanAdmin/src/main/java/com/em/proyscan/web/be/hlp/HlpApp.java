package com.em.proyscan.web.be.hlp;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.em.proyscan.web.be.app.Constants;

public class HlpApp {
	
	public static String getURLResources() {
		
		String config=Constants.APP_SCAN_HOME+ "/"+ Constants.APP_DIR_CONFIG +"/" +   Constants.APP_PROPERTIES_FILE; 
		System.out.println("config filename is : " + config);
		Properties prop = new Properties();
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(config);
		    prop.load(inStream);
		}catch(Exception e) {
		   e.printStackTrace();		
		}finally {
		    if (inStream != null) {
		    	try {
		        inStream.close();
		    	}catch(Exception e) {}
		    }
		}
		String name=prop.getProperty("url_resources");
		return name;
		 
	 }
 
 

}
