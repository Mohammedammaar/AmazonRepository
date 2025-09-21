package com.testExecution;

import org.junit.Ignore;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.pageExecution.AddToCartTest;
import com.pageExecution.LoginTest;
import com.pageExecution.SearchTest;
import com.utillsPackage.RetryFailTest;
import com.utillsPackage.TestListener;



public class TestExecution extends AddToCartTest {
	@BeforeMethod
	public static void setup() {
		launchBrowsers("chrome");
		windowsMax();
		url(readProp("url"));
	}

	@Test(description = "Loging with the valid credentials", priority=0)
	public static void loginApp() {
		handleContinueShopping();
		implicityWait(50);
		login();
		username();
		userSubmit();
		password();
		signIn();
		LoginValidation();
		implicityWait(80);
	}

	@Test(description = "Loging with the invalid credentials",priority=1)
	@Parameters({ "expectedError" })
	public static void invalidLogin(String expectedError) {
		handleContinueShopping();
		implicityWait(50);
		login();
		user();
		userSubmit();
		emptyPassword();
		implicityWait(300);
		invalidValidation(expectedError);
	}

	@Test(description = "Validate the search product is present in the list", priority=2,retryAnalyzer = RetryFailTest.class)
	@Parameters({ "expectedProduct" })
	public static void searchProductList(String expectedProduct) {
		handleContinueShopping();
		implicityWait(600);
		search();
		clkIphone();
		implicityWait(700);
		scrollProductList();
		positiveProductValidation(expectedProduct);
	}

	@Test(description = "Validate the search product is not present in the list", priority=3)
	@Parameters({ "unexpectedProduct" })
	public static void searchUnwantedProductList(String unexpectedProduct) {
		handleContinueShopping();
		implicityWait(600);
		search();
		clkIphone();
		implicityWait(700);
		scrollProductList();
		negativeProductValidation(unexpectedProduct);
	}

	@Test(description = "Validate the product added to the add to cart", priority=4,retryAnalyzer = RetryFailTest.class)
	@Parameters({ "expectConfirmMessage" })
	public static void addToCartValidate(String expectConfirmMessage) {
		handleContinueShopping();
		implicityWait(600);
		search();
		clkIphone();
		implicityWait(700);
		clk1Product();
		switchToNewWindow();
		implicityWait(300);
		ScrollAddToCart();
		addProductToCart();
		implicityWait(300);
		verifyProductAddedInCartMessage(expectConfirmMessage);
		verifyProductCartCount();
	}

	@Test(description = "Verify the cart count not increase after invalid add-to-cart action",priority=5)
	public static void validateCartNotIncreases() {
		handleContinueShopping();
		implicityWait(600);
		search();
		clkIphone();
		implicityWait(700);
		cartNotIncrease();
		implicityWait(700);
	}

	@AfterMethod
	public static void teardown() {
		TestListener.test.get().info("---Pausing for 3 seconds after all test script method execute-----");
        try {
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		driver.quit();
	}

}
