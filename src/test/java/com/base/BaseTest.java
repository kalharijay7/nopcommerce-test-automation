package com.base;

import java.io.File;
import java.time.Duration;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.driver.BrowserFactory;
import com.utils.ConfigReader;
import com.utils.LoggerHelper;

public class BaseTest {
	
	public static WebDriver driver;
	
	@BeforeClass
	public void setupLogsDirectory() {
	    createDir("logs");
	    createDir("screenshots");
	    
		//Initialize the browser driver instance	
		driver = BrowserFactory.getDriver(ConfigReader.getConfig("browser"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	private void createDir(String dirName) {
	    File dir = new File(dirName);
	    if (!dir.exists()) {
	        if (dir.mkdir()) {
	            System.out.println("Created " + dirName + " directory: " + dir.getAbsolutePath());
	        } else {
	            System.err.println("⚠️ Failed to create " + dirName + " directory.");
	        }
	    }
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
