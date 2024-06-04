package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusable.Reusable;

public class CartPage extends Reusable {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	
	
	@FindBy(css=".cartSection h3") List<WebElement> cartProducts;
	@FindBy(xpath="//button[text()='Checkout']") WebElement checkoutButton;
	
	
	public Boolean verifyandReturnProductInCart(String productname) {
		
		Boolean match = cartProducts.stream().anyMatch(item->item.getText().equalsIgnoreCase(productname));
		return match;
		
	}
	
	public PaymentPage checkoutCart() {
		checkoutButton.click();
		PaymentPage paymentpage = new PaymentPage(driver);
		return paymentpage;
	}
	
	
	
	


	
	
	

	
	

}
