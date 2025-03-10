package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contactpage {

	WebDriver driver;

	public Contactpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// object identification

	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement contactimg;
	@FindBy(name = "lastname")
	private WebElement lastnametxt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headertxt;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement plusbtn;
	
	@FindBy(name="support_start_date")
	private WebElement suppdateEdt;
	
	@FindBy(name="support_end_date")
	private WebElement suppEndEdt;

	public WebElement getSuppdateEdt() {
		return suppdateEdt;
	}

	public WebElement getSuppEndEdt() {
		return suppEndEdt;
	}

	public WebElement getPlusbtn() {
		return plusbtn;
	}

	public WebElement getHeadertxt() {
		return headertxt;
	}

	public WebElement getContactimg() {
		return contactimg;
	}

	public WebElement getLastnametxt() {
		return lastnametxt;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	//provide action business library which is used for specific method

	
	public void ContactDate(String lastname, String supportDate, String EndDate) {
		lastnametxt.sendKeys(lastname);
		suppdateEdt.clear();
		suppdateEdt.sendKeys(supportDate);
		suppEndEdt.clear();
		suppEndEdt.sendKeys(EndDate);
		savebtn.click(); //same name as per above arguments
	}
	
}
