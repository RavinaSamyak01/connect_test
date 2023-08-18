package connect_Preferences;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class UserPreference extends BaseInit {

	@Test
	public void userPreference() {
		Actions act = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

		msg.append("User Preference Test start" + "\n");
		logs.info("User Preference Test start");

		// --Go to Preferences
		WebElement Preferences = isElementPresent("Preference_id");
		act.moveToElement(Preferences).build().perform();
		act.moveToElement(Preferences).click().perform();
		logs.info("Clicked on Preferences");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("a_UserPreferencesMain")));
		isElementPresent("UserPref_id").click();
		logs.info("Clicked on User Preference");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@ng-form=\"UserPrefForm\"]")));

	}

}
