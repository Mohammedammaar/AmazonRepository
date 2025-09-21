package com.pageExecution;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.locatorPackage.AddToCartPage;
import com.locatorPackage.LoginPage;
import com.locatorPackage.SearchPage;
import com.utillsPackage.TestListener;

public class LoginTest extends AddToCartPage {

	public static void handleContinueShopping() {
		try {
			TestListener.test.get().info("Checking if Continue Shopping button is displayed");
			if (new LoginPage().getContinueShopping().isDisplayed()) {
				click(new LoginPage().getContinueShopping());
				TestListener.test.get().pass("Clicked Continue Shopping button successfully");
			} else {
				url(readProp("url"));
				TestListener.test.get().info("Continue Shopping button not displayed, redirected to Amazon home page");
			}
		} catch (Exception e) {
			url(readProp("url"));
			TestListener.test.get().pass("Continue Shopping button not found, redirected to home due to exception: " + e.getMessage());
		}
	}

	public static void login() {
		click(new LoginPage().getFirstSignIn());
	}

	public static void username() {
		typeData(new LoginPage().getEmail(), readProp("username"));
	}

	public static void userSubmit() {
		click(new LoginPage().getEmailContinue());
	}

	public static void password() {
		typeData(new LoginPage().getPassword(), readProp("password"));
	}

	public static void signIn() {
		click(new LoginPage().getSigninButton());
	}

	public static void LoginValidation() {
		boolean amazonImage = new LoginPage().getAmazonImage().isDisplayed();
		Assert.assertTrue(amazonImage, "Not logged in to the authentication page");
		TestListener.test.get().pass("Successfully entered into the authentication page");

	}

	public static void user() {
		typeData(new LoginPage().getEmail(), readProp("username1"));
	}

	public static void emptyPassword() {
		typeData(new LoginPage().getPassword(), readProp("password1"));
	}

	public static void invalidValidation(String expectedError) {
		SoftAssert sa = new SoftAssert();
		click(new LoginPage().getSigninButton());

		String actualError = new LoginPage().getErrorMessage().getText();

		TestListener.test.get().info("actual error is :" + actualError);

		sa.assertEquals(expectedError, actualError, "Error not matching");

		try {
			sa.assertAll();
			TestListener.test.get().pass("Assertions passed as actual message is " + actualError);
		} catch (AssertionError ae) {
			TestListener.test.get().fail("Assertion failed: " + ae.getMessage());
			throw ae;
		}
	}

}
