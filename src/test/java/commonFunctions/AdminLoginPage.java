package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage {

	// Define repository for login page
	@FindBy(xpath = "//input[@id='username']")
	WebElement objUsername;
	@FindBy(xpath = "//input[@id='password']")
	WebElement objPassword;
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement objSubmitBtn;
	@FindBy(xpath = "//button[@id='btnreset']")
	WebElement objResetBtn;

	// Method for login
	public void adminLogin(String username, String password) {
		objResetBtn.click();;
		objUsername.sendKeys(username);
		objPassword.sendKeys(password);
		objSubmitBtn.click();
	}

}
