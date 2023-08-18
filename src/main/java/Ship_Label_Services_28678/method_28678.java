package Ship_Label_Services_28678;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class method_28678 extends BaseInit {

	static String pck, rdytime, rectime, arrtime, tndrtime;
	protected static String PUID;

	public static String getData(String sheetName, int row, int col)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		String Env = storage.getProperty("Env");
		String FilePath = null;
		if (Env.equalsIgnoreCase("Pre-Prod")) {
			FilePath = storage.getProperty("PrePRODFile");
		} else if (Env.equalsIgnoreCase("STG")) {
			FilePath = storage.getProperty("28678_ship_label_STGFile");
		} else if (Env.equalsIgnoreCase("DEV")) {
			FilePath = storage.getProperty("DEVFile");
		} else if (Env.equalsIgnoreCase("TEST")) {
			FilePath = storage.getProperty("28678_ship_label_TESTFile");
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
			FilePath = storage.getProperty("28678_ship_label_STGFile");
		} else if (Env.equalsIgnoreCase("DEV")) {
			FilePath = storage.getProperty("DEVFile");
		} else if (Env.equalsIgnoreCase("TEST")) {
			FilePath = storage.getProperty("28678_ship_label_TESTFile");
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

// --Ship label method	
	public String shipLabel(int i, int j, int z)
			throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebDriverWait waithigh = new WebDriverWait(driver, Duration.ofSeconds(80));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);
		String TrackNo = null;
		try {

			// --Ship Label
			WebElement ShipLabel = isElementPresent("TT3ShipLabel_id");
			wait.until(ExpectedConditions.elementToBeClickable(ShipLabel));
			ShipLabel.click();
			logs.info("Clicked on Ship Label Services");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form='SLForm']")));
			try {
				String svc = getData("Sheet3", z, 18);

				if (svc.equalsIgnoreCase("d3p")) {

					WebElement resetlabel = isElementPresent("reset_label_id");
					resetlabel.click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				}
			}

			catch (Exception e) {
				// TODO: handle exception
				System.out.println("no label generate for d3p ");
			}

			try {
				// --If Ship Label is not generated
				// --Select 3p Account

				Select acc = new Select(isElementPresent("TT3ACDrop_id"));
				acc.selectByIndex(i);
				logs.info("Selected 3p Account");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				Thread.sleep(1500);

				// --fetch service

				// String service = (String) js.executeScript("return
				// document.getElementById('txtCarrierName').value");
				// System.out.println("Carrier :" + service);

				String service = driver.findElement(By.xpath("(//input[@id='txtCarrierName'])[2]"))
						.getAttribute("value");
				System.out.println("Carrier :" + service);

				if (service.contains("FEDEX")) {
					// --Select Service
					Select Contacttype = new Select(isElementPresent("TT3Servicedrp_id"));
					Contacttype.selectByVisibleText("FEDEX_GROUND");
					logs.info("Selected Service");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				} else if (service.contains("UPS")) {

					Select Contacttype = new Select(isElementPresent("TT3Servicedrp_id"));
					Contacttype.selectByVisibleText("Ground");
					logs.info("Selected Service");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				}

				else if (service.contains("DHL")) {

					Select Contacttype = new Select(isElementPresent("TT3Servicedrp_id"));
					Contacttype.selectByVisibleText("Express1200");
					logs.info("Selected Service");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				}

				else if (service.contains("CANADAPOST")) {

					Select Contacttype = new Select(isElementPresent("TT3Servicedrp_id"));
					Contacttype.selectByVisibleText("Regular Parcel");
					logs.info("Selected Service");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				}
				
				if (service.contains("DHL")) {
				
				// -- Eneter account in ship label popup : ca_d1cb376247e44b9cb854dd17e1418a95
				
				Thread.sleep(1000);
				WebElement popup_account = isElementPresent("pop_acc_xpath");
				popup_account.clear();
				popup_account.sendKeys("ca_d1cb376247e44b9cb854dd17e1418a95");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				Thread.sleep(1000);
				}
				
				// --Click on Submit
				WebElement Submit = isElementPresent("TT3ShiLSubmit_id");
				wait.until(ExpectedConditions.elementToBeClickable(Submit));
				Submit.click();
				logs.info("Clicked on Submit button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Print button
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='scrollboxIframe']//button[@id='btnPrint']")));
				String TrackingNo = isElementPresent("SLSTrackNo_xpath").getText();
				logs.info("Tracking No==" + TrackingNo);

				// --Get the TrackingNo
				String inLine = TrackingNo;
				String[] lineSplits = inLine.split(":");
				TrackNo = lineSplits[1];
				logs.info("Tracking No==" + TrackNo);
				msg.append("Tracking No for Ship label ==" + TrackNo + "\n");
				setData("Test_Case", j, 5, TrackNo + "-- PASS");
			//	getScreenshot(driver, "shiplabel_tracking_" + service);

				// --Click on Print Button
				WebElement PrintBTN = isElementPresent("SLSPrintBTN_id");
				wait.until(ExpectedConditions.elementToBeClickable(PrintBTN));

				// -- store window id
				String WindowHandlebefore = driver.getWindowHandle();
				System.out.println("window handle befor : " + WindowHandlebefore);

				PrintBTN.click();
				logs.info("Clicked on Print button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form='SLForm']")));

				// Handle Print window

				for (String windHandle : driver.getWindowHandles()) {
					driver.switchTo().window(windHandle);
					System.out.println("window Id is : " + windHandle);
					logs.info("Switched to Print window");
					Thread.sleep(2000);
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					//getScreenshot(driver, "Print_shilabel_" + service);
				}
				driver.close();
				logs.info("Closed Print window");

				driver.switchTo().window(WindowHandlebefore);
				logs.info("Switched to main window");

				try {

					// --Close
					WebElement SLClose = isElementPresent("SLSCloseBtn_id");
					js.executeScript("arguments[0].click();", SLClose);
					logs.info("Clicked on Close button of ShipLabel");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					Thread.sleep(2000);
					logs.info("ShipLabel Test=PASS");
					msg.append("ShipLabel Test=PASS" + "\n");
					
					// -- click on discard save if visible
					try {
						WebElement dicard_save= isElementPresent("discard_save_id");
						if(dicard_save.isDisplayed()) {
							act.moveToElement(dicard_save).build().perform();
							act.click(dicard_save).build().perform();
							waithigh.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
						}
						logs.info("click on Discard save");
					}
					
					catch (Exception e) {
						// TODO: handle exception
						logs.info("discard save not visible");
					}
					
				} catch (Exception CLoseee) {
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					// --Close
					WebElement SLClose = isElementPresent("SLSCloseBtn_id");
					act.moveToElement(SLClose).build().perform();
					act.moveToElement(SLClose).click().perform();
					logs.info("Clicked on Close button of ShipLabel");
					logs.info("ShipLabel Test=PASS");
					msg.append("ShipLabel Test=PASS" + "\n");
					Thread.sleep(2000);
					
					// -- click on discard save if visible
					try {
						WebElement dicard_save= isElementPresent("discard_save_id");
						if(dicard_save.isDisplayed()) {
							act.moveToElement(dicard_save).build().perform();
							act.click(dicard_save).build().perform();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
						}
						logs.info("click on Discard save");
					}
					
					catch (Exception e) {
						// TODO: handle exception
						logs.info("discard save not visible");
					}
				}

			}

			catch (Exception eShipLabel) {
				// --If Ship Label is generated

				// --Print button
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='scrollboxIframe']//button[@id='btnPrint']")));
				String TrackingNo = isElementPresent("SLSTrackNo_xpath").getText();
				logs.info("Tracking No==" + TrackingNo);
				String service = getData("Sheet3", z, 18);

				// --Get the TrackingNo
				String inLine = TrackingNo;
				String[] lineSplits = inLine.split(":");
				TrackNo = lineSplits[1];
				logs.info("Tracking No==" + TrackNo);
				msg.append("Tracking No for Ship label ==" + TrackNo + "\n");
				setData("Test_Case", j, 5, TrackNo + "-- PASS");
				// getScreenshot(driver, "shiplabel_tracking_" + service);
				// -- Click on Ok of pickup error popup if visible
				try {

					if (service.contains("H3P")) {
					WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(80));
					wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnSignIn']")));
					WebElement popup_ok = isElementPresent("popup_error_pickup_ok_xpath");
					if (popup_ok.isDisplayed()) {
						getScreenshot(driver, "DHL_popup");
						act.moveToElement(popup_ok).build().perform();
						Thread.sleep(1000);
						act.click(popup_ok).build().perform();
						logs.info("Click on Ok for opened popup");
						Thread.sleep(1500);
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
						 //-- CLick on Save changes button
						WebElement save_change = isElementPresent("save_change_id");
						
						if(save_change.isDisplayed()) {
							// - -Click button
							getScreenshot(driver, "Shiplable_changes_save");
							save_change.click();
							logs.info("Click on Save changes button");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							
						}
						
						else {
							logs.info("Save changes button is not visible");
						}
						
					}
					}
				} catch (Exception e) {
					// TODO: handle exception
					logs.info("No popup display");
				}

				// --Click on Print Button
				WebElement PrintBTN = isElementPresent("SLSPrintBTN_id");
				wait.until(ExpectedConditions.elementToBeClickable(PrintBTN));

				// -- store window id
				String WindowHandlebefore = driver.getWindowHandle();
				System.out.println("window handle befor : " + WindowHandlebefore);
				// --click on print button

				PrintBTN.click();
				logs.info("Clicked on Print button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form='SLForm']")));

				// Handle Print window

				for (String windHandle : driver.getWindowHandles()) {
					driver.switchTo().window(windHandle);
					System.out.println("window Id is : " + windHandle);
					logs.info("Switched to Print window");
					Thread.sleep(2000);
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					//getScreenshot(driver, "Print_shiplabel_" + service);
				}

				driver.close();
				logs.info("Closed Print window");

				driver.switchTo().window(WindowHandlebefore);
				logs.info("Switched to main window");
				
				try {

					// --Close
					WebElement SLClose = isElementPresent("SLSCloseBtn_id");
					js.executeScript("arguments[0].click();", SLClose);
					logs.info("Clicked on Close button of ShipLabel");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					Thread.sleep(2000);
				} catch (Exception CLoseee) {
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					// --Close
					WebElement SLClose = isElementPresent("SLSCloseBtn_id");
					act.moveToElement(SLClose).build().perform();
					act.moveToElement(SLClose).click().perform();
					logs.info("Clicked on Close button of ShipLabel");
					Thread.sleep(2000);
				}

			}

		} catch (Exception noShipLabel) {
			logs.info("Ship label popup not opned : " + noShipLabel);
			getScreenshot(driver, "Ship_label_fail_error");
			WebElement ship_pop_close = isElementPresent("ship_pop_close_id");
			act.moveToElement(ship_pop_close).build().perform();
			Thread.sleep(1000);
			getScreenshot(driver, "SHiplabel_fail_error");
			ship_pop_close.click();
			logs.info("Close shiplabel popup");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			logs.info("ShipLabel Test=FAIL");
			msg.append("ShipLabel Test=FAIL" + "\n");
			setData("Test_Case", j, 5, TrackNo + "-- FAIL");

		}

		return TrackNo;
	}

	// --Return label method
	public String returnLabel(int i, int j, int z)
			throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);
		String returnTrackNo = null;
		try {
			// --Ship Label
			try {
				// -- CLick on Save changes button
				WebElement save_change = isElementPresent("save_change_id");

				if (save_change.isDisplayed()) {
					// - -Click button
					getScreenshot(driver, "Shiplable_changes_save");
					save_change.click();
					logs.info("Click on Save changes button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				}

				else {
					logs.info("Save changes button is not visible");
				}
			} catch (Exception e) {
				// TODO: handle exception
				logs.info("Save changes button is not visible");
			}
			
			
			WebElement ShipLabel = isElementPresent("TT3ShipLabel_id");
			wait.until(ExpectedConditions.elementToBeClickable(ShipLabel));
			ShipLabel.click();
			logs.info("Clicked on Ship Label Services");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form='SLForm']")));

			try {
				try {
				// --Select return label radio button
				WebElement return_radio = isElementPresent("return_radio_xpath");
				return_radio.click();
				logs.info("return radio button selected");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				}
				
				catch (Exception e) {
					
					// --Select return label radio button not work with click method
					logs.info("Select return label radio button not work with normal click method "+e);
					WebElement return_radio = isElementPresent("return_radio_xpath");
					js.executeScript("arguments[0].click();", return_radio);
					logs.info("return radio button selected");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				}
				
				
				String Carrier = driver.findElement(By.xpath("(//input[@id='txtCarrierName'])[2]"))
						.getAttribute("value");
				System.out.println("Carrier :" + Carrier);
				
				if (Carrier.contains("DHL")) {
				
				// -- Eneter account in ship label popup : ca_d1cb376247e44b9cb854dd17e1418a95
				
				Thread.sleep(1000);
				WebElement return_label_account = isElementPresent("pop_acc_xpath");
				return_label_account.clear();
				return_label_account.sendKeys("ca_d1cb376247e44b9cb854dd17e1418a95");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				Thread.sleep(1000);
				}
				
				
				// -- select pkg for return

				WebElement retun_pkg_checkbox = isElementPresent("return_pkg_id");

				act.moveToElement(retun_pkg_checkbox).build().perform();
				act.click(retun_pkg_checkbox).build().perform();
				logs.info("return package checkbox selected");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Click on Submit
				WebElement Submit = isElementPresent("TT3ShiLSubmit_id");
				wait.until(ExpectedConditions.elementToBeClickable(Submit));
				Submit.click();
				logs.info("Clicked on Submit button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Print button
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='scrollboxIframe']//button[@id='btnPrint']")));
				String return_TrackingNo = isElementPresent("SLSTrackNo_xpath").getText();
				logs.info("Return Tracking No ==" + return_TrackingNo);
				String service = getData("Sheet3", z, 18);

				// --Get the TrackingNo
				String inLine = return_TrackingNo;
				String[] lineSplits = inLine.split(":");
				returnTrackNo = lineSplits[1];
				logs.info("Return Tracking No ==" + returnTrackNo);
				msg.append("Return Tracking No == " + returnTrackNo + "\n");

				setData("Test_Case", j, 6, returnTrackNo + "-- PASS");
				//getScreenshot(driver, "Return_tracking_" + service);
				// --Click on Print Button
				WebElement PrintBTN = isElementPresent("SLSPrintBTN_id");
				wait.until(ExpectedConditions.elementToBeClickable(PrintBTN));

				// -- store window id
				String WindowHandlebefore = driver.getWindowHandle();
				System.out.println("window handle befor : " + WindowHandlebefore);

				PrintBTN.click();
				logs.info("Clicked on Print button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form='SLForm']")));

				// Handle Print window

				for (String windHandle : driver.getWindowHandles()) {
					driver.switchTo().window(windHandle);
					System.out.println("window Id is : " + windHandle);
					logs.info("Switched to Print window");
					Thread.sleep(2000);
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
				//	getScreenshot(driver, "print_return_label_" + service);
				}
				driver.close();
				logs.info("Closed Print window");

				driver.switchTo().window(WindowHandlebefore);
				Thread.sleep(1000);
				logs.info("Switched to main window");

				try {

					// --Close
					WebElement SLClose = isElementPresent("SLSCloseBtn_id");
					js.executeScript("arguments[0].click();", SLClose);
					logs.info("Clicked on Close button of ShipLabel");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					Thread.sleep(2000);
				} catch (Exception CLoseee) {
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					// --Close
					WebElement SLClose = isElementPresent("SLSCloseBtn_id");
					act.moveToElement(SLClose).build().perform();
					act.moveToElement(SLClose).click().perform();
					logs.info("Clicked on Close button of ShipLabel");
					Thread.sleep(2000);
				}

			}

			catch (Exception eShipLabel) {
				// --If Ship Label is generated

				// --Print button
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='scrollboxIframe']//button[@id='btnPrint']")));
				String return_TrackingNo = isElementPresent("SLSTrackNo_xpath").getText();
				logs.info("Return Tracking No ==" + return_TrackingNo);
				String service = getData("Sheet3", z, 18);

				// --Get the TrackingNo
				String inLine = return_TrackingNo;
				String[] lineSplits = inLine.split(":");
				returnTrackNo = lineSplits[1];
				logs.info("Return Tracking No ==" + returnTrackNo);
				msg.append("Return Tracking No == " + returnTrackNo + "\n");
				setData("Test_Case", j, 6, returnTrackNo + "-- PASS");
		//		getScreenshot(driver, "return_tracking_" + service);

				// --Click on Print Button
				WebElement PrintBTN = isElementPresent("SLSPrintBTN_id");
				wait.until(ExpectedConditions.elementToBeClickable(PrintBTN));

				// -- store window id
				String WindowHandlebefore = driver.getWindowHandle();
				System.out.println("window handle befor : " + WindowHandlebefore);
				// --click on print button

				PrintBTN.click();
				logs.info("Clicked on Print button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form='SLForm']")));

				// Handle Print window

				for (String windHandle : driver.getWindowHandles()) {
					driver.switchTo().window(windHandle);
					System.out.println("window Id is : " + windHandle);
					logs.info("Switched to Print window");
					Thread.sleep(2000);
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
				//	getScreenshot(driver, "print_return_label_" + service);
				}

				driver.close();
				logs.info("Closed Print window");

				driver.switchTo().window(WindowHandlebefore);
				logs.info("Switched to main window");
				
				try {

					// --Close
					WebElement SLClose = isElementPresent("SLSCloseBtn_id");
					js.executeScript("arguments[0].click();", SLClose);
					logs.info("Clicked on Close button of ShipLabel");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					Thread.sleep(2000);
				} catch (Exception CLoseee) {
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					// --Close
					WebElement SLClose = isElementPresent("SLSCloseBtn_id");
					act.moveToElement(SLClose).build().perform();
					act.moveToElement(SLClose).click().perform();
					logs.info("Clicked on Close button of ShipLabel");
					Thread.sleep(2000);
				}


			}

		} catch (Exception noReturnLabel) {
			logs.info("Return Label popup not opned : " + noReturnLabel);
			getScreenshot(driver, "Return_label_fail_error");
			WebElement ship_pop_close = isElementPresent("ship_pop_close_id");
			act.moveToElement(ship_pop_close).build().perform();
			Thread.sleep(1000);
			ship_pop_close.click();
			logs.info("Close Return label popup");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			logs.info("ReturnLabel Test=FAIL");
			msg.append("ReturnLabel Test=FAIL" + "\n");
			setData("Test_Case", j, 6, returnTrackNo + "-- FAIL");

		}

		return returnTrackNo;
	}

	// -- Repair label method
	public String repairLabel(int i, int j, int z)
			throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);
		String repairTrackNo = null;
		try {
			// --Ship Label
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			WebElement ShipLabel = isElementPresent("TT3ShipLabel_id");
			wait.until(ExpectedConditions.elementToBeClickable(ShipLabel));
			ShipLabel.click();
			logs.info("Clicked on Ship Label Services");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form='SLForm']")));

			try {
				// --Select repair label radio button
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				WebElement repair_radio = isElementPresent("repair_radio_xpath");
				repair_radio.click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				logs.info("repair label selected");
				
						
				// -- enter del compny name
				WebElement del_comp = isElementPresent("rep_del_comp_id");
				del_comp.sendKeys("repair label comapany", Keys.TAB);
				logs.info("Del compny name entered");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// -- Enter delivery address 1

				WebElement del_add1 = isElementPresent("rep_del_add1_id");
				del_add1.sendKeys("2100 Space Park Dr", Keys.TAB);
				logs.info("Del address entered");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Enter Delivery City

				String pu_city = (String) js.executeScript("return document.getElementById('txtCity').value");
				WebElement del_city = isElementPresent("rep_del_city_id");
				del_city.sendKeys(pu_city, Keys.TAB);
				logs.info("Del City entered");
				// --Enter Deliver State
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				String pu_state = (String) js.executeScript("return document.getElementById('txtState').value");
				WebElement del_state = isElementPresent("rep_del_state_id");
				del_state.sendKeys(pu_state, Keys.TAB);
				logs.info("Del State entered");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Enter Delivery Zipcode

				String pu_zip = (String) js.executeScript("return document.getElementById('txtZipCode').value");
				WebElement del_zip = isElementPresent("rep_del_zip_id");
				del_zip.sendKeys(pu_zip, Keys.TAB);
				logs.info("Del Zip entered");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Enter Delivery Country

				String pu_country = (String) js.executeScript("return document.getElementById('txtCountry').value");
				WebElement del_country = isElementPresent("rep_del_country_id");
				del_country.sendKeys(pu_country, Keys.TAB);
				logs.info("Del Country entered");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				// -- Enter delivery Person to see

				WebElement del_person = isElementPresent("rep_del_person_id");
				del_person.sendKeys("Pshah", Keys.TAB);
				logs.info("Del Per To See entered");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Enter Delivery Phone no

				WebElement del_phno = isElementPresent("rep_del_phno_id");
				del_phno.sendKeys("112233445566", Keys.TAB);
				logs.info("Del phno entered");
				Thread.sleep(1000);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				String service = driver.findElement(By.xpath("//input[@ng-model='ShipLabelBO.CourierName']"))
						.getAttribute("value");
				System.out.println("Carrier :" + service);
				Thread.sleep(2000);

				if (service.contains("FEDEX")) {
					// --Select Service
					Select Contacttype = new Select(isElementPresent("TT3Servicedrp_id"));
					Contacttype.selectByVisibleText("FEDEX_GROUND");
					logs.info("Selected Service");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				} else if (service.contains("UPS")) {

					Select Contacttype = new Select(isElementPresent("TT3Servicedrp_id"));
					Contacttype.selectByVisibleText("Ground");
					logs.info("Selected Service");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				}

				else if (service.contains("DHL")) {

					Select Contacttype = new Select(isElementPresent("TT3Servicedrp_id"));
					Contacttype.selectByValue("string:DomesticExpress1200");

					logs.info("Selected Service");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				}

				else if (service.contains("CANADAPOST")) {

					Select Contacttype = new Select(isElementPresent("TT3Servicedrp_id"));
					Contacttype.selectByVisibleText("Regular Parcel");
					logs.info("Selected Service");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				}
				
				String Carrier = driver.findElement(By.xpath("(//input[@id='txtCarrierName'])[2]"))
						.getAttribute("value");
				System.out.println("Carrier :" + Carrier);
				
				if (Carrier.contains("DHL")) {
					
					// -- Eneter account in ship label popup : ca_d1cb376247e44b9cb854dd17e1418a95
					
					Thread.sleep(1000);
					WebElement repair_label_account = isElementPresent("pop_acc_xpath");
					repair_label_account.clear();
					repair_label_account.sendKeys("ca_d1cb376247e44b9cb854dd17e1418a95");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					Thread.sleep(1000);
					}

				// --Click on Submit
				WebElement Submit = isElementPresent("TT3ShiLSubmit_id");
				wait.until(ExpectedConditions.elementToBeClickable(Submit));
				Submit.click();
				logs.info("Clicked on Submit button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Print button
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='scrollboxIframe']//button[@id='btnPrint']")));
			Thread.sleep(1000);
				String Repair_TrackingNo = isElementPresent("SLSTrackNo_xpath").getText();
				Thread.sleep(1000);
				logs.info("Repair Tracking No ==" + Repair_TrackingNo);
				String services = getData("Sheet3", z, 18);
				// --Get the TrackingNo
				String inLine = Repair_TrackingNo;
				String[] lineSplits = inLine.split(":");
				repairTrackNo = lineSplits[1];
				logs.info("Repair Tracking No ==" + repairTrackNo);
				msg.append("Repair Tracking No == " + repairTrackNo + "\n");

				setData("Test_Case", j, 7, repairTrackNo + "-- PASS");
			//	getScreenshot(driver, "repair_tracking_" + services);
				// --Click on Print Button
				WebElement PrintBTN = isElementPresent("SLSPrintBTN_id");
				wait.until(ExpectedConditions.elementToBeClickable(PrintBTN));

				// -- store window id
				String WindowHandlebefore = driver.getWindowHandle();
				System.out.println("window handle befor : " + WindowHandlebefore);

				PrintBTN.click();
				logs.info("Clicked on Print button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form='SLForm']")));

				// Handle Print window

				for (String windHandle : driver.getWindowHandles()) {
					driver.switchTo().window(windHandle);
					System.out.println("window Id is : " + windHandle);
					logs.info("Switched to Print window");
					Thread.sleep(2000);
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					//getScreenshot(driver, "print_repair_label_" + services);
				}
				driver.close();
				logs.info("Closed Print window");

				driver.switchTo().window(WindowHandlebefore);
				logs.info("Switched to main window");

				try {

					// --Close
					WebElement SLClose = isElementPresent("SLSCloseBtn_id");
					js.executeScript("arguments[0].click();", SLClose);
					logs.info("Clicked on Close button of ShipLabel");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					Thread.sleep(2000);
				} catch (Exception CLoseee) {
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					// --Close
					WebElement SLClose = isElementPresent("SLSCloseBtn_id");
					act.moveToElement(SLClose).build().perform();
					act.moveToElement(SLClose).click().perform();
					logs.info("Clicked on Close button of ShipLabel");
					Thread.sleep(2000);
				}

			}

			catch (Exception eShipLabel) {
				// --If Ship Label is generated
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				// --Print button
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='scrollboxIframe']//button[@id='btnPrint']")));
				Thread.sleep(1500);
				
				String Repair_TrackingNo = isElementPresent("SLSTrackNo_xpath").getText();
				logs.info("Repair Tracking No ==" + Repair_TrackingNo);
				String services = getData("Sheet3", z, 18);
				// --Get the TrackingNo
				String inLine = Repair_TrackingNo;
				String[] lineSplits = inLine.split(":");
				repairTrackNo = lineSplits[1];
				logs.info("Repair Tracking No ==" + repairTrackNo);
				msg.append("Repair Tracking No == " + repairTrackNo + "\n");
				setData("Test_Case", j, 7, repairTrackNo + "-- PASS");
			//	getScreenshot(driver, "repair_tracking_" + services);
				// --Click on Print Button
				WebElement PrintBTN = isElementPresent("SLSPrintBTN_id");
				wait.until(ExpectedConditions.elementToBeClickable(PrintBTN));

				// -- store window id
				String WindowHandlebefore = driver.getWindowHandle();
				System.out.println("window handle befor : " + WindowHandlebefore);
				// --click on print button

				PrintBTN.click();
				logs.info("Clicked on Print button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form='SLForm']")));

				// Handle Print window

				for (String windHandle : driver.getWindowHandles()) {
					driver.switchTo().window(windHandle);
					System.out.println("window Id is : " + windHandle);
					logs.info("Switched to Print window");
					Thread.sleep(2000);
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					//getScreenshot(driver, "print_repair_label_" + services);
				}

				driver.close();
				logs.info("Closed Print window");

				driver.switchTo().window(WindowHandlebefore);
				logs.info("Switched to main window");
				
				try {

					// --Close
					WebElement SLClose = isElementPresent("SLSCloseBtn_id");
					js.executeScript("arguments[0].click();", SLClose);
					logs.info("Clicked on Close button of ShipLabel");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					Thread.sleep(2000);
				} catch (Exception CLoseee) {
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					// --Close
					WebElement SLClose = isElementPresent("SLSCloseBtn_id");
					act.moveToElement(SLClose).build().perform();
					act.moveToElement(SLClose).click().perform();
					logs.info("Clicked on Close button of ShipLabel");
					Thread.sleep(2000);
				}

			}

		} catch (Exception noRepairLabel) {
			logs.info("Repair Label popup not opned : " + noRepairLabel);
			getScreenshot(driver, "Repair_label_fail_error");
			WebElement ship_pop_close = isElementPresent("ship_pop_close_id");
			act.moveToElement(ship_pop_close).build().perform();
			Thread.sleep(1000);
			ship_pop_close.click();
			logs.info("Close repair label popup");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			logs.info("RepairLabel Test=FAIL");
			msg.append("RepairLabel Test=FAIL" + "\n");
			setData("Test_Case", 1, 7, repairTrackNo + "-- FAIL");

		}

		return repairTrackNo;
	}

	public static void inventorySearch(int i)
			throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
		// Actions act = new Actions(driver);

		String PartName = getData("Sheet3", i, 37);
		// --Click on Search Parts button
		WebElement PartsSearch = isElementPresent("OCPartSearch_id");
		wait.until(ExpectedConditions.elementToBeClickable(PartsSearch));
		jse.executeScript("arguments[0].click();", PartsSearch);
		logs.info("Clicked on Parts search button");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"modal-dialog modal-lg\"]")));
		getScreenshot(driver, "InventorySearch_" + i);

		// --Click on Advanced Search
		WebElement AdSearch = isElementPresent("OCPSAdvanceSearch_id");
		wait.until(ExpectedConditions.elementToBeClickable(AdSearch));
		jse.executeScript("arguments[0].click();", AdSearch);
		logs.info("Clicked on Advanced search button");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		/*
		 * // --CLick on FSL Dropdown String Env = storage.getProperty("Env");
		 * System.out.println("Env " + Env); String FSLValue = null; if
		 * (Env.equalsIgnoreCase("STG")) { FSLValue = "AUTOMATION RV (F5505)"; } else if
		 * (Env.equalsIgnoreCase("Pre-Prod")) { FSLValue =
		 * "ORD - MNX DC: CHICAGO IL (F5099)"; }
		 */

		WebElement FSL = isElementPresent("OCPSASFSlDrp_id");
		wait.until(ExpectedConditions.elementToBeClickable(FSL));
		Select FSLdrp = new Select(FSL);
		FSLdrp.selectByValue("string:5443");
		logs.info("Selected FSL");

		// --Field 1
		WebElement Field1 = isElementPresent("OCPSField1_id");
		wait.until(ExpectedConditions.elementToBeClickable(Field1));
		Field1.clear();
		Field1.sendKeys(PartName);
		logs.info("Entered value of Field 1");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// --CLick on Search button
		WebElement PartSearch = isElementPresent("OCPSASPartSearch_id");
		wait.until(ExpectedConditions.elementToBeClickable(PartSearch));
		jse.executeScript("arguments[0].click();", PartSearch);
		logs.info("Clicked on search button");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// --CLick on Add button
		WebElement AddParts = isElementPresent("OCPSASAddPart_xpath");
		wait.until(ExpectedConditions.elementToBeClickable(AddParts));
		jse.executeScript("arguments[0].click();", AddParts);
		logs.info("Clicked on Add Part button");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// --Parts added or not
		String totalQty = isElementPresent("OCPSAddedQty_id").getText();
		logs.info("Total Qty of added parts==" + totalQty);

		if (totalQty.equalsIgnoreCase("1")) {
			logs.info("Part is added successfully==PASS");

		} else {
			logs.info("Part is not added successfully==FAIL, Add Part button is not working");
			getScreenshot(driver, "PartNotAdded_" + i);

		}

		// --CLick on Add button
		WebElement SavePart = isElementPresent("OCPSSave_id");
		wait.until(ExpectedConditions.elementToBeClickable(SavePart));
		jse.executeScript("arguments[0].click();", SavePart);
		logs.info("Clicked on Save Part button");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// --Parts Saved or not
		String PartQty = isElementPresent("OCPartsQty_id").getText();
		logs.info("Qty of added parts==" + PartQty);

		if (PartQty.equalsIgnoreCase("1")) {
			logs.info("Part is saved successfully==PASS");

		} else {
			logs.info("Part is not added successfully==FAIL, Save Part button is not working");
			getScreenshot(driver, "PartNotSaved_" + i);

		}

	}

	public void orderCreation(int i)
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException, InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));// wait time
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));// wait time
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

			// --Get Service
			ServiceID = getData("Sheet3", i, 18);
			System.out.println("Service== " + ServiceID);
			logs.info("=====Service:- " + ServiceID + "=====");
			msg.append("Service:- " + ServiceID );

			// Enter Caller
			String Caller = getData("Sheet3", i, 0);
			isElementPresent("OCCallerName_id").clear();
			isElementPresent("OCCallerName_id").sendKeys(Caller);
			logs.info("Entered CallerName");

			// Enter Phone
			String Phone = getData("Sheet3", i, 1);
			isElementPresent("OCContactPh_id").clear();
			isElementPresent("OCContactPh_id").sendKeys(Phone);
			logs.info("Entered Contact/Phone");

			// Enter Account#
			String Account = getData("Sheet3", i, 2);
			isElementPresent("OCCustCode_id").clear();
			isElementPresent("OCCustCode_id").sendKeys(Account);
			isElementPresent("OCCustCode_id").sendKeys(Keys.TAB);
			logs.info("Entered Customer Code");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			try {
				WebElement DocDialogue = isElementPresent("DRVDialforDoc_xpath");
				wait.until(ExpectedConditions.visibilityOfAllElements(DocDialogue));
				logs.info("Dialogue is displayed for Customer");
				WebElement BTNOk = isElementPresent("DRVDialOKbtn_id");
				wait.until(ExpectedConditions.elementToBeClickable(BTNOk));
				jse.executeScript("arguments[0].click();", BTNOk);
				logs.info("Clicked on OK button");

			} catch (Exception Doc) {
				logs.info("Dialogue is not displayed for Customer");

			}

			if (i >= 10) {
				// --Select Part
				inventorySearch(i);

				// --Click on delivery address tab
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				Thread.sleep(1500);
				WebElement del_add = isElementPresent("delAddress_xpath");
				del_add.click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// -- select country
				Select acc = new Select(isElementPresent("del_country_id"));
				acc.selectByValue("string:DE");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Del Zip
				String DLZip = getData("Sheet3", i, 11);
				isElementPresent("OCDLZip_id").clear();
				isElementPresent("OCDLZip_id").sendKeys(DLZip);
				isElementPresent("OCDLZip_id").sendKeys(Keys.TAB);
				Thread.sleep(2000);
				logs.info("Entered DL Zip");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Click on Delivery Address Tab
				WebElement DLAddTab = isElementPresent("OCDLAdd_id");
				wait.until(ExpectedConditions.elementToBeClickable(DLAddTab));
				jse.executeScript("arguments[0].click();", DLAddTab);
				logs.info("Clicked on Delivery Address Tab");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --DEL Company
				String DelCompany = getData("Sheet3", i, 12);
				isElementPresent("OCDLComp_id").clear();
				isElementPresent("OCDLComp_id").sendKeys(DelCompany);
				logs.info("Entered DL Company");

				// --Del Address
				WebElement DL = isElementPresent("OCDLAdd_id");
				jse.executeScript("arguments[0].click();", DL);
				logs.info("Entered DL Address");

				// --DEL Address1
				String DLAddress1 = getData("Sheet3", i, 13);
				isElementPresent("OCDLAddL1_id").clear();
				isElementPresent("OCDLAddL1_id").sendKeys(DLAddress1);
				logs.info("Entered DL Address Line1");

				// String DLAddr2 = getData("Sheet3", i, 14);
				// driver.findElement(By.id("txtDelAddrLine2")).sendKeys(DLAddr2);

				// --DL Attention
				String DLAttn = getData("Sheet3", i,15);
				isElementPresent("OCDLAtt_id").clear();
				isElementPresent("OCDLAtt_id").sendKeys(DLAttn);
				logs.info("Entered DL Attention");

				// --DL Phone
				String DLPhone = getData("Sheet3", i, 16);
				isElementPresent("OCDLPhone_id").clear();
				isElementPresent("OCDLPhone_id").sendKeys(DLPhone);
				logs.info("Entered DL Phone");

				// --DL Miles
				String dmi = isElementPresent("OCDLMiles_id").getAttribute("value");
				logs.info("DL Miles==" + dmi);

				// String DLInst = getData("Sheet3", i, 17);
				// driver.findElement(By.id("txtDLPhone")).sendKeys(DLInst);
				// String srv =
				// driver.findElement(By.id("idNewOrderServiceId")).getAttribute("value");

				// --Get the ServiceID
				String ServID = getData("Sheet3", i, 18);

				// --Enter the ServiceID
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idNewOrderServiceId")));
				WebElement Service = isElementPresent("OCServiceID_id");
				wait.until(ExpectedConditions.elementToBeClickable(Service));
				Service.clear();
				Service.sendKeys(ServID);
				Service.sendKeys(Keys.TAB);

				if (ServID.equalsIgnoreCase("D3P") || ServID.equalsIgnoreCase("3PLAST")) {
					// --Select 3P Account
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmb3PAccount")));
					WebElement p3acc = isElementPresent("EO3PAcDrop_id");
					wait.until(ExpectedConditions.elementToBeClickable(p3acc));
					Select T3pAc = new Select(p3acc);

					// -- select d3p service (express 1200)
					T3pAc.selectByVisibleText("");
					logs.info("Selected 3P account");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --select FedEx service
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("scrollCarrierService")));
					WebElement ServiceSelect = isElementPresent("OCFDXServiceSelect_id");
					act.moveToElement(ServiceSelect).build().perform();
					wait.until(ExpectedConditions.elementToBeClickable(ServiceSelect));
					jse.executeScript("arguments[0].click();", ServiceSelect);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					logs.info("Selected FedEx service");

					// --Select Drop Off Location
					selectDropOffLoc();

				} else if (ServID.equalsIgnoreCase("H3P")) {
					// --Select 3P Account
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmb3PAccount")));
					WebElement p3acc = isElementPresent("EO3PAcDrop_id");
					wait.until(ExpectedConditions.elementToBeClickable(p3acc));
					Select T3pAc = new Select(p3acc);
					T3pAc.selectByValue("string:DHL");
					logs.info("Selected 3P account");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --select DHL service
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("scrollCarrierService")));
					WebElement ServiceSelect = isElementPresent("express1200_id");
					act.moveToElement(ServiceSelect).build().perform();
					wait.until(ExpectedConditions.elementToBeClickable(ServiceSelect));
					jse.executeScript("arguments[0].click();", ServiceSelect);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					logs.info("Selected DHL service");
				}

				// --Scroll Up
				r.keyPress(KeyEvent.VK_TAB);
				jse.executeScript("window.scrollBy(0,250)", "");
				Thread.sleep(2000);

				// --Total Mileage
				String tmile = isElementPresent("OCTotalMil_id").getAttribute("value");
				logs.info("Total Mileage==" + tmile);

				setData("Sheet3", i, 27, dmi);
				setData("Sheet3", i, 29, tmile);

				// --Scroll down
				/*
				 * r.keyPress(KeyEvent.VK_TAB); jse.executeScript("window.scrollBy(0,250)", "");
				 * Thread.sleep(2000);
				 */

				// --GetScreenShot
				getScreenshot(driver, "CreateOrder_" + ServID);

				// --Click on Create Order button
				WebElement order = isElementPresent("OCSPLCreateOrder_id");
				jse.executeScript("arguments[0].scrollIntoView();", order);
				Thread.sleep(2000);
				act.moveToElement(order).build().perform();
				wait.until(ExpectedConditions.elementToBeClickable(order));
				jse.executeScript("arguments[0].click();", order);
				logs.info("Click on Create Order button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				Thread.sleep(2000);

				try {
					wait2.until(ExpectedConditions
							.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"modal-dialog\"]")));
					String DialogueContent = isElementPresent("CPUDContent_xpath").getText();
					logs.info("Content of the Dialogue is==" + DialogueContent);

					try {
						// --CLick on Yes button
						WebElement YesProceed = isElementPresent("CPUDYesPrc_xpath");
						wait2.until(ExpectedConditions.elementToBeClickable(YesProceed));
						jse.executeScript("arguments[0].click();", YesProceed);
						logs.info("Click on Yes Proceed button");
						wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
						wait2.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"modal-dialog\"]")));

					} catch (Exception YesBTN) {
						// --CLick on Yes button
						WebElement YesProceed = isElementPresent("CPUDYesPrc_xpath");
						wait2.until(ExpectedConditions.elementToBeClickable(YesProceed));
						act.moveToElement(YesProceed).click().build().perform();
						logs.info("Click on Yes Proceed button");
						wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					}

				} catch (Exception e) {
					logs.info("Confirmation message for warehouse is not displayed");
				}

			}
			Thread.sleep(5000);
			WebElement PickUPID = isElementPresent("OCPickuPID_xpath");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
//			wait.until(ExpectedConditions.visibilityOf(PickUPID));
//			wait.until(ExpectedConditions.elementToBeClickable(PickUPID));
			String pck = PickUPID.getText();
			System.out.println("Pickup = " + pck);
			logs.info("=====Pickup =" + pck + "=====" + "\n");
			msg.append("Pickup ID : " + pck );

			// --Set PickUPID
			setData("Sheet3", i, 32, pck);
			setData("Test_Case", 2, 4, pck);

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

	public void selectDropOffLoc() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);

		// --Select Drop Off Location
		try {
			WebElement dropOfLoc = isElementPresent("EOSelDropOfLoc_id");
			act.moveToElement(dropOfLoc).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(dropOfLoc));
			jse.executeScript("arguments[0].click();", dropOfLoc);

		} catch (Exception e) {
			WebElement dropOfLoc = isElementPresent("EODropOfLoc_id");
			act.moveToElement(dropOfLoc).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(dropOfLoc));
			jse.executeScript("arguments[0].click();", dropOfLoc);

		}
		Thread.sleep(5000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		logs.info("Click on Select Drop-Off Location");

		// --Select 1st address
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form=\"FDXUPSFOrm\"]")));
		WebElement AsAddress = isElementPresent("EODrpLoc1stLoc_id");
		wait.until(ExpectedConditions.elementToBeClickable(AsAddress));
		jse.executeScript("arguments[0].click();", AsAddress);
		logs.info("Select 1st drop off location");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

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
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"ajax-loadernew\"]")));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("welcomecontent")));
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

	public void searchJob(int i) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));// wait time
		Actions act = new Actions(driver);

		try {
			// Enter JobID#
			wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			PUID = getData("Sheet3", i, 32);
			Thread.sleep(1000);
			setData("Test_Case", 2, 4, PUID);

			isElementPresent("TLSearch_id").clear();
			isElementPresent("TLSearch_id").sendKeys(PUID);
			isElementPresent("TLSearch_id").sendKeys(Keys.TAB);

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			WebElement Search = isElementPresent("TLSearchButton_id");
			wait.until(ExpectedConditions.elementToBeClickable(Search));
			jse.executeScript("arguments[0].click();", Search);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			try {
				getScreenshot(driver, "H3PJob_After_TenderTo3P");

				List<WebElement> Jobs = driver
						.findElements(By.xpath("//*[contains(@aria-label,'Pickup #,')]//label[@id=\"lblDateTime\"]"));
				for (int job = 0; job < Jobs.size(); job++) {
					String PickupID = Jobs.get(job).getText();
					String[] PickValue = PickupID.split("N");
					String PickID = PickValue[1];
					logs.info("Searched PickUpID==" + PickID);
					if (PickID.equalsIgnoreCase(PUID)) {
						Jobs.get(job).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					}

				}

				logs.info("Same job is displayed with 2 status==PASS");
				// msg.append("Same job is displayed with 2 status==PASS" + "\n");

				// --Get StageName
			} catch (Exception eTenderTo3P) {
				logs.info("Same job is not displayed with 2 status");
				// msg.append("Same job is not displayed with 2 status" + "\n");

				// --Get StageName

			}

		} catch (Exception eTasklog) {
			// --Go To Operations
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a_operations")));
			WebElement Operations = isElementPresent("Operations_id");
			act.moveToElement(Operations).click().perform();
			logs.info("Clicked on Operations");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"OpenCloseClass dropdown open\"]//ul")));

			// --Go to TaskLog
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a_TaskLog")));
			isElementPresent("OpTaskLog_id").click();
			logs.info("Clicked on TaskLog");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			getScreenshot(driver, "TaskLog");

			// Enter JobID#
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			PUID = getData("Sheet3", i, 32);
			setData("Test_Case", 2, 4, PUID);
			logs.info("PickUpID fetch from sheet is =" + PUID + "\n");
			isElementPresent("TLSearch_id").clear();
			isElementPresent("TLSearch_id").sendKeys(PUID);
			isElementPresent("TLSearch_id").sendKeys(Keys.TAB);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			WebElement Search = isElementPresent("TLSearchButton_id");
			wait.until(ExpectedConditions.elementToBeClickable(Search));
			logs.info("Click on Search button of tasklog");
			jse.executeScript("arguments[0].click();", Search);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			try {
				getScreenshot(driver, "tasklog_screen");

				List<WebElement> Jobs = driver
						.findElements(By.xpath("//*[contains(@aria-label,'Pickup #,')]//label[@id=\"lblDateTime\"]"));
				for (int job = 0; job < Jobs.size(); job++) {
					String PickupID = Jobs.get(job).getText();
					String[] PickValue = PickupID.split("N");
					String PickID = PickValue[1];
					logs.info("Searched PickUpID==" + PickID);
					if (PickID.equalsIgnoreCase(PUID)) {
						Jobs.get(job).click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					}

				
				/*
				 * // --Click on Job Name WebElement JobName =
				 * isElementPresent("TLH3PJobName_id");
				 * wait.until(ExpectedConditions.elementToBeClickable(JobName));
				 * JobName.click(); logs.info("Clicked on Job Name");
				 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")
				 * ));
				 */
				logs.info("Same job is displayed with 2 status==PASS");
				// msg.append("Same job is displayed with 2 status==PASS" + "\n");
				}
				// --Get StageName
			} catch (Exception e) {
				logs.info("Same job is not displayed with 2 status "+e);
				// msg.append("Same job is not displayed with 2 status" + "\n");

				// --Get StageName

			}

		}

	}

	public String getServiceID() {
		// --Get ServiceID

		String svc = driver.findElement(By.id("lblServiceID")).getText();
		System.out.println(svc);
		logs.info("Service is==" + svc);

		return svc;
	}

	public void SendEmail_ship_label() throws Exception {
		endTest();
		System.out.println("====Sending Email=====");
		logs.info("====Sending Email=====");
		// Send Details email
		String Env = storage.getProperty("Env");
		String subject = "Selenium Automation Script: " + Env + " " + " Connect : Ship Label Verification";

		if (Env.equalsIgnoreCase("TEST")) {

			logs.info("Test enviornment scenario file attached");
			String File = ".\\Report\\ExtentReport\\ExtentReportResults.html,.\\Report\\log\\ConnectOrderProcess.html,.\\src\\main\\resources\\connect_TestData\\TC_28678_ship_label_TEST.xlsx";
			String EmailID = storage.getProperty("MainEmailAddress");
			System.out.println("MainEmailAddress " + EmailID);
			try {

				Email.sendMail(EmailID, subject, msg.toString(), File);

			} catch (Exception ex) {
				logs.error(ex);
			}
		} else if (Env.equalsIgnoreCase("STG")) {

			logs.info("STG enviornment scenario file attached");
			String File = ".\\Report\\ExtentReport\\ExtentReportResults.html,.\\Report\\log\\ConnectOrderProcess.html,.\\src\\main\\resources\\connect_TestData\\TC_28678_ship_label_STG.xlsx";
			String EmailID = storage.getProperty("MainEmailAddress");
			System.out.println("MainEmailAddress " + EmailID);
			try {

				Email.sendMail(EmailID, subject, msg.toString(), File);

			} catch (Exception ex) {
				logs.error(ex);
			}
		}

	}
}
