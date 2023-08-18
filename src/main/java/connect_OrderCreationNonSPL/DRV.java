package connect_OrderCreationNonSPL;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_OCBaseMethods.Deliver;
import connect_OCBaseMethods.HoldAtOrigin;
import connect_OCBaseMethods.OrderCreation;
import connect_OCBaseMethods.OutForDelivery;
import connect_OCBaseMethods.Pickup;
import connect_OCBaseMethods.ReAlertForDelivery;
import connect_OCBaseMethods.ReadyForDispatch;
import connect_OCBaseMethods.TCAcknowledge;
import connect_OCBaseMethods.VerifyMargin;

public class DRV extends OrderCreation {

	@Test
	public void drvDriver() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		Actions act = new Actions(driver);

		// --Order Creation
		OrderCreation OC = new OrderCreation();
		OC.orderCreation(5);

		
		// --Refresh App
		refreshApp();

	}

}
