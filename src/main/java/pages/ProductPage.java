package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reusable.Reusable;

public class ProductPage extends Reusable {
	
	WebDriver driver;
	
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	By toastLocator = By.cssSelector("#toast-container");
	By addToCartButton = By.cssSelector(".card-body button:nth-of-type(2)");
	
	
	@FindBy(css=".mb-3") List<WebElement> productList;
	@FindBy(css=".ng-animating") WebElement spiner;
	
	
	public List<WebElement> getProductList() {
		wait_visibilityOfAllElementLocated(productList);
		return productList;
		
	}
	
	public WebElement getProductByName(String item) {
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(item)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:nth-of-type(2)"));
		return prod;
	}
	
	
	
	
	public void addProductToCart(String prod1) throws InterruptedException {
		WebElement prod = getProductByName(prod1);
		prod.findElement(addToCartButton).click();
		wait_visibilityOfElementLocatedBy(toastLocator);
		hard_wait(2000);
		
	}
	
	
	
	
	

	
	

}
