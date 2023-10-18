package ShoppingCard.AbstractComponenets;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

//Explicitly Wait
	public void waitForElementToAppear(By Element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Element));
	}
	
	public void waitForWebElementToAppear(WebElement Element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Element));
	}

	public void waitForElementInvisiblity(WebElement animatingPage) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(animatingPage));

	}
	
	public  void screenShotElement(WebElement element, String path) throws IOException {
		File src = element.getScreenshotAs(OutputType.FILE);
		File trg = new File("C:\\Users\\DELL\\eclipse-workspace\\SeleniumFrameWordDesign\\Screenshots");
		FileUtils.copyFile(src, trg);
	}
	
	public void getScreenShot(String ScreenShotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src =ts.getScreenshotAs(OutputType.FILE);
		File trg = new File("C:\\Users\\DELL\\eclipse-workspace\\SeleniumFrameWordDesign\\Screenshots\\"+ScreenShotName+".png");
		FileUtils.copyFile(src, trg);
		
	}
}
