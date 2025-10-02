package com.tests.login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.base.BaseTest;

public class LoginPageValidationsTest extends BaseTest {

	@Test
	public void verifyEmptyFormSubmissionShowsErrors() {
		
		driver.get("https://demo.nopcommerce.com/login");
		
		driver.findElement(By.cssSelector("button.button-1.login-button")).click();
		
		Assert.assertTrue(driver.findElement(By.id("Email-error")).isDisplayed(), "Email error is not displayed!");
		Assert.assertEquals(driver.findElement(By.id("Email-error")).getText(), "Please enter your email", "Email error messages not matched");
	}
	
	@Test
	public void verifyEmptyPasswordShowsError() {
		
		driver.get("https://demo.nopcommerce.com/login");
		
		//Fill the Email field and leave password field empty
		driver.findElement(By.cssSelector("input.email")).sendKeys("johndoe@email.com");
		driver.findElement(By.cssSelector("button.button-1.login-button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.message-error.validation-summary-errors")).isDisplayed()
				, "No validation error is displayed for empty password after form submission!");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error.validation-summary-errors")).getText()
				, "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found", "Login error messages mismatched");
	}
	
	@Test
	public void verifyEmptyEmailShowsError() {
		
		driver.get("https://demo.nopcommerce.com/login");
		
		//Leave the Email field empty and fill the password field
		driver.findElement(By.id("Password")).sendKeys("hello123");
		driver.findElement(By.cssSelector("button.button-1.login-button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.message-error.validation-summary-errors")).isDisplayed()
				, "No validation error is displayed for empty password after form submission!");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error.validation-summary-errors")).getText()
				, "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found", "Login error messages mismatched");
	}
	
	@Test
	public void verifyPasswordToggleChangesPasswordType() {
		
		driver.get("https://demo.nopcommerce.com/login");
		
		WebElement password = driver.findElement(By.id("Password"));
		WebElement passwordEye = driver.findElement(By.cssSelector("span.password-eye"));
		
		password.sendKeys("hello123");
		passwordEye.click();
		
		//Check if the input type changes to text
		Assert.assertEquals(password.getAttribute("type"), "text", "Password type not changed to text");
		
		passwordEye.click();
		//Check if the input type changes to password
		Assert.assertEquals(password.getAttribute("type"), "password", "Password type not changed to password");	
	}
	
	@Test
	public void verifyPasswordRecoveryPageNavigation() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		driver.get("https://demo.nopcommerce.com/login");
		
		driver.findElement(By.cssSelector("span.forgot-password > a")).click();
		
		//Wait until the page title gets displayed
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.page-title > h1")));
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title > h1")).isDisplayed(), "Page title of the password recovery page is not displayed");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title > h1")).getText(), "Password recovery");
	}
}
