package All_runners;

import org.testng.annotations.Test;

import Reports.Reports;




public class Reports_Runner {
	
	@Test
	public void a() {
		Reports.start_test("ts1","Test1 is executing"); 
		Reports.info("ts1","Test case is info method");
		Reports.endTest();
		}
	@Test
	public void b() {
		Reports.start_test("ts1","Test2 is executing"); 
		Reports.info("ts2","Test case 2 is info method");
		Reports.pass("ts3","Test case  2 is pass method");
		Reports.endTest();
		}
	@Test
	public void c() {
		Reports.start_test("ts1","Test3 is executing"); 
		Reports.info("ts2","Test case 3 is info method");
		Reports.pass("ts3","Test case 3 is pass method");
		Reports.fail("ts4","Test case 3 is fail method", "renuka.png");
		Reports.endTest();
		}

	@Test
public void d() {
		Reports.start_test("ts1","Test4 is executing"); 
		Reports.info("ts2","Test case 4 is info method");
		Reports.pass("ts3","Test case 4 is pass method");
		Reports.warn("ts4","Test case 4 is warning method");
		Reports.endTest();
		}
	@Test
	public void e() {
		Reports.start_test("Test case 5", "Test5 is executing"); 
		Reports.info("Step 1", "Test case 5 is info method");
		Reports.pass("Step 2", "Test case 5 is pass method");
		Reports.warn("Step 3", "Test case 5 is warning method");
		Reports.fatal("step 4", "Test case 5  is fatal method");
		Reports.endTest();
		Reports.flush();
		}



}
