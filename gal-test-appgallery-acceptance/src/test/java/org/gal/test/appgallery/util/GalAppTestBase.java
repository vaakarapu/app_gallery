package org.gal.test.appgallery.util;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.familysearch.idx.testframework.selenium.IdxSeleniumTestBase;
import org.familysearch.idx.testframework.selenium.JQuery;
import org.gal.application.pageobject.landingpage.HeaderSection;
import org.gal.application.pageobject.landingpage.MainLandingPage;
import org.gal.application.pageobject.signinpage.MainSignInPage;
import org.gal.application.pageobject.util.GalAppRunner;
import org.gal.test.appgallery.landingpage.SignInTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;


public class GalAppTestBase extends IdxSeleniumTestBase {
	
	private static final Logger logger = org.apache.log4j.Logger.getLogger( GalAppTestBase.class );
	public static String DEFAULT_START_URL = "https://familysearch.org/products/";
	
	public GalAppTestBase(String testBrowserName, String testBrowserVersion, String testBrowserOs) {
		super(testBrowserName, testBrowserVersion, testBrowserOs);
	}
	
	@Override
	public void setDefaultConfiguration() {
		System.out.println("\n\nSetting IdxMarketingTestBase default configuration");	

		setApplicationUrl(DEFAULT_START_URL);//("https://beta.familysearch.org/indexing");
		setTestBrowserLocation("local"); 		
		setSauceUserName("sgubbala");
		setSauceAccessKey("c8601f0c-6a7f-4c7c-96b2-ffa95d017ade");
		
		// Don't want override values that have already been set by the @factory annotation.
		if (getTestBrowserName() == null || getTestBrowserName().equals("") ) {
			setTestBrowserName("firefox");
			//setTestBrowserName("Chrome");
			
			// Only set version and OS if the browser has not been specified.
			if (getTestBrowserVersion() == null || getTestBrowserVersion().equals("") ) {
				setTestBrowserVersion("25");
			}
			if ( getTestBrowserOS() == null || getTestBrowserOS().equals("")  ) {
				setTestBrowserOS("Windows 7"); 
			}
		}
	}
	
	/**
	 * Get the application's base (start) url.
	 * @return
	 */
	@Override
	public String getApplicationUrl() {
		// NOTE:  This checks to see if the applicationUrl is null. If it is, the applicationUrl is
		// set to be the default url. But the url has a default value anyway in the configureTest method.
		// TODO: Is this good enough?  Should the default url go away?
		if ( applicationUrl == null ) {
			applicationUrl = DEFAULT_START_URL;
		}
		
		return applicationUrl;
	}

	public void waitForPageLoad(WebDriver driver) {
		JQuery.waitForPageLoad(driver);
	}
	public void setFocus(WebElement element){

		if("input".equals(element.getTagName())){
			element.sendKeys("");
		} 
		else{
			new Actions(driver).moveToElement(element).perform();
		}

	}

	public void waitForPageLoaded(WebDriver driver,String title) {
		WebDriverWait wait = new WebDriverWait(driver, 60);  
		wait.until(ExpectedConditions.titleContains(title));
	} 
	public void waitForVisibilityOfElement(WebDriver driver,By Value) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 60);  
		wait.until(ExpectedConditions.visibilityOfElementLocated(Value));
		//pauseTest(8);
	}

	public void waitForElementInvisibility(WebDriver driver,By byValue){
		WebDriverWait wait = new WebDriverWait(driver, 90);  
		wait.until(ExpectedConditions.invisibilityOfElementLocated(byValue));
	}
	
	public void waitForElementPresent(WebDriver driver,By byvalue){
		WebDriverWait wait = new WebDriverWait(driver, 90);  
		wait.until(ExpectedConditions.presenceOfElementLocated(byvalue));
	}

	public void waitForElementClickable(WebDriver driver,By byvalue){
		WebDriverWait wait = new WebDriverWait(driver, 60);  
		wait.until(ExpectedConditions.elementToBeClickable(byvalue));
	}

	public boolean isElementDisplayed(WebElement element){
		try {
			return null != element && element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	public boolean isElementPresent(WebElement element){
		try {
			return null != element && element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isElementPresent(By byValue ,WebDriver driver){

		// This will can show an error.  The element is there,
		// but it might not be displayed.
		try {
			driver.findElement(byValue);
			return true;
		}catch (NoSuchElementException e){
			return false;
		}
	}

	public MainLandingPage loginToApp(GalAppRunner webApp,WebDriver driver) throws InterruptedException {
		HashMap testDataMap = (HashMap) XmlReader.getXmlReader().associatedWith( SignInTest.class).andUseAlias("UserInfo", UserInfo.class).andRetrieveContent();
		UserInfo testUser = (UserInfo) testDataMap.get("validTestUser");
		signIn(webApp, driver, testUser.getUserName(), testUser.getPassword());
		//webApp = WebIndexingAppRunner.initializeApplication().withDriver( driver ).andUrl(DEFAULT_START_URL).andStartAppWithExpriment("batchProgressUpdateEx");
		MainLandingPage landingPage = webApp.getMainLandingPage();
		//disableExperiment(driver, "restrictBetaUsersEx");
		return landingPage;

	}

	
	public void signIn(GalAppRunner webApp,WebDriver driver,String userName, String password ) throws InterruptedException {
		MainLandingPage mainLandingPage=webApp.getMainLandingPage();
		MainSignInPage mainSignInPage = webApp.getMainSignInPage();	
		pauseTest(6);
			if(isElementPresent(HeaderSection.getBySignInLink(),driver))
				driver.findElement(HeaderSection.getBySignInLink()).click();
			else
				logger.info("Sign in link is not displayed");

//			waitForPageLoad(driver);
			pauseTest(5);
			mainSignInPage.getSignInBoxSection().mUserName().sendKeys(userName);
			mainSignInPage.getSignInBoxSection().mPassword().sendKeys(password);
			mainSignInPage.getSignInBoxSection().mSinInButton().submit();
			
			// TODO: You shouldn't have to initialize the application because it is already running.
			webApp = GalAppRunner.initializeApplication().withDriver( driver ).andUrl(DEFAULT_START_URL).andStartApp();
			waitForPageLoad(driver);
			
	}
}

