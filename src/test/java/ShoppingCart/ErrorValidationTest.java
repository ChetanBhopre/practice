package ShoppingCart;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ShoppingCart.TestComponent.BaseTest;
import ShppingCart.PageObjects.CartValidation;
import ShppingCart.PageObjects.ConfirmationPage;
import ShppingCart.PageObjects.LandingPage;
import ShppingCart.PageObjects.PaymentPage;
import ShppingCart.PageObjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHanding"})
	public void LoginErrorValidation() {

		LandingPage.loginApplication("Shaktiman@gmail.com", "WrondPassword");
		String expectedMassage = LandingPage.getErrorMassage();
		Assert.assertEquals("Incorrect email or password.", expectedMassage);

	}

	@Test
	public void productErrorValidate() throws IOException, InterruptedException {
		String cart_product = "adidas original";
	
		// login----1

		ProductCatalogue catalogue = LandingPage.loginApplication("Shaktiman@gmail.com", "Shaktiman@123");
		// ProductList-------2
		List<WebElement> allList = catalogue.ProductList();
		// add to product to Cart-----3
		catalogue.addToCart(cart_product);
		// Card vaidation-------4
		boolean match = catalogue.VerifyProductDisplay("adidas");
		Assert.assertFalse(match);

	}
}
