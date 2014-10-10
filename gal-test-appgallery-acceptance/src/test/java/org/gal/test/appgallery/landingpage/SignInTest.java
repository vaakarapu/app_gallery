package org.gal.test.appgallery.landingpage;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.gal.application.pageobject.landingpage.MainLandingPage;
import org.gal.application.pageobject.util.GalAppRunner;
import org.gal.test.appgallery.util.GalAppTestBase;


public class SignInTest extends GalAppTestBase {
	
	private static final Logger logger = org.apache.log4j.Logger.getLogger( SignInTest.class );
	GalAppRunner webApp;
	
	@Factory(dataProviderClass=org.familysearch.idx.testframework.selenium.driver.TestBrowserDataProvider.class, dataProvider="testBrowserDataProvider")
	public SignInTest(String testBrowserName, String testBrowserVersion, String testBrowserOS) {
		super( testBrowserName, testBrowserVersion, testBrowserOS);
		logger.info("OverviewPageTest instance constructed with browser values: " + testBrowserName + ", " + testBrowserVersion + ", " + testBrowserOS );
	}
	
	@BeforeMethod(groups={})
	public void setup() throws Exception {
		webApp = GalAppRunner.initializeApplication().withDriver( getNewTestBrowserDriver() ).andUrl( getApplicationUrl() ).andStartApp();
		logger.info("setup() is starting with the url: " + getApplicationUrl() );
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		if ( webApp !=  null ) {
			webApp.closeApp();
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  TESTS  /////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////
	@Test(groups={},description = "")
	public void testToSignIn() throws InterruptedException{
		logger.info("Logging into the application.");
		MainLandingPage landingpage = loginToApp(webApp, driver);
		waitForPageLoad(driver);
		//Verify User Name details are displayed after Login
		Assert.assertTrue(landingpage.getHeaderSection().mDisplayName().isDisplayed());
		logger.info(landingpage.getHeaderSection().mDisplayName().getText());
	}
}