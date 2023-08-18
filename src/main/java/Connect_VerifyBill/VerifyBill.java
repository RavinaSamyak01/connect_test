package Connect_VerifyBill;

import java.io.IOException;

import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class VerifyBill extends BaseInit {
	static StringBuilder msg = new StringBuilder();

	@Test
	public void massVfyBill() throws IOException {

		msg.append("\n\n" + "===== Verify Bill Test : Start ====" + "\n");

		try {
			VerifyBill_MNX VMNX = new VerifyBill_MNX();
			VMNX.MNX();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "VBMNXFAIL");
		}

		try {
			VerifyBill_FedEx VFedEx = new VerifyBill_FedEx();
			VFedEx.FedEx();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "VBFedExFAIL");
		}

		try {
			VerifyBill_MTX VMTX = new VerifyBill_MTX();
			VMTX.MTX();
		} catch (Exception e) {
			logs.info(e);
			getScreenshot(driver, "VBMTXFAIL");
		}

		msg.append("\n" + "=====Verify Bill Test : End ====" + "\n");

	}

}
