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

public class ReadyForDispatch extends BaseInit {

	@Test
	public void pickupAlert() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		Actions act = new Actions(driver);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		OrderCreation OC = new OrderCreation();
		String svc = OC.getServiceID();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id=\"lblStages\"][contains(text(),'Ready For Dispatch')]")));

			// --Get StageName
			OC.getStageName();

			// --Check Contacted
			if (isElementPresent("TLRDContacted_id").isDisplayed()) {
				WebElement email = isElementPresent("TLRDContacted_id");
				wait.until(ExpectedConditions.elementToBeClickable(email));
				jse.executeScript("arguments[0].click();", email);
				Select CBy = new Select(email);
				CBy.selectByValue("number:377");
				System.out.println("email selected");
				logs.info("Email is selected as a Contact By");
			} else {
				Select Contacttype = new Select(isElementPresent("TLRDContacted_id"));
				Contacttype.selectByVisibleText("Email");
				logs.info("Email is selected as a Contact By");

			}

			// --Enter ContactBy Value
			WebElement emailValue = isElementPresent("TLRDContValue_id");
			wait.until(ExpectedConditions.elementToBeClickable(emailValue));
			emailValue.clear();
			emailValue.sendKeys("Ravina.prajapati@samyak.com");
			logs.info("Entered EmailID");

			// --Spoke With
			WebElement spoke = isElementPresent("TLRDSpokeW_id");
			wait.until(ExpectedConditions.elementToBeClickable(spoke));
			spoke.clear();
			spoke.sendKeys("Ravina");
			logs.info("Entered Spoke With");

			// --Click on Alert and Confirm
			try {
				WebElement Sendpualert = isElementPresent("TLRDAlConfrm_id");
				act.moveToElement(Sendpualert).build().perform();
				jse.executeScript("arguments[0].click();", Sendpualert);
				logs.info("Clicked on Alert&Confirm button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			} catch (Exception e) {
				WebElement Sendpualert = isElementPresent("TLRDAlConfrmBtn_id");
				act.moveToElement(Sendpualert).build().perform();
				jse.executeScript("arguments[0].click();", Sendpualert);
				logs.info("Clicked on Alert&Confirm button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			}

		} catch (Exception e) {
			logs.error(e);

			getScreenshot(driver, "READYFORDISPATCH" + svc);
			System.out.println("READY FOR DISPATCH Not Exist in Flow!!");
			logs.info("READY FOR DISPATCH Not Exist in Flow!!");

		}

	}

}
