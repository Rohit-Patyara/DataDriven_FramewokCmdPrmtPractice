package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AddCustomerPage {

	WebDriver driver;

	// constructor for invoking webdriver methods
	public AddCustomerPage(WebDriver driver) {
		this.driver=driver;
	}
	// Define object repository for Add Customer
	@FindBy(xpath = "(//a[contains(text(),'Customers')])[2]")
	WebElement objCustomersLink;
	@FindBy(xpath = "(//span[@data-caption='Add'])[1]")
	WebElement objAddIcon;
	@FindBy(id = "x_Customer_Number")
	WebElement objCustomerNumber;
	@FindBy(name = "x_Customer_Name")
	WebElement objCustomerName;
	@FindBy(xpath = "//textarea[@id='x_Address']")
	WebElement objAddress;
	@FindBy(id = "x_City")
	WebElement objCity;
	@FindBy(name = "x_Country")
	WebElement objCountry;
	@FindBy(id = "x_Contact_Person")
	WebElement objContactPerson;
	@FindBy(xpath = "//input[@id='x_Phone_Number']")
	WebElement objPhoneNumber;
	@FindBy(id = "x__Email")
	WebElement objEmail;
	@FindBy(name = "x_Mobile_Number")
	WebElement objMobileNumber;
	@FindBy(xpath = "//input[@id='x_Notes']")
	WebElement objNotes;
	@FindBy(id = "btnAction")
	WebElement objAddButton;
	@FindBy(id = "btnCancel")
	WebElement objCancelButton;
	@FindBy(xpath = "//button[text()='OK!']")
	WebElement objConfirmOkBtn;
	@FindBy(xpath = "(//button[text()='Cancel'])[6]")
	WebElement objConfirmCancelBtn;
	@FindBy(xpath = "(//button[contains(text(),'OK')])[6]")
	WebElement objAlertOkBtn;
	@FindBy(xpath = "//span[@data-caption='Search']")
	WebElement objSearchpanel;
	@FindBy(xpath = "//input[@id='psearch']")
	WebElement objSearchTextBox;
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement objSearchButton;
	@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[5]/div/span/span")
	WebElement objCustomerTable;

	// Method for Add Customer
	public boolean addCustomer(String customerName, String address, String city, String country, String contactPerson,
			String phoneNumber, String email, String mobileNumber, String notes) throws Throwable {
		Actions act = new Actions(driver);
		act.moveToElement(objCustomersLink).click().perform();
		act.moveToElement(objAddIcon).click().perform();
		// capture customer number
		String exp_Data = objCustomerNumber.getAttribute("value");
		objCustomerName.sendKeys(customerName);
		objAddress.sendKeys(address);
		objCity.sendKeys(city);
		objCountry.sendKeys(country);
		objContactPerson.sendKeys(contactPerson);
		objPhoneNumber.sendKeys(phoneNumber);
		objEmail.sendKeys(email);
		objMobileNumber.sendKeys(mobileNumber);
		objNotes.sendKeys(notes);
		act.moveToElement(objAddButton).click().perform();
		objConfirmOkBtn.click();
		Thread.sleep(1000);
		objAlertOkBtn.click();
		if (!objSearchTextBox.isDisplayed())
			objSearchpanel.click();
		objSearchTextBox.clear();
		objSearchTextBox.sendKeys(exp_Data);
		objSearchButton.click();
		String act_Data = objCustomerTable.getText();
		if (act_Data.equals(exp_Data)) {
			Reporter.log(act_Data + "  " + exp_Data + "  " + "Customer Number Matching", true);
			return true;
		} else {
			Reporter.log(act_Data + "  " + exp_Data + "  " + "Customer Number is Not Matching", true);
			return false;
		}

	}
}
