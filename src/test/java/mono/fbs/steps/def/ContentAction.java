package mono.fbs.steps.def;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import mono.fbs.utility.Utility;

public class ContentAction {
	
	private static Map<String, String> elementMap = null;
	
	private void switchToMyBookingContent(){
		Utility.switchFrame("default");
		Utility.switchFrame("content");
		Utility.switchFrame("main content");
	}
	
	private void initElementMap(){
		elementMap = new HashMap<String, String>();
		elementMap.put("Search", "//input[@value='Search']");
		elementMap.put("Delete booking", "//input[@value='Delete booking']");
	}
	
	private String lookupElement(String key){
		if(elementMap == null || elementMap.isEmpty()){
			initElementMap();
		}
		return elementMap.get(key);
	}
	
	@When("^in the myBooking content, I click (.+) button$")
	public void clickButton(String button){
		switchToMyBookingContent();
		Utility.click(lookupElement(button));
	}
	
	@When("^in the result area, I click item with reference number (.+)$")
	public void clickResult(String refNo){
		switchToMyBookingContent();
		Utility.click("//a[text()='"+refNo+"']");
	}
	
	@When("^in the main page, I click (.+) button$")
	public void deleteRecord(String button){
		switchToMyBookingContent();
		Utility.click(lookupElement(button));
	}
	
	@When("in the myBooking content, I enter Booking Ref No as (.+)$")
	public void enterBookingRef(String ref){
		switchToMyBookingContent();
		Utility.enterInput("//input[@name='bkingRefNo']", ref);
	}
	
	@Then("I will be redirected to myBooking page")
	public void redirected(){
		
	}
	
	@Then("I should see text displayed as (.+)$")
	public void searchDisplay(String searchText){
		
	}
	
	@Then("^I should see all of my booking$")
	public void i_should_see_all_of_my_booking() throws Throwable {
	    switchToMyBookingContent();
	    assertTrue("failure - No booking found", Utility.countElement("//form[@name='booking']/div/table[2]/tbody/tr") > 1);
	}
	
	@Then("^I should see information of my booking$")
	public void i_should_see_information_of_my_booking() throws Throwable {
		switchToMyBookingContent();
		assertTrue("failure - no detail found", Utility.countElement("//b[text()='Applicants Details']") > 0);
		assertTrue("failure - no detail found", Utility.countElement("//b[text()='Booking Details']") > 0);
	}
	
	@Then("^I should see booking purpose as (.+)$")
	public void i_should_see_booking_purpose(String text) throws Throwable {
		switchToMyBookingContent();
		Utility.checkElement("//input[@name='event']", text);
	}
	
	@Then("^on the top menu, I should see options (.+)$")
	public void checkAllMenu(String value){
		Utility.switchFrame("default");
		Utility.switchFrame("content");
		Utility.switchFrame("header");
		
		String[] inputList = value.split(",");
		String[] eList = Utility.getStrings("//form[@name='menu_frm']/table[2]/tbody/tr[1]/td");
		for(int i = 0; i < inputList.length; i++){
			assertEquals(inputList[i], eList[i]);
		}
	}
	
	@Then("^on the monthly calendar, row 1 should displaying (.+)$")
	public void checkAllString(String value){
		switchToMyBookingContent();
		String[] inputList = value.split(",");
		String[] eList = Utility.getStrings("//form[@name='cal']/table[2]/tbody/tr[1]/td");
		for(int i = 0; i < inputList.length; i++){
			assertEquals(inputList[i], eList[i]);
		}
	}
}
