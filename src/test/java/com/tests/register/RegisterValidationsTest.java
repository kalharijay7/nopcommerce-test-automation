package com.tests.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.base.BaseTest;

public class RegisterValidationsTest extends BaseTest {

	@Test
	public void verifyIncorrectEmailFormatShowsError() {
		
		driver.get("https://demo.nopcommerce.com/register");
		
		driver.findElement(By.id("Email")).sendKeys("tisok15419@gddcorp");
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.id("Email-error")).getText(), "Wrong email");
	}
	
	@Test
	public void verifyExistingEmailShowsError() {
		
		driver.get("https://demo.nopcommerce.com/register");
		
		//Existing email address
		String existingEmail = "tisok15419@gddcorp.com";
		
		driver.findElement(By.id("FirstName")).sendKeys("John");
		driver.findElement(By.id("LastName")).sendKeys("Doe");
		driver.findElement(By.id("Email")).sendKeys(existingEmail);
		driver.findElement(By.id("Password")).sendKeys("Hello123");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("Hello123");
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='message-error validation-summary-errors']//li")).getText(), 
				"The specified email already exists");
	}
	
	@Test
	public void verifyPasswordTooShortError() {
		
		driver.get("https://demo.nopcommerce.com/register");
		
		driver.findElement(By.id("Password")).sendKeys("He");
		//Move the focus away from the field
		driver.findElement(By.id("ConfirmPassword")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Password-error")));
		
		//check first for the element's visibility
		Assert.assertTrue(driver.findElement(By.id("Password-error")).isDisplayed(), "Password too short error is not displayed");
		Assert.assertEquals(driver.findElement(By.id("Password-error")).getText()
				, "Password must meet the following rules: must have at least 6 characters and not greater than 64 characters");
	}
}
