package DLATestBase;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;



public class TestBaseDLA {
	
	public WebDriver driver;
	
	public void Landinginit() throws InterruptedException     // For Browsers
	{
		String url="http://staging.convertmypdf.co/emaillander1.html?basepath=content%2Femail&parentext=superextension.info?no_cache=1";
		//String url="https://www.msn.com/en-in/";
		
		//For Firefox
		//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\jars\\geckodriver-v0.18.0-win64\\geckodriver.exe");
		//driver = new FirefoxDriver();
		
		//For Chrome
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/jars/chromedriver/chromedriver.exe");
	    driver = new ChromeDriver();
	    
	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    driver.manage().window().maximize();
        driver.get(url);
        Thread.sleep(5000);
	}
	
	public void Thankyouinit() throws InterruptedException     // For Browsers
	{
		String url="http://staging.convertthepdf.co/success/coupons-install_success4.html?no_cache=1";
		//String url="https://www.msn.com/en-in/";
		
		//For Firefox
		//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\jars\\geckodriver-v0.18.0-win64\\geckodriver.exe");
		//driver = new FirefoxDriver();
		
		//For Chrome
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/jars/chromedriver/chromedriver.exe");
	    driver = new ChromeDriver();
	    
	    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	    driver.manage().window().maximize();
        driver.get(url);
        Thread.sleep(5000);
	}		
	
	public void closeBrowser()
	{
		driver.close();
	}
	
	

	/*public static void main(String[] args) throws InterruptedException 
	{
		TestBaseDLA test = new TestBaseDLA();
		//test.init();

	}*/

}
