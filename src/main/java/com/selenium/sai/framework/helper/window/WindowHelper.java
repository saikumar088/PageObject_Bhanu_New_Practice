package com.selenium.sai.framework.helper.window;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.selenium.sai.framework.helper.logger.LoggerHelper;

public class WindowHelper {
		private WebDriver driver;
		private Logger log = LoggerHelper.getLogger(WindowHelper.class);
		
		public WindowHelper(WebDriver driver) {
			this.driver=driver;
			log.info("WindowHelper has been initialised.....");
		}
		/**
		 * 
		 * This method to use to switch to parent window
		 * 
		 */
		public void switchToParentWindow() {
			log.info("switching to parent window.....");
			driver.switchTo().defaultContent();
		}
		/**
		 * 
		 * This method will switch to child window based on index
		 * 
		 * 
		 * @param index
		 */
		public void switchToWindow(int index) {			
			Set<String> windows= driver.getWindowHandles();			
			int i=1;		
			for(String window :windows ) {				
				if(i==index) {	
					log.info("switched to window index  :"+i);
					driver.switchTo().window(window);
				}else 
				{				
					i++;
				}
				
			}

		}
		
		/**
		 * 
		 * This method is close all child windows and switch to parent window
		 * 
		 * 
		 */
		public void closeAlltabsAndSwitchToWindow() {
			
			String parent=driver.getWindowHandle();
			Set<String> windows= driver.getWindowHandles();			

			for(String window :windows ) {				
				if(!window.equalsIgnoreCase(parent)) {	
					driver.close();
				}
				log.info("switching to parent window.....");
				driver.switchTo().window(parent);
				
			}
			
		}
		
		/**
		 * 
		 * This method will do browser back to previous window/tab
		 * 
		 * 
		 */		
		public void navigateBack() {	
			log.info("navigating back....");
			driver.navigate().back();
		}

		/**
		 * 
		 * This method will do browser back to previous window/tab
		 * 
		 * 
		 */			
		
		public void navigateForward() {				
			log.info("navigating forward....");
			driver.navigate().forward();
		}
		

}
