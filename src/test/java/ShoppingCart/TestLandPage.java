package ShoppingCart;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.sun.net.httpserver.Authenticator.Retry;

import ShippngCart.data.DataReader;
import ShoppingCard.AbstractComponenets.AbstractComponent;
import ShoppingCart.TestComponent.BaseTest;
import ShppingCart.PageObjects.PaymentPage;
import ShppingCart.PageObjects.ProductCatalogue;

public class TestLandPage extends BaseTest {
	DataReader JsonData = new DataReader();
	// AbstractComponent utils = new AbstractComponent();

	@Test(dataProvider = "getdata")
	public void cart(HashMap<String, String> input) throws InterruptedException, IOException {
		// String cart_product = "adidas original";
		String contry = "IND";
		String expectedMassage = "THANKYOU FOR THE ORDER.";

		// login----1

		ProductCatalogue catalogue = LandingPage.loginApplication(input.get("email"), input.get("Password"));

		catalogue.getScreenShot("Login");

		// ProductList-------2
		catalogue.ProductList();
		catalogue.getScreenShot("ProductList");

		// utils.

		// add to product to Cart-----3
		catalogue.addToCart(input.get("cart_product"));
		catalogue.getScreenShot("add to cart");

		// Card vaidation-------4
		boolean match = catalogue.VerifyProductDisplay(input.get("cart_product"));
//		Assert.assertTrue(match);		
		PaymentPage page = catalogue.goToCheckout();

		// payment page------5
		page.selectCountry(contry);
		// page.submitOrder();

		// ConfirmMassgae--6
		catalogue.confirmationMassge(expectedMassage);

	}

	@Test(dependsOnMethods = "cart")
	public void dummyTestcase() {
		System.out.println("DummyTest");

		// fake fail
		ProductCatalogue catalogue = LandingPage.loginApplication("fakeemail", "fakePass");
		driver.findElement(By.id("nothing")).click();
		;

	}

	@DataProvider
	public Object[][] getdata() throws IOException {
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("email", "Shaktiman@gmail.com");
//		map.put("Password", "Shaktiman@123");
//		map.put("cart_product",  "adidas original");

		List<HashMap<String, String>> JData = JsonData.getJasodToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\ShippngCart\\data\\purchesOrder.json");

		return new Object[][] { { JData.get(0) } };

	}

//	@DataProvider
//	public Object[][] getdata() {
//		return new Object[][] { { "Shaktiman@gmail.com", "Shaktiman@123", "adidas original" }};
//
//	}

	public String getScreenShot(String ScreenShotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\Screenshots\\" + ScreenShotName + ".png");
		FileUtils.copyFile(src, file);

		return System.getProperty("user.dir") + "\\Screenshots\\" + ScreenShotName + ".png";

	}

}
