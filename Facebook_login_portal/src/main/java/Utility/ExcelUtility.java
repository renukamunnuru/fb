package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Reports.Log;
import Reports.Reports;





public class ExcelUtility {
	public static FileInputStream fis;
	public static XSSFWorkbook rwb;
	public static XSSFCell cell;
	public static XSSFCell cell1;
	public static XSSFSheet sheet;

	/**
	 * @author Piyush This method is demanding for filepath and return boolean value
	 *         This method is invoke the provided filepath and recongnize the excel
	 *         file
	 * @param filepath
	 * @return boolean
	 */

	public static boolean invokeExcel(String filepath) {
		boolean flag = false;
		try {

			fis = new FileInputStream(filepath);
			rwb = new XSSFWorkbook(fis);
		//	Log.info(filepath+ " is invoked succesfully");
		//	Reports.info("Excel Invocation", filepath + " is invoked succesfully");
			flag = true;
		} catch (FileNotFoundException e) {

			Log.info(filepath+ " is failed due to " + e.fillInStackTrace());
			Reports.info("Excel Invocation", filepath + " is is faild to invoke the excel due to " + e.toString());

		} catch (IOException e) {

			Log.info(filepath+ " is failed due to " + e.fillInStackTrace());
			Reports.info("Excel Invocation", filepath + " is is faild to invoke the excel due to " + e.toString());

		}
		return flag;
	}

	// -------------------------------------------------------------
	// to get the cell and column value into our variables (Used in method 3 and
	// line no 87 Around)
	public static String readSpecificCellData(int row_num, int col_num) {
		String data = null;
		try { // 0 3
			cell = sheet.getRow(row_num).getCell(col_num);
			data = cell.getStringCellValue();
		} catch (Exception e) {
			cell = sheet.getRow(row_num).getCell(col_num);
			DataFormatter format = new DataFormatter();
			data = format.formatCellValue(cell);
		}
		return data;
	}

	// ---------------------------------------------------------------
	// ---------------Create an array and fill that array by excel cell
	// wise-------------
	public static Object[][] readData(String sheetname) {
		Object[][] exceldata = null;
		sheet = rwb.getSheet(sheetname);
		int nur = sheet.getPhysicalNumberOfRows();
		int nuc = sheet.getRow(0).getPhysicalNumberOfCells();
		// nur, nuc using for loop and defined the size of Array
		// Declare the size of Array
		exceldata = new Object[nur - 1][nuc];

		int obj_row = 0;
		for (int r = 1; r < nur; r++, obj_row++) {

			int obj_col = 0;
			for (int c = 0; c < nuc; c++, obj_col++) {

				exceldata[obj_row][obj_col] = ExcelUtility.readSpecificCellData(r, c);
				// = class name class method(val,val)

				System.out.println("Data store at index-- " + "Data[" + obj_row + "]" + "[" + obj_col + "]==>>" + "["
						+ r + "]" + "[" + c + "]" + "===>>" + exceldata[obj_row][obj_col]);
			}
		}
		return exceldata;
	}

	// Write or save
	public static boolean setCelldata(String excelpath, String sheetname, String text, int row, int column)
			throws IOException {
		boolean flag = false;
		cell1 = sheet.getRow(row).createCell(column);
		cell1.setCellValue(text);
		FileOutputStream fout = new FileOutputStream(excelpath);
		rwb.write(fout);
		rwb.close();
		return flag;
	}

}
