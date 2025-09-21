package com.locatorPackage;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCartPage extends SearchPage {
	public  AddToCartPage() 
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@data-component-type=\"s-search-result\"]")
	private List<WebElement> productlist;
	public List<WebElement> getProductlist() {
		return productlist;
	}
	@FindBy(id = "buy-now-button")
	private WebElement scrollToAddToCart;
	public WebElement getScrollToAddToCart() {
		return scrollToAddToCart;
	}
	@FindBy(xpath = "(//input[@title=\"Add to Shopping Cart\"])[2]")
	private WebElement clikAddToCartButton;
	public WebElement getClikAddToCartButton() {
		return clikAddToCartButton;
	}
	@FindBy(id = "NATC_SMART_WAGON_CONF_MSG_SUCCESS")
	private WebElement cartConfirmMessage;
	public WebElement getCartConfirmMessage() {
		return cartConfirmMessage;
	}
	@FindBy(xpath = "//span[@data-a-selector=\"value\"]")
	private WebElement cartCount;
	public WebElement getCartCount() {
		return cartCount;
	}
	@FindBy(id = "nav-cart-count")
	private WebElement cartDefaultCount;
	public WebElement getCartDefaultCount() {
		return cartDefaultCount;
	}

	@FindBy(xpath = "//a[text()=\"New Releases\"]")
	private WebElement invalidCartAction;
	public WebElement getInvalidCartAction() {
		return invalidCartAction;
	}
	
	
	
	
	
}
