package org.gal.application.pageobject.signinpage;

import org.familysearch.idx.testframework.selenium.fw.FwBasicSection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInBoxSection extends FwBasicSection{
	
	//************By's**************//
	protected By bySignInForm = By.id("eventForm");
	
	//************constructors**************//
	protected SignInBoxSection(WebDriver driver) {
		super(driver,By.className("sign-in-box"));
	}
	
	//************M Methods**************//
	public WebElement mUserName(){
		return findElement(By.id("userName"));
	}
	public WebElement mPassword(){
		return findElement(By.id("password"));
	}
	public WebElement mSinInButton(){
		return findElement(By.id("login"));
	}
	public WebElement mSignInForm() {
		return findElement( bySignInForm );
	}
	
	//************Instance Methods**************//
	public static SignInBoxSection getInstance(WebDriver driver){
		return new SignInBoxSection(driver);

	}
	
}
