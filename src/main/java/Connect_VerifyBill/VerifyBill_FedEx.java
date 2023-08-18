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

public class VerifyBill_FedEx extends BaseInit {

	@Test
	public void FedEx() throws IOException, Exception {

		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
		Actions act = new Actions(driver);

		try {
			logs.info("==== Verify Bill - FedEx : Start ==== " + "\n");
			msg.append("\n"+"==== Verify Bill - FedEx : Start  ==== " + "\n");

			// -- refresh page

			isElementPresent("RefreshLogo_xpath");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// Open Verify Bill
			VerifyBillBase VB = new VerifyBillBase();
			VB.OpenVerifyBill();

			// FedEx
			WebElement VBFedEx = isElementPresent("VB_FedEx_xpath");
			wait.until(ExpectedConditions.visibilityOf(VBFedEx));
			wait.until(ExpectedConditions.elementToBeClickable(VBFedEx));
			act.moveToElement(VBFedEx).click().build().perform();
			logs.info("Click on UNVERIFIED FedEx");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			msg.append("\n" + "Verify Bill: Tab Verification For FedEx" + "\n");

			// Check with Cutoff date filters
			VB.CutOffdateFilter(2);

			// Check with PickUp Id filter
			VB.pickUpIdFilter(2);

			// Check with Service filter
			VB.serviceFilter(2);

			// Check with Origin & Destination filter
			VB.locationFilter(2);

			// Check with account Filter
			VB.accountFilter(2);

			driver.navigate().refresh();
			msg.append("Verify Bill For FedEx:-Passed" + "\n");
			System.out.println("Verify Bill For FedEx is passed");
			logs.info("Verify Bill For FedEx is passed");
			Thread.sleep(4000);

		} catch (Exception e) {

			logs.error(e);
			msg.append("Verify Bill For FedEx :- Failed" + "\n");
			System.out.println("Verify Bill For FedEx is failed");
			logs.info("Verify Bill For FedEx is failed");

		}
		logs.info("==== Verify Bill - FedEx : End ==== " + "\n");
		msg.append("==== Verify Bill - FedEx : End  ==== " + "\n");

	}

}
