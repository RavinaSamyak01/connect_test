package connect_OCBaseMethods;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class XerWaitForArrival extends OrderCreation {

	@Test
	public void xerWaitForArr() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		String svc = driver.findElement(By.id("lblServiceID")).getText();
		System.out.println(svc);

		try {

			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id=\"lblStages\"][contains(text(),'XER Wait For Arr@')]")));

			// --Get StageName
			OrderCreation OC = new OrderCreation();
			OC.getStageName();

			String stg = driver.findElement(By.id("lblStages")).getText();
			if (stg.contains("XER WAIT FOR ARR")) {
				// String tzone =
				// driver.findElement(By.id("lblEditArrivalTimeSZone")).getText();
				// String xerarrtime = getTime(tzone);

				WebElement ZoneID = isElementPresent("TLXWFATZoneID_id");
				String ZOneID = ZoneID.getText();
				System.out.println("ZoneID of is==" + ZOneID);

				// --get Time
				String ZnTime = getTimeAsTZone(ZOneID);

				WebElement Time = isElementPresent("TLXWFATime_id");
				Time.clear();
				Time.sendKeys(ZnTime);
				Time.sendKeys(Keys.TAB);
				logs.info("Enter Arrival Time");

				// --Click on Button
				WebElement Depart = isElementPresent("TLXWFAButton_id");
				wait.until(ExpectedConditions.elementToBeClickable(Depart));
				jse.executeScript("arguments[0].click();", Depart);
				logs.info("Click on Arrival button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			}
		} catch (Exception e) {
			logs.error(e);
			getScreenshot(driver, "XERWaitforArr" + svc);
			System.out.println("XER Wait for Arrival Not Exist in Flow!!");
			logs.info("XER Wait for Arrival Not Exist in Flow!!");

		}

	}

}
