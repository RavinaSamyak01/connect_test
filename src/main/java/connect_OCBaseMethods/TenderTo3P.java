package connect_OCBaseMethods;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TenderTo3P extends OrderCreation {

	@Test
	public void tndrTo3P() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		// Actions act = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;// scroll,click

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		String svc = driver.findElement(By.id("lblServiceID")).getText();
		System.out.println(svc);

		try {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@id=\"lblStages\"][contains(text(),'Tender to 3P')]")));

			} catch (Exception e) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@id=\"lblStages\"][contains(text(),'3rd Party Delivery')]")));

			}

			// --Get StageName
			OrderCreation OC = new OrderCreation();
			String stage = OC.getStageName();

			// --Get the timeZone
			String tzone = isElementPresent("TT3TimeZone_id").getText();
			String rectime = getTimeAsTZone(tzone);

			// --Enter Drop Time
			WebElement DropTime = isElementPresent("TT3droptime_id");
			wait.until(ExpectedConditions.elementToBeClickable(DropTime));
			DropTime.clear();
			DropTime.sendKeys(rectime);
			DropTime.sendKeys(Keys.TAB);
			logs.info("Entered Drop time");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			if (svc.equalsIgnoreCase("3PLAST") && stage.equalsIgnoreCase("3rd Party Delivery")) {

				try {
					WebElement POD = isElementPresent("TT3POD_id");

					if (POD.getAttribute("value").isBlank()) {
						String TrackID = OC.shipLabel();
						// --Enter POD
						POD.clear();
						POD.sendKeys(TrackID);
						POD.sendKeys(Keys.TAB);
						logs.info("Entered POD");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					} else {
						logs.info("POD is already set");

					}

				} catch (Exception e) {

					WebElement POD = isElementPresent("TT3POD2_id");
					if (POD.getAttribute("value").isBlank()) {
						String TrackID = OC.shipLabel();
						// --Enter POD
						POD.clear();
						POD.sendKeys(TrackID);
						POD.sendKeys(Keys.TAB);
						logs.info("Entered POD");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					} else {
						logs.info("POD is already set");

					}

				}

			} else if (svc.equalsIgnoreCase("P3P") && stage.equalsIgnoreCase("Tender to 3P")) {

				try {
					WebElement POD = isElementPresent("TT3POD_id");

					if (POD.getAttribute("value").isBlank()) {
						String TrackID = OC.shipLabel();
						// --Enter POD
						POD.clear();
						POD.sendKeys(TrackID);
						POD.sendKeys(Keys.TAB);
						logs.info("Entered POD");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					} else {
						logs.info("POD is already set");

					}

				} catch (Exception e) {

					WebElement POD = isElementPresent("TT3POD2_id");
					if (POD.getAttribute("value").isBlank()) {
						String TrackID = OC.shipLabel();
						// --Enter POD
						POD.clear();
						POD.sendKeys(TrackID);
						POD.sendKeys(Keys.TAB);
						logs.info("Entered POD");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					} else {
						logs.info("POD is already set");

					}

				}

			}

			// --Get the timeZone
			tzone = isElementPresent("TT3TimeZone_id").getText();
			rectime = getTimeAsTZone(tzone);

			// --Enter Drop Time
			DropTime = isElementPresent("TT3droptime_id");
			wait.until(ExpectedConditions.elementToBeClickable(DropTime));
			DropTime.clear();
			DropTime.sendKeys(rectime);
			DropTime.sendKeys(Keys.TAB);
			logs.info("Entered Drop time");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Click on Tender To 3P
			WebElement Ten3P = isElementPresent("TT3Button_id");
			wait.until(ExpectedConditions.elementToBeClickable(Ten3P));
			js.executeScript("arguments[0].click();", Ten3P);
			logs.info("Clicked on Tender To 3P button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			try {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id=\"idValidationforMain\"]//ul[@id=\"errorid\"]")));
				String Valmsg = isElementPresent("OCValOnePack_xpath").getText();
				logs.info("Validation message is displayed=" + Valmsg);
				if (Valmsg.contains("3P Deliver Date Time must be greater than Tender To 3P Date Time.")) {

					// --Get the timeZone
					tzone = isElementPresent("TT3TimeZone_id").getText();
					rectime = getTimeAsTZone(tzone);

					// --Enter Drop Time
					DropTime = isElementPresent("TT3droptime_id");
					wait.until(ExpectedConditions.elementToBeClickable(DropTime));
					DropTime.clear();
					DropTime.sendKeys(rectime);
					DropTime.sendKeys(Keys.TAB);
					logs.info("Entered Drop time");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Click on Tender To 3P
					Ten3P = isElementPresent("TT3Button_id");
					wait.until(ExpectedConditions.elementToBeClickable(Ten3P));
					js.executeScript("arguments[0].click();", Ten3P);
					logs.info("Clicked on Tender To 3P button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				}

			} catch (Exception evalidation) {

			}
		} catch (Exception e) {
			logs.error(e);
			getScreenshot(driver, "Tenderto3P" + svc);
			System.out.println("Tender to 3P Not Exist in Flow!!");
			logs.info("Tender to 3P Not Exist in Flow!!");

		}

	}

}
//--510087763
