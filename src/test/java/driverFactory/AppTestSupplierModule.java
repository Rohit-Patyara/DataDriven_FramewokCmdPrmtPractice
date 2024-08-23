package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.AddSupplierPage;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTestSupplierModule extends AppUtil {

	String inputPath = "./FileInput/DataEnginePrac.xlsx";
	String outputPath = "./FileOutput/HybridFwPractResults.xlsx";
	String sTestData = "AddSupplier";
	ExtentReports report;
	ExtentTest logger;

	@Test
	public void addCustomer() throws Throwable {
		report = new ExtentReports("./target/ExtentReports/" + sTestData + ".html");
		// call add supplier page class
		AddSupplierPage supplier = PageFactory.initElements(driver, AddSupplierPage.class);
		ExcelFileUtil xl = new ExcelFileUtil(inputPath);
		int rc = xl.rowCount(sTestData);
		Reporter.log("No of Rows: " + rc, true);
		for (int i = 1; i <= rc; i++) {
			logger = report.startTest(sTestData);
			String supplierName = xl.getCellData(sTestData, i, 0);
			String address = xl.getCellData(sTestData, i, 1);
			String city = xl.getCellData(sTestData, i, 2);
			String country = xl.getCellData(sTestData, i, 3);
			String contactPerson = xl.getCellData(sTestData, i, 4);
			String phoneNumber = xl.getCellData(sTestData, i, 5);
			String email = xl.getCellData(sTestData, i, 6);
			String mobileNumber = xl.getCellData(sTestData, i, 7);
			String notes = xl.getCellData(sTestData, i, 8);
			logger.log(LogStatus.INFO, supplierName + " " + address + " " + city + " " + contactPerson + " "
					+ phoneNumber + " " + email + " " + mobileNumber + " " + notes);
			Boolean result = supplier.addSupplier(supplierName, address, city, country, contactPerson, phoneNumber,
					email, mobileNumber, notes);
			if (result) {
				// if result is true write as pass in sTestData cell
				xl.setCelldata(sTestData, i, 9, "Pass", outputPath);
				logger.log(LogStatus.PASS, "Add Supplier is Success");
			} else {
				// if result is true write as fail in sTestData cell
				xl.setCelldata(sTestData, i, 9, "Fail", outputPath);
				logger.log(LogStatus.FAIL, "Add Supplier is Failure");
			}
			report.endTest(logger);
			report.flush();
		}

	}

}
