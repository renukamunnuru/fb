package App_Module;

import java.util.Properties;

import Constants.Constant;
import Reports.Log;
import Reports.Reports;
import Utility.Utility;


public class LoginActions extends Utility{
	public static boolean flag;
	public static Properties prop_loct;

	public static boolean LoginForPositiveData(String uname, String password) {
		flag = false;
		try {
			// here we are giving the value for web page
			prop_loct = Utility.loadProperty(Constant.locaters_path);
			
			
			Utility.getLocator("Login_username_txtbox_name").sendKeys(uname);
			Utility.getLocator("Login_password_txtbox_name").sendKeys(password);
			Utility.getLocator("Login_signin_button_xpath").click();
			
			
			
			
			Reports.info("User Login", " Login Actions completed successfully");
			Log.info("login Action completed  successfully");
			flag = true;
		} catch (Exception e) {
			Log.info("Login Action failed  due to "+ e.fillInStackTrace().toString());
			Reports.fail("Login Action ", "LOgin Action failed due to " + e.toString(), "");
		}
		return flag;
	}

}

