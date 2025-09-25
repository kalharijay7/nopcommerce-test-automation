package com.driver;

import org.openqa.selenium.WebDriver;

public class BrowserFactory {

	public static WebDriver getDriver(String browserName) {
		
		IDriverFactory factory;
		
		switch(browserName.toLowerCase()) {
		case "chrome":
			factory = new ChromeDriverFactory();
			break;
		case "firefox":
			factory = new FirefoxDriverFactory();
			break;
		case "safari":
			factory = new SafariDriverFactory();
			break;
		case "internetexplorer":
			factory = new IeDriverFactory();
			break;
		case "edge":
			factory = new EdgeDriverFactory();
			break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}
		
		return factory.createDriver();
	}
}
