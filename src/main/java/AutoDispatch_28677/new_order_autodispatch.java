package AutoDispatch_28677;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class new_order_autodispatch extends BaseInit {

	static method_28677 mth = new method_28677();

	@Test

	public void new_order_autodispatchs() throws Exception {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));// wait time +
			Actions act = new Actions(driver);
			try {

				logs.info("========== (new order from tasklog) Testcase  : Start ==========");
				msg.append("==== (new order from tasklog) Testcase 1 : Start ====" + "\n" + "\n");

				mth.orderCreation(1);
				Thread.sleep(1000);
				mth.validate_autodispatch(1);
				Thread.sleep(1000);

				logs.info("auto dispatch done");
			}

			catch (Exception e) { //
				method_28677.setData("Test_Case", 1, 3, "FAIL");
				logs.info("Created job is Auto Dispatch == FAIL" + e);
				msg.append("Created job is Auto Dispatch == FAIL" + "\n");

			}

		} catch (Exception e) {
			// TODO: handle exception
			logs.info("Created job is Auto Dispatch == FAIL" + e);
			msg.append("Created job is Auto Dispatch == FAIL" + "\n");
		}

		logs.info("========== Testcase  : End ==========");
		msg.append("==== (new order from tasklog) Testcase 1 : End ====" + "\n" + "\n");
	}

}
