package connect_OCBaseMethods;

import java.util.List;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import connect_BasePackage.BaseInit;

public class ConfirmPUAlert extends BaseInit {

	public void confirmPUAlert() throws Exception {

		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		Actions act = new Actions(driver);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// --Get the ServiceID
		String svc = isElementPresent("TLServID_id").getText();
		System.out.println(svc);
		logs.info("ServiceID=" + svc);

		try {
			try {
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[@id=\"lblStages\"][contains(text(),'Confirm Pu Alert')]")));

				} catch (Exception eStageName) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[@id=\"lblStages\"][contains(text(),'CONFIRM PULL ALERT')]")));

				}
				// --Get StageName
				OrderCreation OC = new OrderCreation();
				OC.getStageName();

				try {
					// --Check Contacted
					if (isElementPresent("D3PContactedBy_id").isDisplayed()) {
						WebElement email = isElementPresent("D3PContactedBy_id");
						wait.until(ExpectedConditions.elementToBeClickable(email));
						jse.executeScript("arguments[0].click();", email);
						Select CBy = new Select(email);
						CBy.selectByValue("number:377");
						System.out.println("email selected");
						logs.info("Email is selected as a Contact By");
					} else {
						try {
							Select Contacttype = new Select(isElementPresent("D3PContactedBy_id"));
							Contacttype.selectByVisibleText("Email");
							logs.info("Email is selected as a Contact By");
						} catch (Exception eContact) {

							List<WebElement> ContactedBy = driver.findElements(By.xpath("//*[@id=\"cmbContacted\"]"));
							int values = ContactedBy.size();
							int ActualContactedBy = values - 1;

							WebElement Contacted = ContactedBy.get(ActualContactedBy);
							wait.until(ExpectedConditions.elementToBeClickable(Contacted));
							jse.executeScript("arguments[0].click();", Contacted);
							Select CBy = new Select(Contacted);
							CBy.selectByValue("number:377");
							System.out.println("email selected");
							logs.info("Email is selected as a Contact By");
						}

					}
				} catch (Exception ee) {
					System.out.println("issue in selection of contacted By");
					logs.info("issue in selection of contacted By");
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

				// --Click on Confirm PU Alert
				try {
					WebElement ConfPUAlert = isElementPresent("TLRDSPUALert_id");
					act.moveToElement(ConfPUAlert).build().perform();
					jse.executeScript("arguments[0].click();", ConfPUAlert);
					logs.info("Clicked on Confirm PU Alert button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				} catch (Exception e) {
					try {
						WebElement ConfPUAlert = isElementPresent("TLRDSPUALert_id");
						act.moveToElement(ConfPUAlert).build().perform();
						jse.executeScript("arguments[0].click();", ConfPUAlert);
						logs.info("Clicked on Confirm PU Alert button");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					} catch (Exception ee) {
						WebElement ConfPUAlert = isElementPresent("D3PConfull_id");
						act.moveToElement(ConfPUAlert).build().perform();
						jse.executeScript("arguments[0].click();", ConfPUAlert);
						logs.info("Clicked on Confirm PU Alert button");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					}

				}
			} catch (Exception EE) {
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[@id=\"lblStages\"][contains(text(),'Confirm Pu Alert')]")));

				} catch (Exception eStageName) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[@id=\"lblStages\"][contains(text(),'CONFIRM PULL ALERT')]")));

				}
				// --Get StageName
				OrderCreation OC = new OrderCreation();
				OC.getStageName();

				try {
					// --Check Contacted
					if (isElementPresent("D3PContactedBy_id").isDisplayed()) {
						WebElement email = isElementPresent("D3PContactedBy_id");
						wait.until(ExpectedConditions.elementToBeClickable(email));
						jse.executeScript("arguments[0].click();", email);
						Select CBy = new Select(email);
						CBy.selectByValue("number:377");
						System.out.println("email selected");
						logs.info("Email is selected as a Contact By");
					} else {
						try {
							Select Contacttype = new Select(isElementPresent("D3PContactedBy_id"));
							Contacttype.selectByVisibleText("Email");
							logs.info("Email is selected as a Contact By");
						} catch (Exception eContact) {

							List<WebElement> ContactedBy = driver.findElements(By.xpath("//*[@id=\"cmbContacted\"]"));
							int values = ContactedBy.size();
							int ActualContactedBy = values - 1;

							WebElement Contacted = ContactedBy.get(ActualContactedBy);
							wait.until(ExpectedConditions.elementToBeClickable(Contacted));
							jse.executeScript("arguments[0].click();", Contacted);
							Select CBy = new Select(Contacted);
							CBy.selectByValue("number:377");
							System.out.println("email selected");
							logs.info("Email is selected as a Contact By");
						}

					}
				} catch (Exception ee) {
					System.out.println("issue in selection of contacted By");
					logs.info("issue in selection of contacted By");
				}

				// --Enter ContactBy Value
				WebElement emailValue = isElementPresent("D3PContValue_id");
				wait.until(ExpectedConditions.elementToBeClickable(emailValue));
				emailValue.clear();
				emailValue.sendKeys("Ravina.prajapati@samyak.com");
				logs.info("Entered EmailID");

				// --Spoke With
				WebElement spoke = isElementPresent("D3PSpeak_id");
				wait.until(ExpectedConditions.elementToBeClickable(spoke));
				spoke.clear();
				spoke.sendKeys("Ravina");
				logs.info("Entered Spoke With");

				// --Click on Confirm PU Alert
				try {
					WebElement ConfPUAlert = isElementPresent("TLRDSPUALert_id");
					act.moveToElement(ConfPUAlert).build().perform();
					jse.executeScript("arguments[0].click();", ConfPUAlert);
					logs.info("Clicked on Confirm PU Alert button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				} catch (Exception e) {
					try {
						WebElement ConfPUAlert = isElementPresent("TLRDSPUALert_id");
						act.moveToElement(ConfPUAlert).build().perform();
						jse.executeScript("arguments[0].click();", ConfPUAlert);
						logs.info("Clicked on Confirm PU Alert button");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					} catch (Exception ee) {
						WebElement ConfPUAlert = isElementPresent("D3PConfull_id");
						act.moveToElement(ConfPUAlert).build().perform();
						jse.executeScript("arguments[0].click();", ConfPUAlert);
						logs.info("Clicked on Confirm PULL Alert button");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					}

				}
			}

		} catch (Exception e) {
			logs.error(e);
			getScreenshot(driver, "ConfirmPUAlert" + svc);
			System.out.println("Confirm PU Alert Not Exist in Flow!!");
			logs.info("Confirm PU Alert Not Exist in Flow!!");
		}

	}

}
