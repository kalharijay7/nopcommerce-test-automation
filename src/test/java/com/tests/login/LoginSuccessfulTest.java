package com.tests.login;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.base.BaseTest;

public class LoginSuccessfulTest extends BaseTest{
	
	private String email;
	private final String password = "User@123";

	@BeforeMethod
	public void setupNewUser() {
		
		driver.get("https://demo.nopcommerce.com/register");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		driver.findElement(By.id("FirstName")).sendKeys("John");
		driver.findElement(By.id("LastName")).sendKeys("Doe");
		
		email = "User" + System.currentTimeMillis()/1000 + "@email.com";
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		
		driver.findElement(By.id("register-button")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.result")));
		
		//Check if success message appears
		Assert.assertTrue(driver.findElement(By.cssSelector("div.result")).isDisplayed(), "Redirection to the register result page is failed");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		//Check if logout link appears
		Assert.assertTrue(driver.findElement(By.cssSelector("a.ico-logout")).isDisplayed(), "Log out link is not displayed, user might not get registered");
		driver.findElement(By.cssSelector("a.ico-logout")).click();
	}
	
	@Test
	public void verifySuccessfulLoginTest() {
		
		driver.get("https://demo.nopcommerce.com/login");
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		
		driver.findElement(By.cssSelector("button.button-1.login-button")).click();
		
		//Check if navigated to the home page after login
		Assert.assertTrue(driver.getCurrentUrl().contains("https://demo.nopcommerce.com/"));
		Assert.assertEquals(driver.findElement(By.cssSelector("div.topic-block-title h2")).getText(), "Welcome to our store");
	}
}
