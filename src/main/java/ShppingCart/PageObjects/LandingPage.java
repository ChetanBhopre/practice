package ShppingCart.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShoppingCard.AbstractComponenets.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	WebElement userEmail=driver.findElement(By.cssSelector("#userEmail"));
	@FindBy(css = "#userEmail")
	WebElement userEmail;

//	driver.findElement(By.cssSelector("#userPassword")).sendKeys("Shaktiman@123");

	@FindBy(css = "#userPassword")
	WebElement userPassword;

// driver.findElement(By.cssSelector("input#login")).click();
	@FindBy(css = "input#login")
	WebElement LoginButton;
	
	@FindBy(xpath="//div[@role='alert']")
	WebElement ErrorMassage;
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		LoginButton.click();
		ProductCatalogue catalogue = new ProductCatalogue(driver);
		
		return catalogue;
	}

	public void ApplicationLink() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMassage() { 
		waitForWebElementToAppear(ErrorMassage);
		return ErrorMassage.getText();
	}
	
}
