package AutoDispatch_28677;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class connect_fetch_imort_job_autodspatch extends BaseInit {

	static method_28677 mth = new method_28677();

	// @Test
	public void fetch_generated_order() throws Exception {
		try {
			msg.append("==== Auto dispatch validate from Import Job : Start ====" + "\n");
			logs.info("==== Auto dispatch validate from Import Job : Start ====");
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

				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
						By.xpath("//*[@class=\"OpenCloseClass dropdown open\"]//ul")));

				// --Click on Import order
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a_ImportRoute")));
				isElementPresent("import_order_id").click();
				logs.info("Clicked on Import Order");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				getScreenshot(driver, "Importorder");
				Thread.sleep(500);

// --  Click on generated Import Order

				WebElement generated_order = isElementPresent("Generated_Order_xpath");
				act.moveToElement(generated_order).build().perform();
				act.click(generated_order).build().perform();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				String FileName = "28677_auto_dispatch_ import_loc" + ".xlsx";
				System.out.println("file location is :" + FileName);

//			String pickup__fetch = driver
//					.findElement(By.xpath("//td[contains(text(),'28677_auto_dispatch')]//following::td[1]")).getText();
//			logs.info("Pickup ID for Imported Job is : " + pickup__fetch);
//			

				List<WebElement> total_pickup = driver
						.findElements(By.xpath("//td[contains(text(),'28677_auto_dispatch')]//following::td[1]"));
				WebElement nav_last_pickup = total_pickup.get(total_pickup.size() - 1);
				act.moveToElement(nav_last_pickup).build().perform();
				Thread.sleep(1000);
				String last_pickup = total_pickup.get(total_pickup.size() - 1).getText();
				logs.info("Latest Pickup ID for Imported Job is ==" + last_pickup);
				method_28677.setData("Test_Case", 4, 4, last_pickup);

				// job id

				List<WebElement> total_job_id = driver
						.findElements(By.xpath("//td[contains(text(),'28677')]//preceding::td[2]"));
				WebElement nav_last_jobid = total_pickup.get(total_pickup.size() - 1);
				act.moveToElement(nav_last_jobid).build().perform();
				Thread.sleep(1000);
				String last_job = total_job_id.get(total_job_id.size() - 1).getText();
				logs.info("Latest Job ID for Imported Job is ==" + last_job);
				method_28677.setData("Test_Case", 4, 6, last_job);
				getScreenshot(driver, "jobid_imported-job_auto-dispatch");

// -- Close popup

				WebElement close_popup = isElementPresent("quick_pop_close_id");
				act.moveToElement(close_popup).build().perform();
				act.click(close_popup).build().perform();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

// -- open job using pickup id

				mth.open_pickup_frm_tasklog(4);

// --validate auto dispatch functionalityu

				mth.validate_autodispatch(4);
			}

			catch (Exception e) {
				// TODO: handle exception

				logs.info("error in fetch uploaded job data " + e);

				msg.append("Auto Dispatch file import == FAIL" + "\n");
				getScreenshot(driver, "error-autodispatch");
				method_28677.setData("Test_Case", 4, 3, "FAIL");
			}

		} catch (Exception e) {
			// TODO: handle exception
			logs.info("error in file upload");
			msg.append("Auto Dispatch file import == FAIL" + "\n");
		}
		
		msg.append("==== Auto dispatch validate from Import Job : End ====" + "\n" + "\n");
		logs.info("==== Auto dispatch validate from Import Job : End ====");
	}
}
