package connect_OrderProcessNonSPL;

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

			// --Scroll to get Rate
			jse.executeScript("window.scrollBy(0,400)", "");
			Thread.sleep(2000);
			WebElement costDiv = isElementPresent("TLActualRate_id");
			jse.executeScript("arguments[0].scrollIntoView();", costDiv);
			Thread.sleep(2000);
			String cost = isElementPresent("TLActualRate_id").getText();
			setData("Sheet1", 1, 31, cost);
			logs.info("Scroll down to Get the Rate");

			// --Moved to Job Status
			WebElement idJob = isElementPresent("TLJobStatusTab_id");
			wait.until(ExpectedConditions.elementToBeClickable(idJob));
			jse.executeScript("arguments[0].click();", idJob);
			logs.info("Clicked on Job Status Tab");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// CSR Acknowledge
			CSRAcknowledge CAck = new CSRAcknowledge();
			CAck.csrAcknowledge();

			// TC Acknowledge
			TCAcknowledge TCAck = new TCAcknowledge();
			TCAck.tcAcknowledge();

			// Pickup Alert
			ReadyForDispatch RFD = new ReadyForDispatch();
			RFD.pickupAlert();

			// PICKEDUP
			Pickup PU = new Pickup();
			PU.confirmPickup();

			// Tender to 3P
			TenderTo3P T3P = new TenderTo3P();
			T3P.tndrTo3P();

			// -- 3P Deliver
			OC.stage3Pdeliver(3);

			// Verify Customer Bill
			VerifyCustomerBill VCB = new VerifyCustomerBill();
			VCB.verifyCustomerBill(3);

			// --Refresh App
			refreshApp();
		}

	}
}
