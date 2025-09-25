package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverManagerSetup {

	public static void main(String [] args) {
		//setting up chromeoptions to optimize speed of tests execution
		ChromeOptions options = new ChromeOptions();
		//Enable headless mode
		options.addArguments("--headless");
		//Disable GPU hardware acceleration
		options.addArguments("--disable-gpu");
		
		//initializing webdrivermanager for chrome
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.google.com/");
		
		driver.manage().window().maximize();
		
		driver.quit();
	}
}
