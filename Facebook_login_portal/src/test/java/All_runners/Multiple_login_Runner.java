package All_runners;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import App_Module.LaunchEnv;
import App_Module.Login_with_multiple_input_data;
import Constants.Constant;
import Reports.Log;
import Reports.Reports;
import Utility.ExcelUtility;
import Utility.Utility;


public class Multiple_login_Runner extends Login_with_multiple_input_data {
	
	  @BeforeMethod
		public void OpenBrowserAndURL() throws InterruptedException {
			Reports.start_test("Login Actions", "Executing the test case for positive Data only");
			
			Assert.assertTrue(LaunchEnv.BrowserInvocation(prop_config.getProperty("Browser")));
			Assert.assertTrue(LaunchEnv.invokeUrl(prop_config.getProperty("App_url")));
		}

		@Test(dataProvider = "anyname")
		public void Login_Runner_multiple(String rm, String un, String pw, String criteria, String expOutput) {
			try {		
				Assert.assertTrue(Login_with_multiple_input_data.loginformultipleData(rm, un, pw, criteria, expOutput));

			} catch (Exception e) {
				System.out.println("It is executing for error message where Runmode = NO...");
				Log.info("It is executing for error message where Runmode = NO...");
				Reports.info("No Data", "It is executing for error message where Runmode = NO...");
			}
		}

		@AfterMethod
		public void close_Browser() {
			Utility.closeBrowser();
			Reports.endTest();
			Log.endTest();

		}

		@DataProvider(name = "anyname")
//		@DataProvider(name="anyname")
		public static Object[][] ExcelSheetReading() {
			ExcelUtility.invokeExcel(Constant.TestData_excel_path);
			Object[][] exceldata = ExcelUtility.readData("input");
			return exceldata;
		}



}
