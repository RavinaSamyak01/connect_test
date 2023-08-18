package connect_OCBaseMethods;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimeZone;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import connect_BasePackage.BaseInit;

public class ConfirmPULLstg extends BaseInit {

	public void ConfirmPullstage(int i) throws Exception {

		JavascriptExecutor js = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
		Actions act = new Actions(driver);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		OrderCreation OC = new OrderCreation();
		if (i >= 10) {
			OC.searchJob(i);

			// --Go to Job status tab if not selected
			try {
				WebElement EditOrTabSelect = isElementPresent("EditOrderTabsel_xpath");
				wait.until(ExpectedConditions.visibilityOf(EditOrTabSelect));

				// --Move to Job Status Tab
				WebElement JoStatusTab = isElementPresent("TLJobStatusTab_id");
				wait.until(ExpectedConditions.visibilityOf(JoStatusTab));
				wait.until(ExpectedConditions.elementToBeClickable(JoStatusTab));
				act.moveToElement(JoStatusTab).click().build().perform();
				logs.info("Clicked on Job Status Tab");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			} catch (Exception eTabSelection) {
				// --Go to Job status tab is selected

			}
		} else {
			System.out.println("No need to run Search");
			logs.info("Job is already in edit mode");

		}

		// --Get the ServiceID
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblServiceID")));
		String svc = isElementPresent("TLServID_id").getText();
		System.out.println(svc);
		logs.info("ServiceID=" + svc);

		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@id=\"lblStages\"][contains(text(),'Confirm Pull')]")));

			// --Get StageName
			OC.getStageName();

			if (svc.equalsIgnoreCase("SD")) {
				// Click on Confirm Pull
				WebElement ConfmPull = isElementPresent("TLConfirmPull_id");
				wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
				act.moveToElement(ConfmPull).build().perform();
				js.executeScript("arguments[0].click();", ConfmPull);
				logs.info("Click on Confirm Pull  button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				// --Get the validation
				try {
					wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@ng-message=\"required\"]")));
					String Valmsg = isElementPresent("PartTimerequired_xpath").getText();
					logs.info("Validation message is displayed=" + Valmsg);
					if (Valmsg.contains("Part Pull time Required.")) {
						// --Get the timeZone
						String tzone = isElementPresent("TT3TimeZone_id").getText();
						String rectime = getTimeAsTZone(tzone);

						// --Enter Actual Pickup Time
						isElementPresent("PartPullTime_id").clear();
						isElementPresent("PartPullTime_id").sendKeys(rectime);
						isElementPresent("PartPullTime_id").sendKeys(Keys.TAB);
						logs.info("Enter Actual Part Pull time");

						// Click on Confirm Pull
						ConfmPull = isElementPresent("TLConfirmPull_id");
						wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
						act.moveToElement(ConfmPull).build().perform();
						js.executeScript("arguments[0].click();", ConfmPull);
						logs.info("Click on Confirm Pull  button");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//*[@id=\"idValidationforMain\"]//ul[@id=\"errorid\"]")));
							String Validmsg = isElementPresent("OCValOnePack_xpath").getText();
							logs.info("Validation message is displayed=" + Validmsg);
							if (Validmsg.contains("Parameter(s) are modified. Please recalculate customer charges.")) {
								// Recalculate the charges
								// --Go to Edit Job tab
								WebElement EditOrTab = isElementPresent("EOEditOrderTab_id");
								act.moveToElement(EditOrTab).build().perform();
								wait.until(ExpectedConditions.elementToBeClickable(EditOrTab));
								act.moveToElement(EditOrTab).click().perform();
								logs.info("Click on Edit Order Tab");
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
								Thread.sleep(5000);

								// -Recalculate button
								WebElement ReCalc = isElementPresent("EORecal_id");
								act.moveToElement(ReCalc).build().perform();
								wait.until(ExpectedConditions.elementToBeClickable(ReCalc));
								act.moveToElement(ReCalc).click().perform();
								logs.info("Click on Recalculate button");
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
								Thread.sleep(5000);

								// --Click on Save Changes button
								WebElement SaveChanges = isElementPresent("TLSaveChanges_id");
								act.moveToElement(SaveChanges).build().perform();
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
								wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
								js.executeScript("arguments[0].click();", SaveChanges);
								logs.info("Click on Save Changes button");
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
								Thread.sleep(5000);

								// --Go to job Status Tab
								WebElement JobOverTab = isElementPresent("TLJobStatusTab_id");
								act.moveToElement(JobOverTab).build().perform();
								wait.until(ExpectedConditions.elementToBeClickable(JobOverTab));
								act.moveToElement(JobOverTab).click().perform();
								logs.info("Click on Job Overview Tab");
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

								tzone = isElementPresent("TT3TimeZone_id").getText();
								rectime = getTimeAsTZone(tzone);

								// --Enter Actual Pickup Time
								isElementPresent("PartPullTime_id").clear();
								isElementPresent("PartPullTime_id").sendKeys(rectime);
								isElementPresent("PartPullTime_id").sendKeys(Keys.TAB);
								logs.info("Enter Actual Part Pull time");

								// Click on Confirm Pull
								ConfmPull = isElementPresent("TLConfirmPull_id");
								wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
								act.moveToElement(ConfmPull).build().perform();
								js.executeScript("arguments[0].click();", ConfmPull);
								logs.info("Click on Confirm Pull  button");
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

								/*
								 * js.executeScript("arguments[0].click();", ConfmPull);
								 * logs.info("Click on Confirm Pull  button");
								 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")
								 * ));
								 */

								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(
											By.xpath("//*[@id=\"idValidationforMain\"]//ul[@id=\"errorid\"]")));
									Validmsg = isElementPresent("OCValOnePack_xpath").getText();
									logs.info("Validation message is displayed=" + Validmsg);
									if (Validmsg.contains("Actual Datetime cannot be greater than Current Datetime.")) {
										// Recalculate the charges
										tzone = isElementPresent("TT3TimeZone_id").getText();
										rectime = getTimeAsTZone(tzone);

										// --Part Pull Date
										String PullDate = getDateAsTZone(tzone);
										System.out.println("Pull Date==" + PullDate);
										isElementPresent("TLPartPullDate_id").clear();
										isElementPresent("TLPartPullDate_id").sendKeys(PullDate);
										isElementPresent("TLPartPullDate_id").sendKeys(Keys.TAB);
										logs.info("Enter Actual Part Pull Date");

										/*
										 * WebElement PullDate = isElementPresent("TLPartPullDate_id");
										 * PullDate.clear(); Date date = new Date(); DateFormat dateFormat = new
										 * SimpleDateFormat("MM/dd/yyyy");
										 * dateFormat.setTimeZone(TimeZone.getTimeZone(tzone));
										 * logs.info(dateFormat.format(date));
										 * PullDate.sendKeys(dateFormat.format(date)); PullDate.sendKeys(Keys.TAB);
										 * logs.info("Entered Actual Pull Date");
										 */

										// --Enter Actual Pull Time
										isElementPresent("PartPullTime_id").clear();
										isElementPresent("PartPullTime_id").sendKeys(rectime);
										isElementPresent("PartPullTime_id").sendKeys(Keys.TAB);
										logs.info("Enter Actual Part Pull time");

										// Click on Confirm Pull
										ConfmPull = isElementPresent("TLConfirmPull_id");
										wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
										act.moveToElement(ConfmPull).build().perform();
										js.executeScript("arguments[0].click();", ConfmPull);
										logs.info("Click on Confirm Pull  button");
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

										js.executeScript("arguments[0].click();", ConfmPull);
										logs.info("Click on Confirm Pull  button");
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
									}

								} catch (Exception ee) {
									logs.info("Validation message is not displayed for Recalculate");

								}
							}

						} catch (Exception ee) {
							logs.info("Validation message is not displayed for Recalculate");

						}
					}

				} catch (Exception error) {
					logs.info("Validation message is not displayed for PartTime");

				}
			} else if (svc.equalsIgnoreCase("H3P") || svc.equalsIgnoreCase("D3P") || svc.equalsIgnoreCase("3PLAST")) {
				// Click on Confirm Pull
				WebElement ConfmPull = isElementPresent("H3PConfirmPUll_id");
				wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
				act.moveToElement(ConfmPull).build().perform();
				js.executeScript("arguments[0].click();", ConfmPull);
				logs.info("Click on Confirm Pull  button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				// --Get the validation
				try {
					wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@ng-message=\"required\"]")));
					String Valmsg = isElementPresent("PartTimerequired_xpath").getText();
					logs.info("Validation message is displayed=" + Valmsg);
					if (Valmsg.contains("Part Pull time Required.")) {
						// --Get the timeZone
						String tzone = isElementPresent("TT3TimeZone_id").getText();
						String rectime = getTimeAsTZone(tzone);
						String RecDate = getDateAsTZone(tzone);

						// --Enter Actual Pickup Date
						isElementPresent("H3PPartPullDate_id").clear();
						isElementPresent("H3PPartPullDate_id").sendKeys(RecDate);
						isElementPresent("H3PPartPullDate_id").sendKeys(Keys.TAB);
						logs.info("Enter Actual Part Pull Date");

						String QPT = driver.findElement(By.id("txtQuotedforPickupTime")).getAttribute("value");
						logs.info("QPT==" + QPT);

						LocalTime t = LocalTime.parse(QPT);
						LocalTime tn = t.minusMinutes(1);
						String Time = tn.toString();
						logs.info("new time==" + Time);

						// --Enter Actual Pickup Time
						isElementPresent("H3PPartPullTime_id").clear();
						isElementPresent("H3PPartPullTime_id").sendKeys(rectime);
						isElementPresent("H3PPartPullTime_id").sendKeys(Keys.TAB);
						logs.info("Enter Actual Part Pull time");

						// Click on Confirm Pull

						Thread.sleep(5000);
						// Click on Confirm Pull
						ConfmPull = isElementPresent("H3PConfirmPUll_id");
						act.moveToElement(ConfmPull).build().perform();
						wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
						ConfmPull = isElementPresent("H3PConfirmPUll_id");
						wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
						act.moveToElement(ConfmPull).build().perform();
						ConfmPull.click();
						logs.info("Click on Confirm Pull  button");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//*[@id=\"idValidationforMain\"]//ul[@id=\"errorid\"]")));
							String Validmsg = isElementPresent("OCValOnePack_xpath").getText();
							logs.info("Validation message is displayed=" + Validmsg);
							if (Validmsg.contains("Parameter(s) are modified. Please recalculate customer charges.")) {
								// Recalculate the charges
								// --Go to Edit Job tab
								WebElement EditOrTab = isElementPresent("EOEditOrderTab_id");
								act.moveToElement(EditOrTab).build().perform();
								wait.until(ExpectedConditions.elementToBeClickable(EditOrTab));
								act.moveToElement(EditOrTab).click().perform();
								logs.info("Click on Edit Order Tab");
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
								Thread.sleep(5000);

								// -Recalculate button
								WebElement ReCalc = isElementPresent("EORecal_id");
								act.moveToElement(ReCalc).build().perform();
								wait.until(ExpectedConditions.elementToBeClickable(ReCalc));
								act.moveToElement(ReCalc).click().perform();
								logs.info("Click on Recalculate button");
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
								Thread.sleep(5000);

								// --Click on Save Changes button
								WebElement SaveChanges = isElementPresent("TLSaveChanges_id");
								act.moveToElement(SaveChanges).build().perform();
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
								wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
								js.executeScript("arguments[0].click();", SaveChanges);
								logs.info("Click on Save Changes button");
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
								Thread.sleep(5000);

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
										wait.until(
												ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
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

								tzone = isElementPresent("TT3TimeZone_id").getText();
								rectime = getTimeAsTZone(tzone);
								RecDate = getDateAsTZone(tzone);

								// --Enter Actual Pickup Date
								isElementPresent("H3PPartPullDate_id").clear();
								isElementPresent("H3PPartPullDate_id").sendKeys(RecDate);
								isElementPresent("H3PPartPullDate_id").sendKeys(Keys.TAB);
								logs.info("Enter Actual Part Pull Date");

								QPT = driver.findElement(By.id("txtQuotedforPickupTime")).getAttribute("value");
								logs.info("QPT==" + QPT);

								t = LocalTime.parse(QPT);
								tn = t.minusMinutes(1);
								Time = tn.toString();
								logs.info("new time==" + Time);

								// --Enter Actual Pickup Time
								isElementPresent("H3PPartPullTime_id").clear();
								isElementPresent("H3PPartPullTime_id").sendKeys(rectime);
								isElementPresent("H3PPartPullTime_id").sendKeys(Keys.TAB);
								logs.info("Enter Actual Part Pull time");

								// Click on Confirm Pull

								Thread.sleep(5000);
								// Click on Confirm Pull
								ConfmPull = isElementPresent("H3PConfirmPUll_id");
								act.moveToElement(ConfmPull).build().perform();
								wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
								ConfmPull = isElementPresent("H3PConfirmPUll_id");
								wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
								act.moveToElement(ConfmPull).build().perform();
								ConfmPull.click();
								logs.info("Click on Confirm Pull  button");
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

								/*
								 * js.executeScript("arguments[0].click();", ConfmPull);
								 * logs.info("Click on Confirm Pull  button");
								 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")
								 * ));
								 */
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(
											By.xpath("//*[@id=\"idValidationforMain\"]//ul[@id=\"errorid\"]")));
									Validmsg = isElementPresent("OCValOnePack_xpath").getText();
									logs.info("Validation message is displayed=" + Validmsg);
									if (Validmsg.contains("Actual Datetime cannot be greater than Current Datetime.")) {
										// Recalculate the charges
										tzone = isElementPresent("TT3TimeZone_id").getText();
										rectime = getTimeAsTZone(tzone);

										// --Part Pull Date
										String PullDate = getDateAsTZone(tzone);
										System.out.println("Pull Date==" + PullDate);
										isElementPresent("H3PPartPullDate_id").clear();
										isElementPresent("H3PPartPullDate_id").sendKeys(PullDate);
										isElementPresent("H3PPartPullDate_id").sendKeys(Keys.TAB);
										logs.info("Enter Actual Part Pull Date");

										/*
										 * WebElement PullDate = isElementPresent("TLPartPullDate_id");
										 * PullDate.clear(); Date date = new Date(); DateFormat dateFormat = new
										 * SimpleDateFormat("MM/dd/yyyy");
										 * dateFormat.setTimeZone(TimeZone.getTimeZone(tzone));
										 * logs.info(dateFormat.format(date));
										 * PullDate.sendKeys(dateFormat.format(date)); PullDate.sendKeys(Keys.TAB);
										 * logs.info("Entered Actual Pull Date");
										 */

										QPT = driver.findElement(By.id("txtQuotedforPickupTime")).getAttribute("value");
										logs.info("QPT==" + QPT);

										t = LocalTime.parse(QPT);
										tn = t.minusMinutes(1);
										Time = tn.toString();
										logs.info("new time==" + Time);

										// --Enter Actual Pickup Time
										isElementPresent("H3PPartPullTime_id").clear();
										isElementPresent("H3PPartPullTime_id").sendKeys(rectime);
										isElementPresent("H3PPartPullTime_id").sendKeys(Keys.TAB);
										logs.info("Enter Actual Part Pull time");

										// Click on Confirm Pull

										Thread.sleep(5000);
										// Click on Confirm Pull
										ConfmPull = isElementPresent("H3PConfirmPUll_id");
										act.moveToElement(ConfmPull).build().perform();
										wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
										ConfmPull = isElementPresent("H3PConfirmPUll_id");
										wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
										act.moveToElement(ConfmPull).build().perform();
										ConfmPull.click();
										logs.info("Click on Confirm Pull  button");
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

										js.executeScript("arguments[0].click();", ConfmPull);
										logs.info("Click on Confirm Pull  button");
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
									} else if (Validmsg.contains(
											"Parameter(s) are modified. Please recalculate customer charges.")) {
										// Recalculate the charges
										// --Go to Edit Job tab
										EditOrTab = isElementPresent("EOEditOrderTab_id");
										act.moveToElement(EditOrTab).build().perform();
										wait.until(ExpectedConditions.elementToBeClickable(EditOrTab));
										act.moveToElement(EditOrTab).click().perform();
										logs.info("Click on Edit Order Tab");
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
										Thread.sleep(5000);

										// -Recalculate button
										ReCalc = isElementPresent("EORecal_id");
										act.moveToElement(ReCalc).build().perform();
										wait.until(ExpectedConditions.elementToBeClickable(ReCalc));
										act.moveToElement(ReCalc).click().perform();
										logs.info("Click on Recalculate button");
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
										Thread.sleep(5000);

										// --Click on Save Changes button
										SaveChanges = isElementPresent("TLSaveChanges_id");
										act.moveToElement(SaveChanges).build().perform();
										wait.until(
												ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
										wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
										js.executeScript("arguments[0].click();", SaveChanges);
										logs.info("Click on Save Changes button");
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
										Thread.sleep(5000);

										// --Go to job Status Tab
										JobOverTab = isElementPresent("TLJobStatusTab_id");
										act.moveToElement(JobOverTab).build().perform();
										wait.until(ExpectedConditions.elementToBeClickable(JobOverTab));
										act.moveToElement(JobOverTab).click().perform();
										logs.info("Click on Job Overview Tab");
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

										// --Get the timeZone
										tzone = isElementPresent("TT3TimeZone_id").getText();
										rectime = getTimeAsTZone(tzone);
										RecDate = getDateAsTZone(tzone);

										// --Enter Actual Pickup Date
										isElementPresent("H3PPartPullDate_id").clear();
										isElementPresent("H3PPartPullDate_id").sendKeys(RecDate);
										isElementPresent("H3PPartPullDate_id").sendKeys(Keys.TAB);
										logs.info("Enter Actual Part Pull Date");

										QPT = driver.findElement(By.id("txtQuotedforPickupTime")).getAttribute("value");
										logs.info("QPT==" + QPT);

										t = LocalTime.parse(QPT);
										tn = t.minusMinutes(1);
										Time = tn.toString();
										logs.info("new time==" + Time);

										// --Enter Actual Pickup Time
										isElementPresent("H3PPartPullTime_id").clear();
										isElementPresent("H3PPartPullTime_id").sendKeys(rectime);
										isElementPresent("H3PPartPullTime_id").sendKeys(Keys.TAB);
										logs.info("Enter Actual Part Pull time");

										Thread.sleep(5000);
										// Click on Confirm Pull
										ConfmPull = isElementPresent("H3PConfirmPUll_id");
										act.moveToElement(ConfmPull).build().perform();
										wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
										ConfmPull = isElementPresent("H3PConfirmPUll_id");
										wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
										act.moveToElement(ConfmPull).build().perform();
										ConfmPull.click();
										// js.executeScript("arguments[0].click();", ConfmPull);
										logs.info("Click on Confirm Pull  button");

										/*
										 * wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
										 * act.moveToElement(ConfmPull).click().build().perform();
										 * logs.info("Click on Confirm Pull  button");
										 */
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

									}

								} catch (Exception ee) {
									logs.info(ee);
									logs.info("Validation message is not displayed for Recalculate");

								}
							}

						} catch (Exception ee) {
							logs.info(ee);

							logs.info("Validation message is not displayed for Recalculate");

						}
					}

				} catch (Exception error) {
					System.out.println(error);
					logs.info("Validation message is not displayed for PartTime");

				}
			} else if (svc.equalsIgnoreCase("CPU")) {
				// Click on Confirm Pull
				WebElement ConfmPull = isElementPresent("H3PConfirmPUll_id");
				wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
				act.moveToElement(ConfmPull).build().perform();
				js.executeScript("arguments[0].click();", ConfmPull);
				logs.info("Click on Confirm Pull  button");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				// --Get the validation
				try {
					wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@ng-message=\"required\"]")));
					String Valmsg = isElementPresent("PartTimerequired_xpath").getText();
					logs.info("Validation message is displayed=" + Valmsg);
					if (Valmsg.contains("Part Pull time Required.")) {
						// --Get the timeZone
						String tzone = isElementPresent("TT3TimeZone_id").getText();
						String rectime = getTimeAsTZone(tzone);
						String RecDate = getDateAsTZone(tzone);

						// --Enter Actual Pickup Date
						isElementPresent("H3PPartPullDate_id").clear();
						isElementPresent("H3PPartPullDate_id").sendKeys(RecDate);
						isElementPresent("H3PPartPullDate_id").sendKeys(Keys.TAB);
						logs.info("Enter Actual Part Pull Date");

						/*
						 * String QPT =
						 * driver.findElement(By.id("txtQuotedforPickupTime")).getAttribute("value");
						 * logs.info("QPT==" + QPT);
						 * 
						 * LocalTime t = LocalTime.parse(QPT); LocalTime tn = t.plusMinutes(1); String
						 * Time = tn.toString(); logs.info("new time==" + Time);
						 */

						// --Enter Actual Pickup Time
						isElementPresent("H3PPartPullTime_id").clear();
						isElementPresent("H3PPartPullTime_id").sendKeys(rectime);
						isElementPresent("H3PPartPullTime_id").sendKeys(Keys.TAB);
						logs.info("Enter Actual Part Pull time");

						// Click on Confirm Pull

						Thread.sleep(5000);
						// Click on Confirm Pull
						ConfmPull = isElementPresent("H3PConfirmPUll_id");
						act.moveToElement(ConfmPull).build().perform();
						wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
						ConfmPull = isElementPresent("H3PConfirmPUll_id");
						wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
						act.moveToElement(ConfmPull).build().perform();
						ConfmPull.click();
						logs.info("Click on Confirm Pull  button");
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//*[@id=\"idValidationforMain\"]//ul[@id=\"errorid\"]")));
							String Validmsg = isElementPresent("OCValOnePack_xpath").getText();
							logs.info("Validation message is displayed=" + Validmsg);
							if (Validmsg.contains("Parameter(s) are modified. Please recalculate customer charges.")) {
								// Recalculate the charges
								// --Go to Edit Job tab
								WebElement EditOrTab = isElementPresent("EOEditOrderTab_id");
								act.moveToElement(EditOrTab).build().perform();
								wait.until(ExpectedConditions.elementToBeClickable(EditOrTab));
								act.moveToElement(EditOrTab).click().perform();
								logs.info("Click on Edit Order Tab");
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
								Thread.sleep(5000);

								// -Recalculate button
								WebElement ReCalc = isElementPresent("EORecal_id");
								act.moveToElement(ReCalc).build().perform();
								wait.until(ExpectedConditions.elementToBeClickable(ReCalc));
								act.moveToElement(ReCalc).click().perform();
								logs.info("Click on Recalculate button");
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
								Thread.sleep(5000);

								// --Click on Save Changes button
								WebElement SaveChanges = isElementPresent("TLSaveChanges_id");
								act.moveToElement(SaveChanges).build().perform();
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
								wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
								js.executeScript("arguments[0].click();", SaveChanges);
								logs.info("Click on Save Changes button");
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
								Thread.sleep(5000);

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
										wait.until(
												ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
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

								tzone = isElementPresent("TT3TimeZone_id").getText();
								rectime = getTimeAsTZone(tzone);
								RecDate = getDateAsTZone(tzone);

								// --Enter Actual Pickup Date
								isElementPresent("H3PPartPullDate_id").clear();
								isElementPresent("H3PPartPullDate_id").sendKeys(RecDate);
								isElementPresent("H3PPartPullDate_id").sendKeys(Keys.TAB);
								logs.info("Enter Actual Part Pull Date");

								/*
								 * QPT =
								 * driver.findElement(By.id("txtQuotedforPickupTime")).getAttribute("value");
								 * logs.info("QPT==" + QPT);
								 * 
								 * t = LocalTime.parse(QPT); tn = t.minusMinutes(1); Time = tn.toString();
								 * logs.info("new time==" + Time);
								 */

								// --Enter Actual Pickup Time
								isElementPresent("H3PPartPullTime_id").clear();
								isElementPresent("H3PPartPullTime_id").sendKeys(rectime);
								isElementPresent("H3PPartPullTime_id").sendKeys(Keys.TAB);
								logs.info("Enter Actual Part Pull time");

								// Click on Confirm Pull

								Thread.sleep(5000);
								// Click on Confirm Pull
								ConfmPull = isElementPresent("H3PConfirmPUll_id");
								act.moveToElement(ConfmPull).build().perform();
								wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
								ConfmPull = isElementPresent("H3PConfirmPUll_id");
								wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
								act.moveToElement(ConfmPull).build().perform();
								ConfmPull.click();
								logs.info("Click on Confirm Pull  button");
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

								/*
								 * js.executeScript("arguments[0].click();", ConfmPull);
								 * logs.info("Click on Confirm Pull  button");
								 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")
								 * ));
								 */
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(
											By.xpath("//*[@id=\"idValidationforMain\"]//ul[@id=\"errorid\"]")));
									Validmsg = isElementPresent("OCValOnePack_xpath").getText();
									logs.info("Validation message is displayed=" + Validmsg);
									if (Validmsg.contains("Actual Datetime cannot be greater than Current Datetime.")) {
										// Recalculate the charges
										tzone = isElementPresent("TT3TimeZone_id").getText();
										rectime = getTimeAsTZone(tzone);

										// --Part Pull Date
										String PullDate = getDateAsTZone(tzone);
										System.out.println("Pull Date==" + PullDate);
										isElementPresent("H3PPartPullDate_id").clear();
										isElementPresent("H3PPartPullDate_id").sendKeys(PullDate);
										isElementPresent("H3PPartPullDate_id").sendKeys(Keys.TAB);
										logs.info("Enter Actual Part Pull Date");

										/*
										 * WebElement PullDate = isElementPresent("TLPartPullDate_id");
										 * PullDate.clear(); Date date = new Date(); DateFormat dateFormat = new
										 * SimpleDateFormat("MM/dd/yyyy");
										 * dateFormat.setTimeZone(TimeZone.getTimeZone(tzone));
										 * logs.info(dateFormat.format(date));
										 * PullDate.sendKeys(dateFormat.format(date)); PullDate.sendKeys(Keys.TAB);
										 * logs.info("Entered Actual Pull Date");
										 */

										/*
										 * QPT =
										 * driver.findElement(By.id("txtQuotedforPickupTime")).getAttribute("value");
										 * logs.info("QPT==" + QPT);
										 * 
										 * t = LocalTime.parse(QPT); tn = t.minusMinutes(1); Time = tn.toString();
										 * logs.info("new time==" + Time);
										 */

										// --Enter Actual Pickup Time
										isElementPresent("H3PPartPullTime_id").clear();
										isElementPresent("H3PPartPullTime_id").sendKeys(rectime);
										isElementPresent("H3PPartPullTime_id").sendKeys(Keys.TAB);
										logs.info("Enter Actual Part Pull time");

										// Click on Confirm Pull

										Thread.sleep(5000);
										// Click on Confirm Pull
										ConfmPull = isElementPresent("H3PConfirmPUll_id");
										act.moveToElement(ConfmPull).build().perform();
										wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
										ConfmPull = isElementPresent("H3PConfirmPUll_id");
										wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
										act.moveToElement(ConfmPull).build().perform();
										ConfmPull.click();
										logs.info("Click on Confirm Pull  button");
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
									} else if (Validmsg.contains(
											"Parameter(s) are modified. Please recalculate customer charges.")) {
										// Recalculate the charges
										// --Go to Edit Job tab
										EditOrTab = isElementPresent("EOEditOrderTab_id");
										act.moveToElement(EditOrTab).build().perform();
										wait.until(ExpectedConditions.elementToBeClickable(EditOrTab));
										act.moveToElement(EditOrTab).click().perform();
										logs.info("Click on Edit Order Tab");
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
										Thread.sleep(5000);

										// -Recalculate button
										ReCalc = isElementPresent("EORecal_id");
										act.moveToElement(ReCalc).build().perform();
										wait.until(ExpectedConditions.elementToBeClickable(ReCalc));
										act.moveToElement(ReCalc).click().perform();
										logs.info("Click on Recalculate button");
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
										Thread.sleep(5000);

										// --Click on Save Changes button
										SaveChanges = isElementPresent("TLSaveChanges_id");
										act.moveToElement(SaveChanges).build().perform();
										wait.until(
												ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
										wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
										js.executeScript("arguments[0].click();", SaveChanges);
										logs.info("Click on Save Changes button");
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
										Thread.sleep(5000);

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
												wait.until(ExpectedConditions
														.invisibilityOfElementLocated(By.id("loaderDiv")));

												// --Click on Save Changes button
												SaveChanges = isElementPresent("TLSaveChanges_id");
												act.moveToElement(SaveChanges).build().perform();
												wait.until(ExpectedConditions
														.visibilityOfElementLocated(By.id("btnSaveChanges")));
												wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
												act.moveToElement(SaveChanges).click().perform();
												logs.info("Click on Save Changes button");
												wait.until(ExpectedConditions
														.invisibilityOfElementLocated(By.id("loaderDiv")));
												Thread.sleep(2000);
											}
										} catch (Exception eRequiredMsg) {
										}

										// --Go to job Status Tab
										JobOverTab = isElementPresent("TLJobStatusTab_id");
										act.moveToElement(JobOverTab).build().perform();
										wait.until(ExpectedConditions.elementToBeClickable(JobOverTab));
										act.moveToElement(JobOverTab).click().perform();
										logs.info("Click on Job Overview Tab");
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

										tzone = isElementPresent("TT3TimeZone_id").getText();
										rectime = getTimeAsTZone(tzone);
										RecDate = getDateAsTZone(tzone);

										// --Enter Actual Pickup Date
										isElementPresent("H3PPartPullDate_id").clear();
										isElementPresent("H3PPartPullDate_id").sendKeys(RecDate);
										isElementPresent("H3PPartPullDate_id").sendKeys(Keys.TAB);
										logs.info("Enter Actual Part Pull Date");

										/*
										 * QPT =
										 * driver.findElement(By.id("txtQuotedforPickupTime")).getAttribute("value");
										 * logs.info("QPT==" + QPT);
										 * 
										 * t = LocalTime.parse(QPT); tn = t.minusMinutes(1); Time = tn.toString();
										 * logs.info("new time==" + Time);
										 */

										// --Enter Actual Pickup Time
										isElementPresent("H3PPartPullTime_id").clear();
										isElementPresent("H3PPartPullTime_id").sendKeys(rectime);
										isElementPresent("H3PPartPullTime_id").sendKeys(Keys.TAB);
										logs.info("Enter Actual Part Pull time");

										// Click on Confirm Pull

										Thread.sleep(5000);
										// Click on Confirm Pull
										ConfmPull = isElementPresent("H3PConfirmPUll_id");
										act.moveToElement(ConfmPull).build().perform();
										wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
										ConfmPull = isElementPresent("H3PConfirmPUll_id");
										wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
										act.moveToElement(ConfmPull).build().perform();
										ConfmPull.click();
										logs.info("Click on Confirm Pull  button");
										wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

										/*
										 * js.executeScript("arguments[0].click();", ConfmPull);
										 * logs.info("Click on Confirm Pull  button");
										 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")
										 * ));
										 */
										try {
											wait.until(ExpectedConditions.visibilityOfElementLocated(
													By.xpath("//*[@id=\"idValidationforMain\"]//ul[@id=\"errorid\"]")));
											Validmsg = isElementPresent("OCValOnePack_xpath").getText();
											logs.info("Validation message is displayed=" + Validmsg);
											if (Validmsg.contains(
													"Actual Datetime cannot be greater than Current Datetime.")) {
												// Recalculate the charges
												tzone = isElementPresent("TT3TimeZone_id").getText();
												rectime = getTimeAsTZone(tzone);

												// --Part Pull Date
												String PullDate = getDateAsTZone(tzone);
												System.out.println("Pull Date==" + PullDate);
												isElementPresent("H3PPartPullDate_id").clear();
												isElementPresent("H3PPartPullDate_id").sendKeys(PullDate);
												isElementPresent("H3PPartPullDate_id").sendKeys(Keys.TAB);
												logs.info("Enter Actual Part Pull Date");

												/*
												 * WebElement PullDate = isElementPresent("TLPartPullDate_id");
												 * PullDate.clear(); Date date = new Date(); DateFormat dateFormat = new
												 * SimpleDateFormat("MM/dd/yyyy");
												 * dateFormat.setTimeZone(TimeZone.getTimeZone(tzone));
												 * logs.info(dateFormat.format(date));
												 * PullDate.sendKeys(dateFormat.format(date));
												 * PullDate.sendKeys(Keys.TAB); logs.info("Entered Actual Pull Date");
												 */
												/*
												 * QPT = driver.findElement(By.id("txtQuotedforPickupTime"))
												 * .getAttribute("value"); logs.info("QPT==" + QPT);
												 * 
												 * t = LocalTime.parse(QPT); tn = t.minusMinutes(1); Time =
												 * tn.toString(); logs.info("new time==" + Time);
												 */

												// --Enter Actual Pickup Time
												isElementPresent("H3PPartPullTime_id").clear();
												isElementPresent("H3PPartPullTime_id").sendKeys(rectime);
												isElementPresent("H3PPartPullTime_id").sendKeys(Keys.TAB);
												logs.info("Enter Actual Part Pull time");

												// Click on Confirm Pull

												Thread.sleep(5000);
												// Click on Confirm Pull
												ConfmPull = isElementPresent("H3PConfirmPUll_id");
												act.moveToElement(ConfmPull).build().perform();
												wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
												ConfmPull = isElementPresent("H3PConfirmPUll_id");
												wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
												act.moveToElement(ConfmPull).build().perform();
												ConfmPull.click();
												logs.info("Click on Confirm Pull  button");
												wait.until(ExpectedConditions
														.invisibilityOfElementLocated(By.id("loaderDiv")));
											} else if (Validmsg.contains(
													"Parameter(s) are modified. Please recalculate customer charges.")) {
												// Recalculate the charges
												// --Go to Edit Job tab
												EditOrTab = isElementPresent("EOEditOrderTab_id");
												act.moveToElement(EditOrTab).build().perform();
												wait.until(ExpectedConditions.elementToBeClickable(EditOrTab));
												act.moveToElement(EditOrTab).click().perform();
												logs.info("Click on Edit Order Tab");
												wait.until(ExpectedConditions
														.invisibilityOfElementLocated(By.id("loaderDiv")));
												Thread.sleep(5000);

												// -Recalculate button
												ReCalc = isElementPresent("EORecal_id");
												act.moveToElement(ReCalc).build().perform();
												wait.until(ExpectedConditions.elementToBeClickable(ReCalc));
												act.moveToElement(ReCalc).click().perform();
												logs.info("Click on Recalculate button");
												wait.until(ExpectedConditions
														.invisibilityOfElementLocated(By.id("loaderDiv")));
												Thread.sleep(5000);

												// --Click on Save Changes button
												SaveChanges = isElementPresent("TLSaveChanges_id");
												act.moveToElement(SaveChanges).build().perform();
												wait.until(ExpectedConditions
														.visibilityOfElementLocated(By.id("btnSaveChanges")));
												wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
												js.executeScript("arguments[0].click();", SaveChanges);
												logs.info("Click on Save Changes button");
												wait.until(ExpectedConditions
														.invisibilityOfElementLocated(By.id("loaderDiv")));
												Thread.sleep(5000);

												// --Go to job Status Tab
												JobOverTab = isElementPresent("TLJobStatusTab_id");
												act.moveToElement(JobOverTab).build().perform();
												wait.until(ExpectedConditions.elementToBeClickable(JobOverTab));
												act.moveToElement(JobOverTab).click().perform();
												logs.info("Click on Job Overview Tab");
												wait.until(ExpectedConditions
														.invisibilityOfElementLocated(By.id("loaderDiv")));

												// --Get the timeZone
												tzone = isElementPresent("TT3TimeZone_id").getText();
												rectime = getTimeAsTZone(tzone);
												RecDate = getDateAsTZone(tzone);

												// --Enter Actual Pickup Date
												isElementPresent("H3PPartPullDate_id").clear();
												isElementPresent("H3PPartPullDate_id").sendKeys(RecDate);
												isElementPresent("H3PPartPullDate_id").sendKeys(Keys.TAB);
												logs.info("Enter Actual Part Pull Date");

												/*
												 * QPT = driver.findElement(By.id("txtQuotedforPickupTime"))
												 * .getAttribute("value"); logs.info("QPT==" + QPT);
												 * 
												 * t = LocalTime.parse(QPT); tn = t.minusMinutes(1); Time =
												 * tn.toString(); logs.info("new time==" + Time);
												 */

												// --Enter Actual Pickup Time
												isElementPresent("H3PPartPullTime_id").clear();
												isElementPresent("H3PPartPullTime_id").sendKeys(rectime);
												isElementPresent("H3PPartPullTime_id").sendKeys(Keys.TAB);
												logs.info("Enter Actual Part Pull time");

												// Click on Confirm Pull

												Thread.sleep(5000);
												// Click on Confirm Pull
												ConfmPull = isElementPresent("H3PConfirmPUll_id");
												act.moveToElement(ConfmPull).build().perform();
												wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
												ConfmPull = isElementPresent("H3PConfirmPUll_id");
												wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
												act.moveToElement(ConfmPull).build().perform();
												ConfmPull.click();
												logs.info("Click on Confirm Pull  button");
												wait.until(ExpectedConditions
														.invisibilityOfElementLocated(By.id("loaderDiv")));

											}

										} catch (Exception ee) {
											logs.info("Validation message is not displayed for Recalculate");

										}
									}

								} catch (Exception ee) {
									logs.info("Validation message is not displayed for Recalculate");

								}
							} else if (Validmsg.contains("Actual Datetime cannot be greater than Current Datetime.")) {
								// Recalculate the charges
								tzone = isElementPresent("TT3TimeZone_id").getText();
								rectime = getTimeAsTZone(tzone);

								// --Part Pull Date
								String PullDate = getDateAsTZone(tzone);
								System.out.println("Pull Date==" + PullDate);
								isElementPresent("H3PPartPullDate_id").clear();
								isElementPresent("H3PPartPullDate_id").sendKeys(PullDate);
								isElementPresent("H3PPartPullDate_id").sendKeys(Keys.TAB);
								logs.info("Enter Actual Part Pull Date");

								/*
								 * WebElement PullDate = isElementPresent("TLPartPullDate_id");
								 * PullDate.clear(); Date date = new Date(); DateFormat dateFormat = new
								 * SimpleDateFormat("MM/dd/yyyy");
								 * dateFormat.setTimeZone(TimeZone.getTimeZone(tzone));
								 * logs.info(dateFormat.format(date));
								 * PullDate.sendKeys(dateFormat.format(date)); PullDate.sendKeys(Keys.TAB);
								 * logs.info("Entered Actual Pull Date");
								 */

								/*
								 * QPT =
								 * driver.findElement(By.id("txtQuotedforPickupTime")).getAttribute("value");
								 * logs.info("QPT==" + QPT);
								 * 
								 * t = LocalTime.parse(QPT); tn = t.minusMinutes(1); Time = tn.toString();
								 * logs.info("new time==" + Time);
								 */

								// --Enter Actual Pickup Time
								isElementPresent("H3PPartPullTime_id").clear();
								isElementPresent("H3PPartPullTime_id").sendKeys(rectime);
								isElementPresent("H3PPartPullTime_id").sendKeys(Keys.TAB);
								logs.info("Enter Actual Part Pull time");

								// Click on Confirm Pull

								Thread.sleep(5000);
								// Click on Confirm Pull
								ConfmPull = isElementPresent("H3PConfirmPUll_id");
								act.moveToElement(ConfmPull).build().perform();
								wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
								ConfmPull = isElementPresent("H3PConfirmPUll_id");
								wait.until(ExpectedConditions.elementToBeClickable(ConfmPull));
								act.moveToElement(ConfmPull).build().perform();
								ConfmPull.click();
								logs.info("Click on Confirm Pull  button");
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							}

						} catch (Exception ee) {
							logs.info("Validation message is not displayed for Recalculate");

						}
					}

				} catch (Exception error) {
					System.out.println(error);
					logs.info("Validation message is not displayed for PartTime");

				}
			}

		} catch (

		Exception e) {
			logs.error(e);
			getScreenshot(driver, "ConfirmPull" + svc);
			System.out.println("Confirm Pull Not Exist in Flow!!");
			logs.info("Confirm Pull Not Exist in Flow!!");
		}

	}
}
