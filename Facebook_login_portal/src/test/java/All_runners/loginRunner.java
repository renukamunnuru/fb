package All_runners;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import App_Module.LaunchEnv;
import App_Module.LoginActions;
import Constants.Constant;
import Reports.Log;
import Reports.Reports;
import Utility.Utility;

public class loginRunner extends LoginActions {

	public static Properties prop_config = Utility.loadProperty(Constant.config_path);
	public static Properties prop_loct = Utility.loadProperty(Constant.locaters_path);

	@BeforeMethod
	public void Launch_browser() throws InterruptedException {

		Reports.start_test("Invoking the browser", "Using Valid name and valid password");
		Log.startTest("Invoking the browser");
		Assert.assertTrue(LaunchEnv.BrowserInvocation(prop_config.getProperty("Browser")));
		Assert.assertTrue(LaunchEnv.invokeUrl(prop_config.getProperty("App_url")));
	}

	@Test
	public void login_Actions() {
		try {
			Assert.assertTrue(LoginActions.LoginForPositiveData(prop_loct.getProperty("Login_valid_username"),
					prop_loct.getProperty("Login_valid_password")));
			Reports.pass("Login Action for Positive Data ", "Login successfully");
		} catch (Exception e) {
			Log.error("Login Action Faild due to := " + e.fillInStackTrace());
		}

	}
	
	@Test(priority=1)
	public void login_Actions1() {
		try {
			Assert.assertTrue(LoginActions.LoginForPositiveData(prop_loct.getProperty("Login_valid_username1"),
					prop_loct.getProperty("Login_valid_password1")));
			Reports.pass("Login Action for Positive Data ", "Login successfully");
		} catch (Exception e) {
			Log.error("Login Action Faild due to := " + e.fillInStackTrace());
		}

	}
	


	@Test(priority=2)
	public void login_Actions2() {
		try {
			Assert.assertTrue(LoginActions.LoginForPositiveData(prop_loct.getProperty("Login_valid_username2"),
					prop_loct.getProperty("Login_valid_password2")));
			Reports.pass("Login Action for Positive Data ", "Login successfully");
		} catch (Exception e) {
			Log.error("Login Action Faild due to := " + e.fillInStackTrace());
		}

	}
	


	@Test(priority=3)
	public void login_Actions3() {
		try {
			Assert.assertTrue(LoginActions.LoginForPositiveData(prop_loct.getProperty("Login_valid_username3"),
					prop_loct.getProperty("Login_valid_password3")));
			Reports.pass("Login Action for Positive Data ", "Login successfully");
		} catch (Exception e) {
			Log.error("Login Action Faild due to := " + e.fillInStackTrace());
		}

	}
	


	

	@AfterMethod
	public void close_browser() {
		Utility.closeBrowser();
		Reports.endTest();
		Log.endTest();
	}

	
}
