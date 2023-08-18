package connect_OCBaseMethods;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class WaitForDeptarture extends OrderCreation {

	@Test
	public void waitForDept() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));//
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		String svc = driver.findElement(By.id("lblServiceID")).getText();
		System.out.println(svc);

		try {

			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id=\"lblStages\"][contains(text(),'Wait for Departure')]")));

			// --Get StageName
			OrderCreation OC = new OrderCreation();
			OC.getStageName();

			// --Departure Time
			isElementPresent("TLWFDTime_id").clear();
			isElementPresent("TLWFDTime_id").sendKeys(rdytime);
			isElementPresent("TLWFDTime_id").sendKeys(Keys.TAB);
			logs.info("Enter Departure Time");

			// --Click on Depart button
			WebElement Depart = isElementPresent("TLWFDDepart_id");
			wait.until(ExpectedConditions.elementToBeClickable(Depart));
			jse.executeScript("arguments[0].click();", Depart);
			logs.info("Click on Depart button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		} catch (Exception e) {
			logs.error(e);
			getScreenshot(driver, "WaitforDep" + svc);
			System.out.println("Wait for Departure Not Exist in Flow!!");
			logs.info("Wait for Departure Not Exist in Flow!!");

		}

	}
}
