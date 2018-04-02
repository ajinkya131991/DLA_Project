package Methods_Functions;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

public class ThanyouPageMethods 
{
	WebDriver driver;
	SoftAssert softasserts = new SoftAssert();
	 
	public ThanyouPageMethods(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);  //this - refers current class objects
	}
	
	public void Thankyoucodechecks()
	{
		System.out.println(" ********** Checklists For Texts to be present or not be present in a source code ********** ");
		Reporter.log(" ********** Checklists For Texts to be present or not be present in a source code ********** ");
	    
        String stored_report = driver.getPageSource();	
	    String[] beincode = {"viewport","woff","<title>Thank you</title>","promote.js","autocompleteAPI="};	    
	    String[] notbeincode = {"javascript:onInstallAction()","js/landing.js?v=1","js/settings.js","woff2","javascript:void(0)","Media.net","Mnet"};    //Pending for partner names
	    		
	   
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
	
	public void Screenshots() throws IOException
	{
		 System.out.println(" ******** Screenshot for Different Resolutions ******** ");
		 Reporter.log(" ******** Screenshot for Different Resolutions ******** ");
	        
	        int width = driver.manage().window().getSize().getWidth();
	        int height = driver.manage().window().getSize().getHeight();        
	        int sizes[] = {1366,2048};
	        Dimension d; 
	        for(int i=0 ; i<sizes.length; i++)
	        {	        	
	        	int resolution = sizes[i];
	        	d = new Dimension(resolution,height);
	        	int h = d.getHeight();
	    	    int w = d.getWidth();
	    	    Reporter.log("SCREENSHOT FOR SIZE : " +w+"X"+h);
	    	    System.out.println(w+"X"+h);
	    	    String destFile = "C:\\Users\\ajinkya.bh\\workspace\\DLA\\src\\main\\java\\ScreenShot\\Thankyoupage\\"+w+"X"+h+".png";
	    	    String ServerdestFile = "//172.16.140.179//File//QA//SeleniumScreenshots//DLA//ThankyouPage//"+w+"X"+h+".png";
	    	    
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
	
	public void Sv7Sv9Checks() throws InterruptedException
	{
		   System.out.println(" ******** Validating Domain Name for Result Page ******** ");
		   Reporter.log(" ******** Validating Domain Name for Result Page ******** ");
		 
		    WebElement element = driver.findElement(By.xpath("//input[@id='homeSearch'][@class='txt-box_success']"));
			element.click();
			element.sendKeys("car");
			WebElement search = driver.findElement(By.xpath("//input[@type='submit'][@class='submit-button']"));
			Thread.sleep(3000);
			search.click();
			
			String currentURL = driver.getCurrentUrl();
			System.out.println("URL displayed on result page is : " + currentURL);
			Reporter.log("URL displayed on result page is : " + currentURL);	
						
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String domain = (String) js.executeScript("return document.domain");
			
			System.out.println("Tab belongs to domain  : " + domain);
			Reporter.log("Tab belongs to domain  : " + domain);	
			driver.navigate().back();
			Thread.sleep(3000);
			Assert.assertEquals("in.search.yahoo.com",domain);											
	}
	
	public void imageURLcheck() throws InterruptedException
	{   
	    	System.out.println(" ******** Validating Images for the format of URL used in the code ******** ");
		    Reporter.log(" ******** Validating Images for the format of URL used in the code ******** ");
		    
		    String stored_report = driver.getPageSource();		 
	        int Imageextentioncount = 0; 
	        int ImageURLCount = 0; 
	        boolean imagecountandurlmatches = false;
	        for (String word : stored_report.split(" ")) 
	        {        
	        	//System.out.println(word);
	            if (word.contains(".png")||word.contains(".jpg")) 
	            {
	            	Imageextentioncount++; 
	            	Thread.sleep(2000);
	                System.out.println(Imageextentioncount);
	            } 
	            
	            if (word.contains("/success/img")) 
	            {
	            	ImageURLCount++;  
	            	Thread.sleep(2000);
	                System.out.println(ImageURLCount);
	            }             
	        }
	        if(Imageextentioncount==ImageURLCount)
	        {
	        	imagecountandurlmatches = true;
	        	System.out.println("All images has a format as '/success/img/'");
	        	Reporter.log("All images has a format as '/success/img/'");
	        }
	        else
	        {
	        	Assert.assertTrue(imagecountandurlmatches);
	        	Thread.sleep(2000);
	        }	        
	}
	
	public void brokenLinks()
	{
		System.out.println(" ******** Validating Broken Links in a Page ******** ");
	    Reporter.log(" ******** Validating Broken Links in a Page ******** ");
	    
		String homePage = driver.getCurrentUrl();
	    String sametab = driver.getCurrentUrl()+"//#";   
	    String url1 = "";
	    HttpURLConnection huc = null;
	    int respCode = 200;
	    int count = 0;
	    
	    List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total links present on this sites are:  "+links.size());
        Reporter.log("Total links present on this sites are:  "+links.size());
        
        Iterator<WebElement> it = links.iterator();        
        while(it.hasNext())
        {            
        	count++;
        	url1 = it.next().getAttribute("href");
            if(url1 == null || url1.isEmpty())
            {
            		System.out.println(url1 + " : URL is either not configured for anchor tag or it is empty");
            		Reporter.log(url1 + " : URL is either not configured for anchor tag or it is empty");
            		
                    continue;
            }            
          
            if(url1 == sametab)
           	{
            	System.out.println(url1 + " : URL is directing to same page URL");
            	Reporter.log(url1 + " : URL is directing to same page URL");
            	
           	}           
            try {
                huc = (HttpURLConnection)(new URL(url1).openConnection());
                huc.setConnectTimeout(2000);
                huc.setRequestMethod("HEAD");                
                huc.connect();                
                respCode = huc.getResponseCode();
                
                if(respCode >= 400)
                {
                    System.out.println(url1+" :  is a Broken link");
                    Reporter.log(url1+" :  is a Broken link");             
                }                   
            } 
            catch (MalformedURLException e) 
            {                
            e.printStackTrace();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }            
        }
        
        Reporter.log(" ");
        System.out.println("Total Links checked : "+count);
        Reporter.log("Total Links checked : "+count);
	}
	
	public void FooterLinks()
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
	
	public void CheckPrefixSlashForFooters() throws InterruptedException
	{		    
		    String stored_report = driver.getPageSource();	    
		    int hrefcount = 0;   
	        boolean hrefcountinpagesource = false;
	        for (String word : stored_report.split(" ")) 
	        {        
	        	//System.out.println(word);
	            if (word.contains("href=\"/")) 
	            {
	            	hrefcount++; 
	            	Thread.sleep(2000);
	                //System.out.println(hrefcount);
	            }                           
	        }
	        
	        List<WebElement> Alllinks = driver.findElements(By.tagName("a"));
	        int hrefinDOM = Alllinks.size();
	        //System.out.println(hrefinDOM);
	       
	        boolean hrefPresence = false;
	        if(hrefcount==hrefinDOM)
	        {
	        	hrefPresence = true;
	        	System.out.println("All Footer links starts with Prefix forward slash ");
	        	Reporter.log("All Footer links starts with Prefix forward slash ");
	        }
	        else
	        {
	        	Assert.assertTrue(hrefPresence);
	        	Thread.sleep(2000);
	        }	     
	}
	
	
	
	
	
	
	
	
	
}
