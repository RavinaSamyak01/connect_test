package connect_OrderProcessNonSPL;

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

public class FRG extends OrderCreation {

	@Test
	public void frgFreight() throws Exception {
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time

		// --Order Creation
		OrderCreation OC = new OrderCreation();
		OC.orderCreation(9);

		// --Move to Job Status Tab
		WebElement JoStatusTab = isElementPresent("TLJobStatusTab_id");
		wait.until(ExpectedConditions.elementToBeClickable(JoStatusTab));
		JoStatusTab.click();
		logs.info("Clicked on Job Status Tab");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// TC Acknowledge
		TCAcknowledge TCAck = new TCAcknowledge();
		TCAck.tcAcknowledge();

		// Pickup Alert
		ReadyForDispatch RFD = new ReadyForDispatch();
		RFD.pickupAlert();

		// PICKEDUP
		Pickup PU = new Pickup();
		PU.confirmPickup();

		// DELIVERED
		Deliver Del = new Deliver();
		Del.confirmDelivery();
		
		// Verify Margin
		VerifyMargin VM= new VerifyMargin();
		VM.verifyMargin(9);

		// --Refresh App
		refreshApp();

	}

}
