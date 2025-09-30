package com.tests.login;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseTest;

public class LoginPageElementsTest extends BaseTest {
	
	@Test
	public void verifyPageIsLoadedSuccessfuly() {
		
		driver.get("https://demo.nopcommerce.com/login");
		
		//Check if the page title is matched
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
		
		//Check if the login form is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='returning-wrapper fieldset']//form")).isDisplayed(), "Login form is not displayed");
	}
	
	@Test
	public void verifyRegisterBlockDisplayed() {
		
		driver.get("https://demo.nopcommerce.com/login");
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertTrue(driver.findElement(By.cssSelector("div.register-block")).isDisplayed(), "Register block is not displayed");
		softAssert.assertTrue(driver.findElement(By.cssSelector("div.register-block .text")).isDisplayed(), "New cutomer text block is not displayed");
		softAssert.assertTrue(driver.findElement(By.cssSelector("button.button-1.register-button")).isDisplayed(), "Register button is not displayed");
		
		softAssert.assertAll();
	}
	
	@Test
	public void verifyEmailFieldDisplayed() {
		
		driver.get("https://demo.nopcommerce.com/login");
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input.email")).isDisplayed(), "Email field is not displayed");
	}
	
	@Test
	public void verifyPasswordFieldDisplayed() {
		
		driver.get("https://demo.nopcommerce.com/login");
		
		Assert.assertTrue(driver.findElement(By.id("Password")).isDisplayed(), "Password field is not displayed");
	}
	
	@Test
	public void verifyRemembermeFiledDisplayed() {
		
		driver.get("https://demo.nopcommerce.com/login");
		
		Assert.assertTrue(driver.findElement(By.id("RememberMe")).isDisplayed(), "Remember me checkbox is not displayed");
	}
	
	@Test
	public void verifyForgotPasswordDisplayed() {
		
		driver.get("https://demo.nopcommerce.com/login");
		
		Assert.assertTrue(driver.findElement(By.cssSelector("span.forgot-password")).isDisplayed(), "Forgot password checkbox is not displayed");
	}
	
	@Test
	public void verifyLoginButtonDisplayed() {
		
		driver.get("https://demo.nopcommerce.com/login");
		
		Assert.assertTrue(driver.findElement(By.cssSelector("button.button-1.login-button")).isDisplayed(), "LOG IN button is not displayed");
	}
}
