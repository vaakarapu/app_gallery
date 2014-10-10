package org.gal.test.appgallery.util;

public class UserInfo {

	String firstName;
	String lastName;
	String displayName;
	String email;
	String userName;
	String password;

	public UserInfo() {

	}
	
	public UserInfo(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public UserInfo withUserName(String userName) {
		this.userName = userName;
		return this;
	}
	
	public UserInfo andPassword(String password) {
		this.password = password;
		return this;
	}
	
	
}