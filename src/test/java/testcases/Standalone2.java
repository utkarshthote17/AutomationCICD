package testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalone2 {
	
	public static void main(String[] args) throws InterruptedException {
		
		String expectedMessage = "THANKYOU FOR THE ORDER.";
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		driver.findElement(By.id("userEmail")).sendKeys("utkarshthote1@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Shoprahulshetty123@#");
		driver.findElement(By.xpath("//input[@name='login']")).click();
		
		
		
		List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = productList.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ADIDAS ORIGINAL")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:nth-of-type(2)")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProducts.stream().anyMatch(item->item.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
		Assert.assertTrue(match);
		
		System.out.println("Product Present in cart");
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Indi");
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".ta-results button:last-of-type"))));
		
		driver.findElement(By.cssSelector(".ta-results button:last-of-type")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		WebElement placeOrderButton =driver.findElement(By.cssSelector(".actions a"));
		js.executeScript("arguments[0].click();", placeOrderButton);
		
		
		String ConfirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertEquals(ConfirmMessage, expectedMessage, "Message not matched");
		
			
			
			
		
	}
}
