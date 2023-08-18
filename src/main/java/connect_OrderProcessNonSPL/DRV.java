package connect_OrderProcessNonSPL;

import java.time.Duration;

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

		// --Scroll to HAS
		WebElement HAS = isElementPresent("DRVHAS_xpath");
		jse.executeScript("arguments[0].scrollIntoView();", HAS);
		Thread.sleep(2000);

		// --Select HAS
		wait.until(ExpectedConditions.elementToBeClickable(HAS));
		act.moveToElement(HAS).click().build().perform();
		logs.info("Select HAS from Package Information");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// --Click on Save Changes
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
		isElementPresent("TLSaveChanges_id").click();
		logs.info("Clicked on Save Changes button");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

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

		// HOLD @ ORIGIN
		HoldAtOrigin HAO = new HoldAtOrigin();
		HAO.hldAtOrigin();

		// RE-ALERT FOR DELIVERY
		ReAlertForDelivery RAFD = new ReAlertForDelivery();
		RAFD.reAlertfordel();

		// OUT FOR DELIVERY
		OutForDelivery OFD = new OutForDelivery();
		OFD.outForDel();

		// DELIVERED
		Deliver Del = new Deliver();
		Del.confirmDelivery();

		// Verify Margin
		VerifyMargin VM = new VerifyMargin();
		VM.verifyMargin(5);
		
		// --Refresh App
		refreshApp();

	}

}
