package com.utillsPackage;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.base.BasePage;

public class TestListener implements ITestListener {
	public static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onStart(ITestContext context) {
		extent = new ExtentReports();
		ExtentSparkReporter htmlreport = new ExtentSparkReporter("target/AmozonSuiteTestReport.html");
		extent.attachReporter(htmlreport);
		extent.setSystemInfo("Environment", "Production");
		extent.setSystemInfo("Tester", "Ammaar");
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test.get().log(Status.PASS, "Test Case Passed : " + result.getMethod().getMethodName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().log(Status.FAIL, "Test Case Failed : " + result.getMethod().getMethodName());
		test.get().log(Status.FAIL, result.getThrowable());
		
		 String screenshotPath = BasePage.captureScreenshot(result.getMethod().getMethodName());
	     test.get().fail("Screenshot of failed Test Cases :",MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		Throwable skipReason = result.getThrowable();
        if (skipReason != null) {
            test.get().skip("Test Case Skipped : " + result.getMethod().getMethodName()
                    + " | Reason: " + skipReason.getMessage());
        } else {
            test.get().skip("Test Skipped: " + result.getMethod().getMethodName());
        }
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
