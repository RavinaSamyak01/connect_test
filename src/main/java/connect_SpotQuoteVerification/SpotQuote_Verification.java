package connect_SpotQuoteVerification;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;
import connect_OCBaseMethods.Deliver;
import connect_OCBaseMethods.OrderCreation;
import connect_OCBaseMethods.Pickup;
import connect_OCBaseMethods.ReadyForDispatch;
import connect_OCBaseMethods.TCAcknowledge;
import connect_OCBaseMethods.VerifyCustomerBill;

public class SpotQuote_Verification extends  BaseInit{

	
	@Test
	public void VerifySpotQuote() throws Exception {
		
	try {	logs.info("==== Spot Quote Rate Verification : Start ==== " + "\n");
		msg.append("\n\n" + "===== Spot Quote Rate Verification : Start ====" + "\n");
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		Actions act = new Actions(driver);
		// --Order Creation
		OrderCreation OC = new OrderCreation();
		OC.orderCreation(1);

		// --Scroll to get Rate
		jse.executeScript("window.scrollBy(0,400)", "");
		String cost = isElementPresent("TLActualRate_id").getText();
		setData("Sheet1", 1, 31, cost);
		logs.info("Scroll down to Get the Rate");
		
		//Enter Spot Quote Rate 
		WebElement CustRevenue= isElementPresent("SQV_CustRevenue_id");
		jse.executeScript("arguments[0].scrollIntoView();", CustRevenue);
		wait.until(ExpectedConditions.visibilityOf(CustRevenue));
		wait.until(ExpectedConditions.elementToBeClickable(CustRevenue));
		//jse.executeScript("arguments[0].value='200';", CustRevenue);
		Thread.sleep(1500);
		CustRevenue.click();
		Thread.sleep(1500);
		CustRevenue.sendKeys("200",Keys.TAB);;
	    logs.info("Customer Spot Quote Rate is added:=$200");
		msg.append("Customer Spot Quote Rate is added:=$200" + "\n");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='SPOT QUOTE']")));
		
		
	    
		 //Recalculate 
		WebElement Recalculate= isElementPresent("SQV_ReCal_id");
		wait.until(ExpectedConditions.visibilityOf(Recalculate));
		wait.until(ExpectedConditions.elementToBeClickable(Recalculate));
		act.moveToElement(Recalculate).build().perform();
		jse.executeScript("arguments[0].click();", Recalculate);
		logs.info("Clicked On Recalculte Charges");
		
		//Save Changes 
		WebElement SaveChanges= isElementPresent("SQV_SaveChanges_id");
		wait.until(ExpectedConditions.visibilityOf(SaveChanges));
		wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
		act.moveToElement(SaveChanges).build().perform();
		jse.executeScript("arguments[0].click();", SaveChanges);
		logs.info("Clicked On Save Changes");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		
		
		// --Moved to Job Status
		WebElement idJob = isElementPresent("SQV_TCAckJobStatus_xpath");
		wait.until(ExpectedConditions.visibilityOf(idJob));
		wait.until(ExpectedConditions.elementToBeClickable(idJob));
		act.moveToElement(idJob).build().perform();
	    act.click(idJob).build().perform();
		logs.info("Clicked on Job Status Tab");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		
		// TC Acknowledge
		TCAcknowledge TCAck = new TCAcknowledge();
		TCAck.tcAcknowledge();
		
		//Enter Spot Courier Rate 
		WebElement CourierSpotQuote= isElementPresent("SQV_SpotQuoteCourier_xpath");
		wait.until(ExpectedConditions.visibilityOf(CourierSpotQuote));
		wait.until(ExpectedConditions.elementToBeClickable(CourierSpotQuote));
		//jse.executeScript("arguments[0].value='200';", CustRevenue);
		CourierSpotQuote.click();
		logs.info("Click on Spot Quote for select all text");
		Thread.sleep(1500);
		CourierSpotQuote.sendKeys("50",Keys.TAB);;
	    logs.info("Courier Spot Quote Rate is added:=$50");
		msg.append("Courier Spot Quote Rate is added:=$50" + "\n");
	
	    
	    
		// Pickup Alert
		ReadyForDispatch RFD = new ReadyForDispatch();
		RFD.pickupAlert();
		
		// PICKEDUP
		Pickup PU = new Pickup();
		PU.confirmPickup();
	    
		// DELIVERED
		Deliver Del = new Deliver();
        Del.confirmDelivery();
        
		// Verify Customer Bill
		VerifyCustomerBill VCB = new VerifyCustomerBill();
		VCB.verifyCustomerBill(1);
		
		
		//Open View Rate 
		WebElement ViewRate= isElementPresent("SQV_ViewRate_id");
		wait.until(ExpectedConditions.visibilityOf(ViewRate));
		wait.until(ExpectedConditions.elementToBeClickable(ViewRate));
		act.moveToElement(ViewRate).build().perform();
	   // act.click(ViewRate).build().perform();
	    jse.executeScript("arguments[0].click();", ViewRate);
	    logs.info("Clicked on View Rate");
	    Thread.sleep(5000);
	    
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='ScrollRate']")));
	    
	    //Verify Customer Spot quote rate 
		WebElement CustSQR= isElementPresent("SQV_CustSQR_xpath");
		wait.until(ExpectedConditions.visibilityOf(CustSQR));
		String CustomerSQR=CustSQR.getText();
	    logs.info("Customer Spot quote rate on VBC stage:="+CustomerSQR);
	    msg.append("Customer Spot quote rate on VBC stage:="+CustomerSQR+ "\n");
	    
	    if(CustomerSQR.equals("$200.00")) {
	    	
	        logs.info("Customer Spot Quote Rate is Verified");
			msg.append("Customer Spot Quote Rate is Verified"+ "\n");
	    	
	    }
	    
	    //Verify Courier Spot quote rate 
		WebElement CourierSQR= isElementPresent("SQV_CourierSQR_xpath");
		wait.until(ExpectedConditions.visibilityOf(CourierSQR));
		String CourierSQRate=CourierSQR.getText();
	    logs.info("Courier Spot quote rate on VBC stage:="+CourierSQRate);
	    msg.append("Courier Spot quote rate on VBC stage:="+CourierSQRate+ "\n");
	    
    if(CourierSQRate.equals("$50.00")) {
	    	
	        logs.info("Courier Spot Quote Rate is Verified");
			msg.append("Courier Spot Quote Rate is Verified"+ "\n");
	    	
	    }
    
    getScreenshot(driver, "SpotQuoteRateVerify");      
    
	logs.info("==== Spot Quote Rate Verification : Pass ==== " + "\n");
	msg.append("\n" + "===== Spot Quote Rate Verification : Pass ====" + "\n");
	
	}
	
	
	catch(Exception e) {
		
		logs.info("Error in Spot quote rate verification:- " + e);
		msg.append("Error in Spot quote rate verification");
	}
    
	
	logs.info("===== Spot Quote Rate Verification : End =====" + "\n");
	msg.append("===== Spot Quote Rate Verification : End =====" + "\n");
		
	
	}
}
