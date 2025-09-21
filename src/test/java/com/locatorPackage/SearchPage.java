package com.locatorPackage;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BasePage;

public class SearchPage extends LoginPage {

	public  SearchPage() 
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(name ="field-keywords")
	private WebElement searchbox;

	public WebElement getSearchbox() {
		return searchbox;
	}
	@FindBy(id ="nav-search-submit-button")
	private WebElement clickphone;
	public WebElement getClickphone() {
		return clickphone;
	}
	@FindBy(xpath = "//div[@data-component-type=\"s-search-result\"]")
	private List<WebElement> productlist;
	public List<WebElement> getProductlist() {
		return productlist;
	}
	@FindBy(xpath="(//button[text()=\"Add to cart\"])[5]")
	private WebElement scrollProductList;
	public WebElement getScrollProductList() {
		return scrollProductList;
	}
}
