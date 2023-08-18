package connect_OrderProcessNonSPL;

import org.testng.annotations.Test;

import connect_OCBaseMethods.Board;
import connect_OCBaseMethods.Board1;
import connect_OCBaseMethods.Deliver;
import connect_OCBaseMethods.Drop;
import connect_OCBaseMethods.OrderCreation;
import connect_OCBaseMethods.Pickup;
import connect_OCBaseMethods.ReadyForDispatch;
import connect_OCBaseMethods.Recover;
import connect_OCBaseMethods.SendDelAlert;
import connect_OCBaseMethods.TCAcknowledge;
import connect_OCBaseMethods.VerifyCustomerBill;
import connect_OCBaseMethods.WaitForArrival;
import connect_OCBaseMethods.WaitForDeptarture;
import connect_OCBaseMethods.XerWaitForArrival;
import connect_OCBaseMethods.XerWaitForDeparture;

public class SD extends OrderCreation {
	@Test
	public void sdSameDay() throws Exception {
		/*
		 * JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));// wait time Actions act =
		 * new Actions(driver);
		 */

		// --Order Creation
		OrderCreation OC = new OrderCreation();
		OC.orderCreation(2);

		// --Unknown Shipper
		OC.unknowShipper(2);

		// --Select Flight
		OC.selectFlight();

		// TC Acknowledge
		TCAcknowledge TCAck = new TCAcknowledge();
		TCAck.tcAcknowledge();

		// Pickup Alert
		ReadyForDispatch RFD = new ReadyForDispatch();
		RFD.pickupAlert();

		// PICKEDUP
		Pickup PU = new Pickup();
		PU.confirmPickup();

		// DROP
		Drop Drp = new Drop();
		Drp.dropAtOrigin();

		// Send Del Alert
		SendDelAlert SDA = new SendDelAlert();
		SDA.delAlert();

		// Wait for Departure
		WaitForDeptarture WFD = new WaitForDeptarture();
		WFD.waitForDept();

		// OnBorad
		Board Brd = new Board();
		Brd.onBoard();

		// XER wait for Arrival
		XerWaitForArrival XWFA = new XerWaitForArrival();
		XWFA.xerWaitForArr();

		// XER Wait for Departure
		XerWaitForDeparture XWFD = new XerWaitForDeparture();
		XWFD.xerWaitForDept();

		// board2
		Board1 Brd1 = new Board1();
		Brd1.onBoard1();

		// Wait for Arrival
		WaitForArrival WFA = new WaitForArrival();
		WFA.waitForArr();

		// Recover
		Recover RCV = new Recover();
		RCV.recoverAtDestination();

		// DELIVERED
		Deliver Del = new Deliver();
		Del.confirmDelivery();

		// Verify Customer Bill
		VerifyCustomerBill VCB = new VerifyCustomerBill();
		VCB.verifyCustomerBill(2);

		// --Refresh App
		refreshApp();
	}
}
