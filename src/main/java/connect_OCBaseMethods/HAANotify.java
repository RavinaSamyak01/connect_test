package connect_OCBaseMethods;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class HAANotify extends BaseInit {

	@Test

	public void haaNtfy() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		Actions act = new Actions(driver);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		String svc = null;
		try {
			try {
				// --Click on Search All Job
				WebElement SearchAllJob = isElementPresent("TLSearchAlljob_id");
				wait.until(ExpectedConditions.visibilityOf(SearchAllJob));
				wait.until(ExpectedConditions.elementToBeClickable(SearchAllJob));
				act.moveToElement(SearchAllJob).build().perform();
				jse.executeScript("arguments[0].click();", SearchAllJob);
				logs.info("Clicked on SearchAllJob");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				for (int j = 1; j < 2; j++) {
					if (j == 1) // HAA for AIR
					{

						// --Get the PUID
						String pickupid = getData("Sheet1", 6, 32);
						logs.info("PUID==" + pickupid);

						// --Enter PU ID
						WebElement PUInput = isElementPresent("TLSAllJPickup_id");
						PUInput.clear();
						PUInput.sendKeys(pickupid);
						logs.info("Entered value in PickUp Input");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					}
				}

				// --Click on Search buttton
				WebElement SearchBtn = isElementPresent("TLSAllJSearchBtn_id");
				wait.until(ExpectedConditions.elementToBeClickable(SearchBtn));
				act.moveToElement(SearchBtn).build().perform();
				jse.executeScript("arguments[0].click();", SearchBtn);
				logs.info("Clicked on Search button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Select 1st Job
				WebElement Select1stJob = isElementPresent("TLSAllJob1stJob_id");
				wait.until(ExpectedConditions.elementToBeClickable(Select1stJob));
				act.moveToElement(Select1stJob).build().perform();
				jse.executeScript("arguments[0].click();", Select1stJob);
				logs.info("Clicked on 1st JobID");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			} catch (Exception e) {
				logs.info(e);
			}
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@id=\"lblStages\"][contains(text(),'HAA Notify')]")));

			svc=isElementPresent("TLServID_id").getText();
			System.out.println(svc);

			// --Get StageName
			OrderCreation OC = new OrderCreation();
			OC.getStageName();

			if (svc.equals("AIR")) {
				// --Move to Job Status Tab
				WebElement JoStatusTab = isElementPresent("TLJobStatusTab_id");
				wait.until(ExpectedConditions.elementToBeClickable(JoStatusTab));
				JoStatusTab.click();
				logs.info("Clicked on Job Status Tab");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			}

			// --Enter HAA Spoke with
			WebElement HAASpokeWith = isElementPresent("EOHAASpkWith_id");
			HAASpokeWith.clear();
			HAASpokeWith.sendKeys("Ravina Oza");
			HAASpokeWith.sendKeys(Keys.TAB);
			logs.info("Entered value in HAA Spoke With");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Click on Notify HAA button
			WebElement HAANotifyBtn = isElementPresent("EONotifyHAABtn_id");
			wait.until(ExpectedConditions.elementToBeClickable(HAANotifyBtn));
			HAANotifyBtn.click();
			logs.info("Clicked on HAA Notify button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		} catch (Exception e) {
			logs.error(e);
			getScreenshot(driver, "HAANotify" + svc);
			System.out.println("HAA Notify Not Exist in Flow!!");
			logs.info("HAA Notify Not Exist in Flow!!");

		}

	}
}
