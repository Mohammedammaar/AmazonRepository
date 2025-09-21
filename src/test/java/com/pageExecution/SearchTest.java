package com.pageExecution;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.base.BasePage;
import com.locatorPackage.SearchPage;
import com.utillsPackage.TestListener;

public class SearchTest extends LoginTest {

	public static void search() {
		typeData(new SearchPage().getSearchbox(), "Iphone 16");

	}

	public static void clkIphone() {
		click(new SearchPage().getClickphone());
	}
	public static void scrollProductList() {
		scrollDown(new SearchPage().getScrollProductList());
	}

	public static void positiveProductValidation(String expectedProduct) {
		List<WebElement> products = new SearchPage().getProductlist();
		boolean matchFound = false;
		for (WebElement productlist : products) {
			String productText = productlist.getText();

			TestListener.test.get().info("Product List :" + productText);

			if (productText.toLowerCase().contains("iphone 16")) {
				matchFound = true;
				break;
			}
		}
		Assert.assertTrue(matchFound, "Product is not found in search list: " + expectedProduct);
		TestListener.test.get().pass("Product is found in search list: " +expectedProduct);
	}
	
	 public static void negativeProductValidation(String unexpectedProduct) {
	        List<WebElement> products = new SearchPage().getProductlist();

	        SoftAssert sa = new SoftAssert();
	        boolean unwantedFound = false;

	        for (WebElement productlist : products) {
	            String productText = productlist.getText();

	            if (productText.toLowerCase().contains(unexpectedProduct.toLowerCase())) {
	                unwantedFound = true;
	                TestListener.test.get().fail("Unexpected product found: " + productText);
	            }
	        }

	        sa.assertTrue(unwantedFound, "Unexpected product is present in the search list: " + unexpectedProduct);
	        TestListener.test.get().fail("product is not found in the search list :" + unexpectedProduct);

	        sa.assertAll();
	    }
}
