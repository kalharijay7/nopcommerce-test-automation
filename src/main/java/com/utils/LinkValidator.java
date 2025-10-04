package com.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LinkValidator {
	
	public static void checkIfLinksArePresent(List<WebElement> links) {
		
		Assert.assertTrue(links.size() > 0, "No links found!");
	}
	
	public static void checkIfLinksAreWorking(List<WebElement> links, WebDriver driver) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//Initiate a HttpClient instance
		HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL) // follow redirects if needed
                .connectTimeout(Duration.ofSeconds(10))
                .build();;
		
		for(WebElement link : links) {
			
			//Skip if the URL is empty
			String linkUrl = link.getAttribute("href");
			System.out.println("The link is " + linkUrl);
			
			if(linkUrl == null || linkUrl.isEmpty()) {
				System.out.println("The link is empty " + linkUrl);
				continue;
			}
			
			//Check if the URL is clickable
			try {
				wait.until(ExpectedConditions.elementToBeClickable(link));
				Assert.assertTrue(link.isEnabled(), "Link is disabled " + link);
			}
			catch(TimeoutException e) {
				Assert.fail("Link is not clickable " + link);
			}
			
			//Check if the URL is working
			try {
				HttpRequest request = HttpRequest.newBuilder()
						.uri(URI.create(linkUrl))
						.GET()
						.timeout(Duration.ofSeconds(10)).build();
				
				HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
				
				int statusCode = response.statusCode();
				Assert.assertEquals(statusCode, 200, "Broken link: " + linkUrl);
			}
			catch(Exception e) {
				Assert.fail("Exception thrown while checking URL " + linkUrl + "->" + e.getMessage());
			}
		}
	}
}
