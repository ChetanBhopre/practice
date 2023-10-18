package ShoppingCart.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ShppingCart.PageObjects.CartValidation;
import ShppingCart.PageObjects.LandingPage;

public class BaseTest{

	public WebDriver driver;
	public LandingPage LandingPage;
	public CartValidation cartvalidat = new CartValidation(driver);

	public WebDriver InitializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\DELL\\eclipse-workspace\\safesSendReturn\\src\\main\\java\\safesSendReturn\\resoruces\\GlobalData.properties");
		prop.load(fis);

		String BrowserNme = prop.getProperty("browser");

		if (BrowserNme.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();

		} else if (BrowserNme.equalsIgnoreCase("Safari")) {
			driver = new ChromeDriver();
		}
		
	

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;

	}

	@BeforeMethod(alwaysRun = true)

	public LandingPage launchApp() throws IOException {

		driver = InitializeDriver();
		LandingPage = new LandingPage(driver);
		LandingPage.ApplicationLink();
		return LandingPage;

	}
	
	public String getScreenShot(String ScreenShotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src =ts.getScreenshotAs(OutputType.FILE);
		File trg = new File("C:\\Users\\DELL\\eclipse-workspace\\SeleniumFrameWordDesign\\Screenshots\\"+ScreenShotName+".png");
		FileUtils.copyFile(src, trg);
		return "C:\\Users\\DELL\\eclipse-workspace\\SeleniumFrameWordDesign\\Screenshots\\"+ScreenShotName+".png";
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	
}
