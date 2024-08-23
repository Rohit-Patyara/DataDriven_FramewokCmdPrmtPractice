package commonFunctions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AddSupplierPage {

	WebDriver driver;

	// constructor for invoking webdriver methods
	public AddSupplierPage(WebDriver driver) {
		this.driver = driver;
	}

	// Define object repository for Add Supplier
	@FindBy(xpath = "(//a[text()='Suppliers'])[2]")
	WebElement objSupplierLink;
	@FindBy(xpath = "(//span[@data-caption='Add'])[1]")
	WebElement objAddIcon;
	@FindBy(id = "x_Supplier_Number")
	WebElement objSupplierNumber;
	@FindBy(name = "x_Supplier_Name")
	WebElement objSupplierName;
	@FindBy(xpath = "//textarea[@id='x_Address']")
	WebElement objAddress;
	@FindBy(xpath = "//input[@id='x_City']")
	WebElement objCity;
	@FindBy(id = "x_Country")
	WebElement objCountry;
	@FindBy(name = "x_Contact_Person")
	WebElement objContactPerson;
	@FindBy(xpath = "//input[@id='x_Phone_Number']")
	WebElement objPhoneNumber;
	@FindBy(id = "x__Email")
	WebElement objEmail;
	@FindBy(name = "x_Mobile_Number")
	WebElement objMobileNumber;
	@FindBy(xpath = "//textarea[@id='x_Notes']")
	WebElement objNotes;
	@FindBy(id = "btnAction")
	WebElement objAddBtn;
	@FindBy(id = "btnCancel")
	WebElement objCancelBtn;
	@FindBy(xpath = "//button[text()='OK!']")
	WebElement objConfirmOkBtn;
	@FindBy(xpath = "(//button[text()='Cancel'])[6]")
	WebElement objConfirmCancelBtn;
	@FindBy(xpath = "(//button[text()='OK'])[6]")
	WebElement objAlertOkBtn;
	@FindBy(xpath = "//span[@data-caption='Search']")
	WebElement objSearchpanel;
	@FindBy(xpath = "//input[@id='psearch']")
	WebElement objSearchTextBox;
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement objSearchButton;
	@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[6]/div/span/span")
	WebElement objSupplierTable;

	// Method for Add Supplier
	public boolean addSupplier(String supplierName, String address, String city, String country, String contactPerson,
			String phoneNumber, String email, String mobileNumber, String notes) throws Throwable {
		// objSupplierLink.click();
		// objAddIcon.click();
		Actions act = new Actions(driver);
		act.moveToElement(objSupplierLink).click().perform();
		act.moveToElement(objAddIcon).click().perform();
		// capture supplier number
		String exp_Data = objSupplierNumber.getAttribute("value");
		objSupplierName.sendKeys(supplierName);
		objAddress.sendKeys(address);
		objCity.sendKeys(city);
		objCountry.sendKeys(country);
		objContactPerson.sendKeys(contactPerson);
		objPhoneNumber.sendKeys(phoneNumber);
		objEmail.sendKeys(email);
		objMobileNumber.sendKeys(mobileNumber);
		objNotes.sendKeys(notes);
		// act.moveToElement(objAddBtn).click().perform();
		objAddBtn.sendKeys(Keys.ENTER);
		objConfirmOkBtn.click();
		Thread.sleep(2000);
		objAlertOkBtn.sendKeys(Keys.ENTER);
		if (!objSearchTextBox.isDisplayed())
			objSearchpanel.click();
		objSearchTextBox.clear();
		objSearchTextBox.sendKeys(exp_Data);
		objSearchButton.click();
		String act_Data = objSupplierTable.getText();
		if (act_Data.equals(exp_Data)) {
			Reporter.log(act_Data + "  " + exp_Data + " " + "Supplier Number is Matching", true);
			return true;
		} else {
			Reporter.log(act_Data + "  " + exp_Data + " " + "Supplier Number is Not Matching", true);
			return false;
		}
	}

}
