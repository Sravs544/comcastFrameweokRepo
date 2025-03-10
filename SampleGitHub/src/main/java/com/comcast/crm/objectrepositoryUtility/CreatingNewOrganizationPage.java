package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {

WebDriver driver;
	
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//object identification

	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDB;
	
	@FindBy(name="accounttype")
	private WebElement typeDB;
	
	@FindBy(id="phone")
	private WebElement phonetxt;
	
	@FindBy(name="rating")
	private WebElement ratingdd;
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headertxt;


	public WebElement getIndustryDB() {
		return industryDB;
	}

	public WebElement getTypeDB() {
		return typeDB;
	}

	public WebElement getPhonetxt() {
		return phonetxt;
	}

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public WebElement getRatingdd() {
		return ratingdd;
	}
	

	public WebElement getHeadertxt() {
		return headertxt;
	}

	//business method here we are entering text n click on save
	public void createOrg(String coloumn1Data) {
		orgNameEdt.sendKeys(coloumn1Data);
		//saveBtn.click();
	}
		
   public void createOrg(String  coloumn1Data, String industry) {//if we want to enter industry also
	   Select sel=new Select(industryDB);
	   sel.selectByContainsVisibleText(industry);
   }
   
   public void createOrg(String coloumn1Data, String industry, String type ) {
	   Select sel=new Select(typeDB);
	   sel.selectByValue(type);
	   saveBtn.click();
   }
	   
	  public void createOrg1(String rating) {
		  
		  Select sel=new Select(ratingdd);
		  sel.selectByValue(rating);
		  saveBtn.click();
	  }
	   

   }
	
	


