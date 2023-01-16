package Reports;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import Constants.Constant;



public class Log {
	public static Logger logger = Logger.getLogger(Reports.class.getName());

	public static void startTest(String test_case_name) {
		DOMConfigurator.configure(Constant.log4jpath);
		logger.info("********************( " + test_case_name + " )**********************");
		logger.info("===============================================================");
	}

	public static void info( String description) {
		logger.info( description);
	}

	public static void error( String description) {
		logger.error( description);
	}

	public static void warn( String description) {
		logger.warn( description);
	}

	public static void fatal( String description) {
		logger.fatal(  description);
	}

	public static void endTest() {

		logger.info("***************************************************************");
		logger.info("===============================================================");
	}

}
