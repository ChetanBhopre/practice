package Shopping.resorces;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReportsNG {

	public static ExtentReports getReportObject() {
		// ExtendSparkReport ExtendReport
		            
		String path =System.getProperty("user.dir")+"//reports//index.html";;
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Shoping Cart Test Result");
		reporter.config().setDocumentTitle("shopping Cart");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Chetan");
		
		return extent;
		
		

	}
}
