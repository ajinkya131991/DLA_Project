package Methods_Functions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import DLATestBase.TestBaseDLA;

public class LandingPageMethods extends TestBaseDLA 
{ 
	WebDriver driver;
	SoftAssert softasserts = new SoftAssert();
	 
	public LandingPageMethods(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);  //this - refers current class objects
	}
	public void LandingPagecodechecks()
	{
		System.out.println(" ********** Checklists For Texts to be present or not be present in a source code ********** ");
		Reporter.log(" ********** Checklists For Texts to be present or not be present in a source code ********** ");
	    
        String stored_report = driver.getPageSource();	
	    String[] beincode = {"js/landing.js?v=1","js/settings.js","mobile-msg","viewport","chrome-webstore-item","javascript:onInstallAction()","woff","<title></title>","../img"};	    
	    String[] notbeincode = {"woff2","_success","- _child","javascript:void(0)","_suceessshould","iFrame bgpage","Media.net","Mnet"};    //Pending for partner names
	    		
	   
	    for(String check:beincode)
	    {
	    	boolean Present = stored_report.contains(check);	    
	    	
	    	if(Present == false)
	    	{
	    		Reporter.log(" "); 
	    		softasserts.assertTrue(Present);
	    		System.out.println(check+ " --- Text need to be present in the code");
	    		Reporter.log(check+ " --- Text need to be present in the code"); 
	    	}
	    }
	    
		for(String check2:notbeincode)
		 {
		  	boolean NeedtoPresent = !stored_report.contains(check2);	
	    	
	    	if(NeedtoPresent == false)
	    	{
	    		softasserts.assertTrue(NeedtoPresent);
	    		System.out.println(check2+ " --- Text should not be present in the code");
	    		Reporter.log(check2+ " --- Text should not be present in the code"); 
	    		Reporter.log(" "); 
	    	}
	    }
		softasserts.assertAll();
		Reporter.log(" "); 
	}
	
	public void PresenceOfjavascriptonInstallActionAndHref()
	{
		System.out.println(" ******** All CTA's should have 'javascript:onInstallAction();' and shouldn’t have ‘href’ in it *********** ");
		Reporter.log(" ******** All CTA's should have 'javascript:onInstallAction();' and shouldn’t have ‘href’ in it *********** ");
		
		List<WebElement> Alllinks = driver.findElements(By.tagName("a"));
        System.out.println("Links "+Alllinks.size());
        for(int i=0;i<Alllinks.size();i++)
        {
            WebElement checkBox = Alllinks.get(i);
            
            String Class = checkBox.getAttribute("class");
            String onclick = checkBox.getAttribute("onclick");
            String href = checkBox.getAttribute("href");

            try 
            {
            	if(!(Class==null)&&!(onclick==null)&&(onclick.contains("javascript:onInstallAction();"))) 
				{
            		System.out.println("javascript:onInstallAction() present for class --- " +Class);
            		Reporter.log("javascript:onInstallAction() present for class --- " +Class); 
            		if(!(href==null))
            		{
            			System.out.println(href + " --- Contains this href in class " +Class);
            			Reporter.log(href + " --- Contains this href in class " +Class);
            		}
				}			
			}
            catch (NullPointerException e)
            	{
					//e.printStackTrace();
            	}          
        }
	}
	
	public void Screenshots() throws IOException
	{
		 System.out.println(" ******** Screenshot for Different Resolutions ******** ");
		 Reporter.log(" ******** Screenshot for Different Resolutions ******** ");
	        
	        int width = driver.manage().window().getSize().getWidth();
	        int height = driver.manage().window().getSize().getHeight();        
	        int sizes[] = {1366,2048};
	            
	        for(int i=0 ; i<sizes.length; i++)
	        {
	        	
	        	int resolution = sizes[i];
	        	Dimension d = new Dimension(resolution,height);
	        	int h = d.getHeight();
	    	    int w = d.getWidth();
	    	    Reporter.log("SCREENSHOT FOR SIZE : " +w+"X"+h);
	    	    System.out.println(w+"X"+h);
	    	    String destFile = "C:\\Users\\ajinkya.bh\\workspace\\DLA\\src\\main\\java\\ScreenShot\\"+w+"X"+h+".png";
	    	    String ServerdestFile = "//172.16.140.179//File//QA//SeleniumScreenshots//DLA//"+w+"X"+h+".png";
	    	    
	    	    driver.manage().window().setSize(d);
	    	    File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	 
	    	    FileUtils.copyFile(src, new File(destFile));
	    	    FileUtils.copyFile(src, new File(ServerdestFile));
	    	    Reporter.log("<a href="+"file:" + ServerdestFile + "><img src=/screenshots/" + ServerdestFile + " style=width:100px;height:100px;/>" + ServerdestFile + "</a><br/>");
	    	    System.out.println("<a href="+"file:" + ServerdestFile + "><img src=/screenshots/" + ServerdestFile + " style=width:100px;height:100px;/>" + ServerdestFile + "</a><br/>");
	        	
	    	    d = null;
	    	    h= 0;
	    	    w = 0;
	    	    Reporter.log(" ");  	    
	        }
	        driver.manage().window().maximize();
	}
	
	


}
