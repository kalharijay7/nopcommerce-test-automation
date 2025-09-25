package com.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IeDriverFactory implements IDriverFactory{

	@Override
	public WebDriver createDriver() {

		WebDriverManager.iedriver().setup();
		WebDriver driver = new InternetExplorerDriver();
		
		driver.manage().window().maximize();
		return driver;
	}

}
