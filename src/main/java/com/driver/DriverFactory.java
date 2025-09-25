package com.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	private static WebDriver driver;
	
	//Initiate the driver instance
	public static WebDriver getDriver(String browserName) {
		if(driver == null) {
			switch(browserName.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				
			case "internetexplorer":
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				
			case "safari":
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
				
			default:
				throw new IllegalArgumentException("Browser not supported:" + browserName);
			}
		}
		
		return driver;
	}
	
	//Quit the driver instance
	public static void quitDriver() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}

}
