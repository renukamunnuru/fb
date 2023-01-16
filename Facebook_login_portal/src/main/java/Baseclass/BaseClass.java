package Baseclass;

import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import Constants.Constant;
import Reports.Log;
import Reports.Reports;

import Utility.Utility;



public class BaseClass extends Constant  {
	public static Properties prop_config;
	public static Properties prop_loct;
	
	@BeforeSuite
	public void intiliaseFiles()
	{		
		//DOMConfigurator.configure(Constant.log4jpath);
		prop_config = Utility.loadProperty(Constant.config_path);
		prop_loct = Utility.loadProperty(Constant.locaters_path);
		//ExcelUtility.invokeExcel(Constant.TestData_excel_path);	
		//Log.info("All files are invoked sucessfully","");
	}
	@AfterTest
	public void FLushReports()
	{
		Reports.flush();
	}  

}
