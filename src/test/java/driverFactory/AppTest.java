package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.AddCustomerPage;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil {

	String inputPath = "./FileInput/DataEnginePrac.xlsx";
	String outputPath = "./FileOutput/HybridFwPractResults.xlsx";
	String cTestData = "AddCustomer";
	ExtentReports report;
	ExtentTest logger;

	@Test
	public void addCustomer() throws Throwable {
		report = new ExtentReports("./target/ExtentReports/" + cTestData + ".html");
		// call add customer page class
		AddCustomerPage customer = PageFactory.initElements(driver, AddCustomerPage.class);
		ExcelFileUtil xl = new ExcelFileUtil(inputPath);
		int rc = xl.rowCount(cTestData);
		Reporter.log("No of Rows: " + rc, true);
		for (int i = 1; i <= rc; i++) {
			logger = report.startTest(cTestData);
			String customerName = xl.getCellData(cTestData, i, 0);
			String address = xl.getCellData(cTestData, rc, 1);
			String city = xl.getCellData(cTestData, rc, 2);
			String country = xl.getCellData(cTestData, rc, 3);
			String contactPerson = xl.getCellData(cTestData, rc, 4);
			String phoneNumber = xl.getCellData(cTestData, rc, 5);
			String email = xl.getCellData(cTestData, rc, 6);
			String mobileNumber = xl.getCellData(cTestData, rc, 7);
			String notes = xl.getCellData(cTestData, rc, 8);
			logger.log(LogStatus.INFO, customerName + " " + address + " " + city + " " + country + " " + contactPerson
					+ " " + phoneNumber + " " + email + " " + mobileNumber + " " + notes);
			boolean result = customer.addCustomer(customerName, address, city, country, contactPerson, phoneNumber,
					email, mobileNumber, notes);
			if (result) {
				// if result is true write as pass in cTestData cell
				xl.setCelldata(cTestData, i, 9, "Pass", outputPath);
				logger.log(LogStatus.PASS, "Add Customer is Passed");
			} else {
				// if result is false write as fail in cTestData cell
				xl.setCelldata(cTestData, i, 9, "Fail", outputPath);
				logger.log(LogStatus.FAIL, "Add Customer is Failed");
			}
			report.endTest(logger);
			report.flush();
		}

	}

}
