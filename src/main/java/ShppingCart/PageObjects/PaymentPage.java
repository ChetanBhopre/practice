package ShppingCart.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShoppingCard.AbstractComponenets.AbstractComponent;

public class PaymentPage extends AbstractComponent {

	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	private WebElement submit;

	@FindBy(css = "[placeholder='Select Country']")
	private WebElement country;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectCountry;

	private By results = By.cssSelector(".ta-results");

	public void selectingCountry(String countryName) throws InterruptedException {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
		try {
			Thread.sleep(10000);
			a.moveToElement(submit).click().perform();
		} catch (Exception e) {
			e.printStackTrace();
			a.moveToElement(submit).click().perform();

		}
	}
	public void selectCountry(String countryName) throws InterruptedException {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
		//Thread.sleep(5000);
		//Creating the JavascriptExecutor interface object by Type casting		
        JavascriptExecutor js = (JavascriptExecutor)driver;	
      //Perform Click on LOGIN button using JavascriptExecutor		
        js.executeScript("arguments[0].click();", submit);
	
		//a.moveToElement(submit).click().perform();
	}

	public ProductCatalogue submitOrder()  throws InterruptedException {
		Thread.sleep(10000);
		submit.click();
		return new ProductCatalogue(driver);

	}

}
