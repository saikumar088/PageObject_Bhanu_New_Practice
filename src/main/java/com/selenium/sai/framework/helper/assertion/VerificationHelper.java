package com.selenium.sai.framework.helper.assertion;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.selenium.sai.framework.helper.logger.LoggerHelper;

public class VerificationHelper {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(VerificationHelper.class);

	public VerificationHelper(WebDriver driver) {
		this.driver = driver;
		log.info("Webdriver is intialised for VerificationHelper");
	}

	/**
	 * 
	 * 
	 * This method will check wether the element is displayed or not
	 * 
	 * @param element
	 * @return
	 */
	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is displayed.." + element.getText());
			return true;

		} catch (Exception e) {
			log.error("Element is not present..", e.getCause());
			return false;
		}
	}

	/**
	 * This method will return true if element is not displayed otherwise it will
	 * return false
	 * @param element
	 * @return
	 */
	public boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element text is.." + element.getText());
			return false;

		} catch (Exception e) {
			log.error("Element is not present..");
			return true;
		}

	}

	/**
	 * 
	 * 
	 * This is method is to return the webelement text/value based on the
	 * availability
	 * 
	 * @param element
	 * @return
	 */

	public String getText(WebElement element) {

		if (null == element) {

			log.info("WebElement is null...");
			return null;
		}
		boolean status = isDisplayed(element);
		if (status) {
			log.info("element text is .." + element.getText());
			return element.getText();
		} else {
			return null;
		}

	}

}
