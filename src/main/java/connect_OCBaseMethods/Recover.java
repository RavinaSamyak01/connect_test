package connect_OCBaseMethods;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Recover extends OrderCreation {

	@Test
	public void recoverAtDestination() throws Exception {
		// JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		String svc = driver.findElement(By.id("lblServiceID")).getText();
		System.out.println(svc);
	
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id=\"lblStages\"][contains(text(),'Recover @ Destination')]")));

			// --Get StageName
			OrderCreation OC = new OrderCreation();
			OC.getStageName();

			// --Actual Recover Time

			WebElement ZoneID = isElementPresent("TLRADTimeZone_id");
			String ZOneID = ZoneID.getText();
			System.out.println("ZoneID of is==" + ZOneID);

			// --get Time
			String ZnTime = getTimeAsTZone(ZOneID);

			WebElement Time = isElementPresent("TLRADRecTime_id");
			Time.clear();
			Time.sendKeys(ZnTime);
			Time.sendKeys(Keys.TAB);
			logs.info("Enter Arrival Time");

			// --Recover Date
			isElementPresent("TLRADRecDate_id").sendKeys(Keys.TAB);

			// --Click on COnfirm Recover
			WebElement ConfRecover = isElementPresent("TLRADConfRecover_id");
			wait.until(ExpectedConditions.elementToBeClickable(ConfRecover));
			ConfRecover.click();
			logs.info("Click on Confirm Recover button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		} catch (Exception e) {
			logs.error(e);
			getScreenshot(driver, "RECOVER@DEST" + svc);
			System.out.println("RECOVER @ DESTINATION Not Exist in Flow!!");
			logs.info("RECOVER @ DESTINATION Not Exist in Flow!!");

		}

	}

}
