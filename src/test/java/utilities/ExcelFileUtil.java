package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {

	Workbook wb;

	// Constructor for reading excel path
	public ExcelFileUtil(String excelPath) throws Throwable {
		FileInputStream fis = new FileInputStream(excelPath);
		wb = WorkbookFactory.create(fis);
	}

	// Count no of rows in a sheet
	public int rowCount(String sheetName) {
		int rc = wb.getSheet(sheetName).getLastRowNum();
		return rc;
	}

	// Method for reading cell data
	public String getCellData(String sheetName, int row, int column) {
		String data;
		if (wb.getSheet(sheetName).getRow(row).getCell(column).getCellType() == CellType.NUMERIC) {
			int cellData = (int) wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(cellData);
		} else {
			data = wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}

	// Method for setCelldata
	public void setCelldata(String sheetName, int row, int column, String status, String writeExcelpath)
			throws Throwable {
		// Get sheet from wb. For sheet we have two packages choose ss
		Sheet ws = wb.getSheet(sheetName);
		// Get row from sheet
		Row rowNum = ws.getRow(row);
		// Create cell in row
		Cell cell = rowNum.createCell(column);
		// Write status
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("Pass")) {
			CellStyle style = wb.createCellStyle();
			// Go with interface not java class
			Font font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		} else if (status.equalsIgnoreCase("Fail")) {
			CellStyle style = wb.createCellStyle();
			// Go with interface not java class
			Font font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		} else if (status.equalsIgnoreCase("Blocked")) {
			CellStyle style = wb.createCellStyle();
			// Go with interface not java class
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}
		FileOutputStream fos = new FileOutputStream(writeExcelpath);
		wb.write(fos);
	}

	public static void main(String[] args) throws Throwable {
		ExcelFileUtil xl = new ExcelFileUtil("E:\\Qedge Data Files\\Sample.xlsx");
		int rowCount = xl.rowCount("Emp");
		System.out.println("No of rows :" + rowCount);
		for (int i = 1; i <= rowCount; i++) {
			// Read all rows each cell data
			String fName = xl.getCellData("Emp", i, 0);
			String mName = xl.getCellData("Emp", i, 1);
			String lName = xl.getCellData("Emp", i, 2);
			String eId = xl.getCellData("Emp", i, 3);
			System.out.println(fName + " " + mName + " " + lName + " " + eId);
			// Write as Pass into Status cell
			//xl.setCelldata("EMp", i, 4, "Pass", "E:\\Qedge Data Files\\SampleResults.xlsx");
			//xl.setCelldata("EMp", i, 4, "Fail", "E:\\Qedge Data Files\\SampleResults.xlsx");
			xl.setCelldata("EMp", i, 4, "Blocked", "E:\\Qedge Data Files\\SampleResults.xlsx");
		}
	}
}
