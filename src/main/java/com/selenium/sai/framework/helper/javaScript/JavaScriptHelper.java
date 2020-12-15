package com.selenium.sai.framework.helper.javaScript;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.selenium.sai.framework.helper.logger.LoggerHelper;

public class JavaScriptHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(JavaScriptHelper.class);
	
	public JavaScriptHelper(WebDriver driver) {
		this.driver=driver;
		log.info("JavaScriptHelper has been initialised");
	}
	
	/**
	 * 
	 * 
	 * This method will exucute the javascript using  parameter (script)
	 * @param script
	 * @return
	 */
	public Object executeScript(String script) {
		JavascriptExecutor exe=	(JavascriptExecutor)driver;
		return exe.executeScript(script);
	}
	
	/**
	 * This will execute javascript based on multiple arguments
	 * @param script
	 * @param args
	 * @return
	 */
	public Object executeScript(String script,Object...args) {
		JavascriptExecutor exe=	(JavascriptExecutor)driver;
		return exe.executeScript(script,args);
	}
	
	public void scrollToElement(WebElement element) {
		
		 log.info("scroll to WebElement "+element.toString());
         executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x,element.getLocation().y);
	}

	public void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		//executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x,element.getLocation().y);
		element.click();
		log.info("Element is clicked   " +element.toString());
	}
	
	public void scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView()", element);
		log.info("scroll to element   " +element.toString());
	}
	
	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info("scroll to "+element.toString()+ "  and clicked  " );
	}
	
	public void scrollDownVertically() {
		log.info("scrolling Down Vertically...");
            executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void scrollUpVertically() {
		
		log.info("scrolling Up Vertically...");
        executeScript("window.scrollTo(0,-document.body.scrollHeight)");
}
	
	/**
	 * 
	 * This method will scroll down to specified pixel
	 * @param pixel
	 */
	
	public void scrollDownByPixel(int pixel) {
		
		log.info("scrolling down by pixel:  "+pixel);
        executeScript("window.scrollBY(0,"+pixel+")");
}
	/**
	 * 
	 * This method will scroll up to specified pixel
	 * @param pixel
	 */
	public void scrollUpByPixel(int pixel) {
		
		log.info("scrolling up by pixel:  "+pixel);
        executeScript("window.scrollBY(0,-"+pixel+")");
}
	
	/**
	 * 
	 * This method will zoom screen by 100%
	 * @param pixel
	 */	
	public void ZoomInBy100Percentage(int pixel) {
		
		log.info("scrolling up by pixel:  "+pixel);
        executeScript("document.body.style.zoom='100%'");
}
	
	
	public void ZoomInBy60Percentage(int pixel) {
		
		log.info("scrolling up by pixel:  "+pixel);
        executeScript("document.body.style.zoom='60%'");
}
	
	public void clickElement(WebElement element) {

		executeScript("arguments[0].click();",element);
}
}
