package reusable;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.CartPage;
import pages.OrderPage;

public class Reusable {
	
	WebDriver driver;
	
	public Reusable(WebDriver driver) {
		this.driver = driver;
		
		
	}
	
	
	
	@FindBy(css="[routerlink*='cart']") WebElement cartButton;
	@FindBy(css="[routerlink*='myorders']") WebElement orderButton;
	
	
	
	
	public CartPage goToCart() {
		cartButton.click();
		CartPage cartpage= new CartPage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrders() {
		orderButton.click();
		OrderPage orderpage= new OrderPage(driver);
		return orderpage;
	}
	
	
	public void wait_visibilityOfElementLocatedBy(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
	public void wait_visibilityOfElementLocated(WebElement locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(locator));
		
	}
	
	public void wait_visibilityOfAllElementLocated(List<WebElement> locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(locator));
		
	}

	
	public void wait_invisibilityOf(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(elem));
		
	}
	
	public void wait_elementToBeClickable(WebElement locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		
	}
	
	public void Js_Click(WebElement elem) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", elem);
	}
	
	public void hard_wait(long time) throws InterruptedException {
		Thread.sleep(time);
	}
	


}
