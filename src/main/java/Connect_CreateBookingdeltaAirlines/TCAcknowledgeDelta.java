package Connect_CreateBookingdeltaAirlines;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.io.FileUtils;
import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;
import connect_Ecourier.OrderCreationE;

public class TCAcknowledgeDelta extends BaseInit {

	@Test
	public void tcAcknowledge() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));// wait time
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		Actions act = new Actions(driver);
		Random random = new Random();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		// --Get the ServiceID
		String svc = isElementPresent("TLServID_id").getText();
		System.out.println(svc);
		logs.info("ServiceID=" + svc);
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@id=\"lblStages\"][contains(text(),'Acknowledge')]")));

			// --Get StageName
			OrderCreationE OC = new OrderCreationE();
			OC.getStageName();

			// --Click on TC Ack button
		
			if (svc.equals("SD") || svc.equals("PA") || svc.equals("AIR") || svc.equals("FRA")) {
			
					//Add air bill 
				WebElement Addairbill = isElementPresent("AirBill_xpath");
				//jse.executeScript("arguments[0].scrollIntoView();",Addairbill );
				jse.executeScript("arguments[0].scrollIntoView();", Addairbill);
				Thread.sleep(1500);
				wait.until(ExpectedConditions.visibilityOf(Addairbill));
				wait.until(ExpectedConditions.elementToBeClickable(Addairbill));
				jse.executeScript("arguments[0].click();", Addairbill);
				logs.info("Clicked on Add Air Bill button");
				Thread.sleep(2000);
				//Qty 
				WebElement Qty = isElementPresent("AirBillQty_xpath");
				wait.until(ExpectedConditions.visibilityOf(Qty));
//				Qty.click();
				Qty.sendKeys("1");
				logs.info("Qty added");
				
				//Weight 
				WebElement Weight = isElementPresent("AirBillWgt_xpath");
				wait.until(ExpectedConditions.visibilityOf(Weight));
				Weight.sendKeys("1");
				logs.info("Weight added");
				
				//Description 
				WebElement Description = isElementPresent("AirBillDescptn_xpath");
				wait.until(ExpectedConditions.visibilityOf(Description));
				Description.sendKeys("None");
				logs.info("Description added");
				
				//Account 
				WebElement Account = isElementPresent("AirBillAcctn_xpath");
				wait.until(ExpectedConditions.visibilityOf(Account));
				wait.until(ExpectedConditions.elementToBeClickable(Account));
				jse.executeScript("arguments[0].click();", Account);
				logs.info("Clicked on Account");
				
		        List<WebElement> AllAccounts = driver.findElements(By.xpath("(//*[@id=\"cmbCarrier\"])[2]//option"));
		        int AccountSize =AllAccounts.size();
//		        int Index1=random.nextInt(AccountSize);
		        
		        int Index1=ThreadLocalRandom.current().nextInt(1, 4);
		        AllAccounts.get(Index1).click();
		        
//				Select sel=new Select(Account);
//			    sel.selectByIndex(Index1);
		        
			    String SelectedAccount =Account.getAttribute("value");
				logs.info("Account Selected is:-"+SelectedAccount);
				logs.info("Account Selected");
				Thread.sleep(2500);
				
				//Service 
				WebElement Service = isElementPresent("AirBillService_xpath");
				wait.until(ExpectedConditions.visibilityOf(Service));
				wait.until(ExpectedConditions.elementToBeClickable(Service));
				jse.executeScript("arguments[0].click();", Service);
				logs.info("Clicked on Service");
				
				 List<WebElement>  AllService= driver.findElements(By.xpath("(//*[@id=\"cmbCarrier\"])[3]//option"));
				 int ServicesSize=AllService.size();
//				 int index2=random.nextInt(ServicesSize);
				 
				   int Index2=ThreadLocalRandom.current().nextInt(1, 4);
				  AllService.get(Index2).click();
				 
//				 Select sel2=new Select(Service);
//				 sel2.selectByIndex(index2);
				 String SelectedService =Service.getAttribute("value");
				 logs.info("Service Selected is:-"+SelectedService);
				 logs.info("Service Selected");
					Thread.sleep(2500);
					
				//Create Booking 
				WebElement CreateBooking = isElementPresent("AirBillCreateBookng_id");
				wait.until(ExpectedConditions.visibilityOf(CreateBooking));
				wait.until(ExpectedConditions.elementToBeClickable(CreateBooking));
				act.moveToElement(CreateBooking).build().perform();
				jse.executeScript("arguments[0].click();", CreateBooking);
				logs.info("Clicked on Create Booking");
				
				try {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				}
				catch(Exception extrawait) {
					logs.info("Wait more");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));	
				}
				
				
				
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='AirbillControlId']")));
				
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@ng-bind='SuccessMsg']")));
					//Booking Success full message 
					bookingSuccessful() ;
					
				}
				
				catch(Exception timeout) {
					
					WebElement Eroor= isElementPresent("AirBillTimeOutError_xpath");
					wait.until(ExpectedConditions.visibilityOf(Eroor));
					String ErrorMsg=Eroor.getText();
			      	logs.info("Error Message is:-"+ErrorMsg);
			      	
			       	try {
					if(ErrorMsg.equalsIgnoreCase("Endpoint request timed out")) {
						
						//Create Booking 
						ClickOnCreateBooking();
					    getScreenshotDelta(driver,"AirBill");
						bookingSuccessful() ;
					}
					else if(ErrorMsg.equalsIgnoreCase("Unexpected character encountered while parsing value: <. Path '', line 0, position 0.")) {
						
						//Create Booking 
						ClickOnCreateBooking();
					    getScreenshotDelta(driver,"AirBill");
						bookingSuccessful() ;
					}
					else if(ErrorMsg.equalsIgnoreCase("Q626-DIMS MANDATORY FOR PROD")) {
						
						//Create Booking 
						ClickOnCreateBooking();
					    getScreenshotDelta(driver,"AirBill");
						bookingSuccessful() ;
					}
					
					else if(ErrorMsg.equalsIgnoreCase("Internal server error")) {
					
						//Create Booking 
						ClickOnCreateBooking();
					    getScreenshotDelta(driver,"AirBill");
						bookingSuccessful() ;
					}
			
					else if(ErrorMsg.equalsIgnoreCase("Incomplete booking information")) {
						
						//Create Booking 
						ClickOnCreateBooking();
					    getScreenshotDelta(driver,"AirBill");
						bookingSuccessful() ;
						
					}
	
              	else if(ErrorMsg.equalsIgnoreCase("No routing available due to a station or flight restriction or insufficient capacity for the date requested")) {
						
						//Create Booking 
						ClickOnCreateBooking();
					    getScreenshotDelta(driver,"AirBill");
						bookingSuccessful() ;
						
					}   }
			       	
			       	catch (Exception Bookingfailed) {
			       		
			          	logs.info("Booking Failed");
			           	msg.append("Booking Failed:-Error From Delta Air Line side"+"\n"); 
			       		
			       	}
				} 
				
			}

				
						
						
				
			      	
			      
			         }
				

			
		
		
		catch (Exception e) {
			logs.error(e);
			getScreenshot(driver, "TCACKNOWLEDGE" + svc);
			System.out.println("TC ACKNOWLEDGE Not Exist in Flow!!");
			logs.info("TC ACKNOWLEDGE Not Exist in Flow!!");

		}


	}
	
	
	
	
	public void ClickOnCreateBooking() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));// wait time
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		Actions act = new Actions(driver);
		//Create Booking 
		WebElement CreateBooking1 = isElementPresent("AirBillCreateBookng_id");
		wait.until(ExpectedConditions.visibilityOf(CreateBooking1));
		wait.until(ExpectedConditions.elementToBeClickable(CreateBooking1));
		act.moveToElement(CreateBooking1).build().perform();
		jse.executeScript("arguments[0].click();", CreateBooking1);
		logs.info("Clicked again on Create Booking");
		
		try {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		}
		catch(Exception waitmore) {
			logs.info("Wait More");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		}
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='AirbillControlId']")));
	}

	public void bookingSuccessful() throws IOException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));// wait time
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@ng-bind='SuccessMsg']")));
		//Booking Success full message 
		WebElement BookingSucessful= isElementPresent("BookingSucess_xpath");
		wait.until(ExpectedConditions.visibilityOf(BookingSucessful));
		           String BookingSucessfull = BookingSucessful.getText();
		
		       	logs.info("Booking SuccessFul:-"+BookingSucessfull);
		       	
		       	msg.append("Booking SuccessFul:="+BookingSucessfull +"\n"); 
		       	
		       	//Take screen shot Successful 
		        getScreenshotDelta(driver,"AirBill");
		    
		
		
	}
	
	
	public static String getScreenshotDelta(WebDriver driver, String screenshotName) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		//Time Stamp 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String timestamp = dateFormat.format(new Date());
		
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/Report/DeltaAirLineSS/" + screenshotName+ timestamp+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
		
	
}
