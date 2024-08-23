package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLogoutPage {

	// Define repository for logout elements
	@FindBy(xpath = "//a[@id='logout']")
	WebElement objLogoutLink;

	// Method for logout
	public void adminLogout() {
		objLogoutLink.click();
	}
}
