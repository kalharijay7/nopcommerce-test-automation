package com.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.driver.BrowserFactory;
import com.utils.ConfigReader;

public class BaseTest {
	
	protected WebDriver driver;

	@BeforeClass
	public void setUp() {
		//Initialize the browser driver instance	
		driver = BrowserFactory.getDriver(ConfigReader.getConfig("browser"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown() {
		System.out.println("Driver content" + driver);
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
