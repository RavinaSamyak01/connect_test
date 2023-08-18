package Connect_CreateBookingdeltaAirlines;

import org.testng.annotations.Test;

import connect_Ecourier.OrderCreationE;
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

public class SDdelta extends OrderCreation {
	static StringBuilder msg = new StringBuilder();

	@Test
	public void sdSameDay() throws Exception {
		/*
		 * JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));// wait time Actions act =
		 * new Actions(driver);
		 */

		msg.append("\n\n" + "===== Delta Airline Test : Start ====" + "\n");

		// --Order Creation
		OrderCreationE OC = new OrderCreationE();
		OC.orderCreation(2);

		// User story detail

		// --Unknown Shipper
		OC.unknowShipper(2);

		// --Select Flight
		OC.selectFlight();

		// TC Acknowledge

		TCAcknowledgeDelta TCAck = new TCAcknowledgeDelta();
		TCAck.tcAcknowledge();

		// --Refresh App
		refreshApp();

		msg.append("\n" + "===== Delta Airline Test : End ====" + "\n");

	}
}
