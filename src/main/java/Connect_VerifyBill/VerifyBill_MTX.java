package Connect_VerifyBill;

import java.io.IOException;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class VerifyBill_MTX extends BaseInit {

	@Test
	public void MTX() throws IOException, Exception {

		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
		Actions act = new Actions(driver);
		try {

			logs.info("==== Verify Bill - MTX : Start ==== " + "\n");
			msg.append("\n"+"==== Verify Bill - MTX : Start  ==== " + "\n");

			// -- refresh page

			isElementPresent("RefreshLogo_xpath");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// Open Verify Bill
			VerifyBillBase VB = new VerifyBillBase();
			VB.OpenVerifyBill();

			// MTX
			WebElement VBMTX = isElementPresent("VB_MTX_xpath");
			wait.until(ExpectedConditions.visibilityOf(VBMTX));
			wait.until(ExpectedConditions.elementToBeClickable(VBMTX));
			act.moveToElement(VBMTX).click().build().perform();
			logs.info("Click on UNVERIFIED MTX");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			msg.append("\n" + "Verify Bill: Tab Verification For MTX" + "\n");

			// Check with Cutoff date filters
			VB.CutOffdateFilter(3);

			// Check with PickUp Id filter
			VB.pickUpIdFilter(3);

			// Check with Service filter
			VB.serviceFilter(3);

			// Check with account Filter
			VB.accountFilter(3);

			// Verify Re-Calculate Charges
			VB.VerifyReCalCharges(3);

			// Check JobID Filter
			VB.JOBIDFilter(3);

			// Verify "Verified" Stage
			VB.VerifyJob(3);

			driver.navigate().refresh();
			msg.append("Verify Bill For MTX:-Passed" + "\n");
			System.out.println("Verify Bill For MTX is passed");
			logs.info("Verify Bill For MTX is passed");
			Thread.sleep(4000);
		}

		catch (Exception e) {
			logs.error(e);
			msg.append("Verify Bill For MTX :- Failed" + "\n");
			System.out.println("Verify Bill For MTX is failed");
			logs.info("Verify Bill For MTX is failed");

		}

		logs.info("==== Verify Bill - MTX : End ==== " + "\n");
		msg.append("==== Verify Bill - MTX : End  ==== " + "\n");

	}

}
