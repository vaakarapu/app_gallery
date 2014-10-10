package org.gal.application.pageobject.util;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import org.gal.application.pageobject.landingpage.MainLandingPage;
import org.gal.application.pageobject.signinpage.MainSignInPage;

public class GalAppRunner {
	
	 private static final Logger logger = org.apache.log4j.Logger.getLogger( GalAppRunner.class );
	    
	    
	    // Each instance needs its own driver
	    private WebDriver driver;
	    private String startUrl;
	    
	    
	    
	    // 
	    // Constructor
	    // 
	    
	    public GalAppRunner() {
	        // Just keeps the default values.
	    }
	    
	    
	    public GalAppRunner(String startUrl) {
	        this.startUrl = startUrl;
	    }

	    
	    //
	    // Kind of like a Builder pattern
	    //  
	    
	    public static GalAppRunner initializeApplication() {
	        return new GalAppRunner();
	    }
	    
	    public GalAppRunner withDriver(WebDriver driver) {
	        this.driver = driver;
	        return this;
	    }
	    
	    public GalAppRunner andUrl(String startUrl) {
	        this.startUrl = startUrl;
	        return this;
	    }
	    
	    public GalAppRunner andStartApp() {
	        return startApp();
	    }
	    
	    // Members and member getter methods
	    public WebDriver getDriver() {
	        return driver;
	    }

	    
	    public String getStartUrl() {
	        return startUrl;
	    }
	    
	    // Methods
	    // 
	    
	    
	    public GalAppRunner startApp() {
	        // Assumes driver and start url have been set.
	        
	        // TODO:  If there are failures, this should return an exception.
	        
	        // The get() doesn't return until the page is loaded.  But it doesn't account
	        // for all ajax stuff, so the page might still be loading.
	    
	    	setWindowSize( new Dimension(1560, 1024));
	        logger.info("Starting Web Indexing application with URL: " + startUrl);
	        driver.get( startUrl );
	        
	        logger.info("Application is loaded");
	        

	        
	        // TODO: This should be moved to a different method.
	        //setWindowSize( new Dimension(1560, 674));//getCurrentWindowSize().width));
	        //logger.info("Setting the page size.");

	        return this;
	        
	    }
	    
	    public void closeApp() {
	        logger.info("Closing the Admin Client Application by shutting down the browser.");
	        // Checking in case there was a major problem, and I couldn't even get the driver.
	        if ( driver != null ) {
	            //driver.close();  // Close the page.
	            driver.quit();  // Quit the applicaton.
	            // Set the driver to null to indicate that it shouldn't be used again.
	            logger.info("Browser Closed.");
	        } 
	        else {
	            logger.info("Driver was set to null, so quit() wasn't called.");
	        }
	            
	        
	    }
	    
	    public Dimension getCurrentWindowSize() {
	        return getDriver().manage().window().getSize();
	        
	    }
	        
	    public void setWindowSize(Dimension newSize) {
	        driver.manage().window().setSize( newSize);
	    }

	    
	    /*
	     *  Load a specific page.  A convenience method to hide the driver.
	     */
	    public void loadPage(String url) {
	        driver.get(url);
	    }
	    
	    
	    public MainSignInPage getMainSignInPage()
	    {
	        return MainSignInPage.getInstance(driver);
	    }
	    
	    public MainLandingPage getMainLandingPage()
	    {
	        return MainLandingPage.getInstance(driver);
	    }
	        



}
