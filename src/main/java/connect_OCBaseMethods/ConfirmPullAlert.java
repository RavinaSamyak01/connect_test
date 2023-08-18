package connect_OCBaseMethods;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import connect_BasePackage.BaseInit;

public class ConfirmPullAlert extends BaseInit {

	public void ConfirmPullAlertstage() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		Actions act = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// --Get the ServiceID
		String svc = isElementPresent("TLServID_id").getText();
		System.out.println(svc);
		logs.info("ServiceID=" + svc);

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id=\"lblStages\"][contains(text(),'CONFIRM PULL ALERT')]")));

			OrderCreation OC = new OrderCreation();
			OC.getStageName();
			// Click on Confirm Pull ALert
			WebElement ConfPullAlert = isElementPresent("TLRDSPUALert_id");
			wait.until(ExpectedConditions.elementToBeClickable(ConfPullAlert));
			act.moveToElement(ConfPullAlert).build().perform();
			js.executeScript("arguments[0].click();", ConfPullAlert);
			logs.info("Click on Confirm Pull Alert  button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		} catch (Exception e) {
			logs.error(e);
			getScreenshot(driver, "ConfirmPullAlert" + svc);
			System.out.println("Confirm Pull ALert Not Exist in Flow!!");
			logs.info("Confirm Pull ALert Not Exist in Flow!!");
		}

	}
}
