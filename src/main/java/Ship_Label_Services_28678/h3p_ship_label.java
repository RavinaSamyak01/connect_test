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

public class h3p_ship_label extends OrderCreation {

	method_28678 mth = new method_28678();

	//@Test
	public void h3p_label() throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time
			
			logs.info("==== H3P service Ship Label Test : Start ==== ");
			msg.append("==== H3P service Ship Label Test : Start ====" + "\n");
			driver.navigate().refresh();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --H3P order creation
			H3P_DHL_carrier_order_create oc = new H3P_DHL_carrier_order_create();
			oc.h3P();
			mth.searchJob(14);

			
			// -- navigate to edit job tab
			
			WebElement edit_job_tab= isElementPresent("TLEditJobtab_id");
			Thread.sleep(1500);
			edit_job_tab.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
			
			
			// -- fetch ship label

			mth.shipLabel(3,2,14);
			
			// -- Return label
//			int = select for text index and int j = write result in sheet
			
			mth.returnLabel(3,2,14);
			
			// -- Repair label
			
			mth.repairLabel(3,2,14);
			
			method_28678.setData("Test_Case", 2, 3, "PASS");
			
			msg.append("H3P Ship-Label Test == PASS " + "\n");
			
			msg.append("Carreier : DHL"+"\n" );
			
		} catch (Exception e) {
			
			logs.info("H3P, Ship Label == FAIL "+e);
			msg.append("==== H3P Ship-Label Test == FAIL " + "\n");
			method_28678.setData("Test_Case", 2, 3, "FAIL");
			msg.append("Carreier : DHL"+"\n" );
		}
		logs.info("==== H3P service Ship Label Test : End ==== ");
		msg.append("==== H3P service Ship LAbel Test : End ====" + "\n"+"\n");
	}
}
