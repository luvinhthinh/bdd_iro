package mono.fbs.steps.def;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import mono.fbs.utility.Utility;

public class Login {
	
	private void switchToLoginFrame(){
		Utility.switchFrame("default");
		Utility.switchFrame("content");
	}
	
	@Given("^(.+) environment$")
    public void lauchUrl(String env) {
		String url = Utility.lookupURL(env);
		if(url == null){
			System.out.println("Cannot find env : " + env);
		}else{
			Utility.goToUrl(url);
		}
    }
	
	@Given("^I successfully log in with account (.+) to (.+)$")
    public void login(String id, String env) {
		lauchUrl(env);
		
		switchToLoginFrame();
		login(id);
    }
	
	@When("^I login with account (.+)$")
	public void login(String id){
		switchToLoginFrame();
		Utility.enterInput("//input[@name='userid']", id);
		Utility.enterInput("//input[@name='pwd']", Utility.lookupCredential(id));
		Utility.click("//input[@value='Login']");
	}
	
	@Then("^on alert dialog, I click (.+)$")
	public void alertSelect(String choice){
		Utility.alertAction(choice);
	}
	
	@Then("^I should see error message (.+)$")
	public void errorMessage(String value){
		switchToLoginFrame();
		Utility.checkElement("//html/body/table[2]/tbody/tr[5]/td[2]/font/strong", value);
	}
	
	@Then("^I log out$")
	public void logout(){
		Utility.logout();
	}
	
	@When("^on the login page, I click Login$")
	public void loginClick(){
		switchToLoginFrame();
		Utility.click("//input[@value='Login']");
	}
	
	@Then("^on the main content, I should see (.+)$")
	public void checkHomepage(String value){
		switchToLoginFrame();
		Utility.switchFrame("main content");
		Utility.checkElement("//form[@name='fbsForm']/table[1]/tbody/tr[2]/td/b", value);
	}

	@When("^on the login page, I enter user id as (.+)$")
	public void loginId(String input){
		String _input = "<empty>".equalsIgnoreCase(input) ? "" : input;
		switchToLoginFrame();
		Utility.enterInput("//input[@name='userid']", _input);
	}
	
	@When("^on the login page, I enter password as (.+)$")
	public void loginPassword(String input){
		String _input = "<empty>".equalsIgnoreCase(input) ? "" : input;
		switchToLoginFrame();
		Utility.enterInput("//input[@name='pwd']", _input);
	}
	
	@Then("^I should be alerted with message (.+)$")
	public void alert(String content){
		Utility.checkAlertContent(content);
	}
	
	
}
