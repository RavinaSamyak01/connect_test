package AutoDispatch_28677;

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
import AutoDispatch_28677.*;

public class connect_autodispatch extends connect_imort_autodspatch {
	static StringBuilder msg = new StringBuilder();

// -- all combinations are call here using method class

	@Test
	public void auto_dispatch() throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
			msg.append("\n\n" + "===== Auto Dispatch Test : Start ====" + "\n");
driver.navigate().refresh();
			// import job (Create job by import file (Priority for run == 1))

			connect_imort_autodspatch fi = new connect_imort_autodspatch();
			fi.import_order_create();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// Validate Job is auto dispatch for New Order
			new_order_autodispatch new_order = new new_order_autodispatch();
			new_order.new_order_autodispatchs();

			// Validate Job is auto dispatch for Shipment template

			connect_new_shipment_autodispach new_order_shipment = new connect_new_shipment_autodispach();
			new_order_shipment.shipment_order_create();

			// Validate Job is auto dispatch , which Is created from Netship portal
			netship_autodispatch ns = new netship_autodispatch();
			ns.netship_auto_dispatch_order();

			// --Fetch Generated pickup no for import Job and validate Auto dispatch
			// functionality

			connect_fetch_imort_job_autodspatch chech = new connect_fetch_imort_job_autodspatch();
			chech.fetch_generated_order();

			logs.info("Auto Dispatch flow == PASS ");
			msg.append("Auto Dispatch flow == PASS" + "\n");

		} catch (Exception e) {
			// TODO: handle exception
			logs.info("Error in auto dispatch flow " + e);
			msg.append("auto dispatch flow == FAIL " + "\n");
		}

		msg.append("===== Auto Dispatch Test : End ====" + "\n" + "\n");

	}
}
