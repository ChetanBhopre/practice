package ShoppingCart;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ShppingCart.PageObjects.LandingPage;


public class StandAloneTest {

	@Test
	public void cart() throws InterruptedException, IOException {
		String cart_product = "adidas original";
		WebDriver driver = new ChromeDriver();
		LandingPage loginPage = new LandingPage(driver);
		
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");

		// login
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("Shaktiman@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Shaktiman@123");
		driver.findElement(By.cssSelector("input#login")).click();

		// find ALl Product
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mb-3')]")));

		List<WebElement> allProduct = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));

		WebElement prod = allProduct.stream().filter(
				product -> product.findElement(By.cssSelector("b")).getText().trim().equalsIgnoreCase(cart_product))
				.findFirst().orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click();

		System.out.println(driver.findElement(By.cssSelector("[class='cartSection'] h3")).getText());

		List<WebElement> cartavaialbleProd = driver.findElements(By.cssSelector("[class='cartSection'] h3"));

		cartavaialbleProd.stream().filter(cart -> cart.getText().equalsIgnoreCase(cart_product));

		boolean match = cartavaialbleProd.stream()
				.anyMatch(cartavaialble -> cartavaialble.getText().equalsIgnoreCase(cart_product));

		Assert.assertTrue(match);

		// click on checkOutButton

		driver.findElement(By.cssSelector(".totalRow button")).click();

		// payment page

		driver.findElement(By.cssSelector("[placeholder = 'Select Country']")).sendKeys("Ind");

		List<WebElement> Countrys = driver.findElements(By.cssSelector("span[class='ng-star-inserted']"));

		for (WebElement SelectCountry : Countrys) {
			try {
				wait.until(ExpectedConditions.visibilityOf(SelectCountry));
				if (SelectCountry.getText().equalsIgnoreCase("INDIA")) {

					WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(5));
					WebElement element = waits.until(ExpectedConditions.elementToBeClickable(SelectCountry));
					element.click();
				}
			} catch (StaleElementReferenceException e) {
				// Handle the stale element reference exception if it occurs
				System.out.println("Stale element reference encountered.");
				// Optionally, you can re-find the elements or take other appropriate actions
			}

		}

		System.out.println(driver.findElement(By.xpath("(//div[@class='payment__title'])[1]")).getText());

		// click on place order
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));

		System.out.println(driver.findElement(By.cssSelector("a[class*='action__submit']")).getText());

		WebElement takeorder = driver.findElement(By.cssSelector("a[class*='action__submit']"));

		Thread.sleep(10000);

		 screenShotElement(takeorder,
		 "C:\\Users\\DELL\\eclipse-workspace\\miniProject\\ScreenShot\\msg.png");

		Actions actions = new Actions(driver);
		actions.moveToElement(takeorder).click().perform();

		// confirmation page
		String expectedMassage = "THANKYOU FOR THE ORDER.";
		                       

		String massage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		System.out.println(massage);
		Assert.assertEquals(massage, expectedMassage);

		Assert.assertTrue(massage.equalsIgnoreCase(expectedMassage));

	}

	public static void screenShotElement(WebElement element, String path) throws IOException {
		File src = element.getScreenshotAs(OutputType.FILE);
		File trg = new File(path);
		FileUtils.copyFile(src, trg);
	}

}

// SelectCountry.stream().anyMatch(country->country.getText().equalsIgnoreCase("INDIA"));
