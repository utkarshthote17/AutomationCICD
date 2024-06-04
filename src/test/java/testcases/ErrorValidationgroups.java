package testcases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import pages.CartPage;
import pages.ProductPage;
import testcomponent.Base;

public class ErrorValidationgroups extends Base {
	
	
	@Test(groups= {"Error"}, retryAnalyzer=testcomponent.Retry.class)
	public void ErrorTest() throws IOException, InterruptedException {
		
		String expectedMessage = "Incorrect email or password.##";
		landingpage.loginApplication("utkarshthote1@gmail.com", "Shoprahulshetty1@#");
		Assert.assertEquals(landingpage.getErrorMessage(), expectedMessage);
		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		
		ProductPage productpage = landingpage.loginApplication("utkarshthote1@gmail.com", "Shoprahulshetty123@#");
		List<WebElement> productList = productpage.getProductList();
		productpage.addProductToCart("ADIDAS ORIGINAL");
		CartPage cartpage = productpage.goToCart();
		Boolean match = cartpage.verifyandReturnProductInCart("ADIDAS ORIGINAL#");
		Assert.assertTrue(match);
		
			
		
	}
}
