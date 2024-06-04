package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusable.Reusable;

public class OrderPage extends Reusable {
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	
	
	@FindBy(css="tr td:nth-of-type(2)") List<WebElement> orderProducts;
	@FindBy(xpath="//button[text()='Checkout']") WebElement checkoutButton;
	
	
	public Boolean verifyandReturnOrderInCart(String productname) {
		
		Boolean match = orderProducts.stream().anyMatch(item->item.getText().equalsIgnoreCase(productname));
		return match;
		
	}
	
	
	
	
	
	


	
	
	

	
	

}
