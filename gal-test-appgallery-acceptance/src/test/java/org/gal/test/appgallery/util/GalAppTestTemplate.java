package org.gal.test.appgallery.util;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.gal.application.pageobject.util.GalAppRunner;

public class GalAppTestTemplate extends GalAppTestBase{
	
	private static final Logger logger = org.apache.log4j.Logger.getLogger( GalAppTestTemplate.class );
	GalAppRunner webApp;
	
	@Factory(dataProviderClass=org.familysearch.idx.testframework.selenium.driver.TestBrowserDataProvider.class, dataProvider="testBrowserDataProvider")
	public GalAppTestTemplate(String testBrowserName, String testBrowserVersion, String testBrowserOS) {
		super( testBrowserName, testBrowserVersion, testBrowserOS);
		logger.info("GalAppTestTemplate instance constructed with browser values: " + testBrowserName + ", " + testBrowserVersion + ", " + testBrowserOS );
	}
	
	
	@BeforeMethod()
	public void setup() throws Exception {
		webApp = GalAppRunner.initializeApplication()
				.withDriver( getNewTestBrowserDriver() )
				.andUrl( getApplicationUrl() )
				.andStartApp();
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
	
	
	//@Test(groups={""},description = "")
	@Test(enabled=false)
	public void testTemplate() throws InterruptedException{
		logger.info("Ensuring that the test template runs.");
	}
}
