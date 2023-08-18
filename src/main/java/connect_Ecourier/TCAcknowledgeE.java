package connect_Ecourier;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class TCAcknowledgeE extends BaseInit {

	@Test
	public void tcAcknowledge() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		Actions act = new Actions(driver);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		// --Get the ServiceID
		String svc = isElementPresent("TLServID_id").getText();
		System.out.println(svc);
		logs.info("ServiceID=" + svc);
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@id=\"lblStages\"][contains(text(),'Acknowledge')]")));

			// --Get StageName
			OrderCreationE OC = new OrderCreationE();
			OC.getStageName();

			// --Click on TC Ack button
			if (svc.equals("LOC") || svc.equals("P3P") || svc.equals("DRV") || svc.equals("SDC") || svc.equals("FRG")
					|| svc.equals("H3P") || svc.equals("CPU") || svc.equals("D3P") || svc.equals("3PLAST")) {
				WebElement TCAckBtn = isElementPresent("TLAcknBTN_id");
				wait.until(ExpectedConditions.elementToBeClickable(TCAckBtn));
				jse.executeScript("arguments[0].click();", TCAckBtn);
				logs.info("Clicked on TC Acknowledge button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				Thread.sleep(4000);

			
			}

			if (svc.equals("SD") || svc.equals("PA") || svc.equals("AIR") || svc.equals("FRA")) {
				WebElement TCAckBtn = isElementPresent("TLAckBTn2_id");
				wait.until(ExpectedConditions.elementToBeClickable(TCAckBtn));
				jse.executeScript("arguments[0].click();", TCAckBtn);
				logs.info("Clicked on TC Acknowledge button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			}
		} catch (Exception e) {
			logs.error(e);
			getScreenshot(driver, "TCACKNOWLEDGE" + svc);
			System.out.println("TC ACKNOWLEDGE Not Exist in Flow!!");
			logs.info("TC ACKNOWLEDGE Not Exist in Flow!!");

		}

	}
}
