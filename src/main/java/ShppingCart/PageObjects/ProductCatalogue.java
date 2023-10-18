package ShppingCart.PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ShoppingCard.AbstractComponenets.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// find ALl Product

	By productLocat = By.xpath("//div[contains(@class,'mb-3')]");
	By productTextElement = By.cssSelector("b");
	By addToCard = By.cssSelector(".card-body button:last-of-type");
	By ToastMassage = By.cssSelector("#toast-container");

	// List<WebElement> allProduct =
	// driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));

	@FindBy(xpath = "//div[contains(@class,'mb-3')]")
	List<WebElement> allProduct;

	@FindBy(css = ".ng-animating")
	WebElement animating;

	// driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click();
	@FindBy(xpath = "//button[contains(@routerlink,'cart')]")
	WebElement Button_routerlink;

	public List<WebElement> ProductList() {
		waitForElementToAppear(productLocat);

		return allProduct;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = ProductList().stream().filter(
				product -> product.findElement(productTextElement).getText().trim().equalsIgnoreCase(productName))
				.findFirst().orElse(null);

		return prod;
	}

	public void addToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCard).click();

		waitForElementToAppear(ToastMassage);
	//	waitForElementInvisiblity(animating);
		
		Thread.sleep(1000);
		Button_routerlink.click();
	

	}

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;

	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}

	public PaymentPage goToCheckout() {
		checkoutEle.click();
		return new PaymentPage(driver);
		

	}
	
	@FindBy(css=".hero-primary")
	WebElement Massage;

	public void confirmationMassge(String expectedMassage) {
		
		String actualMassage= Massage.getText();

		Assert.assertEquals(actualMassage, expectedMassage);

	Assert.assertTrue(actualMassage.equalsIgnoreCase(expectedMassage));
	
	
	}

}



