package connect_OrderCreationNonSPL;

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
import connect_OCBaseMethods.VerifyMargin;
import connect_OCBaseMethods.WaitForArrival;
import connect_OCBaseMethods.WaitForDeptarture;
import connect_OCBaseMethods.XerWaitForArrival;
import connect_OCBaseMethods.XerWaitForDeparture;

public class FRA extends OrderCreation {

	@Test
	public void fraFreight() throws Exception {
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));// wait time

		// --Order Creation
		OrderCreation OC = new OrderCreation();
		OC.orderCreation(8);

		// --Refresh App
		refreshApp();

	}
}
