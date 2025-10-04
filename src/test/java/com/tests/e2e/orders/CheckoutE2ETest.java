package com.tests.e2e.orders;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.base.BaseTest;

public class CheckoutE2ETest extends BaseTest {

	private String email;
	private final String password = "User@123";
	private WebDriverWait wait;

	@BeforeMethod
	public void setupNewUser() {
		
		driver.get("https://demo.nopcommerce.com/register");
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
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
	
	private void fillCheckoutDetails(String city, String address1, String zipcode, String phoneNumber) {
		
		driver.findElement(By.id("BillingNewAddress_City")).sendKeys(city);
		driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys(address1);
		driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys(zipcode);
		driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys(phoneNumber);
		Select select = new Select(driver.findElement(By.id("BillingNewAddress_StateProvinceId")));
		select.selectByValue("1714");
	}
	
	@Test
	public void verifyCheckoutFlow() {
		
		//Login with the new user credentials
		driver.get("https://demo.nopcommerce.com/login");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		
		driver.findElement(By.cssSelector("button.button-1.login-button")).click();
		
		String productTitle = driver.findElement(By.cssSelector("div.product-item[data-productid='1'] h2.product-title")).getText();
		//Add the first product to cart
		driver.findElement(By.cssSelector("div.product-item[data-productid='1'] button.product-box-add-to-cart-button")).click();
		
		//Check navigation to the product page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-productid='1'] div.product-name")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div[data-productid='1'] div.product-name")).getText(), productTitle);
		
		//Select mandatory fields
		//Processor dropdown
		Select processor = new Select(driver.findElement(By.id("product_attribute_1")));
		processor.selectByVisibleText("2.2 GHz Intel Pentium Dual-Core E2200");
		
		//RAM dropdown
		Select ram = new Select(driver.findElement(By.id("product_attribute_2")));
		ram.selectByValue("4");
		
		//HDD
		driver.findElement(By.id("product_attribute_3_6")).click();
		
		//Add the product to cart
		driver.findElement(By.id("add-to-cart-button-1")).click();
		
		String successmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.bar-notification.success p"))).getText().trim();
		
		//Check for success message after adding the product
		Assert.assertEquals(successmsg, "The product has been added to your shopping cart", "Success messages mismatch!");
		//Close the success message notification
		driver.findElement(By.cssSelector("div.bar-notification.success span.close")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.bar-notification.success")));
		
		//Click on the cart
		driver.findElement(By.cssSelector("li#topcartlink a")).click();
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("div.page-title h1")), "Shopping cart"));
		
		//Check terms and services checkbox
		driver.findElement(By.id("termsofservice")).click();
		
		//Click checkout
		driver.findElement(By.id("checkout")).click();
		
		//Fill checkout details
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#opc-billing h2.title"))).getText(), "Billing address");
		fillCheckoutDetails("Kandy", "Katugastota Rd", "2000", "+94760000000");
		driver.findElement(By.cssSelector("#billing-buttons-container button[name='save']")).click();
		
		//Proceed from shipping method 
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#opc-shipping_method h2.title"))).getText(), "Shipping method");
		driver.findElement(By.cssSelector("#shipping-method-buttons-container button.shipping-method-next-step-button")).click();
		
		//Proceed from payment method
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#opc-payment_method h2.title"))).getText(), "Payment method");
		driver.findElement(By.cssSelector("#payment-method-buttons-container button.payment-method-next-step-button")).click();
		
		//Proceed from payment information
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#opc-payment_info h2.title"))).getText(), "Payment information");
		driver.findElement(By.cssSelector("#payment-info-buttons-container button.payment-info-next-step-button")).click();
		
		//Confirm the order
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#opc-confirm_order h2.title"))).getText(), "Confirm order");
		driver.findElement(By.cssSelector("#confirm-order-buttons-container button.confirm-order-next-step-button")).click();
		
		//Check for order success message and continue
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.order-completed")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div.order-completed strong")).getText().trim(), "Your order has been successfully processed!");
		driver.findElement(By.cssSelector("button.order-completed-continue-button")).click();
		
		//Check navigation to home page
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.home-page div.topic-block-title h2"))).getText(), "Welcome to our store");
	}
}
