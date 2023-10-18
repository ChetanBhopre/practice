package ShppingCart.PageObjects;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import ShoppingCard.AbstractComponenets.AbstractComponent;

public class CartValidation_Old extends AbstractComponent {

	WebDriver driver;

	public CartValidation_Old(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	List<WebElement> cartavaialbleProd = driver.findElements(By.cssSelector("[class='cartSection'] h3"));

	@FindBy(css = "[class='cartSection'] h3")
	private List<WebElement> cartavaialbleProd;

	@FindBy(css = ".totalRow button")
	WebElement checkOutButton;

	public boolean vrifyProductDisplayed(String cart_product) {

		boolean match = cartavaialbleProd.stream().anyMatch(cartavaialble -> cartavaialble.getText().equalsIgnoreCase(cart_product));

		return match;

	}

	public void goToPaymntPage() {
		checkOutButton.click();
	}

	// click on checkOutButton

}
