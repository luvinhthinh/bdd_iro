package mono.fbs.utility;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Utility {
	private static long TIMEOUT = 5;
	
	private static WebDriver driver = null; 
	private static Map<String, String> urlMap;
	private static Map<String, String> credentialMap;
	private static Map<String, String> frameMap;
	
	private static void initFrameMap(){
		frameMap = new HashMap<String, String>();
		frameMap.put("content", "//frame[@name='CONTENTS']");
		frameMap.put("main content", "//frame[@name='CONTENT']");
		frameMap.put("header", "//frame[@name='HEADER']");
	}
	
	private static void initUrlMap(){
		urlMap = new HashMap<String, String>();
		urlMap.put("IRO local", "http://monovm:8080/IRO");
		urlMap.put("IRO sit", "https://sit-aces.nus.edu.sg/IRO/");
		urlMap.put("Visits - Add", "/IRO/ExchangeStaffServlet?actionParam=addPageForward");
		urlMap.put("FBS local", "http://monovm:8080/IRO");
		urlMap.put("FBS sit", "https://sit-aces.nus.edu.sg/fbs/jsp/index.jsp");
		urlMap.put("logout", "https://sit-aces.nus.edu.sg/fbs/Logout");
	}
	
	private static void initCredentialMap(){
		credentialMap = new HashMap<String, String>();
		credentialMap.put("", "");
		credentialMap.put("CCELVT", "abc");
	}
	
	public static String lookupURL(String env){
		if(urlMap == null || urlMap.isEmpty()){
			initUrlMap();
		}
		return urlMap.get(env);
	}
	
	public static String lookupFrame(String frame){
		if(frameMap == null || frameMap.isEmpty()){
			initFrameMap();
		}
		return frameMap.get(frame);
	}
	
	public static String lookupCredential(String id){
		if(credentialMap == null || credentialMap.isEmpty()){
			initCredentialMap();
		}
		return credentialMap.get(id);
	}
	
	private static WebDriver getDriver(){
		if(driver == null ){
			System.setProperty("webdriver.gecko.driver","D:\\geckodriver-v0.18.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		return driver;
	}
	
	public static void goToUrl(String url){
		getDriver().get(url);
	}
	
	public static void enterInput(String xpath, String value){
		WebDriver driver = getDriver();
		WebElement element = (new WebDriverWait(driver, TIMEOUT))
				  .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		element.sendKeys(value);
	}
	
	public static void click(String xpath){
		WebDriver driver = getDriver();
		WebElement element = (new WebDriverWait(driver, TIMEOUT))
				  .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		element.click();
	}
	
	public static void switchFrame(String frame){
		WebDriver driver = getDriver();
		if("default".equals(frame)){
			driver.switchTo().defaultContent();
		}else{
			String xpath = lookupFrame(frame);
			if(xpath != null){
				driver = driver.switchTo().frame(driver.findElement(By.xpath(xpath)));
			}else{
				System.out.println("No xpath found.");
			}
		}
	}
	
	public static void checkElement(String xpath, String value){
		WebDriver driver = getDriver();
		WebElement element = (new WebDriverWait(driver, TIMEOUT))
				  .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		String text = "";
		if("input".equals(element.getTagName())){
			text = element.getAttribute("value");
		}else{
			text = element.getText();
		}
		
		assertEquals(text, value);
	}
	
	public static void checkAlertContent(String content){
		WebDriver driver = getDriver();
		Alert alert = (new WebDriverWait(driver, TIMEOUT))
				  .until(ExpectedConditions.alertIsPresent());
		assertEquals(alert.getText(), content);
	}
	
	public static void alertAction(String action){
		WebDriver driver = getDriver();
		Alert alert = (new WebDriverWait(driver, TIMEOUT))
				  .until(ExpectedConditions.alertIsPresent());
		if("OK".equalsIgnoreCase(action)){
			alert.accept();
		}else if("Cancel".equalsIgnoreCase(action)){
			alert.dismiss();
		}
		
	}
	
	public static int countElement(String xpath){
		return getElements(xpath).size();
	}
	
	public static void closeBrowser(){
		getDriver().close();
	}
	
	public static void logout(){
		getDriver().get(lookupURL("logout"));
	}
	
	public static List<WebElement> getElements(String xpath){
		WebDriver driver = getDriver();
		return (new WebDriverWait(driver, TIMEOUT))
				  .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath))); 
	}
	
	public static String[] getStrings(String xpath){
		List<WebElement> eList = getElements(xpath);
		List<String> stringList = new ArrayList<String>();
		for(WebElement e : eList){
			stringList.add(e.getText());
		}
		return stringList.toArray(new String[stringList.size()]);
	}
}
