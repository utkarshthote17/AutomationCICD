package testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CartPage;
import pages.LandingPage;
import pages.LastPage;
import pages.PaymentPage;
import pages.ProductPage;

public class Standalone3 {
	
	public static void main(String[] args) throws InterruptedException {
		
		String expectedMessage = "THANKYOU FOR THE ORDER.";
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		LandingPage landingpage = new LandingPage(driver);
		landingpage.goTo();
		landingpage.loginApplication("utkarshthote1@gmail.com", "Shoprahulshetty123@#");
		ProductPage productpage = new ProductPage(driver);
		List<WebElement> productList = productpage.getProductList();
		productpage.addProductToCart("ADIDAS ORIGINAL");
		productpage.goToCart();
		CartPage cartpage= new CartPage(driver);
		Boolean match = cartpage.verifyandReturnProductInCart("ADIDAS ORIGINAL");
		Assert.assertTrue(match);
		cartpage.checkoutCart();
		PaymentPage paymentpage = new PaymentPage(driver);
		paymentpage.selectCountry();
		paymentpage.placeOrder();
		LastPage lastpage = new LastPage(driver);
		String confirmationMessage = lastpage.getConfirmationText();
		Assert.assertEquals(confirmationMessage, expectedMessage, "Confirm message not matched");
	
			
		
	}
}
