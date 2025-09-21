package com.pageExecution;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.locatorPackage.AddToCartPage;
import com.locatorPackage.SearchPage;
import com.utillsPackage.TestListener;

public class AddToCartTest extends SearchTest {

	public static void search() {
		typeData(new SearchPage().getSearchbox(), "Iphone 16");

	}

	public static void clkIphone() {
		click(new SearchPage().getClickphone());
	}

	public static void clk1Product() {
		click(new AddToCartPage().getProductlist().get(0));
	}

	public static void ScrollAddToCart() {
		scrollDown(new AddToCartPage().getScrollToAddToCart());
	}

	public static void addProductToCart() {
		clik(new AddToCartPage().getClikAddToCartButton(), 200);
	}

	public static void verifyProductAddedInCartMessage(String expectConfirmMessage) {
		String actualMessage = new AddToCartPage().getCartConfirmMessage().getText();
		Assert.assertEquals(expectConfirmMessage, actualMessage, "Cart confirm  message mismatches");
		TestListener.test.get().pass("Confirmation Expected Message is : " + expectConfirmMessage + " And Actual Message is :"
				+ actualMessage);

	}

	public static void verifyProductCartCount() {
		String count = new AddToCartPage().getCartCount().getText();
		int actualCount = Integer.parseInt(count);
		Assert.assertTrue(actualCount > 0, "Cart count not updated after adding product.");
		TestListener.test.get().pass("Product successfully added to Cart&count is : " + actualCount);

	}
	public static void cartNotIncrease() {
		String cartDefaultCount = new AddToCartPage().getCartDefaultCount().getText();
		int cartCountBefore = Integer.parseInt(cartDefaultCount);
		TestListener.test.get().info("Initial Cart Count: " + cartCountBefore);
		
		clik(new AddToCartPage().getInvalidCartAction(), 40);
		
		int countAfterInvalidAction = Integer.parseInt(cartDefaultCount);
		TestListener.test.get().info("Count after invalid cart action: " +countAfterInvalidAction);
        
		SoftAssert sa = new SoftAssert();
		
		sa.assertNotEquals(cartCountBefore,countAfterInvalidAction,"Cart count increased after invalid action");
		
		TestListener.test.get().fail("Expected Cart Count :"+ cartCountBefore +" Actual Cart Count :"+countAfterInvalidAction);
		
		sa.assertAll();
		
		
	}

}
