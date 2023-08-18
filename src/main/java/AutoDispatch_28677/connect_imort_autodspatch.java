package AutoDispatch_28677;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class connect_imort_autodspatch extends BaseInit {

	static method_28677 mth = new method_28677();

//@Test
	public void import_order_create() throws Exception {
		
		try {

			msg.append("==== Auto dispatch from import order : Start ====" + "\n");
			logs.info("==== Auto dispatch from import order : Start ====");
		try {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
			Actions act = new Actions(driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click


			// --Go to Tools tab
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a_tools")));
			WebElement Tools = isElementPresent("Tools_id");
			act.moveToElement(Tools).click().perform();
			logs.info("Clicked on Tools");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"OpenCloseClass dropdown open\"]//ul")));

			// --Click on Import order
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a_ImportRoute")));
			isElementPresent("import_order_id").click();
			logs.info("Clicked on Import Order");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			getScreenshot(driver, "Importorder");
			Thread.sleep(500);

			// --Click on browse button

			WebElement browse = isElementPresent("IRBrowse_id");
			// wait.until(ExpectedConditions.elementToBeClickable(browse));
			browse.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			logs.info("Clicked on Browse button");
			String FileName = "28677_auto_dispatch_ import_loc";

			logs.info("FileName== " + FileName);
//			String Fpath = ".\\src\\main\\resources\\connect_TestData\\" + FileName + ".xlsx";
//			System.getProperty("user.dir") + "\\src\\main\\resources\\Downloads";

			String Fpath = System.getProperty("user.dir") + "\\src\\main\\resources\\connect_TestData\\" + FileName
					+ ".xlsx";

			System.out.println("file location is :" + Fpath);

			// -- select file

			WebElement InFile = isElementPresent("IRSelectFile_id");
			InFile.sendKeys(Fpath);

			logs.info("Send file to input file");

			// --Click on Upload btn
			isElementPresent("IRUpload_id").click();

			logs.info("Click on Upload button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			Thread.sleep(2000);

			// --CLick on Continue button
			WebElement Continue = isElementPresent("IRContinue_id");
			wait.until(ExpectedConditions.elementToBeClickable(Continue));
			act.moveToElement(Continue).build().perform();
			act.moveToElement(Continue).click().perform();

			logs.info("Click on Continue button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			String sucmsg = isElementPresent("SLSSuccess_xpath").getText();
			logs.info("Success message is : " + sucmsg);

			if (sucmsg.contains("Job creation process is in Progress")) {
				logs.info("Auto Dispatch file import successfully");
				msg.append("Auto Dispatch file import == PASS" + "\n");
				getScreenshot(driver, "fileupload_auto-dispatch");
				method_28677.setData("Test_Case", 3, 3, "PASS");
			} else {
				logs.info("error in file upload");
				msg.append("Auto Dispatch file import == FAIL" + "\n");
				getScreenshot(driver, "fileupload_auto-dispatch");
				method_28677.setData("Test_Case", 3, 3, "FAIL");
			}

//			// Click on generated Import Order
//			
//			WebElement generated_order= isElementPresent("Generated_Order_xpath");
//			act.moveToElement(generated_order).build().perform();
//			act.click(generated_order).build().perform();
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		}

		catch (Exception e) {
			// TODO: handle exception
			method_28677.setData("Test_Case", 3, 3, "FAIL");
			logs.info("error in file upload" + e);
			msg.append("Auto Dispatch file import == FAIL" + "\n");
			
		}

	} catch (Exception e) {
		// TODO: handle exception
		logs.info("error in file upload" + e);
		msg.append("Auto Dispatch file import == FAIL" + "\n");
	}
	msg.append("==== create auto dispatch job using import : End ====" + "\n" + "\n");
	logs.info("==== create auto dispatch job using import : End  ====");
}
}
