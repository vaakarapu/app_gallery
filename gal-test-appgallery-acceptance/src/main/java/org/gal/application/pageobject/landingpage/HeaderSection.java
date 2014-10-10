package org.gal.application.pageobject.landingpage;

import java.util.List;

import org.familysearch.idx.testframework.selenium.fw.FwBasicSection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderSection extends FwBasicSection{
	
	//************By's**************//
	private By mainNavigationBoxBy = By.cssSelector("[class='main-nav-wrapper'] ul");
	private By familyTreeNavTabBy = By.cssSelector("li a[href='/tree/']");
	private By memoriesNavTabBy = By.cssSelector("li a[href='/photos/']");
	private By searchNavTabBy = By.cssSelector("li a[href='/search']");
	private static final By signInLinkBy = By.cssSelector("[class*='desktop-nav'] a[id='sign-in']");//By.id("sign-in");
	
	
	//************constructors**************//
		protected HeaderSection(WebDriver driver) {
			// global-pri-nav-container
			// super(driver,By.cssSelector("[class*='ddPopover']"));
			super(driver,By.cssSelector("[class*='global-pri-nav-container']"));

		}
		
		//************M Methods**************//
		public WebElement mMainNavigationBox(){
			return findElement(mainNavigationBoxBy);
		}
		public WebElement mFamilyTreeNavTab(){
			return mMainNavigationBox().findElement(familyTreeNavTabBy);
		}
		public WebElement mPhotosNavTab(){
			return mMainNavigationBox().findElement(memoriesNavTabBy);
		}
		public WebElement mSearchNavTab(){
			return mMainNavigationBox().findElement(searchNavTabBy);
		}
		public WebElement mSignInLink(){
			// Since this is an ID, don't use context to find it.
			// Use the driver.  This will be more robust.
			return driver.findElement(signInLinkBy);
		}
		public WebElement mSignOutLink() {
			return findElement(By.cssSelector("[data-i18n-bind*='SignOut']"));
		}
		public WebElement mDisplayName() {
			// TODO: KLUDGE
			// This is a work around to get around a problem with the header.  There are now two 
			// instances of nav-display-name ids.  This is a fix to get around the problem until
			// the problem is fixed.
			
			WebElement displayNameElement = null;
			List<WebElement> displayNameElems = driver.findElements(By.id("nav-display-name"));
			for ( WebElement elem: displayNameElems ) {
				if ( elem.isDisplayed() ) {
					displayNameElement = elem;
					break;
				}
			}
			
			// There is a possibility that this will be null, which will cause the test
			// to fail.  But this is just a temporary workaround.
			return displayNameElement;	
			
			// End of Kludge.
			
			
			// The original way, which will be restored.
			//return findElement(By.id("nav-display-name"));
		}
		
		
		//************Instance Methods**************//
		public static HeaderSection getInstance(WebDriver driver){
			return new HeaderSection(driver);
		}
		public static By getBySignInLink() {
			return signInLinkBy;
		}
		public By getByMainNavigationBox() {
			return mainNavigationBoxBy;
		}
		public void clickSignInLink(){
			mSignInLink().click();
		}
	}
