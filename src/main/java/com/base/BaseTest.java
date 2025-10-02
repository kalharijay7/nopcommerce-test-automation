package com.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.driver.BrowserFactory;
import com.utils.ConfigReader;

public class BaseTest {
	
	protected WebDriver driver;

	@BeforeTest
	public void setUp() {
		//Initialize the browser driver instance	
		driver = BrowserFactory.getDriver(ConfigReader.getConfig("browser"));
		
		driver.manage().window().maximize();
	}
	
	@AfterTest
	public void tearDown() {
		System.out.println("Driver content" + driver);
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
