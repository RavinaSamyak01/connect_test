package connect_OCBaseMethods;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class SendDelAlert extends BaseInit {
	@Test
	public void delAlert() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		Actions act = new Actions(driver);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		String svc = driver.findElement(By.id("lblServiceID")).getText();
		System.out.println(svc);

		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@id=\"lblStages\"][contains(text(),'Send Del Alert')]")));

			// --Get StageName
			OrderCreation OC = new OrderCreation();
			OC.getStageName();

			// --Contacted
			Select Contacttype = new Select(isElementPresent("TLRDContacted_id"));
			Contacttype.selectByVisibleText("Email");
			logs.info("Selected Email option as Contacted");
			Thread.sleep(1000);

			// --Contatcted Value
			isElementPresent("TLRDContValue_id").clear();
			isElementPresent("TLRDContValue_id").sendKeys("Ravina.prajapati@samyak.com");
			logs.info("entered Contacted By");

			// --Spoke with
			isElementPresent("TLRDSpokeW_id").clear();
			isElementPresent("TLRDSpokeW_id").sendKeys("Ravina");
			logs.info("entered Spoke With");
			Thread.sleep(1000);

			// --Driver
			Select Driver = new Select(isElementPresent("PDNameDrp_id"));
			Driver.selectByIndex(1);
			logs.info("Selected Driver");
			Thread.sleep(1000);

			// --Alert&Confirm button
			try {

				WebElement AlConfirm = isElementPresent("TLRDAlConfrmBtn_id");
				wait.until(ExpectedConditions.elementToBeClickable(AlConfirm));
				jse.executeScript("arguments[0].click();", AlConfirm);
				logs.info("CLicked on Alert and Confirm Button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			} catch (Exception ee) {
				WebElement AlConfirm = isElementPresent("TLRDAlConfrm_id");
				act.moveToElement(AlConfirm).build().perform();
				wait.until(ExpectedConditions.elementToBeClickable(AlConfirm));
				jse.executeScript("arguments[0].click();", AlConfirm);
				logs.info("CLicked on Alert and Confirm Button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			}
		} catch (Exception e) {
			logs.error(e);
			getScreenshot(driver, "SENDDELALERT" + svc);
			System.out.println("SEND DEL ALERT Not Exist in Flow!!");
			logs.info("SEND DEL ALERT Not Exist in Flow!!");

		}
	}
}
