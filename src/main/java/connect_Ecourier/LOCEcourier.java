package connect_Ecourier;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class LOCEcourier extends BaseInit {
	static StringBuilder msg = new StringBuilder();

	@Test
	public void LocEcourier() throws Exception {
		try {
			logs.info("==== LOC Ecourier : Start ==== " + "\n");
			msg.append("\n\n" + "===== ECourier-Test Utility Test : Start ====" + "\n");

			JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time

			// --Order Creation
			OrderCreationE OC = new OrderCreationE();
			OC.orderCreation(1);

			// --Scroll to get Rate
			jse.executeScript("window.scrollBy(0,400)", "");
			String cost = isElementPresent("TLActualRate_id").getText();
			setData("Sheet2", 1, 31, cost);
			logs.info("Scroll down to Get the Rate");

			// --Moved to Job Status
			WebElement idJob = isElementPresent("TLJobStatusTab_id");
			wait.until(ExpectedConditions.elementToBeClickable(idJob));
			jse.executeScript("arguments[0].click();", idJob);
			logs.info("Clicked on Job Status Tab");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// User story details
			msg.append("*** Ecourier: Submit job to Ecourier, process from Test Utility ***" + "\n");
			
			// TC Acknowledge

			TCAcknowledgeE TCAck = new TCAcknowledgeE();
			TCAck.tcAcknowledge();

			// Pickup Alert
			ReadyForDispatchE RFD = new ReadyForDispatchE();
			RFD.pickupAlert();

			// Confirm PU alert
			try {

				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@id=\"lblStages\"][contains(text(),'Confirm Pu Alert')]")));
				ConfirmPuAlert();
			} catch (Exception e) {

				logs.info("Confirm Pu Alert is Skipped");
				// PICKEDUP
				PickupEcourier PU = new PickupEcourier();
				PU.confirmPickup();

			}

			try {

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@title='Car']")));
				// PICKEDUP
				PickupEcourier PU = new PickupEcourier();
				PU.confirmPickup();

			}

			catch (Exception e) {

				logs.info("Pick up stage activity is done already");

			}

			// E courier process
			EcourierUtility EU = new EcourierUtility();
			EU.EcourierSetStatuReject();
			
		OC.SearchPICKUPID_after_reject_utility();

			// Click on Job status
			PickupEcourier PU = new PickupEcourier();
			PU.ClickonJobStatus();

			// --Refresh App
			OC.refreshApp();

		} catch (Exception e) {
			// TODO: handle exception
			logs.info("Error in LOC E-Courier " + e);

			msg.append("Error in LOC E-Courier");
		}

		logs.info("==== LOC Ecourier : End ==== " + "\n");
		msg.append("==== LOC Ecourier : End ==== " + "\n");

		msg.append("\n" + "===== ECourier-Test Utility Test : End ====" + "\n");

	}

	public static void ConfirmPuAlert() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		WebElement ConfirmPUStage = driver.findElement(By.xpath("//*[@id=\"lblStages\"]"));
		String stage = ConfirmPUStage.getText();
		System.out.println(stage);

		WebElement confirmPUAlert = driver.findElement(By.xpath("//*[@id=\"GreenTickAlertPickup\"]"));
		wait.until(ExpectedConditions.elementToBeClickable(confirmPUAlert));
		jse.executeScript("arguments[0].click();", confirmPUAlert);
		Thread.sleep(10000);

	}

}
