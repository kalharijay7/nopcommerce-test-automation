package com.tests.register;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.base.BaseTest;

public class RegisterSuccessfulTest extends BaseTest {

	@Test
	public void verifySuccessfulRegistrationFlow() {
		
		driver.get("https://demo.nopcommerce.com/register");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		String uniqueEmail = "user"+ System.currentTimeMillis() + "@mail.com";
		
		driver.findElement(By.id("FirstName")).sendKeys("John");
		driver.findElement(By.id("LastName")).sendKeys("Doe");
		driver.findElement(By.id("Email")).sendKeys(uniqueEmail);
		driver.findElement(By.id("Password")).sendKeys("user123");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("user123");
		driver.findElement(By.id("register-button")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.result")));
		
		//Check if success message appears
		Assert.assertTrue(driver.findElement(By.cssSelector("div.result")).isDisplayed(), "Redirection to the register result page is failed");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		//Check if my account link appears
		Assert.assertTrue(driver.findElement(By.cssSelector("a.ico-account")).isDisplayed(), "My account link is not displayed, user might not get registered");
		
		//Check if logout link appears
		Assert.assertTrue(driver.findElement(By.cssSelector("a.ico-logout")).isDisplayed(), "Log out link is not displayed, user might not get registered");
		
		//Check if continue button appears
		Assert.assertTrue(driver.findElement(By.cssSelector("a.register-continue-button")).isDisplayed()
				, "Continue button is not displayed, user might not get registered");
		driver.findElement(By.cssSelector("a.register-continue-button")).click();
		
		//Check navigation to the home page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.topic-block-title")));
		
		List<WebElement> featuredItems = driver.findElements(By.xpath("//div[@class='product-item']"));
		Assert.assertTrue(featuredItems.size() > 0, "Products are not displayed on the homepage once redirected from the register result page");	
	}
}
