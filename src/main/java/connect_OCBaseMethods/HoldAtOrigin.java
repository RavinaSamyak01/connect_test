package connect_OCBaseMethods;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class HoldAtOrigin extends BaseInit {

	@Test
	public void hldAtOrigin() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		String svc = driver.findElement(By.id("lblServiceID")).getText();
		System.out.println(svc);
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@id=\"lblStages\"][contains(text(),'Hold @ Origin')]")));

			// --Get StageName
			OrderCreation OC = new OrderCreation();
			OC.getStageName();

			WebElement HAOrigin = isElementPresent("TLHAOBtn_id");
			jse.executeScript("arguments[0].click();", HAOrigin);
			logs.info("Click on Hold@Origin button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		} catch (Exception e) {
			logs.error(e);
			getScreenshot(driver, "Hold@Origin" + svc);
			System.out.println("Hold @ Origin Not Exist in Flow!!");
			logs.info("Hold @ Origin Not Exist in Flow!!");

		}

	}
}