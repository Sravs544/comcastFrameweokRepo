package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationFilterPage {

	WebDriver driver;

	public OrganizationFilterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	// object identification

	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	@FindBy(name="search_field")
	private WebElement searchdd;
	
	@FindBy(name="submit")
	private WebElement searchbtn;

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchdd() {
		return searchdd;
	}

	public WebElement getSearchbtn() {
		return searchbtn;
	}
	
}
