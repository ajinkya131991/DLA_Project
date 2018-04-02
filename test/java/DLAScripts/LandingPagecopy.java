package DLAScripts;

import java.io.File;
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
import org.testng.Reporter;


public class LandingPagecopy {

	public static WebDriver driver;
	public static void main(String[] args) throws Exception 
	{
		String url="http://staging.check-maps.org/coupons_lander1.html?basepath=content%2Fcoupons&parentext=superextension.info?no_cache=1";
		//String url = "https://www.msn.com/en-in/";
		//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\jars\\geckodriver-v0.18.0-win64\\geckodriver.exe");
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/jars/chromedriver/chromedriver.exe");
	    driver = new ChromeDriver();
		//driver = new HtmlUnitDriver();
	    
	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    driver.manage().window().maximize();
        driver.get(url);
        Thread.sleep(5000);
        
        
        System.out.println("  ------- All CTAs and hyperlinks should have  / javascript:onInstallAction() / and shouldn’t have / href / in it.  --------");
   
        

        //******************************1st Script
        
        List<WebElement> Alllinks = driver.findElements(By.tagName("a"));
        System.out.println("Links "+Alllinks.size());
        for(int i=0;i<Alllinks.size();i++)
        {
            WebElement checkBox = Alllinks.get(i);
            
            String Class = checkBox.getAttribute("class");
            String onclick = checkBox.getAttribute("onclick");
            String href = checkBox.getAttribute("href");
            //System.out.println(href);

            try 
            {
            	if(!(Class==null)&&!(onclick==null)&&(onclick.contains("javascript:onInstallAction();"))) 
				{
            		System.out.println("javascript:onInstallAction() present for class" +Class);
            		if(!(href==null))
            		{
            			System.out.println("Contains href in class " +href);
            		}
				}			
			}
            catch (NullPointerException e)
            	{
					//e.printStackTrace();
            	}
            
            if((!(Class==null)&&(!(href==null))))
            	{
            		System.out.println("Contains href in class " +href);
            	}            
        }
        
        
        
        // ***************************2nd Script
        System.out.println("  ------- All footer links should be checked to ensure links are mapped correctly and are opening in same window.  --------");
        
        
         
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
	            	}
	            	
	            	if((!(href==null)&&!newtab.equals("_blank")))
		            {
		            	System.out.println("****  Above link is opening in same tab (Please fix this issue)  ****");
		            }
	            }	            	      
	    	}	    		    	    			     
		}
	    
	    
	 // ***************************3rd Script
        System.out.println("  ------- No console errors should be present.  --------");
	    
        //////// For Chrome  
        
        Logs log = driver.manage().logs();
    	LogEntries entries = log.get(LogType.BROWSER);
    	List<LogEntry> list = entries.getAll();
    	for (int i = 0; i < list.size(); i++) {
    		LogEntry e = list.get(i);
    		if((e.getLevel().equals(Level.SEVERE)) )	
    		{
    			System.out.println(e);
    		}		    			
    	}
    	
       System.out.println("  ------- Checklists For Texts to be present or not be present in a source code  --------");
	    
        String stored_report = driver.getPageSource();	
	    String[] beincode = {"js/landing.js?v=1","js/settings.js","mobile-msg","viewport","chrome-webstore-item","javascript:onInstallAction()","woff","<title></title>","../img"};	    
	    String[] notbeincode = {"woff2","_success","- _child","javascript:void(0)","_suceessshould","iFrame bgpage","Media.net","Mnet"};    //Pending for partner names
	    		
	   
	    for(String check:beincode)
	    {
	    	boolean Present = stored_report.contains(check);	    
	    	
	    	if(Present == false)
	    	{
	    		System.out.println(check+ " --> Text need to be present in the code");
	    	}
	    }
	    
		for(String check2:notbeincode)
		 {
		  	boolean NeedtoPresent = !stored_report.contains(check2);	
	    	
	    	if(NeedtoPresent == false)
	    	{
	    		System.out.println(check2+ " --> Text should not be present in the code");
	    	}
	    }
        
		
		// ***************************4th Script
        System.out.println("  ------- Screenshot for Different Resolutions  --------");
        
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();        
        int sizes[] = {1366,2048};
            
        for(int i=0 ; i<sizes.length; i++)
        {
        	
        	int resolution = sizes[i];
        	Dimension d = new Dimension(resolution,height);
        	int h = d.getHeight();
    	    int w = d.getWidth();
    	    //Reporter.log("SCREENSHOT FOR SIZE : " +w+"X"+h);
    	    //System.out.println(w+"X"+h);
    	    String destFile = "C:\\Users\\ajinkya.bh\\workspace\\DLA\\src\\main\\java\\ScreenShot\\"+w+"X"+h+".png";
    	    String ServerdestFile = "//172.16.140.179//File//QA//SeleniumScreenshots//DLA//"+w+"X"+h+".png";
    	    
    	    driver.manage().window().setSize(d);
    	    File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	 
    	    FileUtils.copyFile(src, new File(destFile));
    	    FileUtils.copyFile(src, new File(ServerdestFile));
    	    //Reporter.log("<a href="+"file:" + ServerdestFile + "><img src=/screenshots/" + ServerdestFile + " style=width:100px;height:100px;/>" + ServerdestFile + "</a><br/>");
    	    //System.out.println("<a href="+"file:" + ServerdestFile + "><img src=/screenshots/" + ServerdestFile + " style=width:100px;height:100px;/>" + ServerdestFile + "</a><br/>");
        	
    	    d = null;
    	    h= 0;
    	    w = 0;
    	    //Reporter.log(" ");
    	    
        }
        
        
        
	    
        driver.close();		
	}
}
