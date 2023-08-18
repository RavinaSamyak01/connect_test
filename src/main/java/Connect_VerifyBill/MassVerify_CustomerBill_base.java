package Connect_VerifyBill;

import java.io.IOException;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import connect_BasePackage.BaseInit;

public class MassVerify_CustomerBill_base extends BaseInit {

	JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));// wait time
	Actions act = new Actions(driver);
    public String OrderID;
    
	public void OpenMassVerifyCustomerBill() {
		// Go TO Billing
		WebElement billing = isElementPresent("Billing_xpath");
		wait.until(ExpectedConditions.visibilityOf(billing));
		wait.until(ExpectedConditions.elementToBeClickable(billing));
		act.moveToElement(billing).click().build().perform();
		logs.info("Click on billing");

		// GO To Mass verify customer Bill
		WebElement MassVCB = isElementPresent("MassVCB_xpath");
		wait.until(ExpectedConditions.visibilityOf(MassVCB));
		wait.until(ExpectedConditions.elementToBeClickable(MassVCB));
		act.moveToElement(MassVCB).click().build().perform();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Account #']")));
		logs.info("Click on Mass verify Customer Bill");

	}

	public void DateFilter() throws IOException, InterruptedException {

		// From Date
		WebElement FromDate = isElementPresent("MVCB_fromdate_id");
		wait.until(ExpectedConditions.visibilityOf(FromDate));
		FromDate.clear();
		FromDate.sendKeys("03/01/2023");
		FromDate.sendKeys(Keys.TAB);
		logs.info("Entered Order From Date");

		// To date
		WebElement ToDate = isElementPresent("MVCB_todate_id");
		wait.until(ExpectedConditions.visibilityOf(ToDate));
		ToDate.sendKeys(BaseInit.CuDate());
		ToDate.sendKeys(Keys.TAB);
		logs.info("Entered Order To Date");

		// Search
		// Click on Search
		WebElement VBSearch = isElementPresent("MVCB_SearchBttn_id");
		wait.until(ExpectedConditions.visibilityOf(VBSearch));
		wait.until(ExpectedConditions.elementToBeClickable(VBSearch));
		Thread.sleep(2000);
		act.moveToElement(VBSearch).click().build().perform();
		logs.info("Click on Search");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblAccount")));
		// Screen Shot
		// Screen Shot
		getScreenshot(driver, "MVCB_DateFilter");
		Thread.sleep(2500);

		logs.info("Order From & Order To date Filter=Pass");
		msg.append("Order From & Order To date Filter=Pass" + "\n");

		// Click on Reset
		WebElement VBReset = isElementPresent("MVCB_ResetBttn_id");
		wait.until(ExpectedConditions.visibilityOf(VBReset));
		wait.until(ExpectedConditions.elementToBeClickable(VBReset));
		act.moveToElement(VBReset).click().build().perform();
		logs.info("Click on Reset");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

	}

	public void OrderTypeFilter() throws IOException, InterruptedException {

		// Order Type
		// Click on Order Type
		WebElement MVCBOrderType = isElementPresent("MVCB_orderType_xpath");
		wait.until(ExpectedConditions.visibilityOf(MVCBOrderType));
		wait.until(ExpectedConditions.elementToBeClickable(MVCBOrderType));
		act.moveToElement(MVCBOrderType).click().build().perform();
		logs.info("Click on Order Type");

		// Select Work order in
		WebElement MVCBWorkOrderIn = isElementPresent("MVCB_WorkOrderIn_xpath");
		jse.executeScript("arguments[0].scrollIntoView();", MVCBWorkOrderIn);
		wait.until(ExpectedConditions.visibilityOf(MVCBWorkOrderIn));
		wait.until(ExpectedConditions.elementToBeClickable(MVCBWorkOrderIn));
		act.moveToElement(MVCBWorkOrderIn).click().build().perform();
		logs.info("Work Order In is selected");

		// Search
		// Click on Search
		WebElement VBSearch = isElementPresent("MVCB_SearchBttn_id");
		wait.until(ExpectedConditions.visibilityOf(VBSearch));
		wait.until(ExpectedConditions.elementToBeClickable(VBSearch));
		act.moveToElement(VBSearch).click().build().perform();
		logs.info("Click on Search");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblAccount")));
		// Screen Shot
		// Screen Shot
		getScreenshot(driver, "MVCB_OrderTypeFilter");
		Thread.sleep(2500);

		logs.info("Order Type Filter=Pass");
		msg.append("Order Type Filter=Pass" + "\n");

		// Click on Reset
		WebElement VBReset = isElementPresent("MVCB_ResetBttn_id");
		wait.until(ExpectedConditions.visibilityOf(VBReset));
		wait.until(ExpectedConditions.elementToBeClickable(VBReset));
		act.moveToElement(VBReset).click().build().perform();
		logs.info("Click on Reset");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

	}

	public void FSLNameFilter() throws IOException, InterruptedException {
		// Order Type
		// Click on FSL Name
		WebElement MVCBFSLName = isElementPresent("MVCB_FSLname_xpath");
		wait.until(ExpectedConditions.visibilityOf(MVCBFSLName));
		wait.until(ExpectedConditions.elementToBeClickable(MVCBFSLName));
		act.moveToElement(MVCBFSLName).click().build().perform();
		logs.info("Click on FSL Name");

		// Select ATL-Network fsl
		WebElement MVCBATLNetwork = isElementPresent("MVCB_ATLnetwork_xpath");
		jse.executeScript("arguments[0].scrollIntoView();", MVCBATLNetwork);
		wait.until(ExpectedConditions.visibilityOf(MVCBATLNetwork));
		wait.until(ExpectedConditions.elementToBeClickable(MVCBATLNetwork));
		act.moveToElement(MVCBATLNetwork).click().build().perform();
		logs.info("ATL-Network is selected");

		// Search
		// Click on Search
		WebElement VBSearch = isElementPresent("MVCB_SearchBttn_id");
		wait.until(ExpectedConditions.visibilityOf(VBSearch));
		wait.until(ExpectedConditions.elementToBeClickable(VBSearch));
		act.moveToElement(VBSearch).click().build().perform();
		logs.info("Click on Search");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblAccount")));
		// Screen Shot
		// Screen Shot
		getScreenshot(driver, "MVCB_FSLNameFilter");
		Thread.sleep(2500);

		logs.info("FSL Name Filter=Pass");
		msg.append("FSL Name Filter=Pass" + "\n");

		// Click on Reset
		WebElement VBReset = isElementPresent("MVCB_ResetBttn_id");
		wait.until(ExpectedConditions.visibilityOf(VBReset));
		wait.until(ExpectedConditions.elementToBeClickable(VBReset));
		act.moveToElement(VBReset).click().build().perform();
		logs.info("Click on Reset");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
	}

	public void AccountFilter() throws IOException, InterruptedException {

		// Enter Account Number
		WebElement MVCBAccount = isElementPresent("MVCB_Account_id");
		wait.until(ExpectedConditions.visibilityOf(MVCBAccount));
		MVCBAccount.clear();
		MVCBAccount.sendKeys("950682");
		logs.info("Account Number Entered");

		// Click on Include
		WebElement MVCBInclude = isElementPresent("MVCB_Include_xpath");
		wait.until(ExpectedConditions.visibilityOf(MVCBInclude));
		wait.until(ExpectedConditions.elementToBeClickable(MVCBInclude));
		act.moveToElement(MVCBInclude).click().build().perform();
		logs.info("Click on Include radio Button");

		// Search
		// Click on Search
		WebElement VBSearch = isElementPresent("MVCB_SearchBttn_id");
		wait.until(ExpectedConditions.visibilityOf(VBSearch));
		wait.until(ExpectedConditions.elementToBeClickable(VBSearch));
		act.moveToElement(VBSearch).click().build().perform();
		logs.info("Click on Search");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblAccount")));
		// Click on Show $0 jobs
		WebElement MVCBShow0job = isElementPresent("MVCB_Show0job_id");
		wait.until(ExpectedConditions.visibilityOf(MVCBShow0job));
		wait.until(ExpectedConditions.elementToBeClickable(MVCBShow0job));
		act.moveToElement(MVCBShow0job).click().build().perform();
		logs.info("Click on Show $0 Job");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// Screen Shot
		getScreenshot(driver, "MVCB_AccountFilter");
		Thread.sleep(2500);

		logs.info("Account Filter=Pass");
		msg.append("Account Filter=Pass" + "\n");

	}

	public void VerifyOrder() throws IOException, InterruptedException {

		// Radio Button
		WebElement MVCBRadioButton = isElementPresent("MVCB_radioBttn_xpath");
		wait.until(ExpectedConditions.visibilityOf(MVCBRadioButton));
		wait.until(ExpectedConditions.elementToBeClickable(MVCBRadioButton));
		act.moveToElement(MVCBRadioButton).click().build().perform();
		logs.info("Click on radio Button");
		
		//Extract Order Number 
		WebElement MVCBOrderID = isElementPresent("MVCB_OrderID_xpath");
		wait.until(ExpectedConditions.visibilityOf(MVCBOrderID));
		String OrderID=MVCBOrderID.getText();
		this.OrderID=OrderID;
		logs.info("Order ID Is:="+OrderID);
		logs.info("Order ID Extracted");
		
		
		
		// Click on Verify
		WebElement MVCBVerifyBttn = isElementPresent("MVCB_Verify_id");
		wait.until(ExpectedConditions.visibilityOf(MVCBVerifyBttn));
		wait.until(ExpectedConditions.elementToBeClickable(MVCBVerifyBttn));
		act.moveToElement(MVCBVerifyBttn).click().build().perform();
		logs.info("Click on Verify Button");

		
		
		//Search Order ID
		SearchORDERID();
		
	   //Order Status 
		WebElement MVCBOrderStatus= isElementPresent("MVCB_OrderStatus_id");
		wait.until(ExpectedConditions.visibilityOf(MVCBOrderStatus));
		String OrderStatus=MVCBOrderStatus.getText();
		logs.info("Order Status Is:="+OrderStatus);
		
		// Screen Shot
		getScreenshot(driver, "MVCB_VerifyOrder");
		Thread.sleep(2500);
		
		
		if(OrderStatus.equals("VERIFIED")) {
			
			logs.info("Verify Order:=Passed");
			msg.append("Verify Order=Pass" + "\n");
		}
		
		else{
			
			logs.info("Verify Order:=Failed");
			msg.append("Verify Order=Failed" + "\n");
		}
		

	}

	public void SearchORDERID() throws InterruptedException {

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

		// Click on SPL
		WebElement SPL = driver.findElement(By.xpath("//a[@id='a_inventory']"));
		wait.until(ExpectedConditions.visibilityOf(SPL));
		wait.until(ExpectedConditions.elementToBeClickable(SPL));
		jse.executeScript("arguments[0].click();", SPL);
		logs.info("Click on SPL");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		Thread.sleep(1500);
		// Click on Search All WO
		WebElement SearchAllWO = driver.findElement(By.linkText("Search All WO"));
		wait.until(ExpectedConditions.visibilityOf(SearchAllWO));
		wait.until(ExpectedConditions.elementToBeClickable(SearchAllWO));
       act.moveToElement(SearchAllWO).build().perform();
   	    jse.executeScript("arguments[0].click();", SearchAllWO);
		logs.info("Click on Search All WO");
	
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		Thread.sleep(2000);
		
      //Order 
		WebElement Order= driver.findElement(By.xpath("//input[@id='txtOrderNo']"));
		wait.until(ExpectedConditions.visibilityOf(Order));	
		Order.clear();
		Order.sendKeys(OrderID);
		logs.info("Order ID Entered in Search Box");
		
		// Click on Search
		WebElement SearchButton = driver.findElement(By.id("btnSearch"));
		wait.until(ExpectedConditions.visibilityOf(SearchButton));
		wait.until(ExpectedConditions.elementToBeClickable(SearchButton));
		jse.executeScript("arguments[0].click();", SearchButton);
		logs.info("Click on Search");
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		Thread.sleep(2000);
		
		
	}

}
