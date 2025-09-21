package com.additionalTasks;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.base.BasePage;

public class VerifyBrokenLink extends BasePage{
	public static ExtentReports extent;
    public static ExtentTest test;
	
	@BeforeClass
	 public void ReportSetUp() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/LinkValidationReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }
	
	@BeforeMethod
	public static void setup() {
		launchBrowsers("chrome");
		windowsMax();
		url(readProp("url2"));
	}
	
	@Test(description ="Validate all the links present in the webpage")
	public static void verifyBrokenLinks() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total links found: " + links.size());

        for (WebElement link : links) {
            String url = link.getAttribute("href");
            String testName = (url == null || url.isEmpty()) ? "Empty URL" : url;
            test = extent.createTest("URL Check: " + testName);
            if (url == null || url.isEmpty()) {
            	test.skip("Skipping empty URL");
                continue;
            }
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("HEAD");
                conn.connect();

                int status = conn.getResponseCode();
                if (status < 400) {
                 test.log(Status.PASS,"Valid URL: " + url + " | Status: " + status);
                } else {
                    test.log(Status.FAIL,"Broken URL: " + url + " | Status: " + status);
                }
                  
                Assert.assertTrue(status < 400, "Broken link detected: " + url);
               
            }
             catch (Exception e) {
            	 String errorMessage = "Error: " + url + " | " + e.getMessage();
                 test.warning(errorMessage);
                 Assert.fail("Exception occurred for URL: " + url);
            }
        }
      }
	
	@AfterMethod
	public static void teardown() {
		driver.quit();
	    extent.flush();
	}
}
