package Reports;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class Reports{
	public static ExtentReports report = new ExtentReports("Renuka.html", false);
	public static ExtentTest test;

	public static void start_test(String test_case_name, String description) {
		test = report.startTest(test_case_name, description);
		test.assignCategory("Regression Suit");
		test.assignAuthor("Renuka");
		addSystemInfo();
	}

	public static void addSystemInfo() {
		Map<String, String> sysInfo = new HashMap<String, String>();
		sysInfo.put("SeleniumVersion", "3.141.5");
		report.addSystemInfo(sysInfo);
	}

	public static void info(String Stepname, String details) {
		test.log(LogStatus.INFO, Stepname, details);
	}

	public static void pass(String Stepname, String details) {
		test.log(LogStatus.PASS, Stepname, details);
	}

	public static void fail(String Stepname, String details, String imagepath) {
		test.log(LogStatus.FAIL, Stepname, details+test.addScreenCapture(imagepath));
	}

	public static void warn(String Stepname, String details) {
		test.log(LogStatus.WARNING, Stepname, "<span style = 'font-weight:Bold; color:Yellow'>" + details + "</span>");
	}

	public static void fatal(String Stepname, String details) {
		test.log(LogStatus.FATAL, Stepname, details);
	}

	public static void endTest() {
		report.endTest(test);
	}

	public static void flush() {
		report.flush();
	}

}
