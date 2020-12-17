package com.selenium.sai.framework.helper.browserConfigurations.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.selenium.sai.framework.helper.browserConfigurations.BrowserType;
import com.selenium.sai.framework.helper.resource.ResourceHelper;

public class PropertyReader implements ConfigReader {
	private static FileInputStream file;
	private static Properties prop;

	public PropertyReader() {

		String filePath = ResourceHelper.getResourcePath("/src/main/resources/configfile/config.properties");
		try {
			file = new FileInputStream(new File(filePath));
			prop = new Properties();
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getImplicitWait() {

		return Integer.parseInt(prop.getProperty("implicitwait"));
	}

	public int getExplicitWait() {

		return Integer.parseInt(prop.getProperty("explicitwait"));
	}

	public int getPageLoadTime() {

		return Integer.parseInt(prop.getProperty("pageloadtime"));
	}

	public BrowserType getBrowserType() {

		return BrowserType.valueOf(prop.getProperty("browserType"));
	}

}
