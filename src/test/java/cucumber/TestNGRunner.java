package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src//test//java//cucumber",glue="stepdefinitions", monochrome=true,tags="@Regression", plugin= {"html:target/cucumber1.html"})
public class TestNGRunner extends AbstractTestNGCucumberTests {
	
	

}

