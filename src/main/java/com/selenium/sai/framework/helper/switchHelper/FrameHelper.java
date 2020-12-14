package com.selenium.sai.framework.helper.switchHelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.selenium.sai.framework.helper.logger.LoggerHelper;

public class FrameHelper {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(FrameHelper.class);
	
	public FrameHelper(WebDriver driver) {
		this.driver=driver;
	}
	
	/**
	 * 
	 * This method will switchToFrame based on frame index
	 * @param frameIndex
	 */
	
	public void switchToFrame(int frameIndex) {
		driver.switchTo().frame(frameIndex);
		log.info("switched to  "+frameIndex+" frame");
	}
	
	/**
	 * 
	 * This method will switchToFrame based on frame frameName
	 * @param frameIndex
	 */
	
	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
		log.info("switched to frame "+frameName);
	}
	
	/**
	 * 
	 * This method will switchToFrame based on frame frameElement
	 * @param frameIndex
	 * 
	 * 
	 */
	
	public void switchToFrame(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
		log.info("switched to frame "+frameElement.toString());
	}

	public void switchFrameToDefaultContent() {
		log.info("switching to main window.....");
		driver.switchTo().defaultContent();
	}

}
