package DLAScripts;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import DLATestBase.TestBaseDLA;
import Methods_Functions.CommonMethods;
import Methods_Functions.LandingPageMethods;



public class LandingPage extends TestBaseDLA {
	
	
	CommonMethods code;
	LandingPageMethods LandingMethod;
	
	@BeforeClass
	public void setUp() throws IOException, InterruptedException 
	{
	   Landinginit(); 
	  Thread.sleep(5000);
	     
	}
	
	@Test(priority=0)
	public void TC001_CheckingCodeForNeededTexts() throws InterruptedException 
	{
		Reporter.log(" ");
	    System.out.println("TESTING BELOW CHECKLIST FOR URL : --> " +driver.getCurrentUrl());
	    Reporter.log("TESTING BELOW CHECKLIST FOR URL : --> " +driver.getCurrentUrl());
	    Reporter.log(" "); 
	    code = new CommonMethods(driver);
	    LandingMethod = new LandingPageMethods(driver);
	    
	    LandingMethod.LandingPagecodechecks();
	}
	
	@Test(priority=1)
	public void TC002_ConsoleErrors() throws InterruptedException 
	{
		code.ConsoleErrors();
	}
	
	@Test(priority=2)
	public void TC003_PresenceOfjavascriptonInstallActionAndHref() throws InterruptedException 
	{
		LandingMethod.PresenceOfjavascriptonInstallActionAndHref();
	}
	
	@Test(priority=3)
	public void TC004_AllFooterLinkVerifications()
	{
		code.AllFooterLinkVerifications();
	}
	
	@Test(priority=4)
	public void TC005_Screenshots() throws IOException
	{
		LandingMethod.Screenshots();
	}
	
	
	 
	
	@AfterClass
	public void close()
	{
		closeBrowser();
	}
		
}
