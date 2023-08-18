package connect_Ecourier;

import java.util.concurrent.TimeUnit;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.google.common.base.CharMatcher;

import connect_OCBaseMethods.OrderCreation;

public class PickupEcourier extends OrderCreation {

	public static String ActualTimeZone, AreadyDate;
	public static String ExpectedTime, AReadytime, UtilityTime;

	@Test
	public void confirmPickup() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));// wait time
		Actions act = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// --Get the ServiceID
		String svc = isElementPresent("TLServID_id").getText();
		System.out.println(svc);
		logs.info("ServiceID=" + svc);

		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@id=\"lblStages\"][contains(text(),'Pickup')]")));

			// --Get StageName
			OrderCreationE OC = new OrderCreationE();
			OC.getStageName();

			Thread.sleep(8000);

			// Open Memo
			WebElement Memo = isElementPresent("Memo_xpath");
			wait.until(ExpectedConditions.elementToBeClickable(Memo));
			jse.executeScript("arguments[0].click();", Memo);
			logs.info("Clicked on To Open Memo");

			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("memonote_0")));
			}

			catch (Exception extrawait) {

				// Audit history
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("spnAuditHistory")));
				WebElement AuditHistory = isElementPresent("AuditHistory_xpath");
				wait.until(ExpectedConditions.elementToBeClickable(AuditHistory));
				act.moveToElement(AuditHistory).build().perform();
				jse.executeScript("arguments[0].click();", AuditHistory);
				logs.info("Clicked on Audit Histroy");
				Thread.sleep(2500);

//				//click again on memo
				WebElement Memo1 = isElementPresent("Memo1_xpath");
				wait.until(ExpectedConditions.elementToBeClickable(Memo1));
				act.moveToElement(Memo1).build().perform();
				jse.executeScript("arguments[0].click();", Memo1);
				logs.info("Clicked on Memo1");

				WebDriverWait Extrawait = new WebDriverWait(driver,Duration.ofSeconds(120));// wait time
				logs.info("Wait for some time");
				Extrawait.until(ExpectedConditions.visibilityOfElementLocated(By.id("memonote_0")));
			}

			getScreenshot(driver, "EcourierID");
			logs.info("Screen Shot of Ecourier Order Tracking Number taken");

			Thread.sleep(6000);

			// Get Memo Text
			OC.getEcourierID();

			// Close memo pop up
			WebElement Close = isElementPresent("MemoClose_xpath");
			wait.until(ExpectedConditions.elementToBeClickable(Close));
			jse.executeScript("arguments[0].click();", Close);
			logs.info("Clicked on close Memo");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='GroundFlow']")));
			// Edit job
			WebElement EditJob = driver.findElement(By.xpath("//div[@id='idEditOrder']"));
			wait.until(ExpectedConditions.visibilityOf(EditJob));
			wait.until(ExpectedConditions.elementToBeClickable(EditJob));
			act.moveToElement(EditJob).build().perform();
			jse.executeScript("arguments[0].click();", EditJob);
			logs.info("Clicked on Edit Job");

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
					By.xpath("(//div[@class='row-eq-height form-group margin-b-5'])[1]")));

			// Ready Time
			WebElement Radytimezone = isElementPresent("ReadyTimeZone_xpath");
			wait.until(ExpectedConditions.visibilityOf(Radytimezone));
			String ActualTimeZone = Radytimezone.getText();
			this.ActualTimeZone = ActualTimeZone;
			System.out.println("Ready Time Zone:-" + ActualTimeZone);
			logs.info("Actual Time zone fetched");

			// Ready Time
			WebElement Readytime = driver.findElement(By.xpath("//*[@id=\"txtEditReadyforPickupTime\"]"));
			Thread.sleep(3500);
			String AReadytime = Readytime.getAttribute("value");
			System.out.println("Actual Ready Time:-" + AReadytime);
			logs.info("Actual Ready time extracted");

			// Ready Date

			WebElement Readydate = driver.findElement(By.id("txtEditReadyforPickupDate"));
			Thread.sleep(2000);
			String AreadyDate = Readydate.getAttribute("value");
			this.AreadyDate = AreadyDate;
			System.out.println("Pick Up date:-" + AreadyDate);
			logs.info("Pick Up date extracted");

		} catch (Exception e) {
			logs.error(e);
			getScreenshot(driver, "PICKUP" + svc);
			System.out.println("PICKUP Not Exist in Flow!!");
			logs.info("PICKUP Not Exist in Flow!!");

		}

	}

	public void ClickonJobStatus() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		Actions act = new Actions(driver);
		OrderCreationE OCE = new OrderCreationE();

		// Exit without save
		WebElement ExitWS = driver.findElement(By.xpath("//button[@id='btnexit']"));
		wait.until(ExpectedConditions.visibilityOf(ExitWS));
		wait.until(ExpectedConditions.elementToBeClickable(ExitWS));
		act.moveToElement(ExitWS).build().perform();
		jse.executeScript("arguments[0].click();", ExitWS);
		logs.info("Clicked on Exit without Save");
		Thread.sleep(2500);

		// Search Pickup Id
		OCE.SearchPICKUPID();

		// --Get StageName
		OCE.getStageName();

		try {
			// Send PU alert
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Send Pu Alert']")));
			WebElement SendPUAlert = driver.findElement(By.xpath("//label[text()='Send Pu Alert']"));
			wait.until(ExpectedConditions.visibilityOf(SendPUAlert));
			String Text = SendPUAlert.getText();
			logs.info("Verified Send PU Alert:-" + Text);

			getScreenshot(driver, "DeclineAlertBy34775");

			// Verify courier declined
			WebElement DeclineMsg = driver.findElement(By.xpath("//label[@id='lblDeclinedMsg']"));
			wait.until(ExpectedConditions.visibilityOf(DeclineMsg));
			String declinetxt = DeclineMsg.getText();
			logs.info("Verified Decline Alert:-" + declinetxt);
			logs.info("Job is rejected From Test Utility=PASS");
			msg.append("Job is rejected From Test Utility=PASS" + "\n");
		}

		catch (Exception Ex) {
			logs.info("Job is not rejected From Test Utility=FAIL");
			msg.append("Job is not rejected From Test Utility=FAIL" + "\n");

		}

	}

}
