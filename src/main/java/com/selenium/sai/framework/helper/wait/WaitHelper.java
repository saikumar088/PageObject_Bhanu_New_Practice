package com.selenium.sai.framework.helper.wait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.sai.framework.helper.logger.LoggerHelper;


public class WaitHelper {
	
private WebDriver driver;
private Logger log = LoggerHelper.getLogger(WaitHelper.class);


public WaitHelper(WebDriver driver) {
	log.info("WaitHelper driver is intialized");
	this.driver=driver;
}

/**
 * This is Implcit wait
 * @param timeout
 * @param unit
 */
public void setImplicitWait(long timeout,TimeUnit unit)
{
	log.info("Implicit wait has been set to:  "+timeout);
	driver.manage().timeouts().implicitlyWait(timeout, unit);
}

/**
 * This method will return WebDriverWait
 * @param timeoutInSeconds
 * @param pollingEveryMiliSec
 * @return
 */
private WebDriverWait getWait(int timeoutInSeconds, int pollingEveryMiliSec) {
	
	log.info("intialized fluent wait");
	log.info("Wait has been set to " + timeoutInSeconds+ "and polling set to " +pollingEveryMiliSec);
	WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
	wait.pollingEvery(Duration.ofMillis(pollingEveryMiliSec));
	wait.ignoring(NoSuchElementException.class);
	wait.ignoring(ElementNotVisibleException.class);
	wait.ignoring(StaleElementReferenceException.class);
	wait.ignoring(NoSuchFrameException.class);	
	return wait;
}



/**
 * 
 * This to wait for element to be visible with polling time
 * @param element
 * @param timeoutInSeconds
 * @param pollingEveryMiliSec
 */

public void waitForElementVisibleWithPollingTime(WebElement element,int timeoutInSeconds, int pollingEveryMiliSec) {
	
	log.info("waitng for  :"+element.toString()+ " for : "+timeoutInSeconds+"      seconds");
	WebDriverWait  wait= getWait(timeoutInSeconds, pollingEveryMiliSec);
	wait.until(ExpectedConditions.visibilityOf(element));
	log.info("Element is visable now");

}

/**
 * 
 * 
 * this to wait for Element to be clickable
 * @param element
 * @param timeoutInSeconds
 */
public void waitForElementClickable(WebElement element,int timeoutInSeconds) {
	
	log.info("waitng for  :"+element.toString()+ " for : "+timeoutInSeconds+"      seconds");
	WebDriverWait  wait= new WebDriverWait(driver,timeoutInSeconds);
	wait.until(ExpectedConditions.elementToBeClickable(element));
	log.info("Element is clickable now");
	
}
/**
 * 
 * This method will wait for element to be not visible
 * @param element
 * @param timeoutInSeconds
 * @return
 */
public boolean waitForElementNotPresent(WebElement element,int timeoutInSeconds) {
	
	log.info("waitng for  :"+element.toString()+ " for : "+timeoutInSeconds+"      seconds");
	WebDriverWait  wait= new WebDriverWait(driver,timeoutInSeconds);
	boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
	log.info("Element is invisible now");
	return status;
}

/**
 * 
 * 
 * This method is wait for FrameToBeVisible
 * @param element
 * @param timeoutInSeconds
 */
public void  waitForFrameToBeVisible(WebElement element,int timeoutInSeconds) {
	log.info("waitng for  :"+element.toString()+ " for : "+timeoutInSeconds+"      seconds");
	WebDriverWait  wait= new WebDriverWait(driver,timeoutInSeconds);
	 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	log.info("Frame is available and swithed");

}

/**
 * 
 * This method will give fluentWait object
 * @param timeoutInSeconds
 * @param pollingEveryMiliSec 500 milliseconds (default)
 * @return
 */

private Wait<WebDriver> getFluentWait(int timeoutInSeconds, int pollingEveryMiliSec) {
	log.info("FluentWait is intialised.....");
	Wait<WebDriver>  fwait= new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(timeoutInSeconds))
			.pollingEvery(Duration.ofMillis(pollingEveryMiliSec)).ignoring(NoSuchFrameException.class);	
	return fwait;	
}

/**
 * 
 * This method will wait for visibilityOf element using fluent wait
 * @param element
 * @param timeoutInSeconds
 * @param pollingEveryMiliSec
 * @return 
 */
public WebElement waitForElement(WebElement element,int timeoutInSeconds, int pollingEveryMiliSec)
{
	log.info("waitng for (fluent)  :"+element.toString()+ " for : "+timeoutInSeconds+"      seconds"+"  polling every:  "+pollingEveryMiliSec);
	Wait<WebDriver> fwait= getFluentWait(timeoutInSeconds,pollingEveryMiliSec);
	fwait.until(ExpectedConditions.visibilityOf(element));
	return element;
}

public void pageLoadTime(long time, TimeUnit unit) {
	log.info("waiting for page to load for : "+time+ "  seconds");
	driver.manage().timeouts().pageLoadTimeout(time, unit);
	log.info("Page is loaded ");
}





}
