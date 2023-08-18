package connect_Ecourier;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;
import connect_Ecourier.OrderCreationE;



public class ReadyForDispatchE extends BaseInit {

	
	
	@Test
	public void pickupAlert() throws Exception {

		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		Actions act = new Actions(driver);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		OrderCreationE OC = new OrderCreationE();
		
		String svc = OC.getServiceID();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id=\"lblStages\"][contains(text(),'Ready For Dispatch')]")));

			// --Get StageName
			OC.getStageName();
			
			//Send Alert to courier 
			//Edit option 
			WebElement Edit = isElementPresent("EditCourier_xpath");
			wait.until(ExpectedConditions.elementToBeClickable(Edit));
			jse.executeScript("arguments[0].click();", Edit);
			logs.info("Clicked on Edit Courier Option");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("panel-body")));
			
			//Search for Courier 
			//Enter Courier number in Search text Box
			WebElement CourierTextBox = isElementPresent("CourierIDTextBox_xpath");
			wait.until(ExpectedConditions.visibilityOf(CourierTextBox));
			CourierTextBox.clear();
			CourierTextBox.sendKeys("34775");
			logs.info("Entered 34775 Courier Number");
			
			//Search Button
			WebElement Search = isElementPresent("SearchCourier_xpath");
			wait.until(ExpectedConditions.elementToBeClickable(Search));
			jse.executeScript("arguments[0].click();", Search);
			logs.info("Clicked on Search Button");
			
           //Heavy weight
			WebElement HeavyWeight = isElementPresent("HeavyWeight_xpath");
			wait.until(ExpectedConditions.elementToBeClickable(HeavyWeight));
			jse.executeScript("arguments[0].click();", HeavyWeight);
			logs.info("Clicked on Heavy Weight Radio Button");
			Thread.sleep(2500);
			//Search Button
			WebElement Search1 = isElementPresent("SearchCourier_xpath");
			wait.until(ExpectedConditions.visibilityOf(Search1));
			wait.until(ExpectedConditions.elementToBeClickable(Search1));
			jse.executeScript("arguments[0].click();", Search1);
			logs.info("Clicked on Search Button");
			
			
			
			//Select Courier id 
			WebElement CourierID= isElementPresent("CourierID_xpath");
			jse.executeScript("arguments[0].scrollIntoView(true);", CourierID);
			wait.until(ExpectedConditions.elementToBeClickable(CourierID));
			jse.executeScript("arguments[0].click();", CourierID);
			logs.info("Selected Courier ID 34775 ");
			Thread.sleep(5000);


			// --Spoke With
			WebElement spoke = isElementPresent("TLRDSpokeW_id");
			wait.until(ExpectedConditions.elementToBeClickable(spoke));
			spoke.clear();
			spoke.sendKeys("Ravina");
			logs.info("Entered Spoke With");

 
			//Send PU alert 
			WebElement SendPUAlert= isElementPresent("SendPUAlert_id");
			wait.until(ExpectedConditions.elementToBeClickable(SendPUAlert));
			jse.executeScript("arguments[0].click();", SendPUAlert);
			logs.info("Clicked On SnedPU alert");
			Thread.sleep(8000);
			
			//Search Pick Up ID
		OC.SearchPICKUPID();
			
			
			
			
			

		} catch (Exception e) {
			logs.error(e);

			getScreenshot(driver, "READYFORDISPATCH" + svc);
			System.out.println("READY FOR DISPATCH Not Exist in Flow!!");
			logs.info("READY FOR DISPATCH Not Exist in Flow!!");

		}

	}

}
