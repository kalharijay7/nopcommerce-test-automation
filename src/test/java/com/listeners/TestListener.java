package com.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.base.BaseTest;
import com.driver.BrowserFactory;
import com.utils.ConfigReader;
import com.utils.LoggerHelper;

public class TestListener implements ITestListener {

	private static final Logger logger = LoggerHelper.getLogger(TestListener.class);
	
	@Override
	public void onTestStart(ITestResult result) {
		logger.info("🟢 Starting test: " + result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("✅ Test passed: " + result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
        logger.error("❌ Test failed: " + result.getMethod().getMethodName());
        logger.error("Reason: " + result.getThrowable().getMessage());

        WebDriver driver = BaseTest.driver;
        if (driver != null) {
            takeScreenshot(result.getMethod().getMethodName(), driver);
        }
	}
	
    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("⚠️ Test skipped: " + result.getMethod().getMethodName());
    }
	
    @Override
    public void onStart(ITestContext context) {
        logger.info("===== Test Suite Started: " + context.getName() + " =====");
    }
    
    @Override
    public void onFinish(ITestContext context) {
        logger.info("===== Test Suite Finished: " + context.getName() + " =====");
    }
    
    private void takeScreenshot(String methodName, WebDriver driver) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = "screenshots/" + methodName + "_" + timestamp + ".png";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);

        try {
            FileUtils.copyFile(srcFile, destFile);
            logger.info("📸 Screenshot saved: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Failed to save screenshot: " + e.getMessage());
        }
    }
}
