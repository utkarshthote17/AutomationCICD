package testcomponent;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LandingPage;

public class Base {
	
	public WebDriver driver;
	public LandingPage landingpage;
	
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties properties = new Properties();
		FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");
		properties.load(file);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :properties.getProperty("browser");
		//properties.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			ChromeOptions option = new ChromeOptions();
			if(browserName.contains("headless")) {
				option.addArguments("headless");
			}
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(option);
			
		}
		else if(browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();			
		}
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
		
	}
	
	
	
	public String getScreenshot(String testname,WebDriver driver) throws IOException {
		
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//"+testname+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//"+testname+".png";
		
	}
	
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		
		String JsonContent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		
		//String to Json -- we need jackson Databind  dependency
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
		
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		
		driver=initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() {
		driver.close();
	}
	
	
}
