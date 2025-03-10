package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//object identification


	@FindBy(linkText="Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText="Contacts")
	private WebElement contactlnk;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignlnk;
	
	@FindBy(linkText="More")
	private WebElement morelnk;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signoutlnk;  
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headertxt;
	
	//Object Encapsulation

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactlnk() {
		return contactlnk;
	}
	
	public WebElement getSignoutlnk() {
		return signoutlnk;
	}
	
	
	public WebElement getHeadertxt() {
		return headertxt;
	}

	//business lib
	public void navigateToCampaignPage() {
		Actions act=new Actions(driver);
		
		act.moveToElement(morelnk).perform();
		campaignlnk.click();
	
	}
	
	public void logout() {
		Actions act=new Actions(driver);
		act.moveToElement(adminImg).perform();
		signoutlnk.click();
	}

	

	

}
