package com.tests.home;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.base.BaseTest;
import com.utils.LinkValidator;

public class HomePageTest extends BaseTest{
	
	@Test
	public void verifyLogoIsDisplayed() {
		
		driver.get("https://demo.nopcommerce.com/");
		
		WebElement logo = driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']"));
		
		System.out.println("Webelement of the logo is" + logo);
		
		Assert.assertTrue(logo.isDisplayed(), "Logo is displayed");
	}
	
	@Test 
	public void verifyPageTitle() {
		
		driver.get("https://demo.nopcommerce.com/");
		
		String pageTitle = driver.getTitle();
		
		System.out.println("The page title is" + pageTitle);
		
		Assert.assertEquals(pageTitle, "nopCommerce demo store. Home page title");
	}
	
	@Test
	public void verifySearchBoxIsVisible() {
		
		driver.get("https://demo.nopcommerce.com/");
		WebElement searchBox = driver.findElement(By.id("small-searchterms"));
		
		System.out.println("Webelement of the searchbox is" + searchBox);
		Assert.assertTrue(searchBox.isDisplayed());
	}
	
	@Test
	public void verifyLogoRedirectsToHomePage() {
		
		driver.get("https://demo.nopcommerce.com/");
		
		WebElement loginLink = driver.findElement(By.xpath("//a[@class='ico-login']"));
		loginLink.click();
		
		WebElement logo = driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']"));
		logo.click();	
		
		Assert.assertTrue(driver.getCurrentUrl().contentEquals("https://demo.nopcommerce.com/"));
	}
	
	@Test
	public void verifyProdCatogryLinksAreWorking() {
		
		driver.get("https://demo.nopcommerce.com/");
		
		List<WebElement> prodCatLinks = 
				driver.findElements(By.xpath("//div[@class = 'category-grid home-page-category-grid']//div[@class= 'item-box']//a"));
		
		LinkValidator.checkIfLinksArePresent(prodCatLinks);
		
		LinkValidator.checkIfLinksAreWorking(prodCatLinks, driver);
	}
	
	@Test
	public void verifyFeaturedProductsAreVisible() {
		
		driver.get("https://demo.nopcommerce.com/");
		
		//Fetch all featured product items
		List<WebElement> featuredProdCount = 
				driver.findElements(By.xpath("//div[@class='product-grid home-page-product-grid']//div[@class='product-item']"));
		
		Assert.assertTrue(featuredProdCount.size() > 0, "No featured products found" );
	}
	
	@Test
	public void verifyRegisterLinkRedirectsToRegisterPage() {
		
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.className("ico-register")).click();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("/register"), "Redirecting to the Register page is failed");
	}
	
	@Test
	public void verifyLoinLinkRedirectsToLoginPage() {
		
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.className("ico-login"));
		
		Assert.assertTrue(driver.getCurrentUrl().contains("/login"), "Redirecting to the Login page is failed");
	}
	
	@Test
	public void verifyWishlistPageRedirection() {
		
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.className("ico-wishlist"));
		
		Assert.assertTrue(driver.getCurrentUrl().contains("/wishlist"), "Redirecting to the Wshlist page is failed");
	}
	
	@Test
	public void verifyShoppingcartPageRedirection() {
		
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.className("ico-cart")).click();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("/cart"), "Redirecting to the Shoppingcart page is failed");
	}
	
	@Test
	public void verifyFooterLinksAreWorking() {
		
		driver.get("https://demo.nopcommerce.com/");
		
		List<WebElement> footerLinks = driver.findElements(By.xpath("//div[@class='footer']//a"));
		
		LinkValidator.checkIfLinksArePresent(footerLinks);
		
		LinkValidator.checkIfLinksAreWorking(footerLinks, driver);
	}
}
