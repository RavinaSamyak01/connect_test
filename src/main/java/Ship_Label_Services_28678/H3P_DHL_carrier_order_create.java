package Ship_Label_Services_28678;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class H3P_DHL_carrier_order_create extends BaseInit {
	method_28678 mth = new method_28678();
	//@Test
	public void h3P() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		// Actions act = new Actions(driver);

		// --Order Creation
		

	//	mth.searchJob(10);

		mth.orderCreation(14);

		// --Get ServiceID
		//String ServiceID = mth.getServiceID();

	

		// --Error Pop Up
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@class=\"ngdialog-content ui-draggable\"]")));
			getScreenshot(driver, "ErrorPopUp_" + "error_h3p");
			WebElement ErrorPUp = isElementPresent("EOErrorPUp_id");
			wait.until(ExpectedConditions.elementToBeClickable(ErrorPUp));
			jse.executeScript("arguments[0].click();", ErrorPUp);
			logs.info("Clicked on OK button of Error Message");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		} catch (

		Exception eError) {
			System.out.println("error pop up is not displayed");
		}

		// --Moved to Job Status
		WebElement idJob = isElementPresent("TLJobStatusTab_id");
		wait.until(ExpectedConditions.elementToBeClickable(idJob));
		jse.executeScript("arguments[0].click();", idJob);
		logs.info("Clicked on Job Status Tab");
		wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		

		// --Refresh App
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		mth.refreshApp();

		msg.append("\n\n\n");
	}

}
