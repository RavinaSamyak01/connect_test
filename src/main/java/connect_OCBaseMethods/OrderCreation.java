package connect_OCBaseMethods;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import connect_BasePackage.BaseInit;

public class OrderCreation extends BaseInit {
	static String pck, rdytime, rectime, arrtime, tndrtime;
	protected static String PUID;

	public void orderCreation(int i)
			throws EncryptedDocumentException, InvalidFormatException, IOException, AWTException, InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait;
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time

		} catch (Exception ewait) {
			wait = new WebDriverWait(driver, Duration.ofSeconds(70));
			// wait time

		}
		Actions act = new Actions(driver);

		Robot r = new Robot();
		String ServiceID = null;
		try {
			// --Go to Operation
			WebElement operation = isElementPresent("OperMenu_id");
			wait.until(ExpectedConditions.visibilityOf(operation));
			wait.until(ExpectedConditions.elementToBeClickable(operation));
			act.moveToElement(operation).build().perform();
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
			getData("Sheet1", i, 0);

			// --Get Service
			ServiceID = getData("Sheet1", i, 18);
			System.out.println("Service== " + ServiceID);
			logs.info("=====Service:- " + ServiceID + "=====");
			msg.append("\n" + "=====Service:- " + ServiceID + "=====" + "\n");

			// Enter Caller
			String Caller = getData("Sheet1", i, 0);
			isElementPresent("OCCallerName_id").clear();
			isElementPresent("OCCallerName_id").sendKeys(Caller);
			logs.info("Entered CallerName");

			// Enter Phone
			String Phone = getData("Sheet1", i, 1);
			isElementPresent("OCContactPh_id").clear();
			isElementPresent("OCContactPh_id").sendKeys(Phone);
			logs.info("Entered Contact/Phone");

			// Enter Account#
			String Account = getData("Sheet1", i, 2);
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

			if (i < 10) {
				// --wait until pU section is enabled
				try {
					wait.until(ExpectedConditions.invisibilityOfElementLocated(
							By.xpath("//*[@id=\"PickupSection\"][@disabled=\"disabled\"]")));
				} catch (Exception PUDIsable) {
					logs.error(PUDIsable);
					getScreenshot(driver, "PUSDisabled");
					WebDriverWait waitPUD = new WebDriverWait(driver, Duration.ofSeconds(120));// wait time
					waitPUD.until(ExpectedConditions.invisibilityOfElementLocated(
							By.xpath("//*[@id=\"PickupSection\"][@disabled=\"disabled\"]")));
					logs.info("PU Section is Enabled");

				}
				// Enter Pickup Zip code
				String PUZip = getData("Sheet1", i, 3);
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
				String PickupCom = getData("Sheet1", i, 4);
				isElementPresent("OCPUComp_id").clear();
				isElementPresent("OCPUComp_id").sendKeys(PickupCom);
				logs.info("Entered PU Company");

				// --PU AddressLine1
				String PUAddress1 = getData("Sheet1", i, 5);
				isElementPresent("OCPUAddL1_id").clear();
				isElementPresent("OCPUAddL1_id").sendKeys(PUAddress1);
				logs.info("Entered PU AddressLine1");

				// String Add2 = getData("Sheet1", i, 6);
				// driver.findElement(By.id("txtPUAddrLine2")).sendKeys(Add2);

				// --PU Attention
				String Attn = getData("Sheet1", i, 7);
				isElementPresent("OCPUAtt_id").clear();
				isElementPresent("OCPUAtt_id").sendKeys(Attn);
				logs.info("Entered PU Attention");

				// --PU Phone
				String PuPhone = getData("Sheet1", i, 8);
				isElementPresent("OCPUPhone_id").clear();
				isElementPresent("OCPUPhone_id").sendKeys(PuPhone);
				logs.info("Entered PU Phone");

				// String PUInst = getData("Sheet1", i, 9);
				// driver.findElement(By.id(" ")).sendKeys(PUInst);

				// --Wait to get PU Ready time
				try {
					wait.until(ExpectedConditions.invisibilityOfElementLocated(
							By.xpath("//input[contains(@class,'ng-invalid ng-invalid-required')]")));
					logs.info("PU Ready time is blank");
				} catch (Exception PUTimeNotExist) {
					logs.error(PUTimeNotExist);
					logs.info("PU Ready time is exist");

				}
				// --Getting ready PickupTime
				rdytime = isElementPresent("OCPURTime_id").getAttribute("value");
				logs.info("PU Ready Time==" + rdytime);
				setData("Sheet1", i, 34, rdytime);

				rectime = isElementPresent("OCPURTime_id").getAttribute("value");
				logs.info("PU Receive Time==" + rectime);
				setData("Sheet1", i, 35, rectime);

				arrtime = isElementPresent("OCPURTime_id").getAttribute("value");
				logs.info("PU Arrival Time==" + arrtime);
				setData("Sheet1", i, 36, arrtime);

				// set time in excel

				// tndrtime = driver.findElement(By.id("txtReadyforPickupTime")).getText();

				// --PU Miles
				String pmi = isElementPresent("OCPUMiles_id").getAttribute("value");
				logs.info("PU Mileage==" + pmi);

				// --Del Zip
				String DLZip = getData("Sheet1", i, 11);
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
				String DelCompany = getData("Sheet1", i, 12);
				isElementPresent("OCDLComp_id").clear();
				isElementPresent("OCDLComp_id").sendKeys(DelCompany);
				logs.info("Entered DL Company");

				// --DEL Address1
				String DLAddress1 = getData("Sheet1", i, 13);
				isElementPresent("OCDLAddL1_id").clear();
				isElementPresent("OCDLAddL1_id").sendKeys(DLAddress1);
				logs.info("Entered DL Address Line1");

				// String DLAddr2 = getData("Sheet1", i, 14);
				// driver.findElement(By.id("txtDelAddrLine2")).sendKeys(DLAddr2);

				// --DL Attention
				String DLAttn = getData("Sheet1", i,15);
				isElementPresent("OCDLAtt_id").clear();
				isElementPresent("OCDLAtt_id").sendKeys(DLAttn);
				logs.info("Entered DL Attention");

				// --DL Phone
				String DLPhone = getData("Sheet1", i, 16);
				isElementPresent("OCDLPhone_id").clear();
				isElementPresent("OCDLPhone_id").sendKeys(DLPhone);
				logs.info("Entered DL Phone");

				// --DL Miles
				String dmi = isElementPresent("OCDLMiles_id").getAttribute("value");
				logs.info("DL Miles==" + dmi);

				// String DLInst = getData("Sheet1", i, 17);
				// driver.findElement(By.id("txtDLPhone")).sendKeys(DLInst);
				// String srv =
				// driver.findElement(By.id("idNewOrderServiceId")).getAttribute("value");

				// --Get the ServiceID
				String GeneratedServiceID = isElementPresent("OCServiceID_id").getAttribute("value");
				System.out.println("ServiceID==" + GeneratedServiceID);

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
				String Weight = getData("Sheet1", i, 19);
				isElementPresent("OCWeight_id").clear();
				isElementPresent("OCWeight_id").sendKeys(Weight);
				isElementPresent("OCWeight_id").sendKeys(Keys.TAB);
				logs.info("Entered Weight");

				// --Length
				String Len = getData("Sheet1", i, 20);
				isElementPresent("OCLength_id").clear();
				isElementPresent("OCLength_id").sendKeys(Len);
				isElementPresent("OCLength_id").sendKeys(Keys.TAB);
				logs.info("Entered Length");

				// --Width
				String Width = getData("Sheet1", i, 21);
				isElementPresent("OCWidth_id").clear();
				isElementPresent("OCWidth_id").sendKeys(Width);
				isElementPresent("OCWidth_id").sendKeys(Keys.TAB);
				logs.info("Entered Width");

				// --Height
				String Height = getData("Sheet1", i, 22);
				isElementPresent("OCHeight_id").clear();
				isElementPresent("OCHeight_id").sendKeys(Height);
				isElementPresent("OCHeight_id").sendKeys(Keys.TAB);
				logs.info("Entered Height");

				// --Commodity
				String Commodity = getData("Sheet1", i, 23);
				isElementPresent("OCDesc_id").clear();
				isElementPresent("OCDesc_id").sendKeys(Commodity);
				isElementPresent("OCDesc_id").sendKeys(Keys.TAB);
				logs.info("Entered Commodity");

				// --Scroll Up
				r.keyPress(KeyEvent.VK_TAB);
				jse.executeScript("window.scrollBy(0,250)", "");
				Thread.sleep(2000);

				// --Total Mileage
				String tmile = isElementPresent("OCTotalMil_id").getAttribute("value");
				logs.info("Total Mileage==" + tmile);

				setData("Sheet1", i, 25, pmi);
				setData("Sheet1", i, 27, dmi);
				setData("Sheet1", i, 29, tmile);

				if (i == 3) {
					// p3P
					jse.executeScript("window.scrollBy(0,-350)", "");
					Thread.sleep(2000);

					// --Enter P3P service
					WebElement ServiceInpt = isElementPresent("EOServiceInp_id");
					wait.until(ExpectedConditions.elementToBeClickable(ServiceInpt));
					ServiceInpt.sendKeys(Keys.CONTROL, "a");
					ServiceInpt.sendKeys(Keys.BACK_SPACE);
					ServiceInpt.clear();
					ServiceInpt.clear();
					ServiceInpt.sendKeys("P3P");
					Thread.sleep(2000);
					ServiceInpt.sendKeys(Keys.TAB);
					Thread.sleep(2000);
					logs.info("Enter P3P service in Service input");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Select 3P Account
					WebElement p3acc = isElementPresent("EO3PAcDrop_id");
					wait.until(ExpectedConditions.visibilityOf(p3acc));
					wait.until(ExpectedConditions.elementToBeClickable(p3acc));
					Select T3pAc = new Select(p3acc);
					T3pAc.selectByIndex(1);
					logs.info("Selected 3P account");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Select 3P Service
					WebElement t3pService = isElementPresent("EO3PServiceS_id");
					act.moveToElement(t3pService).build().perform();
					jse.executeScript("arguments[0].scrollIntoView();", t3pService);
					Thread.sleep(2000);
					wait.until(ExpectedConditions.visibilityOf(t3pService));
					wait.until(ExpectedConditions.elementToBeClickable(t3pService));
					Select T3pService = new Select(t3pService);
					T3pService.selectByVisibleText("FEDEX_GROUND");
					logs.info("Selected 3P Service");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Scroll down
					r.keyPress(KeyEvent.VK_TAB);
					jse.executeScript("window.scrollBy(0,250)", "");
					Thread.sleep(2000);

					selectDropOffLoc();

				} else if (i == 4) {
					// PA
					jse.executeScript("window.scrollBy(0,-350)", "");
					Thread.sleep(2000);

					// --Enter PA service
					WebElement ServiceInpt = isElementPresent("EOServiceInp_id");
					wait.until(ExpectedConditions.elementToBeClickable(ServiceInpt));
					ServiceInpt.clear();
					Thread.sleep(2000);
					ServiceInpt.clear();
					ServiceInpt.sendKeys("PA");
					Thread.sleep(2000);
					ServiceInpt.sendKeys(Keys.TAB);
					Thread.sleep(2000);
					logs.info("Enter PA service in Service input");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				} else if (i == 6) {
					// AIR
					jse.executeScript("window.scrollBy(0,-450)", "");

					// --Click on Unknow Shipper
					WebElement UnShipper = isElementPresent("EOAIRUnShipper_id");
					wait.until(ExpectedConditions.visibilityOf(UnShipper));
					wait.until(ExpectedConditions.elementToBeClickable(UnShipper));
					act.moveToElement(UnShipper).build().perform();
					jse.executeScript("arguments[0].click();", UnShipper);
					logs.info("Clicked on Unknown Shipper");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					wait.until(ExpectedConditions
							.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"modal-content ui-draggable\"]")));

					// --Enter value in FedEx Verify
					WebElement VerifyFedEx = isElementPresent("EOAIRVeryFedEx_id");
					wait.until(ExpectedConditions.visibilityOf(VerifyFedEx));
					VerifyFedEx.clear();
					VerifyFedEx.sendKeys("KSMS DONE");
					logs.info("Entered value in FedEx Verify Input");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Click on Yes button
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnYes")));
					WebElement BtnYes = isElementPresent("EOAIRUnSHYes_id");
					wait.until(ExpectedConditions.elementToBeClickable(BtnYes));
					act.moveToElement(BtnYes).build().perform();
					jse.executeScript("arguments[0].click();", BtnYes);
					logs.info("Clicked on Yes button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Click on Select Flight
					WebElement SelectFlight = isElementPresent("TLSelFlight_id");
					wait.until(ExpectedConditions.elementToBeClickable(SelectFlight));
					act.moveToElement(SelectFlight).build().perform();
					jse.executeScript("arguments[0].click();", SelectFlight);
					logs.info("Clicked on Select Flight button");
					Thread.sleep(5000);

					wait.until(ExpectedConditions
							.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form=\"ItineraryForm\"]")));

					// --CLick on Select Flight
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hlkSel_0")));
					WebElement Select1stFlight = isElementPresent("TLSelect1stFlgt_id");
					wait.until(ExpectedConditions.elementToBeClickable(Select1stFlight));
					act.moveToElement(Select1stFlight).build().perform();
					jse.executeScript("arguments[0].click();", Select1stFlight);
					logs.info("Selected 1st flight");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Click on Assign button
					WebElement AssignFlight = isElementPresent("TLAssignFlght_xpath");
					wait.until(ExpectedConditions.elementToBeClickable(AssignFlight));
					act.moveToElement(AssignFlight).build().perform();
					jse.executeScript("arguments[0].click();", AssignFlight);
					logs.info("Clicked on Assign button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					try {

						WebElement Validation = isElementPresent("Error_id");
						wait.until(ExpectedConditions.visibilityOf(Validation));
						String ValMsg = Validation.getText();
						logs.info("Validation==" + ValMsg);

						if (ValMsg.equalsIgnoreCase(
								"Please select the appropriate Product Code before assigning the flight.")) {

							// --Select Product Code
							WebElement ProductCode = isElementPresent("TLSFProdCode_id");
							wait.until(ExpectedConditions.elementToBeClickable(ProductCode));
							Select FSLdrp = new Select(ProductCode);
							FSLdrp.selectByIndex(1);
							logs.info("Selected Product Code");
							Thread.sleep(2000);

							// --Click on Assign button
							AssignFlight = isElementPresent("TLAssignFlght_xpath");
							wait.until(ExpectedConditions.elementToBeClickable(AssignFlight));
							act.moveToElement(AssignFlight).build().perform();
							jse.executeScript("arguments[0].click();", AssignFlight);
							logs.info("Clicked on Assign button");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							setResultData("Result", 15, 4, "PASS");
							getScreenshot(driver, "SelectFlight");

						}

					} catch (Exception ee) {

					}
					try {
						// --Click on Yes button
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnYes")));
						BtnYes = isElementPresent("EOAIRUnSHYes_id");
						wait.until(ExpectedConditions.elementToBeClickable(BtnYes));
						act.moveToElement(BtnYes).build().perform();
						jse.executeScript("arguments[0].click();", BtnYes);
						logs.info("Clicked on Yes button");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					} catch (Exception e) {

					}

					// --Click on HAA checkbox
					WebElement HAA = isElementPresent("EOAIRHAA_id");
					wait.until(ExpectedConditions.visibilityOf(HAA));
					wait.until(ExpectedConditions.elementToBeClickable(HAA));
					act.moveToElement(HAA).build().perform();
					jse.executeScript("arguments[0].click();", HAA);
					logs.info("Clicked on HAA Checkbox");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Scroll Down
					jse.executeScript("window.scrollBy(0,450)", "");
					Thread.sleep(2000);

					// --Click on Recal button
					WebElement ReCal = isElementPresent("EOAIRRecal_xpath");
					wait.until(ExpectedConditions.visibilityOf(ReCal));
					wait.until(ExpectedConditions.elementToBeClickable(ReCal));
					act.moveToElement(ReCal).build().perform();
					jse.executeScript("arguments[0].click();", ReCal);
					logs.info("Clicked on ReCalculate button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				} else if (i == 7) {
					// SDC
					jse.executeScript("window.scrollBy(0,-350)", "");
					Thread.sleep(2000);

					// --Enter P3P service
					WebElement ServiceInpt = isElementPresent("EOServiceInp_id");
					wait.until(ExpectedConditions.elementToBeClickable(ServiceInpt));
					ServiceInpt.clear();
					ServiceInpt.clear();
					ServiceInpt.sendKeys("SDC");
					ServiceInpt.sendKeys(Keys.TAB);
					logs.info("Enter SDC service in Service input");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				} else if (i == 8) {
					// FRA
					jse.executeScript("window.scrollBy(0,-350)", "");
					Thread.sleep(2000);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Enter FRA service
					WebElement ServiceInpt = isElementPresent("EOServiceInp_id");
					wait.until(ExpectedConditions.elementToBeClickable(ServiceInpt));
					ServiceInpt.clear();
					Thread.sleep(2000);
					ServiceInpt.clear();
					ServiceInpt.sendKeys("FRA");
					Thread.sleep(2000);
					ServiceInpt.sendKeys(Keys.TAB);
					Thread.sleep(2000);
					logs.info("Enter FRA service in Service input");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Click on Unknow Shipper
					WebElement UnShipper = isElementPresent("EOAIRUnShipper_id");
					wait.until(ExpectedConditions.visibilityOf(UnShipper));
					wait.until(ExpectedConditions.elementToBeClickable(UnShipper));
					act.moveToElement(UnShipper).build().perform();
					jse.executeScript("arguments[0].click();", UnShipper);
					logs.info("Clicked on Unknown Shipper");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					wait.until(ExpectedConditions
							.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"modal-content ui-draggable\"]")));

					// --Enter value in FedEx Verify
					WebElement VerifyFedEx = isElementPresent("EOAIRVeryFedEx_id");
					wait.until(ExpectedConditions.visibilityOf(VerifyFedEx));
					VerifyFedEx.clear();
					VerifyFedEx.sendKeys("KSMS DONE");
					logs.info("Entered value in FedEx Verify Input");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Click on Yes button
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnYes")));
					WebElement BtnYes = isElementPresent("EOAIRUnSHYes_id");
					wait.until(ExpectedConditions.elementToBeClickable(BtnYes));
					act.moveToElement(BtnYes).build().perform();
					jse.executeScript("arguments[0].click();", BtnYes);
					logs.info("Clicked on Yes button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				}
				// --Click on Create Order button
				WebElement order = isElementPresent("OCOProcess_id");
				jse.executeScript("arguments[0].scrollIntoView();", order);
				Thread.sleep(2000);
				act.moveToElement(order).build().perform();
				wait.until(ExpectedConditions.elementToBeClickable(order));
				jse.executeScript("arguments[0].click();", order);
				logs.info("Click on Create Order button");

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

					}
				} catch (Exception eee) {

				}

				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//*[@name=\"NewOrderForm\"]//ul[@id=\"errorid\"]")));
					String ValMsg = isElementPresent("OCValMsg_xpath").getText();
					logs.info("Validation message=" + ValMsg);
					if (ValMsg.contains("Please add atleast one Package.")) {

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
						Weight = getData("Sheet1", i, 19);
						isElementPresent("OCWeight_id").clear();
						isElementPresent("OCWeight_id").sendKeys(Weight);
						isElementPresent("OCWeight_id").sendKeys(Keys.TAB);
						logs.info("Entered Weight");

						// --Length
						Len = getData("Sheet1", i, 20);
						isElementPresent("OCLength_id").clear();
						isElementPresent("OCLength_id").sendKeys(Len);
						isElementPresent("OCLength_id").sendKeys(Keys.TAB);
						logs.info("Entered Length");

						// --Width
						Width = getData("Sheet1", i, 21);
						isElementPresent("OCWidth_id").clear();
						isElementPresent("OCWidth_id").sendKeys(Width);
						isElementPresent("OCWidth_id").sendKeys(Keys.TAB);
						logs.info("Entered Width");

						// --Height
						Height = getData("Sheet1", i, 22);
						isElementPresent("OCHeight_id").clear();
						isElementPresent("OCHeight_id").sendKeys(Height);
						isElementPresent("OCHeight_id").sendKeys(Keys.TAB);
						logs.info("Entered Height");

						// --Commodity
						Commodity = getData("Sheet1", i, 23);
						isElementPresent("OCDesc_id").clear();
						isElementPresent("OCDesc_id").sendKeys(Commodity);
						isElementPresent("OCDesc_id").sendKeys(Keys.TAB);
						logs.info("Entered Commodity");

						// --Click on Create Order button
						order = isElementPresent("OCOProcess_id");
						jse.executeScript("arguments[0].scrollIntoView();", order);
						Thread.sleep(2000);
						act.moveToElement(order).build().perform();
						wait.until(ExpectedConditions.elementToBeClickable(order));
						jse.executeScript("arguments[0].click();", order);
						logs.info("Click on Create Order button");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
						Thread.sleep(2000);

						try {
							boolean sameairport = driver.getPageSource().contains(
									"Pickup and Delivery airport are different. Do you want to make it same?");

							if (sameairport == true) {
								logs.info("PopUp message is displayed for Same Airport");
								WebElement Yes = isElementPresent("OCSameApPupYes_xpath");
								wait.until(ExpectedConditions.elementToBeClickable(Yes));
								jse.executeScript("arguments[0].click();", Yes);
								logs.info("Clicked on YES button of popup");

							}
						} catch (Exception eee) {

						}

					}

				} catch (Exception packagee) {
					logs.info("Validation for Package is not displayed");

				}
				// --Scroll down
				/*
				 * r.keyPress(KeyEvent.VK_TAB); jse.executeScript("window.scrollBy(0,250)", "");
				 * Thread.sleep(2000);
				 */

				// --Get the PickUPID
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//*[@class=\"modal-dialog modal-sm\"]")));

				} catch (Exception WaitOp) {
					try {
						/*
						 * order = isElementPresent("OCOProcess_id");
						 * jse.executeScript("arguments[0].scrollIntoView();", order);
						 * Thread.sleep(2000);
						 */
						/*
						 * order = isElementPresent("OCOProcess_id");
						 * wait.until(ExpectedConditions.elementToBeClickable(order));
						 * act.moveToElement(order).click().build().perform();
						 * logs.info("Click on Create Order button");
						 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")
						 * ));
						 */
						/*
						 * order = isElementPresent("OCOProcess_id");
						 * jse.executeScript("arguments[0].scrollIntoView();", order);
						 * Thread.sleep(2000);
						 */
						order = isElementPresent("OCOProcess_id");
						act.moveToElement(order).build().perform();
						wait.until(ExpectedConditions.elementToBeClickable(order));
						jse.executeScript("arguments[0].click();", order);
						logs.info("Click on Create Order button");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
						Thread.sleep(2000);

						try {
							boolean sameairport = driver.getPageSource().contains(
									"Pickup and Delivery airport are different. Do you want to make it same?");

							if (sameairport == true) {
								logs.info("PopUp message is displayed for Same Airport");
								WebElement Yes = isElementPresent("OCSameApPupYes_xpath");
								wait.until(ExpectedConditions.elementToBeClickable(Yes));
								jse.executeScript("arguments[0].click();", Yes);
								logs.info("Clicked on YES button of popup");

							}
						} catch (Exception eee) {

						}
						WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(40));// wait time
						wait1.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//*[@class=\"modal-dialog modal-sm\"]")));

					} catch (Exception e) {

					}
				}

			}
			if (i >= 10) {
				// --Select Part
				inventorySearch(i);

				// --Del Zip
				String DLZip = getData("Sheet1", i, 11);
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
				String DelCompany = getData("Sheet1", i, 12);
				isElementPresent("OCDLComp_id").clear();
				isElementPresent("OCDLComp_id").sendKeys(DelCompany);
				logs.info("Entered DL Company");

				// --Del Address
				WebElement DL = isElementPresent("OCDLAdd_id");
				jse.executeScript("arguments[0].click();", DL);
				logs.info("Entered DL Address");

				// --DEL Address1
				String DLAddress1 = getData("Sheet1", i, 13);
				isElementPresent("OCDLAddL1_id").clear();
				isElementPresent("OCDLAddL1_id").sendKeys(DLAddress1);
				logs.info("Entered DL Address Line1");

				// String DLAddr2 = getData("Sheet1", i, 14);
				// driver.findElement(By.id("txtDelAddrLine2")).sendKeys(DLAddr2);

				// --DL Attention
				String DLAttn = getData("Sheet1", i,15);
				isElementPresent("OCDLAtt_id").clear();
				isElementPresent("OCDLAtt_id").sendKeys(DLAttn);
				logs.info("Entered DL Attention");

				// --DL Phone
				String DLPhone = getData("Sheet1", i, 16);
				isElementPresent("OCDLPhone_id").clear();
				isElementPresent("OCDLPhone_id").sendKeys(DLPhone);
				logs.info("Entered DL Phone");

				// --DL Miles
				String dmi = isElementPresent("OCDLMiles_id").getAttribute("value");
				logs.info("DL Miles==" + dmi);

				// String DLInst = getData("Sheet1", i, 17);
				// driver.findElement(By.id("txtDLPhone")).sendKeys(DLInst);
				// String srv =
				// driver.findElement(By.id("idNewOrderServiceId")).getAttribute("value");

				// --Get the ServiceID
				String ServID = getData("Sheet1", i, 18);

				// --Enter the ServiceID
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idNewOrderServiceId")));
				WebElement Service = isElementPresent("OCServiceID_id");
				wait.until(ExpectedConditions.elementToBeClickable(Service));
				Service.clear();
				Service.sendKeys(ServID);
				Service.sendKeys(Keys.TAB);
				if (ServID.equalsIgnoreCase("CPU")) {

				} else if (ServID.equalsIgnoreCase("D3P") || ServID.equalsIgnoreCase("3PLAST")) {
					// --Select 3P Account
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmb3PAccount")));
					WebElement p3acc = isElementPresent("EO3PAcDrop_id");
					wait.until(ExpectedConditions.elementToBeClickable(p3acc));
					Select T3pAc = new Select(p3acc);
					T3pAc.selectByIndex(1);
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

					try {
						// --Select 3P Service
						// --Select 3P Service
						WebElement t3pService = isElementPresent("EO3PServiceS_id");
						act.moveToElement(t3pService).build().perform();
						jse.executeScript("arguments[0].scrollIntoView();", t3pService);
						Thread.sleep(2000);
						wait.until(ExpectedConditions.visibilityOf(t3pService));
						wait.until(ExpectedConditions.elementToBeClickable(t3pService));
						Select T3pService = new Select(t3pService);
						T3pService.selectByVisibleText("FEDEX_GROUND");
						logs.info("Selected 3P Service");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					} catch (Exception e) {

					}

					// --Select Drop Off Location
					selectDropOffLoc();
				} else if (ServID.equalsIgnoreCase("H3P")) {
					// --Select 3P Account
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmb3PAccount")));
					WebElement p3acc = isElementPresent("EO3PAcDrop_id");
					wait.until(ExpectedConditions.elementToBeClickable(p3acc));
					Select T3pAc = new Select(p3acc);
					T3pAc.selectByIndex(1);
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
				}

				// --Scroll Up
				r.keyPress(KeyEvent.VK_TAB);
				jse.executeScript("window.scrollBy(0,250)", "");
				Thread.sleep(2000);

				// --Total Mileage
				String tmile = isElementPresent("OCTotalMil_id").getAttribute("value");
				logs.info("Total Mileage==" + tmile);

				setData("Sheet1", i, 27, dmi);
				setData("Sheet1", i, 29, tmile);

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
					wait.until(ExpectedConditions
							.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"modal-dialog\"]")));
					String DialogueContent = isElementPresent("CPUDContent_xpath").getText();
					logs.info("Content of the Dialogue is==" + DialogueContent);

					try {
						// --CLick on Yes button
						WebElement YesProceed = isElementPresent("CPUDYesPrc_xpath");
						wait.until(ExpectedConditions.elementToBeClickable(YesProceed));
						jse.executeScript("arguments[0].click();", YesProceed);
						logs.info("Click on Yes Proceed button");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By.xpath("//*[@class=\"modal-dialog\"]")));

					} catch (Exception YesBTN) {
						// --CLick on Yes button
						WebElement YesProceed = isElementPresent("CPUDYesPrc_xpath");
						wait.until(ExpectedConditions.elementToBeClickable(YesProceed));
						act.moveToElement(YesProceed).click().build().perform();
						logs.info("Click on Yes Proceed button");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					}

				} catch (Exception e) {
					logs.info("Confirmation message for warehouse is not displayed");
				}

			}

			WebElement PickUPID = isElementPresent("OCPickuPID_xpath");
			wait.until(ExpectedConditions.visibilityOf(PickUPID));
			wait.until(ExpectedConditions.elementToBeClickable(PickUPID));
			String pck = PickUPID.getText();
			System.out.println("Pickup = " + pck);
			logs.info("=====Pickup =" + pck + "=====" + "\n");
			msg.append("=====Pickup =" + pck + "=====" + "\n");

			// --Set PickUPID
			setData("Sheet1", i, 32, pck);
			setResultData("Result", i, 2, pck);

			// --Click on Edit Order
			WebElement EditOrder = isElementPresent("OCEditOrder_id");
			wait.until(ExpectedConditions.elementToBeClickable(EditOrder));
			EditOrder.click();
			logs.info("Clicked on Edit Order");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		} catch (Exception e) {
			logs.error(e);
			msg.append("Issue in Create Order for Service==" + ServiceID + "\n");
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
			getScreenshot(driver, "CreateOrderIssue");
		}

	}

	public void compareCourier(int i, String SVC)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait;
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(15));// wait time

		} catch (Exception ewait) {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			// wait time

		}
		Actions act = new Actions(driver);

		String ExpCourierID = getData("Sheet1", i, 38);
		logs.info("Expected CourierID==" + ExpCourierID);
		msg.append("Expected CourierID==" + ExpCourierID + "\n");
		try {
			// --Get CourierID'
			// --Send Pull Alert
			WebElement Courier = isElementPresent("SPCourierID_xpath");
			act.moveToElement(Courier).build().perform();
			wait.until(ExpectedConditions.visibilityOf(Courier));
			String ActCourierID = Courier.getText();
			logs.info("Actual CourierID==" + ActCourierID);
			msg.append("Actual CourierID==" + ActCourierID + "\n");

			if (ExpCourierID.equalsIgnoreCase(ActCourierID) && SVC.equalsIgnoreCase("H3P")
					|| SVC.equalsIgnoreCase("D3P") || SVC.equalsIgnoreCase("3PLAST") || SVC.equalsIgnoreCase("CPU")) {
				logs.info("Default selected CourierID is matched with Customer's CourierId==PASS");
				msg.append("Default selected CourierID is matched with Customer's CourierId==PASS" + "\n");
				getScreenshot(driver, "DefaultCourier");
				setResultData("Result", 20, 4, "PASS");
			} else if (ExpCourierID != (ActCourierID) && SVC.equalsIgnoreCase("H3P") || SVC.equalsIgnoreCase("D3P")
					|| SVC.equalsIgnoreCase("3PLAST") || SVC.equalsIgnoreCase("CPU")) {
				logs.info("Default selected CourierID is not matched with Customer's CourierId==FAIL");
				msg.append("Default selected CourierID is not matched with Customer's CourierId==FAIL" + "\n");
				setResultData("Result", 20, 4, "FAIL");

				selectCourier(SVC);

			} else if (ExpCourierID.equalsIgnoreCase(ActCourierID) && SVC != ("H3P") || SVC != ("D3P")
					|| SVC != ("3PLAST") || SVC != ("CPU")) {
				logs.info("Default selected CourierID is as per expected");
				msg.append("Default selected CourierID is as per expected" + "\n");

			} else if (ExpCourierID != (ActCourierID) && SVC != ("H3P") || SVC != ("D3P") || SVC != ("3PLAST")
					|| SVC != ("CPU")) {
				logs.info("Default selected CourierID is not as per expected");
				msg.append("Default selected CourierID is not as per expected" + "\n");
				selectCourier(SVC);
			}
		} catch (Exception ee) {
			logs.error(ee);
			getScreenshot(driver, "CompareCourier" + SVC);
			String Error = ee.getMessage();
			setResultData("Result", 20, 4, "FAIL");
			setResultData("Result", 20, 5, Error);
			System.out.println("Courier is not there");
			logs.info("Courier is not there");
		}

	}

	public void selectCourier(String SVC) throws EncryptedDocumentException, InvalidFormatException, IOException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time

		Actions act = new Actions(driver);

		// --Select Courier
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hlkedit")));
			isElementPresent("CourierEdit_id").click();
			logs.info("Clicked on Edit button of Driver");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"modal-dialog modal-md\"]")));
			getScreenshot(driver, "CourierSearch" + SVC);

			// --Search driver by AgentID
			wait.until(ExpectedConditions.elementToBeClickable(By.id("txtCourierId")));
			isElementPresent("CourierID_id").clear();
			isElementPresent("CourierID_id").sendKeys("34769");
			logs.info("Enter CourierID");

			// --Click on Search
			WebElement SearchBTN = isElementPresent("PDSearch_id");
			jse.executeScript("arguments[0].click();", SearchBTN);
			logs.info("Clicked on search button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Get the List of Agent
			List<WebElement> Agents = driver
					.findElements(By.xpath("//*[@id=\"scrollboxEditAgent\"]//a[contains(@id,'AgentId')]"));

			int TotalAgent = Agents.size();
			logs.info("Total Agent==" + TotalAgent);
			String AgentValue = null;
			for (int agent = 0; agent < TotalAgent; agent++) {
				WebElement AgentID = Agents.get(agent);
				String AgentIDV = Agents.get(agent).getText();
				act.moveToElement(AgentID).build().perform();
				AgentValue = AgentIDV;
				if (AgentIDV.equalsIgnoreCase("34769")) {
					logs.info("Searched Agent is exist");

					// --Select the Agent
					act.moveToElement(AgentID).build().perform();
					act.moveToElement(AgentID).click().perform();
					logs.info("Select the AgentID");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hlkedit")));
					AgentValue = AgentIDV;

				} else {
					logs.info("There is no Agent with search parameter");

				}

			}

			// --Compare the Searched Agent and selected Agent
			String AgeValue = isElementPresent("PDAgentValue_id").getText();
			logs.info("Selected agent is==" + AgeValue);

			if (AgeValue.equalsIgnoreCase(AgentValue)) {
				logs.info("Selected Agent is displayed in Driver section");

			} else {
				logs.info("Selected Agent is not displayed in Driver section");

			}

			// msg.append("==RTE Edit Driver Test End==" + "\n");
			msg.append("Edit Driver Test=PASS" + "\n");
			setResultData("Result", 21, 4, "PASS");

		} catch (Exception ex) {
			logs.error(ex);
			msg.append("Edit Courier Test=FAIL" + "\n");
			getScreenshot(driver, "SelectCourierError");
			String Error = ex.getMessage();
			setResultData("Result", 21, 4, "FAIL");
			setResultData("Result", 21, 5, Error);

		}
	}

	public void searchJob(int i) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		Actions act = new Actions(driver);

		try {
			try {
				// Enter JobID#
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtContains")));
				wait.until(ExpectedConditions.elementToBeClickable(By.id("txtContains")));

				PUID = getData("Sheet1", i, 32);
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

					List<WebElement> Jobs = driver.findElements(
							By.xpath("//*[contains(@aria-label,'Pickup #,')]//label[@id=\"lblDateTime\"]"));
					for (int job = 0; job < Jobs.size(); job++) {
						String PickupID = Jobs.get(job).getText();
						String PickID = null;

						if (PickupID.startsWith("N")) {
							String[] PickValue = PickupID.split("N");
							PickID = PickValue[1];
						} else if (PickupID.startsWith("F")) {
							String[] PickValue = PickupID.split("F");
							PickID = PickValue[1];
						} else if (PickupID.startsWith("R")) {
							String[] PickValue = PickupID.split("R");
							PickID = PickValue[1];
						}

						logs.info("Searched PickUpID==" + PickID);
						PUID = getData("Sheet1", i, 32);
						if (PickID.equalsIgnoreCase(PUID)) {
							Jobs.get(job).click();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

						}

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

				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
						By.xpath("//*[@class=\"OpenCloseClass dropdown open\"]//ul")));

				// --Go to TaskLog
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a_TaskLog")));
				isElementPresent("OpTaskLog_id").click();
				logs.info("Clicked on TaskLog");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				getScreenshot(driver, "TaskLog");

				// Enter JobID#
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				PUID = getData("Sheet1", i, 32);
				logs.info("PickUpID=" + PUID + "\n");
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

					List<WebElement> Jobs = driver.findElements(
							By.xpath("//*[contains(@aria-label,'Pickup #,')]//label[@id=\"lblDateTime\"]"));
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
						logs.info("Same job is displayed with 2 status opt relevent == PASS");
						// msg.append("Same job is displayed with 2 status==PASS" + "\n");
					}
					// --Get StageName
				} catch (Exception e) {
					logs.info("Same job is not displayed with 2 status");
					// msg.append("Same job is not displayed with 2 status" + "\n");

					// --Get StageName

				}

			}
			setResultData("Result", 22, 4, "PASS");

		} catch (Exception ewait) {
			getScreenshot(driver, "SearchJobError" + i);
			String Error = ewait.getMessage();
			setResultData("Result", 22, 4, "FAIL");
			setResultData("Result", 22, 5, Error);

		}

	}

	public String getServiceID() {
		// --Get ServiceID

		String svc = driver.findElement(By.id("lblServiceID")).getText();
		System.out.println(svc);
		logs.info("Service is==" + svc);

		return svc;
	}

	public String getStageName() {
		WebDriverWait wait;
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(15));// wait time

		} catch (Exception ewait) {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			// wait time

		}
		// --Get the Stage Name
		WebElement Stage = isElementPresent("EOStageName_id");
		wait.until(ExpectedConditions.visibilityOf(Stage));
		String StageName = Stage.getText();
		System.out.println(StageName);
		logs.info("Stage=" + StageName);
		msg.append("Stage=" + StageName + "\n");
		return StageName;

	}

	public void refreshApp() {
		WebDriverWait wait;
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(15));// wait time

		} catch (Exception ewait) {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			// wait time

		}
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

	public void unknowShipper(int i) throws EncryptedDocumentException, InvalidFormatException, IOException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);
		try {
			// --Unknown Shipper click
			WebElement UnShipper = isElementPresent("TLUnShipp_id");
			wait.until(ExpectedConditions.visibilityOf(UnShipper));
			wait.until(ExpectedConditions.elementToBeClickable(UnShipper));
			act.moveToElement(UnShipper).build().perform();
			js.executeScript("arguments[0].click();", UnShipper);
			logs.info("Clicked on Unknown Shipper");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Wait for pop up of Unknown Shipper
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"modal-dialog modal-sm\"]")));

			// --Click on Confirm Button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnConfirmExtrernal")));
			WebElement UnShCOnfirm = isElementPresent("TLUnShConfrm_id");
			wait.until(ExpectedConditions.elementToBeClickable(UnShCOnfirm));
			UnShCOnfirm.click();
			logs.info("Clicked on Confirm button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Scroll to get Rate
			js.executeScript("window.scrollBy(0,400)", "");
			String cost = isElementPresent("TLActualRate_id").getText();
			setData("Sheet1", i, 31, cost);
			logs.info("Scroll down to Get the Rate");

			// --Click on Save Changes
			isElementPresent("TLSaveChanges_id").click();
			logs.info("Clicked on Save Changes button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			setResultData("Result", 14, 4, "PASS");
		} catch (Exception ewait) {
			logs.info(ewait);
			getScreenshot(driver, "eUnknownShipperError");
			String Error = ewait.getMessage();
			setResultData("Result", 14, 4, "FAIL");
			setResultData("Result", 14, 5, Error);

		}

	}

	public void selectFlight()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		try {
			// --Move to Job Status Tab
			WebElement JoStatusTab = isElementPresent("TLJobStatusTab_id");
			wait.until(ExpectedConditions.visibilityOf(JoStatusTab));
			wait.until(ExpectedConditions.elementToBeClickable(JoStatusTab));
			act.moveToElement(JoStatusTab).click().build().perform();
			logs.info("Clicked on Job Status Tab");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Click on Select Flight
			WebElement SelectFlight = isElementPresent("TLSelFlight_id");
			wait.until(ExpectedConditions.elementToBeClickable(SelectFlight));
			act.moveToElement(SelectFlight).build().perform();
			js.executeScript("arguments[0].click();", SelectFlight);
			logs.info("Clicked on Select Flight button");
			Thread.sleep(5000);

			wait.until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form=\"ItineraryForm\"]")));

			// --CLick on Select Flight
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hlkSel_0")));
			WebElement Select1stFlight = isElementPresent("TLSelect1stFlgt_id");
			wait.until(ExpectedConditions.elementToBeClickable(Select1stFlight));
			act.moveToElement(Select1stFlight).build().perform();
			js.executeScript("arguments[0].click();", Select1stFlight);
			logs.info("Selected 1st flight");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Click on Assign button
			WebElement AssignFlight = isElementPresent("TLAssignFlght_xpath");
			wait.until(ExpectedConditions.elementToBeClickable(AssignFlight));
			act.moveToElement(AssignFlight).build().perform();
			js.executeScript("arguments[0].click();", AssignFlight);
			logs.info("Clicked on Assign button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			setResultData("Result", 15, 4, "PASS");
			getScreenshot(driver, "SelectFlight");
			try {

				WebElement Validation = isElementPresent("Error_id");
				wait.until(ExpectedConditions.visibilityOf(Validation));
				String ValMsg = Validation.getText();
				logs.info("Validation==" + ValMsg);

				if (ValMsg
						.equalsIgnoreCase("Please select the appropriate Product Code before assigning the flight.")) {

					// --Select Product Code
					WebElement ProductCode = isElementPresent("TLSFProdCode_id");
					wait.until(ExpectedConditions.elementToBeClickable(ProductCode));
					Select FSLdrp = new Select(ProductCode);
					FSLdrp.selectByIndex(1);
					logs.info("Selected Product Code");
					Thread.sleep(2000);

					// --Click on Assign button
					AssignFlight = isElementPresent("TLAssignFlght_xpath");
					wait.until(ExpectedConditions.elementToBeClickable(AssignFlight));
					act.moveToElement(AssignFlight).build().perform();
					js.executeScript("arguments[0].click();", AssignFlight);
					logs.info("Clicked on Assign button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					setResultData("Result", 15, 4, "PASS");
					getScreenshot(driver, "SelectFlight");

				}

			} catch (Exception ee) {

			}

		} catch (Exception ewait) {
			logs.info(ewait);
			getScreenshot(driver, "eSelectFlightError");
			String Error = ewait.getMessage();
			setResultData("Result", 15, 4, "FAIL");
			setResultData("Result", 15, 5, Error);

		}

	}

	public void reCalc(String svc) {
		WebDriverWait wait;
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(15));// wait time

		} catch (Exception ewait) {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			// wait time

		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);

		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@id=\"idValidationforMain\"]//ul[@id=\"errorid\"]")));
			String Valmsg = isElementPresent("OCValOnePack_xpath").getText();
			logs.info("Validation message is displayed=" + Valmsg);
			if (Valmsg.contains("Parameter(s) are modified. Please recalculate customer charges.")) {
				// Recalculate the charges
				// --Go to Edit Job tab
				WebElement EditOrTab = isElementPresent("EOEditOrderTab_id");
				act.moveToElement(EditOrTab).build().perform();
				wait.until(ExpectedConditions.elementToBeClickable(EditOrTab));
				act.moveToElement(EditOrTab).click().perform();
				logs.info("Click on Edit Order Tab");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// -Recalculate button
				WebElement ReCalc = isElementPresent("EORecal_id");
				act.moveToElement(ReCalc).build().perform();
				wait.until(ExpectedConditions.elementToBeClickable(ReCalc));
				act.moveToElement(ReCalc).click().perform();
				logs.info("Click on Recalculate button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Click on Save Changes button
				WebElement SaveChanges = isElementPresent("TLSaveChanges_id");
				act.moveToElement(SaveChanges).build().perform();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
				wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
				act.moveToElement(SaveChanges).click().perform();
				logs.info("Click on Save Changes button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				Thread.sleep(2000);

				try {
					WebElement Validation = isElementPresent("EOValidation_id");
					wait.until(ExpectedConditions.visibilityOf(Validation));
					String ValMsg = Validation.getText();
					logs.info("Validation==" + ValMsg);

					if (ValMsg.equalsIgnoreCase("Pickup Phone# is Required.")) {
						// --Enter Pickup Phone No
						WebElement PUPhoneNo = isElementPresent("EOPickupPhone_id");
						act.moveToElement(PUPhoneNo).build().perform();
						wait.until(ExpectedConditions.elementToBeClickable(PUPhoneNo));
						PUPhoneNo.sendKeys("1112221112");
						logs.info("Entered PU Phone");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

						// --Click on Save Changes button
						SaveChanges = isElementPresent("TLSaveChanges_id");
						act.moveToElement(SaveChanges).build().perform();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
						wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
						act.moveToElement(SaveChanges).click().perform();
						logs.info("Click on Save Changes button");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
						Thread.sleep(2000);
					}
				} catch (Exception eRequiredMsg) {
				}

				// --Go to job Status Tab
				WebElement JobOverTab = isElementPresent("TLJobStatusTab_id");
				act.moveToElement(JobOverTab).build().perform();
				wait.until(ExpectedConditions.elementToBeClickable(JobOverTab));
				act.moveToElement(JobOverTab).click().perform();
				logs.info("Click on Job Overview Tab");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				Thread.sleep(5000);

				// --Enter SIgnature
				wait.until(ExpectedConditions.elementToBeClickable(By.id("txtDeliverySignature")));
				isElementPresent("TLDSignature_id").clear();
				isElementPresent("TLDSignature_id").sendKeys("RVOza");
				isElementPresent("TLDSignature_id").sendKeys(Keys.TAB);
				logs.info("Enter Signature");

				if (svc.equals("LOC") || svc.equals("DRV") || svc.equals("SDC") || svc.equals("FRG")
						|| svc.equals("3PLAST")) {
					// --Get the timeZone
					String tzone = isElementPresent("TLLOCDActTimZone_id").getText();
					logs.info("Actual DL TimeZone==" + tzone);
					String rectime = getTimeAsTZone(tzone);
					logs.info("Actual DL Time==" + rectime);

					// --Enter Actual DL time
					wait.until(ExpectedConditions.elementToBeClickable(By.id("txtActualDeliveryTime")));
					isElementPresent("TLDActDLTime_id").clear();
					isElementPresent("TLDActDLTime_id").sendKeys(rectime);
					logs.info("Enter Actual DL Time");

					// --Click on Confirm DL button
					WebElement ConfDL = isElementPresent("TLDConfDL_id");
					wait.until(ExpectedConditions.elementToBeClickable(ConfDL));
					Thread.sleep(2000);
					act.moveToElement(ConfDL).click().build().perform();
					logs.info("Click on Confirm DEL button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				} else if (svc.equals("SD") || svc.equals("PA") || svc.equals("FRA")) {

					// --Enter SIgnature
					wait.until(ExpectedConditions.elementToBeClickable(By.id("txtDeliverySignature")));
					isElementPresent("TLDSignature_id").clear();
					isElementPresent("TLDSignature_id").sendKeys("RVOza");
					isElementPresent("TLDSignature_id").sendKeys(Keys.TAB);
					logs.info("Enter Signature");

					// --Enter Actual DL time
					// --Get the timeZone
					String tzone = isElementPresent("TLSDDActTimZone_id").getText();
					logs.info("Actual DL TimeZone==" + tzone);
					String rectime = getTimeAsTZone(tzone);
					logs.info("Actual DL Time==" + rectime);

					// --Enter Actual DL time
					wait.until(ExpectedConditions.elementToBeClickable(By.id("txtActualDeliveryTime")));
					isElementPresent("TLDActDLTime_id").clear();
					isElementPresent("TLDActDLTime_id").sendKeys(rectime);
					logs.info("Enter Actual DL Time");

					// --Click on Confirm DL button
					WebElement ConfDL = isElementPresent("TLDConfDL2_id");
					wait.until(ExpectedConditions.elementToBeClickable(ConfDL));
					Thread.sleep(2000);
					act.moveToElement(ConfDL).click().build().perform();
					logs.info("Click on Confirm DEL button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				} else if (svc.equals("AIR")) {
					Thread.sleep(5000);

					// --Get the timeZone
					String tzone = isElementPresent("TLDAIRTZone_id").getText();
					logs.info("Actual DL TimeZone==" + tzone);
					String rectime = getTimeAsTZone(tzone);
					logs.info("Actual DL Time==" + rectime);

					// --Enter Actual DL time
					wait.until(ExpectedConditions.elementToBeClickable(By.id("txtOnHandActualDeliveryTime")));
					isElementPresent("TLDAIRActualDTime_id").clear();
					isElementPresent("TLDAIRActualDTime_id").sendKeys(rectime);
					logs.info("Enter Actual DL Time");

					// --Click on Confirm DL button
					WebElement ConfDL = isElementPresent("TLDConfDL2_id");
					wait.until(ExpectedConditions.elementToBeClickable(ConfDL));
					Thread.sleep(2000);
					act.moveToElement(ConfDL).click().build().perform();
					logs.info("Click on Confirm DEL button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				}

			}

		} catch (Exception PModify) {
			logs.info("Validation message is not displayed for Recalculate the charges");

		}
	}

	public static void inventorySearch(int i)
			throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time;
		try {

			String PartName = getData("Sheet1", i, 37);
			// --Click on Search Parts button
			WebElement PartsSearch = isElementPresent("OCPartSearch_id");
			wait.until(ExpectedConditions.elementToBeClickable(PartsSearch));
			jse.executeScript("arguments[0].click();", PartsSearch);
			logs.info("Clicked on Parts search button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"modal-dialog modal-lg\"]")));
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
			FSLdrp.selectByIndex(1);
			logs.info("Selected FSL");

			// --Field 1
			WebElement Field1 = isElementPresent("OCPSField1_id");
			wait.until(ExpectedConditions.elementToBeClickable(Field1));
			Field1.clear();
			Field1.sendKeys(PartName);
			logs.info("Entered value of Field 1");

			// --CLick on Search button
			WebElement PartSearch = isElementPresent("OCPSASPartSearch_id");
			wait.until(ExpectedConditions.elementToBeClickable(PartSearch));
			jse.executeScript("arguments[0].click();", PartSearch);
			logs.info("Clicked on search button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			try {
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
					setResultData("Result", 16, 4, "PASS");

				} else {
					logs.info("Part is not added successfully==FAIL, Save Part button is not working");
					getScreenshot(driver, "PartNotSaved_" + i);
					setResultData("Result", 16, 4, "FAIL");

				}
			} catch (Exception e) {
				logs.info("Parts is not available");
				getScreenshot(driver, "SearchPartNotAvailable");
				msg.append("Parts not available" + "\n");
				setResultData("Result", 16, 4, "PASS");
				setResultData("Result", 16, 5, "Parts not available"); // wait time

			}
		} catch (Exception ewait) {
			logs.info(ewait);
			getScreenshot(driver, "InventorySearchError");
			String Error = ewait.getMessage();
			setResultData("Result", 16, 4, "FAIL");
			setResultData("Result", 16, 5, Error); // wait time

		} // Actions act = new Actions(driver);

	}

	public void selectDropOffLoc()
			throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);

		try {
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

			getScreenshot(driver, "DropOffLocationPopUp");
			logs.info("Drop Off Location Test= PASS");
			msg.append("Drop Off Location Test= PASS" + "\n");
			setResultData("Result", 17, 4, "PASS");

		} catch (Exception ee) {
			logs.info("issue in drop off location");
			logs.info(ee);
			getScreenshot(driver, "DropofflocationnoLocation");
			String Error = ee.getMessage();
			setResultData("Result", 17, 4, "FAIL");
			setResultData("Result", 17, 5, Error); // wait time
			logs.info("Drop Off Location Test= FAIL");
			msg.append("Drop Off Location Test= FAIL" + "\n");
			try {
				// -close drop off pop up
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("IconClose")));
				WebElement CloseDropOf = isElementPresent("OCSelDropOfClose_id");
				wait.until(ExpectedConditions.elementToBeClickable(CloseDropOf));
				jse.executeScript("arguments[0].click();", CloseDropOf);
				logs.info("Click on Close button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// -Select FCA
				WebElement FCA = isElementPresent("OCFCA_xpath");
				wait.until(ExpectedConditions.visibilityOf(FCA));
				wait.until(ExpectedConditions.elementToBeClickable(FCA));
				jse.executeScript("arguments[0].click();", FCA);
				logs.info("Click on FCA button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Enter Company
				WebElement FCAComp = isElementPresent("OCFCACompany_id");
				wait.until(ExpectedConditions.visibilityOf(FCAComp));
				wait.until(ExpectedConditions.elementToBeClickable(FCAComp));
				FCAComp.clear();
				FCAComp.sendKeys("Nothing Bundt Cakes");
				logs.info("Enter FCA Company");

				// --Enter Zipcode
				WebElement FCAZip = isElementPresent("OCFCAZip_id");
				wait.until(ExpectedConditions.visibilityOf(FCAZip));
				wait.until(ExpectedConditions.elementToBeClickable(FCAZip));
				FCAZip.clear();
				FCAZip.sendKeys("77598");
				logs.info("Enter FCA ZipCode");
				FCAZip.sendKeys(Keys.TAB);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Enter Address
				WebElement FCAADD = isElementPresent("OCFCAAddress_id");
				wait.until(ExpectedConditions.visibilityOf(FCAADD));
				wait.until(ExpectedConditions.elementToBeClickable(FCAADD));
				FCAADD.clear();
				FCAADD.sendKeys("154 W Bay Area Blvd.");
				logs.info("Enter FCA Address");

				// --CLick on Save button
				WebElement FCASave = isElementPresent("OCFCASave_xpath");
				wait.until(ExpectedConditions.visibilityOf(FCASave));
				wait.until(ExpectedConditions.elementToBeClickable(FCASave));
				jse.executeScript("arguments[0].click();", FCASave);
				logs.info("Click on FCA Save button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				try {
					WebElement SuccMsg = isElementPresent("OCFCASuccess_id");
					wait.until(ExpectedConditions.visibilityOf(FCASave));
					String SUccMsg = SuccMsg.getText();
					logs.info(SUccMsg);
					logs.info("Consignee address saved successfully=PASS");
					setResultData("Result", 18, 4, "PASS");

				} catch (Exception e) {
					logs.info("Unable to add consignee Address=FAIL");
					getScreenshot(driver, "DropofflocationFAIL");
					logs.info("Drop Off Location Test= FAIL");
					msg.append("Drop Off Location Test= FAIL" + "\n");
					String Error1 = e.getMessage();
					setResultData("Result", 18, 4, "FAIL");
					setResultData("Result", 18, 5, Error1); // wait time
				}

				// --CLick on Close button
				WebElement FCAClose = isElementPresent("OCFCAClose_id");
				wait.until(ExpectedConditions.visibilityOf(FCAClose));
				wait.until(ExpectedConditions.elementToBeClickable(FCAClose));
				jse.executeScript("arguments[0].click();", FCAClose);
				logs.info("Click on FCA Close button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			} catch (Exception e) {
				logs.info("Unable to close drop off location");
				logs.info(e);
				getScreenshot(driver, "DropofflocationnoLocation");

				logs.info("Drop Off Location Test= FAIL");
				msg.append("Drop Off Location Test= FAIL" + "\n");

			}

		}

	}

	public static void routeNoteSpecialInstruction() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("btnRouteAck")).click();
		Thread.sleep(1000);

		driver.findElement(By.id("btnSpecialFields")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("txtrefer0")).sendKeys("1");
		driver.findElement(By.id("txtrefer1")).sendKeys("2");
		driver.findElement(By.id("txtrefer2")).sendKeys("3");
		driver.findElement(By.id("txtrefer4")).sendKeys("4");
		driver.findElement(By.id("txtrefer6")).sendKeys("321012");

		Select rtn = new Select(driver.findElement(By.id("cmbrefer3")));
		rtn.selectByVisibleText("Customer");

		Select suptype = new Select(driver.findElement(By.id("cmbrefer5")));
		suptype.selectByVisibleText("Other");
		Thread.sleep(2000);
		driver.findElement(By.id("btnSave")).click();

	}

	public static void fedExCarrier() throws InterruptedException {
		Thread.sleep(2000);
		Select cracct = new Select(driver.findElement(By.id("cmb3PAccount")));
		cracct.selectByVisibleText("Quantum Fedex US (510087763)");
	}

	public static void upsCarrier() throws InterruptedException {
		Thread.sleep(2000);
		Select cracct = new Select(driver.findElement(By.id("cmb3PAccount")));
		cracct.selectByVisibleText("United State - UPS (6295vv)");
	}

	public static void dhlCarrier() throws InterruptedException {
		Thread.sleep(2000);
		Select cracct = new Select(driver.findElement(By.id("cmb3PAccount")));
		cracct.selectByVisibleText("Quantum DHL UK (ca_7787eb057e2c4e158589cd5e5dee7ba7)");
	}

	public static void shipLabelServicesFedEx() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.id("hlkShiplabel")).click();
		Thread.sleep(4000);
		Select rtn = new Select(driver.findElement(By.id("cmbServiceType")));
		rtn.selectByVisibleText("FEDEX_GROUND");
		Thread.sleep(2000);
		driver.findElement(By.id("btnSubmit")).click();
		Thread.sleep(10000);
		getScreenshot(driver, "ShipLabel_FedEx.jpg");
		driver.findElement(By.id("idanchorclose")).click();
		Thread.sleep(4000);

	}

	public static void shipLabelServicesUPS() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.id("hlkShiplabel")).click();
		Thread.sleep(4000);
		Select rtn = new Select(driver.findElement(By.id("cmbServiceType")));
		rtn.selectByVisibleText("Ground");
		Thread.sleep(2000);
		driver.findElement(By.id("btnSubmit")).click();
		Thread.sleep(10000);
		getScreenshot(driver, "ShipLabel_UPS.jpg");
		driver.findElement(By.id("idanchorclose")).click();
		Thread.sleep(4000);

	}

	public static void shipLabelServicesDHL() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.id("hlkShiplabel")).click();
		Thread.sleep(4000);
		Select rtn = new Select(driver.findElement(By.id("cmbServiceType")));
		rtn.selectByVisibleText("Express1200NonDoc");
		Thread.sleep(2000);
		driver.findElement(By.id("btnSubmit")).click();
		Thread.sleep(10000);
		getScreenshot(driver, "ShipLabel_DHL.jpg");
		driver.findElement(By.id("idanchorclose")).click();
		Thread.sleep(4000);

	}

	public String shipLabel()
			throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
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
				// --If Ship Label is not generated
				// --Select 3p Account
				Select p3acc = new Select(isElementPresent("TT3ACDrop_id"));
				p3acc.selectByIndex(1);
				logs.info("Selected 3p Account");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Select Service
				Select Contacttype = new Select(isElementPresent("TT3Servicedrp_id"));
				Contacttype.selectByVisibleText("FEDEX_GROUND");
				logs.info("Selected Service");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Click on Submit
				WebElement Submit = isElementPresent("TT3ShiLSubmit_id");
				wait.until(ExpectedConditions.elementToBeClickable(Submit));
				Submit.click();
				logs.info("Clicked on Submit button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				try {
					// --Print button
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[@id=\"scrollboxIframe\"]//button[@id=\"btnPrint\"]")));
					String TrackingNo = isElementPresent("SLSTrackNo_xpath").getText();
					logs.info("Tracking No==" + TrackingNo);

					// --Get the TrackingNo
					String inLine = TrackingNo;
					String[] lineSplits = inLine.split(":");
					TrackNo = lineSplits[1];
					logs.info("Tracking No==" + TrackNo);
					setResultData("Result", 19, 2, TrackNo); // wait time // wait time
					setResultData("Result", 19, 4, "PASS");
					logs.info("ShipLabel Test=PASS");
					msg.append("ShipLabel Test=PASS" + "\n");
				} catch (Exception ee) {
					logs.info(ee);
					getScreenshot(driver, "ShipLabelError");
					String Error1 = ee.getMessage();
					setResultData("Result", 19, 4, "FAIL");
					setResultData("Result", 19, 5, Error1); // wait time // wait time
					logs.info("ShipLabel Test=FAIL");
					msg.append("ShipLabel Test=FAIL" + "\n");

				}

			} catch (Exception eShipLabel) {
				// --If Ship Label is generated

				// --Send Email
				WebElement SendEmail = isElementPresent("SLSemail_id");
				wait.until(ExpectedConditions.elementToBeClickable(SendEmail));
				SendEmail.clear();
				SendEmail.sendKeys("ravina.prajapati.samyak@gmail.com");
				logs.info("Entered Email in Send Email");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Click on Send Button
				WebElement SendBTN = isElementPresent("SLSSend_id");
				wait.until(ExpectedConditions.elementToBeClickable(SendBTN));
				js.executeScript("arguments[0].click();", SendBTN);
				logs.info("Clicked on Send button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// ErrorMsg
				try {
					wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"idValidationPopup\"]")));
					logs.info("ErroMsg is Displayed=" + isElementPresent("SLSVal_xpath").getText());

					// -- Check the checkbox
					WebElement ShipLabl = isElementPresent("SLSSelect1_xpath");
					wait.until(ExpectedConditions.elementToBeClickable(ShipLabl));
					js.executeScript("arguments[0].click();", ShipLabl);
					logs.info("Checked the checkbox of ship label");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Send Email
					SendEmail = isElementPresent("SLSemail_id");
					wait.until(ExpectedConditions.elementToBeClickable(SendEmail));
					SendEmail.clear();
					SendEmail.sendKeys("ravina.prajapati.samyak@gmail.com");
					logs.info("Entered Email in Send Email");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Click on Send Button
					SendBTN = isElementPresent("SLSSend_id");
					wait.until(ExpectedConditions.elementToBeClickable(SendBTN));
					SendBTN.click();
					logs.info("Clicked on Send button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success")));
					System.out
							.println("Success Message is Displayed=" + isElementPresent("SLSSuccess_xpath").getText());

				} catch (Exception e) {
					logs.info("Error Message is not displayed");
					System.out.println(e);
				}

				// --Print button
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@id=\"scrollboxIframe\"]//button[@id=\"btnPrint\"]")));
				String TrackingNo = isElementPresent("SLSTrackNo_xpath").getText();
				logs.info("Tracking No==" + TrackingNo);

				// --Get the TrackingNo
				String inLine = TrackingNo;
				String[] lineSplits = inLine.split(":");
				TrackNo = lineSplits[1];
				logs.info("Tracking No==" + TrackNo);
				setResultData("Result", 19, 2, TrackNo); // wait time // wait time
				setResultData("Result", 19, 4, "PASS");
				logs.info("ShipLabel Test=PASS");
				msg.append("ShipLabel Test=PASS" + "\n");

				/*
				 * // --Click on Print Button WebElement PrintBTN =
				 * isElementPresent("SLSPrintBTN_id");
				 * wait.until(ExpectedConditions.elementToBeClickable(PrintBTN));
				 * PrintBTN.click(); logs.info("Clicked on Print button");
				 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")
				 * )); wait.until( ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
				 * "//*[@ng-form=\"SLForm\"]")));
				 * 
				 * // Handle Print window String WindowHandlebefore = driver.getWindowHandle();
				 * for (String windHandle : driver.getWindowHandles()) {
				 * driver.switchTo().window(windHandle); logs.info("Switched to Print window");
				 * Thread.sleep(5000); getScreenshot(driver, "PrintShipLabelService"); }
				 * driver.close(); logs.info("Closed Print window");
				 * 
				 * driver.switchTo().window(WindowHandlebefore);
				 * logs.info("Switched to main window");
				 * 
				 * // Handle Print window String WindowHandlebefore = driver.getWindowHandle();
				 * for (String windHandle : driver.getWindowHandles()) {
				 * driver.switchTo().window(windHandle); logs.info("Switched to Print window");
				 * Thread.sleep(5000); getScreenshot(driver, "PrintShipLabelService"); }
				 * driver.close(); logs.info("Closed Print window");
				 * 
				 * driver.switchTo().window(WindowHandlebefore);
				 * logs.info("Switched to main window");
				 */

			}

			try {

				// --Close
				WebElement SLClose = isElementPresent("SLSCloseBtn_id");
				js.executeScript("arguments[0].click();", SLClose);
				logs.info("Clicked on Close button of ShipLabel");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@ng-form=\\\"SLForm\\\"]")));
				Thread.sleep(2000);
			} catch (Exception CLoseee) {
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form=\"SLForm\"]")));

				// --Close
				WebElement SLClose = isElementPresent("SLSCloseBtn_id");
				act.moveToElement(SLClose).build().perform();
				act.moveToElement(SLClose).click().perform();
				logs.info("Clicked on Close button of ShipLabel");
				Thread.sleep(2000);
			}

		} catch (Exception noShipLabel) {
			logs.info("There is no Ship Label button" + noShipLabel);

		}

		return TrackNo;

		// --Close Ship Label Service pop up

		/*
		 * logs.info("===ShipLabel Test End==="); msg.append("===ShipLabel Test End==="
		 * + "\n\n");
		 */

	}

	public void stage3Pdeliver(int i) throws Exception {
		// JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait;
		try {
			wait = new WebDriverWait(driver,Duration.ofSeconds(15));// wait time

		} catch (Exception ewait) {
			wait = new WebDriverWait(driver,Duration.ofSeconds(60));
			// wait time

		}
		Actions act = new Actions(driver);

		// Search the Job
		OrderCreation OC = new OrderCreation();
		OC.searchJob(i);

		String STGName = OC.getStageName();
		String ServiceName = OC.getServiceID();

		if (STGName.equalsIgnoreCase("3P Deliver")
				|| STGName.equalsIgnoreCase("Deliver") && ServiceName.equalsIgnoreCase("H3P")) {
			// --Get the timeZone
			WebElement TimeZone = isElementPresent("EOCSTTime_id");
			act.moveToElement(TimeZone).build().perform();
			wait.until(ExpectedConditions.visibilityOf(TimeZone));
			String tzone = TimeZone.getText();
			String rectime = getTimeAsTZone(tzone);

			// --Move to DeliveryDate and Time
			WebElement DelTime = isElementPresent("EOActDelTime_id");
			act.moveToElement(DelTime).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(DelTime));
			DelTime.sendKeys(rectime);
			logs.info("Entered Delivery Time");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			String DELDateValue = getDateAsTZone(tzone);

			// --Move to DeliveryDate and Time
			WebElement DelDate = isElementPresent("EOActDelDate_id");
			act.moveToElement(DelDate).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(DelDate));
			DelDate.sendKeys(DELDateValue);
			logs.info("Entered Delivery Date");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Move to DeliveryDate and Time
			WebElement Signature = isElementPresent("EOSignature_id");
			act.moveToElement(Signature).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(Signature));
			Signature.sendKeys("Ravina");
			logs.info("Entered Signature");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Click on Save Changes button
			WebElement SaveChanges = isElementPresent("TLSaveChanges_id");
			act.moveToElement(SaveChanges).build().perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
			wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
			act.moveToElement(SaveChanges).click().perform();
			logs.info("Click on Save Changes button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(2000);

			try {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id=\"idValidationforMain\"]//ul[@id=\"errorid\"]")));
				String Validmsg = isElementPresent("OCValOnePack_xpath").getText();
				logs.info("Validation message is displayed=" + Validmsg);
				if (Validmsg.contains("Delivery time cannot be less than Drop Time.")) {
					// --Get the timeZone
					TimeZone = isElementPresent("EOH3pTimeZone_id");
					act.moveToElement(TimeZone).build().perform();
					wait.until(ExpectedConditions.visibilityOf(TimeZone));
					tzone = TimeZone.getText();
					logs.info("TimeZone=" + tzone);
					rectime = getTimeAsTZone(tzone);
					logs.info("Time=" + rectime);

					// --Move to DeliveryDate and Time
					DelTime = isElementPresent("EOActDelTime_id");
					act.moveToElement(DelTime).build().perform();
					wait.until(ExpectedConditions.elementToBeClickable(DelTime));
					DelTime.clear();
					DelTime.sendKeys(rectime);
					logs.info("Entered Delivery Time");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					DELDateValue = getDateAsTZone(tzone);
					logs.info("Date=" + DELDateValue);

					// --Move to DeliveryDate and Time
					DelDate = isElementPresent("EOActDelDate_id");
					act.moveToElement(DelDate).build().perform();
					wait.until(ExpectedConditions.elementToBeClickable(DelDate));
					DelDate.clear();
					DelDate.sendKeys(DELDateValue);
					logs.info("Entered Delivery Date");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Move to DeliveryDate and Time
					Signature = isElementPresent("EOSignature_id");
					act.moveToElement(Signature).build().perform();
					wait.until(ExpectedConditions.elementToBeClickable(Signature));
					Signature.clear();
					Signature.sendKeys("Ravina");
					logs.info("Entered Signature");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Click on Save Changes button
					SaveChanges = isElementPresent("TLSaveChanges_id");
					act.moveToElement(SaveChanges).build().perform();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
					wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
					act.moveToElement(SaveChanges).click().perform();
					logs.info("Click on Save Changes button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					Thread.sleep(2000);
					try {
						WebElement Validation = isElementPresent("EOValidation_id");
						wait.until(ExpectedConditions.visibilityOf(Validation));
						String ValMsg = Validation.getText();
						logs.info("Validation==" + ValMsg);

						if (ValMsg.equalsIgnoreCase("Pickup Phone# is Required.")) {
							// --Enter Pickup Phone No
							WebElement PUPhoneNo = isElementPresent("EOPickupPhone_id");
							act.moveToElement(PUPhoneNo).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(PUPhoneNo));
							PUPhoneNo.sendKeys("1112221112");
							logs.info("Entered PU Phone");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// --Click on Save Changes button
							SaveChanges = isElementPresent("TLSaveChanges_id");
							act.moveToElement(SaveChanges).build().perform();
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
							wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
							act.moveToElement(SaveChanges).click().perform();
							logs.info("Click on Save Changes button");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							Thread.sleep(2000);

							// --Click on Save&Exit button
							WebElement SaveExit = isElementPresent("EOSaveExit_id");
							act.moveToElement(SaveExit).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(SaveExit));
							act.moveToElement(SaveExit).click().perform();
							logs.info("Click on Save&Exit button");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							Thread.sleep(2000);

						} else if (Validmsg.contains("Delivery time cannot be less than Drop Time.")) {
							// --Get the timeZone
							TimeZone = isElementPresent("EOH3pTimeZone_id");
							act.moveToElement(TimeZone).build().perform();
							wait.until(ExpectedConditions.visibilityOf(TimeZone));
							tzone = TimeZone.getText();
							rectime = getTimeAsTZone(tzone);

							// --Move to DeliveryDate and Time
							DelTime = isElementPresent("EOActDelTime_id");
							act.moveToElement(DelTime).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(DelTime));
							DelTime.clear();
							DelTime.sendKeys(rectime);
							logs.info("Entered Delivery Time");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							DELDateValue = getDateAsTZone(tzone);

							// --Move to DeliveryDate and Time
							DelDate = isElementPresent("EOActDelDate_id");
							act.moveToElement(DelDate).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(DelDate));
							DelDate.sendKeys(DELDateValue);
							logs.info("Entered Delivery Date");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// --Move to DeliveryDate and Time
							Signature = isElementPresent("EOSignature_id");
							act.moveToElement(Signature).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(Signature));
							Signature.clear();
							Signature.sendKeys("Ravina");
							logs.info("Entered Signature");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// --Click on Save Changes button
							SaveChanges = isElementPresent("TLSaveChanges_id");
							act.moveToElement(SaveChanges).build().perform();
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
							wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
							act.moveToElement(SaveChanges).click().perform();
							logs.info("Click on Save Changes button");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							Thread.sleep(2000);
						}

					} catch (Exception eRequiredMsg) {

					}

				}

			} catch (

			Exception ee) {
				logs.info("Validation message is not displayed for Drop Time");

			}

			try {
				WebElement Validation = isElementPresent("EOValidation_id");
				wait.until(ExpectedConditions.visibilityOf(Validation));
				String ValMsg = Validation.getText();
				logs.info("Validation==" + ValMsg);

				if (ValMsg.equalsIgnoreCase("Pickup Phone# is Required.")) {
					// --Enter Pickup Phone No
					WebElement PUPhoneNo = isElementPresent("EOPickupPhone_id");
					act.moveToElement(PUPhoneNo).build().perform();
					wait.until(ExpectedConditions.elementToBeClickable(PUPhoneNo));
					PUPhoneNo.sendKeys("1112221112");
					logs.info("Entered PU Phone");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Click on Save Changes button
					SaveChanges = isElementPresent("TLSaveChanges_id");
					act.moveToElement(SaveChanges).build().perform();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
					wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
					act.moveToElement(SaveChanges).click().perform();
					logs.info("Click on Save Changes button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					Thread.sleep(2000);
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//*[@id=\"idValidationforMain\"]//ul[@id=\"errorid\"]")));
						String Validmsg = isElementPresent("OCValOnePack_xpath").getText();
						logs.info("Validation message is displayed=" + Validmsg);
						if (Validmsg.contains("Delivery time cannot be less than Drop Time.")) {
							// --Get the timeZone
							TimeZone = isElementPresent("EOH3pTimeZone_id");
							act.moveToElement(TimeZone).build().perform();
							wait.until(ExpectedConditions.visibilityOf(TimeZone));
							tzone = TimeZone.getText();
							logs.info("TimeZone=" + tzone);
							rectime = getTimeAsTZone(tzone);
							logs.info("Time=" + rectime);

							// --Move to DeliveryDate and Time
							DelTime = isElementPresent("EOActDelTime_id");
							act.moveToElement(DelTime).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(DelTime));
							DelTime.clear();
							DelTime.sendKeys(rectime);
							logs.info("Entered Delivery Time");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							DELDateValue = getDateAsTZone(tzone);
							logs.info("Date=" + DELDateValue);

							// --Move to DeliveryDate and Time
							DelDate = isElementPresent("EOActDelDate_id");
							act.moveToElement(DelDate).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(DelDate));
							DelDate.clear();
							DelDate.sendKeys(DELDateValue);
							logs.info("Entered Delivery Date");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// --Move to DeliveryDate and Time
							Signature = isElementPresent("EOSignature_id");
							act.moveToElement(Signature).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(Signature));
							Signature.clear();
							Signature.sendKeys("Ravina");
							logs.info("Entered Signature");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// --Click on Save Changes button
							SaveChanges = isElementPresent("TLSaveChanges_id");
							act.moveToElement(SaveChanges).build().perform();
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
							wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
							act.moveToElement(SaveChanges).click().perform();
							logs.info("Click on Save Changes button");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							Thread.sleep(2000);
							try {
								Validation = isElementPresent("EOValidation_id");
								wait.until(ExpectedConditions.visibilityOf(Validation));
								ValMsg = Validation.getText();
								logs.info("Validation==" + ValMsg);

								if (ValMsg.equalsIgnoreCase("Pickup Phone# is Required.")) {
									// --Enter Pickup Phone No
									PUPhoneNo = isElementPresent("EOPickupPhone_id");
									act.moveToElement(PUPhoneNo).build().perform();
									wait.until(ExpectedConditions.elementToBeClickable(PUPhoneNo));
									PUPhoneNo.sendKeys("1112221112");
									logs.info("Entered PU Phone");
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

									// --Click on Save Changes button
									SaveChanges = isElementPresent("TLSaveChanges_id");
									act.moveToElement(SaveChanges).build().perform();
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
									wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
									act.moveToElement(SaveChanges).click().perform();
									logs.info("Click on Save Changes button");
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
									Thread.sleep(2000);

									// --Click on Save&Exit button
									WebElement SaveExit = isElementPresent("EOSaveExit_id");
									act.moveToElement(SaveExit).build().perform();
									wait.until(ExpectedConditions.elementToBeClickable(SaveExit));
									act.moveToElement(SaveExit).click().perform();
									logs.info("Click on Save&Exit button");
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
									Thread.sleep(2000);

								} else if (Validmsg.contains("Delivery time cannot be less than Drop Time.")) {
									// --Get the timeZone
									TimeZone = isElementPresent("EOH3pTimeZone_id");
									act.moveToElement(TimeZone).build().perform();
									wait.until(ExpectedConditions.visibilityOf(TimeZone));
									tzone = TimeZone.getText();
									rectime = getTimeAsTZone(tzone);

									// --Move to DeliveryDate and Time
									DelTime = isElementPresent("EOActDelTime_id");
									act.moveToElement(DelTime).build().perform();
									wait.until(ExpectedConditions.elementToBeClickable(DelTime));
									DelTime.clear();
									DelTime.sendKeys(rectime);
									logs.info("Entered Delivery Time");
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

									DELDateValue = getDateAsTZone(tzone);

									// --Move to DeliveryDate and Time
									DelDate = isElementPresent("EOActDelDate_id");
									act.moveToElement(DelDate).build().perform();
									wait.until(ExpectedConditions.elementToBeClickable(DelDate));
									DelDate.sendKeys(DELDateValue);
									logs.info("Entered Delivery Date");
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

									// --Move to DeliveryDate and Time
									Signature = isElementPresent("EOSignature_id");
									act.moveToElement(Signature).build().perform();
									wait.until(ExpectedConditions.elementToBeClickable(Signature));
									Signature.clear();
									Signature.sendKeys("Ravina");
									logs.info("Entered Signature");
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

									// --Click on Save Changes button
									SaveChanges = isElementPresent("TLSaveChanges_id");
									act.moveToElement(SaveChanges).build().perform();
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
									wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
									act.moveToElement(SaveChanges).click().perform();
									logs.info("Click on Save Changes button");
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
									Thread.sleep(2000);
								}

							} catch (Exception eRequiredMsg) {

							}

						}

					} catch (

					Exception ee) {
						logs.info("Validation message is not displayed for Drop Time");

					}

					// --Click on Save&Exit button
					WebElement SaveExit = isElementPresent("EOSaveExit_id");
					act.moveToElement(SaveExit).build().perform();
					wait.until(ExpectedConditions.elementToBeClickable(SaveExit));
					act.moveToElement(SaveExit).click().perform();
					logs.info("Click on Save&Exit button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					Thread.sleep(2000);

				}
			} catch (Exception eRequiredMsg) {

			}
		} else if (STGName.equalsIgnoreCase("Deliver")
				&& (ServiceName.equalsIgnoreCase("D3P") || ServiceName.equalsIgnoreCase("P3P"))) {

			// --Get the timeZone
			WebElement TimeZone = isElementPresent("D3PDelTimeZone_id");
			act.moveToElement(TimeZone).build().perform();
			wait.until(ExpectedConditions.visibilityOf(TimeZone));
			String tzone = TimeZone.getText();
			String rectime = getTimeAsTZone(tzone);

			// --Move to DeliveryDate and Time
			WebElement DelTime = isElementPresent("D3PDelTime_id");
			act.moveToElement(DelTime).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(DelTime));
			DelTime.sendKeys(rectime);
			logs.info("Entered Delivery Time");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			String DELDateValue = getDateAsTZone(tzone);

			// --Move to DeliveryDate and Time
			WebElement DelDate = isElementPresent("D3PDelDate_id");
			act.moveToElement(DelDate).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(DelDate));
			DelDate.sendKeys(DELDateValue);
			logs.info("Entered Delivery Date");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Move to DeliveryDate and Time
			WebElement Signature = isElementPresent("D3PDelSign_id");
			act.moveToElement(Signature).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(Signature));
			Signature.sendKeys("Ravina");
			logs.info("Entered Signature");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Click on Save Changes button
			WebElement SaveChanges = isElementPresent("TLSaveChanges_id");
			act.moveToElement(SaveChanges).build().perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
			wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
			act.moveToElement(SaveChanges).click().perform();
			logs.info("Click on Save Changes button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(2000);

			try {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id=\"idValidationforMain\"]//ul[@id=\"errorid\"]")));
				String Validmsg = isElementPresent("OCValOnePack_xpath").getText();
				logs.info("Validation message is displayed=" + Validmsg);
				if (Validmsg.contains("Actual Datetime cannot be greater than Current Datetime.")) {
					// --Get the timeZone
					TimeZone = isElementPresent("D3pDelivTimeZone_xpath");
					act.moveToElement(TimeZone).build().perform();
					wait.until(ExpectedConditions.visibilityOf(TimeZone));
					tzone = TimeZone.getText();
					rectime = getTimeAsTZone(tzone);

					// --Move to DeliveryDate and Time
					DelTime = isElementPresent("D3PDelTime_id");
					act.moveToElement(DelTime).build().perform();
					wait.until(ExpectedConditions.elementToBeClickable(DelTime));
					DelTime.clear();
					DelTime.sendKeys(rectime);
					logs.info("Entered Delivery Time");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					DELDateValue = getDateAsTZone(tzone);

					// --Move to DeliveryDate and Time
					DelDate = isElementPresent("D3PDelDate_id");
					act.moveToElement(DelDate).build().perform();
					wait.until(ExpectedConditions.elementToBeClickable(DelDate));
					DelDate.clear();
					DelDate.sendKeys(DELDateValue);
					logs.info("Entered Delivery Date");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Move to DeliveryDate and Time
					Signature = isElementPresent("D3PDelSign_id");
					act.moveToElement(Signature).build().perform();
					wait.until(ExpectedConditions.elementToBeClickable(Signature));
					Signature.clear();
					Signature.sendKeys("Ravina");
					logs.info("Entered Signature");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Click on Save Changes button
					SaveChanges = isElementPresent("TLSaveChanges_id");
					act.moveToElement(SaveChanges).build().perform();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
					wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
					act.moveToElement(SaveChanges).click().perform();
					logs.info("Click on Save Changes button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					Thread.sleep(2000);
					try {
						WebElement Validation = isElementPresent("EOValidation_id");
						wait.until(ExpectedConditions.visibilityOf(Validation));
						String ValMsg = Validation.getText();
						logs.info("Validation==" + ValMsg);

						if (ValMsg.equalsIgnoreCase("Pickup Phone# is Required.")) {
							// --Enter Pickup Phone No
							WebElement PUPhoneNo = isElementPresent("EOPickupPhone_id");
							act.moveToElement(PUPhoneNo).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(PUPhoneNo));
							PUPhoneNo.sendKeys("1112221112");
							logs.info("Entered PU Phone");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// --Click on Save Changes button
							SaveChanges = isElementPresent("TLSaveChanges_id");
							act.moveToElement(SaveChanges).build().perform();
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
							wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
							act.moveToElement(SaveChanges).click().perform();
							logs.info("Click on Save Changes button");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							Thread.sleep(2000);

							// --Click on Save&Exit button
							WebElement SaveExit = isElementPresent("EOSaveExit_id");
							act.moveToElement(SaveExit).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(SaveExit));
							act.moveToElement(SaveExit).click().perform();
							logs.info("Click on Save&Exit button");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							Thread.sleep(2000);

						} else if (Validmsg.contains("Delivery time cannot be less than Drop Time.")) {
							// --Move to DeliveryDate and Time
							DelTime = isElementPresent("EOActDelTime_id");
							act.moveToElement(DelTime).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(DelTime));
							DelTime.sendKeys(rectime);
							logs.info("Entered Delivery Time");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							DELDateValue = getDateAsTZone(tzone);

							// --Move to DeliveryDate and Time
							DelDate = isElementPresent("D3PDelDate_id");
							act.moveToElement(DelDate).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(DelDate));
							DelDate.sendKeys(DELDateValue);
							logs.info("Entered Delivery Date");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// --Move to DeliveryDate and Time
							Signature = isElementPresent("D3PDelSign_id");
							act.moveToElement(Signature).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(Signature));
							Signature.sendKeys("Ravina");
							logs.info("Entered Signature");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// --Click on Save Changes button
							SaveChanges = isElementPresent("TLSaveChanges_id");
							act.moveToElement(SaveChanges).build().perform();
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
							wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
							act.moveToElement(SaveChanges).click().perform();
							logs.info("Click on Save Changes button");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							Thread.sleep(2000);
						}

					} catch (Exception eRequiredMsg) {

					}

				}
			} catch (

			Exception ee) {
				logs.info("Validation message is not displayed for Drop Time");

			}
			try {
				WebElement Validation = isElementPresent("EOValidation_id");
				wait.until(ExpectedConditions.visibilityOf(Validation));
				String ValMsg = Validation.getText();
				logs.info("Validation==" + ValMsg);

				if (ValMsg.equalsIgnoreCase("Pickup Phone# is Required.")) {
					// --Enter Pickup Phone No
					WebElement PUPhoneNo = isElementPresent("EOPickupPhone_id");
					act.moveToElement(PUPhoneNo).build().perform();
					wait.until(ExpectedConditions.elementToBeClickable(PUPhoneNo));
					PUPhoneNo.sendKeys("1112221112");
					logs.info("Entered PU Phone");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					WebElement Commo = isElementPresent("EOCommodity_id");
					act.moveToElement(Commo).build().perform();
					wait.until(ExpectedConditions.elementToBeClickable(Commo));
					Commo.sendKeys("Packet");
					logs.info("Entered Commodity");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Click on Save Changes button
					SaveChanges = isElementPresent("TLSaveChanges_id");
					act.moveToElement(SaveChanges).build().perform();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
					wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
					act.moveToElement(SaveChanges).click().perform();
					logs.info("Click on Save Changes button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
					Thread.sleep(2000);
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//*[@id=\"idValidationforMain\"]//ul[@id=\"errorid\"]")));
						String Validmsg = isElementPresent("OCValOnePack_xpath").getText();
						logs.info("Validation message is displayed=" + Validmsg);
						if (Validmsg.contains("Actual Datetime cannot be greater than Current Datetime.")) {
							// --Get the timeZone
							TimeZone = isElementPresent("D3pDelivTimeZone_xpath");
							act.moveToElement(TimeZone).build().perform();
							wait.until(ExpectedConditions.visibilityOf(TimeZone));
							tzone = TimeZone.getText();
							rectime = getTimeAsTZone(tzone);

							// --Move to DeliveryDate and Time
							DelTime = isElementPresent("D3PDelTime_id");
							act.moveToElement(DelTime).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(DelTime));
							DelTime.clear();
							DelTime.sendKeys(rectime);
							logs.info("Entered Delivery Time");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							DELDateValue = getDateAsTZone(tzone);

							// --Move to DeliveryDate and Time
							DelDate = isElementPresent("D3PDelDate_id");
							act.moveToElement(DelDate).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(DelDate));
							DelDate.clear();
							DelDate.sendKeys(DELDateValue);
							logs.info("Entered Delivery Date");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// --Move to DeliveryDate and Time
							Signature = isElementPresent("D3PDelSign_id");
							act.moveToElement(Signature).build().perform();
							wait.until(ExpectedConditions.elementToBeClickable(Signature));
							Signature.clear();
							Signature.sendKeys("Ravina");
							logs.info("Entered Signature");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// --Click on Save Changes button
							SaveChanges = isElementPresent("TLSaveChanges_id");
							act.moveToElement(SaveChanges).build().perform();
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
							wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
							act.moveToElement(SaveChanges).click().perform();
							logs.info("Click on Save Changes button");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							Thread.sleep(2000);
							try {
								Validation = isElementPresent("EOValidation_id");
								wait.until(ExpectedConditions.visibilityOf(Validation));
								ValMsg = Validation.getText();
								logs.info("Validation==" + ValMsg);

								if (ValMsg.equalsIgnoreCase("Pickup Phone# is Required.")) {
									// --Enter Pickup Phone No
									PUPhoneNo = isElementPresent("EOPickupPhone_id");
									act.moveToElement(PUPhoneNo).build().perform();
									wait.until(ExpectedConditions.elementToBeClickable(PUPhoneNo));
									PUPhoneNo.sendKeys("1112221112");
									logs.info("Entered PU Phone");
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

									// --Click on Save Changes button
									SaveChanges = isElementPresent("TLSaveChanges_id");
									act.moveToElement(SaveChanges).build().perform();
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
									wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
									act.moveToElement(SaveChanges).click().perform();
									logs.info("Click on Save Changes button");
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
									Thread.sleep(2000);

									// --Click on Save&Exit button
									WebElement SaveExit = isElementPresent("EOSaveExit_id");
									act.moveToElement(SaveExit).build().perform();
									wait.until(ExpectedConditions.elementToBeClickable(SaveExit));
									act.moveToElement(SaveExit).click().perform();
									logs.info("Click on Save&Exit button");
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
									Thread.sleep(2000);

								} else if (Validmsg.contains("Delivery time cannot be less than Drop Time.")) {
									// --Move to DeliveryDate and Time
									DelTime = isElementPresent("EOActDelTime_id");
									act.moveToElement(DelTime).build().perform();
									wait.until(ExpectedConditions.elementToBeClickable(DelTime));
									DelTime.sendKeys(rectime);
									logs.info("Entered Delivery Time");
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

									DELDateValue = getDateAsTZone(tzone);

									// --Move to DeliveryDate and Time
									DelDate = isElementPresent("D3PDelDate_id");
									act.moveToElement(DelDate).build().perform();
									wait.until(ExpectedConditions.elementToBeClickable(DelDate));
									DelDate.sendKeys(DELDateValue);
									logs.info("Entered Delivery Date");
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

									// --Move to DeliveryDate and Time
									Signature = isElementPresent("D3PDelSign_id");
									act.moveToElement(Signature).build().perform();
									wait.until(ExpectedConditions.elementToBeClickable(Signature));
									Signature.sendKeys("Ravina");
									logs.info("Entered Signature");
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

									// --Click on Save Changes button
									SaveChanges = isElementPresent("TLSaveChanges_id");
									act.moveToElement(SaveChanges).build().perform();
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
									wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
									act.moveToElement(SaveChanges).click().perform();
									logs.info("Click on Save Changes button");
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
									Thread.sleep(2000);
								}

							} catch (Exception eRequiredMsg) {

							}

						}
					} catch (

					Exception ee) {
						logs.info("Validation message is not displayed for Drop Time");

					}

				}

			} catch (Exception eRequiredMsg) {

			}

		}

		// --Click on Save&Exit button
		WebElement SaveExit = isElementPresent("EOSaveExit_xpath");
		act.moveToElement(SaveExit).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(SaveExit));
		act.moveToElement(SaveExit).click().perform();
		logs.info("Click on Save&Exit button");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		Thread.sleep(2000);

	}
}
