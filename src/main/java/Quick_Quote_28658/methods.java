package Quick_Quote_28658;

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
import org.openqa.selenium.support.ui.WebDriverWait;

import connect_BasePackage.BaseInit;
import connect_BasePackage.Email;

public class methods extends BaseInit {

	public void new_order_page_navigate(int i) throws EncryptedDocumentException, InvalidFormatException, IOException {
try {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
		Actions act = new Actions(driver);
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
	}
catch (Exception e) {
	// TODO: handle exception
	
	logs.info("not able to fetch data "+e);
	msg.append("not able to fetch data from sheet"+"\n");
}
	}
	public void refreshApp() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);

		try {
			WebElement NGLLOgo = isElementPresent("RefreshLogo_xpath");
			wait.until(ExpectedConditions.elementToBeClickable(NGLLOgo));
			act.moveToElement(NGLLOgo).build().perform();
			js.executeScript("arguments[0].click();", NGLLOgo);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		} catch (Exception refresh) {
			WebElement NGLLOgo = isElementPresent("RefreshLogo_xpath");
			wait.until(ExpectedConditions.elementToBeClickable(NGLLOgo));
			act.moveToElement(NGLLOgo).build().perform();
			act.moveToElement(NGLLOgo).click().perform();
			js.executeScript("arguments[0].click();", NGLLOgo);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
		}

	}

	public static String getData(String sheetName, int row, int col)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		String Env = storage.getProperty("Env");
		String FilePath = null;
		if (Env.equalsIgnoreCase("Pre-Prod")) {
			FilePath = storage.getProperty("PrePRODFile");
		} else if (Env.equalsIgnoreCase("STG")) {
			FilePath = storage.getProperty("28658_quick_quote_STGFile");
		} else if (Env.equalsIgnoreCase("DEV")) {
			FilePath = storage.getProperty("DEVFile");
		} else if (Env.equalsIgnoreCase("TEST")) {
			FilePath = storage.getProperty("28658_quick_quote_TESTFile");
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
			FilePath = storage.getProperty("28658_quick_quote_STGFile");
		} else if (Env.equalsIgnoreCase("DEV")) {
			FilePath = storage.getProperty("DEVFile");
		} else if (Env.equalsIgnoreCase("TEST")) {
			FilePath = storage.getProperty("28658_quick_quote_TESTFile");
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

	public void pickup_info() throws EncryptedDocumentException, InvalidFormatException, IOException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time

		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click

		// --PU Address
		WebElement Puaddr = isElementPresent("OCPUAdd_id");
		wait.until(ExpectedConditions.elementToBeClickable(Puaddr));
		jse.executeScript("arguments[0].click();", Puaddr);
		logs.info("Click on PU Address");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// --PU Company
		String PickupCom = getData("address", 5, 4);
		isElementPresent("OCPUComp_id").clear();
		isElementPresent("OCPUComp_id").sendKeys(PickupCom);
		logs.info("Entered PU Company");

		// --PU AddressLine1
		String PUAddress1 = getData("address", 5, 5);
		isElementPresent("OCPUAddL1_id").clear();
		isElementPresent("OCPUAddL1_id").sendKeys(PUAddress1);
		logs.info("Entered PU AddressLine1");

		// String Add2 = getData("address",5, 6);
		// driver.findElement(By.id("txtPUAddrLine2")).sendKeys(Add2);

		// --PU Attention
		String Attn = getData("address", 5, 7);
		isElementPresent("OCPUAtt_id").clear();
		isElementPresent("OCPUAtt_id").sendKeys(Attn);
		logs.info("Entered PU Attention");

		// --PU Phone
		String PuPhone = getData("address", 5, 8);
		isElementPresent("OCPUPhone_id").clear();
		isElementPresent("OCPUPhone_id").sendKeys(PuPhone);
		logs.info("Entered PU Phone");

		// --Getting ready PickupTime
		String rdytime = isElementPresent("OCPURTime_id").getAttribute("value");
		logs.info("PU Ready Time==" + rdytime);
		setData("address", 1, 34, rdytime);

		String rectime = isElementPresent("OCPURTime_id").getAttribute("value");
		logs.info("PU Receive Time==" + rectime);
		setData("address", 1, 35, rectime);

		String arrtime = isElementPresent("OCPURTime_id").getAttribute("value");
		logs.info("PU Arrival Time==" + arrtime);
		setData("address", 1, 36, arrtime);

		// set time in excel

		// tndrtime = driver.findElement(By.id("txtReadyforPickupTime")).getText();

		// --PU Miles
		String pmi = isElementPresent("OCPUMiles_id").getAttribute("value");
		logs.info("PU Mileage==" + pmi);

	}

	public void Delivery_info() throws EncryptedDocumentException, InvalidFormatException, IOException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time

		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click

		// --Del Address
		WebElement DL = isElementPresent("OCDLAdd_id");
		wait.until(ExpectedConditions.elementToBeClickable(DL));
		jse.executeScript("arguments[0].click();", DL);
		logs.info("Entered DL Address");

		// --DEL Company
		String DelCompany = getData("address", 5, 12);
		isElementPresent("OCDLComp_id").clear();
		isElementPresent("OCDLComp_id").sendKeys(DelCompany);
		logs.info("Entered DL Company");

		// --DEL Address1
		String DLAddress1 = getData("address", 5, 13);
		isElementPresent("OCDLAddL1_id").clear();
		isElementPresent("OCDLAddL1_id").sendKeys(DLAddress1);
		logs.info("Entered DL Address Line1");

		// String DLAddr2 = getData("address",5, 14);
		// driver.findElement(By.id("txtDelAddrLine2")).sendKeys(DLAddr2);

		// --DL Attention
		String DLAttn = getData("address", 5,15);
		isElementPresent("OCDLAtt_id").clear();
		isElementPresent("OCDLAtt_id").sendKeys(DLAttn);
		logs.info("Entered DL Attention");

		// --DL Phone
		String DLPhone = getData("address", 5, 16);
		isElementPresent("OCDLPhone_id").clear();
		isElementPresent("OCDLPhone_id").sendKeys(DLPhone);
		logs.info("Entered DL Phone");

		// --DL Miles
		String dmi = isElementPresent("OCDLMiles_id").getAttribute("value");
		logs.info("DL Miles==" + dmi);

		// --Commodity
		String Commodity = getData("address", 5, 23);
		isElementPresent("OCDesc_id").clear();
		isElementPresent("OCDesc_id").sendKeys(Commodity);
		isElementPresent("OCDesc_id").sendKeys(Keys.TAB);
		logs.info("Entered Commodity");

		getScreenshot(driver, "order-detail_quickquote");

	}

	public void craete_job()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time

		Actions act = new Actions(driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		try {
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

			WebElement PickUPID = isElementPresent("OCPickuPID_xpath");
			wait.until(ExpectedConditions.visibilityOf(PickUPID));
			wait.until(ExpectedConditions.elementToBeClickable(PickUPID));
			String pck = PickUPID.getText();
			System.out.println("Pickup = " + pck);
			logs.info("=====Pickup =" + pck + "=====" + "\n");
			msg.append("Pickup =" + pck + "\n");
			getScreenshot(driver, "Pickupid_quickquote");

			// --Set PickUPID
			setData("address", 5, 32, pck);
			setData("Test_Case", 8, 5, pck);
			setData("Test_Case", 4, 5, "Pickup Created in TC 8 for this QUick Quote is :" + pck);

		} catch (Exception e) {
			// TODO: handle exception
			setData("Test_Case", 8, 3, "FAIL");
			logs.info("not able to create order");
			getScreenshot(driver, "Pickupid-fail_quickquote");

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
//			/kunjan.modi@samyak.com, pgandhi@samyak.com,parth.doshi@samyak.com

			Email.sendMail(EmailID, subject, msg.toString(), File);

			// Email.sendMail("ravina.prajapati@samyak.com", subject, msg.toString(), File);

		} catch (Exception ex) {
			logs.error(ex);
		}
	}

}
