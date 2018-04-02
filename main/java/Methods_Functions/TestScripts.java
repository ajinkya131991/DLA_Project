package Methods_Functions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

public class TestScripts {

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriver driver;
		SoftAssert softasserts = new SoftAssert();
		//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\jars\\geckodriver-v0.18.0-win64\\geckodriver.exe");
		//driver = new FirefoxDriver();
		
		//For Chrome
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/jars/chromedriver/chromedriver.exe");
	    driver = new ChromeDriver();
	    String url="http://staging.convertthepdf.co/success/coupons-install_success4.html?no_cache=1";
	    driver.get(url);
	    driver.manage().window().maximize();
	    
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