package connect_OrderProcessSPL;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;
import connect_OCBaseMethods.ConfirmPUAlert;
import connect_OCBaseMethods.ConfirmPULLstg;
import connect_OCBaseMethods.OrderCreation;
import connect_OCBaseMethods.SendPull;
import connect_OCBaseMethods.TCAcknowledge;
import connect_OCBaseMethods.TenderTo3P;
import connect_OCBaseMethods.VerifyCustomerBill;

public class H3P extends BaseInit {
	@Test
	public void h3P() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		// Actions act = new Actions(driver);

		// --Order Creation
		OrderCreation OC = new OrderCreation();

		OC.orderCreation(10);
		
		try {
 			//--PickUp Validation
			WebElement PickupDialogue=isElementPresent("EOPupDialogue_id");
			wait.until(ExpectedConditions.visibilityOf(PickupDialogue));
			//--Click on OK button
			WebElement DOKBtn = isElementPresent("EOPDOkBtn_id");
			jse.executeScript("arguments[0].click();", DOKBtn);
			logs.info("Clicked on Ok button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		}catch(Exception ee) {
			logs.info("PickUp window pop up not displayed");

		}
		

		// --Get ServiceID
		String ServiceID = OC.getServiceID();

		// --Scroll to get Rate
		jse.executeScript("window.scrollBy(0,400)", "");
		String cost = isElementPresent("TLActualRate_id").getText();
		setData("Sheet1", 10, 31, cost);
		logs.info("Scroll down to Get the Rate");

		// --Error Pop Up
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@class=\"ngdialog-content ui-draggable\"]")));
			getScreenshot(driver, "ErrorPopUp_" + ServiceID);
			WebElement ErrorPUp = isElementPresent("EOErrorPUp_id");
			wait.until(ExpectedConditions.elementToBeClickable(ErrorPUp));
			jse.executeScript("arguments[0].click();", ErrorPUp);
			logs.info("Clicked on OK button of Error Message");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		} catch (

		Exception eError) {
			System.out.println("error pop up is not displayed");
		}

		// --Moved to Job Status
		WebElement idJob = isElementPresent("TLJobStatusTab_id");
		wait.until(ExpectedConditions.elementToBeClickable(idJob));
		jse.executeScript("arguments[0].click();", idJob);
		logs.info("Clicked on Job Status Tab");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// TC Acknowledge
		TCAcknowledge TCAck = new TCAcknowledge();
		TCAck.tcAcknowledge();

		// Send Pull ALert
		SendPull Sp = new SendPull();
		Sp.sendPull();

		// Confirm PUll alert
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		ConfirmPUAlert CP = new ConfirmPUAlert();
		CP.confirmPUAlert();

		// confirm Pull
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		ConfirmPULLstg pull = new ConfirmPULLstg();
		pull.ConfirmPullstage(10);

		// Tender to 3P
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		TenderTo3P tenderTo3P = new TenderTo3P();
		tenderTo3P.tndrTo3P();

		// -- 3P Deliver
		OC.stage3Pdeliver(10);

		/*
		 * // Verify Customer Bill VerifyCustomerBill VCB = new VerifyCustomerBill();
		 * VCB.verifyCustomerBill(10);
		 */

		// --Refresh App
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		OC.refreshApp();

		msg.append("\n\n\n");
	}

}
