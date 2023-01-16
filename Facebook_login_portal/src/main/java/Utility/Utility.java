package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import App_Module.LaunchEnv;
import Constants.Constant;
import Reports.Log;
import Reports.Reports;


public class Utility extends LaunchEnv{
	public static FileInputStream fis;
	public static Properties prop;
	public static WebElement element;
	public static String filepath;

	/**
	 * @author Piyush
	 * @param filepath
	 * @return
	 * @this method is demanding for filepath and return and the properties Provide
	 *       file will be read all the properties (key=value) returns all the
	 *       properties in one variables
	 */

	public static Properties loadProperty(String filepath) {
		prop = null;
		try {
			fis = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		prop = new Properties();

		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}

	// -------------------------------------------------------------------------------------------------
	public static WebElement getLocator(String Key) throws InterruptedException {
		element = null;
		Properties proper = Utility.loadProperty(Constant.locaters_path);
		String value = proper.getProperty(Key);

		if (Key.endsWith("_id")) {
			element = driver.findElement(By.id(value));
			HighlighElement(element);
			Log.info(Key + "   and its value is	" + value + "is returned the element ");
		} else if (Key.endsWith("_xpath")) {
			element = driver.findElement(By.xpath(value));
			HighlighElement(element);
			Log.info(Key + "   and its value is	" + value + "is returned the element ");
		} else if (Key.endsWith("_name")) {
			element = driver.findElement(By.name(value));
			HighlighElement(element);
			Log.info(Key + "   and its value is	" + value + "is returned the element ");

		} else {
			Log.info("provided value is not available in the properties ...... ");
		}

		return element;

	}

	private static void HighlighElement(WebElement element2) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(500);
		js.executeScript("arguments[0].setAttribute('style', 'background:brown;, border:2px dashed blue'); ", element);

		Thread.sleep(300);
	}
	// --------------------------------------------------
	// Return List

	public static List<WebElement> ElementCollection(String xpath) {
		List<WebElement> collections = driver.findElements(By.xpath(xpath));
		return collections;

	}
	// ------------------------------------------------------------------------------------------------

	public static void closeBrowser() {
		try {
			driver.quit();
			Log.info("browser closed successfully");
		} catch (Exception e) {
			Reports.fail("closing browser", e.toString(), " ");
			Log.info("browser closing failed due to ....." + e.fillInStackTrace());
		}
	}

	/// ----------------------------------------------------------------------------------------------
	// MULTIPLE WAIT METHODS

	public static void waitImplicit() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	// -----------------------------------------------------
	public static void Elementwait() {
		Log.info("waiting for page to load");
		WebDriverWait wait = new WebDriverWait(driver, 120);
		FluentWait<WebDriver> explicitwait = null;
		explicitwait.withTimeout(90, TimeUnit.SECONDS);
		explicitwait.pollingEvery(2, TimeUnit.SECONDS);
		explicitwait.ignoring(NoSuchElementException.class);

	}
	// ----------------------------------------------------

	public static void waitForElementClickable() throws InterruptedException {
		Log.info("waiting for the element to be clickable");
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.withTimeout(60, TimeUnit.SECONDS);
		wait.pollingEvery(2, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(InvalidSelectorException.class);
		wait.ignoring(WebDriverException.class);
		wait.until(ExpectedConditions.elementToBeClickable(Utility.getLocator("Key")));

	}

	// ---------------------------------
	/*
	 * This function will dynamically wait for text on element to be present
	 * 
	 */
	public static void waitForTextToBeDisplayed(String Key) throws InterruptedException

	{
		Log.info("waiting for the element to be visible");
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.withTimeout(60, TimeUnit.SECONDS);
		wait.pollingEvery(2, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(InvalidSelectorException.class);
		wait.ignoring(WebDriverException.class);
		wait.until(ExpectedConditions.elementToBeClickable(Utility.getLocator("Key")));

	}

	// -------------------------------------------------
	public static void getCurrentDate() {
		// String curdate = null
		Date date = new Date();
		System.out.println(date);
		// return curdate;
	}

	// --------------------------------------------------------------------
	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
		Date date = new Date();
		/*
		 * String founddate = dateFormat.format(date); String[] parts =
		 * founddate.split(" "); String[] appenderpart1 = parts[0].split("/"); String
		 * appender = appenderpart1[1] +"-" + appenderpart1[2] + "-"+ appenderpart1[0];
		 * return appender;
		 */

		// method-2
		String founddate = dateFormat.format(date);
		Log.info(dateFormat.format(date));
		return founddate;

	}

	// -------------------------------------
	public static String getDatetime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
		Date date = new Date();
		String founddate = dateFormat.format(date);
		Log.info(dateFormat.format(date));
		return founddate;

	}

	// ----------------------------------------------------------------------
	public static String getfailScreenshot() {
		try {
			filepath = null;
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			filepath = System.getProperty("user.dir") + "\\Screenshots\\FailureScreenshots\\" + Utility.getDatetime()
					+ System.currentTimeMillis() + ".png";
			FileUtils.copyFile(file, new File(filepath));

		} catch (IOException e) {
			Reports.fail(" ", e.toString(), " ");
			e.printStackTrace();
		}
		return filepath;
	}
	public static String getSucessScreenshot() {
		try {
			filepath = null;
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			filepath = System.getProperty("user.dir") + "\\Screenshots\\SucessScreenshots\\" + Utility.getDatetime()
					+ System.currentTimeMillis() + ".png";
			FileUtils.copyFile(file, new File(filepath));

		} catch (IOException e) {
			Reports.fail(" ", e.toString(), " ");
			e.printStackTrace();
		}
		return filepath;
	}

	// ----------------------------------------------------------- i have some doubt
	// on this topic how to delete
	public static void FrameworkcleanUp(int daysBack, String dirway) {
		File directory = new File(dirway);
		if (directory.exists()) {
			File[] listFiles = directory.listFiles();
			long purgeTime = System.currentTimeMillis() - (daysBack * 24 * 60 * 60 * 1000);
			for (File listFile : listFiles) {
				if (listFile.lastModified() < purgeTime) {
					if (!listFile.delete()) {
						System.err.println("UNABLE TO DELETE THE FILE:; " + listFile);
					}
				}
			}
		}

	}
}
