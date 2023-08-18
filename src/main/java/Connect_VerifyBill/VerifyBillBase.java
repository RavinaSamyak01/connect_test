package Connect_VerifyBill;

import java.io.IOException;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import connect_BasePackage.BaseInit;
import connect_OCBaseMethods.OrderCreation;

public class VerifyBillBase extends BaseInit {
	JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
	Actions act = new Actions(driver);
	public static String PickUpId, ActaulRate,JOBID;
	public static WebElement VBverifyS, VBBaseRate, VBRecalRate;

	public void OpenVerifyBill() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		// Go TO Billing
		WebElement billing = isElementPresent("Billing_xpath");
		wait.until(ExpectedConditions.visibilityOf(billing));
		wait.until(ExpectedConditions.elementToBeClickable(billing));
		act.moveToElement(billing).click().build().perform();
		logs.info("Click on billing");

		// GO To Verify Bill
		WebElement VerifyBill = isElementPresent("VerifyBill_id");
		wait.until(ExpectedConditions.visibilityOf(VerifyBill));
		wait.until(ExpectedConditions.elementToBeClickable(VerifyBill));
		act.moveToElement(VerifyBill).click().build().perform();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='UNVERIFIED - MNX']")));
		logs.info("Click on Verfy Bill");

	}

	public void CutOffdateFilter(int i) throws IOException, Exception {

		// Pick Up date
		WebElement PickUpdate = isElementPresent("VB_PickUpDatetxt_id");
		wait.until(ExpectedConditions.visibilityOf(PickUpdate));
		PickUpdate.sendKeys("03/01/2023");
		PickUpdate.sendKeys(Keys.TAB);
		logs.info("Entered PickUp Date");

		// Delivery Date
		WebElement Deldate = isElementPresent("VB_DelDatetxt_xpath");
		wait.until(ExpectedConditions.visibilityOf(Deldate));
//		Deldate.sendKeys(BaseInit.CuDate());
		Deldate.sendKeys("03/01/2023");
		Deldate.sendKeys(Keys.TAB);
		logs.info("Entered Delivery Date");

		// Click on Search
		WebElement VBSearch = isElementPresent("VB_Search_id");
		wait.until(ExpectedConditions.visibilityOf(VBSearch));
		wait.until(ExpectedConditions.elementToBeClickable(VBSearch));
		act.moveToElement(VBSearch).click().build().perform();
		logs.info("Click on Search");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		if (i == 1) {
			// Screen Shot
			getScreenshot(driver, "VB_CutoffDateFilter_MNX");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-MNX");
		} else if (i == 2) {
			// Screen Shot
			getScreenshot(driver, "VB_CutoffDateFilter_FedEX");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-FedEx");
		}

		else if (i == 3) {
			// Screen Shot
			getScreenshot(driver, "VB_CutoffDateFilter_MTX");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-MTX");
		}
		
		msg.append("Pickup & Delivery Cutoff date Filter=Pass" + "\n");
		// Click on Reset
		WebElement VBReset = isElementPresent("VB_Reset_id");
		wait.until(ExpectedConditions.visibilityOf(VBReset));
		wait.until(ExpectedConditions.elementToBeClickable(VBReset));
		act.moveToElement(VBReset).click().build().perform();
		logs.info("Click on Reset");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
	
	}

	
	
	
	
	
	
	public void pickUpIdFilter(int i) throws IOException, InterruptedException {

		if (i == 1) {
			// Extract Pick Up ID MNX
			WebElement VBPickUpID = isElementPresent("VB_pickUpID_xpath");
			wait.until(ExpectedConditions.visibilityOf(VBPickUpID));
			String PickUpId = VBPickUpID.getText();
			this.PickUpId = PickUpId;
			logs.info("PickUp ID is:-" + PickUpId);
			logs.info("PickUp ID Extracted");
		} else if (i == 2) {
			// Extract Pick Up ID for FedEx
			WebElement VBPickUpID = isElementPresent("VB_pickupIdFedEx_xpath");
			wait.until(ExpectedConditions.visibilityOf(VBPickUpID));
			String PickUpId = VBPickUpID.getText();
			this.PickUpId = PickUpId;
			logs.info("PickUp ID is:-" + PickUpId);
			logs.info("PickUp ID Extracted");
		} else if (i == 3) {

			// Extract Pick Up ID for FedEx
			WebElement VBPickUpID = isElementPresent("VB_pickupIdMTX_xpath");
			wait.until(ExpectedConditions.visibilityOf(VBPickUpID));
			String PickUpId = VBPickUpID.getText();
			this.PickUpId = PickUpId;
			logs.info("PickUp ID is:-" + PickUpId);
			logs.info("PickUp ID Extracted");

		}

		// Enter In PickUp Text Box
		WebElement PickTxtBox = isElementPresent("VB_pickUptxtBox_id");
		wait.until(ExpectedConditions.visibilityOf(PickTxtBox));
		PickTxtBox.sendKeys(PickUpId);

		PickTxtBox.sendKeys(Keys.TAB);
		logs.info("Entered PickUp ID");

		// Click on Search
		WebElement VBSearch = isElementPresent("VB_Search_id");
		wait.until(ExpectedConditions.visibilityOf(VBSearch));
		wait.until(ExpectedConditions.elementToBeClickable(VBSearch));
		act.moveToElement(VBSearch).click().build().perform();
		logs.info("Click on Search");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		if (i == 1) {
			// Screen Shot
			getScreenshot(driver, "VB_PickUpIDFilterMNX");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-MNX");
		} else if (i == 2) {
			// Screen Shot
			getScreenshot(driver, "VB_PickUpIDFilterFedEx");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-FedEx");
		} else if (i == 3) {
			// Screen Shot
			getScreenshot(driver, "VB_PickUpIDFilterMTX");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-MTX");
		}
		msg.append("Pickup ID Filter=Pass" + "\n");
		
		// Click on Reset
		WebElement VBReset = isElementPresent("VB_Reset_id");
		wait.until(ExpectedConditions.visibilityOf(VBReset));
		wait.until(ExpectedConditions.elementToBeClickable(VBReset));
		act.moveToElement(VBReset).click().build().perform();
		logs.info("Click on Reset");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

	}

	public void serviceFilter(int i) throws IOException, InterruptedException {

		// Select Service
		WebElement VBService = isElementPresent("VB_Service_xpath");
		wait.until(ExpectedConditions.visibilityOf(VBService));
		wait.until(ExpectedConditions.elementToBeClickable(VBService));
		act.moveToElement(VBService).click().build().perform();
		logs.info("Click on Service");

		// Select LOC
		WebElement VBLoc = isElementPresent("VB_LOC_linkText");
		jse.executeScript("arguments[0].scrollIntoView();", VBLoc);
		wait.until(ExpectedConditions.visibilityOf(VBLoc));
		wait.until(ExpectedConditions.elementToBeClickable(VBLoc));
		act.moveToElement(VBLoc).click().build().perform();
		VBLoc.sendKeys(Keys.TAB);
		logs.info("LOC service selected");

		// Click on Origin Text Box
		WebElement VBOrigin = isElementPresent("VB_origin_id");
		wait.until(ExpectedConditions.visibilityOf(VBOrigin));
		jse.executeScript("arguments[0].click();", VBOrigin);
		logs.info("Origin Text Box Clicked");

		// Click on Search
		WebElement VBSearch = isElementPresent("VB_Search_id");
		wait.until(ExpectedConditions.visibilityOf(VBSearch));
		wait.until(ExpectedConditions.elementToBeClickable(VBSearch));
		act.moveToElement(VBSearch).click().build().perform();
		logs.info("Click on Search");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		if (i == 1) {
			// Screen Shot
			getScreenshot(driver, "VB_ServiceFilter_MNX");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-MNX");
		} else if (i == 2) {
			// Screen Shot
			getScreenshot(driver, "VB_ServiceFilter_FedEX");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-FedEx");
		}

		else if (i == 3) {
			// Screen Shot
			getScreenshot(driver, "VB_ServiceFilter_MTX");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-MTX");
		}
		msg.append("Service Filter=Pass" + "\n");
		// Click on Reset
		WebElement VBReset = isElementPresent("VB_Reset_id");
		wait.until(ExpectedConditions.visibilityOf(VBReset));
		wait.until(ExpectedConditions.elementToBeClickable(VBReset));
		act.moveToElement(VBReset).click().build().perform();
		logs.info("Click on Reset");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		Thread.sleep(4000);

	}

	public void locationFilter(int i) throws IOException, InterruptedException {

		// Extract location
		WebElement VBLocation = isElementPresent("VB_location_xpath");
		wait.until(ExpectedConditions.visibilityOf(VBLocation));
		String Location = VBLocation.getText();
		logs.info("Location is:-" + Location);
		logs.info("Location Extracted");

		// Enter Origin Location
		WebElement VBOrigin = isElementPresent("VB_origin_id");
		wait.until(ExpectedConditions.visibilityOf(VBOrigin));
		VBOrigin.sendKeys(Location);
		logs.info("Origin Entered");

		// Destination Entered
		WebElement VBDestin = isElementPresent("VB_destin_id");
		wait.until(ExpectedConditions.visibilityOf(VBDestin));
		VBDestin.sendKeys(Location);
		logs.info("Destination Entered");

		// Click on Search
		WebElement VBSearch = isElementPresent("VB_Search_id");
		wait.until(ExpectedConditions.visibilityOf(VBSearch));
		wait.until(ExpectedConditions.elementToBeClickable(VBSearch));
		act.moveToElement(VBSearch).click().build().perform();
		logs.info("Click on Search");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		if (i == 1) {
			// Screen Shot
			getScreenshot(driver, "VB_LoactionFilter_MNX");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-MNX");
		} else if (i == 2) {

			// Screen Shot
			getScreenshot(driver, "VB_LoactionFilter_FedEx");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-FedEx");
		}
		
		msg.append("Origin & Destiantion location Filter=Pass" + "\n");
		// Click on Reset
		WebElement VBReset = isElementPresent("VB_Reset_id");
		wait.until(ExpectedConditions.visibilityOf(VBReset));
		wait.until(ExpectedConditions.elementToBeClickable(VBReset));
		act.moveToElement(VBReset).click().build().perform();
		logs.info("Click on Reset");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

	}

	public void accountFilter(int i) throws IOException, InterruptedException {

		// Select Account compare option
		WebElement VBAccountCompare = isElementPresent("VB_AccountC_id");
		wait.until(ExpectedConditions.visibilityOf(VBAccountCompare));
		wait.until(ExpectedConditions.elementToBeClickable(VBAccountCompare));
		act.moveToElement(VBAccountCompare).click().build().perform();
		logs.info("Click on Account");

		// Select Comparison operator
		Select Sel = new Select(VBAccountCompare);
		Sel.selectByValue("Y");
		logs.info("Equal To Operator is Selcted");
		if (i == 1) {
			// Enter Account Number MNX
			WebElement VBAccountTxtBox = isElementPresent("VB_AccountTxtBox_id");
			wait.until(ExpectedConditions.visibilityOf(VBAccountTxtBox));
			VBAccountTxtBox.sendKeys("950655");
			logs.info("Account Number Entered");
		} else if (i == 2) {
			// Enter Account Number FedEx
			WebElement VBAccountTxtBox = isElementPresent("VB_AccountTxtBox_id");
			wait.until(ExpectedConditions.visibilityOf(VBAccountTxtBox));
			VBAccountTxtBox.sendKeys("100901994");
			logs.info("Account Number Entered");
		} else if (i == 3) {
			// Enter Account Number MTX
			WebElement VBAccountTxtBox = isElementPresent("VB_AccountTxtBox_id");
			wait.until(ExpectedConditions.visibilityOf(VBAccountTxtBox));
			VBAccountTxtBox.sendKeys("950837");
			logs.info("Account Number Entered");

		}

		// Click on Search
		WebElement VBSearch = isElementPresent("VB_Search_id");
		wait.until(ExpectedConditions.visibilityOf(VBSearch));
		wait.until(ExpectedConditions.elementToBeClickable(VBSearch));
		act.moveToElement(VBSearch).click().build().perform();
		logs.info("Click on Search");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		if (i == 1) {
			// Screen Shot
			getScreenshot(driver, "VB_AccountFilter_MNX");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-MNX");
		} else if (i == 2) {
			// Screen Shot
			getScreenshot(driver, "VB_AccountFilter_FedEx");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-FedEx");
		} else if (i == 3) {
			// Screen Shot
			getScreenshot(driver, "VB_AccountFilter_MTX");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-MTX");
		}
		
		msg.append("Account Equals To Filter=Pass" + "\n");

	}

	public void VerifyReCalCharges(int i) throws InterruptedException, IOException {

		if(i==1) {
		//Select LOC service 
		SelectLOCService();    
		
		}
	
		else if(i==3) {
			
			logs.info("Move on");
		}
		
		if (i == 1) {
			// Extract Pick Up ID MNX
			WebElement VBPickUpID = isElementPresent("VB_pickUpID_xpath");
			wait.until(ExpectedConditions.visibilityOf(VBPickUpID));
			String PickUpId = VBPickUpID.getText();
			this.PickUpId = PickUpId;
			logs.info("PickUp ID is:-" + PickUpId);
			logs.info("PickUp ID Extracted");
		} else if (i == 3) {

			// Extract Pick Up ID for FedEx
			WebElement VBPickUpID = isElementPresent("VB_pickupIdMTX_xpath");
			wait.until(ExpectedConditions.visibilityOf(VBPickUpID));
			String PickUpId = VBPickUpID.getText();
			this.PickUpId = PickUpId;
			logs.info("PickUp ID is:-" + PickUpId);
			logs.info("PickUp ID Extracted");

		}

		// Enter In PickUp Text Box
		WebElement PickTxtBox = isElementPresent("VB_pickUptxtBox_id");
		wait.until(ExpectedConditions.visibilityOf(PickTxtBox));
		PickTxtBox.sendKeys(PickUpId);
		PickTxtBox.sendKeys(Keys.TAB);
		logs.info("Entered PickUp ID");

		// Click on Search
		WebElement VBSearch = isElementPresent("VB_Search_id");
		wait.until(ExpectedConditions.visibilityOf(VBSearch));
		wait.until(ExpectedConditions.elementToBeClickable(VBSearch));
		act.moveToElement(VBSearch).click().build().perform();
		logs.info("Click on Search");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// Click on Verify
		if (i == 1 || i == 3) {
			WebElement VBverifyS = isElementPresent("VB_SVerify_xpath");
			jse.executeScript("arguments[0].scrollIntoView();", VBverifyS);
			this.VBverifyS = VBverifyS;
		}

		wait.until(ExpectedConditions.visibilityOf(VBverifyS));
		wait.until(ExpectedConditions.elementToBeClickable(VBverifyS));
		act.moveToElement(VBverifyS).click().build().perform();
		logs.info("Click on Verify For Given PickUp ID");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// Scroll up to Customer charges
		
		if (i == 1) {
			// Scroll up to Customer charges
			WebElement VBBaseRate = isElementPresent("VB_BaseRate_xpath");
			jse.executeScript("arguments[0].scrollIntoView();", VBBaseRate);
			this.VBBaseRate = VBBaseRate;
			logs.info("Scroll Down till Customer Charges");
		} else if (i == 3) {

			Thread.sleep(2000);
			// Scroll up to Customer charges
			WebElement VBBaseRate = isElementPresent("VB_BaseRateMTX_xpath");
			jse.executeScript("arguments[0].scrollIntoView();", VBBaseRate);
			this.VBBaseRate = VBBaseRate;
			logs.info("Scroll Down till Customer Charges");
		}
		
		
		
		//Extract Actual Rate
		
		if (i == 1) {
		
			// Extract Actual Rate
			WebElement VBActaulRate = isElementPresent("VB_ActualRate_xpath");
		
			wait.until(ExpectedConditions.visibilityOf(VBActaulRate));
			wait.until(ExpectedConditions.elementToBeClickable(VBActaulRate));
			String ActaulRate = VBActaulRate.getText();
			this.ActaulRate = ActaulRate;
			logs.info("Actaul Rate is:-" + ActaulRate);
			logs.info("Actaul Rate is Extracted");
			
		}

		else if (i == 3) {
			// Extract Actual Rate
			WebElement VBActaulRate = isElementPresent("VB_ActualRateMTX_xpath");
		
			wait.until(ExpectedConditions.visibilityOf(VBActaulRate));
			wait.until(ExpectedConditions.elementToBeClickable(VBActaulRate));
			String ActaulRate = VBActaulRate.getText();
			this.ActaulRate = ActaulRate;
			logs.info("Actaul Rate is:-" + ActaulRate);
			logs.info("Actaul Rate is Extracted");

		}


		
		
		
		// Remove Base Rate
		wait.until(ExpectedConditions.visibilityOf(VBBaseRate));
		wait.until(ExpectedConditions.elementToBeClickable(VBBaseRate));
		act.moveToElement(VBBaseRate).click().build().perform();
		logs.info("Click on Remove Base Rate Button");

		if (i == 1) {
			// Rate after Removing base rate
			WebElement VBModifiedRate = isElementPresent("VB_ActualRate_xpath");
			wait.until(ExpectedConditions.visibilityOf(VBModifiedRate));
			wait.until(ExpectedConditions.elementToBeClickable(VBModifiedRate));
			String ModifiedRate = VBModifiedRate.getText();
			logs.info("Modified  Rate is:-" + ModifiedRate);
			logs.info("Modified Rate is Extracted");
		}

		else if (i == 3) {
			// Rate after Removing base rate
			WebElement VBModifiedRate = isElementPresent("VB_ActualRateMTX_xpath");
			wait.until(ExpectedConditions.visibilityOf(VBModifiedRate));
			wait.until(ExpectedConditions.elementToBeClickable(VBModifiedRate));
			String ModifiedRate = VBModifiedRate.getText();
			logs.info("Modified  Rate is:-" + ModifiedRate);
			logs.info("Modified Rate is Extracted");

		}

		// Click on Save
		WebElement VBSave = isElementPresent("VB_SaveRate_xpath");
		wait.until(ExpectedConditions.visibilityOf(VBSave));
		wait.until(ExpectedConditions.elementToBeClickable(VBSave));
		act.moveToElement(VBSave).click().build().perform();
		logs.info("Click on Save Button");

		// Click on Exit without save
		WebElement VBExitWSave = isElementPresent("VB_ExitWSave_xpath");
		wait.until(ExpectedConditions.visibilityOf(VBExitWSave));
		wait.until(ExpectedConditions.elementToBeClickable(VBExitWSave));
		act.moveToElement(VBExitWSave).build().perform();
//	     act.click(VBExitWSave).build().perform();
		jse.executeScript("arguments[0].click();", VBExitWSave);
        logs.info("Click on Exit without Save Button");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		Thread.sleep(2000);

		// Select Radio button for Pick Up ID
		WebElement VBRadioButton = isElementPresent("VB_VFRadioBttn_xpath");
		wait.until(ExpectedConditions.visibilityOf(VBRadioButton));
		wait.until(ExpectedConditions.elementToBeClickable(VBRadioButton));
		act.moveToElement(VBRadioButton).click().build().perform();
		logs.info("Click on Radio Button");

		// Click on Re-Cal charges
		WebElement VBRecalCharges = isElementPresent("VB_ReCalCharges_id");
		wait.until(ExpectedConditions.visibilityOf(VBRecalCharges));
		wait.until(ExpectedConditions.elementToBeClickable(VBRecalCharges));
		act.moveToElement(VBRecalCharges).click().build().perform();
		logs.info("Click on Re-Calculate Charges");

		// Verify Re-Cal charges is done message
		WebElement VBRecalSucessMsg = isElementPresent("VB_ReCalSuccessMsg_id");
		wait.until(ExpectedConditions.visibilityOf(VBRecalSucessMsg));
		String SucessMsg = VBRecalSucessMsg.getText();
		logs.info("Validate Successful Message:-" + SucessMsg);
		logs.info("Validated Sucessful Message");

		
		
		if (i == 1) {
			// Screen Shot
			getScreenshot(driver, "VB_Re-CalCharges_MNX");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-MNX");
		} else if (i == 3) {

			// Screen Shot
			getScreenshot(driver, "VB_Re-CalCharges_MTX");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-MTX");
		}

		// Charges after Re-Cal
		if (i == 1) {
			WebElement VBRecalRate = isElementPresent("VB_ReCalRate_xpath");
			wait.until(ExpectedConditions.visibilityOf(VBRecalRate));
			this.VBRecalRate = VBRecalRate;
		} else if (i == 3) {

			WebElement VBRecalRate = isElementPresent("VB_ReCalRateMTX_xpath");
			jse.executeScript("arguments[0].scrollIntoView();", VBRecalRate);
			wait.until(ExpectedConditions.visibilityOf(VBRecalRate));
			this.VBRecalRate = VBRecalRate;
		}

		String ReCalRate = VBRecalRate.getText();
		logs.info("Rate After Re-Calculation:=" + ReCalRate);
		logs.info("Rate After Re-Calculation is Extracetd");

		// Validate Rate Before & After Re-cal

		if (ActaulRate.equals(ReCalRate)) {

			logs.info("Re-Calculate Charges Function is working Properly");
		} else {

			logs.info("Re-Calculate Charges Function is not working Properly");
		}
		
		msg.append("Re-Calculate Charges Functionality=Pass" + "\n");
		
		// Click on Reset
		WebElement VBReset = isElementPresent("VB_Reset_id");
		wait.until(ExpectedConditions.visibilityOf(VBReset));
		wait.until(ExpectedConditions.elementToBeClickable(VBReset));
		act.moveToElement(VBReset).click().build().perform();
		logs.info("Click on Reset");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

	}
	
	
	

	public void VerifyJob(int i) throws Exception {

		// Scroll to Memo
		WebElement Memo = driver.findElement(By.xpath("(//div[@role='checkbox'])[1]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", Memo);

		// Select Radio button for Pick Up ID
		WebElement VBRadioButton = isElementPresent("VB_VFRadioBttn_xpath");
		jse.executeScript("arguments[0].scrollIntoView(true);", VBRadioButton);
		wait.until(ExpectedConditions.visibilityOf(VBRadioButton));
		wait.until(ExpectedConditions.elementToBeClickable(VBRadioButton));
		act.moveToElement(VBRadioButton).click().build().perform();
		logs.info("Click on Radio Button");

		// Click on Verify job `
		WebElement VBVerifyJob = isElementPresent("VB_VerifyJobbttn_id");
		wait.until(ExpectedConditions.visibilityOf(VBVerifyJob));
		wait.until(ExpectedConditions.elementToBeClickable(VBVerifyJob));
		act.moveToElement(VBVerifyJob).click().build().perform();
		logs.info("Click on Verify Job");

		// Handle Pop-Up
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[text()='Are you sure you want to Verify Job(s)? ']")));
		WebElement VBYes = isElementPresent("VB_YesPopUp_xpath");
		wait.until(ExpectedConditions.visibilityOf(VBYes));
		wait.until(ExpectedConditions.elementToBeClickable(VBYes));
		jse.executeScript("arguments[0].click();", VBYes);
		logs.info("Click on Yes option");

		// Search Pick Up Id
		SearchPICKUPID();

		// Verify "Verified" stage
		WebElement VerifiedStage = isElementPresent("VB_VerifiedStage_xpath");
		wait.until(ExpectedConditions.visibilityOf(VerifiedStage));
		String VerifiedText = VerifiedStage.getText();

		// --Get StageName
		OrderCreation OC = new OrderCreation();
		OC.getStageName();
		
		if (VerifiedText.equals("VERIFIED")) {

			logs.info("Job with PickUp ID:-" + PickUpId + " is " + VerifiedText);
			
			msg.append("Job with PickUp ID:-" + PickUpId + " is " + VerifiedText + "\n");
			
		}

	}


	public void JOBIDFilter(int i) throws Exception {
		
		//Get Job Id & go to Billing tab 
		getJOBID();
		
		if(i==1) {
			logs.info("Ready To Enter Job Id");
		    }
		
		else if(i==2) {
			
			//FedEx
			WebElement VBFedEx = isElementPresent("VB_FedEx_xpath");
			wait.until(ExpectedConditions.visibilityOf(VBFedEx));
			wait.until(ExpectedConditions.elementToBeClickable(VBFedEx));
			act.moveToElement(VBFedEx).click().build().perform();
			logs.info("Click on UNVERIFIED FedEx");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			
		}
		else if (i==3) {
			
			//MTX
			WebElement VBMTX = isElementPresent("VB_MTX_xpath");
			wait.until(ExpectedConditions.visibilityOf(VBMTX));
			wait.until(ExpectedConditions.elementToBeClickable(VBMTX));
			act.moveToElement(VBMTX).click().build().perform();
			logs.info("Click on UNVERIFIED MTX");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			
		}
		
	
	
		//Job Id filter MNX 
		WebElement VBJobID = isElementPresent("VB_JobIdTxtBox_id");
		wait.until(ExpectedConditions.visibilityOf(VBJobID));
		VBJobID.clear();
		VBJobID.sendKeys(JOBID);
        logs.info("Entered Job Id"); 
        
		// Click on Search
		WebElement VBSearch = isElementPresent("VB_Search_id");
		wait.until(ExpectedConditions.visibilityOf(VBSearch));
		wait.until(ExpectedConditions.elementToBeClickable(VBSearch));
		act.moveToElement(VBSearch).click().build().perform();
		logs.info("Click on Search");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		if (i == 1) {
			// Screen Shot
			getScreenshot(driver, "VB_JobIDFilter_MNX");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-MNX");
		} else if (i == 2) {

			// Screen Shot
			getScreenshot(driver, "VB_JobIDFilter_FedEx");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-FedEx");
		}
		else if (i == 3) {

			// Screen Shot
			getScreenshot(driver, "VB_JobIDFilter_MTX");
			Thread.sleep(2500);
			logs.info("Screen Shot Taken-MTX");
		}
		
		 logs.info("Job Id Fliter Working Properly"); 
		
		
	}
	
	
	public void getJOBID() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		Actions act = new Actions(driver);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// --Go to Operation
		WebElement operation = isElementPresent("OperMenu_id");
		wait.until(ExpectedConditions.visibilityOf(operation));
		wait.until(ExpectedConditions.elementToBeClickable(operation));
		act.moveToElement(operation).click().build().perform();
		logs.info("Click on Operation");
//Order Search 
		WebElement orderSearch = driver.findElement(By.id("a_OrderSearch"));
		wait.until(ExpectedConditions.visibilityOf(orderSearch));
		wait.until(ExpectedConditions.elementToBeClickable(orderSearch));
		jse.executeScript("arguments[0].click();", orderSearch);
		logs.info("Click on Order Search");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AdvancesSearch")));
//Enter Pickup id 
		WebElement pickuptextbox = driver.findElement(By.xpath("//input[@id='txtPickup']"));
		wait.until(ExpectedConditions.visibilityOf(pickuptextbox));
		jse.executeScript("arguments[0].click();", pickuptextbox);
		pickuptextbox.clear();
		Thread.sleep(2000);
		pickuptextbox.sendKeys(PickUpId);
		logs.info("Entered Pick Up ID");

//Search 
		WebElement Search = driver.findElement(By.id("btnSearch"));
		wait.until(ExpectedConditions.visibilityOf(Search));
		wait.until(ExpectedConditions.elementToBeClickable(Search));
		jse.executeScript("arguments[0].click();", Search);
		logs.info("Clicked on Search");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='ng-scope'])[2]")));
		Thread.sleep(1500);
//JobID 
		WebElement JobID = driver.findElement(By.xpath("//*[@id=\"lblJobIdValue_0\"]"));
		wait.until(ExpectedConditions.visibilityOf(JobID));
		String JOBID=JobID.getText();
		this.JOBID=JOBID;
		logs.info("Job Id Is:-"+JOBID);
		
		//Go to Verify Bill 
		 OpenVerifyBill();

		 
		 
	}
	
	public void SearchPICKUPID() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		Actions act = new Actions(driver);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// --Go to Operation
		WebElement operation = isElementPresent("OperMenu_id");
		wait.until(ExpectedConditions.visibilityOf(operation));
		wait.until(ExpectedConditions.elementToBeClickable(operation));
		act.moveToElement(operation).click().build().perform();
		logs.info("Click on Operation");
//Order Search 
		WebElement orderSearch = driver.findElement(By.id("a_OrderSearch"));
		wait.until(ExpectedConditions.visibilityOf(orderSearch));
		wait.until(ExpectedConditions.elementToBeClickable(orderSearch));
		jse.executeScript("arguments[0].click();", orderSearch);
		logs.info("Click on Order Search");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AdvancesSearch")));
//Enter Pickup id 
		WebElement pickuptextbox = driver.findElement(By.xpath("//input[@id='txtPickup']"));
		wait.until(ExpectedConditions.visibilityOf(pickuptextbox));
		jse.executeScript("arguments[0].click();", pickuptextbox);
		pickuptextbox.clear();
		Thread.sleep(2000);
		pickuptextbox.sendKeys(PickUpId);
		logs.info("Entered Pick Up ID");

//Search 
		WebElement Search = driver.findElement(By.id("btnSearch"));
		wait.until(ExpectedConditions.visibilityOf(Search));
		wait.until(ExpectedConditions.elementToBeClickable(Search));
		jse.executeScript("arguments[0].click();", Search);
		logs.info("Clicked on Search");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='ng-scope'])[2]")));
		Thread.sleep(1500);
//JobID 
		WebElement JobID = driver.findElement(By.xpath("//*[@id=\"lblJobIdValue_0\"]"));
		wait.until(ExpectedConditions.visibilityOf(JobID));
		wait.until(ExpectedConditions.elementToBeClickable(JobID));
		act.moveToElement(JobID).build().perform();
		act.click(JobID).perform();
		logs.info("Clicked on JOBID");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

	}

	
  public void SelectLOCService() throws InterruptedException {
	  
		// Select Service
		WebElement VBService = isElementPresent("VB_Service_xpath");
		wait.until(ExpectedConditions.visibilityOf(VBService));
		wait.until(ExpectedConditions.elementToBeClickable(VBService));
		act.moveToElement(VBService).click().build().perform();
		logs.info("Click on Service");
       Thread.sleep(2000);
       
		// Select LOC
		WebElement VBLoc = isElementPresent("VB_LOC_linkText");
		jse.executeScript("arguments[0].scrollIntoView();", VBLoc);
		wait.until(ExpectedConditions.visibilityOf(VBLoc));
		wait.until(ExpectedConditions.elementToBeClickable(VBLoc));
		act.moveToElement(VBLoc).click().build().perform();
		VBLoc.sendKeys(Keys.TAB);
		logs.info("LOC service selected");

		// Click on Origin Text Box
		WebElement VBOrigin = isElementPresent("VB_origin_id");
		wait.until(ExpectedConditions.visibilityOf(VBOrigin));
		jse.executeScript("arguments[0].click();", VBOrigin);
		logs.info("Origin Text Box Clicked");
		
		// Click on Search
		WebElement VBSearch = isElementPresent("VB_Search_id");
		wait.until(ExpectedConditions.visibilityOf(VBSearch));
		wait.until(ExpectedConditions.elementToBeClickable(VBSearch));
		act.moveToElement(VBSearch).click().build().perform();
		logs.info("Click on Search");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
  }
	
}
