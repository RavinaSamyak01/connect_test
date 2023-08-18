package connect_OCBaseMethods;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import connect_BasePackage.BaseInit;

public class SendPull extends BaseInit {

	public void sendPull() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		Actions act = new Actions(driver);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		OrderCreation OC = new OrderCreation();

		// --Get the ServiceID
		String svc = isElementPresent("TLServID_id").getText();
		System.out.println(svc);
		logs.info("ServiceID=" + svc);

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id=\"lblStages\"][contains(text(),'Send Pull Alert')]")));

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

			// --Click on Send Pull button

			WebElement Sendpullalert = isElementPresent("TLRDSPUALert_id");
			act.moveToElement(Sendpullalert).build().perform();
			jse.executeScript("arguments[0].click();", Sendpullalert);
			logs.info("Clicked on Send Pull button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			try {
				// --Ship label pop up
				wait.until(
						ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"modal-dialog\"]")));
				logs.info("Ship Label dialogue is displayed");

				// --CLick on Proceed button
				WebElement ShipDialogue = isElementPresent("EOProceedBtn_xpath");
				act.moveToElement(ShipDialogue).build().perform();
				jse.executeScript("arguments[0].click();", ShipDialogue);
				logs.info("Clicked on Proceed button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			} catch (Exception e) {

			}

		} catch (Exception e) {
			logs.error(e);

			getScreenshot(driver, "SendPull" + svc);
			System.out.println("Send Pull Not Exist in Flow!!");
			logs.info("Send Pull Not Exist in Flow!!");

		}

	}

}
