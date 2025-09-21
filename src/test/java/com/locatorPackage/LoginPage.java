package com.locatorPackage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BasePage;

public class LoginPage extends BasePage {
	public  LoginPage() 
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//button[@type=\"submit\"]")
	private WebElement continueShopping;
	public WebElement getContinueShopping() {
		return continueShopping;
	}
	@FindBy(id="ap_email_login")
	private WebElement email;
	public WebElement getEmail() {
		return email;
	}
	@FindBy(xpath ="//input[@type=\"submit\"]")
	private WebElement emailContinue;
	public WebElement getEmailContinue() {
		return emailContinue;
	}
	@FindBy(id="ap_password")
	private WebElement password;
	public WebElement getPassword() {
		return password;
	}
	@FindBy(id = "signInSubmit")
	private WebElement signinButton;
	public WebElement getSigninButton() {
		return signinButton;
	}

	@FindBy(xpath="//i[@aria-label=\"Amazon\"]")
	private WebElement amazonImage;
	public WebElement getAmazonImage() {
		return amazonImage;
	}
	@FindBy(xpath = "//span[text()=\"Hello, sign in\"]")
	private WebElement firstSignIn;
	public WebElement getFirstSignIn() {
		return firstSignIn;
	}
	@FindBy(xpath = "(//div[@class=\"a-alert-content\"])[3]")
	private WebElement errorMessage;
	public WebElement getErrorMessage() {
		return errorMessage;
	}
	
}
