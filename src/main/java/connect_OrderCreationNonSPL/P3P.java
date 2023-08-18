package connect_OrderCreationNonSPL;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_OCBaseMethods.CSRAcknowledge;
import connect_OCBaseMethods.OrderCreation;
import connect_OCBaseMethods.Pickup;
import connect_OCBaseMethods.ReadyForDispatch;
import connect_OCBaseMethods.TCAcknowledge;
import connect_OCBaseMethods.TenderTo3P;
import connect_OCBaseMethods.VerifyCustomerBill;

public class P3P extends OrderCreation {

	@Test
	public void p3pservice() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time

		String Env = storage.getProperty("Env");
		if (Env.contains("PROD")) {
			logs.info("Unable to run because of Authentication issue in select drop off location");
		} else {
			// --Order Creation
			OrderCreation OC = new OrderCreation();
			OC.orderCreation(3);

			// --Refresh App
			refreshApp();
		}

	}
}
