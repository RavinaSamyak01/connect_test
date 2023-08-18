package BU_verification_30014;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Quick_Quote_28658.methods;
import connect_BasePackage.BaseInit;

public class Business_unit_verification extends BaseInit {
	
	static method_30014 mth = new method_30014();
	
	// -- validate BU unit = FATL for SVC= DRV
	@Test
	public void fatl() throws IOException, EncryptedDocumentException, InvalidFormatException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		//-- open job
		msg.append("\n\n" + "===== BU Verification Test : Start ====" + "\n");
		logs.info("\n" + "===== BU Verification Test : Start ====" + "\n");
		driver.navigate().refresh();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		for (int j = 1; j <= 5; j++) {

			mth.open_pickup_frm_tasklog(j);

			// -- Edit job navigation
			mth.navigate_Edit_Job();
			
			//-- verify BU 
			
			mth.verify_BU_svc(j);

		}
		msg.append("\n\n" + "===== BU Verification Test : End ====" + "\n");
	}
	

}
