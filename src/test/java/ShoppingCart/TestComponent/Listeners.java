package ShoppingCart.TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Shopping.resorces.ExtendReportsNG;
import ShoppingCard.AbstractComponenets.AbstractComponent;

public class Listeners extends BaseTest implements ITestListener {
	

	ExtentReports extent =ExtendReportsNG.getReportObject();
	ExtentTest listnerTest;
	
	
	@Override
	public void onTestStart(ITestResult result) {
		//extent trpoty
		 listnerTest =extent.createTest(result.getMethod().getMethodName());
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		listnerTest.generateLog(Status.PASS," Testcase pass ");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		listnerTest.generateLog(Status.FAIL, "Test case fail");
		listnerTest.fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String screenshotPath= null;
		try {
			screenshotPath = getScreenShot(result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		listnerTest.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

}
