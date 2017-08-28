package mono.fbs.steps.def;

import java.util.HashMap;
import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import mono.fbs.utility.DBUtil;
import mono.fbs.utility.Utility;

public class HomeAction {
	private static Map<String, String> elementMap = null;
	
	private void initElementMap(){
		elementMap = new HashMap<String, String>();
		elementMap.put("General facility", "G");
		elementMap.put("Others", "O");
	}
	
	private String lookupElement(String key){
		if(elementMap == null || elementMap.isEmpty()){
			initElementMap();
		}
		return elementMap.get(key);
	}
	
	@Given("^a booking with reference number (.+)$")
    public void prepareBooking(String ref) {
		if(!DBUtil.hasBookingByRefNo(ref)){
			System.out.println("---Insert new record---");
			DBUtil.insertEntry(ref);
		}		
    }
	
	@When("^on the home content, I click (.+)$")
	public void selectBookOption(String option){
		Utility.switchFrame("default");
		Utility.switchFrame("content");
//		Utility.switchFrame("content");
		Utility.switchFrame("main content");
		Utility.click("//input[@name='facilityType' and @value='"+lookupElement(option)+"']");
	}
}
