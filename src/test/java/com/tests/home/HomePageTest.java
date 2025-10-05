package com.tests.home;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.base.BaseTest;
import com.utils.LinkValidator;

public class HomePageTest extends BaseTest{
	
	@BeforeMethod
	public void navigateToHomePage() {
		
		driver.get("https://demo.nopcommerce.com/");
	}
	
	@Test
	public void verifyLogoIsDisplayed() {

		WebElement logo = driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']"));
		
		System.out.println("Webelement of the logo is" + logo);
		
		Assert.assertTrue(logo.isDisplayed(), "Logo is displayed");
	}
	
	@Test 
	public void verifyPageTitle() {

		String pageTitle = driver.getTitle();
		
		System.out.println("The page title is" + pageTitle);
		
		Assert.assertEquals(pageTitle, "nopCommerce demo store. Home page title");
	}
	
	@Test
	public void verifySearchBoxIsVisible() {

		WebElement searchBox = driver.findElement(By.id("small-searchterms"));
		
		Assert.assertTrue(searchBox.isDisplayed());
	}
	
	@Test
	public void verifyLogoRedirectsToHomePage() {

		WebElement loginLink = driver.findElement(By.xpath("//a[@class='ico-login']"));
		loginLink.click();
		
		WebElement logo = driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']"));
		logo.click();	
		
		Assert.assertTrue(driver.getCurrentUrl().contentEquals("https://demo.nopcommerce.com/"));
	}
	
	@Test
	public void verifyProdCatogryLinksAreWorking() {

		List<WebElement> prodCatLinks = 
				driver.findElements(By.xpath("//div[@class = 'category-grid home-page-category-grid']//div[@class= 'item-box']//a"));
		
		LinkValidator.checkIfLinksArePresent(prodCatLinks);
		
		LinkValidator.checkIfLinksAreWorking(prodCatLinks, driver);
	}
	
	@Test
	public void verifyFeaturedProductsAreVisible() {

		//Fetch all featured product items
		List<WebElement> featuredProdCount = 
				driver.findElements(By.xpath("//div[@class='product-grid home-page-product-grid']//div[@class='product-item']"));
		
		Assert.assertTrue(featuredProdCount.size() > 0, "No featured products found" );
	}
	
	@Test
	public void verifyRegisterLinkRedirectsToRegisterPage() {

		driver.findElement(By.className("ico-register")).click();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("/register"), "Redirecting to the Register page is failed");
	}
	
	@Test
	public void verifyLoginLinkRedirectsToLoginPage() {

		driver.findElement(By.className("ico-login")).click();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("/login"), "Redirecting to the Login page is failed");
	}
	
	@Test
	public void verifyWishlistPageRedirection() {

		driver.findElement(By.className("ico-wishlist")).click();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("/wishlist"), "Redirecting to the Wshlist page is failed");
	}
	
	@Test
	public void verifyShoppingcartPageRedirection() {

		driver.findElement(By.className("ico-cart")).click();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("/cart"), "Redirecting to the Shoppingcart page is failed");
	}
	
	@Test
	public void verifyFooterLinksAreWorking() {

		List<WebElement> footerLinks = driver.findElements(By.xpath("//div[@class='footer']//a"));
		
		LinkValidator.checkIfLinksArePresent(footerLinks);
		
		LinkValidator.checkIfLinksAreWorking(footerLinks, driver);
	}
}
