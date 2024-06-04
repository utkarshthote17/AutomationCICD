package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusable.Reusable;

public class LandingPage extends Reusable {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(id="userEmail") WebElement userEmailField;
	@FindBy(id="userPassword") WebElement userPasswordField;
	@FindBy(xpath="//input[@name='login']") WebElement loginButton;
	@FindBy(xpath="//div[text()=' Incorrect email or password. ']") WebElement ErrorPopup;
	
	
	
	
	public String getErrorMessage() {
		wait_visibilityOfElementLocated(ErrorPopup);
		return ErrorPopup.getText();
		
	}
	
	
	
	public ProductPage loginApplication(String username, String password) {
		
		userEmailField.sendKeys(username);
		userPasswordField.sendKeys(password);
		loginButton.click();
		ProductPage productpage = new ProductPage(driver);
		return productpage;
	}
	
	
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	

}
