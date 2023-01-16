package App_Module;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Baseclass.BaseClass;
import Constants.Constant;
import Reports.Log;
import Reports.Reports;
import Utility.Utility;


public class LaunchEnv extends BaseClass{
	public static WebDriver driver;
	public static boolean flag;

	public static boolean BrowserInvocation(String Browser) throws InterruptedException {
		Browser = "chrome";
		flag = false;
		try {
			if (Browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", Constant.chromepath);
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				Utility.waitImplicit();
			}

			flag = true;
			Log.info("Launching browser sucessfully " + Browser);
			Reports.pass("LAunching browser chrome", "Successfully LAunched");
		} catch (Exception e) {
			Log.info("failing launching Browser");
			Reports.fail("Browser Invocation", Browser, " failed due to " + e.toString());
		}

		return flag;
	}

	public static boolean invokeUrl(String url) {
		flag = false;
		System.out.println(" Present application url is " + url);
		try {
			driver.get(url);
			flag = true;
			Log.info(url+ " invoked successfully");
			Reports.pass("Application Invocation", url + " invoked succesfully ");
		} catch (Exception e) {
			Log.info("failing to invoke due to "+ e.fillInStackTrace().toString());
			Reports.fail("Application Invocation", url, " failed due to " + e.toString());
		}
		return flag;
	}

}
