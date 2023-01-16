package All_runners;


import org.testng.annotations.Test;

import Reports.Log;





public class LoggerRunner {
  @Test
  public void methodA() {

		// will start
		Log.startTest(" Login method ");
		Log.info(" Browser invoked successfully ");
		Log.info(" provided url opened successfully ");
		Log.warn(" entered data may not be correct, please enter valid data ");
		Log.error(" clicking on submit button failed, due to it is under disabled mode ");
		Log.fatal(" data can't be entered into database because database is filled, hence stopping execution ");
		// will end
		Log.endTest();

	}

}
