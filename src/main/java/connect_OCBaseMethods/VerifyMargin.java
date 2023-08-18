package connect_OCBaseMethods;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class VerifyMargin extends BaseInit {
	@Test
	public void verifyMargin(int i) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));//

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// --Search the Job
		OrderCreation OC = new OrderCreation();
		OC.searchJob(i);

		String svc = driver.findElement(By.id("lblServiceID")).getText();
		System.out.println(svc);

		try {

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@id=\"lblStages\"][contains(text(),'Verify Margin')]")));

			// --Get StageName
			OC.getStageName();

		} catch (Exception e) {
			logs.error(e);
			getScreenshot(driver, "VerifyMarginIssue" + svc);
			System.out.println("Verify Margin Not Exist in Flow!!");
			logs.info("Verify Margin Not Exist in Flow!!");

		}

	}
}
