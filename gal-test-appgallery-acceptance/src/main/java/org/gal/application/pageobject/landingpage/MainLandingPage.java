package org.gal.application.pageobject.landingpage;

import org.familysearch.idx.testframework.selenium.fw.FwBasicWebPage;
import org.openqa.selenium.WebDriver;

public class MainLandingPage extends FwBasicWebPage {

	//************constructors**************//
	protected MainLandingPage(WebDriver driver) {
		super(driver);
	}
	
	//************Instance Methods**************//
	public static MainLandingPage getInstance(WebDriver driver) {
		return new MainLandingPage(driver);
	}
	public HeaderSection getHeaderSection(){
		return HeaderSection.getInstance(driver);
	}
	
}
