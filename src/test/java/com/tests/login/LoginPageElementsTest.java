package com.tests.login;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.base.BaseTest;

public class LoginPageElementsTest extends BaseTest {
	
	@BeforeMethod
	public void navigateToLoginPage() {
		
		driver.get("https://demo.nopcommerce.com/login");
	}
	
	@Test
	public void verifyPageIsLoadedSuccessfuly() {

		//Check if the page title is matched
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
		
		//Check if the login form is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='returning-wrapper fieldset']//form")).isDisplayed(), "Login form is not displayed");
	}
	
	@Test
	public void verifyRegisterBlockDisplayed() {

		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertTrue(driver.findElement(By.cssSelector("div.register-block")).isDisplayed(), "Register block is not displayed");
		softAssert.assertTrue(driver.findElement(By.cssSelector("div.register-block .text")).isDisplayed(), "New cutomer text block is not displayed");
		softAssert.assertTrue(driver.findElement(By.cssSelector("button.button-1.register-button")).isDisplayed(), "Register button is not displayed");
		
		softAssert.assertAll();
	}
	
	@Test
	public void verifyEmailFieldDisplayed() {

		Assert.assertTrue(driver.findElement(By.cssSelector("input.email")).isDisplayed(), "Email field is not displayed");
	}
	
	@Test
	public void verifyPasswordFieldDisplayed() {

		Assert.assertTrue(driver.findElement(By.id("Password")).isDisplayed(), "Password field is not displayed");
	}
	
	@Test
	public void verifyRemembermeFiledDisplayed() {

		Assert.assertTrue(driver.findElement(By.id("RememberMe")).isDisplayed(), "Remember me checkbox is not displayed");
	}
	
	@Test
	public void verifyForgotPasswordDisplayed() {

		Assert.assertTrue(driver.findElement(By.cssSelector("span.forgot-password")).isDisplayed(), "Forgot password checkbox is not displayed");
	}
	
	@Test
	public void verifyLoginButtonDisplayed() {

		Assert.assertTrue(driver.findElement(By.cssSelector("button.button-1.login-button")).isDisplayed(), "LOG IN button is not displayed");
	}
}
