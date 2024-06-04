package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reusable.Reusable;

public class PaymentPage extends Reusable {
	
	WebDriver driver;
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	String productName = "ADIDAS ORIGINAL";
	String countryName = "India";
	
	
	@FindBy(xpath="//input[@placeholder='Select Country']") WebElement countryField;
	@FindBy(css=".ta-results button:last-of-type") WebElement optionCountry;
	@FindBy(css=".actions a") WebElement placeOrderButton;
	
	
	public void selectCountry() {
		countryField.sendKeys(countryName);
		wait_elementToBeClickable(optionCountry);
		optionCountry.click();
	}
	
	
	public LastPage placeOrder() {
		Js_Click(placeOrderButton);
		LastPage lastpage = new LastPage(driver);
		return lastpage;
	}
	
	
	
	

}
