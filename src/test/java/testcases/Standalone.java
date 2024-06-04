package testcases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.LastPage;
import pages.OrderPage;
import pages.PaymentPage;
import pages.ProductPage;
import testcomponent.Base;

public class Standalone extends Base {
	
	String productName = "ADIDAS ORIGINAL";
	
	@Test
	public void standaloneTest() throws IOException, InterruptedException {
		
		String expectedMessage = "THANKYOU FOR THE ORDER.";
		ProductPage productpage = landingpage.loginApplication("utkarshthote1@gmail.com", "Shoprahulshetty123@#");
		List<WebElement> productList = productpage.getProductList();
		productpage.addProductToCart("ADIDAS ORIGINAL");
		CartPage cartpage = productpage.goToCart();
		Boolean match = cartpage.verifyandReturnProductInCart("ADIDAS ORIGINAL");
		Assert.assertTrue(match);
		PaymentPage paymentpage =cartpage.checkoutCart();
		paymentpage.selectCountry();
		LastPage lastpage = paymentpage.placeOrder();
		String confirmationMessage = lastpage.getConfirmationText();
		Assert.assertEquals(confirmationMessage, expectedMessage, "Confirm message not matched");
		
		
	}
	
	
	
	
	@Test(dependsOnMethods= {"standaloneTest"})
	public void VerifyOrderTest() throws IOException, InterruptedException {
		
		String expectedMessage = "THANKYOU FOR THE ORDER.";
		ProductPage productpage = landingpage.loginApplication("utkarshthote1@gmail.com", "Shoprahulshetty123@#");
		OrderPage orderpage = productpage.goToOrders();
		Boolean match = orderpage.verifyandReturnOrderInCart("ADIDAS ORIGINAL");
		Assert.assertTrue(match);
		

	}
	
	
	
	
}
