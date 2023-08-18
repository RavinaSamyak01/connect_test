package Connect_VerifyBill;

import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;
import connect_OCBaseMethods.OrderCreation;

public class MassVerifyBillCustomer extends BaseInit {

	@Test
	public void MVCBill() {

		try {
			logs.info("==== Mass Verify Customer Bill  : Start ==== " + "\n");
			msg.append("==== Mass Verify Customer Bill : Start  ==== " + "\n");
			// Open Mass verify Bill
			MassVerify_CustomerBill_base MVCB = new MassVerify_CustomerBill_base();
			MVCB.OpenMassVerifyCustomerBill();
			msg.append("\n" + "Mass Verify Bill Customer: Tab Verification" + "\n");
			// Date Filter
			MVCB.DateFilter();

			// Order Type filter
			MVCB.OrderTypeFilter();

			// FSL-Name filter
			MVCB.FSLNameFilter();

			// Account Filter
			MVCB.AccountFilter();

			// Verify Order
			MVCB.VerifyOrder();

			// --Refresh App
			OrderCreation OC = new OrderCreation();
			OC.refreshApp();

			msg.append("Mass Verify Customer Bill:=Passed" + "\n");
			logs.info("Mass Verify Customer Bill is passed");
			Thread.sleep(2000);
		}

		catch (Exception ex) {

			logs.error(ex);
			msg.append("Mass Verify Customer Bill:= Failed" + "\n");
			logs.info("Mass Verify Customer Bill is failed");

		}
		logs.info("==== Mass Verify Customer Bill  : End ==== " + "\n");
		msg.append("==== Mass Verify Customer Bill : End  ==== " + "\n");
	}

}
