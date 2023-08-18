package Quick_Quote_28658;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;
import connect_OCBaseMethods.OrderCreation;
import Quick_Quote_28658.*;

public class quick_quote extends BaseInit {
	//static StringBuilder msg = new StringBuilder();

	JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click

	static methods mth = new methods();

	@Test
	public void quickQuote() throws IOException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		driver.navigate().refresh();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		msg.append("\n\n" + "=====Quick Quote Test : Start ====" + "\n");
		try {
			navigate_quick_quote();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "navigate_quick_quote");
		}

		try {
			cal_quick_rate();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "cal_quick_rate");
		}

		try {
			save_quick_quote();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "save_quick_quote");
		}

		/*
		 * try { reject_quick_quote(); } catch (Exception e) { logs.info(e);
		 * getScreenshot(driver, "reject_quick_quote"); }
		 */

		try {
			validate_email();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "validate_email");
		}
		
		// --print window is not able to handle

//		try {
//			validate_print();
//		} catch (Exception e) {
//			logs.info(e);
//			getScreenshot(driver, "validate_print");
//		}

		try {
			validate_process_button();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "validate_process_button");
		}

		try {
			validate_order_create_history();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "validate_order_create_history");
		}

		msg.append("\n" + "=====Quick Quote Test : End ====" + "\n");

	}

	// @Test(priority = 1, enabled = false)
	// -- Validate User able to redirect on Quick Quote Page

	public static void navigate_quick_quote()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		logs.info("========== Quick Quote Testcase 1 : Start ==========");
		msg.append("========== Quick Quote Testcase 1 : Start ==========" + "\n");

		try {
			// -- refresh connect app

			mth.refreshApp();

			// call method for naviagte new order page

			mth.new_order_page_navigate(1);

			// -- Click on Quick Quote button

			WebElement quick_quote_button = isElementPresent("quick_quote_button_id");
			quick_quote_button.click();

			// -- validate Quick Quote open

			WebElement quick_quote_title = isElementPresent("quick_title_xpath");

			if (quick_quote_title.isDisplayed()) {
				logs.info("Naviagate to Quick Quote page == PASS");
				msg.append("Naviagate to Quick Quote page == PASS" + "\n");
				getScreenshot(driver, "Quick_quote_pass");
				methods.setData("Test_Case", 1, 3, "PASS");
			}

			else {
				logs.info("Naviagate to Quick Quote page == FAIL");
				getScreenshot(driver, "Quick_quote_fail");
				msg.append("Naviagate to Quick Quote page == FAIL" + "\n");
				methods.setData("Test_Case", 1, 3, "FAIL");
			}

		} catch (Exception e) {
			logs.info("Naviagate to Quick Quote page == FAIL");
			getScreenshot(driver, "Quick_quote_fail");
			methods.setData("Test_Case", 1, 3, "FAIL");
			msg.append("Naviagate to Quick Quote page == FAIL" + "\n");

		}
		logs.info("========== Quick Quote Testcase 1 : End ==========");
		msg.append("========== Quick Quote Testcase 1 : End ==========" + "\n" + "\n");

	}

	// @Test(priority = 2, enabled = false)

	// -- calculate and fetch Service and Estimate Rate for Quick Quote

	public static void cal_quick_rate()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		logs.info("========== Quick Quote Testcase 2 : Start ==========");
		msg.append("========== Quick Quote Testcase 2 : Start ==========" + "\n");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
		WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(60));// wait time
		Actions act = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click

		try {

			// -- OPen pickup popup and get screenshot

			WebElement Pusearch_open = isElementPresent("quick_pu_pop_open_id");
			Pusearch_open.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			getScreenshot(driver, "pupopup_quickquote");

			logs.info("Pickup popup opened and screenshot is captured");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			WebElement Pusearch_close = isElementPresent("quick_pop_close_id");
			Pusearch_close.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(200);
			// -- OPen pickup airport popup and get screenshot

			WebElement Pusearch_airport_open = isElementPresent("quick_pu_airport_open_id");
			Pusearch_airport_open.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(200);

			getScreenshot(driver, "PU Airport popup_quickquote");

			logs.info("Pickup Airport popup opened and screenshot is captured");

			WebElement Pusearch_airport_close = isElementPresent("quick_pop_close_id");
			Pusearch_airport_close.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(200);

			// -- OPen Del popup and get screenshot

			WebElement delsearch_open = isElementPresent("quick_del_pop_open_id");
			delsearch_open.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(200);

			getScreenshot(driver, "Delpopup_quickquote");

			logs.info("Delivery popup opened and screenshot is captured");

			WebElement delsearch_close = isElementPresent("quick_pop_close_id");
			delsearch_close.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(200);

			// -- OPen Del airport popup and get screenshot

			WebElement delsearch_airport_open = isElementPresent("quick_del_airport_open_id");
			delsearch_airport_open.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(200);

			getScreenshot(driver, "Del Airport popup_quickquote");

			logs.info("Delivery Airport popup opened and screenshot is captured");

			WebElement delsearch_airport_close = isElementPresent("quick_pop_close_id");
			delsearch_airport_close.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(200);

			// -- Open ExpandedList take Screenshot
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			WebElement expanded_list_open = isElementPresent("quick_expanded_list_id");

			act.moveToElement(expanded_list_open).build().perform();
			jse.executeScript("arguments[0].scrollIntoView();", expanded_list_open);
			wait.until(ExpectedConditions.elementToBeClickable(expanded_list_open));
			expanded_list_open.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(200);

			getScreenshot(driver, "expandedlist_quickquote");

			logs.info("Expanded list popup opened and screenshot is captured");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			WebElement expand_list_popup_close = isElementPresent("quick_expanded_list_close_xpath");
			jse.executeScript("arguments[0].scrollIntoView();", expand_list_popup_close);
			jse.executeScript("arguments[0].click();", expand_list_popup_close);
			logs.info("expanded popup is close");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(500);
		}

		catch (Exception e) {

			logs.info("Not able to take scrrenshot  due to : " + e);
			msg.append("Fetch Rate and Service screenshot == FAIL" + "\n");

		}

		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			// -- Enter Pickup ZIP on Quick Quote page
			WebElement pu_zip = isElementPresent("quick_pu_zip_id");
			jse.executeScript("arguments[0].scrollIntoView();", pu_zip);
			Thread.sleep(500);
			String pickup_zip = methods.getData("address", 1, 3);
			System.out.println("fetched PU zip is : " + pickup_zip);
			pu_zip.sendKeys(pickup_zip, Keys.TAB);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(500);
			logs.info("Entered Pickup Zipcode is : " + pickup_zip);

			// -- Enter Delivery ZIP on Quick Quote page
			WebElement del_zip = isElementPresent("quick_del_zip_id");
			String delivery_zip = methods.getData("address", 1, 11);
			System.out.println("fetched Del zip is : " + delivery_zip);
			del_zip.sendKeys(delivery_zip, Keys.TAB);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(500);
			logs.info("Entered Pickup Zipcode is : " + delivery_zip);

			// -- Enter Shipment Weight

			WebElement ship_weight = isElementPresent("quick_ship_weight_id");
			String weight = methods.getData("address", 1, 19);
			System.out.println("fetched weight is : " + weight);
			ship_weight.sendKeys(weight, Keys.TAB);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			logs.info("Entered Pickup Zipcode is : " + weight);

			// --Length
			String Len = methods.getData("address", 1, 20);
			isElementPresent("quick_ship_length_id").clear();
			isElementPresent("quick_ship_length_id").sendKeys(Len);
			isElementPresent("quick_ship_length_id").sendKeys(Keys.TAB);
			logs.info("Entered Length");

			// --Width
			String Width = methods.getData("address", 1, 21);
			isElementPresent("quick_ship_width_id").clear();
			isElementPresent("quick_ship_width_id").sendKeys(Width);
			isElementPresent("quick_ship_width_id").sendKeys(Keys.TAB);
			logs.info("Entered Width");

			// --Height
			String Height = methods.getData("address", 1, 22);
			isElementPresent("quick_ship_height_id").clear();
			isElementPresent("quick_ship_height_id").sendKeys(Height);
			isElementPresent("quick_ship_height_id").sendKeys(Keys.TAB);
			logs.info("Entered Height");
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// -- Calculate rate and service

			WebElement calculate = isElementPresent("quick_calulate_id");
			wait2.until(ExpectedConditions.elementToBeClickable(calculate));
			jse.executeScript("arguments[0].scrollIntoView();", calculate);
			jse.executeScript("arguments[0].click();", calculate);
			logs.info("Click on Calculate button on Quick Quote page");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// - fetch and store service

			String service = (String) jse.executeScript("return document.getElementById('txtServiceType').value");
			System.out.println("service : " + service);
			logs.info("service use for entered data is : " + service);
			msg.append("Quick Quote Service : " + service + "\n");
			methods.setData("Test_Case", 2, 6, service);

			// - fetch and store Estimate Rate

			WebElement rate = isElementPresent("quick_est_rate_id");
			String est_rate = rate.getText();
			logs.info("Estimate rate is : " + est_rate);
			msg.append("Quick Quote Rate : " + est_rate + "\n");
			// set data in sheet
			methods.setData("Test_Case", 2, 7, est_rate);

			getScreenshot(driver, "service and rate_quick Quote");

			// -- set pass in sheet

			methods.setData("Test_Case", 2, 3, "PASS");
			msg.append("Fetch Rate and Service == PASS" + "\n");

		}

		catch (Exception e) {

			msg.append("Fetch Rate and Service == FAIL" + "\n");

			getScreenshot(driver, "service and rate-FAIL_quick Quote");
			methods.setData("Test_Case", 2, 3, "FAIL");
		}

		logs.info("========== Quick Quote Testcase 2 : End ==========");
		msg.append("========== Quick Quote Testcase 2 : End ==========" + "\n" + "\n");
	}

	/*
	 * //@Test(priority = 3)
	 * 
	 * // -- Validate Reject Quick Quote functionality before save Quick Quote
	 * 
	 * public static void reject_quick_quote() throws EncryptedDocumentException,
	 * InvalidFormatException, IOException, AWTException {
	 * 
	 * logs.info("========== Testcase 3 : Start ==========");
	 * msg.append("========== Testcase 3 : Start ==========" + "\n"); WebDriverWait
	 * wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time WebDriverWait wait2 = new
	 * WebDriverWait(driver,Duration.ofSeconds(60));// wait time Actions act = new Actions(driver);
	 * JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
	 * 
	 * // -- Click on Decline button to reject quote
	 * 
	 * WebElement decline = isElementPresent("quick_decline_id");
	 * jse.executeScript("arguments[0].scrollIntoView();", decline);
	 * jse.executeScript("arguments[0].click();", decline);
	 * logs.info("CLicked on Decline button");
	 * 
	 * String quick_quote_no =
	 * driver.findElement(By.xpath("//span[@class='pull-left']")).getText();
	 * 
	 * String only_quick_quote = quick_quote_no.replace("Order : ", "");
	 * System.out.println("quick Quote is : " + only_quick_quote);
	 * 
	 * }
	 * 
	 * 
	 */

	// @Test(priority = 4)

	// -- Validate save Quick Quote functionality

	public static void save_quick_quote()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		System.out.println("called 1st test");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time

		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		try {
			// - ENter data on new order and click on Quick Quote
			navigate_quick_quote();

			// - ENter data on Quick Quote and cal rate
			System.out.println("called 2nd test");
			cal_quick_rate();

			logs.info("========== Quick Quote Testcase 4 : Start ==========");
			msg.append("========== Quick Quote Testcase 4 : Start ==========" + "\n");

			// - fetch and store service

			String service = (String) jse.executeScript("return document.getElementById('txtServiceType').value");
			System.out.println("service : " + service);
			logs.info("service use for entered data is : " + service);
			msg.append("Quick Quote Service : " + service + "\n");
			methods.setData("Test_Case", 4, 6, service);

			// - fetch and store Estimate Rate

			WebElement rate = isElementPresent("quick_est_rate_id");
			String est_rate = rate.getText();
			logs.info("Estimate rate is : " + est_rate);
			msg.append("Quick Quote Rate : " + est_rate + "\n");
			// set data in sheet
			methods.setData("Test_Case", 4, 7, est_rate);

			getScreenshot(driver, "service and rate_quick Quote");

			try {
				// -- Click on save button
				WebElement save_quick_quote = isElementPresent("quick_save_xpath");
				jse.executeScript("arguments[0].scrollIntoView();", save_quick_quote);
				jse.executeScript("arguments[0].click();", save_quick_quote);
				logs.info("CLicked on Save button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				jse.executeScript("window.scrollBy(0,-500)", "");

				// -- fetch Quick Quote no
				WebElement quick_quote = isElementPresent("quick_save_quote_xpath");
				jse.executeScript("arguments[0].scrollIntoView();", quick_quote);
				String q_id = quick_quote.getText();
				logs.info("Quick Quote no : " + q_id);
				msg.append("Quick Quote no : " + q_id + "\n");
				methods.setData("Test_Case", 4, 4, q_id);
				getScreenshot(driver, "Quote-id_quick Quote");

				/// add pass and fail in sheet
				methods.setData("Test_Case", 4, 3, "PASS");
				logs.info("Fetch Rate , Service and Quick Quote No == PASS");
				msg.append("Fetch Rate , Service and Quick Quote No == PASS" + "\n");

			} catch (Exception e) {

				logs.info("Fetch Rate , Service and Quick Quote No == FAIL");
				methods.setData("Test_Case", 4, 3, "FAIL");
				msg.append("Fetch Rate , Service and Quick Quote No == FAIL" + "\n");
				getScreenshot(driver, "Quote-id_quick Quote_fail");

			}

		}

		catch (Exception e) {
			// TODO: handle exception

			logs.info("Fetch Rate , Service and Quick Quote No == FAIL " + e);
			msg.append("Fetch Rate , Service and Quick Quote No == FAIL " + "\n");

		}
		logs.info("========== Quick Quote Testcase 4 : End ==========");
		msg.append("========== Quick Quote Testcase 4 : End ==========" + "\n" + "\n");
	}

	// @Test(priority = 5)

	// -- Validate Email functionality for Quick Quote

	public static void validate_email()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time

		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click

		logs.info("========== Quick Quote Testcase 5 : Start ==========");
		msg.append("========== Quick Quote Testcase 5 : Start ==========" + "\n");

		try {

			// scroll down to Email field and enter Email

			jse.executeScript("window.scrollBy(0,250)", "");

			WebElement email = isElementPresent("quick_email_id");
			jse.executeScript("arguments[0].scrollIntoView();", email);
			// jse.executeScript("arguments[0].click();", email);
			email.sendKeys("parth.shah@samyak.com");
			logs.info("Email ID i sentered");

			// Click on send button after email entered

			WebElement send_email = isElementPresent("quick_send_email_id");
			send_email.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// get Email success message and validate

			WebElement email_suc = isElementPresent("quick_email_cnfm_id");

			String email_success_msg = isElementPresent("quick_email_cnfm_id").getText();
			System.err.println("email success message is : " + email_success_msg);

			if (email_success_msg.equalsIgnoreCase("Email sent successfully!")) {
				logs.info("Email sent successfully");
				highLight(email_suc, driver);
				getScreenshot(driver, "email-pass_quick Quote");
				methods.setData("Test_Case", 5, 3, "PASS");
				logs.info("Email Sent == PASS");
				msg.append("Email Sent  == PASS" + "\n");

			}
		}

		catch (Exception e) {

			logs.info("Email Sent == FAIL" + e);
			getScreenshot(driver, "email-fail_quick Quote");
			msg.append("Email Sent  == FAIL" + "\n");
			methods.setData("Test_Case", 5, 3, "FAIL");

		}

		logs.info("========== Quick Quote Testcase 5 : End ==========");
		msg.append("========== Quick Quote Testcase 5 : End ==========" + "\n" + "\n");

	}

	// @Test(priority = 6, enabled = false)

	// -- Validate Print button

	public static void validate_print()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time

		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click

		logs.info("========== Quick Quote Testcase 6 : Start ==========");
		msg.append("========== Quick Quote Testcase 6 : Start ==========" + "\n");
		try {
			String mainWindowHandle = driver.getWindowHandle();
			System.out.println("main window id : " + mainWindowHandle);
			logs.info("main window id : " + mainWindowHandle);
			
			WebElement print_button = isElementPresent("quick_print_id");
			print_button.click();
			logs.info("clicked on Print button");

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(scrFile,
					new File(System.getProperty("user.dir") + "/Report/Connect_Screenshot/" + "PDFDOWNNLOAD.pdf"));

			
			Set<String> allWindowHandles = driver.getWindowHandles();
			Iterator<String> iterator = allWindowHandles.iterator();

			// Here we will check if child window has other child windows and will fetch the
			// heading of the child window
			
			for (String windHandle : driver.getWindowHandles()) {
				driver.switchTo().window(windHandle);
				System.out.println("window Id is : " + windHandle);
				logs.info("Switched to Print window");
				Thread.sleep(2000);
				driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
				//getScreenshot(driver, "print_quick Quote");
				methods.setData("Test_Case", 6, 3, "PASS");
				logs.info("print popup open == PASS");
				msg.append("print popup open == PASS" + "\n");
			}
			driver.close();
			logs.info("Closed Print window");

			driver.switchTo().window(mainWindowHandle);
			logs.info("Switched to main window");
			

				
		} catch (Exception e) {

			getScreenshot(driver, "print_quick Quote");

			logs.info("print popup open == FAIL");
			msg.append("print popup open == FAIL" + "\n");
			methods.setData("Test_Case", 6, 3, "FAIL");
		}

		logs.info("========== Quick Quote Testcase 6 : End ==========");
		msg.append("========== Quick Quote Testcase 6 : End ==========" + "\n" + "\n");

	}

	// @Test(priority = 7)

	// -- Validate Process button

	public static void validate_process_button()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time

		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click

		logs.info("========== Quick Quote Testcase 7 : Start ==========");
		msg.append("========== Quick Quote Testcase 7 : Start ==========" + "\n");
		try {

			// -- Click on Proceed button

			WebElement process_button = isElementPresent("quick_process_id");

			process_button.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

// fetch and validate Puzip /Delzip and shipment info same as entered in Quick quote screen

			// fetch and validate PUZIP

			String puzip_neworder = (String) jse.executeScript("return document.getElementById('txtPUZipcode').value");
			logs.info("Pickup Zip on New order screen is: " + puzip_neworder);

			String Puzip_quick_screen = methods.getData("address", 1, 3);
			logs.info("Pickup Zip on Quick  screen is: " + puzip_neworder);

			// fetch and validate DelZIP
			String delzip_neworder = (String) jse.executeScript("return document.getElementById('txtDLZipCode').value");
			logs.info("Delivery Zip on New order screen is: " + delzip_neworder);

			String delzip_quick_screen = methods.getData("address", 1, 11);
			logs.info("Delivery Zip on Quick  screen is: " + delzip_quick_screen);

			// fetch and validate ship weight

			String weight_neworder = (String) jse
					.executeScript("return document.getElementById('txtpWeight_NewOrder').value");
			logs.info("weight on New order screen is: " + weight_neworder);

			String weight_quick_screen = methods.getData("address", 1, 19);
			logs.info("Weight on Quick  screen is: " + weight_quick_screen);

			// fetch and validate shipment length

			String length_neworder = (String) jse
					.executeScript("return document.getElementById('txtlength_NewOrder').value");
			logs.info("length on New order screen is: " + length_neworder);

			String length_quick_screen = methods.getData("address", 1, 20);
			logs.info("length on Quick  screen is: " + length_quick_screen);

			// fetch and validate shipment width

			String width_neworder = (String) jse
					.executeScript("return document.getElementById('txtwidth_NewOrder').value");
			logs.info("width on New order screen is: " + width_neworder);

			String width_quick_screen = methods.getData("address", 1, 21);
			logs.info("width on Quick  screen is: " + width_quick_screen);

// fetch and validate shipment height

			String height_neworder = (String) jse
					.executeScript("return document.getElementById('txtheight_NewOrder').value");
			logs.info("height on New order screen is: " + height_neworder);

			String heigth_quick_screen = methods.getData("address", 1, 21);
			logs.info("height on Quick  screen is: " + heigth_quick_screen);

			if (puzip_neworder.equalsIgnoreCase(Puzip_quick_screen)
					&& delzip_neworder.equalsIgnoreCase(delzip_quick_screen)
					&& weight_neworder.equalsIgnoreCase(weight_quick_screen)
					&& length_neworder.equalsIgnoreCase(length_quick_screen)
					&& width_neworder.equalsIgnoreCase(width_quick_screen)
					&& height_neworder.equalsIgnoreCase(heigth_quick_screen)) {

				logs.info("Data on New order screen is same Quick Quote screen");
				getScreenshot(driver, "print_quick Quote");
				methods.setData("Test_Case", 7, 3, "PASS");
				msg.append("Data on New order screen is same Quick Quote screen == PASS " + "\n");

			}

		}

		catch (Exception e) {
			// TODO: handle exception

			logs.info("Data is not same as entered in Quick Quote Screen");
			msg.append("Data on New order screen is same Quick Quote screen == FAIL " + "\n");
			methods.setData("Test_Case", 7, 3, "FAIL");

		}
		logs.info("========== Quick Quote Testcase 7 : End ==========");
		msg.append("========== Quick Quote Testcase 7 : End ==========" + "\n" + "\n");

	}

	// @Test(priority = 8)
	public static void validate_order_create_history()
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
		Actions act = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click

		logs.info("========== Quick Quote Testcase 8 : Start ==========");
		msg.append("========== Quick Quote Testcase 8 : Start ==========" + "\n");
		try {

			// -- Validate Quick Quote able to process from History section

			// --Click on history section

			WebElement history = isElementPresent("quick_history_id");
			act.moveToElement(history).build().perform();
			//jse.executeScript("arguments[0].scrollIntoView(true);", history);
			Thread.sleep(1500);
			act.click(history).build().perform();
			logs.info("Click on History Tab");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			WebElement quote_input_history = isElementPresent("hist_quote_id");

			if (quote_input_history.isDisplayed()) {

				logs.info("History Tab is open");
			}

			else {
				logs.info("error in open History section");
			}

			// -- fetch Quick Quote id store in sheet for TEst case 4

			String Quick_quote = methods.getData("Test_Case", 4, 4);
			System.out.println("Quick Quote id : " + Quick_quote);

			// enter fetched quote on Quote# of history page

			quote_input_history.sendKeys(Quick_quote);
			logs.info("Quote no entered on history page is : " + Quick_quote);
			Thread.sleep(1000);

			// -- Click on Process Button of entered Quote

			WebElement process = driver
					.findElement(By.xpath("//td[contains(text(),'" + Quick_quote + "')]//following::button[1]"));
			System.out.println("xpath for process button is : " + process);
			act.moveToElement(process).build().perform();
			act.click(process).build().perform();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// enter Pickup info on order creation screen

			mth.pickup_info();
			Thread.sleep(500);

			// enter Deliver info on order creation screen
			mth.Delivery_info();
			Thread.sleep(500);

			// -- Crteate order
			mth.craete_job();
			Thread.sleep(500);

			// --Click on Edit Order
			WebElement exittotasklog = isElementPresent("exit_to_tasklod_id");
			wait.until(ExpectedConditions.elementToBeClickable(exittotasklog));
			exittotasklog.click();
			logs.info("Clicked on Edit Order");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --validate quote not visible in history section

			// --Click on history section

			WebElement historys = isElementPresent("quick_history_id");
			act.moveToElement(historys).build().perform();
			act.click(historys).build().perform();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			WebElement quote_input_historys = isElementPresent("hist_quote_id");
			if (quote_input_historys.isDisplayed()) {

				logs.info("History Tab is open");
			}

			else {
				logs.info("error in open History section");
			}

			// -- fetch Quick Quote id store in sheet for TEst case 4
			String Quick_quote_no = methods.getData("Test_Case", 4, 4);
			System.out.println("Quick Quote id : " + Quick_quote_no);

			// enter fetched quote on Quote# of history page

			quote_input_historys.sendKeys(Quick_quote_no);
			logs.info("Quote no entered on history page is : " + Quick_quote_no);
			Thread.sleep(1000);

			try {

				WebElement process_button = driver
						.findElement(By.xpath("//td[contains(text(),'" + Quick_quote_no + "')]//following::button[1]"));
				System.out.println("xpath for process button is : " + process_button);

				if (process_button.isDisplayed()) {

					methods.setData("Test_Case", 8, 3, "FAIL, quote still appear in history");
					logs.info("order created with Quick Quote not visible in history section == FAIL");
					msg.append("order created with Quick Quote not visible in history section == FAIL");

				}

				else {
					logs.info("order created with Quick Quote not visible in history section == PASS");
					msg.append("order created with Quick quote functionality == PASS" + "\n");
					msg.append("order created with Quick Quote not visible in history section == PASS" + "\n");
					methods.setData("Test_Case", 8, 3, "PASS");
				}
			} catch (Exception e) {

				logs.info("Not able to find Process button on History page");
				logs.info("order created with Quick Quote not visible in history section == PASS");
				msg.append("order created with Quick quote functionality == PASS" + "\n");
				msg.append("order created with Quick Quote not visible in history section == PASS" + "\n");
				methods.setData("Test_Case", 8, 3, "PASS");
			}

			System.out.println("case 8 checked");
		}

		catch (Exception e) {
			// TODO: handle exception

			logs.info("order created with Quick quote functionality == FAIL" + e);
			msg.append("order created with Quick quote functionality == FAIL" + "\n");
			methods.setData("Test_Case", 8, 3, "FAIL");
		}
		logs.info("========== Quick Quote Testcase 8 : End ==========");
		msg.append("========== Quick Quote Testcase 8 : End ==========" + "\n" + "\n");
	}

}
