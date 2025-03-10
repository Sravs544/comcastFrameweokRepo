package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage {

	        WebDriver driver;
			
			public LeadsPage(WebDriver driver) {
				this.driver=driver;
				PageFactory.initElements(driver, this);
			}
			//object identification
			
		    @FindBy(linkText="Leads")
		    private WebElement leadlnk;
		    
		    @FindBy(xpath="//img[@title='Create Lead...']")
		    private WebElement imgbtn;
		    
		    @FindBy(name="lastname")
		    private WebElement lastnameEdt;
		    
		    @FindBy(name="company")
		    private WebElement companyEdt;
		    
		    @FindBy(xpath="//input[@title='Save [Alt+S]']")
		    private WebElement saveBtn;

			public WebElement getLeadlnk() {
				return leadlnk;
			}

			public WebElement getImgbtn() {
				return imgbtn;
			}

			public WebElement getLastnameEdt() {
				return lastnameEdt;
			}

			public WebElement getCompanyEdt() {
				return companyEdt;
			}

			public WebElement getSaveBtn() {
				return saveBtn;
			}
		    
		    


		}



