package connect_Ecourier;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import connect_BasePackage.BaseInit;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EcourierUtility extends OrderCreationE {

	public static void main(String[] args) throws Exception {

		EcourierUtility EU = new EcourierUtility();
		EU.EcourierSetStatuReject();

	}

	public void EcourierSetStatuReject() throws Exception {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--headless", "--window-size=1920,1200");
		options.addArguments("--incognito");

		driver = new ChromeDriver(options);
		driver.get("http://10.20.104.71:9077/TestApplicationUtility/VendorStatusWS");

		try {

			PickupEcourier PU = new PickupEcourier();
			OrderCreationE OC = new OrderCreationE();
			BaseInit Base = new BaseInit();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
			Actions act = new Actions(driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;

			logs.info("Ecourier Utility is opened");
			Thread.sleep(5000);

			// Method
			WebElement Method = driver.findElement(By.xpath("//*[@id=\"MainContent_DropDownList1\"]"));
			wait.until(ExpectedConditions.visibilityOf(Method));
			wait.until(ExpectedConditions.elementToBeClickable(Method));
			jse.executeScript("arguments[0].click();", Method);
			Select sel1 = new Select(Method);
			sel1.selectByValue("SetStatusReject");

			logs.info("Selected Set Status Reject :");
			Thread.sleep(4000);
			// Vendor ID
			WebElement VendorID = driver.findElement(By.xpath("//*[@id=\"MainContent_cmbvendor\"]"));
			wait.until(ExpectedConditions.visibilityOf(VendorID));
			wait.until(ExpectedConditions.elementToBeClickable(VendorID));
			jse.executeScript("arguments[0].click();", VendorID);
			logs.info("Clicked on Vendor ID drop down");
			Select sel = new Select(VendorID);
			sel.selectByValue("ECourier");
			logs.info("Selected Vendor ID as Ecourier");
			Thread.sleep(4000);
			// Order ID
			WebElement OrderID = driver.findElement(By.xpath("//*[@id=\"MainContent_txtJobId\"]"));
			wait.until(ExpectedConditions.visibilityOf(OrderID));
			OrderID.sendKeys(ECourierOrderNum);
			logs.info("Entered Agent System OrderId :"+ECourierOrderNum);
			Thread.sleep(4000);
			// Date & Time
			WebElement DateNTime = driver.findElement(By.xpath("//*[@id=\"MainContent_txtDttm\"]"));
			wait.until(ExpectedConditions.visibilityOf(DateNTime));
			try {

				DateNTime.sendKeys(PU.AreadyDate + " ");
				logs.info("Entered date");
//			DateNTime.sendKeys(PU.UtilityTime);
//			logs.info("Entered date");

				if (PU.ActualTimeZone.equalsIgnoreCase("EDT")) {

					DateNTime.sendKeys(Base.getTimeAsTZone("EDT"));
				}

				else if (PU.ActualTimeZone.equalsIgnoreCase("CST") || PU.ActualTimeZone.equalsIgnoreCase("CDT")) {

					DateNTime.sendKeys(Base.getTimeAsTZone("CDT"));
					logs.info("Entered Time");
				}

				else if (PU.ActualTimeZone.equalsIgnoreCase("PDT")) {
					DateNTime.sendKeys(Base.getTimeAsTZone("PDT"));
				}

				else if (PU.ActualTimeZone.equalsIgnoreCase("MDT")) {
					DateNTime.sendKeys(Base.getTimeAsTZone("MDT"));
				}

			} catch (Exception e) {

				System.out.println(e);
			}
			Thread.sleep(4000);
			// Pick Up ID
			WebElement PickUPID = driver.findElement(By.xpath("//*[@id=\"MainContent_txtpickupId\"]"));
			wait.until(ExpectedConditions.visibilityOf(DateNTime));
			PickUPID.sendKeys(PICKUPID);
			logs.info("Entered Pick Up id with Prefix");
			Thread.sleep(4000);
			// Send Request
			WebElement SendRequest = driver.findElement(By.xpath("//*[@id=\"MainContent_btnSend\"]"));
			wait.until(ExpectedConditions.visibilityOf(SendRequest));
			wait.until(ExpectedConditions.elementToBeClickable(SendRequest));
			act.moveToElement(SendRequest).build().perform();
			act.click(SendRequest).build().perform();
			logs.info("Clicked on Send Request to Reject");
			Thread.sleep(15000);

			// Click again
//		WebElement SendRequest2 = driver.findElement(By.xpath("//*[@id=\"MainContent_btnSend\"]"));
//		wait.until(ExpectedConditions.visibilityOf(SendRequest2));
//		wait.until(ExpectedConditions.elementToBeClickable(SendRequest2));
//	    act.moveToElement(SendRequest2).build().perform();
//		jse.executeScript("arguments[0].click();", SendRequest2);
//		logs.info("Clicked again on Send Request to Reject");
//		Thread.sleep(6000);

			// Close Browser
			driver.quit();
			logs.info("Closed Uility Screen");

		} catch (Exception e) {
			// TODO: handle exception
			logs.info("Closed Uility Screen " + e);
			driver.quit();

		}
	}

}
