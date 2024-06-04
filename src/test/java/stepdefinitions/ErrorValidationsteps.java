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

public class ErrorValidationsteps extends Base {
	
	public LandingPage landingpage;
	
	@Given("landed on E-Commerce Page")
	public void landingtoCommerceAapp() throws IOException {
		
		landingpage = launchApplication();
	}
	
	
	@When("^Login to App with username(.+) and password(.+)$")
	public void loginToApp(String email, String password) {
		
		landingpage.loginApplication(email, password);
	}
	
	
	@Then("{string} message is displayed")
	public void confirmorder(String string) {
		Assert.assertEquals(string, landingpage.getErrorMessage());
		driver.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
