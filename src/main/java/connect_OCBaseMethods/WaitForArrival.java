package connect_OCBaseMethods;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class WaitForArrival extends OrderCreation {

	@Test
	public void waitForArr() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		String svc = driver.findElement(By.id("lblServiceID")).getText();
		System.out.println(svc);

		try {

			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id=\"lblStages\"][contains(text(),'Wait for Arrival')]")));

			// --Get StageName
			OrderCreation OC = new OrderCreation();
			OC.getStageName();

			String stg = driver.findElement(By.id("lblStages")).getText();

			if (stg.contains("WAIT FOR ARRIVAL")) {

				// --Actual Recover Time

				WebElement ZoneID = isElementPresent("TLWFATimeZone_id");
				String ZOneID = ZoneID.getText();
				System.out.println("ZoneID of is==" + ZOneID);

				// --get Time
				String ZnTime = getTimeAsTZone(ZOneID);

				WebElement Time = isElementPresent("TLWFATime_id");
				Time.clear();
				Time.sendKeys(ZnTime);
				Time.sendKeys(Keys.TAB);
				logs.info("Enter Arrival Time");

				// --Click on Arrival button
				WebElement Arrival = isElementPresent("TLWFArrival_id");
				wait.until(ExpectedConditions.elementToBeClickable(Arrival));
				jse.executeScript("arguments[0].click();", Arrival);
				logs.info("Click on Arrival button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			}
		} catch (Exception e) {
			logs.error(e);
			getScreenshot(driver, "WaitforArrival" + svc);
			System.out.println("Wait for Arrival Not Exist in Flow!!");
			logs.info("Wait for Arrival Not Exist in Flow!!");
		}

	}

}
