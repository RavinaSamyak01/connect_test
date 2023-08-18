package connect_OCBaseMethods;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class CSRAcknowledge extends BaseInit {

	@Test
	public void csrAcknowledge() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));//
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		String svc = driver.findElement(By.id("lblServiceID")).getText();
		System.out.println(svc);

		try {

			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id=\"lblStages\"][contains(text(),'CSR Acknowledge')]")));

			// --Get StageName
			OrderCreation OC = new OrderCreation();
			OC.getStageName();

			if (svc.equals("LOC") || svc.equals("P3P")) {
				// --Click on Update button
				WebElement update = isElementPresent("TLAcknBTN_id");
				wait.until(ExpectedConditions.elementToBeClickable(update));
				jse.executeScript("arguments[0].click();", update);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			}

			if (svc.equals("SD") || svc.equals("PA")) {
				// --Click on Update button
				WebElement update = isElementPresent("TLAckBTn2_id");
				wait.until(ExpectedConditions.elementToBeClickable(update));
				jse.executeScript("arguments[0].click();", update);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			}
		} catch (Exception e) {
			logs.error(e);
			getScreenshot(driver, "CSRACKNOWLEDGE" + svc);
			System.out.println("CSR ACKNOWLEDGE Not Exist in Flow!!");
			logs.info("CSR ACKNOWLEDGE Not Exist in Flow!!");

		}

	}
}
