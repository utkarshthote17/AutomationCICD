package stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.LandingPage;
import pages.LastPage;
import pages.PaymentPage;
import pages.ProductPage;
import testcomponent.Base;

public class StandaloneStepDefImp extends Base {
	
	public LandingPage landingpage;
	public ProductPage productpage;
	public CartPage cartpage;
	public PaymentPage paymentpage;
	public LastPage lastpage;
	String expectedMessage = "THANKYOU FOR THE ORDER.";
	
	@Given("I landed on E-Commerce Page")
	public void langingtoCommerceAapp() throws IOException {
		
		landingpage = launchApplication();
	}
	
	
	@Given("^Login to App using username(.+) and password(.+)$")
	public void loginToApp(String email, String password) {
		
		productpage = landingpage.loginApplication(email, password);
	}
	
	
	@When("^I add product(.+) to cart$")
	public void addProductToCart(String prod) throws InterruptedException {
		
		List<WebElement> productList = productpage.getProductList();
		productpage.addProductToCart(prod);
		
	}
	
	@When("^Checkout product(.+) and submit order$")
	public void checkoutAndSubmitOrder(String prod) {
		
		cartpage = productpage.goToCart();
		Boolean match = cartpage.verifyandReturnProductInCart(prod);
		Assert.assertTrue(match);
		paymentpage =cartpage.checkoutCart();
		paymentpage.selectCountry();
		lastpage = paymentpage.placeOrder();
				
		
	}
	
	
	@Then("{string} message is displayed on Confirmation page")
	public void confirmorder(String string) {
		String confirmationMessage = lastpage.getConfirmationText();
		Assert.assertEquals(confirmationMessage, string, "Confirm message not matched");
		driver.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
