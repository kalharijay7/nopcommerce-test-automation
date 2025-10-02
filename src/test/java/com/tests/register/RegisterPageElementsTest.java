package com.tests.register;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.base.BaseTest;

public class RegisterPageElementsTest extends BaseTest{
	
	@BeforeMethod
	public void navigateToRegisterPage() {
		
		driver.get("https://demo.nopcommerce.com/register");
	}
	
	@Test
	public void verifyPageIsLoadedSuccessfully() {
		
		//Check if the page title is matched
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Register");
		
		//Check if the register form is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class ='page registration-page']//form")).isDisplayed(), 
				"No Register form found!");
	}
	
	@Test
	public void verifyGenderFieldDisplayed() {
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertTrue(driver.findElement(By.id("gender-male")).isDisplayed(), "Male gender option not displayed!");
		softAssert.assertTrue(driver.findElement(By.id("gender-female")).isDisplayed(), "Female gender option not displayed!");
		
		softAssert.assertAll();
	}
	
	@Test
	public void verifyNameFieldsDisplayed() {
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertTrue(driver.findElement(By.id("FirstName")).isDisplayed(), "First name field not displayed!");
		softAssert.assertTrue(driver.findElement(By.id("LastName")).isDisplayed(), "Last name field not displayed!");
		
		softAssert.assertAll();
	}
	
	@Test
	public void verifyEmailFieldDisplayed() {
		
		Assert.assertTrue(driver.findElement(By.id("Email")).isDisplayed(), "Email field not displayed!");
	}
	
	@Test
	public void verifyCompanyNameFieldDisplayed() {
		
		Assert.assertTrue(driver.findElement(By.id("Company")).isDisplayed(), "Company name field not displayed!");
	}
	
	@Test
	public void verifyNewletterFieldDisplayed() {
		
		Assert.assertTrue(driver.findElement(By.id("Newsletter")).isDisplayed(), "Newsletter field not displayed");
	}
	
	@Test
	public void verifyPasswordFieldsDisplayed() {
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertTrue(driver.findElement(By.id("Password")).isDisplayed(), "Password field not displayed");
		softAssert.assertTrue(driver.findElement(By.id("ConfirmPassword")).isDisplayed(), "Confirm password field not displayed");
	}
	
	@Test
	public void verifyRegisterButtonDisplayed() {
		
		Assert.assertTrue(driver.findElement(By.id("register-button")).isDisplayed(), "Register button not displayed");
	}
}
