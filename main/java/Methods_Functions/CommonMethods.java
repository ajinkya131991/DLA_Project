package Methods_Functions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

public class CommonMethods 
{
	WebDriver driver;
	SoftAssert softasserts = new SoftAssert();
	 
	public CommonMethods(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);  //this - refers current class objects
	}
	
	public void ConsoleErrors()
	{
		System.out.println(" ********** No console errors should be present. ********** ");
		Reporter.log(" ********** No console errors should be present. ********** ");
		
		Logs log = driver.manage().logs();
    	LogEntries entries = log.get(LogType.BROWSER);
    	List<LogEntry> list = entries.getAll();
    	for (int i = 0; i < list.size(); i++) 
    	{
    		LogEntry e = list.get(i);
    		String newlog = e.toString();
		
    		if((e.getLevel().equals(Level.SEVERE)) )	
    		{    			  
    			
    			System.out.println(newlog);
    			Reporter.log(newlog); 
    			Reporter.log(" ");    			
    		}	   		
    	}
	}
		
	public void AllFooterLinkVerifications()
	{
		 System.out.println(" ******** All footer links should be checked to ensure links are mapped correctly and are opening in same window.  ******** ");
		 Reporter.log(" ******** All footer links should be checked to ensure links are mapped correctly and are opening in same window.  ******** ");
	        
	        
		    List<WebElement> Alllinks = driver.findElements(By.tagName("a"));
	        HashMap<String, String> hm = new HashMap<String,String>();
		    
		    hm.put("EULA","eula.html");
		    hm.put("Privacy Policy","PrivacyPolicy.html");
		    hm.put("Endorsement Disclaimer and DMCA Policy","EndorsementPolicy.html");
		    hm.put("Contact Us","contact-us.html");
		    hm.put("Uninstall","uninstall.html");
		   	    
		    for(Map.Entry m:hm.entrySet())
			{ 
		    	String result = (String) m.getKey();
		    	String value = (String) m.getValue();
		    	for(int i=0;i<Alllinks.size();i++)
		    	{
		    		WebElement checkBox = Alllinks.get(i);
		            String href = checkBox.getAttribute("href");
		            String newtab = checkBox.getAttribute("target");
		            String text = checkBox.getText();
		            //System.out.println(href);
		            //System.out.println(t);
		            
		            if((!(href==null)&&href.contains(value)))
		            {
		            	if(text.equals(result))
		            	{	
		            		System.out.println("Direction for "+ result + " is kept as link " + href);
		            		Reporter.log("Direction for "+ result + " is kept as link " + href); 
		            	}
		            	
		            	if((!(href==null)&&!newtab.equals("_blank")))
			            {
			            	System.out.println("****  Above link is opening in same tab (Please fix this issue)  ****");
			            	Reporter.log("****  Above link is opening in same tab (Please fix this issue)  ****");
			            }
		            }	            	      
		    	}	    		    	    			     
			  }
	       }
	
	
	
	
	

}
