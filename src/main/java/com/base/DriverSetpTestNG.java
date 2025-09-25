package com.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetpTestNG {
	WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		//setting up chromeoptions to optimize speed of tests execution
		ChromeOptions options = new ChromeOptions();
		//Enable headless mode
		//options.addArguments("--headless");
		//Disable GPU hardware acceleration
		options.addArguments("--disable-gpu");
		
		//initializing webdrivermanager for chrome
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver(options);
	}
	
	@Test
	public void firstTest() {
		driver.get("https://www.google.com/");
		driver.findElement(By.id("APjFqb")).sendKeys("Hello");
	}

	@AfterTest
	public void tearDown() {
		//driver.quit();
	}
}
