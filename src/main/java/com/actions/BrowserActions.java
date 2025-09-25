package com.actions;

import org.openqa.selenium.WebDriver;

public class BrowserActions {
	
	protected WebDriver driver;

	public BrowserActions(WebDriver driver) {
		this.driver = driver;
	}
	
	public void navigateTo(String url) {
		driver.get(url);
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public void navigateBack() {
		driver.navigate().back();
	}
	
	public void navigateForward() {
		driver.navigate().forward();
	}
}
