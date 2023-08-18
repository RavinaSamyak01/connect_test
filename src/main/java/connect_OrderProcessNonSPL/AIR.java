package connect_OrderProcessNonSPL;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_OCBaseMethods.Board;
import connect_OCBaseMethods.Deliver;
import connect_OCBaseMethods.Drop;
import connect_OCBaseMethods.HAANotify;
import connect_OCBaseMethods.OnHandAtDestination;
import connect_OCBaseMethods.OrderCreation;
import connect_OCBaseMethods.Pickup;
import connect_OCBaseMethods.ReadyForDispatch;
import connect_OCBaseMethods.TCAcknowledge;
import connect_OCBaseMethods.VerifyMargin;
import connect_OCBaseMethods.WaitForArrival;
import connect_OCBaseMethods.WaitForDeptarture;
import connect_OCBaseMethods.XerWaitForArrival;
import connect_OCBaseMethods.XerWaitForDeparture;

public class AIR extends OrderCreation {

	@Test
	public void airService() throws Exception {
		// JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));// wait time

		// --Order Creation
		OrderCreation OC = new OrderCreation();
		OC.orderCreation(6);

		// --Move to Job Status Tab
		WebElement JoStatusTab = isElementPresent("TLJobStatusTab_id");
		wait.until(ExpectedConditions.elementToBeClickable(JoStatusTab));
		JoStatusTab.click();
		logs.info("Clicked on Job Status Tab");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

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

		// HAA Notify
		HAANotify HAAN = new HAANotify();
		HAAN.haaNtfy();

		// Wait for Departure
		WaitForDeptarture WFD = new WaitForDeptarture();
		WFD.waitForDept();

		// XER wait for Arrival
		XerWaitForArrival XWFA = new XerWaitForArrival();
		XWFA.xerWaitForArr();

		// XER Wait for Departure
		XerWaitForDeparture XWFD = new XerWaitForDeparture();
		XWFD.xerWaitForDept();

		// Wait for Arrival
		WaitForArrival WFA = new WaitForArrival();
		WFA.waitForArr();

		// ONHAND @ DESTINATION
		OnHandAtDestination OHAD = new OnHandAtDestination();
		OHAD.onHandDst();

		// OnBorad
		Board Brd = new Board();
		Brd.onBoard();

		// XER wait for Arrival
		XWFA.xerWaitForArr();

		// XER Wait for Departure
		XWFD.xerWaitForDept();

		// OnBorad
		Brd.onBoard();

		// Wait for Arrival
		WFA.waitForArr();

		// ONHAND @ DESTINATION
		OHAD.onHandDst();

		// DELIVERED
		Deliver Del = new Deliver();
		Del.confirmDelivery();

		// Verify Margin
		VerifyMargin VM = new VerifyMargin();
		VM.verifyMargin(6);

		// -Refresh App
		OC.refreshApp();

	}
}
