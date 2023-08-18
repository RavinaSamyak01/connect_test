package customer_segment_28661;

import java.awt.AWTException;
import java.awt.Robot;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class customer_segment extends BaseInit {
	//static StringBuilder msg = new StringBuilder();

	JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click

	static method_28661 mth = new method_28661();

	@Test
	public void custSegment() throws IOException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		driver.navigate().refresh();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		msg.append("\n\n" + "=====Customer Segment Test : Start ====" + "\n");
		try {
			nav_fetch_cust_segment();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "nav_fetch_cust_segment");
		}

		try {
			order_create();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "order_create");
		}

		try {
			tasklog_seg_validate();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "tasklog_seg_validate");
		}

		try {
			edit_order_seg_validate();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "edit_order_seg_validate");
		}

		try {
			validate_segment_B();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "validate_segment_B");
		}

		try {
			validate_segment_C();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "validate_segment_C");
		}

		try {
			validate_segment_newA();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "validate_segment_newA");
		}

		try {
			validate_segment_newB();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "validate_segment_newB");
		}

		msg.append("\n" + "=====Customer Segment Test : End ====" + "\n");

	}

//	///@Test(priority = 1) // -- Open customer fetch and store segment in sheet

	public static void nav_fetch_cust_segment()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		try {
			logs.info("========== Cust-segment Testcase 1 : Start ==========");
			msg.append("========== Cust-segment Testcase 1 : Start ==========" + "\n");
			JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time +
			Actions act = new Actions(driver);

			Robot r = new Robot();

			mth.cust_detail_page(1);

			// selct segment

			Select Custsegment = new Select(isElementPresent("cust_seg_id"));
			Custsegment.selectByVisibleText("A");

			// Click on save button
			WebElement save_customer = isElementPresent("CustEdSaveBtn_xpath");
			save_customer.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// Get selected value of segment

			String segments = Custsegment.getFirstSelectedOption().getText();
			logs.info("Selected Segment is: " + segments);
			msg.append("Selected Segment is: " + segments + "\n");
			msg.append("Fetch and store segment in sheet == PASS " + "\n");
			method_28661.setData("Test_Case", 1, 5, segments);
			method_28661.setData("Test_Case", 1, 3, "Pass");
			getScreenshot(driver, "cust-detail2_segment");
		}

		catch (Exception e) {

			msg.append("Fetch and store segment in sheet == FAIL" + "\n");
			logs.info("not able to perform test 1 : " + e);
			method_28661.setData("Test_Case", 1, 5, "FAIL");
		}

		logs.info("========== Cust-segment Testcase 1 : End ==========");
		msg.append("========== Cust-segment Testcase 1 : End ==========" + "\n" + "\n");

	}

	// @Test(priority = 2) // -- create new job order with same customer

	public static void order_create()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		try {
			logs.info("========== Cust-segment Testcase 2 : Start ==========");
			msg.append("========== Cust-segment Testcase 2 : Start ==========" + "\n");
			JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
			Actions act = new Actions(driver);

			mth.orderCreation(1);
			msg.append("Create new job order with same customer == PASS" + "\n");
			method_28661.setData("Test_Case", 2, 3, "PASS");
		}

		catch (Exception e) {
			msg.append("Create new job order with same customer == FAIL" + "\n");
			logs.info("not able to perform test 1 : " + e);
			method_28661.setData("Test_Case", 2, 3, "FAIL");
		}
		logs.info("========== Cust-segment Testcase 2 : End ==========");
		msg.append("========== Cust-segment Testcase 2 : End ==========" + "\n" + "\n");
	}

	// @Test(priority = 3)
	// -- Open customer fetch and store segment in sheet

	public static void tasklog_seg_validate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		try {
			logs.info("========== Cust-segment Testcase 3 : Start ==========");
			msg.append("========== Cust-segment Testcase 3 : Start ==========" + "\n");
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

			String LOC_pickup_id = method_28661.getData("Test_Case", 2, 6);
			System.out.println("use pickup id is :" + LOC_pickup_id);
			try {
				Thread.sleep(500);
				WebElement job_segment = driver.findElement(
						By.xpath("//label[contains(text(),'" + LOC_pickup_id + "')]//following::label[1]"));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				act.moveToElement(job_segment).build().perform();
				jse.executeScript("arguments[0].scrollIntoView();", job_segment);
				Thread.sleep(1500);
				highLight(job_segment, driver);
				getScreenshot(driver, "segment-tasklog_segment");

				String fetch_segment_tasklog = job_segment.getAttribute("title");
				logs.info("Segment on TAsklog: " + fetch_segment_tasklog);
				msg.append("Segment on TAsklog: " + fetch_segment_tasklog + "\n");

				method_28661.setData("Test_Case", 3, 7, fetch_segment_tasklog);

				String cust_segment_set = method_28661.getData("Test_Case", 1, 5);
				logs.info("Segment in Customer detail : " + fetch_segment_tasklog);
				msg.append("Segment in Customer detail : " + fetch_segment_tasklog + "\n");
				if (fetch_segment_tasklog.equalsIgnoreCase(cust_segment_set)) {

					method_28661.setData("Test_Case", 3, 3, "PASS");

					logs.info("Segment in Customer detail and Tasklog match : PASS");
					msg.append("Segment in Customer detail and Tasklog match : PASS" + "\n");
				}

			} catch (Exception e) { //

				getScreenshot(driver, "segment-tasklog-fail_segment");
				logs.info("Segment in Customer detail and Tasklog match : FAIL " + e);
				msg.append("Segment in Customer detail and Tasklog match : FAIL" + "\n");
				method_28661.setData("Test_Case", 3, 3, "FAIL");
			}

		}

		catch (Exception e) { //
			logs.info("Error in execute testcase 3 : " + e);
			getScreenshot(driver, "segment-tasklog-fail_segment");
			msg.append("Segment in Customer detail and Tasklog match : FAIL" + "\n");
			method_28661.setData("Test_Case", 3, 3, "FAIL");
		}

		logs.info("========== Cust-segment Testcase 3 : End ==========");
		msg.append("========== Cust-segment Testcase 3 : End ==========" + "\n" + "\n");

	}

	// @Test(priority = 4)
	// -- Open customer fetch and store segment in sheet

	public static void edit_order_seg_validate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		try {
			logs.info("========== Cust-Segment verify in Edit Order : Start ==========");
			msg.append("========== Cust-Segment verify in Edit Order: Start ==========" + "\n");
			JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
			Actions act = new Actions(driver);

			try {

				// --fetch pick from testcase 2 sheet

				mth.open_pickup_frm_tasklog(2);
				// -- navigate to edit job tab

				mth.navigate_Edit_Job();

				// --fetch customer degment from edit order screen

				String edit_order_segment = isElementPresent("edit_segmet_xpath").getText();

				System.out.println("fetched customer segment is : " + edit_order_segment);
				getScreenshot(driver, "edit_order_segment-A");
				String fetched_seg = edit_order_segment;
				String[] segment = fetched_seg.split("-");
				String final_segment = segment[0];
				System.out.println("segment fetched in edit order screen is : " + final_segment);
				logs.info("segment fetched in edit order screen is : " + final_segment);
				method_28661.setData("Test_Case", 4, 8, final_segment);
				String seg_in_cust_details = method_28661.getData("Test_Case", 1, 5);

				if (final_segment.equalsIgnoreCase(seg_in_cust_details)) {
					logs.info("Segment in edit order and customer detail is match == PASS");
					msg.append("Segment in edit order and customer detail is match == PASS" + "\n");
					method_28661.setData("Test_Case", 4, 3, "PASS");
				}

			}

			catch (Exception e) {
				//
				logs.info("Segment in edit order and customer detail is match == FAIL");
				msg.append("Segment in edit order and customer detail is match == FAIL" + "\n");
				method_28661.setData("Test_Case", 4, 3, "FAIL");
				getScreenshot(driver, "edit_order_segment-A");
			}

		}

		catch (Exception e) {
			msg.append("Segment in edit order and customer detail is match == FAIL" + "\n");
			logs.info("Not able to fetch and match segment for edit order screen" + e);
			getScreenshot(driver, "edit_order_segment-A");
			method_28661.setData("Test_Case", 4, 3, "FAIL");
		}

		logs.info("========== Cust Segment A verify in Edit Order : End ==========");
		msg.append("========== Cust Segment A verify in Edit Order : End ==========" + "\n" + "\n");
	}

	// @Test(priority = 5)
	// -- Select segment B and validate that it reflect on tasklog and Edit order
	// screen

	public static void validate_segment_B()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		try {
			logs.info("========== Cust Segment B  : Start ==========");
			msg.append("========== Cust Segment B : Start ==========" + "\n");
			JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
			Actions act = new Actions(driver);

			mth.cust_detail_page(1);

			// selct segment
			Select Custsegment = new Select(isElementPresent("cust_seg_id"));
			Custsegment.selectByVisibleText("B");

			// Click on save button
			WebElement save_customer = isElementPresent("CustEdSaveBtn_xpath");
			save_customer.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// Get selected value of segment
			try {
				String segments = Custsegment.getFirstSelectedOption().getText();
				logs.info("Selected Segment is: " + segments);
				msg.append("Selected Segment is: " + segments + "\n");
				method_28661.setData("Test_Case", 5, 5, segments);
				method_28661.setData("Test_Case", 5, 3, "Pass");
				getScreenshot(driver, "cust-detail-B_segment");
			} catch (Exception e) { //
				logs.info("not able to perform test 1 : " + e);
				method_28661.setData("Test_Case", 5, 5, "FAIL");
			}

			// -- validate segment in tasklog
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

				String LOC_pickup_id = method_28661.getData("Test_Case", 2, 6);
				System.out.println("use pickup id is :" + LOC_pickup_id);
				Thread.sleep(500);
				WebElement job_segment = driver.findElement(
						By.xpath("//label[contains(text(),'" + LOC_pickup_id + "')]//following::label[1]"));

				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				act.moveToElement(job_segment).build().perform();
				jse.executeScript("arguments[0].scrollIntoView();", job_segment);
				Thread.sleep(1500);
				highLight(job_segment, driver);
				getScreenshot(driver, "segment-B-tasklog_segment");

				String fetch_segment_tasklog = job_segment.getAttribute("title");
				logs.info("Segment on TAsklog: " + fetch_segment_tasklog);
				msg.append("Segment on TAsklog: " + fetch_segment_tasklog + "\n");

				method_28661.setData("Test_Case", 5, 7, fetch_segment_tasklog);

				String cust_segment_set = method_28661.getData("Test_Case", 5, 5);
				logs.info("Segment in Customer detail : " + fetch_segment_tasklog);
				msg.append("Segment in Customer detail : " + fetch_segment_tasklog + "\n");

				// -- match segment with cust segment and tasklog segment

				if (fetch_segment_tasklog.equalsIgnoreCase(cust_segment_set)) {

					logs.info("Segment in Customer detail and Tasklog match == PASS");
					msg.append("Segment in Customer detail and Tasklog match == PASS" + "\n");
				}

			} catch (Exception e) {

				getScreenshot(driver, "segment-tasklog-fail_segment");
				logs.info("Segment in Customer detail and Tasklog match == FAIL " + e);
				msg.append("Segment in Customer detail and Tasklog match == FAIL" + "\n");
			}

			/// match segment with cust segment and Edit order segment

			try {
				logs.info("========== Segment verify in Edit Order : Start ==========");

				// --fetch pick from testcase 2 sheet

				mth.open_pickup_frm_tasklog(2);
				// -- navigate to edit job tab

				mth.navigate_Edit_Job();

				// --fetch customer degment from edit order screen

				String edit_order_segment = isElementPresent("edit_segmet_xpath").getText();

				System.out.println("fetched customer segment is : " + edit_order_segment);
				getScreenshot(driver, "edit_order_segment-A");
				String fetched_seg = edit_order_segment;
				String[] segment = fetched_seg.split("-");
				String final_segment = segment[0];
				System.out.println("segment fetched in edit order screen is : " + final_segment);
				logs.info("segment fetched in edit order screen is : " + final_segment);
				method_28661.setData("Test_Case", 5, 8, final_segment);
				String seg_in_cust_details = method_28661.getData("Test_Case", 5, 5);

				if (final_segment.equalsIgnoreCase(seg_in_cust_details)) {
					logs.info("Segment in edit order and customer detail is match == PASS");
					msg.append("Segment in edit order and customer detail is match == PASS" + "\n");
					method_28661.setData("Test_Case", 5, 3, "PASS");
				}
			}

			catch (Exception e) {
				msg.append("Segment in edit order and customer detail is match == FAIL" + "\n");
				logs.info("Not able to fetch and match segment for edit order screen" + e);
				getScreenshot(driver, "edit_order_segment-B");
				method_28661.setData("Test_Case", 5, 3, "FAIL");
			}

		} catch (Exception e) {
			//

			logs.info("Segment B verification  == FAIL " + e);
			msg.append("Segment B verification  == FAIL" + "\n");

		}
		logs.info("========== Cust Segment B  : End ==========");
		msg.append("========== Cust Segment B : End ==========" + "\n" + "\n");

	}

	// @Test(priority = 6)
	// -- Select segment C and validate that it reflect on tasklog and Edit order
	// screen

	public static void validate_segment_C()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		try {
			logs.info("========== Cust Segment C  : Start ==========");
			msg.append("========== Cust Segment C : Start ==========" + "\n");
			JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
			Actions act = new Actions(driver);

			mth.cust_detail_page(1);

			// selct segment
			Select Custsegment = new Select(isElementPresent("cust_seg_id"));
			Custsegment.selectByVisibleText("C");

			// Click on save button
			WebElement save_customer = isElementPresent("CustEdSaveBtn_xpath");
			save_customer.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// Get selected value of segment
			try {
				String segments = Custsegment.getFirstSelectedOption().getText();
				logs.info("Selected Segment is: " + segments);
				msg.append("Selected Segment is: " + segments + "\n");
				method_28661.setData("Test_Case", 6, 5, segments);
				getScreenshot(driver, "cust-detail-C_segment");

			} catch (Exception e) { //
				logs.info("not able to perform test 1 : " + e);
				method_28661.setData("Test_Case", 6, 5, "FAIL");
			}

			// -- validate segment in tasklog
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

				String LOC_pickup_id = method_28661.getData("Test_Case", 2, 6);
				System.out.println("use pickup id is :" + LOC_pickup_id);
				Thread.sleep(500);
				WebElement job_segment = driver.findElement(
						By.xpath("//label[contains(text(),'" + LOC_pickup_id + "')]//following::label[1]"));

				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				act.moveToElement(job_segment).build().perform();
				jse.executeScript("arguments[0].scrollIntoView();", job_segment);
				Thread.sleep(1500);
				highLight(job_segment, driver);
				getScreenshot(driver, "segment-C-tasklog_segment");

				String fetch_segment_tasklog = job_segment.getAttribute("title");
				logs.info("Segment on TAsklog: " + fetch_segment_tasklog);
				msg.append("Segment on TAsklog: " + fetch_segment_tasklog + "\n");

				method_28661.setData("Test_Case", 6, 7, fetch_segment_tasklog);

				String cust_segment_set = method_28661.getData("Test_Case", 6, 5);
				logs.info("Segment in Customer detail : " + fetch_segment_tasklog);
				msg.append("Segment in Customer detail : " + fetch_segment_tasklog + "\n");

				// -- match segment with cust segment and tasklog segment

				if (fetch_segment_tasklog.equalsIgnoreCase(cust_segment_set)) {

					logs.info("Segment in Customer detail and Tasklog match == PASS");
					msg.append("Segment in Customer detail and Tasklog match == PASS" + "\n");
				}

			} catch (Exception e) {

				getScreenshot(driver, "segment-tasklog-fail_segment");
				logs.info("Segment in Customer detail and Tasklog match == FAIL " + e);
				msg.append("Segment in Customer detail and Tasklog match == FAIL" + "\n");
			}

			/// match segment with cust segment and Edit order segment

			try {
				logs.info("========== Segment verify in Edit Order : Start ==========");

				// --fetch pick from testcase 2 sheet

				mth.open_pickup_frm_tasklog(2);
				// -- navigate to edit job tab

				mth.navigate_Edit_Job();

				// --fetch customer degment from edit order screen

				String edit_order_segment = isElementPresent("edit_segmet_xpath").getText();

				System.out.println("fetched customer segment is : " + edit_order_segment);
				getScreenshot(driver, "edit_order_segment-C");
				String fetched_seg = edit_order_segment;
				String[] segment = fetched_seg.split("-");
				String final_segment = segment[0];
				System.out.println("segment fetched in edit order screen is : " + final_segment);
				logs.info("segment fetched in edit order screen is : " + final_segment);
				method_28661.setData("Test_Case", 6, 8, final_segment);
				String seg_in_cust_details = method_28661.getData("Test_Case", 6, 5);

				if (final_segment.equalsIgnoreCase(seg_in_cust_details)) {
					logs.info("Segment in edit order and customer detail is match == PASS");
					msg.append("Segment in edit order and customer detail is match == PASS" + "\n");
					method_28661.setData("Test_Case", 6, 3, "PASS");
				}
			}

			catch (Exception e) {
				msg.append("Segment in edit order and customer detail is match == FAIL" + "\n");
				logs.info("Not able to fetch and match segment for edit order screen" + e);
				getScreenshot(driver, "edit_order_segment-C");
				method_28661.setData("Test_Case", 6, 3, "FAIL");
			}

		} catch (Exception e) {
			//

			logs.info("Segment C verification  == FAIL " + e);
			msg.append("Segment C verification  == FAIL" + "\n");

		}
		logs.info("========== Cust Segment C  : End ==========");
		msg.append("========== Cust Segment C : End ==========" + "\n" + "\n");

	}

	// @Test(priority = 7)
//	Select segment New A and validate that it reflect on tasklog and Edit order screen

	public static void validate_segment_newA()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		try {
			logs.info("========== Cust Segment new A  : Start ==========");
			msg.append("========== Cust Segment new A : Start ==========" + "\n");
			JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
			Actions act = new Actions(driver);

			mth.cust_detail_page(1);

			// selct segment
			Select Custsegment = new Select(isElementPresent("cust_seg_id"));
			Custsegment.selectByVisibleText("New A");

			// Click on save button
			WebElement save_customer = isElementPresent("CustEdSaveBtn_xpath");
			save_customer.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// Get selected value of segment
			try {
				String segments = Custsegment.getFirstSelectedOption().getText();
				logs.info("Selected Segment is: " + segments);
				msg.append("Selected Segment is: " + segments + "\n");
				method_28661.setData("Test_Case", 7, 5, segments);
				getScreenshot(driver, "cust-detail-newA_segment");

			} catch (Exception e) { //
				logs.info("not able to perform test 1 : " + e);
				method_28661.setData("Test_Case", 7, 5, "FAIL");
			}

			// -- validate segment in tasklog
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

				String LOC_pickup_id = method_28661.getData("Test_Case", 2, 6);
				System.out.println("use pickup id is :" + LOC_pickup_id);
				Thread.sleep(500);
				WebElement job_segment = driver.findElement(
						By.xpath("//label[contains(text(),'" + LOC_pickup_id + "')]//following::label[1]"));

				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				act.moveToElement(job_segment).build().perform();
				jse.executeScript("arguments[0].scrollIntoView();", job_segment);
				Thread.sleep(1500);
				highLight(job_segment, driver);
				getScreenshot(driver, "segment-C-tasklog_segment");

				String fetch_segment_tasklog = job_segment.getAttribute("title");
				logs.info("Segment on TAsklog: " + fetch_segment_tasklog);
				msg.append("Segment on TAsklog: " + fetch_segment_tasklog + "\n");

				method_28661.setData("Test_Case", 7, 7, fetch_segment_tasklog);

				String cust_segment_set = method_28661.getData("Test_Case", 6, 5);
				logs.info("Segment in Customer detail : " + fetch_segment_tasklog);
				msg.append("Segment in Customer detail : " + fetch_segment_tasklog + "\n");

				// -- match segment with cust segment and tasklog segment

				if (fetch_segment_tasklog.equalsIgnoreCase(cust_segment_set)) {

					logs.info("Segment in Customer detail and Tasklog match == PASS");
					msg.append("Segment in Customer detail and Tasklog match == PASS" + "\n");
				}

			} catch (Exception e) {

				getScreenshot(driver, "segment-tasklog-fail_segment");
				logs.info("Segment in Customer detail and Tasklog match == FAIL " + e);
				msg.append("Segment in Customer detail and Tasklog match == FAIL" + "\n");
			}

			/// match segment with cust segment and Edit order segment

			try {
				logs.info("========== Segment verify in Edit Order : Start ==========");

				// --fetch pick from testcase 2 sheet

				mth.open_pickup_frm_tasklog(2);
				// -- navigate to edit job tab

				mth.navigate_Edit_Job();

				// --fetch customer segment from edit order screen

				String edit_order_segment = isElementPresent("edit_segmet_xpath").getText();

				System.out.println("fetched customer segment is : " + edit_order_segment);
				getScreenshot(driver, "edit_order_segment-newA");
				String fetched_seg = edit_order_segment;
				String[] segment = fetched_seg.split("-");
				String final_segment = segment[0];
				System.out.println("segment fetched in edit order screen is : " + final_segment);
				logs.info("segment fetched in edit order screen is : " + final_segment);
				method_28661.setData("Test_Case", 7, 8, final_segment);
				String seg_in_cust_details = method_28661.getData("Test_Case", 7, 5);

				if (final_segment.equalsIgnoreCase(seg_in_cust_details)) {
					logs.info("Segment in edit order and customer detail is match == PASS");
					msg.append("Segment in edit order and customer detail is match == PASS" + "\n");
					method_28661.setData("Test_Case", 7, 3, "PASS");
				}
			}

			catch (Exception e) {
				msg.append("Segment in edit order and customer detail is match == FAIL" + "\n");
				logs.info("Not able to fetch and match segment for edit order screen" + e);
				getScreenshot(driver, "edit_order_segment-NewA");
				method_28661.setData("Test_Case", 7, 3, "FAIL");
			}

		} catch (Exception e) {
			//

			logs.info("Segment New A verification  == FAIL " + e);
			msg.append("Segment New A verification  == FAIL" + "\n");

		}
		logs.info("========== Cust Segment New A  : End ==========");
		msg.append("========== Cust Segment New A : End ==========" + "\n" + "\n");

	}

	// @Test(priority = 8)
//	Select segment new B and validate that it reflect on tasklog and Edit order screen

	public static void validate_segment_newB()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		try {
			logs.info("========== Cust Segment new B  : Start ==========");
			msg.append("========== Cust Segment new B : Start ==========" + "\n");
			JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
			Actions act = new Actions(driver);

			mth.cust_detail_page(1);

			// selct segment
			Select Custsegment = new Select(isElementPresent("cust_seg_id"));
			Custsegment.selectByVisibleText("New B");

			// Click on save button
			WebElement save_customer = isElementPresent("CustEdSaveBtn_xpath");
			save_customer.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// Get selected value of segment
			try {
				String segments = Custsegment.getFirstSelectedOption().getText();
				logs.info("Selected Segment is: " + segments);
				msg.append("Selected Segment is: " + segments + "\n");
				method_28661.setData("Test_Case", 8, 5, segments);
				getScreenshot(driver, "cust-detail-NewB_segment");

			} catch (Exception e) { //
				logs.info("not able to perform test 1 : " + e);
				method_28661.setData("Test_Case", 8, 5, "FAIL");
			}

			// -- validate segment in tasklog
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
				Thread.sleep(1500);
				String LOC_pickup_id = method_28661.getData("Test_Case", 2, 6);
				System.out.println("use pickup id is :" + LOC_pickup_id);
				Thread.sleep(100);
				WebElement job_segment = driver.findElement(
						By.xpath("//label[contains(text(),'" + LOC_pickup_id + "')]//following::label[1]"));

				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				act.moveToElement(job_segment).build().perform();
				jse.executeScript("arguments[0].scrollIntoView();", job_segment);
				Thread.sleep(1500);
				highLight(job_segment, driver);
				getScreenshot(driver, "segment-C-tasklog_segment");

				String fetch_segment_tasklog = job_segment.getAttribute("title");
				logs.info("Segment on TAsklog: " + fetch_segment_tasklog);
				msg.append("Segment on TAsklog: " + fetch_segment_tasklog + "\n");

				method_28661.setData("Test_Case", 8, 8, fetch_segment_tasklog);

				String cust_segment_set = method_28661.getData("Test_Case", 6, 5);
				logs.info("Segment in Customer detail : " + fetch_segment_tasklog);
				msg.append("Segment in Customer detail : " + fetch_segment_tasklog + "\n");

				// -- match segment with cust segment and tasklog segment

				if (fetch_segment_tasklog.equalsIgnoreCase(cust_segment_set)) {

					logs.info("Segment in Customer detail and Tasklog match == PASS");
					msg.append("Segment in Customer detail and Tasklog match == PASS" + "\n");
				}

			} catch (Exception e) {

				getScreenshot(driver, "segment-tasklog-fail_segment");
				logs.info("Segment in Customer detail and Tasklog match == FAIL " + e);
				msg.append("Segment in Customer detail and Tasklog match == FAIL" + "\n");
			}

			/// match segment with cust segment and Edit order segment

			try {
				logs.info("========== Segment verify in Edit Order : Start ==========");

				// --fetch pick from testcase 2 sheet

				mth.open_pickup_frm_tasklog(2);
				// -- navigate to edit job tab

				mth.navigate_Edit_Job();

				// --fetch customer degment from edit order screen

				String edit_order_segment = isElementPresent("edit_segmet_xpath").getText();

				System.out.println("fetched customer segment is : " + edit_order_segment);
				getScreenshot(driver, "edit_order_segment-NewB");
				String fetched_seg = edit_order_segment;
				String[] segment = fetched_seg.split("-");
				String final_segment = segment[0];
				System.out.println("segment fetched in edit order screen is : " + final_segment);
				logs.info("segment fetched in edit order screen is : " + final_segment);
				method_28661.setData("Test_Case", 8, 8, final_segment);
				String seg_in_cust_details = method_28661.getData("Test_Case", 8, 5);

				if (final_segment.equalsIgnoreCase(seg_in_cust_details)) {
					logs.info("Segment in edit order and customer detail is match == PASS");
					msg.append("Segment in edit order and customer detail is match == PASS" + "\n");
					method_28661.setData("Test_Case", 8, 3, "PASS");
				}
			}

			catch (Exception e) {
				msg.append("Segment in edit order and customer detail is match == FAIL" + "\n");
				logs.info("Not able to fetch and match segment for edit order screen" + e);
				getScreenshot(driver, "edit_order_segment-NewB");
				method_28661.setData("Test_Case", 8, 3, "FAIL");
			}

		} catch (Exception e) {
			//

			logs.info("Segment new B verification  == FAIL " + e);
			msg.append("Segment new B verification  == FAIL" + "\n");

		}
		logs.info("========== Cust Segment new B  : End ==========");
		msg.append("========== Cust Segment new B : End ==========" + "\n" + "\n");

	}

}
