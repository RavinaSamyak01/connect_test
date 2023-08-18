	package Ship_Label_Services_28678;

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
import connect_OCBaseMethods.OrderCreation;
import customer_segment_28661.method_28661;
import AutoDispatch_28677.*;

public class connect_ship_label extends OrderCreation {

	// -- all combinations are call here using method class
	//static StringBuilder msg = new StringBuilder();
	static method_28678 mth = new method_28678();
	@Test
	public void ship_label() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		driver.navigate().refresh();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		logs.info("========== Test Shiplabel : Start ==========");
		msg.append("\n\n" + "===== Ship Label Test : Start ====" + "\n");

		try {

			// -- validate P3p label with fedex carrier

			p3p_ship_label p3p = new p3p_ship_label();
			p3p.p3p_label();

			// -- create and validate h3p label with DHL carrier
			h3p_ship_label h3p = new h3p_ship_label();
			h3p.h3p_label();

			// -- validate D3p label with UPS carrier

			d3p_ship_label d3p = new d3p_ship_label();
			d3p.d3p_label();

			logs.info("Ship Lable flow == PASS ");
			msg.append("Ship Lable flow == PASS" + "\n");

		} catch (Exception e) {
			// TODO: handle exception
			logs.info("Error in Ship Lable flow " + e);
			msg.append("Ship Lable flow  == FAIL " + "\n");
		}
		logs.info("========== Test Shiplabel : End ==========");
		msg.append("\n" + "===== Ship Label Test : End ====" + "\n");
		mth.SendEmail_ship_label();
	}

}
