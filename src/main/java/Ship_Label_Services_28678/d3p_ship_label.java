package Ship_Label_Services_28678;

import java.util.Iterator;
import java.util.Set;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;
import connect_OCBaseMethods.OrderCreation;
import AutoDispatch_28677.*;

public class d3p_ship_label extends OrderCreation {

	method_28678 mth = new method_28678();

	//@Test
	public void d3p_label() throws Exception {
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
			
			logs.info("==== D3P service Ship Label Test : Start ==== ");
			msg.append("==== D3P service Ship Label Test : Start ====" + "\n");
			// -- search d3p order from sheet
			driver.navigate().refresh();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			OrderCreation OC = new OrderCreation();
			OC.searchJob(11);
			logs.info(" Pickup ID : " + PUID);
			method_28678.setData("Test_Case", 3, 4, PUID);
			msg.append("Pickup id: " + PUID + "\n");
			
			// -- navigate to edit job tab
			
						WebElement edit_job_tab= isElementPresent("TLEditJobtab_id");
						edit_job_tab.click();
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// -- fetch ship label

			mth.shipLabel(4,3,11);
			
			// -- Return label
			
			OC.searchJob(11);
			mth.returnLabel(4,3,11);
			
			// -- Repair label
			
			mth.repairLabel(4,3,11);
			
			method_28678.setData("Test_Case", 3, 3, "PASS");
			
			msg.append("D3P Ship-Label Test == PASS " + "\n");
			
			msg.append("Carreier : UPS"+"\n" );
			
		} catch (Exception e) {
			
			logs.info("D3P, Ship Label == FAIL "+e);
			msg.append("==== D3P Ship-Label Test == FAIL " + "\n");
			method_28678.setData("Test_Case", 3, 3, "FAIL");
			msg.append("Carreier : UPS"+"\n" );
			
		}
		logs.info("==== D3P service Ship Label Test : End ==== ");
		msg.append("==== D3P service Ship Label Test : End ====" + "\n"+"\n");
	}
}
