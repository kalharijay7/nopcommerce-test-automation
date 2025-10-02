package com.tests.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.base.BaseTest;

public class RegisterMandatoryFieldsTests extends BaseTest {
	
	@BeforeMethod
	public void navigateToRegisterPage() {
		
		driver.get("https://demo.nopcommerce.com/register");
	}

	@Test
	public void verifyEmptyFormSubmissionShowsErrors() {

		driver.findElement(By.id("register-button")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		SoftAssert softAssert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.field-validation-error span#FirstName-error")));
		
		softAssert.assertEquals(driver.findElement(By.cssSelector("span.field-validation-error span#FirstName-error")).getText(), "First name is required.");
		softAssert.assertEquals(driver.findElement(By.id("LastName-error")).getText(), "Last name is required.");
		softAssert.assertEquals(driver.findElement(By.id("Email-error")).getText(), "Email is required.");
		softAssert.assertEquals(driver.findElement(By.id("ConfirmPassword-error")).getText(), "Password is required.");
		
		softAssert.assertAll();
	}
	
	@Test
	public void verfyPartiallyFilledFormShowsErrors() {

		//Fill only the first name field
		driver.findElement(By.id("FirstName")).sendKeys("Joe");
		driver.findElement(By.id("register-button")).click();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(driver.findElement(By.id("LastName-error")).getText(), "Last name is required.");
		softAssert.assertEquals(driver.findElement(By.id("Email-error")).getText(), "Email is required.");
		softAssert.assertEquals(driver.findElement(By.id("ConfirmPassword-error")).getText(), "Password is required.");
		
		softAssert.assertAll();
	}
	
	@Test
	public void verifyPasswordMismatchShowsError() {

		driver.findElement(By.id("FirstName")).sendKeys("John");
		driver.findElement(By.id("LastName")).sendKeys("Doe");
		driver.findElement(By.id("Email")).sendKeys("tisok15419@gddcorp.com");
		driver.findElement(By.id("Password")).sendKeys("Hello123");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("Hello12");
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.id("ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
	}
}
