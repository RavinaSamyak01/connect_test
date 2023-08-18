package AutoDispatch_28677;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class connect_new_shipment_autodispach extends BaseInit {
	

	static method_28677 mth = new method_28677();

//	@Test
	public void shipment_order_create() throws Exception {
		try {
			msg.append("==== auto dispatch from shipment template : Start ====" + "\n");
			logs.info("==== Validate job auto dispatch from shipment template : Start ====");

			// navigate to new shipment creation page

			mth.nav_shipment_creation_page();

			// Enter data for new template
			mth.ship_order_create(2);

			// --Open Job ID from tasklog , fetch and validate Pickup id and job status

			mth.open_jobID_frm_tasklog(2);

			// -- fetch pickup id from opened job id

			String P_id = isElementPresent("ship_pickup_xpath").getText();

			String pickupID = P_id.replaceAll("[^0-9]", "");
			logs.info("Pickup ID is : " + pickupID);

			method_28677.setData("Test_Case", 2, 4, pickupID);
			getScreenshot(driver, "pickupID_shipment");

			// -- validate job status

			mth.validate_autodispatch(2);
			method_28677.setData("Test_Case", 2, 3, "PASS");

		}

		catch (Exception e) {
			msg.append("Job is proceed with  Auto Dispatch == FAIL" + "\n");
			logs.info("not able to validate auto dispatch for Shipment template" + e);
			getScreenshot(driver, "shipment-fail");
			method_28677.setData("Test_Case", 2, 3, "FAIL");
			
		}

		msg.append("==== auto dispatch from shipment template : End ====" + "\n" + "\n");
		logs.info("==== auto dispatch from shipment template : End ====");

	}


}

