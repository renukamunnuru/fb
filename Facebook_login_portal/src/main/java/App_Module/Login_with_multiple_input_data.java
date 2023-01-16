package App_Module;
import java.util.Properties;

import org.testng.Assert;

import Constants.Constant;
import Reports.Log;
import Reports.Reports;
import Utility.Utility;

public class Login_with_multiple_input_data extends Utility {
	
	
	public static boolean flag;
	public static Properties prop_loct;
	public static String actualOutput;

	public static boolean loginformultipleData(String rm, String un, String pw, String criteria, String expOutput) {
		flag = false;
		if (rm.equalsIgnoreCase("Yes")) {
			try {
				prop_loct = Utility.loadProperty(Constant.locaters_path);

				Utility.getLocator("Login_username_txtbox_name").sendKeys(un);
				Utility.getLocator("Login_password_txtbox_name").sendKeys(pw);
				Utility.getLocator("Login_signin_button_xpath").click();
				;

				if (criteria.equalsIgnoreCase("valid")) {
					actualOutput = Utility.getLocator("Home_sucMessage_message_xpath").getText();
					Assert.assertEquals(actualOutput, expOutput);
					Log.info("Test Case Passed");
					Reports.pass("For Valid Data", "Test Case passed for valid data");
					flag = true;
				} else if (criteria.equalsIgnoreCase("invalid") && un == "") {
					actualOutput = Utility.getLocator("Login_errwithoutun_message_xpath").getText();
					Assert.assertEquals(actualOutput, expOutput);
					Log.info("Test Case Passed for invalid without un");
					Reports.pass("For Invalid without un", "Test Case passed for invalid data without username");
					flag = true;
				} else if (criteria.equalsIgnoreCase("invalid") && un != "") {
					actualOutput = Utility.getLocator("Login_errwithoutun_message_xpath").getText();
					Assert.assertEquals(actualOutput, expOutput);
					Log.info("Test Case Passed for invalid without un");
					Reports.pass("For Invalid without un", "Test Case passed for invalid data without username");
					flag = true;
				} else
					Log.info("Test CAse Failed");
				Reports.pass("Login Actions", "Test Case Failed");

			} catch (Exception e) {
				Reports.fail("Login Actions", "Login Actions Failed due to ", e.toString());
				Log.error("LOgin Actions Failed due to : " + e.fillInStackTrace().toString());
			}
		} else {
			System.out.println("Run mode is no for this data");

		}
		return flag;
	}


}
