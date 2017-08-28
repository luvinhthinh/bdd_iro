package mono;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import mono.fbs.utility.DBUtil;
import mono.fbs.utility.Utility;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources", glue = "mono.fbs.steps.def")
public class RunCukesTest {
	
	@org.junit.BeforeClass
	public static void init(){
		DBUtil.deleteEntry("9999999");
	}
	
	@org.junit.AfterClass
	public static void close(){
		Utility.closeBrowser();
	}
}
