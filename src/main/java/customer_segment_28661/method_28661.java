package customer_segment_28661;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import connect_BasePackage.BaseInit;
import connect_BasePackage.Email;

public class method_28661 extends BaseInit {

	public void cust_detail_page(int i) throws EncryptedDocumentException, InvalidFormatException, IOException {

		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
		Actions act = new Actions(driver);
		// --Go to customer
		WebElement customer = isElementPresent("custmenu_id");
		wait.until(ExpectedConditions.visibilityOf(customer));
		wait.until(ExpectedConditions.elementToBeClickable(customer));
		act.moveToElement(customer).click().build().perform();
		logs.info("Click on customer Menu");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// --Go to Customer section
		WebElement custsection = isElementPresent("custsection_xpath");
		wait.until(ExpectedConditions.visibilityOf(custsection));
		wait.until(ExpectedConditions.elementToBeClickable(custsection));
		act.moveToElement(custsection).click().build().perform();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		logs.info("Click on Customer section");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// -- Enter customer account no

		String cust_account = getData("address", 1, 2);
		System.out.println("Customer account is : " + cust_account);

		WebElement c_account = isElementPresent("cust_ac_id");
		c_account.sendKeys(cust_account);
		logs.info("Entered Customer Account no is " + cust_account);
		msg.append("Segment verification is perform for Account no : " + cust_account + "\n");

		// -- Click on Search button
		WebElement search_customer = isElementPresent("CustSearchBTN_id");
		act.moveToElement(search_customer).click().build().perform();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// -- fetch and store Customer account from Customer detail screen

		String acc = (String) jse.executeScript("return document.getElementById('txtAccount').value");
		logs.info("Fetched Account no is :" + acc);
		msg.append("Customer Account is : " + acc + "\n");

		setData("Test_Case", 1, 4, acc);
		getScreenshot(driver, "cust-detail_segment");
	}

	public static String getData(String sheetName, int row, int col)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		String Env = storage.getProperty("Env");
		String FilePath = null;
		if (Env.equalsIgnoreCase("Pre-Prod")) {
			FilePath = storage.getProperty("PrePRODFile");
		} else if (Env.equalsIgnoreCase("STG")) {
			FilePath = storage.getProperty("28661_cust_segment_STGFile");
		} else if (Env.equalsIgnoreCase("DEV")) {
			FilePath = storage.getProperty("DEVFile");
		} else if (Env.equalsIgnoreCase("TEST")) {
			FilePath = storage.getProperty("28661_cust_segment_TESTFile");
		}

		File src = new File(FilePath);

		FileInputStream FIS = new FileInputStream(src);
		Workbook workbook = WorkbookFactory.create(FIS);
		Sheet sh1 = workbook.getSheet(sheetName);

		DataFormatter formatter = new DataFormatter();
		String Cell = formatter.formatCellValue(sh1.getRow(row).getCell(col));
		FIS.close();
		return Cell;
	}

	public static void setData(String sheetName, int row, int col, String value)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String Env = storage.getProperty("Env");
		String FilePath = null;
		if (Env.equalsIgnoreCase("Pre-Prod")) {
			FilePath = storage.getProperty("PrePRODFile");
		} else if (Env.equalsIgnoreCase("STG")) {
			FilePath = storage.getProperty("28661_cust_segment_STGFile");
		} else if (Env.equalsIgnoreCase("DEV")) {
			FilePath = storage.getProperty("DEVFile");
		} else if (Env.equalsIgnoreCase("TEST")) {
			FilePath = storage.getProperty("28661_cust_segment_TESTFile");
		}

		File src = new File(FilePath);
		FileInputStream fis = new FileInputStream(src);
		Workbook workbook = WorkbookFactory.create(fis);
		FileOutputStream fos1 = new FileOutputStream(src);
		Sheet sh = workbook.getSheet(sheetName);

		sh.getRow(row).createCell(col).setCellValue(value);
		workbook.write(fos1);
		fos1.close();
		fis.close();
	}

	public void orderCreation(int i)
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException, InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
		Actions act = new Actions(driver);

		Robot r = new Robot();
		String ServiceID = null;
		try {
			// --Go to Operation
			WebElement operation = isElementPresent("OperMenu_id");
			wait.until(ExpectedConditions.visibilityOf(operation));
			wait.until(ExpectedConditions.elementToBeClickable(operation));
			act.moveToElement(operation).click().build().perform();
			logs.info("Click on Operation");

			// --Go to TaskLog
			WebElement TaskLog = isElementPresent("OpTaskLog_id");
			wait.until(ExpectedConditions.visibilityOf(TaskLog));
			wait.until(ExpectedConditions.elementToBeClickable(TaskLog));
			act.moveToElement(TaskLog).click().build().perform();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtContains")));
			logs.info("Click on Tasklog");

			// --Click on New Order
			WebElement Order = isElementPresent("NewOrder_id");
			wait.until(ExpectedConditions.visibilityOf(Order));
			wait.until(ExpectedConditions.elementToBeClickable(Order));
			jse.executeScript("arguments[0].click();", Order);
			logs.info("Click on New Order");

			// --Waiting for Order section
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("idOrder")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --get the Data
			getData("address", i, 0);

			// Enter Caller
			String Caller = getData("address", i, 0);
			isElementPresent("OCCallerName_id").clear();
			isElementPresent("OCCallerName_id").sendKeys(Caller);
			logs.info("Entered CallerName");

			// Enter Phone
			String Phone = getData("address", i, 1);
			isElementPresent("OCContactPh_id").clear();
			isElementPresent("OCContactPh_id").sendKeys(Phone);
			logs.info("Entered Contact/Phone");

			// Enter Account#
			String Account = getData("address", i, 2);
			isElementPresent("OCCustCode_id").clear();
			isElementPresent("OCCustCode_id").sendKeys(Account);
			isElementPresent("OCCustCode_id").sendKeys(Keys.TAB);
			logs.info("Entered Customer Code");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// Enter Pickup Zip code
			String PUZip = getData("address", i, 3);
			isElementPresent("OCPUZp_id").clear();
			isElementPresent("OCPUZp_id").sendKeys(PUZip);
			isElementPresent("OCPUZp_id").sendKeys(Keys.TAB);
			Thread.sleep(2000);
			logs.info("Entered PU Zip");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --PU Address
			WebElement Puaddr = isElementPresent("OCPUAdd_id");
			wait.until(ExpectedConditions.elementToBeClickable(Puaddr));
			jse.executeScript("arguments[0].click();", Puaddr);
			logs.info("Click on PU Address");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --PU Company
			String PickupCom = getData("address", i, 4);
			isElementPresent("OCPUComp_id").clear();
			isElementPresent("OCPUComp_id").sendKeys(PickupCom);
			logs.info("Entered PU Company");

			// --PU AddressLine1
			String PUAddress1 = getData("address", i, 5);
			isElementPresent("OCPUAddL1_id").clear();
			isElementPresent("OCPUAddL1_id").sendKeys(PUAddress1);
			logs.info("Entered PU AddressLine1");

			// String Add2 = getData("address", i, 6);
			// driver.findElement(By.id("txtPUAddrLine2")).sendKeys(Add2);

			// --PU Attention
			String Attn = getData("address", i, 7);
			isElementPresent("OCPUAtt_id").clear();
			isElementPresent("OCPUAtt_id").sendKeys(Attn);
			logs.info("Entered PU Attention");

			// --PU Phone
			String PuPhone = getData("address", i, 8);
			isElementPresent("OCPUPhone_id").clear();
			isElementPresent("OCPUPhone_id").sendKeys(PuPhone);
			logs.info("Entered PU Phone");

			// --Del Zip
			String DLZip = getData("address", i, 11);
			isElementPresent("OCDLZip_id").clear();
			isElementPresent("OCDLZip_id").sendKeys(DLZip);
			isElementPresent("OCDLZip_id").sendKeys(Keys.TAB);
			Thread.sleep(2000);
			logs.info("Entered DL Zip");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Del Address
			WebElement DL = isElementPresent("OCDLAdd_id");
			wait.until(ExpectedConditions.elementToBeClickable(DL));
			jse.executeScript("arguments[0].click();", DL);
			logs.info("Entered DL Address");

			// --DEL Company
			String DelCompany = getData("address", i, 12);
			isElementPresent("OCDLComp_id").clear();
			isElementPresent("OCDLComp_id").sendKeys(DelCompany);
			logs.info("Entered DL Company");

			// --DEL Address1
			String DLAddress1 = getData("address", i, 13);
			isElementPresent("OCDLAddL1_id").clear();
			isElementPresent("OCDLAddL1_id").sendKeys(DLAddress1);
			logs.info("Entered DL Address Line1");

			// String DLAddr2 = getData("address", i, 14);
			// driver.findElement(By.id("txtDelAddrLine2")).sendKeys(DLAddr2);

			// --DL Attention
			String DLAttn = getData("address", i,15);
			isElementPresent("OCDLAtt_id").clear();
			isElementPresent("OCDLAtt_id").sendKeys(DLAttn);
			logs.info("Entered DL Attention");

			// --DL Phone
			String DLPhone = getData("address", i, 16);
			isElementPresent("OCDLPhone_id").clear();
			isElementPresent("OCDLPhone_id").sendKeys(DLPhone);
			logs.info("Entered DL Phone");

			// --Total Qty
			isElementPresent("OCTotalQty_id").clear();
			// isElementPresent("OCTotalQty_id").sendKeys(Keys.BACK_SPACE);
			isElementPresent("OCTotalQty_id").sendKeys(Keys.CONTROL, "a");
			isElementPresent("OCTotalQty_id").sendKeys(Keys.BACK_SPACE);
			isElementPresent("OCTotalQty_id").clear();
			isElementPresent("OCTotalQty_id").clear();
			isElementPresent("OCTotalQty_id").sendKeys("01");
			isElementPresent("OCTotalQty_id").sendKeys(Keys.TAB);
			Thread.sleep(2000);
			logs.info("Entered Total Qty");

			// --Weight
			String Weight = getData("address", i, 19);
			isElementPresent("OCWeight_id").clear();
			isElementPresent("OCWeight_id").sendKeys(Weight);
			isElementPresent("OCWeight_id").sendKeys(Keys.TAB);
			logs.info("Entered Weight");

			// --Length
			String Len = getData("address", i, 20);
			isElementPresent("OCLength_id").clear();
			isElementPresent("OCLength_id").sendKeys(Len);
			isElementPresent("OCLength_id").sendKeys(Keys.TAB);
			logs.info("Entered Length");

			// --Width
			String Width = getData("address", i, 21);
			isElementPresent("OCWidth_id").clear();
			isElementPresent("OCWidth_id").sendKeys(Width);
			isElementPresent("OCWidth_id").sendKeys(Keys.TAB);
			logs.info("Entered Width");

			// --Height
			String Height = getData("address", i, 22);
			isElementPresent("OCHeight_id").clear();
			isElementPresent("OCHeight_id").sendKeys(Height);
			isElementPresent("OCHeight_id").sendKeys(Keys.TAB);
			logs.info("Entered Height");

			// --Commodity
			String Commodity = getData("address", i, 23);
			isElementPresent("OCDesc_id").clear();
			isElementPresent("OCDesc_id").sendKeys(Commodity);
			isElementPresent("OCDesc_id").sendKeys(Keys.TAB);
			logs.info("Entered Commodity");

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			getScreenshot(driver, "order-data_segment");

			// --Click on Create Order button
			WebElement order = isElementPresent("OCOProcess_id");
			jse.executeScript("arguments[0].scrollIntoView();", order);
			Thread.sleep(2000);
			act.moveToElement(order).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(order));
			jse.executeScript("arguments[0].click();", order);
			logs.info("Click on Create Order button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(2000);

			// store Pickup id
			WebElement PickUPID = isElementPresent("OCPickuPID_xpath");
			wait.until(ExpectedConditions.visibilityOf(PickUPID));
			wait.until(ExpectedConditions.elementToBeClickable(PickUPID));
			String pck = PickUPID.getText();
			System.out.println("Pickup = " + pck);
			logs.info("=====Pickup =" + pck + "=====" + "\n");
			msg.append("Pickup :" + pck + "\n");

			// --Set PickUPID
			setData("address", i, 24, pck);
			setData("Test_Case", 2, 6, pck);
			getScreenshot(driver, "pickupID_segment");

			// --Click on Edit Order
			WebElement EditOrder = isElementPresent("OCEditOrder_id");
			wait.until(ExpectedConditions.elementToBeClickable(EditOrder));
			EditOrder.click();
			logs.info("Clicked on Edit Order");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		} catch (Exception e) {
			logs.error(e);
			getScreenshot(driver, "CreateOrder_" + ServiceID);

			// --ScrollUp
			jse.executeScript("window.scrollBy(0,-250)", "");
			Thread.sleep(2000);
			getScreenshot(driver, "CreateOrder1_" + ServiceID);

			// --ScrollDown
			jse.executeScript("window.scrollBy(0,250)", "");
			Thread.sleep(2000);
			getScreenshot(driver, "CreateOrder2_" + ServiceID);

			System.out.println("Issue in Create Order");
			logs.info("Issue in Create Order");
		}

	}

	public void open_pickup_frm_tasklog(int i)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
		Actions act = new Actions(driver);
		WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(60));// wait time

		try {

			// --Go To Operations
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a_operations")));
			WebElement Operations = isElementPresent("OperationsTab_id");
			act.moveToElement(Operations).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(Operations));
			act.moveToElement(Operations).click().perform();
			logs.info("Clicked on Operations");
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Go to TaskLog
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a_TaskLog")));
			isElementPresent("TaskLog_id").click();
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			logs.info("Clicked on TaskLog");

			// --Enter pickUpID
			wait.until(ExpectedConditions.elementToBeClickable(By.id("txtContains")));

		//	int row_count = getTotalRow("address");
		//	int total_row = row_count - 1;
		//	System.out.println("Total row count is : " + total_row);
			String Pickupid_seg = getData("Test_Case", i, 6);
			logs.info("Use pickup id to check Segment: " + Pickupid_seg);
			
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			
			isElementPresent("TLSearch_id").clear();
			// isElementPresent("TLBasicSearch_id").clear();
			Thread.sleep(1000);
			isElementPresent("TLSearch_id").sendKeys(Pickupid_seg);
			logs.info("Pickup id entered in seach box");

			// --click on search button for pickup search
			WebElement search = isElementPresent("TLSearchButton_id");

			act.moveToElement(search).build().perform();
			Thread.sleep(500);
			act.click(search).build().perform();
			logs.info("click on search button for search pickup id from tasklog");
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		} catch (Exception e) {
			// TODO: handle exception
			logs.info("error in open tasklog due to :" + e);
		}

	}
	
	
	
	public void navigate_Edit_Job()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
		Actions act = new Actions(driver);
		WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(60));// wait time


		// -- Check whether Edit status is enabled or disabled for perform actions
		String edit_job = isElementPresent("EOEditOrderTab_id").getText();
		System.out.println("User is on : " + edit_job + " tab");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		WebElement stg_selector = isElementPresent("TLstage_selector_id");
		WebElement edit_job_tab = isElementPresent("TLEditJobtab_id");
		try {

			if (stg_selector.isDisplayed()) {
				// edit_job_tab.click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				System.out.println("User is on : " + edit_job + " tab");
				logs.info("EditJob tab is already Opened");
			}

		} catch (Exception JobStatus) {
			// -- OPen Edit Job Tab

			edit_job_tab.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			WebElement stg_selectors = isElementPresent("TLstage_selector_id");
			if (stg_selectors.isDisplayed()) {
				logs.info("Job EditJob tab is Opened");
			} else {
				logs.info("Job EditJob tab is not Opened");
			}

		}
	}

	public void SendEmail() throws Exception {
		String BaseURL = null;
		logOut();
		report.flush();
		// --Close browser
		Complete();
		System.out.println("====Sending Email=====");
		logs.info("====Sending Email=====");
		// Send Details email

		msg.append("\n" + "*** This is automated generated email and send through automation script ***" + "\n");
		msg.append("Process URL : " + BaseURL + "\n");
		msg.append("Please find attached file of Report and Log");

		String Env = storage.getProperty("Env");

		String subject = "Selenium Automation Script:" + Env + " Quick Quote Creation&Processing";
		String File = ".\\Report\\ExtentReport\\ExtentReportResults.html,.\\Report\\log\\ConnectOrderProcess.html";

		String EmailID = storage.getProperty("MainEmailAddress");
		System.out.println("MainEmailAddress " + EmailID);
		try {
//				/kunjan.modi@samyak.com, pgandhi@samyak.com,parth.doshi@samyak.com

			Email.sendMail(EmailID, subject, msg.toString(), File);

			// Email.sendMail("ravina.prajapati@samyak.com", subject, msg.toString(), File);

		} catch (Exception ex) {
			logs.error(ex);
		}
	}

}
