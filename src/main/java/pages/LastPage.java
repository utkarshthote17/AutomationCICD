package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusable.Reusable;

public class LastPage extends Reusable {
	
	WebDriver driver;
	
	public LastPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	String expectedMessage = "THANKYOU FOR THE ORDER.";
	
	
	@FindBy(css=".hero-primary") WebElement confirmText;
	
	
	
	
	public String getConfirmationText() {
		 return confirmText.getText();
		
	}
	
	
	
	

}
