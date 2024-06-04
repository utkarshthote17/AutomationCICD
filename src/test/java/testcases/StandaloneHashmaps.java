package testcases;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.dockerjava.transport.DockerHttpClient.Request.Method;

import pages.CartPage;
import pages.LastPage;
import pages.OrderPage;
import pages.PaymentPage;
import pages.ProductPage;
import testcomponent.Base;

public class StandaloneHashmaps extends Base {
	
	String productName = "ADIDAS ORIGINAL";
	
	@Test(dataProvider="getData", groups= {"purchase"})
	public void standaloneTest(HashMap<String, String> input) throws IOException, InterruptedException {
		
		
		
		String expectedMessage = "THANKYOU FOR THE ORDER.";
		ProductPage productpage = landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> productList = productpage.getProductList();
		productpage.addProductToCart(input.get("product"));
		CartPage cartpage = productpage.goToCart();
		Boolean match = cartpage.verifyandReturnProductInCart(input.get("product"));
		Assert.assertTrue(match);
		PaymentPage paymentpage =cartpage.checkoutCart();
		paymentpage.selectCountry();
		LastPage lastpage = paymentpage.placeOrder();
		String confirmationMessage = lastpage.getConfirmationText();
		Assert.assertEquals(confirmationMessage, expectedMessage, "Confirm message not matched");
		
	
	}

	
	//comment entered for checking CICD
	
	@Test(dependsOnMethods= {"standaloneTest"})
	public void VerifyOrderTest() throws IOException, InterruptedException {
		
		String expectedMessage = "THANKYOU FOR THE ORDER.";
		ProductPage productpage = landingpage.loginApplication("utkarshthote1@gmail.com", "Shoprahulshetty123@#");
		OrderPage orderpage = productpage.goToOrders();
		Boolean match = orderpage.verifyandReturnOrderInCart("ADIDAS ORIGINAL");
		Assert.assertTrue(match);
		

	}
	
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\testdata.json");
		
		return new Object[] [] {
			{data.get(0)},{data.get(1)}
		};
		
		
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "utkarshthote1@gmail.com");
//		map.put("password", "Shoprahulshetty123@#");
//		map.put("product", "ADIDAS ORIGINAL");
//		
//		HashMap<Object, Object> map1 = new HashMap<Object, Object>();
//		map1.put("email", "utkarshthote1@gmail.com");
//		map1.put("password", "Shoprahulshetty12@#");
//		map1.put("product", "ADIDAS ORIGINAL");
		
		
//		return new Object[] [] {
//			{map},{map1}
//		};
		
		
		
		
	}
	
	
	
	
}
