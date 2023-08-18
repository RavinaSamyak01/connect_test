package Connect_VerifyBill;

import java.io.IOException;

import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class VerifyBill_MNX extends BaseInit {

	@Test
	public void MNX() throws Exception, Exception {

		try {
			logs.info("==== Verify Bill - MNX : Start ==== " + "\n");
			msg.append("==== ==== Verify Bill - MNX : Start ==== " + "\n");

			// Open Verify Bill
			VerifyBillBase VB = new VerifyBillBase();
			VB.OpenVerifyBill();
			msg.append("Verify Bill: Tab Verification For MNX" + "\n");
			// Check with Cutoff date filters
			VB.CutOffdateFilter(1);

			// Check with PickUp Id filter
			VB.pickUpIdFilter(1);

			// Check with Service filter
			VB.serviceFilter(1);

			// Check with Origin & Destination filter
			VB.locationFilter(1);

			// Check with account Filter
			VB.accountFilter(1);

			// Verify Re-Calculate Charges
			VB.VerifyReCalCharges(1);

			// Check JobID Filter
			VB.JOBIDFilter(1);

			// Verify "Verified" Stage
			VB.VerifyJob(1);

			driver.navigate().refresh();
			msg.append("Verify Bill For MNX:-Passed" + "\n");
			System.out.println("Verify Bill For MNX is passed");
			logs.info("Verify Bill For MNX is passed");
			Thread.sleep(4000);

		}

		catch (Exception e) {

			logs.error(e);
			msg.append("Verify Bill For MNX :- Failed" + "\n");
			System.out.println("Verify Bill For MNX is failed");
			logs.info("Verify Bill For MNX is failed");

		}

		logs.info("==== Verify Bill - MNX : End ==== "+"\n");
		msg.append("==== Verify Bill - MNX : End  ==== "+"\n");
	}

}
