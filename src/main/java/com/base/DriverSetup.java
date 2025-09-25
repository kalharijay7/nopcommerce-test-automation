package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;

public class DriverSetup {
	
	@BeforeTest
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\Learning\\Java-selenium-lambatest\\driver-actions-practice\\driver\\chromedriver.exe");
		
		//setting up chromeoptions to optimize speed of tests execution
		ChromeOptions options = new ChromeOptions();
		//Enable headless mode
		options.addArguments("--headless");
		//Disable GPU hardware acceleration
		options.addArguments("--disable-gpu");
		
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.google.com/");
		
		//maximize the window
		driver.manage().window().maximize();
		
		driver.quit();
	}

}
