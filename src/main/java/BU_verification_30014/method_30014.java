package BU_verification_30014;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mongodb.diagnostics.logging.Logger;

import connect_BasePackage.BaseInit;

public class method_30014 extends BaseInit {

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
			FilePath = storage.getProperty("30014_BU_verification_STGFile");
		} else if (Env.equalsIgnoreCase("DEV")) {
			FilePath = storage.getProperty("DEVFile");
		} else if (Env.equalsIgnoreCase("TEST")) {
			FilePath = storage.getProperty("30014_BU_verification_TESTFile");
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
			FilePath = storage.getProperty("30014_BU_verification_STGFile");
		} else if (Env.equalsIgnoreCase("DEV")) {
			FilePath = storage.getProperty("DEVFile");
		} else if (Env.equalsIgnoreCase("TEST")) {
			FilePath = storage.getProperty("30014_BU_verification_TESTFile");
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
	
	// -- 
	
	public void navigate_Edit_Job()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// -- Check whether Edit status is enabled or disabled for perform actions
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		String edit_job = isElementPresent("TLEditJob_id").getText();
		System.out.println("User is on : " + edit_job + " tab");
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

	public void verify_BU_svc(int j)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		Actions act = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {

			String expected_BU_Status = getData("Test_Case", j, 5);
			logs.info("Expected Bu status is : " + expected_BU_Status);
			msg.append("Expected Bu status is : " + expected_BU_Status + "\n");

			// Actual BU Status

			WebElement BU_connect = isElementPresent("BU_id");
			Select connect_BU = new Select(isElementPresent("BU_id"));

			String connect_BU_value = connect_BU.getFirstSelectedOption().getText();
			highLighted_screenshot(BU_connect, driver, "connect_BU_value_"+j);
			logs.info("Fetched Business Unit is : " + connect_BU_value);
			msg.append("Fetched Business Unit is : " + connect_BU_value + "\n");
			setData("Test_Case", j, 7, connect_BU_value);

			// -- Expected Service

			String expected_service = getData("Test_Case", j, 6);
			logs.info("Expected Service is : " + expected_service);
			msg.append("Expected Service is : " + expected_service + "\n");

			// -- ACTual Fetched Service

			WebElement service = isElementPresent("TLServID_id");
			String connect_service = service.getText();
			logs.info("Actual fetched Service is : " + connect_service);
			msg.append("Actual fetched Service is : " + connect_service + "\n");
			setData("Test_Case", j, 8, connect_service);

			if ((expected_BU_Status.equalsIgnoreCase(connect_BU_value))
					&& (expected_service.equalsIgnoreCase(connect_service))) {
				setData("Test_Case", j, 3, "PASS");
				logs.info("Service and BU is as expected = PASS");
				msg.append("Service and BU is as expected = PASS" + "\n");

			}

		}

		catch (Exception e) {
			// TODO: handle exception
			setData("Test_Case", j, 3, "FAIL");
			logs.info("Service and BU is as expected = FAIL");
			msg.append("Service and BU is as expected = FAIL" + "\n");
		}
	}
	
	public static void highLighted_screenshot(WebElement element, WebDriver driver, String screenshotName)
			throws IOException {
		// for (int i = 0; i < 2; i++) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: black; border: 4px solid red;");
			Thread.sleep(500);
			getScreenshot(driver, screenshotName);

			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// }

	}

	public void open_pickup_frm_tasklog(int i)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(15));
		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(25));
		Actions act = new Actions(driver);

		try {

			// String return_Pickup_id = null;

			// --Go To Operations
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a_operations")));
			WebElement Operations = isElementPresent("OperationsTab_id");
			act.moveToElement(Operations).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(Operations));
			act.moveToElement(Operations).click().perform();
			logs.info("Clicked on Operations");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Go to TaskLog
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a_TaskLog")));
			isElementPresent("TaskLog_id").click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			logs.info("Clicked on TaskLog");

			// --Enter pickUpID
			wait.until(ExpectedConditions.elementToBeClickable(By.id("txtContains")));

			String return_Pickup_id = getData("Test_Case", i, 4);
			logs.info("Return order Pickup ID: " + return_Pickup_id);

			isElementPresent("TLBasicSearch_id").clear();
			// isElementPresent("TLBasicSearch_id").clear();
			isElementPresent("TLBasicSearch_id").sendKeys(return_Pickup_id);

			logs.info("Entered PickUpID in basic search");

			WebElement search = isElementPresent("TLSearchButton_id");

			// --Click on Search

			act.moveToElement(search).build().perform();
			Thread.sleep(500);
			act.click(search).build().perform();
			logs.info("CLick on Search button of tasklog");
			wait3.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			try {

				List<WebElement> total_result = driver.findElements(By.xpath("//span[@class='dx-checkbox-icon']"));
				int size = total_result.size();
				// String s=String.valueOf(size);
				if (size > 1) {
					WebElement pickup_id_task = driver
							.findElement(By.xpath("//label[contains(text(),'" + return_Pickup_id + "')]"));
					act.moveToElement(pickup_id_task).build().perform();
					Thread.sleep(500);
					act.click(pickup_id_task).build().perform();
					logs.info("Appropriate Job selected from multiple jobs");
					wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				}

			}

			catch (Exception e) {
				// click on pickup id when multiple job visible for entered pickup id

				// List<WebElement> total_result =
				// driver.findElements(By.xpath("//span[@class='dx-checkbox-icon']"));

				logs.info("Multiple job not visible for entered pickup id in taskbar"+e);

			}

		} catch (Exception e) {
			// TODO: handle exception

			logs.info("error in  Open PickUP from Tasklog " + e);
			msg.append("error in  Open PickUP from Tasklog " + "\n");
		}
	}
}
