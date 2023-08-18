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

public class p3p_ship_label extends OrderCreation {

	method_28678 mth = new method_28678();

//	@Test
	public void p3p_label() throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
			logs.info("==== P3P service Ship Label Test : Start ==== ");
			msg.append("==== P3P service Ship Label Test : Start ====" + "\n");
			// -- search P3P order from sheet

			OrderCreation OC = new OrderCreation();
			OC.searchJob(3);
			logs.info(" Pickup ID : " + PUID);
			method_28678.setData("Test_Case", 1, 4, PUID);
			msg.append("Pickup id: " + PUID + "\n");
			

			// -- navigate to edit job tab
			
			WebElement edit_job_tab= isElementPresent("TLEditJobtab_id");
			edit_job_tab.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// -- fetch ship label

			mth.shipLabel(1,1,3);
			
			// -- Return label
			
			mth.returnLabel(1,1,3);
			
			// -- Repair label
			
			mth.repairLabel(1,1,3);
			
			method_28678.setData("Test_Case", 1, 3, "PASS");
			logs.info("P3P Ship-Label Test == PASS ");
			msg.append("P3P Ship-Label Test == PASS " + "\n");
			msg.append("Carreier : Fedex"+"\n" );
			
		} catch (Exception e) {
			
			logs.info("P3P, Ship Label == FAIL "+e);
			msg.append("==== P3P Ship-Label Test == FAIL " + "\n");
			method_28678.setData("Test_Case", 1, 3, "FAIL");
			msg.append("Carreier : Fedex"+"\n" );
			
		}
		logs.info("==== P3P service Ship LAbel Test : End ====" );
		msg.append("==== P3P service Ship LAbel Test : End ====" + "\n"+"\n");
	}
}
