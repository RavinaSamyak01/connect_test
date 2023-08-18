package AutoDispatch_28677;

import java.util.Iterator;
import java.util.Set;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;
import connect_BasePackage.Email;

public class netship_autodispatch extends BaseInit {
	String Env = storage.getProperty("Env");
	static method_28677 mth = new method_28677();

	@Test(priority = 1)
	public void netship_auto_dispatch_order() throws Exception {
		try {

			logs.info("========== (netship from tasklog) Testcase  : Start ==========");
			msg.append("========== (netship from tasklog) Testcase  : Start ==========" + "\n");

			JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));// wait time +
			Actions act = new Actions(driver);
			
				String netship_stg_link = storage.getProperty("netship_url_STG");

				String new_tab_link = "window.open('" + netship_stg_link + "','_blank');";

				((JavascriptExecutor) driver).executeScript(new_tab_link);

				// Get handles of the windows , move focus on Cheetah Portal

				String mainWindowHandle = driver.getWindowHandle();
				Set<String> allWindowHandles = driver.getWindowHandles();
				Iterator<String> iterator = allWindowHandles.iterator();
				try {

					// Here we will check if child window has other child windows and will fetch the
					// heading of the child window
					while (iterator.hasNext()) {
						String ChildWindow = iterator.next();
						if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {

							// -- focucs is now on Cheetah portal
							driver.switchTo().window(ChildWindow);

//			WebElement cheetah_submit = isElementPresent("submit_cheetah_id");
//			wait.until(ExpectedConditions.elementToBeClickable(cheetah_submit));
							logs.info("Focus is on Netship portal");

							// -- Enter credential for login in etship
							
							String baseUrl = null;
							if (Env.equalsIgnoreCase("TEST")) {
								baseUrl = storage.getProperty("netship_url_TEST");
								driver.get(baseUrl);
								Thread.sleep(2000);
								try {
									wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("login")));
									String UserName = storage.getProperty("netship_user_TEST");
									highLight(isElementPresent("UserName_id"), driver);
									isElementPresent("UserName_id").clear();
									isElementPresent("UserName_id").sendKeys(UserName);
									logs.info("Entered UserName");
									String Password = storage.getProperty("netship_pwd_TEST");
									highLight(isElementPresent("Password_id"), driver);
									isElementPresent("Password_id").clear();
									isElementPresent("Password_id").sendKeys(Password);
									logs.info("Entered Password");

								} catch (Exception e) {
									msg.append("URL is not working==FAIL");
									getScreenshot(driver, "LoginIssue");
									driver.quit();
									Env = storage.getProperty("Env");
									String File = ".\\Report\\RTE_Screenshot\\LoginIssue.png";
									Env = storage.getProperty("Env");
									String subject = "Selenium Automation Script:" + Env + " netship_auto_dispatch";

									String EmailID = storage.getProperty("MainEmailAddress");
									System.out.println("MainEmailAddress " + EmailID);
									try {
//										/kunjan.modi@samyak.com, pgandhi@samyak.com,parth.doshi@samyak.com

										Email.sendMail(EmailID, subject, msg.toString(), File);
										
									} catch (Exception ex) {
										logs.error(ex);
									}

								}
							} else if (Env.equalsIgnoreCase("STG")) {
								baseUrl = storage.getProperty("netship_url_STG");
								driver.get(baseUrl);
								Thread.sleep(2000);
								try {
									wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("login")));
									String UserName = storage.getProperty("netship_user_STG");
									highLight(isElementPresent("UserName_id"), driver);
									isElementPresent("UserName_id").sendKeys(UserName);
									logs.info("Entered UserName");
									String Password = storage.getProperty("netship_pwd_STG");
									highLight(isElementPresent("Password_id"), driver);
									isElementPresent("Password_id").sendKeys(Password);
									logs.info("Entered Password");

								} catch (Exception e) {
									msg.append("URL is not working==FAIL");
									getScreenshot(driver, "LoginIssue");
									driver.quit();
									Env = storage.getProperty("Env");
									String File = ".\\Report\\RTE_Screenshot\\LoginIssue.png";
									Env = storage.getProperty("Env");
									String subject = "Selenium Automation Script:" + Env + " netship_auto_dispatch";

									String EmailID = storage.getProperty("MainEmailAddress");
									System.out.println("MainEmailAddress " + EmailID);
									try {

										Email.sendMail(EmailID, subject, msg.toString(), File);
										
									} catch (Exception ex) {
										logs.error(ex);
									}

								}
							}
							
							

							// -- click on signin button

							WebElement signin = isElementPresent("ns_login_id");
							act.moveToElement(signin).click(signin).build().perform();
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							logs.info("Clicked on signin button");
							Thread.sleep(2000);
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							// Click on ship package icon
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ShipPackageTab")));
							Thread.sleep(2000);
							WebElement ship_pkg = isElementPresent("ship_pkg_id");
							jse.executeScript("arguments[0].scrollIntoView(true);", ship_pkg);
							jse.executeScript("arguments[0].click();", ship_pkg);

							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// -- select billing account : 950836
							Select billing_acc = new Select(isElementPresent("billing_id"));
							billing_acc.selectByValue("950836");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// pickup delivery information
							WebElement pudel = isElementPresent("pudelInfo_xpath");
							jse.executeScript("arguments[0].click();", pudel);
							System.out.println("click pickup and delivery information");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							logs.info("Clicked on pickup and delivery information button");
//-- using ex code
							// select Pickup address tab
							WebElement address_tab = isElementPresent("pickup_add_id");
							jse.executeScript("arguments[0].click();", address_tab);
							System.out.println("click pickup address");
							logs.info("Clicked on Pickup Address Tab");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// Enter PickUp company

							String PickUpCom = method_28677.getData("address", 3, 4);
							isElementPresent("pickUpCompany_xpath").sendKeys(PickUpCom);
							System.out.println("Entered PU company name");
							logs.info("Entered pickup company name");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// Add line
							String AddLine = method_28677.getData("address", 3, 5);
							isElementPresent("pickUpAddLine_xpath").sendKeys(AddLine);
							System.out.println("Address");
							logs.info("Enter address");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							Thread.sleep(2000);

							// Pick Up Zip
							String PickUpZip = method_28677.getData("address", 3, 3);
							isElementPresent("PickUpZipCode_xpath").clear();

							isElementPresent("PickUpZipCode_xpath").sendKeys(PickUpZip);
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							isElementPresent("PickUpZipCode_xpath").sendKeys(Keys.TAB);
							Thread.sleep(2000);
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							System.out.println("Enter Pick Up zipcode");
							logs.info("Enter pick Up zipcode");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// Contact Person
							String pu_ContactPerson = method_28677.getData("address", 3, 7);
							isElementPresent("puContact_xpath").sendKeys(pu_ContactPerson);
							System.out.println("enter contact person");
							logs.info("Enter contact person");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// Phone
							String Phone1 = method_28677.getData("address", 3, 8);
							isElementPresent("phone_xpath").sendKeys(Phone1);
							System.out.println("Entered PickUp phone");
							logs.info("Enter PickUp phone");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							jse.executeScript("scroll(0,-1000)");

							// delivery address
							WebElement deladd = isElementPresent("delAddress_xpath");
							jse.executeScript("arguments[0].click();", deladd);
							System.out.println("click delivery address");
							logs.info("Click delivery address ");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// Enter Delivery company

							String DelCom = method_28677.getData("address", 3, 12);
							isElementPresent("DelCompany_id").sendKeys(DelCom);
							System.out.println("Entered PU company name");
							logs.info("Entered pickup company name");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// Add line
							String del_AddLine = method_28677.getData("address", 3, 13);
							isElementPresent("DelAddLine_id").sendKeys(del_AddLine);
							System.out.println("Address");
							logs.info("Enter address");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							Thread.sleep(2000);
							// Delivery Zip
							String Delzip = method_28677.getData("address", 3, 11);
							isElementPresent("DelZipCode_id").clear();
							isElementPresent("DelZipCode_id").sendKeys(Delzip);
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							isElementPresent("DelZipCode_id").sendKeys(Keys.TAB);
							Thread.sleep(2000);
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							System.out.println("Enter Pick Up zipcode");
							logs.info("Enter pick Up zipcode");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// Contact Person
							String del_ContactPerson = method_28677.getData("address", 3,15);
							isElementPresent("del_contact_id").sendKeys(del_ContactPerson);
							System.out.println("enter contact person");
							logs.info("Enter contact person");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							// Phone
							String del_Phone1 = method_28677.getData("address", 3, 16);
							isElementPresent("DelPhone_id").sendKeys(del_Phone1);
							System.out.println("Entered PickUp phone");
							logs.info("Enter PickUp phone");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							jse.executeScript("scroll(0,-1000)");

							// select service
							WebElement service = isElementPresent("service_id");

							Select service1 = new Select(service);
							service1.selectByValue("string:LOC");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

							getScreenshot(driver, "netship-data_auto-dispatch");

							// -- Place Order
							WebElement PlaceOrderBttn = isElementPresent("PlaceOrder_xpath");
							wait.until(ExpectedConditions.elementToBeClickable(PlaceOrderBttn));
							jse.executeScript("arguments[0].click();", PlaceOrderBttn);
//					    act.moveToElement(PlaceOrderBttn).click().build().perform();
							System.out.println("click on place order");
							logs.info("click on place order");
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
							Thread.sleep(2000);

							// -- fetch Picup id

							WebElement msg = isElementPresent("cnfm_popup_id");
							String Pickup = msg.getText();
							logs.info("Msg for pickup creation is : " + Pickup);

							String Pickup_id = Pickup.replaceAll("[^0-9]", "");
							logs.info("Pickup ID is :" + Pickup_id);
							method_28677.setData("Test_Case", 5, 4, Pickup_id);
							getScreenshot(driver, "netship_auto-dispatch");

							// move to connect
							driver.close();
							Thread.sleep(1000);
							driver.switchTo().window(mainWindowHandle);
							Thread.sleep(2000);

							// -- open job from tasklog

							mth.open_pickup_frm_tasklog(5);

							// -- validate auto dispatch

							mth.validate_autodispatch(5);

							method_28677.setData("Test_Case", 5, 3, "PASS");
						}
					}
				}	
				catch (Exception e) {
					// TODO: handle exception
					driver.switchTo().window(mainWindowHandle);
					Thread.sleep(2000);
					logs.info("Created job is Auto Dispatch == FAIL" + e);
					msg.append("Created job is Auto Dispatch == FAIL" + "\n");
					method_28677.setData("Test_Case", 5, 3, "FAIL");
				}

			

		} catch (Exception e) {
			// TODO: handle exception

			logs.info("Created job is Auto Dispatch == FAIL" + e);
			msg.append("Created job is Auto Dispatch == FAIL" + "\n");
		}
		logs.info("========== (netship from tasklog) Testcase  : End ==========");
		msg.append("========== (netship from tasklog) Testcase  : End ==========" + "\n" + "\n");
	}

}
