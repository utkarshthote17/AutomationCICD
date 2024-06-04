package resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	
	
	public static ExtentReports generateReport() {
		
		File file = new File(System.getProperty("user.dir")+"//reports//index.html");
		ExtentSparkReporter reporter  = new ExtentSparkReporter(file);
		reporter.config().setReportName("My Automation report");
		reporter.config().setDocumentTitle("Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "utho");
		return extent;
		
		
	}
	
}
