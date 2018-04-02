package DLAScripts;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import DLATestBase.TestBaseDLA;
import Methods_Functions.CommonMethods;
import Methods_Functions.LandingPageMethods;
import Methods_Functions.ThanyouPageMethods;



public class ThankYouPage extends TestBaseDLA {
	
	
	CommonMethods code;
	ThanyouPageMethods ThankyouMethod;
	
	@BeforeClass
	public void setUp() throws IOException, InterruptedException 
	{
	  Thankyouinit(); 
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
	    ThankyouMethod = new ThanyouPageMethods(driver);
	    
	    ThankyouMethod.Thankyoucodechecks();
	}
	
	@Test(priority=1)
	public void TC002_ConsoleErrors() throws InterruptedException 
	{
		code.ConsoleErrors();
	}
	
	@Test(priority=2)
	public void TC003_Screenshots() throws IOException
	{
		ThankyouMethod.Screenshots();
	}
	
	@Test(priority=3)
	public void TC004_Sv7Sv9Checks() throws IOException, InterruptedException
	{
		ThankyouMethod.Sv7Sv9Checks();
	}
	
	@Test(priority=4)
	public void TC005_ImageURLCheck() throws IOException, InterruptedException
	{
		ThankyouMethod.imageURLcheck();
	}
	
	@Test(priority=5)
	public void TC005_ValidatingBrokenLinks() throws IOException, InterruptedException
	{
		ThankyouMethod.brokenLinks();
	}
	
	@Test(priority=6)
	public void TC006_VerifyFooterLinks() throws IOException, InterruptedException
	{
		ThankyouMethod.FooterLinks();
	}
	
	@Test(priority=7)
	public void TC007_CheckPrefixSlashForFooters() throws IOException, InterruptedException
	{
		ThankyouMethod.CheckPrefixSlashForFooters();
	}

	@AfterClass
	public void close()
	{
		closeBrowser();
	}
		
}
