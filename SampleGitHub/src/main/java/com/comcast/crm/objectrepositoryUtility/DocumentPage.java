package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentPage {

	WebDriver driver;

	public DocumentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// object identification
	@FindBy(linkText = "Documents")
	private WebElement doclnk;

	@FindBy(xpath = "//img[@title='Create Document...']")
	private WebElement docimg;

	@FindBy(name = "notes_title")
	private WebElement titleEdt;
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headertxt;
	

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;

	public WebElement getHeadertxt() {
		return headertxt;
	}

	public WebElement getDoclnk() {
		return doclnk;
	}

	public WebElement getDocimg() {
		return docimg;
	}

	public WebElement getTitleEdt() {
		return titleEdt;
	}
	
	public WebElement getSavebtn() {
		return savebtn;
	}
	
}
