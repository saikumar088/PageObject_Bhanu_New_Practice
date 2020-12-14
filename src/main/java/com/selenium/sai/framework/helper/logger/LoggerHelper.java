package com.selenium.sai.framework.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.selenium.sai.framework.helper.resource.ResourceHelper;

public class LoggerHelper {
	
	private  static boolean root =false;
	
	
	public  static Logger getLogger(Class cls) {
		
		if (root) {		
			
			//if logger is intialized by some utility then it will return logger 
			return Logger.getLogger(cls);		
		}
		
		//to configure log4j properties we need to use below command
		PropertyConfigurator.configure(ResourceHelper.getResourcePath("\\src\\main\\resources\\configfile\\log4j.properties"));
		root=true;
		return Logger.getLogger(cls);		
	}
	



}
