package connect_OrderCreationNonSPL;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_OCBaseMethods.Deliver;
import connect_OCBaseMethods.OrderCreation;
import connect_OCBaseMethods.Pickup;
import connect_OCBaseMethods.ReadyForDispatch;
import connect_OCBaseMethods.TCAcknowledge;
import connect_OCBaseMethods.VerifyMargin;

public class SDC extends OrderCreation {

	@Test
	public void sdcSameDayCity() throws Exception {
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time

		// --Order Creation
		OrderCreation OC = new OrderCreation();
		OC.orderCreation(7);

		// --Refresh App
		OC.refreshApp();
	}
}
