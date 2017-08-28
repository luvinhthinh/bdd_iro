package mono.fbs.steps.def;

import java.util.HashMap;
import java.util.Map;

import cucumber.api.java.en.When;
import mono.fbs.utility.Utility;

public class MenuAction {
	
	private static Map<String, String> urlMap = null;
	
	private void initUrlMap(){
		urlMap = new HashMap<String, String>();
		urlMap.put("myBooking", "//a[@href='javascript:myBookings()']");
	}
	
	private String lookupURL(String key){
		if(urlMap == null || urlMap.isEmpty()){
			initUrlMap();
		}
		return urlMap.get(key);
	}
	
	
	@When("^on the top menu, I click on (.+) link$")
	public void clickLink(String href){
		Utility.switchFrame("default");
//		Utility.switchFrame("content");
		Utility.switchFrame("content");
		Utility.switchFrame("header");
		Utility.click(lookupURL(href));
	}
}
