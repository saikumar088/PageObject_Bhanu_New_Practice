package com.selenium.sai.framework.helper.listner;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import com.selenium.sai.framework.helper.logger.LoggerHelper;

public class Retry implements IRetryAnalyzer {
     private int retryCount=0;
	 private int maxRetryCount =3;
		private Logger log = LoggerHelper.getLogger(Retry.class);
		
	public boolean retry(ITestResult result) {
		if(retryCount<maxRetryCount) {
			log.info("Retrying test "+result.getName()+" with status "+getResultStatusName(result.getStatus())+ " for the " +(retryCount+1)+  "  times.  ");
			retryCount++;
			return true;
		}
		return false;
	}
	
/**
 * 
 * 
 * This method is for logging purpose for the method retry
 * @param status
 * @return
 */
	public String getResultStatusName(int status) {
		
		String ResultName=null;
		
		if(status==1) {	
			ResultName="SUCCESS";
		}
		if(status==2) {	
			ResultName="FAILURE";
		}
		if(status==3) {	
			ResultName="SKIP";
		}
		return ResultName;
		
	}

}
