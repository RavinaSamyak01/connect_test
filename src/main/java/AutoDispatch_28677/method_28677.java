package AutoDispatch_28677;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Key;

import connect_BasePackage.BaseInit;
import connect_BasePackage.Email;

public class method_28677 extends BaseInit {

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
		getScreenshot(driver, "cust-detail_auto-dispatch");
	}

	public static String getData(String sheetName, int row, int col)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		String Env = storage.getProperty("Env");
		String FilePath = null;
		if (Env.equalsIgnoreCase("Pre-Prod")) {
			FilePath = storage.getProperty("PrePRODFile");
		} else if (Env.equalsIgnoreCase("STG")) {
			FilePath = storage.getProperty("28677_auto_dispatch_STGFile");
		} else if (Env.equalsIgnoreCase("DEV")) {
			FilePath = storage.getProperty("DEVFile");
		} else if (Env.equalsIgnoreCase("TEST")) {
			FilePath = storage.getProperty("28677_auto_dispatch_TESTFile");
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

	public String getTimeAsTZone(String timeZone) {

		System.out.println("ZoneID of is==" + timeZone);
		logs.info("ZoneID of is==" + timeZone);
		if (timeZone.equalsIgnoreCase("EDT")) {
			timeZone = "America/New_York";
		} else if (timeZone.equalsIgnoreCase("CDT")) {
			timeZone = "CST";
		} else if (timeZone.equalsIgnoreCase("PDT")) {
			timeZone = "PST";
		} else if (timeZone.equalsIgnoreCase("MDT")) {
			timeZone = "America/Denver";
		}

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
		logs.info(dateFormat.format(date));
		String Time = dateFormat.format(date);
		System.out.println("Time==" + Time);

		return Time;

	}

	public static void setData(String sheetName, int row, int col, String value)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String Env = storage.getProperty("Env");
		String FilePath = null;
		if (Env.equalsIgnoreCase("Pre-Prod")) {
			FilePath = storage.getProperty("PrePRODFile");
		} else if (Env.equalsIgnoreCase("STG")) {
			FilePath = storage.getProperty("28677_auto_dispatch_STGFile");
		} else if (Env.equalsIgnoreCase("DEV")) {
			FilePath = storage.getProperty("DEVFile");
		} else if (Env.equalsIgnoreCase("TEST")) {
			FilePath = storage.getProperty("28677_auto_dispatch_TESTFile");
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

			getScreenshot(driver, "order-data_auto-dispatch");

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

			try {
				boolean sameairport = driver.getPageSource()
						.contains("Pickup and Delivery airport are different. Do you want to make it same?");

				if (sameairport == true) {
					logs.info("PopUp message is displayed for Same Airport");
					WebElement Yes = isElementPresent("OCSameApPupYes_xpath");
					wait.until(ExpectedConditions.elementToBeClickable(Yes));
					jse.executeScript("arguments[0].click();", Yes);
					logs.info("Clicked on YES button of popup");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				}
			} catch (Exception eee) {

				logs.info("not able to make same airport" + eee);

			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
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
			setData("Test_Case", 1, 4, pck);
			getScreenshot(driver, "pickupID_auto-dispatch");

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

			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
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

			// int row_count = getTotalRow("address");
			// int total_row = row_count - 1;
			// System.out.println("Total row count is : " + total_row);
			String Pickupid_seg = getData("Test_Case", i, 4);
			logs.info("Use pickup id to check Segment: " + Pickupid_seg);

			isElementPresent("TLSearch_id").clear();
			// isElementPresent("TLBasicSearch_id").clear();
			isElementPresent("TLSearch_id").sendKeys(Pickupid_seg);

			// --click on search button for pickup search
			WebElement search = isElementPresent("TLSearchButton_id");

			act.moveToElement(search).build().perform();
			Thread.sleep(500);
			act.click(search).build().perform();
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		} catch (Exception e) {
			// TODO: handle exception
			logs.info("error in open tasklog due to :" + e);
		}

	}

	public void open_jobID_frm_tasklog(int i)
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

			// --Enter Job ID
			wait.until(ExpectedConditions.elementToBeClickable(By.id("txtContains")));

			// int row_count = getTotalRow("address");
			// int total_row = row_count - 1;
			// System.out.println("Total row count is : " + total_row);
			String job_id = getData("Test_Case", i, 6);
			logs.info("Use pickup id to check Segment: " + job_id);

			isElementPresent("TLSearch_id").clear();
			// isElementPresent("TLBasicSearch_id").clear();
			isElementPresent("TLSearch_id").sendKeys(job_id);

			// --click on search button for pickup search
			WebElement search = isElementPresent("TLSearchButton_id");

			act.moveToElement(search).build().perform();
			Thread.sleep(500);
			act.click(search).build().perform();
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			try {

				List<WebElement> total_result = driver.findElements(By.xpath("//span[@class='dx-checkbox-icon']"));
				int size = total_result.size();
				// String s=String.valueOf(size);

				if (size > 1) {
					WebElement pickup_id_task = driver
							.findElement(By.xpath("//label[contains(text(),'" + job_id + "'])"));
					act.moveToElement(pickup_id_task).build().perform();
					Thread.sleep(500);
					getScreenshot(driver, "multiple_data_tasklog");
					act.click(pickup_id_task).build().perform();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				}

			}

			catch (Exception e) {

				logs.info("Multiple job not visible for entered pickup id in taskbar");

			}

		} catch (Exception e) {
			// TODO: handle exception
			logs.info("error in open tasklog due to :" + e);
		}

	}

	public void validate_autodispatch(int i) throws EncryptedDocumentException, InvalidFormatException, IOException {
		String return_Pickup_id = getData("Test_Case", i, 4);
		logs.info("Return order Pickup ID: " + return_Pickup_id);

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("a_operations")));

			Actions act = new Actions(driver);
			WebElement Operations = isElementPresent("OperationsTab_id");
			act.moveToElement(Operations).click().perform();
			logs.info("Clicked on Operations");

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("a_TaskLog")));

			WebElement taskLog = isElementPresent("TaskLog_id");
			taskLog.click();
			logs.info("Clicked on TaskLog");

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("txtContains")));

			String Pickup_id = getData("Test_Case", i, 4);

			isElementPresent("TLBasicSearch_id").clear();

			isElementPresent("TLBasicSearch_id").sendKeys(Pickup_id);
			logs.info("PickUpID==" + Pickup_id);
			logs.info("Entered PickUpID in basic search");
			// msg.append("PickUpID==" + Pickup_id + "\n");
			logs.info("PickUpID==" + Pickup_id + "\n");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Click on Search
			WebElement search = isElementPresent("TLSearchButton_id");

			act.moveToElement(search).build().perform();
			Thread.sleep(500);
			act.click(search).build().perform();
			logs.info("CLick on Search button on Task log");

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			try {

				List<WebElement> total_result = driver.findElements(By.xpath("//span[@class='dx-checkbox-icon']"));
				int size = total_result.size();
				// String s=String.valueOf(size);

				for (int job = 0; job < size; job++) {

					String PickupID = total_result.get(job).getText();

					String[] PickValue = PickupID.split("N");

					String PickID = PickValue[1];

					logs.info("Searched PickUpID==" + PickID);

					if (PickID.equalsIgnoreCase(Pickup_id)) {

						total_result.get(job).click();

						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

						break;

					}
				}
			}

			catch (Exception e) {

				logs.info("Multiple job not visible for entered pickup id in taskbar" + e);

			}

			String Agent_id = getData("Test_Case", i, 7);
			logs.info("Agent ID Expected is :" + Agent_id);
			msg.append("Agent ID Expected is :" + Agent_id + "\n");
			Thread.sleep(2000);
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[@id='lblAgentId'])[2]")));

			// -- navigate to Job Status Tab
			try {
				WebElement job_status_tab = isElementPresent("TLJobStatusTab_id");
				act.moveToElement(job_status_tab).build().perform();
				act.click(job_status_tab).build().perform();
				wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[@id='lblAgentId'])[2]")));

				// --validate agent id , name and job status

				String fetch_agent_id = isElementPresent("PDAgentID_xpath").getText();
				// String service = (String) jse.executeScript("return
				// document.getElementById('txtServiceType').value");
				logs.info("Agent ID actaual is : " + fetch_agent_id);
				msg.append("Agent ID actaual is :" + fetch_agent_id + "\n");
				// -- fetch agent NAme and compare that it same as we config in auto disppatch
				// (currently we set agent name in "RTE auto dispatch" sheet

				String Agent_name = getData("Test_Case", i, 8);
				logs.info("Agent Name Expected is :" + Agent_name);
				msg.append("Agent Name Expected is :" + Agent_name + "\n");
				Thread.sleep(1000);

				String fetch_agent_name = isElementPresent("PDAgentName_id").getText();
				logs.info("Agent Name Actual is :" + fetch_agent_name);
				msg.append("Agent Name Actual is : " + fetch_agent_name + "\n");
				Thread.sleep(1000);

				String jobStatus = isElementPresent("TLStageLable_id").getText();
				logs.info("Job status is==" + jobStatus);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				Thread.sleep(1000);

				if (jobStatus.equalsIgnoreCase("Confirm Pu Alert") && Agent_name.equalsIgnoreCase(fetch_agent_name)
						&& Agent_id.equalsIgnoreCase(fetch_agent_id)) {

					logs.info("stage == " + jobStatus + ",  proceed with  Auto Dispatch configuaration");
					setData("Test_Case", i, 5, jobStatus);
					setData("Test_Case", i, 3, "PASS");
					getScreenshot(driver, "job_status_auto-dispatch");
					msg.append("stage == " + jobStatus + "\n" + "Pickup ID : " + Pickup_id
							+ " Proceed with  Auto Dispatch == PASS" + "\n");
					logs.info("Created job is Auto Dispatch == PASS");
					msg.append("Created job is Auto Dispatch == PASS" + "\n");

				}

				else {

					logs.info(" Pickup ID : " + Pickup_id + " not proceed with  Auto Dispatch");
					msg.append("stage == " + jobStatus + "\n" + "Pickup ID : " + Pickup_id
							+ " Proceed with  Auto Dispatch == FAIL" + "\n");
					getScreenshot(driver, "job_status_auto-dispatch");
					setData("Test_Case", i, 3, "FAIL");
					msg.append("Job is proceed with  Auto Dispatch == FAIL" + "\n");

				}
			} catch (Exception e) {
				getScreenshot(driver, "job_status_auto-dispatch");
				setData("Test_Case", i, 3, "FAIL");
				logs.info(" Pickup ID is not proceed with  Auto Dispatch");
				msg.append("Job is proceed with  Auto Dispatch == FAIL" + "\n");
			}

		} catch (Exception e) {
			logs.info("Error in opening pickup from Tasklog: " + e);
			msg.append("Error in opening pickup from Tasklog: " + "\n");
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

	public void nav_shipment_creation_page()
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

			// --Go to Shipment template
			WebElement ship_template = isElementPresent("ship_template_id");
			wait.until(ExpectedConditions.visibilityOf(ship_template));
			wait.until(ExpectedConditions.elementToBeClickable(ship_template));
			act.moveToElement(ship_template).click().build().perform();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			logs.info("Click on Shipment Template");

			// --Click on New Template
			WebElement new_template = isElementPresent("ship_new_temp_xpath");
			wait.until(ExpectedConditions.visibilityOf(new_template));
			wait.until(ExpectedConditions.elementToBeClickable(new_template));
			jse.executeScript("arguments[0].click();", new_template);
			logs.info("Click on New Template");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			WebElement cust_acc = isElementPresent("ship_cust_acc_xpath");
			if (cust_acc.isDisplayed()) {
				logs.info("User is on New Template page");
			}
		} catch (Exception e) {
			// TODO: handle exception
			logs.info("User is not on  New Template page" + e);
		}
	}

	public void ship_order_create(int i)
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException, InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
		Actions act = new Actions(driver);

		Robot r = new Robot();
		String ServiceID = null;
		try {
			// eneter customert Acc
			WebElement cust_acc = isElementPresent("ship_cust_acc_xpath");
			String cust_acc_no = getData("address", i, 2);
			logs.info("Customer Account no is :" + cust_acc_no);
			cust_acc.clear();
			cust_acc.sendKeys(cust_acc_no, Keys.TAB);
			logs.info("Customer Account no is :" + cust_acc_no + "  : entered");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// Enter Pickup Zip code
			String PUZip = getData("address", i, 3);
			isElementPresent("ship_pu_zip_xpath").clear();
			isElementPresent("ship_pu_zip_xpath").sendKeys(PUZip);
			isElementPresent("ship_pu_zip_xpath").sendKeys(Keys.TAB);
			Thread.sleep(2000);
			logs.info("Entered PU Zip on new template");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --PU Company
			String PickupCom = getData("address", i, 4);
			isElementPresent("ship_pu_comp_id").clear();
			isElementPresent("ship_pu_comp_id").sendKeys(PickupCom);
			logs.info("Entered PU Company");

			// --PU Address
			String PUAddress1 = getData("address", i, 5);
			WebElement Puaddr = isElementPresent("ship_pu_add_xpath");
			wait.until(ExpectedConditions.elementToBeClickable(Puaddr));
			isElementPresent("ship_pu_add_xpath").sendKeys(PUAddress1);
			logs.info("Click on PU Address");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --PU Attention
			String Attn = getData("address", i, 7);
			isElementPresent("ship_pu_att_id").clear();
			isElementPresent("ship_pu_att_id").sendKeys(Attn);
			logs.info("Entered PU Attention");

			// --PU Phone
			String PuPhone = getData("address", i, 8);
			isElementPresent("ship_pu_ph_no_id").clear();
			isElementPresent("ship_pu_ph_no_id").sendKeys(PuPhone);
			logs.info("Entered PU Phone");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Ready for Pickup
			isElementPresent("ship_rdy_pickup_id").clear();
			isElementPresent("ship_rdy_pickup_id").sendKeys("0005", Keys.TAB);
			logs.info("Entered Ready for Pickup Time");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Quote for Pickup
			isElementPresent("ship_quote_pickup_id").clear();
			isElementPresent("ship_quote_pickup_id").sendKeys("0010", Keys.TAB);
			logs.info("Entered Quote for Pickup Time");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// -- happens on

			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
			Date date = new Date();
			String date1 = dateFormat.format(date);
			logs.info("Current Date :- " + date1);
			isElementPresent("ship_pu_happenson_id").clear();
			isElementPresent("ship_pu_happenson_id").sendKeys(date1, Keys.TAB);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// -- happens at
			// String tz= isElementPresent("ship_timzone_id").getText();
			isElementPresent("ship_pu_happen_AT_id").clear();
			String zone = getTimeAsTZone("CST");
			isElementPresent("ship_pu_happen_AT_id").sendKeys(zone);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Del Zip
			String DLZip = getData("address", i, 11);
			isElementPresent("ship_del_zip_xpath").clear();
			isElementPresent("ship_del_zip_xpath").sendKeys(DLZip);
			isElementPresent("ship_del_zip_xpath").sendKeys(Keys.TAB);
			Thread.sleep(1000);
			logs.info("Entered DL Zip");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --DEL Company
			String DelCompany = getData("address", i, 12);
			isElementPresent("ship_del_comp_id").clear();
			isElementPresent("ship_del_comp_id").sendKeys(DelCompany);
			logs.info("Entered DL Company");

			// --Del Address
			String DLAddress1 = getData("address", i, 13);
			WebElement DL = isElementPresent("ship_del_add_xpath");
			wait.until(ExpectedConditions.elementToBeClickable(DL));
			isElementPresent("ship_del_add_xpath").sendKeys(DLAddress1);
			logs.info("Entered DL Address");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --DL Attention
			String DLAttn = getData("address", i,15);
			isElementPresent("ship_del_att_id").clear();
			isElementPresent("ship_del_att_id").sendKeys(DLAttn);
			logs.info("Entered DL Attention");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --DL Phone
			String DLPhone = getData("address", i, 16);
			isElementPresent("ship_del_ph_no_id").clear();
			isElementPresent("ship_del_ph_no_id").sendKeys(DLPhone);
			logs.info("Entered DL Phone");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// -- Estimate Delivery time
			isElementPresent("ship_est_del_time_id").clear();
			isElementPresent("ship_est_del_time_id").sendKeys("0810", Keys.TAB);
			logs.info("Entered Delivery time");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Total Qty
			isElementPresent("ship_qty_id").clear();
			// isElementPresent("OCTotalQty_id").sendKeys(Keys.BACK_SPACE);
			isElementPresent("ship_qty_id").sendKeys(Keys.CONTROL, "a");
			isElementPresent("ship_qty_id").sendKeys(Keys.BACK_SPACE);
			isElementPresent("ship_qty_id").sendKeys("01");
			isElementPresent("ship_qty_id").sendKeys(Keys.TAB);
			Thread.sleep(2000);
			logs.info("Entered Total Qty");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Weight
			String Weight = getData("address", i, 19);
			isElementPresent("ship_weight_id").clear();
			isElementPresent("ship_weight_id").sendKeys(Weight);
			isElementPresent("ship_weight_id").sendKeys(Keys.TAB);
			logs.info("Entered Weight");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Length
			String Len = getData("address", i, 20);
			isElementPresent("ship_length_id").clear();
			isElementPresent("ship_length_id").sendKeys(Len, Keys.TAB);
			logs.info("Entered Length");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Width
			String Width = getData("address", i, 21);
			isElementPresent("ship_width_id").clear();
			isElementPresent("ship_width_id").sendKeys(Width);
			isElementPresent("ship_width_id").sendKeys(Keys.TAB);
			logs.info("Entered Width");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Height
			String Height = getData("address", i, 22);
			isElementPresent("ship_height_id").clear();
			isElementPresent("ship_height_id").sendKeys(Height);
			isElementPresent("ship_height_id").sendKeys(Keys.TAB);
			logs.info("Entered Height");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Commodity
			String Commodity = getData("address", i, 23);
			isElementPresent("ship_commodity_id").clear();
			isElementPresent("ship_commodity_id").sendKeys(Commodity);
			isElementPresent("ship_commodity_id").sendKeys(Keys.TAB);
			logs.info("Entered Commodity");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(100);
			// -- select vehicle
			WebElement vehicle_select = isElementPresent("ship_select_car_id");

			wait.until(ExpectedConditions.elementToBeClickable(vehicle_select));
			vehicle_select.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// -- select template valid from

//			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
//			Date date = new Date();
//			String date1 = dateFormat.format(date);
			System.out.println("Current Date :- " + date1);
			isElementPresent("ship_valid_from_id").clear();
			isElementPresent("ship_valid_from_id").sendKeys(date1, Keys.TAB);

			// -- select template Valid to

			System.out.println("Current Date :- " + date1);
			isElementPresent("ship_valid_to_id").clear();
			isElementPresent("ship_valid_to_id").sendKeys(date1, Keys.TAB);

			getScreenshot(driver, "order-data_auto-dispatch_shipment");

			// --Click on generate job button
			WebElement generate_job = isElementPresent("ship_generate_job_id");
			jse.executeScript("arguments[0].scrollIntoView();", generate_job);
			Thread.sleep(1000);
			act.moveToElement(generate_job).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(generate_job));
			jse.executeScript("arguments[0].click();", generate_job);
			logs.info("Click on Generate Job button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(2000);

			// store Job ID
			WebElement Job_id = isElementPresent("ship_job_msg_id");
			wait.until(ExpectedConditions.visibilityOf(Job_id));
			wait.until(ExpectedConditions.elementToBeClickable(Job_id));
			getScreenshot(driver, "job_id_tamplate");
			String pck = Job_id.getText();

			String jobID = pck.replaceAll("[^0-9]", "");
			System.out.println("JobID = " + jobID);
			logs.info("===== Job ID =" + jobID + "=====" + "\n");
			msg.append("Job ID :" + jobID + "\n");

			// --Set Job ID
			setData("address", i, 25, jobID);
			setData("Test_Case", i, 6, jobID);
			getScreenshot(driver, "pickupID_auto-dispatch");

		}

		catch (Exception e) {
			// TODO: handle exception
			logs.info("error in job create from new shipment template" + e);
			getScreenshot(driver, "issue in shipment job creation");
		}

	}
}